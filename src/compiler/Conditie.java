package compiler;

import java.util.LinkedList;

import tokenizer_week_2.Token;

public class Conditie {
	
	boolean state;
	
	double variable1;
	Token enumBeschrijving;
	double variable2;
	
//What is conditie?
	//Alles what in de haakjes staan
	//Dus alles in binnen de BEGIN_CONDITION en END_CONDITION
	
	// CONDITION is x < 3
	// While( x < 3)
	// x+=1;
	//STATEMENT is x+= 1;
	
	//LanguageWord.Beschrijving.LESS_THAN  
	//
	// WHILE ( "VARIABLE" "ENUM_BESCHRIJVING" "INTEGER")
	
	// int x = variable
	// if (
	
	public Conditie(LinkedList<Token> conditionTokens){
	
		assessWhileCondition(conditionTokens);
		
	}
	
	public void PrintLinkedList(LinkedList<Token> tokens){
		System.out.println("Token ID \t|Tekst \t\t| Identifier \t\t\t| Level  | Positie In Regel  | Regel Nummer | Partner  |  Variabe Value");
		for (Token tok : tokens) {
		System.out.format("%03d \t\t %10s \t %30s   %2d \t\t  %2d \t\t %2d \t\t%2d \t\t%2d",
				tok.tokenID, 
				tok.tekst,
				tok.beschrijvingString,
				tok.level,
				tok.positieInRegel,
				tok.regelNummer,
				tok.Partner,
				tok.intValue);
		System.out.println();
		}
	}
	
	public void assessWhileCondition(LinkedList<Token> conditionTokens){
		
		PrintLinkedList(conditionTokens);

	
		variable1 = conditionTokens.get(0).intValue;
		enumBeschrijving = conditionTokens.get(1);		
		variable2 = conditionTokens.get(2).intValue;
		
		//Set Conditie hier zo? Bij het aanmaken van een conditie object? Misschien?

		//While implementatie
		switch (enumBeschrijving.beschrijvingEnumObject){
		
		case LESS_THAN:
			
			if(variable1 < variable2){	
				
				state = true;
				System.out.println("Condition is: " + state);
			}
			else{
				
				state = false;
				System.out.println("Condition is: " + state);
				System.out.println("Statement will not be processed");
			}
				
			break;
			
		case GREATER_THAN:
			
			if(variable1 > variable2){	
				state = true;
				System.out.println("Condition is: " + state);
			}
			else
			{
				state = false;
				System.out.println("Condition is: " + state);
				System.out.println("Statement will not be processed");
			}

			break;
			
		case EQUAL:
			
			if(variable1 == variable2){	
				state = true;
			}
			else{
				state = false;
			}
			break;
		
			default:
				// Nothing at the moment
				
				break;
		}
	}
	
	
	//NOT USED AT THE MOMENT
	public void assessNewVariableDeclaration(LinkedList<Token> conditionTokens){
		
		
		
		
		
	}
	
	
 	public boolean getState(){
		return state;
	}
	
}
