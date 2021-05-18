package com.emarsys.utils;

public class DateTimeValidator {
    public boolean dateTimeIsValid(int[] dateTime) {
        
        int year = dateTime[0];
        int months = dateTime[1];
        int days = dateTime[2];
        int hours = dateTime[3];
        int minutes = dateTime[4];
        boolean leapYear = year % 4 == 0;
        int[] daysInMonths = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int[] daysInMonthsLeapYear = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        
        if (year < 0) {return false;}
        if (months > 12 || months < 1) {return false;}
        if (hours >= 17 || hours < 9) {return false;}
        if (minutes >= 60 || minutes < 0) {return false;}

        if (leapYear) {
            if (daysInMonthsLeapYear[months - 1] < days || 1 > days) {
                return false;
            }
        } else {
            if (daysInMonths[months - 1] < days || 1 > days) {
                return false;
            }
        }
        
        return true;
    }
}
