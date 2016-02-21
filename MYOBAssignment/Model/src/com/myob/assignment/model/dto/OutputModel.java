package com.myob.assignment.model.dto;

/**
 * Instances of this class represents the output by the application after calculating the taxes
 */
public class OutputModel
{
    /**
     * The full name of the employee the taxes were calculated for.
     */
    private String fullName;
    /**
     * The date range the taxes were calculated for, only copied from the input.
     */
    private String dateRange;
    /**
     * Gross income calculated as Math.round(Annual Salary / 12)
     */
    private int grossIncome;
    /**
     * Monthly income tax as calculated by the proper salary range instance.
     */
    private int incomeTax;
    /**
     * Net income (Gross income - Income tax)
     */
    private int netIncome;
    /**
     * Super value (Gross income * super rate)
     */
    private int superValue;
    
    public void setFullName(String fullName)
    {
        this.fullName = fullName;
    }

    public String getFullName()
    {
        return fullName;
    }

    public void setDateRange(String dateRange)
    {
        this.dateRange = dateRange;
    }

    public String getDateRange()
    {
        return dateRange;
    }

    public void setGrossIncome(int grossIncome)
    {
        this.grossIncome = grossIncome;
    }

    public int getGrossIncome()
    {
        return grossIncome;
    }

    public void setIncomeTax(int incomeTax)
    {
        this.incomeTax = incomeTax;
    }

    public int getIncomeTax()
    {
        return incomeTax;
    }

    public void setNetIncome(int netIncome)
    {
        this.netIncome = netIncome;
    }

    public int getNetIncome()
    {
        return netIncome;
    }

    public void setSuperValue(int superValue)
    {
        this.superValue = superValue;
    }

    public int getSuperValue()
    {
        return superValue;
    }

}
