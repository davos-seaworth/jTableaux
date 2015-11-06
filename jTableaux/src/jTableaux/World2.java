package jTableaux;

import java.util.ArrayList;

public class World2 {
	
	public World2(String n)
	{
		name = n;
	}
	
	
	public void doRules()
	{
		for(int i=0;i<formulas.size();i++)
		{
			Formula f = formulas.get(i);
			//System.out.println(f.getMainConnective()+"========");
			switch(f.getMainConnective())
			{
			
			case "!!":
				if(f.getLeftFormula().getLeftFormula().isSingleConnective()||f.getLeftFormula().getLeftFormula().isAtomic())
				{
					Formula g = new Formula(f.getLeftFormula().getLeftFormula());
					if (!alreadyHere(g))
						formulas.add(g);
				}
				else
				{
					Formula g = new Formula(f.getLeftFormula().getLeftFormula().getLeftFormula(), f.getLeftFormula().getLeftFormula().getRightFormula(),f.getLeftFormula().getLeftFormula().getMainConnective());
					if(!alreadyHere(g))
						formulas.add(g);
				}
				break;
			case "!|":
				Formula g = new Formula(f.getLeftFormula().getLeftFormula(),"!");
				Formula h = new Formula(f.getLeftFormula().getRightFormula(),"!");
				if(!alreadyHere(g))
					formulas.add(g);
				if(!alreadyHere(h))
					formulas.add(h);
				break;
			case "&":
				Formula g1 = new Formula(f.getLeftFormula());
				Formula h1 = f.getRightFormula();		//why does this work?
				if(!alreadyHere(g1))
					formulas.add(g1);
				if(!alreadyHere(h1))
					formulas.add(h1);
				break;
			case "!->":
				Formula g2 = new Formula(f.getLeftFormula().getLeftFormula());
				Formula h2 = new Formula(f.getLeftFormula().getRightFormula(),"!");
				if(!alreadyHere(g2))
					formulas.add(g2);
				if(!alreadyHere(h2))
					formulas.add(h2);
				break;
			case "![]":
				World2 v = new World2(name+"R"+name+"_v | created by: " + f.renderAsString());
				v.addFormula(new Formula(f.getLeftFormula().getLeftFormula(),"!"));
				related_worlds.add(v);
				break;
			case "[]":
				for(int k=0;k<related_worlds.size();k++)
				{//related_worlds.get(k).addFormula(
					Formula t = new Formula(f.getLeftFormula());
					if(!related_worlds.get(k).alreadyHere(t))
						related_worlds.get(k).addFormula(t);
				}
				break;
			case "<>":
				World2 v2 = new World2(name+"R"+name+"_v | created by: " + f.renderAsString());
				v2.addFormula(new Formula(f.getLeftFormula()));
				related_worlds.add(v2);
				break;
			case "!<>":
				for(int k=0;k<related_worlds.size();k++)
				{//related_worlds.get(k).addFormula(
					Formula t = new Formula(f.getLeftFormula().getLeftFormula(),"!");
					if(!related_worlds.get(k).alreadyHere(t))
						related_worlds.get(k).addFormula(t);
				}
				break;
			case "|":
				Formula g3 = new Formula(f.getLeftFormula());
				
				
			}
		}
		for(int i=0;i<related_worlds.size();i++)
		{
			related_worlds.get(i).doRules();
		}
	}
	
	
	public World2(World2 w)
	{
		name = w.getName();
	}
	
	public String getName()
	{
		return name;
	}
	
	public void sort()
	{
		for (int i=0;i<formulas.size();i++)
		{
			if(!formulas.get(i).getMainConnective().equals("![]")&&!formulas.get(i).getMainConnective().equals("<>"))
				formulas.add(formulas.remove(i));
		}
	}

	public boolean alreadyHere(Formula f)
	{
		String s = f.renderAsString();
		for(int i=0;i<formulas.size();i++)
		{
			if(formulas.get(i).renderAsString().equals(s))
				return true;
		}
		return false;
	}
	
	
	
	
	public void printBranch()
	{
		System.out.println("--Formulas in world: "+name+"--");
		for (int i=0;i<formulas.size();i++)
		{
			System.out.println(formulas.get(i).renderAsString());
		}
		for(int i=0;i<related_worlds.size();i++)
		{
			System.out.println("**This world is related to: "+name+"**");
			related_worlds.get(i).printBranch();
		}
	}
	
	public void addPremise(Formula f)
	{
		formulas.add(f);
	}
	
	public void addConclusion(Formula f)
	{
		formulas.add(new Formula(f, "!"));
	}
	
	public void addFormula(Formula f)
	{
		formulas.add(f);
	}
	
	
	public ArrayList<World> branches = new ArrayList<World>();
	
	private String name;
	public ArrayList<Formula> formulas = new ArrayList<Formula>();
	public ArrayList<World2> related_worlds = new ArrayList<World2>();

}
