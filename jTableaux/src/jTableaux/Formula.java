package jTableaux;

public class Formula {

	public Formula(String lit)
	{
		
	}
	
	public String getMainConnective()
	{
		return connective;
	}
	
	private String connective;
	private Formula[] subformulas;
}
