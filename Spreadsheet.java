package spreadsheet;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Spreadsheet {
    
    //Declaring a hashmap to store values of cells
    private Map<String,Object> table;

    public Spreadsheet(){
        this.table = new HashMap<>();
    }

    //setting the values in hashmap
    public void setCellValue(String cellId, Object value){
        if(cellId.matches("^\\d.*")&&cellId.matches(".*[A-Za-z].*")){
            throw new IllegalArgumentException("Cell Ids doesnt start with digits");
        }
        table.put(cellId, value);
    }

    //retriving the values of cellIds from HashMap  
    public int getCellValue(String cellId){
        
        Object value  = table.getOrDefault(cellId,0);
        if(value instanceof Integer) return (int)value;
        else if(value instanceof String){
            String equation = (String) value;
            
            
            if(equation.startsWith("=")){
                // split all cellIds
                String[] cellsInEquation = equation.substring(1).split("[\\+\\-\\*\\/]");
                //split and retrieve arithmetic operators
                String[] operators  = extractOperators(equation);
                
                int output = 0 ;
                int operatorIndex = -1;
                //loop through all cellIds
                for(String cells:cellsInEquation){
                    if(cells.matches("[A-Z]+\\d+")){
                        int cellValue = getCellValue(cells);
                        //if its the first element then output is that value only
                        if(operatorIndex==-1) {
                            output = cellValue;
                            operatorIndex++;
                            continue;
                        }
                        if(operatorIndex<operators.length && !operators[operatorIndex].isEmpty()){
                            //getting the first operator and the character at the first index
                            char operator = operators[operatorIndex].charAt(0);

                            switch (operator) {
                                case '+':
                                    output += cellValue;
                                    break;
                                case '-':
                                    output -= cellValue;
                                    break;
                                case '*':
                                    output *= cellValue;
                                    break;
                                case '/':
                                    if(cellValue!=0)
                                        output /= cellValue;
                                    else
                                        throw new ArithmeticException("Divide by 0 is not possible");
                                    break;
                            
                                default:
                                    throw new IllegalArgumentException("Invalid operator: " + operator);
                            }
                            operatorIndex++;
                        }
                    }else if(cells.matches("^\\d.*")&&cells.matches(".*[A-Za-z].*")){
                        throw new IllegalArgumentException("Cell Ids doesnt start with digits");
                    }else{
                        output +=Integer.parseInt(cells);
                    }
                }
                return output;
            }else{
                throw new IllegalArgumentException("Invalid Equation Format");
            }
        }else{
            throw new IllegalArgumentException("Invalid cell Value");
        }
    }

    //the method to retrieve operators
    private String[] extractOperators(String equation) {
       String[] operators  = equation.substring(1).split("[A-Za-z0-9]+");
       List<String> operatorsList = Arrays.stream(operators).filter(e->!e.isEmpty()).collect(Collectors.toList());
       String[] operatorsArray = operatorsList.toArray(new String[0]);
       return operatorsArray;
    }

    public static void main(String[] args) {
        Spreadsheet spreadsheet = new Spreadsheet();
        spreadsheet.setCellValue("A1", 13);
        spreadsheet.setCellValue("A2", 14);

        System.out.println("A1 ->"+spreadsheet.getCellValue("A1"));
        spreadsheet.setCellValue("A3", "=A1+A2");
        System.out.println("A3 ->"+spreadsheet.getCellValue("A3"));

        spreadsheet.setCellValue("A4", "=A1+A2+A3");
        System.out.println("A4 ->"+spreadsheet.getCellValue("A4"));
    }



}
