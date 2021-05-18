package com.emarsys.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateTimeValidatorTest {
    
    private DateTimeValidator underTest;
    
    @BeforeEach
    void setUp() { underTest = new DateTimeValidator(); }
    
    @Test
    void ValidateWithYears() {
        int[] input1 = {-2021, 2, 28, 16, 59}; //Only positive number can be given as year
        int[] input2 = {2021, 2, 28, 16, 59};
        
        assertFalse(underTest.dateTimeIsValid(input1));
        assertTrue(underTest.dateTimeIsValid(input2));
    }
    
    @Test
    void ValidateWithMonths() {
        int[] input1 = {2021, 2, 29, 16, 59}; //Not a leap year so should fail
        int[] input2 = {2020, 2, 29, 16, 59}; //Leap year 29th of feb
        int[] input3 = {2021, 11, 31, 16, 59}; //November 31th does not exist
        int[] input4 = {2021, -12, 31, 16, 59};
        
        assertFalse(underTest.dateTimeIsValid(input1));
        assertTrue(underTest.dateTimeIsValid(input2));
        assertFalse(underTest.dateTimeIsValid(input3));
        assertFalse(underTest.dateTimeIsValid(input4));
    }
    
    @Test
    void ValidateWithWorkingHours() {
        int[] input1 = {2021, 12, 31, 17, 0};
        int[] input2 = {2021, 12, 31, 9, 0};
        int[] input3 = {2021, 12, 31, 16, 59};
        int[] input4 = {2021, 12, 31, 8, 59};
        
        assertFalse(underTest.dateTimeIsValid(input1));
        assertTrue(underTest.dateTimeIsValid(input2));
        assertTrue(underTest.dateTimeIsValid(input3));
        assertFalse(underTest.dateTimeIsValid(input4));
    }
    
    @Test
    void ValidateWithMinutes() {
        int[] input1 = {2021, 12, 31, 16, 60};
        int[] input2 = {2021, 12, 31, 16, -1};
        int[] input3 = {2021, 12, 31, 16, 0};
        
        assertFalse(underTest.dateTimeIsValid(input1));
        assertFalse(underTest.dateTimeIsValid(input2));
        assertTrue(underTest.dateTimeIsValid(input3));
    }
    
}