package com.emarsys.services;

import com.emarsys.utils.DateConverterToDays;
import com.emarsys.utils.DateTimeValidator;
import com.emarsys.utils.DayConverterToDate;


public class DueDateCalculatorService {
    
    private final DateTimeValidator dateTimeValidator = new DateTimeValidator();
    private final DateConverterToDays dateConverterToDays = new DateConverterToDays();
    private final DayConverterToDate dayConverterToDate = new DayConverterToDate();
    
    public int[] calculateDueDate(int[] yearMonthDayHourMin, int turnaroundTime) throws IllegalStateException {
        
        int[] output = new int[5];
        
        if (!dateTimeValidator.dateTimeIsValid(yearMonthDayHourMin)) {
            throw new IllegalStateException("The date is invalid or not submitted during working hours");
        }
        
        if (turnaroundTime <= 0) {
            throw new IllegalArgumentException("The turnaround time must be positive and not null");
        }
    
        int submittedYear = yearMonthDayHourMin[0];
        int submittedMonth = yearMonthDayHourMin[1];
        int submittedDay = yearMonthDayHourMin[2];
        int submittedHour = yearMonthDayHourMin[3];
        int submittedMinute = yearMonthDayHourMin[4];
        
        
        final int[] standard = {2021, 1, 1}; //1st of January in 2021 was a Friday
        
        int workingDaysItTakes = turnaroundTime / 8;
        int remainderOfWorkingHours = turnaroundTime % 8;
        int alreadyPassedUsefulHoursOnTheSubmittedDay = submittedHour - 9;
        if (submittedMinute !=0 ) {
            alreadyPassedUsefulHoursOnTheSubmittedDay++;
        }
        output[4] = 0; //The company invoices only full hours so no need to bother with minutes
        if (alreadyPassedUsefulHoursOnTheSubmittedDay + remainderOfWorkingHours > 8) {
            workingDaysItTakes++;
            output[3] = 9 + alreadyPassedUsefulHoursOnTheSubmittedDay + remainderOfWorkingHours - 8;
        } else {
            output[3] = 9 + alreadyPassedUsefulHoursOnTheSubmittedDay + remainderOfWorkingHours;
        }
        
        int onTheDaySubmittedIndex = 0;
        //convert To
        final double standardMonday = dateConverterToDays.dayfinder(2021,5,17);
        double submittedDateConvToDays = dateConverterToDays.dayfinder(submittedYear, submittedMonth, submittedDay);
    
        //The following if statement used to use the daysOfTheWeek string array
        //final String[] daysOfTheWeek = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        //I did not delete it because helps understand it better so I commented
        if (standardMonday >= submittedDateConvToDays){
            onTheDaySubmittedIndex = (int) (6 - ((standardMonday - submittedDateConvToDays) % 7));
            
        } else {
            onTheDaySubmittedIndex = (int) ((submittedDateConvToDays - standardMonday) % 7);
            
        }
        
        if (onTheDaySubmittedIndex == 5 || onTheDaySubmittedIndex == 6){
            throw new IllegalStateException("Issue can only be submitted on working days and in working hours");
        }
        

        int dayCounter = 1;
        for (int i = 0; i < workingDaysItTakes;) {
            if ((onTheDaySubmittedIndex + dayCounter) % 7 == 5 || (onTheDaySubmittedIndex + dayCounter) % 7 == 6 ){
                dayCounter++;
            } else {
                dayCounter++;
                i++;
            }
        }
        
        int[] outputYearMonthDay = dayConverterToDate.dayConvertToDate(submittedDateConvToDays + dayCounter - 1);
        output[0] = outputYearMonthDay[0];
        output[1] = outputYearMonthDay[1];
        output[2] = outputYearMonthDay[2];
        
        return output;
    }
}
