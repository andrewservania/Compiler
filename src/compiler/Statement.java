package compiler;

import java.util.LinkedList;

import tokenizer_week_2.Token;

public class Statement {
	//Een statement is alles what er in de
	//BEGIN_STATEMENT { en de END_STATEMENT } zit
	//STATEMENT is x+= 1;
	
	public Statement(LinkedList<Token> statementTokens){
		
		PrintLinkedList(statementTokens);
		//Set the statement misschien bij het aanmaken van een nieuwe
		//statement?
	}
	
	
	public  void PrintLinkedList(LinkedList<Token> tokens){
		
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
	
	
	
	
}
