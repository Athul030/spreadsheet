*Spreadsheet Application*

*Description:*
This Java application simulates a simple spreadsheet backend that supports setting cell values and retrieving computed cell values based on equations provided.

*Features:*
Set cell values: The setCellValue method allows users to set values for specific cells.
Get computed cell values: The getCellValue method retrieves the computed value of a cell based on the equation provided.

*How to Use:*
Instantiate the Spreadsheet class.
Use the setCellValue method to set values for individual cells.
Use the getCellValue method to retrieve computed values of cells with equations.

*Methods:*
setCellValue(String cellId, Object value): Sets the value for the specified cell.
getCellValue(String cellId): Retrieves the computed value of the specified cell.

*Error Handling:*
If a cell ID provided starts with a digit, an IllegalArgumentException is thrown.
If the equation format is invalid, an IllegalArgumentException is thrown.
If there's an attempt to divide by zero, an ArithmeticException is thrown.
If an invalid operator is encountered (other than +,-,*,/) an IllegalArgumentException is thrown.

*Main Method:*
The main method demonstrates the usage of the Spreadsheet class by setting values for cells and retrieving computed values based on equations.

*Technologies Used:*
Java
HashMap

*How to Run:*
Clone the repository to your local machine.
Open the project in your preferred Java IDE.
Run the main method in the Spreadsheet class.

*Contributor:*
Athul Raj
athul.raj100@gmail.com
