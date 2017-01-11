package com.shupro.core.utils.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.shupro.core.utils.MyBeanUtil;
import com.shupro.core.utils.lang.DateUtil;

public class ExcelUtilTest {

	@Test
	public void read_test() throws IOException {
		String filePath = "D:\\autotemp\\aa.xlsx";
		List<String[]> list =  ExcelUtil.read(filePath);
		//对获得的list 进行操作，一般实际可能是进行增加操作，此处是打印操作
		for (String[] stringArr : list) {
			for (String str : stringArr) {
				System.out.print(str + "  ");
			}
			System.out.println();
		}
	}
	
//	//需要用到具体的sysUserService.select2Excel方法，暂时屏蔽
//	@Test
//	public void write_test() throws IOException {
//		String fileName = "users-"+ DateUtil.today2YyMMddHHmmss() +".xls";
//		String sheetName = "用户";
//		String[] titles = new String[]{"名称","登录名","性别","手机号","备用号","地址","部门id","入职时间","离职时间"};
//		String[] fields = new String[]{"name","loginName","sexName","phoneNum","phoneNum2","address","deptid","createtime","leavetime"};
////		Map<String, Object> argsMap = JsonUtil.jsonStr2Map(teamInfo);
//		Map<String, Object> argsMap = new HashMap<>();
//		argsMap.put("state", "1");
//		
//		List<Map<String, Object>> list = sysUserService.select2Excel(argsMap);
//		ExcelInfo excelInfo = new ExcelInfo(fileName, sheetName, titles, fields, list);
//		//excelInfo.setPageSize(5);//默认是20条
//		String outFilePath = "";
//		OutputStream outStream = new FileOutputStream(new File(outFilePath));
//		//不分页的
//		ExcelUtil.write(excelInfo, outStream);
//		//分页的,不设置pageSize,默认是20条
//		//ExcelUtil.write2Page(excelInfo, outStream);
//	}

}
