
public class Notation {

    /**
     * @author Yesho Vir
     * @param infix
     * @return
     * @throws InvalidNotationFormatException
     */

	public static String convertInfixToPostfix(String infix) throws InvalidNotationFormatException {
		char[] infixArray = infix.toCharArray();
		MyStack<Character> tempStack = new MyStack<>(infixArray.length);
		MyQueue<String> solutionQueue = new MyQueue<>(infixArray.length);
		
		for (char infixChar : infixArray) {
			if (infixChar != ' ') {
				if (isOperand(infixChar)) {
					try {
						solutionQueue.enqueue(Character.toString(infixChar));
					} catch (QueueOverflowException e) {
						throw new InvalidNotationFormatException();
					}
				} else if (infixChar == '(') {
					try {
						tempStack.push(infixChar);
					} catch (StackOverflowException e) {
						throw new InvalidNotationFormatException();
					}
				} else if (isOperator(infixChar)) {
					try {
						while (!tempStack.isEmpty() && isOperator(tempStack.top())) {
							if (higherOperator(tempStack.top(), infixChar) == tempStack.top())
								solutionQueue.enqueue(Character.toString(tempStack.pop()));
						}
						tempStack.push(infixChar);
					} catch (StackUnderflowException | StackOverflowException | QueueOverflowException e) {
						throw new InvalidNotationFormatException();
					}
				} else if (infixChar == ')') {
					try {
						while (tempStack.top() != '(') {
							solutionQueue.enqueue(Character.toString(tempStack.pop()));
						}
						tempStack.pop();
					} catch (StackUnderflowException | QueueOverflowException e) {
						throw new InvalidNotationFormatException();
					}
				}
			}
		}
		
		while (!tempStack.isEmpty()) {
			try {
				solutionQueue.enqueue(Character.toString(tempStack.pop()));
			} catch (QueueOverflowException | StackUnderflowException e) {
				throw new InvalidNotationFormatException();
			}
		}
		
		return solutionQueue.toString();
	}

    /**
     * 
     * @param postfix
     * @return
     * @throws InvalidNotationFormatException
     */

	public static String convertPostfixToInfix(String postfix) throws InvalidNotationFormatException {
		char[] postfixArray = postfix.toCharArray();
		MyStack<String> solutionStack = new MyStack<>(postfixArray.length);
		
		for (char postfixChar : postfixArray) {
			if (postfixChar != ' ') {
				if (isOperand(postfixChar)) {
					try {
						solutionStack.push(Character.toString(postfixChar));
					} catch (StackOverflowException e) {
						throw new InvalidNotationFormatException();
					}
				} else if (isOperator(postfixChar)) {
					if (solutionStack.size() < 2)
						throw new InvalidNotationFormatException();
					try {
						String secondValue = solutionStack.pop();
						String firstValue = solutionStack.pop();
						String together = firstValue + Character.toString(postfixChar) + secondValue;
						String togetherWithParenthesis = "(" + together + ")";
						solutionStack.push(togetherWithParenthesis);
					} catch (StackUnderflowException | StackOverflowException e) {
						throw new InvalidNotationFormatException();
					}
				} else {
					try {
						throw new InvalidNotationFormatException();
					} catch (InvalidNotationFormatException e) {
						throw new InvalidNotationFormatException();
					}
				}
			}
		}
		
		if (solutionStack.size() > 1)
			throw new InvalidNotationFormatException();
		
		return solutionStack.toString();
	}

    /**
     * 
     * @param postdixExpr
     * @return
     * @throws InvalidNotationFormatException
     */

	public static double evaluatePostfixExpression(String postdixExpr) throws InvalidNotationFormatException {
		char[] postfixArray = postdixExpr.toCharArray();
		MyStack<String> stack = new MyStack<>(postfixArray.length);

		for (char postfixChar : postfixArray) {
			if (postfixChar != ' ') {
				if (isOperand(postfixChar)) {
					try {
						stack.push(Character.toString(postfixChar));
					} catch (StackOverflowException e) {
						throw new InvalidNotationFormatException();
					}
				} else if (isOperator(postfixChar)) {
					if (stack.size() < 2)
						throw new InvalidNotationFormatException();
					try {
						String secondValue = stack.pop();
						String firstValue = stack.pop();
						double result = doCalculation(postfixChar, firstValue, secondValue);
						stack.push(String.valueOf(result));
					} catch (StackUnderflowException | StackOverflowException e) {
						throw new InvalidNotationFormatException();
					}
				}
			}
		}
		
		if (stack.size() > 1)
			throw new InvalidNotationFormatException();
		
		return Double.parseDouble(stack.toString());
	}

    /**
     * 
     * @param item
     * @return
     */

	private static boolean isOperator(char item) {
		char[] operators = "+-*/".toCharArray();
		for (char operator : operators)
			if (item == operator)
				return true;
		return false;
	}

    /**
     * 
     * @param item
     * @return
     */

	private static boolean isOperand(char item) {
		char[] numbers = "0123456789".toCharArray();
		for (char number : numbers)
			if (item == number)
				return true;
		return false;
	}

    /**
     * 
     * @param operator1
     * @param operator2
     * @return
     */

	private static char higherOperator(char operator1, char operator2) {
		char[] higherOperators = "*/".toCharArray();
		char[] middleOperators = "+-".toCharArray();
		char[] lowestOperators = "()".toCharArray();
		
		if (operator1 == operator2)
			return operator1;
		
		for (char operator : higherOperators)
			if (operator1 == operator)
				return operator1;
			else if (operator2 == operator)
				return operator2;
		
		for (char operator : middleOperators)
			if (operator1 == operator)
				return operator1;
			else if (operator2 == operator)
				return operator2;
		
		for (char operator : lowestOperators)
			if (operator1 == operator)
				return operator1;
			else if (operator2 == operator)
				return operator2;
		
		return operator1;
	}

    /**
     * 
     * @param operator
     * @param num1
     * @param num2
     * @return
     */

	private static double doCalculation(char operator, String num1, String num2) {
		double result = 0.0;
		
		switch (operator) {
			case '+':
				result = Double.parseDouble(num1) + Double.parseDouble(num2);
				break;
			case '-':
				result = Double.parseDouble(num1) - Double.parseDouble(num2);
				break;
			case '*':
				result = Double.parseDouble(num1) * Double.parseDouble(num2);
				break;
			case '/':
				result = Double.parseDouble(num1) / Double.parseDouble(num2);
				break;
		}
		
		return result;
	}

}
