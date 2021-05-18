package com.emarsys.services;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        DueDateCalculatorService dueDateCalculatorService = new DueDateCalculatorService();
        int[] input1 = {2021, 5, 21, 16, 10};
        System.out.println(Arrays.toString(dueDateCalculatorService.calculateDueDate(input1, 40)));

    }

}
