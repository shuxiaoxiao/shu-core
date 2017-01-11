package com.shupro.core.utils.lang;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.junit.Test;

public class DateUtilTest {

	@Test
	public void getDate_test1() throws DatatypeConfigurationException {
		Date date = new Date();//2017-01-10 15:10:10  周二 
		//2017-2-1 0:00:00
		Date nextMonthDate = DateUtil.getFirstDayOfNextMonth(date);
		//2016-12-1 0:00:00
		Date lextMonthDate = DateUtil.getFirstDayOfLastMonth(date);
		System.out.println(nextMonthDate.toLocaleString());
		System.out.println(lextMonthDate.toLocaleString());
		
		//2017-1-1 0:00:00
		Date newDate1 =  DateUtil.getFirstDayOfMonth(date, 1);
		Date date2 = DateUtil.strToDate("2016-10-10 11:03:20");
		//2016-1-1 0:00:00
		Date newDate2 =  DateUtil.getFirstDayOfMonth(date2, 1);
		
		//2017-1-1 0:00:00
		Date newDate3 =  DateUtil.getFirstDayOfMonth(date);
		//2016-10-1 0:00:00
		Date newDate4 =  DateUtil.getFirstDayOfMonth(date2);
		
		//2017-1-1 0:00:00
		Date newDate5 =  DateUtil.getFirstDayOfMonth(1);
		System.out.println(newDate1.toLocaleString());
		System.out.println(newDate2.toLocaleString());
		System.out.println(newDate3.toLocaleString());
		System.out.println(newDate4.toLocaleString());
		System.out.println(newDate5.toLocaleString());
		
		//2017-1-11 0:00:00
		Date endTime =  DateUtil.getEndTimeOfDate(date);
		System.out.println(endTime.toLocaleString());
		//2017-1-10 0:00:00
		Date startTime =  DateUtil.getStartTimeOfDate(date);
		System.out.println(startTime.toLocaleString());
	}
	
	@Test
	public void getInts_test1() throws DatatypeConfigurationException {
		Date date = new Date();//2017-01-10 15:10:10  周二 
		int year = DateUtil.getYear(date);
		int month = DateUtil.getMonth(date);
		int day = DateUtil.getDay(date);
		int week = DateUtil.getWeek(date);
		int week2 = DateUtil.getWeek2Zh(date);
		boolean isWeekend = DateUtil.isWeekend(date);
		int daysOfMonth = DateUtil.getDaysOfMonth(date);
		
		System.out.println(year);//2017
		System.out.println(month);//1
		System.out.println(day);//10
		System.out.println(week);//3
		System.out.println(week2);//2
		System.out.println(isWeekend);//false
		System.out.println(daysOfMonth);//31
	}
	
	@Test
	public void timeDiff_test1() throws DatatypeConfigurationException {
		Date date1 = DateUtil.strToDate("2017-01-10 11:03:20");
		Date date2 = DateUtil.strToDate("2017-01-10 11:03:25");
		long diff = DateUtil.timeDiff(date1, date2);
		System.out.println(diff);//-5000
	}
	
	@Test
	public void dateToXmlDate_test1() throws DatatypeConfigurationException {
		Date date = new Date();
		XMLGregorianCalendar xgcal = DateUtil.dateToXmlDate(date);
		System.out.println(xgcal);//2017-01-10T11:43:20.237+08:00
	}
	
	@Test
	public void xmlDateToDate_test1() throws DatatypeConfigurationException {
		GregorianCalendar gcal = new GregorianCalendar();
		XMLGregorianCalendar xgcal= DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
		Date date = DateUtil.xmlDateToDate(xgcal);
		System.out.println(date);//Tue Jan 10 11:40:18 CST 2017
	}
	
	@Test
	public void strToXmlDate_test1() throws DatatypeConfigurationException {
		String dateStr = "2017-01-10 11:26:05";
		XMLGregorianCalendar xmlDate = DateUtil.strToXmlDate(dateStr);
		System.out.println(xmlDate);//2017-01-10T11:26:05.000+08:00
	}
	
	@Test
	public void strToXmlDate_test2() throws DatatypeConfigurationException {
		String dateStr = "2017-01-10 11:26:05.728";
		XMLGregorianCalendar xmlDate = DateUtil.strToXmlDate(dateStr, "yyyy-MM-dd HH:mm:ss.SSS");
		System.out.println(xmlDate);//2017-01-10T11:26:05.728+08:00
	}
	
	@Test
	public void xmlDateToStr_test1() throws DatatypeConfigurationException {
		GregorianCalendar gcal = new GregorianCalendar();
		XMLGregorianCalendar xgcal= DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
		System.out.println(xgcal);//2017-01-10T11:26:05.728+08:00
		String dateStr = DateUtil.xmlDateToStr(xgcal);
		System.out.println(dateStr);//2017-01-10 11:26:05.728
	}
	
	@Test
	public void xmlDateToStr_test2() throws DatatypeConfigurationException {
		GregorianCalendar gcal = new GregorianCalendar();
		XMLGregorianCalendar xgcal= DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
		System.out.println(xgcal);//2017-01-10T11:26:05.728+08:00
		String dateStr = DateUtil.xmlDateToStr(xgcal, "yyyy-MM-dd HH:mm:ss");
		System.out.println(dateStr);//2017-01-10 11:26:05
	}
	
	@Test
	public void dateToStr_test1() {
		Date date = new Date();
		String dateStr = DateUtil.dateToStr(date);
		System.out.println(dateStr);//2017-01-10 10:23:06
	}
	
	@Test
	public void dateToStr_test2() {
		Date date = new Date();
		String dateStr = DateUtil.dateToStr(date, "yyyy-MM-dd");
		System.out.println(dateStr);//2017-01-10 
	}
	
	@Test
	public void strToDate_test1() {
		String dateStr = "";
		//由于需要yyyy-MM-dd HH:mm:ss 格式, 传入yyyy-MM-dd格式不满足,返回null
//		String dateStr = "2017-01-01";
		Date date = DateUtil.strToDate(dateStr);
		System.out.println(date);//null
	}
	
	@Test
	public void strToDate_test2() {
		String dateStr = "2017-01-01 12:00:00";
		Date date = DateUtil.strToDate(dateStr);
		//Sun Jan 01 12:00:00 CST 2017
		System.out.println(date);
	}
	
	@Test
	public void strToDate_test3() {
		String dateStr = "2017-01-01";
		Date date = DateUtil.strToDate(dateStr, "yyyy-MM-dd");
		//Sun Jan 01 00:00:00 CST 2017
		System.out.println(date);
	}

}
