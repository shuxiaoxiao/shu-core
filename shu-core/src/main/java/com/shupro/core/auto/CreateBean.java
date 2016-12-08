package com.shupro.core.auto;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;

import com.shupro.core.utils.db.JdbcUtil;

public class CreateBean {

	private String driver;
	private String url;
	private String username;
	private String password;
	private String databaseName;

	/* 换行 */
	private String rn = "\r\n";
	/* 1个空位符 */
	private String space_1t = "\t";
	/* 2个空位符 */
	private String space_2t = "\t\t";
	/* 3个空位符 */
	private String space_3t = "\t\t\t";
	/* 4个空位符 */
	private String space_4t = "\t\t\t\t";
	/* 换行+空位符 */
	private String rt = rn + space_1t;
	/* 换行+2个空位符 */
	private String rn2t = rn + space_2t;
	
	String SQLTables = "show tables";
	private String method;
	private String argv;
	// static String selectStr;
	// static String from;

	public CreateBean(String driver, String url, String username, String password, String databaseName) {
		this.driver = driver;
		this.url = url;
		this.username = username;
		this.password = password;
		this.databaseName = databaseName;
	}

	/**
	 * 
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<String> getTables() throws SQLException {
		Connection conn = JdbcUtil.getConn(driver, url, username, password);
		PreparedStatement ps = conn.prepareStatement(this.SQLTables);
		ResultSet rs = ps.executeQuery();

		List<String> list = new ArrayList<>();
		while (rs.next()) {
			String tableName = rs.getString(1);
			list.add(tableName);
		}

		JdbcUtil.releaseConn(conn, ps, rs);
		return list;
	}

	/**
	 * 获取表字段
	 * 
	 * @param tableName
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<ColumnData> getColumnDatas(String tableName) throws SQLException, ClassNotFoundException {

		String SQLColumns = "select column_name ,data_type,column_comment,0,0,character_maximum_length,is_nullable nullable "
				+ "from information_schema.columns where table_name =  '" + tableName + "' " + "and table_schema = '"
				+ databaseName + "'";

		Connection conn = JdbcUtil.getConn(driver, url, username, password);
		PreparedStatement ps = conn.prepareStatement(SQLColumns);
		List<ColumnData> columnList = new ArrayList<>();
		ResultSet rs = ps.executeQuery();

		StringBuffer str = new StringBuffer();
		StringBuffer getset = new StringBuffer();
		while (rs.next()) {
//			String name = rs.getString(1).toLowerCase();//为了命名规则不转换
			String name = rs.getString(1);
			String type = rs.getString(2);
			String comment = rs.getString(3);
			String precision = rs.getString(4);
			String scale = rs.getString(5);
			String charmaxLength = (rs.getString(6) == null) ? "" : rs.getString(6);
			String nullable = TableConvert.getNullAble(rs.getString(7));
			type = getType(type, precision, scale);

			ColumnData cd = new ColumnData();
			cd.setColumnName(name);
			cd.setDataType(type);
			cd.setColumnType(rs.getString(2));
			cd.setColumnComment(comment);
			cd.setPrecision(precision);
			cd.setScale(scale);
			cd.setCharmaxLength(charmaxLength);
			cd.setNullable(nullable);
			formatFieldClassType(cd);

			columnList.add(cd);
		}
		this.argv = str.toString();
		this.method = getset.toString();

		JdbcUtil.releaseConn(conn, ps, rs);
		return columnList;
	}

	/**
	 * 查询表里面的字段
	 * 
	 * @param tableName
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public String getBeanFeilds(String tableName) throws SQLException, ClassNotFoundException {

		List<ColumnData> dataList = getColumnDatas(tableName);

		StringBuffer str = new StringBuffer();
		StringBuffer getset = new StringBuffer();
		for (Iterator<ColumnData> localIterator = dataList.iterator(); localIterator.hasNext();) {
			ColumnData d = localIterator.next();
			String name = d.getColumnName();
			String type = d.getDataType();
			String comment = d.getColumnComment();

			str.append(rt);//空一行
			str.append("private ").append(type + " ").append(name).append(";// ").append(comment);
//			String maxChar = name.substring(0, 1).toUpperCase();
//			String method = maxChar + name.substring(1, name.length());
			String method = WordUtils.capitalize(name);
			getset.append(rt).append("public ").append(type + " ").append("get" + method + "() {");
			getset.append(rt).append(space_1t).append("return this.").append(name).append(";");
			getset.append(rt).append("}");
			getset.append(rt).append("public void ").append("set" + method + "(" + type + " " + name + ") {");
			getset.append(rt).append(space_1t).append("this." + name + "=").append(name).append(";");
			getset.append(rt).append("}");
		}
		this.argv = str.toString();
		this.method = getset.toString();

		return this.argv + rn + this.method;
	}

	/**
	 * 生成easyui需要的类型
	 * 
	 * @param columnt
	 */
	private void formatFieldClassType(ColumnData columnt) {
		Map<String, String> easyuiTypeMap = new HashMap<>();
		easyuiTypeMap.put("int", "easyui-numberbox");
		easyuiTypeMap.put("number", "easyui-numberbox");
		easyuiTypeMap.put("float", "easyui-numberbox");
		easyuiTypeMap.put("double", "easyui-numberbox");
		easyuiTypeMap.put("decimal", "easyui-numberbox");
		easyuiTypeMap.put("datetime", "easyui-datetimebox");
		easyuiTypeMap.put("time", "easyui-datetimebox");
		easyuiTypeMap.put("date", "easyui-datebox");

		String fieldType = columnt.getColumnType();
		String classType = easyuiTypeMap.get(fieldType);
		if (classType == null) {
			classType = "easyui-textbox";
		}
		columnt.setClassType(classType);

		if ("N".equals(columnt.getNullable())) {
			columnt.setOptionType("required:true");
		}
		// 由于easyui-numberbox的precision属性默认值是2

		// String fieldType = columnt.getColumnType();
		// String scale = columnt.getScale();
		// if (("datetime".equals(fieldType)) || ("time".equals(fieldType))) {
		// columnt.setClassType("easyui-datetimebox");
		// } else if ("date".equals(fieldType)) {
		// columnt.setClassType("easyui-datebox");
		// } else if ("int".equals(fieldType)) {
		// columnt.setClassType("easyui-numberbox");
		// } else if ("number".equals(fieldType)) {
		// columnt.setClassType("easyui-numberbox");
		// if ((StringUtils.isNotBlank(scale)) && (Integer.parseInt(scale) > 0))
		// {
		// if (StringUtils.isNotBlank(columnt.getOptionType())) {
		// columnt.setOptionType(columnt.getOptionType() + "," +
		// "precision:2,groupSeparator:','");
		// } else {
		// columnt.setOptionType("precision:2,groupSeparator:','");
		// }
		// }
		// } else if (("float".equals(fieldType)) ||
		// ("double".equals(fieldType)) || ("decimal".equals(fieldType))) {
		// columnt.setClassType("easyui-numberbox");
		// if (StringUtils.isNotBlank(columnt.getOptionType())){
		// columnt.setOptionType(columnt.getOptionType() + "," +
		// "precision:2,groupSeparator:','");
		// }else{
		// columnt.setOptionType("precision:2,groupSeparator:','");
		// }
		// } else {
		// columnt.setClassType("easyui-textbox");
		// }
	}

	/**
	 * 生成Java数据类型
	 * 
	 * @param dataType
	 * @param precision
	 * @param scale
	 * @return
	 */
	public String getType(String dataType, String precision, String scale) {
		Map<String, String> typeMap = new HashMap<>();
		// typeMap.put("char", "String");
		// typeMap.put("varchar", "String");
		// typeMap.put("nvarchar", "String");
		typeMap.put("int", "Integer");
		typeMap.put("bigint", "Integer");
		typeMap.put("float", "Float");
		typeMap.put("double", "Double");
		typeMap.put("decimal", "java.math.BigDecimal");
		typeMap.put("date", "java.util.Date");
		typeMap.put("datetime", "java.util.Date");
		typeMap.put("time", "java.sql.Timestamp");
		typeMap.put("clob", "java.sql.Clob");

		String key = dataType.toLowerCase();
		if (key.contains("char")) {
			dataType = "String";
		} else if (key.contains("number")) {
			if ((StringUtils.isNotBlank(scale)) && (Integer.parseInt(scale) > 0))
				dataType = "java.math.BigDecimal";
			else if ((StringUtils.isNotBlank(precision)) && (Integer.parseInt(precision) > 6))
				dataType = "Long";
			else
				dataType = "Integer";
		} else {
			dataType = typeMap.get(key);
		}

		return dataType;
	}

	public void getPackage(int type, String createPath, String content, String packageName, String className,
			String extendsClassName, String[] importName) throws Exception {

		if (packageName == null)
			packageName = "";

		StringBuffer sb = new StringBuffer();
		sb.append("package ").append(packageName).append(";");
		sb.append(rn + rn);
		for (int i = 0; i < importName.length; ++i){
			sb.append("import ").append(importName[i]).append(";").append(rn);
		}
		sb.append(rn);
		sb.append("/**\r\n *  entity. @author wolf Date:"
				+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "\r\n */");
		sb.append("\r\n");
		sb.append("\r\npublic class ").append(className);
		if (extendsClassName != null)
			sb.append(" extends ").append(extendsClassName);

		if (type == 1)
			sb.append(" ").append("implements java.io.Serializable {\r\n");
		else
			sb.append(" {\r\n");

		sb.append(rt);
		sb.append("private static final long serialVersionUID = 1L;\r\n\t");
		String temp = className.substring(0, 1).toLowerCase();
		temp = temp + className.substring(1, className.length());
		if (type == 1)
			sb.append("private " + className + " " + temp + "; // entity ");

		sb.append(content);
		sb.append("\r\n}");
		System.out.println(sb.toString());
		createFile(createPath, "", sb.toString());
	}

	public void createFile(String path, String fileName, String str) throws IOException {

		FileWriter writer = new FileWriter(new File(path + fileName));
		writer.write(new String(str.getBytes("utf-8")));
		writer.flush();
		writer.close();
	}

	public Map<String, Object> getAutoCreateSql(String tableName) throws Exception {

		Map<String, Object> sqlMap = new HashMap<>();
		List<ColumnData> columnDatas = getColumnDatas(tableName);
		String columns = getColumnSplit(columnDatas);
		String[] columnList = getColumnList(columns);
		String columnFields = getColumnFields(columns);
		String insert = "insert into " + tableName + "(" + columns.replaceAll("\\|", ",") + ")\n\t\t values(#{"
				+ columns.replaceAll("\\|", "},#{") + "})";
		String update = getUpdateSql(tableName, columnList);
		String updateSelective = getUpdateSelectiveSql(tableName, columnDatas);
		String selectById = getSelectByIdSql(tableName, columnList);
		String delete = getDeleteSql(tableName, columnList);
		String deleteByIds = getDeleteByIdsSql(tableName, columnList);
		
		sqlMap.put("columnList", columnList);
		sqlMap.put("columnFields", columnFields);
		sqlMap.put("insert", insert.replace("#{createTime}", "now()").replace("#{updateTime}", "now()"));
		sqlMap.put("update", update.replace("#{createTime}", "now()").replace("#{updateTime}", "now()"));
		sqlMap.put("delete", delete);
		sqlMap.put("deleteByIds", deleteByIds);
		sqlMap.put("updateSelective", updateSelective);
		sqlMap.put("selectById", selectById);

		return sqlMap;
	}

	public String getDeleteByIdsSql(String tableName, String[] columnsList) throws SQLException {

		StringBuffer sb = new StringBuffer();
		sb.append("delete from ");
		sb.append(tableName).append(" where ").append(columnsList[0]).append(" in ");
		sb.append(rt).append("<foreach collection=\"array\" item=\"id\" open=\"(\" close=\")\" separator=\",\"> ");
		sb.append(rn2t).append(" #{").append(columnsList[0]).append("}");
		sb.append(rt).append("</foreach>");
		return sb.toString();
	}
	
	public String getDeleteSql(String tableName, String[] columnsList) throws SQLException {
		
		StringBuffer sb = new StringBuffer();
		sb.append("delete from ");
		sb.append(tableName).append(" where ");
		sb.append(columnsList[0]).append(" = #{").append(columnsList[0]).append("}");
		return sb.toString();
	}

	public String getSelectByIdSql(String tableName, String[] columnsList) throws SQLException {

		StringBuffer sb = new StringBuffer();
		sb.append("select <include refid=\"Base_Column_List\" />");
		sb.append(rn).append(space_1t + "from ").append(tableName);
		sb.append(rn + space_1t +"where ").append(columnsList[0]).append(" = #{").append(columnsList[0]).append("}");
		return sb.toString();
	}

	public String getColumnFields(String columns) throws SQLException {

		String fields = columns;
		if ((fields != null) && (!("".equals(fields))))
			fields = fields.replaceAll("[|]", ",");

		return fields;
	}

	public String[] getColumnList(String columns) throws SQLException {

		String[] columnList = columns.split("[|]");
		return columnList;
	}

	public String getUpdateSql(String tableName, String[] columnsList) throws SQLException {

		StringBuffer sb = new StringBuffer();

		for (int i = 1; i < columnsList.length; ++i) {
			String column = columnsList[i];
			if ("CREATETIME".equals(column.toUpperCase()))
				continue;

			if ("UPDATETIME".equals(column.toUpperCase()))
				sb.append(column + "=now()");
			else
				sb.append(column + "=#{" + column + "}");

			if (i + 1 < columnsList.length)
				sb.append(",");
		}

		String update = "update " + tableName + " set " + sb.toString() + rn + space_2t + "where " + columnsList[0] + "=#{"
				+ columnsList[0] + "}";
		return update;
	}

	public String getUpdateSelectiveSql(String tableName, List<ColumnData> columnList) throws SQLException {

		StringBuffer sb = new StringBuffer();
		ColumnData cd = (ColumnData) columnList.get(0);
		sb.append(space_2t + "<trim  suffixOverrides=\",\" >");
		sb.append(rn);
		for (int i = 1; i < columnList.size(); ++i) {
			ColumnData data = (ColumnData) columnList.get(i);
			String columnName = data.getColumnName();
			sb.append(space_3t + "<if test=\"").append(columnName).append(" != null ");

			if ("String" == data.getDataType())
				sb.append("and ").append(columnName).append(" != ''");

			sb.append(" \">");
			sb.append(rn + space_4t +columnName + "=#{" + columnName + "},");
			sb.append(rn + space_3t + "</if>");
			sb.append(rn);
		}
		sb.append(space_2t + "</trim>");
		String update = "update " + tableName + " set " + rn + sb.toString() + rn + space_2t + "where " + cd.getColumnName() + "=#{"
				+ cd.getColumnName() + "}";
		return update;
	}

	public String getColumnSplit(List<ColumnData> columnList) throws SQLException {

		StringBuffer commonColumns = new StringBuffer();
		for (Iterator<ColumnData> localIterator = columnList.iterator(); localIterator.hasNext();) {
			ColumnData data = localIterator.next();
			commonColumns.append(data.getColumnName() + "|");
		}
		return commonColumns.delete(commonColumns.length() - 1, commonColumns.length()).toString();
	}
}