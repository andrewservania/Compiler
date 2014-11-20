package tokenizer_week_2;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Tokenizer {

	// public static String testString =
	// " Harry>  x==5   <  [ Potter>   \"It Works! x = x++1\"   < ]";
	// \ en / even vervangd met [ en ] om foutmeldingen in Eclipse te vermijden
	// public static String testString =
	// " Harry>x==5<[ Poop>It Works! x = x++1<]";
	
	/*
	    1. Create language 	
		2. Add language words to a dictionary
		3. Make up a single line of the new language		
		4. Tokenize the words in this line(add them	to a list)		
		5. Use that list to detect words		
		6. Print it out
	 */
	
	private int level = 1;
	private int characterPositionInLineCounter = 0;
	private int characterLine = 1;  
	private LinkedList<LanguageWord> languageDictionary; //Add all the words of your language in here
	private LinkedList<Token> tokens;
	
	final class Bracket{	
		String ORDER;
		String TYPE;
		int LEVEL;
		int POSITION;
		int LINE;
		int ID;
		
		public Bracket(String order, String type, int level, int position, int line, int id)
		{
			ORDER = order;
			TYPE = type;
			LEVEL = level;
			POSITION = position;
			LINE = line;
			ID = id;
		}
		

		
	}
	
	public Tokenizer() {
		languageDictionary = new LinkedList<LanguageWord>();
		tokens = new LinkedList<Token>();
		
		add("if", LanguageWord.Beschrijving.IF,0,0);
		add("else", LanguageWord.Beschrijving.ELSE,0,0);
		add("while", LanguageWord.Beschrijving.WHILE,0,0);
		
		add("[0-9]",LanguageWord.Beschrijving.INTEGER,0,0);
		add("[a-zA-Z][a-zA-Z0-9_]*",LanguageWord.Beschrijving.VARIABLE,0,0);
		
		add("\\+=", LanguageWord.Beschrijving.ADDITION_COMPOUND_ASSIGNMENT,0,0);
		add("\\-=", LanguageWord.Beschrijving.SUBSTRACTION_COMPOUND_ASSIGNMENT,0,0);
		add("\\/=", LanguageWord.Beschrijving.DIVISION_COMPOUND_ASSIGNMENT,0,0);
		add("\\*=", LanguageWord.Beschrijving.MULTIPLICATION_COMPOUND_ASSIGNMENT,0,0);
		
		
		add("==", LanguageWord.Beschrijving.EQUAL,0,0);
		add("=", LanguageWord.Beschrijving.ASSIGNMENT_OPERATOR,0,0);
		
		add("<=", LanguageWord.Beschrijving.LESS_THAN_OR_EQUAL_TO,0,0);
		add(">=", LanguageWord.Beschrijving.GREATER_THAN_OR_EQUAL_TO,0,0);
		add("<", LanguageWord.Beschrijving.LESS_THAN,0,0);
		add(">", LanguageWord.Beschrijving.GREATER_THAN,0,0);
		
		add("\\!=", LanguageWord.Beschrijving.NOT_EQUAL_TO,0,0);
		//add("||", LanguageWord.Beschrijving.OR,0,0);  WHY IS THIS ONE A PROBLEM?
		add("\\&&", LanguageWord.Beschrijving.AND,0,0);
		
		add("\\+", LanguageWord.Beschrijving.ADDITION,0,0);
		add("\\-", LanguageWord.Beschrijving.SUBSTRACTION,0,0);
		add("\\*", LanguageWord.Beschrijving.MULTIPLICATION,0,0);
		add("\\/", LanguageWord.Beschrijving.DIVISION,0,0);
		
		add("\\(", LanguageWord.Beschrijving.BEGIN_CONDITION,1,0);
		add("\\)", LanguageWord.Beschrijving.END_CONDITION,1,0);
		
		add("\\{", LanguageWord.Beschrijving.BEGIN_STATEMENT,1,0);
		add("\\}", LanguageWord.Beschrijving.END_STATEMENT,1,0);
		
		add(";", LanguageWord.Beschrijving.SEMICOLON,0,0);
		add("&n", LanguageWord.Beschrijving.NEWLINE, 0,0);

		add("'", LanguageWord.Beschrijving.STRING, 0,0);
		
		//Test LanguageWords
		add("Harry", LanguageWord.Beschrijving.IF, 0, 0);
		add("Potter", LanguageWord.Beschrijving.PRINT, 0, 0);
		
	}

	public LinkedList<Token> getTokens() {
		
		return tokens;
	}

	public void add(String tekst, LanguageWord.Beschrijving enumBeschrijving, int level, int partner) {
		languageDictionary.add(new LanguageWord(Pattern.compile("^(" + tekst + ")"), enumBeschrijving, level, partner)); //NEEDED!!!
	}
	
	public void addWithVariableValue(String tekst, LanguageWord.Beschrijving enumBeschrijving, int level, int partner, int intVariableValue) {
		languageDictionary.add(new LanguageWord(Pattern.compile("^(" + tekst + ")"), enumBeschrijving, level, partner)); //NEEDED!!!
	}

	public LinkedList<Token> tokenize(String stringToProcess) throws ParseException {
		/*
		 *  What's the difference ?????????????
		 *  Which one is better?faster? use less memory??
		 *  which one? WHICH ONE?!?
		 *  :D XD
		 *  String s = str;
		 *  String s = new String(str);
		 *  String s = new String(str).trim(); 
		 */
		
		ArrayList<Bracket> arrayListOfBeginBrackets = new ArrayList<Bracket>();
		ArrayList<Bracket> arrayListOfEndBrackets = new ArrayList<Bracket>();	
		String temporaryString = stringToProcess.trim();
		int tokenID = 0;
		int beginBracketID = 0;
		int endBracketID = 0;
		
		//tokens.clear();  WHY DO I NEED THIS LINE???

		while (!temporaryString.equals("")) {   //  While temporaryString is NOT empty
			
			boolean match = false;
			
			for (LanguageWord word : languageDictionary) {
				
				// see if 'word' contains the same word as in 'temporaryString'	
				Matcher matcherChecker = word.regex.matcher(temporaryString);	
				
				if (matcherChecker.find()) {
					match = true;
					String sequence = matcherChecker.group().trim();		
					characterPositionInLineCounter++;
					
					//Increase Level if ( or { is detected
					if(sequence.equals("(") || sequence.equals("{"))
					{
						beginBracketID++;
						
						arrayListOfBeginBrackets.add(new Bracket("BEGIN_BRACKET",sequence,level,characterPositionInLineCounter,characterLine,beginBracketID));
						level++;
					}
					
					//Decrease Level if ( or { is detected
					if(sequence.equals(")")|| sequence.equals("}"))
					{
						endBracketID++;
						arrayListOfEndBrackets.add(new Bracket("END_BRACKET",sequence,level,characterPositionInLineCounter,characterLine,endBracketID));
						level--;
					}
					
					if(sequence.equals("&n"))
					{
						characterLine++;
						characterPositionInLineCounter = 1;
					}
					
			
					tokenID++;
				
					Token newToken = new Token(tokenID,sequence, level, word.partner,word.beschrijvingEnumObject,characterLine,characterPositionInLineCounter,beginBracketID,endBracketID);
						
					tokens.add(newToken);
					
					temporaryString = matcherChecker.replaceFirst("").trim();
					
					break;
				}
			}

			//Check for unknown characters
			checkForUnknownCharacters(match);
			
		}
		
		
		// partner up brackets with their respective partners
		assignBracketPartners(arrayListOfBeginBrackets, arrayListOfEndBrackets);
			
		//check for missing begin brackets
		detectMissingBrackets(tokens, arrayListOfBeginBrackets, arrayListOfEndBrackets);
			
		return tokens;
		
	}
	
	public void checkForUnknownCharacters(boolean match) throws ParseException
	{
		if (!match) {
			throw new ParseException("SYNTAX_ERROR_01: Unexpected character in input at: \nline: " + (characterLine+1) + "\ncharacter position: " + (characterPositionInLineCounter+1),
					 1);
			
		}
	}

	// Test Method to verify the amount of brackets
	public void printOutBrackets(LinkedList<Token> tokens, ArrayList<Bracket> arrayListOfBeginBrackets, ArrayList<Bracket> arrayListOfEndBrackets)
	{	
		for (Token addedToken : tokens)
		{
				if(addedToken.tekst.equals("("))
				{
					System.out.println("Token tekst:" + addedToken.tekst +  " Token position: " + addedToken.positieInRegel + " Bracket ID: " + addedToken.beginBracketID);
						
				}
				
				if(addedToken.tekst.equals("{"))
				{
					System.out.println("Token tekst:" + addedToken.tekst +  " Token position: " + addedToken.positieInRegel + " Bracket ID: " + addedToken.beginBracketID);
						
				}
				
			
				if(addedToken.tekst.equals(")"))
				{
					System.out.println("Token tekst:" + addedToken.tekst + " Token position: " + addedToken.positieInRegel + " Bracket ID: " + addedToken.endBracketID);
						
				}
				
				if(addedToken.tekst.equals("}"))
				{
					System.out.println("Token tekst:" + addedToken.tekst + " Token position: " + addedToken.positieInRegel + " Bracket ID: " + addedToken.endBracketID);
						
				}
		}
		
		System.out.println();
		
		for(Bracket addedBeginBracket : arrayListOfBeginBrackets)
		{
			System.out.println("Bracket Tekst: " + addedBeginBracket.TYPE + " Position: "+addedBeginBracket.POSITION +" Begin Bracket ID: "+ addedBeginBracket.ID);
		}
				
		for(Bracket addedEndBracket : arrayListOfEndBrackets)
		{
			System.out.println("Bracket Tekst: " + addedEndBracket.TYPE + " Position: "+addedEndBracket.POSITION +" Begin Bracket ID: "+ addedEndBracket.ID);
		}
		System.out.println();
	}
	
	public void assignBracketPartners(ArrayList<Bracket> arrayListOfBeginBrackets, ArrayList<Bracket> arrayListOfEndBrackets)
	{
		/*
		 * Bracket Needs:
		 * 
		 * int Level
		 * int ID
		 * String type: BEGIN or END
		 * int partner
		 * 
		 * LEVEL and ID must be the same numbers in order to be considered partners
		 * When this statement is true:
		 * The BEGIN partner should get the position of END partner  AND
		 * THe END partner should get the position of the BEGIN partner
		 * 
		 * 
		 * 
		 */
		
		//IMPORTANT FEATURE: PARTNER DETERMINATION		
				for (Token addedToken : tokens)
				{
					for(Bracket addedEndBracket : arrayListOfEndBrackets)
					{
						if(addedToken.tekst.equals("("))
						{
							
								if(addedToken.level == addedEndBracket.LEVEL && addedToken.beginBracketID == addedEndBracket.ID)
								{
									
									addedToken.Partner = addedEndBracket.POSITION;
								}
						}
						
						if(addedToken.tekst.equals("{"))
						{
							if(addedToken.level == addedEndBracket.LEVEL && addedToken.beginBracketID == addedEndBracket.ID)
							{
								
								addedToken.Partner = addedEndBracket.POSITION;
							}
						}
						
					}
					
						for(Bracket addedBeginBracket : arrayListOfBeginBrackets)
						{
					
							if(addedToken.tekst.equals(")"))
							{
								if(addedToken.level == addedBeginBracket.LEVEL && addedToken.beginBracketID == addedBeginBracket.ID)
								{
									
									addedToken.Partner = addedBeginBracket.POSITION;
								}
							}
						
							if(addedToken.tekst.equals("}"))
							{
								if(addedToken.level == addedBeginBracket.LEVEL && addedToken.beginBracketID == addedBeginBracket.ID)
								{
									
									addedToken.Partner = addedBeginBracket.POSITION;
								}
							}
					
						}

			
				}

		
	}
	
	public void detectMissingBrackets(LinkedList<Token> tokens, ArrayList<Bracket> arrayListOfBeginBrackets, ArrayList<Bracket> arrayListOfEndBrackets) throws ParseException
	{
		
		if(arrayListOfBeginBrackets.size()<arrayListOfEndBrackets.size())
		{
			throw new ParseException("SYNTAX_ERROR_02: Missing begin brackets" + 
					"\nAmount of begin brackets: " + arrayListOfBeginBrackets.size() + 
					"\nAmount of end brackets: "+ arrayListOfEndBrackets.size()
					,2);
			
		}
		
		//check for missing end brackets
		if(arrayListOfBeginBrackets.size() > arrayListOfEndBrackets.size()){
			throw new ParseException("SYNTAX_ERROR_03: Missing end brackets" +
					"\nAmount of begin brackets: " + arrayListOfBeginBrackets.size() + 
					"\nAmount of end brackets: "+ arrayListOfEndBrackets.size()		
					,3);
		}
		
	}

}
