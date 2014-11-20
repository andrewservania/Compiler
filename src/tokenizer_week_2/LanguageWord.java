package tokenizer_week_2;

import java.util.regex.Pattern;


public class LanguageWord {

	public  Pattern regex;
	public  String tekst;
	
	public  int level;
	public  int partner;
	
	public 			enum 			Beschrijving {
		  IF,
		  ELSE,
		  WHILE, 
		  SWITCH, 
		  BEGIN_CONDITION, 
		  IDENTIFIER,
		  END_CONDITION, 
		  BEGIN_STATEMENT,
		  MULTIPLICATION,
		  DIVISION,
		  ADDITION,
		  SUBSTRACTION,
		  LESS_THAN,
		  GREATER_THAN,
		  NOT_EQUAL_TO,
		  OR,
		  AND,
		  LESS_THAN_OR_EQUAL_TO,
		  GREATER_THAN_OR_EQUAL_TO,
		  VARIABLE,
		  EQUAL,
		  ASSIGNMENT_OPERATOR,
		  MULTIPLICATION_COMPOUND_ASSIGNMENT,
		  DIVISION_COMPOUND_ASSIGNMENT,
		  ADDITION_COMPOUND_ASSIGNMENT,
		  SUBSTRACTION_COMPOUND_ASSIGNMENT,
		  SEMICOLON,
		  END_STATEMENT,
		  INTEGER, 
		  STRING,
		  PRINT,
		  NEWLINE}; 

    public Beschrijving beschrijvingEnumObject;
    
    public String beschrijvingString;
	
	
	
	public LanguageWord(Pattern word, Beschrijving  enumBescrijving , int level, int partner) {
		super();
		this.regex = word;
		this.tekst = word.toString();
		this.level = level;
		this.partner = partner;
		
		beschrijvingEnumObject = enumBescrijving;
		beschrijvingString = enumBescrijving.name();
		
		
	}

}
