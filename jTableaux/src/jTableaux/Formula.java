package jTableaux;

public class Formula {

	public Formula(String lit)
	{
		
	}
	
	public String getMainConnective()
	{
		return connective;
	}
	
	public boolean hasBeenused()
	{
		return ruleUsed;
	}
	
	private String determineConnective(String lit)
	{
		String conn="";
		
		return conn;
	}
	
	private String connective;
	private Formula[] subformulas;
	private boolean ruleUsed = false;
}
