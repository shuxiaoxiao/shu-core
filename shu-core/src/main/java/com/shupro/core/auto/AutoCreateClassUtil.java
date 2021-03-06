package com.shupro.core.auto;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.lang3.text.WordUtils;

/**
 * 自动生成代码的工具类
 * @author shu
 */
public class AutoCreateClassUtil {
//	private static String projectPath = SystemUtils.USER_DIR;
	
	/**
	 * jar部分代码生成，注意如果mapping里面有like语句这自己修改下<br>
	 * 使用 content like CONCAT('%',#{content},'%')
	 * 
	 * @param autoModel
	 */
	public static void generate2Jar(AutoModel autoModel) {
		CreateBean createBean = new CreateBean(autoModel.getDiver(), autoModel.getJdbcUrl(), autoModel.getUsername(),
				autoModel.getPassword(), autoModel.getDatabaseName());
		
		String tableName = autoModel.getTableName();
		String str = WordUtils.capitalizeFully(tableName, '_');// 在规则地方转换，首字母都转成大写
		String className = str.replace("_", "");
		String lowerName = WordUtils.uncapitalize(className);
		
		String srcPath = SystemUtils.USER_DIR + "/" + autoModel.getSourceRoot() + "/";
		//
		String pckPath = srcPath + autoModel.getAppPackageUrl() + "/";
		
		String beanPath = "model/" + className + ".java";
		String mapperPath = "dao/" + className + "Mapper.java";
		String sqlMapperPath = "mapping/" + className + "Mapper.xml";
		String servicePath = "service/" + className + "Service.java";
		String serviceImplPath = "service/impl/" + className + "ServiceImpl.java";
		
		Map<String, Object> root = new HashMap<>();
		root.put("className", className);
		root.put("lowerName", lowerName);
		root.put("tableName", tableName);
		root.put("bussPackage", autoModel.getBussPackage());
		root.put("appPackagePrefix", autoModel.getAppPackagePrefix());
		try {
			root.put("feilds", createBean.getBeanFeilds(tableName));
			
			Map sqlMap = createBean.getAutoCreateSql(tableName);
			root.put("columnDatas", createBean.getColumnDatas(tableName));
			root.put("SQL", sqlMap);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		String templateRoot = "/template";
		CommonPageParser.WriterPage(root, templateRoot, "ModelTemplate.ftl", pckPath, beanPath);
		CommonPageParser.WriterPage(root, templateRoot, "DaoTemplate.ftl", pckPath, mapperPath);
		CommonPageParser.WriterPage(root, templateRoot, "MapperTemplate.xml", pckPath, sqlMapperPath);
		CommonPageParser.WriterPage(root, templateRoot, "ServiceTemplate.ftl", pckPath, servicePath);
		CommonPageParser.WriterPage(root, templateRoot, "ServiceImplTemplate.ftl", pckPath, serviceImplPath);
		
		System.out.println("----------------------------代码jar部分生成完毕---------------------------");
	}
	
	/**
	 * war部分代码生成，注意表字段需要添加中文注释<br>
	 * 模板默认是easyUI
	 * 
	 * @param autoModel
	 */
	public static void generate2War(AutoModel autoModel) {
		String templateRoot = "/template2Easyui";
		generate2War(autoModel, templateRoot);
	}
		
	/**
	 * war部分代码生成，注意表字段需要添加中文注释<br>
	 * 
	 * @param autoModel
	 * @param templateRoot 模板目录名，如 template2Easyui 
	 */
	public static void generate2War(AutoModel autoModel, String templateRoot) {
		CreateBean createBean = new CreateBean(autoModel.getDiver(), autoModel.getJdbcUrl(), autoModel.getUsername(),
				autoModel.getPassword(), autoModel.getDatabaseName());
		
		String tableName = autoModel.getTableName();
		String str = WordUtils.capitalizeFully(tableName, '_');// 在规则地方转换
		String className = str.replace("_", "");
		String lowerName = WordUtils.uncapitalize(className);
		//jsp文件前缀部分路径
		String jspSrcPath = SystemUtils.USER_DIR + "/" + autoModel.getJspPath() + "/";
		//java文件的前缀
		String srcPath = SystemUtils.USER_DIR + "/" + autoModel.getSourceRoot() + "/";
		//
		String pckPath = srcPath + autoModel.getAppPackageUrl() + "/";
		
		String controllerPath = "controller/" + className + "Controller.java";
		String jspPath = autoModel.getAppName() + "/" + lowerName + ".jsp";
		
		Map<String, Object> root = new HashMap<>();
		root.put("className", className);
		root.put("lowerName", lowerName);
//		root.put("tableName", tableName);
		root.put("bussPackage", autoModel.getBussPackage());
		root.put("autoModel", autoModel);
		try {
			root.put("columnDatas", createBean.getColumnDatas(tableName));
//			root.put("feilds", createBean.getBeanFeilds(tableName));
//			Map sqlMap = createBean.getAutoCreateSql(tableName);
//			root.put("SQL", sqlMap);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		CommonPageParser.WriterPage(root, templateRoot, "ControllerTemplate.ftl", pckPath, controllerPath);
		CommonPageParser.WriterPage(root, templateRoot, "jspTemplate.ftl", jspSrcPath, jspPath);
		
		System.out.println("----------------------------代码war部分生成完毕---------------------------");
	}
	
}
