package jTableaux;

public class Formula {

	public Formula(String lit)
	{
		int conIndex = 0;
		boolean found= false;
		if(lit.charAt(0)!='(')
		{
			if(lit.length()>1)
			{
				if(lit.substring(0,1).equals("!"))
				{
					connective = "!";
					subformulas = new Formula[1];
					subformulas[0] = new Formula(lit.substring(2,lit.length()-1));
					System.out.println(connective+"#"+subformulas[0].getMainConnective());
					connective=connective+subformulas[0].getMainConnective();
					
				//	System.out.println(subformulas[0].getAtom()+"12345");
				}
				else if(lit.substring(0,2).equals("[]")||lit.substring(0,2).equals("<>"))
				{
					connective = lit.substring(0,2);
					subformulas = new Formula[1];
					subformulas[0] = new Formula(lit.substring(3,lit.length()-1));
					
				//	System.out.println(subformulas[0].getAtom()+"12345");
				}
				else
				{
					connective="";
					atom=lit;
					
				//	System.out.println(atom+"plz");
				}
			}
			else
			{
				connective="";
				atom=lit;
				
				//System.out.println(atom+"plz");
			}
		}
		else
		{
			connective = lit;
			while(connective.contains("("))
			{
				if(connective.charAt(connective.length()-1)!=')'&&found==false)
				{
					conIndex = connective.length()-1;
					found = true;
				}
				/**System.out.println(connective.substring(0,connective.lastIndexOf("(")));
				System.out.println(connective.substring(connective.indexOf(")", connective.lastIndexOf("("))+1));**/
				
				connective = connective.substring(0,connective.lastIndexOf("(")) + connective.substring(connective.indexOf(")", connective.lastIndexOf("(") )+1 );
			}
			subformulas = new Formula[2];//System.out.println("the conn is " + connective);
			//System.out.println("a test||"+lit.substring(1,conIndex-1));
			//System.out.println("b test||"+lit.substring(conIndex+2,lit.length()-1));
			if(connective.equals("->"))
				subformulas[0] = new Formula(lit.substring(1,conIndex-2));
			else
				subformulas[0] = new Formula(lit.substring(1,conIndex-1));
			subformulas[1] = new Formula(lit.substring(conIndex+2,lit.length()-1));
			
			//System.out.println(subformulas[0].getAtom()+"*");
			//System.out.println(subformulas[1].getAtom()+"*");
			
		}
/**		System.out.println("-------------------------");
		System.out.println(conIndex);
		System.out.println(connective);
		System.out.println(atom);**/
		
	//	System.out.println("Subformula 1 = " + lit.substring(0,conIndex));
	//	System.out.println("Subformula 2 = " + lit.substring(conIndex+1));
		
		//System.out.println("-------------------------");
		
		
	}
	
	public Formula(Formula a, Formula b, String conn)
	{
		connective = conn;
		subformulas = new Formula[2];
		subformulas[0] = a;
		subformulas[1] = b;
	}
	
	public Formula(Formula a, String conn)
	{
		connective = conn;
		subformulas = new Formula[1];
		subformulas[0] = a;
	}
	
	public Formula(Formula a)
	{
		connective = a.getMainConnective();
		if (!a.isSingleConnective()&&!a.isAtomic())
		{
			subformulas = new Formula[2];
			subformulas[0] = a.getLeftFormula();
			subformulas[1] = a.getRightFormula();
		}
		else if(a.isSingleConnective())
		{
			subformulas = new Formula[1];
			subformulas[0] = a.getLeftFormula();
		}
		if(a.isAtomic())
		{
			atom = a.getAtom();
		}
	}
	
	public String renderAsString()
	{
		String tmp = "";
		tmp+="(";
		if(isAtomic())
			tmp+=atom;
		else
		{
			if(subformulas.length==1)
			{
				if(!connective.contains("!"))
					tmp+=connective+subformulas[0].renderAsString();
				else
					tmp+="!"+subformulas[0].renderAsString();
			}
			else
			{
				tmp+=subformulas[0].renderAsString()+connective+subformulas[1].renderAsString();
			}
		}tmp+=")";
		
		return tmp;
			
	}
	
	public void printForm()
	{
		System.out.print("(");
		if(isAtomic())
			System.out.print(atom);
		else
		{
			if(subformulas.length==1)
			{
				if(!connective.contains("!"))
				{
					System.out.print(connective);
					subformulas[0].printForm();
				}
				else
				{
					System.out.print("!");
					subformulas[0].printForm();
				}
			}
			else
			{
				subformulas[0].printForm();
				System.out.print(connective);
				subformulas[1].printForm();
			}
		}System.out.print(")");
		
	}
	
	
	
	public String getMainConnective()
	{
		if(connective.equals("!"))
		{
			String tmp = connective+subformulas[0].getMainConnective();
			if(tmp.contains("!!"))
			{
				return "!!";
			}		
			return tmp;
		}
		if(connective.contains("!!"))
		{
			return "!!";
		}	
		return connective;
	}
	
	public Formula getLeftFormula()
	{
		if (isAtomic())
			return null;
		return subformulas[0];
	}
	
	public String getAtom()
	{
		if(isAtomic())
			return atom;
		return null;
	}
	
	public Formula getRightFormula()
	{
		if (isAtomic())
			return null;
		return subformulas[1];
	}
	
	public boolean isSingleConnective()
	{
		if(connective.contains("!"))
			return true;
		return connective.equals("!")||connective.equals("[]")||connective.equals("<>");
	}
	
	
	public boolean isAtomic()
	{
		return connective.equals("");
	}
	
	public void toggleUsed()
	{
		ruleUsed = !ruleUsed;
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
	
	private String atom;
	private String connective;
	private Formula[] subformulas;
	private boolean ruleUsed = false;
}
