package com.myob.assignment.model.engine;

import com.myob.assignment.model.dto.InputModel;
import com.myob.assignment.model.dto.OutputModel;
import com.myob.assignment.model.dto.SalaryRange;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

/** 
 * The main operation processor, implements Runnable to run in a separate thread in order not to
 * freeze the UI while processing large number of inputs
 */
public class Engine implements Runnable
{
    /**
     * The path of the CSV file to be used as input
     */
    private String csvFilePath;
    /**
     * The list of observers to be passed to the TaxCalculator object to be notified of the progress
     * of the tax conversion operation
     */
    private List<Observer> observers;
    /**
     * The TaxCalculator object the performs the calculation
     */
    private TaxCalculator calculator;
    
    public Engine(String csvFilePath, List<Observer> observers)
    {
       this.csvFilePath = csvFilePath;
       this.observers = observers;
       calculator = new TaxCalculator();
    }
    
    @Override
    public void run()
    {
        /* 
         * Initializing the list of supported salary ranges, this could be retrieved from an online API
         * or from a database to support a variable list of salary ranges with no code modifications.
         */
        ArrayList<SalaryRange> salaryRanges = new ArrayList<SalaryRange>();
        salaryRanges.add(new SalaryRange(0, 18200, 0, 0));
        salaryRanges.add(new SalaryRange(18200, 37000, 0, 0.19f));
        salaryRanges.add(new SalaryRange(37000, 80000, 3572, 0.325f));
        salaryRanges.add(new SalaryRange(80000, 180000, 17547, 0.37f));
        salaryRanges.add(new SalaryRange(180000, Integer.MAX_VALUE, 54547, 0.45f));
        /* Loading the list of inputs from the csv file*/
        ArrayList<InputModel> inputs = parseCSV(csvFilePath);
        /* Initializing the tax calculator */
        calculator.setSalaryRanges(salaryRanges);
        /* Adding any available observers */
        if(observers != null)
        {
            for(Observer observer: observers)
                calculator.addObserver(observer);
        }
        calculator.setInputs(inputs);
        /* Starting calculations */
        calculator.start();
    }
    /**
     * This method parses the input CSV file into a list of InputModel, i could have used a third party API but 
     * i didn't find that it would save any effort for such a short method
     * @param filePath The path to the input file
     * @return List of InputModel
     */
    @SuppressWarnings(
        {
            "oracle.jdeveloper.java.nested-assignment", "oracle.jdeveloper.java.insufficient-catch-block",
            "oracle.jdeveloper.java.null-collection-return"
        })
    private ArrayList<InputModel> parseCSV(String filePath)
    {
        ArrayList<InputModel> inputs = new ArrayList<InputModel>();
        FileReader fr = null;
        String line = "";
        try
        {
            fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);
            /* Skipping the headers line*/
            line = br.readLine();
            while ((line = br.readLine()) != null)
            {
                String[] columns = line.split(",");
                String firstName = columns[0];
                String lastName = columns[1];
                int annulSalary = Integer.parseInt(columns[2]);
                float superRate = Float.parseFloat(columns[3]);
                String dateRange = columns[4];
                inputs.add(new InputModel(firstName, lastName, annulSalary, superRate, dateRange));
            }
            br.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            /* 
             * Returning null to indicate to the TaxCalculator and its observes that the list of inputs
             * is invalid, in a real application we should log file reading errors here.
             */
            return null;
        }
        finally
        {
            /* 
             * Closing file reader in a finally block to mitigate against up normal termination of 
             * the catch block, any errors that might occur while closing the file reader are of
             * no interest so it is just surrounded with an empty catch block.
             */
            try{fr.close();}catch(Exception ex){}
        }
        return inputs;
    }
    /**
     * This method exports the calculated results to a CSV at the given path
     * @param outputFilePath The path of the CSV file to write
     * @return a boolean to indicate the success of the export operation, as an indicator to the method
     * caller.
     */
    @SuppressWarnings("oracle.jdeveloper.java.insufficient-catch-block")
    public boolean export(String outputFilePath)
    {
        FileWriter fw = null;
        try
        {
            fw = new FileWriter(outputFilePath);
            BufferedWriter bw = new BufferedWriter(fw);
            /* Writing the header row to the file */
            bw.write("Name, Pay Period, Gross Income, Income Tax, Net Income, Super");
            bw.write(System.lineSeparator());
            if(calculator.getOutputs() != null)
            {
                /* Writing the result records */
                for(OutputModel outout: calculator.getOutputs())
                {
                    bw.write(new StringBuilder(outout.getFullName()).append(",")
                            .append(outout.getDateRange()).append(",")
                            .append(outout.getGrossIncome()).append(",")
                            .append(outout.getIncomeTax()).append(",")
                            .append(outout.getNetIncome()).append(",")
                            .append(outout.getSuperValue()).append(",").toString());
                    bw.write(System.lineSeparator());
                }
            }
            bw.flush();
            bw.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
        finally
        {
            /* 
             * Closing file writer in a finally block to mitigate against up normal termination of 
             * the catch block, any errors that might occur while closing the file writer are of
             * no interest so it is just surrounded with an empty catch block.
             */
            try{fw.close();}catch(Exception ex){}
        }
        return true;
    }
}
