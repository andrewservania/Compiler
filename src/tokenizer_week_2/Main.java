package tokenizer_week_2;

import java.text.ParseException;
import java.util.LinkedList;

import compiler.CodeCompiler;


public class Main {

	public static String testString = "Harry ( x == 5 ) { Potter 'helloworld' }";
	public static String bertTestString = "if ( x = 3 ) { x = 2 ; } else { x = 4 ; &n }";
	public static String compilerTestString = "while(x<3){ x+=1;}";
	
public static void main(String[] commandLineArguments) {
		Tokenizer tokenizer = new Tokenizer();
		LinkedList<Token> tokens = null;
		
		try{
			tokens = tokenizer.tokenize(compilerTestString);		
		   }
		catch (ParseException e){
		System.out.println(e.getMessage());		
	   }
	//	PrintTokens(tokens);
			
		CodeCompiler compiler = new CodeCompiler(tokens);
		
		//TODO:Implement compiler logic here...
		
		//if (conditie ==true)
		// { Execute statement}
		
		
	}

public static void PrintTokens(LinkedList<Token> tokens){
	System.out.println("Token ID \t|Tekst \t\t| Identifier \t\t\t| Level  | Positie In Regel  | Regel Nummer | Partner");
	for (Token tok : tokens) {
	System.out.format("%03d \t\t %10s \t %30s   %2d \t\t  %2d \t\t %2d \t\t%2d",
			tok.tokenID, 
			tok.tekst,
			tok.beschrijvingString,
			tok.level,
			tok.positieInRegel,
			tok.regelNummer,
			tok.Partner);
	
	System.out.println();
	}
	
	}

}



