package com.myob.assignment.model.console;

import com.myob.assignment.model.engine.Engine;
import com.myob.assignment.model.engine.TaxCalculator;

import java.util.Observable;
import java.util.Observer;

/**
 * This class is not part of the tax calculation process it only acts as an observer for the conversion
 * process, it is completely loosely coupled from the tax calculation engine, it monitors the progress
 * using the observer design pattern, exactly as the swing interface.
 * This class is only used when running the application using the main method in Runner.java not when 
 * using the swing interface.
 */
public class ConsoleObserver
    implements Observer
{
    private Engine engine;
    private String outputFilePath;
    public ConsoleObserver(String outputFilePath)
    {
        this.outputFilePath = outputFilePath;
    }

    /**
     * The observer update method, called by the system when the Observable calls the notifyObservers()
     * method, this method detects the progress of the calculation and notifies the user when it is done
     * by printing to the console
     * @param o The object being observed
     * @param arg An optional object to be passed by the object being observed 
     */
    @Override
    public void update(Observable o, Object arg)
    {
        TaxCalculator calculator = (TaxCalculator) o;
        /* If total number of entries in the TaxCalculator is -1, this means that the CSV file was not
         * correctly parsed 
         */
        if (calculator.getTotalEntries() == -1)
        {
            System.out.println("Error Parsing the CSV file");
            System.exit(1);
        }
        /* Case of successful parsing of the file */
        else
        {
            if (calculator.getCompletedEntries() == calculator.getTotalEntries())
            {
                if (engine.export(outputFilePath))
                    System.out.println("Export complete");
                else
                    System.out.println("Export failed");
                System.exit(0);
            }
        }
    }

    public void setEngine(Engine engine)
    {
        this.engine = engine;
    }

}
