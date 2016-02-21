package com.myob.assignment.model.engine;

import com.myob.assignment.model.dto.InputModel;
import com.myob.assignment.model.dto.OutputModel;
import com.myob.assignment.model.dto.SalaryRange;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * The class responsible for calculating the taxes for a list of input models, it extends
 * java.util.Observable to provide a clean way to update the UI, the complete business of
 * the applicatio is encapsulated in this class thus it can be used alone to perform tax
 * calculations like how it is used in the JUnit test cases.
 */
public class TaxCalculator
    extends Observable
{
    /**
     * The total number of entries to be processed, needed in order to notify the observers
     */
    private int totalEntries;

    /**
     * The total number of entries processed so far, needed in order to notify the observers
     */
    private int completedEntries;

    /**
     * The list of input models to process
     */
    private List<InputModel> inputs;

    /**
     * The list of salary ranges supported by the application
     */
    private List<SalaryRange> salaryRanges;

    /**
     * The list of completed outputs
     */
    private List<OutputModel> outputs;
    
    public int getTotalEntries()
    {
        return totalEntries;
    }
    public int getCompletedEntries()
    {
        return completedEntries;
    }
    public void setSalaryRanges(List<SalaryRange> salaryRanges)
    {
        this.salaryRanges = salaryRanges;
    }
    public List<OutputModel> getOutputs()
    {
        return outputs;
    }

    public void setInputs(List<InputModel> inputs)
    {
        this.inputs = inputs;
        totalEntries = inputs == null? -1: inputs.size();
        /* Notifying the observers that the list of inputs has changed, to reset UI, set maximum 
         * progress bar value, .. etc.*/
        setChanged();
        notifyObservers();
    }

    /**
     * The method that performs the main functionality of the application which is calculating 
     * taxes for specific salaries
     */
    public void start()
    {
        if(inputs == null)
            return;
        outputs = new ArrayList<OutputModel>();
        for (InputModel input: inputs)
        {
            int annualSalary = input.getAnnualSalary();
            int grossIncome = Math.round(annualSalary / 12);
            /* 
             * Selecting the suitable salary range for the current salary
             */
            SalaryRange suitableRange = null;
            for(SalaryRange range: salaryRanges)
            {
                if(annualSalary > range.getRangeStart() && annualSalary <= range.getRangeEnd())
                {
                    suitableRange = range;
                    break;
                }
            }
            /* Using the retrieved salary range to calculate tax for the current salary */
            int incomeTax = suitableRange.calculateTax(annualSalary);
            /* netIncome = grossIncome - incomeTax */
            int netIncome = grossIncome - incomeTax;
            /* superValue = Math.round(grossIncome * input.getSuperRate() / 100)*/
            int superValue = Math.round(grossIncome * input.getSuperRate() / 100);
            /* Creating new OutputModel to fill the output data*/
            OutputModel output = new OutputModel();
            output.setDateRange(input.getDateRange());
            output.setFullName(new StringBuilder(input.getFirstName()).append(" ").append(input.getLastName()).toString());
            output.setGrossIncome(grossIncome);
            output.setIncomeTax(incomeTax);
            output.setNetIncome(netIncome);
            output.setSuperValue(superValue);
            /* Adding the output object to the list of outputs*/
            outputs.add(output);
            /* Notifying observers of the progress */
            completedEntries++;
            setChanged();
            notifyObservers();
        }
    }
}
