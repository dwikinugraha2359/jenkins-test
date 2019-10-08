package com.media2359.helper;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.testng.Reporter;

/**
 * Utils
 */
public class HelperFunc {
    public HelperFunc() {
    }

    // to clean download dir
    public void cleanDownloadDir() throws IOException {
        // clean all file on download directory
        FileUtils.cleanDirectory(new File("/Users/2359id/developments/office/GoldBell-Web-Automation/download/"));
        System.out.println("Successfully clean on download dir");
    }

    // to clean pic result of test
    public static void deleteDirectoryFile() throws IOException {
        // to delete directory
        FileUtils.deleteDirectory(new File("test-output/pic/" + generateDateNHour()));
        System.out.println("Successfully delete pic dir");
    }

    // to generate date with hour
    public static String generateDateNHour() {
        DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy-HH");
        Date date = new Date();
        return dateFormat.format(date);
    }

    // Get list of week
    public static List<String> getListofWeek(int weekOfYear) {
        List<String> result = new ArrayList<String>();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.WEEK_OF_YEAR, weekOfYear);
        SimpleDateFormat formatter = new SimpleDateFormat("EEE dd");
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        result.add(formatter.format(cal.getTime()));
        for (int i = 1; i <= 7; i++) {
            cal.add(Calendar.DAY_OF_WEEK, 1);
            result.add(formatter.format(cal.getTime()));
        }
        return result;
    }

    // Get list of date week (1 Jul)
    public static List<String> getListofDateWeek(int weekOfYear) {
        List<String> result = new ArrayList<String>();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.WEEK_OF_YEAR, weekOfYear);
        SimpleDateFormat formatter = new SimpleDateFormat("d MMM");
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        result.add(formatter.format(cal.getTime()));
        for (int i = 1; i <= 6; i++) {
            cal.add(Calendar.DAY_OF_WEEK, 1);
            result.add(formatter.format(cal.getTime()));
        }
        return result;
    }

    // Get list of week - format yyyy-MM-dd (2019-01-13)
    public static List<String> getListofWeekyyyMMdd(int weekOfYear) {
        List<String> result = new ArrayList<String>();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.WEEK_OF_YEAR, weekOfYear);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        result.add(formatter.format(cal.getTime()));
        for (int i = 1; i <= 7; i++) {
            cal.add(Calendar.DAY_OF_WEEK, 1);
            result.add(formatter.format(cal.getTime()));
        }
        return result;
    }

    // get date (9 Jul)
    public static String getDateString(int weekOfYear, int dayExp) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.WEEK_OF_YEAR, weekOfYear);
        SimpleDateFormat formatter = new SimpleDateFormat("d MMM");
        cal.set(Calendar.DAY_OF_WEEK, dayExp);
        Reporter.log(formatter.format(cal.getTime()));
        return formatter.format(cal.getTime());
    }

    public static String getDateString(Date date, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        // Reporter.log(formatter.format(date));
        return formatter.format(date);
    }

    public static Date convertDateString(String date, String format) throws ParseException {
        return new SimpleDateFormat(format).parse(date);
    }

    // conver date into month year
    public static String dateToMonthYear(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("MMMM yyyy");
        return formatter.format(date);
    }

    // convert date into E, MMM dd (Thu, Jun 20)
    public static String dateToEMMMdd(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("E, MMM dd");
        return formatter.format(date);
    }

    // convert string into date
    public static Date stringToDate(String stringDate) throws ParseException {
        Date resultDate = new SimpleDateFormat("yyyy-MM-dd").parse(stringDate);
        return resultDate;
    }

    // convert string into date
    public static Date stringToDate(String stringDate, String dateFormat) throws ParseException {
        Date resultDate = new SimpleDateFormat(dateFormat).parse(stringDate);
        return resultDate;
    }

    // convert (E, MMM dd yyyy) string into date
    // convert string into date
    public static Date stringDPToDate(String stringDate) throws ParseException {
        Date resultDate = new SimpleDateFormat("E, MMM dd yyyy").parse(stringDate);
        return resultDate;
    }

    // generate random number in range
    public static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}