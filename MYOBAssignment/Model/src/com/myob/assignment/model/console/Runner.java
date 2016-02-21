package com.myob.assignment.model.console;

import com.myob.assignment.model.engine.Engine;

import java.util.ArrayList;
import java.util.Observer;

/**
 * The class used to run the tax calculation process through the system console, will not be used if the
 * swing interface is used.
 */
public class Runner
{
    public static void main(String[] args)
    {
        /* Validating correct program arguments, the first command line argument should be the 
         * input CSV file, the second argument is the output CSV file
         */
        if(args.length < 2)
        {
            System.out.println("Usage: Runner <input_file_path> <output_file_path>");
            System.exit(0);
        }
        /* Creating the observer object to monitor the tax calculation progress */
        ConsoleObserver co = new ConsoleObserver(args[1]);
        ArrayList<Observer> observers = new ArrayList<Observer>();
        observers.add(co);
        /* Initializing the engine */
        Engine e = new Engine(args[0], observers);
        /* Passing the engine to the observer to execute the export when the tax calculation is completed */
        co.setEngine(e);
        /* Starting the calculation process */
        new Thread(e).start();
    }

}
