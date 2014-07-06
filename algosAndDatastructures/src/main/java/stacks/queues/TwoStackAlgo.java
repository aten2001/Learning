package stacks.queues;

import java.util.Scanner;


public class TwoStackAlgo {

	public static void main(String... args){
		Scanner sc = new Scanner(System.in);
		LinkedStack<Character> operator = new LinkedStack<>();
		LinkedStack<Integer> operands = new LinkedStack<>();
		while(sc.hasNext()){ // assuming ASCII inputs
			char c = (char) sc.nextByte();
			if(Character.isWhitespace(c)) continue;
			if(c == '(') continue;
			else if(Character.isDigit(c)) operands.push(Character.digit(c, 10));
			else if (c == '+' || c == '-' || c == '*' || c == '/') operator.push(c);
			else if(c == ')'){
				int b = operands.pop(); int a = operands.pop();
				char opr = operator.pop();
				if(opr == '+') operands.push(a + b);
				else if (opr == '-') operands.push(a - b);
				else if (opr == '*') operands.push(a * b);
				else  operands.push(a / b); // opr == '/'
			}
		}
		
		System.out.println(operands.pop());
	}
}
