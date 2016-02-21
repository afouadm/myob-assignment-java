package test.com.myob.assignment.model.engine;

import com.myob.assignment.model.dto.InputModel;
import com.myob.assignment.model.dto.OutputModel;
import com.myob.assignment.model.dto.SalaryRange;
import com.myob.assignment.model.engine.TaxCalculator;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TaxCalculatorTest
{
    private ArrayList<SalaryRange> salaryRanges;
    private TaxCalculator c;
    ArrayList<InputModel> inputs;
    
    @Before
    public void init()
    {
        salaryRanges = new ArrayList<SalaryRange>();
        salaryRanges.add(new SalaryRange(0, 18200, 0, 0));
        salaryRanges.add(new SalaryRange(18200, 37000, 0, 0.19f));
        salaryRanges.add(new SalaryRange(37000, 80000, 3572, 0.325f));
        salaryRanges.add(new SalaryRange(80000, 180000, 17547, 0.37f));
        salaryRanges.add(new SalaryRange(180000, Integer.MAX_VALUE, 54547, 0.45f));
        c = new TaxCalculator();
        c.setSalaryRanges(salaryRanges);
        inputs = new ArrayList<InputModel>();
    }
    
    @Test
    public void testCase1()
    {
        InputModel model = new InputModel("David", "Rudd", 60050, 9, "01 March - 31 March");
        inputs.add(model);
        c.setInputs(inputs);
        c.start();
        OutputModel output = c.getOutputs().get(0);
        Assert.assertEquals(output.getNetIncome(), 4082);
    }
    
    @Test
    public void testCase2()
    {
        InputModel model = new InputModel("Ryan", "Chen", 120000, 10, "01 March - 31 March");
        inputs.add(model);
        c.setInputs(inputs);
        c.start();
        OutputModel output = c.getOutputs().get(0);
        Assert.assertEquals(output.getNetIncome(), 7304);
    }
    
    @Test
    public void testCase3()
    {
        InputModel model = new InputModel("Emery", "Coby", 43000, 5, "01 March - 31 March");
        inputs.add(model);
        c.setInputs(inputs);
        c.start();
        OutputModel output = c.getOutputs().get(0);
        Assert.assertEquals(output.getNetIncome(), 3123);
    }
    
    @Test
    public void testCase4()
    {
        InputModel model = new InputModel("Tiger", "Kermit", 22000, 4, "01 March - 31 March");
        inputs.add(model);
        c.setInputs(inputs);
        c.start();
        OutputModel output = c.getOutputs().get(0);
        Assert.assertEquals(output.getNetIncome(), 1773);
    }
    
    @Test
    public void testCase5()
    {
        InputModel model = new InputModel("Uriah", "Tyrone", 51000, 8, "01 March - 31 March");
        inputs.add(model);
        c.setInputs(inputs);
        c.start();
        OutputModel output = c.getOutputs().get(0);
        Assert.assertEquals(output.getNetIncome(), 3573);
    }
    
    @Test
    public void testCase6()
    {
        InputModel model = new InputModel("Macaulay", "Zeph", 64000, 11, "01 March - 31 March");
        inputs.add(model);
        c.setInputs(inputs);
        c.start();
        OutputModel output = c.getOutputs().get(0);
        Assert.assertEquals(output.getNetIncome(), 4304);
    }
}
