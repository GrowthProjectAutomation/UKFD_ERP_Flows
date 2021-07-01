package com.qa.pages;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import bsh.ParseException;

public class Datess{
	public static void main(String args[]) throws java.text.ParseException
	{
		Calendar cal = Calendar.getInstance();
        SimpleDateFormat format1 = new SimpleDateFormat("d/M/yyyy");
        String today_date = format1.format(cal.getTime());
        System.out.println(today_date);
        cal.setTime(format1.parse(today_date));
        cal.add(Calendar.DATE,Integer.parseInt("0"));
        String next_day=format1.format(cal.getTime());
        System.out.println(next_day);
	}

}
