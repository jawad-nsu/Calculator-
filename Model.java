package application;

public class Model {
	public float calculate(float number1, float number2, String operator) throws InvalidDivisorException {
		switch (operator) {
		case "+":
			return number1 + number2;
		case "-":
			return number1 - number2;
		case "%":
			return number1 % number2;
		case "*":
			return number1 * number2;
		
		case "/":
			if(number2 == 0)
				throw new InvalidDivisorException("Cannot Divide by Zero");
			
			else
			    return number1 / number2;
			
		default:
			return 0;
		}
		
	}

}
