package ait.team.java.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

class Employees {
	String name;
	String position;
	int phone;
	Date test;

	public Employees(String name, String position, int phone, Date test) {
		super();
		this.name = name;
		this.position = position;
		this.phone = phone;
		this.test = test;
	}
}

class Customer {
	String name;
	int phone;
	String address;
	Date test;

	public Customer(String name, int phone, String address, Date test) {
		super();
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.test = test;
	}

}

public class UnitTest extends CommonUtils {
	public static void main(String[] args) throws ParseException, IllegalArgumentException, IllegalAccessException {
		// TODO Auto-generated method stub
		// #2
		String date = "2021/06/05 09:08:12";
		//System.out.println(formatDateString(date));

		// #4
		String container = "CSQU3054383";
		//System.out.println(checkContainerCode(container)); 
		
		String dateProcess = "2021/06/01 09:30:56";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/mm/dd");
		Date date1 = formatter.parse(dateProcess);
		String tomorrow = formatter.format( addDays(date1, 1));
		String yesterday = formatter.format( addDays(date1, -1));
		
		System.out.println("Tomorrow: " + date1); 
		//System.out.println("Yesterday: " + yesterday); 
		
//		List<Date> totalDayOfWeek = getDateOfWeek(date1);
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(date1);
//		
//		String a = formatter.format(getDateOfWeek(date1).get(0));
//		Date date2 = formatter.parse(a);
//		cal.setTime(date2);
//		for(int i=0; i<totalDayOfWeek.size(); i++)
//			System.out.println(totalDayOfWeek.get(i));  
		
	}

}
