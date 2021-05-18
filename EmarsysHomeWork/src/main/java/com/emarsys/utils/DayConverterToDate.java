package com.emarsys.utils;

public class DayConverterToDate {
    
    public int[] dayConvertToDate(double numberOfDays) {
        int[] output = new int[3];
        int years = (int) (numberOfDays / 365.25);
        int daysRemainder = (int) (numberOfDays % 365.25);
        if (daysRemainder == 0 && (years - 1) % 4 == 0) {
            years--;
            daysRemainder += 366;
        } else if (daysRemainder == 0) {
            years--;
            daysRemainder += 365;
        }
        output[0] = years;
    
        int[] daysInMonths = {0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334};
        int[] daysInMonthsLeapYear = {0, 31, 60, 91, 121, 152, 182, 213, 244, 274, 305, 335};
        
        if (years % 4 == 0){
            
            for (int i = 0; i < daysInMonthsLeapYear.length; i++) {
                if (i == daysInMonthsLeapYear.length - 1) {
                    output[1] = i + 1;
                    output[2] = daysRemainder - daysInMonthsLeapYear[i];
        
                } else if (daysRemainder <= daysInMonthsLeapYear[i + 1]) {
                    output[1] = i + 1;
                    output[2] = daysRemainder - daysInMonthsLeapYear[i];
                    break;
    
                }
            }
        } else {
            for (int i = 0; i < daysInMonths.length; i++) {
                if (i == daysInMonths.length - 1) {
                    output[1] = i + 1;
                    output[2] = daysRemainder - daysInMonths[i];
    
                } else if (daysRemainder <= daysInMonths[i+1]) {
                    output[1] = i + 1;
                    output[2] = daysRemainder - daysInMonths[i];
                    break;
    
                }
            }
            
        }


        return output;
    }
}
