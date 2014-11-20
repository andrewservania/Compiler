package compiler;

import java.util.LinkedList;

import tokenizer_week_2.LanguageWord.Beschrijving;
import tokenizer_week_2.Token;

public class CodeCompiler {


	//De gekoppelde lijst in Java kent geen referenties naar 
	//knooppunten op de dure index na, die je uiteraard niet gebruikt (1 als waardering).
	//TODO: Maar WAAR???
	//Lees week 4 Slide 3 om te BEGRIJPEN wat er in welke klasse aanwezig moet zijn
	
	
	
	//1. Doe Niets
	
	//2. Conditie
	Conditie whileConditie;
	
	//3. ConditieSprong (If false gaan naar laatste Do Niets)
	ConditionalJump conditionalJump = new ConditionalJump();

	//4. Doe niets
	
	//5. Statement
	Statement whileStatement;
	
	//6. Jump
	Jump jump = new Jump();
	
	//7. Doe Niets
	
	
	LinkedList<Token> whileConditionTokens;
	LinkedList<Token> whileStatementTokens;
	
	boolean mWhileFound = false;
	boolean mWhileBeginConditionStart = false;
	boolean mWhileEndConditionFound = false;
	
	boolean mWhileBeginStatementStart = false;
	boolean mWhileEndStatementFound = false;
	
	public CodeCompiler(){
		
	}
	
	public CodeCompiler(LinkedList<Token> tokens){
		whileConditionTokens = new LinkedList<Token>();
		whileStatementTokens = new LinkedList<Token>();
		
		for(Token token : tokens ){ // 1. Check all tokens for a 'WHILE' Keyword
			
			//compileNewVariables(token);
			compileWhileCondition(token);
			compileWhileStatement(token);
			
		
		}	
		
		whileConditie = new Conditie(whileConditionTokens);
		whileStatement = new Statement(whileStatementTokens);
		
	}
	
	// What will the method parameter be??
	// IF while is found, compute/compile next statement within that level
	
	private void compileWhileCondition(Token token){
	
		//WHILE CONDITION
		

		// 2. If token is a WHILE
		if(token.beschrijvingEnumObject == Beschrijving.WHILE){ 
			mWhileFound = true;    //3. WHILE Found
		}
		
		
		// 4. If WHILE has been found			
		if(mWhileFound == true){  
			
			if(token.beschrijvingEnumObject == Beschrijving.BEGIN_CONDITION){
				mWhileBeginConditionStart = true;
			}
			
		}
		
		
		//5. If WHILE BEGIN CONDITION is true
		if(mWhileBeginConditionStart == true){
			
			if(token.beschrijvingEnumObject == Beschrijving.END_CONDITION){
				mWhileBeginConditionStart = false;
			}
			
			
			
			if(token.beschrijvingEnumObject != Beschrijving.BEGIN_CONDITION){
				if(token.beschrijvingEnumObject == Beschrijving.END_CONDITION){
					// Do NOT add END_CONDITION Brackets
				}
				else{
					whileConditionTokens.add(token);
				}
				
			}
			
		}
		
		
	}
	
	private void compileWhileStatement(Token token){
		//WHILE STATEMENT 
		//6. ...
		if(token.beschrijvingEnumObject == Beschrijving.BEGIN_STATEMENT){
			mWhileBeginStatementStart = true;
		}	
			
		
		//7. If WHILE BEGIN CONDITION is true
		if(mWhileBeginStatementStart == true){
			
			
			if(token.beschrijvingEnumObject == Beschrijving.END_STATEMENT){
				mWhileBeginStatementStart = false;
			}
			
			if(token.beschrijvingEnumObject != Beschrijving.BEGIN_STATEMENT){
				
				if(token.beschrijvingEnumObject == Beschrijving.END_STATEMENT){
					
					// Do NOT add END_STATEMENT Brackets
				}
				else{
					whileStatementTokens.add(token);
				}
			}
		}
		
	}

	private void compileNewVariables(Token token){
		
		if(token.beschrijvingEnumObject == Beschrijving.VARIABLE){
			
			int newIntVariable;
			
			
		}
		
		
		
		
		
		
	}
}
