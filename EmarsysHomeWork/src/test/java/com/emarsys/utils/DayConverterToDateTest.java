package com.emarsys.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DayConverterToDateTest {
    
    private DayConverterToDate underTest;
    
    @BeforeEach
    void setUp() { underTest = new DayConverterToDate(); }
    
    @Test
    void shouldConvertDaysToYearsMonthsDays() {
        int[] expected1 = {2021, 3, 1}; //Not leapyear
        int[] expected2 = {2020, 2, 29}; //LeapYear 29th of Feb
        int[] expected3 = {2020, 12, 31};
        int[] expected4 = {2021, 12, 31};
        
        assertArrayEquals(underTest.dayConvertToDate(738230.25), expected1 );
        assertArrayEquals(underTest.dayConvertToDate(737865), expected2 );
        assertArrayEquals(underTest.dayConvertToDate(738170.25), expected3 );
        assertArrayEquals(underTest.dayConvertToDate(738535.5), expected4 );
    }
}