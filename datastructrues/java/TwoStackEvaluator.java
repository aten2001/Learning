

public class TwoStackEvaluator {

  public static void main(String[] args){
    String input = " ( 3 + 4 ( 5 * 3 ) ) ";
    Stack<String> operators = new LinkedStack<String>();
    Stack<Integer> operands = new LinkedStack<Integer>();
    
    for(int i=0; i<input.length(); i++){
      char c = input.charAt(i);
      if(c == ' ') continue;
      if(c == '(') continue;
      if(c == ')') {
        int a = operand.pop(), b = operands.pop();
        char opr = opeartors.pop();
        if(opr == '+') opearnds.push(a+b);
        else if(opr == '-') operands.push(a-b);
        else if (opr == '*') opearnds.push(a*b);
        else opearands.push(a/b);
      }
      else operands.push((int) c);
    }
  }

}


