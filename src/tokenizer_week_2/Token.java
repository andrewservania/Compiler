package tokenizer_week_2;

public class Token{
	
	public			int 			tokenID;
	public 			int 			regelNummer;
	public 			int 			positieInRegel;
	public 			String 			tekst;
	
	public 			int 			level;
	public			int 		    Partner;
	public 					String 		beschrijvingString;
	public LanguageWord.Beschrijving    beschrijvingEnumObject;
	boolean isPartnerSet = false;
	public 			int beginBracketID;
	public 			int endBracketID;
	
	//int value for a variable
	public 			int intValue;
	
	
	
	public Token(int tokenID, String tekst, int level, int Partner, LanguageWord.Beschrijving enumBeschrijving, int regelNummer, int positieInRegel, int beginBracketId, int endBracketId){
		super();
		this.tokenID = tokenID;
		this.tekst = tekst;
		this.level = level;
		this.Partner = Partner;
		this.regelNummer = regelNummer;
		this.positieInRegel = positieInRegel;
		beschrijvingEnumObject = enumBeschrijving;
		beschrijvingString = enumBeschrijving.name();
		beginBracketID = beginBracketId;
		endBracketID = endBracketId;
		
		if(enumBeschrijving == LanguageWord.Beschrijving.INTEGER){
			intValue = Integer.parseInt(tekst);
		}
		
		
		
		
		
	}

	public boolean getSetValue()
	{return isPartnerSet;}
	
	public void setSetValue(boolean state)
	{isPartnerSet = state;}
	
	
	public void setVariableValue(int value){
		intValue = value;
	}
	
}
