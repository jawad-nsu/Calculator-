package application;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MainController {
	
	@FXML
	private Label result; //the screen we see, you can see our input and outputs here
	private float number1 = 0; // initialize the operand to 0
	private String operator = ""; //initialize the operator as none
	private boolean start = true; 
	private Model model = new Model(); // create a model object to later access it's calculate method 
	@FXML
	public void processNumbers(ActionEvent event) { //  The ActionEvent is generated when a number button is clicked i.e 1, 2, 3
		if(start == true) { 
			result.setText(""); // incase of new calculation, remove the previous result and start new
			start = false;
		}
		String value = ((Button)event.getSource()).getText(); // get the first operand / number
		result.setText(result.getText()+ value); //show the inputed value to the label
	}
	
	@FXML
	public void processOperators(ActionEvent event) { //  The ActionEvent is generated when a operator button is clicked i.e plus, minus, multiply etc
		//fetch the inputed user operator
		String value = ((Button)event.getSource()).getText(); 
		if(value.equals("C")){ 
			result.setText(""); // resets the label into empty and clears previous data
			start = true;
			return;
		}
		 
		if(value.equals("=")) {
			if(operator.isEmpty()) // if the user presses the equal button without clicking any operator, then have to return 
				return;
			
			float number2 = Float.parseFloat(result.getText()); //if the user has inserted an operator then we have to fetch the 2nd operand / number
			float output = 0;
			try {
				output = model.calculate(number1, number2, operator);//At this point, we have 2 value and a operator, so we call the calculate method
			} catch (InvalidDivisorException e1) {
				result.setText("");
				System.out.println(e1);
			} 
			result.setText(String.valueOf(output)); //output the result into our label
			
			//Setting up the time class 
			LocalDateTime now = LocalDateTime.now();  
			// formatting the pattern of Date and time
			DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");  
			String formatDateTime = now.format(format);  
	        
	        //
	        try{    
	        	//opening a file to write
	        	BufferedWriter fw = new BufferedWriter(new FileWriter("testout.txt", true));  
	            //write into file
	            fw.write("Date and time: " + formatDateTime + " result "+ output + "\n"); 
	            fw.close();    
	           }catch(Exception e){System.out.println(e);}    
	           
			operator = "";
			start = true;
			
		}else {
			
			if(!operator.isEmpty())
				return;
			
			operator = value;
			number1 = Float.parseFloat(result.getText());
			result.setText("");
			
		}
	}

}
