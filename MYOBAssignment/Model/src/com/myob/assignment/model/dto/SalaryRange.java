package com.myob.assignment.model.dto;

/**
 * Instances of this class represents the possible tax salary used to determine employee's taxes, at 
 * runtime, the salary is compared to instances of this class to select the correct salary range to be 
 * used to apply taxes on the provided salary.
 * Also, this class is responsible for calculating the tax for the given salary after it has been 
 * determined that a given instance is suitable for calculating the tax for this salary.
 */
/*
 * Supressing warnings for the absence of the hashCode implementation, which i didn't need to add.
 */
@SuppressWarnings("oracle.jdeveloper.java.semantic-warning")
public class SalaryRange
{
    /**
     * An integer value representing the exclusive start of the salary range represented by this instance, since all calculations are 
     * rounded to the nearest integer, I assumed that tax range bounds are always integers.
     */
    private int rangeStart;
    /**
     * An integer value representing the inclusive end of the salary range represented by this instance, since all calculations are 
     * rounded to the nearest integer, I assumed that tax range bounds are always integers.
     */
    private int rangeEnd;
    /**
     * An integer value representing the constant tax for this salary range.
     */
    private int taxBase;
    /**
     * A float value representing the tax per dollar used in calculating the income tax for this 
     * salary range.
     */
    private float taxPerDollar;
    
    /**
     * A constructor used to create the possible tax range instances when initializing the application.
     * @param rangeStart The exclusive start of the salary range.
     * @param rangeEnd The inclusive end of the salary range.
     * @param taxBase The constant tax for this salary range.
     * @param taxPerDollar The tax per dollar used in calculating the income tax for this salary range.
     */
    public SalaryRange(int rangeStart, int rangeEnd, int taxBase, float taxPerDollar)
    {
        this.rangeStart = rangeStart;
        this.rangeEnd = rangeEnd;
        this.taxBase = taxBase;
        this.taxPerDollar = taxPerDollar;
    }
    
    /**
     * The method responsible for calculating the tax on salary when the salary falls in the bounds 
     * of this salary range.
     * @param salary The annual salary to create the tax for.
     * @return Monthly income tax for the provided salary
     */
    public final int calculateTax(int salary)
    {
        return Math.round((taxBase + (salary - rangeStart) * taxPerDollar) / 12);
    }

    /**
     * Getter for the salary range start field, this is accessed while determining the suitable 
     * salary range to calculate the tax for a given salary
     */
    public int getRangeStart()
    {
        return rangeStart;
    }

    /**
     * Getter for the salary range end field, this is accessed while determining the suitable 
     * salary range to calculate the tax for a given salary
     */
    public int getRangeEnd()
    {
        return rangeEnd;
    }

}
