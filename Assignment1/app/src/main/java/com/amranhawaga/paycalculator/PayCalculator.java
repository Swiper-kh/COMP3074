package com.amranhawaga.paycalculator;

/**
 * Holds the pay formulas from the assignment.
 * Kept separate from the screen code so the math is easy to read and test.
 */
public class PayCalculator {

    public static final double REGULAR_HOURS = 40;
    public static final double OVERTIME_MULTIPLIER = 1.5;

    public final double pay;
    public final double overtimePay;
    public final double totalPay;
    public final double tax;

    /**
     * @param hours      number of hours worked
     * @param hourlyRate pay per hour
     * @param taxRate    tax rate as a decimal (e.g. 0.13 for 13%)
     */
    public PayCalculator(double hours, double hourlyRate, double taxRate) {
        if (hours <= REGULAR_HOURS) {
            pay = hours * hourlyRate;
            overtimePay = 0;
        } else {
            pay = REGULAR_HOURS * hourlyRate;
            overtimePay = (hours - REGULAR_HOURS) * hourlyRate * OVERTIME_MULTIPLIER;
        }
        totalPay = pay + overtimePay;
        tax = pay * taxRate;
    }
}
