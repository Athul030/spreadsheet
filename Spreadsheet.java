package mainNew;

import java.util.HashMap;
import java.util.Map;

public class Spreadsheet {
    
    //Declaring a hashmap to store values of cells
    private Map<String,Object> table;

    public Spreadsheet(){
        this.table = new HashMap<>();
    }

    public void setCellValue(String cellId, Object value){
        if(cellId.matches("^\\d.*")&&cellId.matches(".*[A-Za-z].*")){
            throw new IllegalArgumentException("Cell Ids doesnt start with digits");
        }
        table.put(cellId, value);
    }

    public int getCellValue(String cellId){
        
        Object value  = table.getOrDefault(cellId,0);
        if(value instanceof Integer) return (int)value;
        else if(value instanceof String){
            String equation = (String) value;
            if(equation.startsWith("=")){
                String[] cellsInEquation = equation.substring(1).split("\\+");
                int sum=0;
                for(String cells:cellsInEquation){
                    if(cells.matches("[A-Z]+\\d+")){
                        sum +=getCellValue(cells);
                    }else if(cells.matches("^\\d.*")&&cells.matches(".*[A-Za-z].*")){
                        throw new IllegalArgumentException("Cell Ids doesnt start with digits");
                    }else{
                        sum +=Integer.parseInt(cells);
                    }
                }
                return sum;
            }else{
                throw new IllegalArgumentException("Invalid Equation Format");
            }
        }else{
            throw new IllegalArgumentException("Invalid cell Value");
        }
    }
    public static void main(String[] args) {
        Spreadsheet spreadsheet = new Spreadsheet();
        spreadsheet.setCellValue("A1", 13);
        spreadsheet.setCellValue("A2", 14);

        System.out.println("Celll value of A1 ->"+spreadsheet.getCellValue("A1"));
        spreadsheet.setCellValue("A3", "=A1+A2");
        System.out.println("Cell value of A3 ->"+spreadsheet.getCellValue("A3"));

        spreadsheet.setCellValue("A4", "=A1+A2+A3");
        System.out.println("Cell value of A4 ->"+spreadsheet.getCellValue("A4"));

        spreadsheet.setCellValue("XFD10", 6997);
        spreadsheet.setCellValue("XFD11", "=XFD10+A1");
        System.out.println("Celll value of XFD11 ->"+spreadsheet.getCellValue("XFD11"));





    }



}
