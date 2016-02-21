package com.myob.assignment.view.models;

import com.myob.assignment.model.dto.OutputModel;

import java.util.List;

import javax.swing.table.DefaultTableModel;

@SuppressWarnings("oracle.jdeveloper.java.serialversionuid-field-missing")
public class ResultsTableModel
    extends DefaultTableModel
{
    @SuppressWarnings("oracle.jdeveloper.java.field-not-serializable")
    private List<OutputModel> tableData;
    private String[] columnNames = new String[]
    {
        "Name", "Pay Period", "Gross Income", "Income Tax", "Net Income", "Super"
    };
    private Class[] types = new Class[]
    {
        java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class,
        java.lang.Integer.class, java.lang.Integer.class
    };
    boolean[] canEdit = new boolean[]
    {
        false, false, false, false, false, false
    };
    @Override
    public Class getColumnClass(int columnIndex)
    {
        return types[columnIndex];
    }
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex)
    {
        return canEdit[columnIndex];
    }
    @Override
    public int getColumnCount()
    {
        return columnNames.length;
    }
    @Override
    public int getRowCount()
    {
        return tableData == null ? 0: tableData.size();
    }
    
    @Override
    public String getColumnName(int index)
    {
        return columnNames[index];
    }
    public void setTableData(List<OutputModel> tableData)
    {
        this.tableData = tableData;
    }
    @Override
    public Object getValueAt(int row, int column)
    {
        OutputModel model = tableData.get(row);
        switch(column)
        {
            case 0:
                return model.getFullName();
            case 1:
                return model.getDateRange();
            case 2:
                return model.getGrossIncome();
            case 3:
                return model.getIncomeTax();
            case 4:
                return model.getNetIncome();
            case 5:
                return model.getSuperValue();
            default:
                return null;
        }
    }
}
