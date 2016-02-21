package com.myob.assignment.model.dto;

/**
 * Instances of this class represents the inputs to the application to calculate the tax for, after 
 * creation input models need not be changed, so they are immutable;
 */
public class InputModel
{
    /**
     * The first name of the employee to calculate the tax for.
     */
    private String firstName;
    /**
     * The last name of the employee to calculate the tax for.
     */
    private String lastName;
    /**
     * The annual salary to calculate the tax for.
     */
    private int annualSalary;
    /**
     * The super rate to calculate the super for the employee salary.
     */
    private float superRate;
    /**
     * A string representing the date range to calculate the tax for, as per the problem statement,  
     * the tax ranges table, and the assumptions listed in the reply email, the date specified does not
     * participate in the tax calculation, so it would be just copied from the input to the output
     */
    private String dateRange;
    
    public InputModel(String firstName, String lastName, int annualSalary, float superRate, String dateRange)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.annualSalary = annualSalary;
        this.superRate = superRate;
        this.dateRange = dateRange;
    }
    
    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public int getAnnualSalary()
    {
        return annualSalary;
    }

    public float getSuperRate()
    {
        return superRate;
    }

    public String getDateRange()
    {
        return dateRange;
    }
}
