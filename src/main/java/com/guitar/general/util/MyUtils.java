package com.guitar.general.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyUtils {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static String formatDate(Date date) {
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		String strDate = df.format(date);

		return strDate;
	}
}
