package com.emarsys.utils;

public class DateConverterToDays {
    
    public double dayfinder(int year, int month, int day){
        int[] plusDaysPerMonth = {0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334 };
        int[] plusDaysPerMonthInLeapYear = {0, 31, 60, 91, 121, 152, 182, 213, 244, 274, 305, 335 };
        
        //All the days before the current year
        //To make it a little bit easier I count leap years like every 4 years doesn't matter if it can be divided by 100
        //And also counting the missing days between 1587. October 21st and November 1st
        double sumNumberOfDays = (year * 365) + year / 4;
        
        //Adding current year days
        sumNumberOfDays += year % 4 == 0 ? plusDaysPerMonth[month-1] + day : plusDaysPerMonthInLeapYear[month-1] + day;
        
        return sumNumberOfDays;
    }
}
