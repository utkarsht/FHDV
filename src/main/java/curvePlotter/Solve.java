package curvePlotter;

import java.util.ArrayList;
import java.util.Stack;

public class Solve 
{
    private static final int deltaH = 30;    											 //	deltaH for Integration and differentiation Limits
    private static final double EPS = 0.15;												 //	Minimum off in case of = relation
    
    private int getPriority(char symbol)
    {
        if(symbol == 'x' || symbol == 'y')  return 12;
        switch(symbol)
        {
            case '+':   return 1;
            case '-':   return 2;
            case '*':   return 3;
            case '%':   return 4;
            case '/':   return 5;
            case '^':   return 6;
            case 'S':   return 7;
            case 'C':   return 8;
            case 'T':   return 9;
            case 'L':   return 10;
            case '(':   return 0;
            case ')':   return 11;
        }
        return -1;
    }
    
    private ArrayList<String> parseInput(String str)               						 //	remove spaces and create tokens
    {
        String tmp = "";
        /*
          If an operator is the first thing in your expression, or comes after another operator,
          or comes after a left parenthesis, then it's an unary operator.
        */
        
        for (int i = 0; i < str.length(); i++)											 //	handling unary minus here
        {
        	if(i == 0 && str.charAt(0) == '-')		tmp += "0-";
        	else if(str.charAt(i) == '-')
        	{
        		int p = getPriority(str.charAt(i-1));
        		if(p != -1 && p != 12)
        			tmp += "0-";
        		else
        			tmp += "-";
        	}
        	else
        		tmp += str.charAt(i);
        }
        str = tmp;
        tmp = "";
        for (int i = 0; i < str.length(); i++)
        {
        	if(str.charAt(i) >= 'A' && str.charAt(i) <= 'Z')
        		tmp += (char)(str.charAt(i) + ' ');
        	else if(str.charAt(i) != ' ')
                tmp += str.charAt(i);
        }
        str = tmp;
        
        ArrayList<String> parsed = new ArrayList<String>();
        parsed.add("(");
        char ch;
        int i = 0;
        while(i < str.length())															 //	creating tokens
        {
            ch = str.charAt(i);
            if(ch == 'x' || getPriority(ch) != -1)
            {
                parsed.add(ch + "");
                i++;
            }
            else if(ch == 's' || ch == 'c' || ch == 't' || ch == 'l')          			 //  extra checking needed 
            {
                i += 3;
                parsed.add((char)(ch - ' ') + "");                         				 //  toUpperCase
            }
            else
            {
                tmp = "";
                while(i < str.length() && ((str.charAt(i) >= '0' && str.charAt(i) <= '9') || (str.charAt(i) == '.')) )
                {
                    tmp += str.charAt(i) + "";
                    i++;
                }
                parsed.add(tmp);
            }
        }
        parsed.add(")");
        return parsed;
    }
    
    public ArrayList<String> infixToPostfix(String infix)             					 //  not handles erroneous input
    {
        ArrayList<String> parsed = parseInput(infix);
        Stack<Character> s = new Stack<Character>(); 
        ArrayList<String> postfix = new ArrayList<String>();

        for(String tmp : parsed)
        {
            char c = tmp.charAt(0);
            int P  = getPriority(c);
            
            if(P == -1)
                postfix.add(tmp);
            else
            {
                if(c == ')')
                {
                    while(s.peek() != '(')
                    {
                        postfix.add(s.peek() + "");
                        s.pop();
                    }
                    s.pop();
                }
                else if(c == '(' || P > getPriority(s.peek()))
                    s.push(c);
                else
                {
                    while(P <= getPriority(s.peek()))
                    {
                        postfix.add(s.peek() + "");
                        s.pop();
                    }
                    s.push(c);
                }
            }
        }
        return postfix;
    }
    
    public double evaluate(ArrayList<String> postfix, double X)							 //	gives value of function at X
    {
        Stack<Double> st = new Stack<Double>();
        for(String tmp : postfix)
        {
            char ch = tmp.charAt(0);
            int P  = getPriority(ch);
            
            if(ch == 'x')
                st.push(X);
            else if(P == -1)
                st.push(Double.parseDouble(tmp));
            else
            {
                if(getPriority(ch) >= 7 && getPriority(ch) <= 10)
                {
                    double a = st.peek();       st.pop();
                    if(ch == 'S')
                        a = Math.sin(a);
                    if(ch == 'C')
                        a = Math.cos(a);
                    if(ch == 'T')
                        a = Math.tan(a);
                    if(ch == 'L')														//	problem in LOG if less than 0
                    {
//                      if(a < 0.0)
//                          return 0.0;
//                      else 
                        a = (a > 0.0) ? Math.log(a) : 0;
                    }
                    st.push(a);
                }
                else
                {
                    double a = st.peek();       st.pop();
                    double b = st.peek();       st.pop();
                    if(ch == '+')
                        a = b + a;
                    if(ch == '-')
                        a = b - a;
                    if(ch == '*')
                        a = b * a;
                    if(ch == '/')
                        a = (double)b / a;
                    if(ch == '^')
                        a = Math.pow(b, a);
                    if(ch == '%')
                        a = b % a;
                    st.push(a);
                }
            }
        }
        return st.peek();          
    }
    
    public double evaluate2variable(ArrayList<String> postfix, double X, double Y)       //  function evaluation of 2 variable
    {
        Stack<Double> st = new Stack<Double>();
        
        for(String tmp : postfix)
        {
            char ch = tmp.charAt(0);
            int P  = getPriority(ch);
            
            if(ch == 'x')
                st.push(X);
            else if(ch == 'y')
                st.push(Y);
            else if(P == -1)
                st.push(Double.parseDouble(tmp));
            else
            {
                if(getPriority(ch) >= 7 && getPriority(ch) <= 10)
                {
                    double a = st.peek();       st.pop();
                    if(ch == 'S')
                        a = Math.sin(a);
                    if(ch == 'C')
                        a = Math.cos(a);
                    if(ch == 'T')
                        a = Math.tan(a);
                    if(ch == 'L')
                    {
//                      if(a < 0.0)
//                          return 0.0;
//                      else 
                        a = (a > 0.0) ? Math.log(a) : 0;
                    }
                    st.push(a);
                }
                else
                {
                    double a = st.peek();       st.pop();
                    double b = st.peek();       st.pop();
                    if(ch == '+')
                        a = b + a;
                    if(ch == '-')
                        a = b - a;
                    if(ch == '*')
                        a = b * a;
                    if(ch == '/')
                        a = (double)b / a;
                    if(ch == '^')
                        a = Math.pow(b, a);
                    if(ch == '%')
                        a = b % a;
                    st.push(a);
                }
            }
        }
        return st.peek();          
    }
    
    public boolean checkRelation(String relation, double a, double b)				 	 //	checks relation between LHS and RHS
    {
        if(relation.equals("<") && a < b)
            return true;
        if(relation.equals("<=") && a <= b)
            return true;
        if(relation.equals("=") && Math.abs(a - b) < EPS)
            return true;
        if(relation.equals(">=") && a >= b)
            return true;
        if(relation.equals(">") && a > b)
            return true;
        return false;
    }
    
    public double integral(ArrayList<String> postfix, double a, double b)              	 //  Integral value by simpson's 3/8 rule
    {
        double area = 0.0, X;
        double h = (double)(b-a) / deltaH;
        
        for(int i = 0; i <= deltaH; i++)
        {
            X = evaluate(postfix, a + (double)i*h);             
            if(i % deltaH == 0)
                area += X;
            else if(i % 3 == 0)
                area += 2*X;
            else
                area += 3*X;
        }
        return (double)(3.0*h / 8.0) * area;
    }
    
    public double derivative(ArrayList<String> postfix, double a)                         //  derivative value  By limit method
    {
        double h = deltaH * 0.001;
        return (evaluate(postfix, a + h) - evaluate(postfix, a - h)) / (2*h);           
    }
}
