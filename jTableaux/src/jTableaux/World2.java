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
			if(!f.hasBeenused()){
			switch(f.getMainConnective())
			{
			
			case "!!":f.toggleUsed();
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
			case "!|":f.toggleUsed();
				Formula g = new Formula(f.getLeftFormula().getLeftFormula(),"!");
				Formula h = new Formula(f.getLeftFormula().getRightFormula(),"!");
				if(!alreadyHere(g))
					formulas.add(g);
				if(!alreadyHere(h))
					formulas.add(h);
				break;
			case "&":f.toggleUsed();
				Formula g1 = new Formula(f.getLeftFormula());
				Formula h1 = f.getRightFormula();		//why does this work?
				if(!alreadyHere(g1))
					formulas.add(g1);
				if(!alreadyHere(h1))
					formulas.add(h1);
				break;
			case "!->":f.toggleUsed();
				Formula g2 = new Formula(f.getLeftFormula().getLeftFormula());
				Formula h2 = new Formula(f.getLeftFormula().getRightFormula(),"!");
				if(!alreadyHere(g2))
					formulas.add(g2);
				if(!alreadyHere(h2))
					formulas.add(h2);
				break;
			case "![]":f.toggleUsed();
				World2 v = new World2(name+"R"+name+"_v | created by: " + f.renderAsString());
				v.addFormula(new Formula(f.getLeftFormula().getLeftFormula(),"!"));
				related_worlds.add(v);
				break;
			case "[]":f.toggleUsed();
				for(int k=0;k<related_worlds.size();k++)
				{//related_worlds.get(k).addFormula(
					Formula t = new Formula(f.getLeftFormula());
					if(!related_worlds.get(k).alreadyHere(t))
						related_worlds.get(k).addFormula(t);
				}
				break;
			case "<>":f.toggleUsed();
				World2 v2 = new World2(name+"R"+name+"_v | created by: " + f.renderAsString());
				v2.addFormula(new Formula(f.getLeftFormula()));
				related_worlds.add(v2);
				break;
			case "!<>":f.toggleUsed();
				for(int k=0;k<related_worlds.size();k++)
				{//related_worlds.get(k).addFormula(
					Formula t = new Formula(f.getLeftFormula().getLeftFormula(),"!");
					if(!related_worlds.get(k).alreadyHere(t))
						related_worlds.get(k).addFormula(t);
				}
				break;
			case "|":f.toggleUsed();
				Formula g3 = new Formula(f.getLeftFormula());
				World2 v1 = new World2(this);
				formulas.add(g3);
				v1.addFormula(f.getRightFormula());
				branches.add(v1);
				break;
			case "->":f.toggleUsed();
				Formula g4 = new Formula(f.getLeftFormula());
				World2 v3 = new World2(this);
				formulas.add(new Formula(g4,"!"));
				v3.addFormula(f.getRightFormula());
				branches.add(v3);
				break;
			}
			}

		}
		for(int i=0;i<related_worlds.size();i++)
		{
			related_worlds.get(i).doRules();
		}
		for(int i=0;i<branches.size();i++)
			branches.get(i).doRules();
	}
	
	
	public World2(World2 w)
	{
		name = w.getName();
		ArrayList<Formula> newform = w.getFormulas();
		ArrayList<World2> newrelated = w.getRelated();
		ArrayList<World2> newbrnch = w.getBranches();
		for (int i=0;i<newform.size();i++)
		{
			
			formulas.add(new Formula(newform.get(i)));
			if(newform.get(i).hasBeenused())
				formulas.get(i).toggleUsed();
		}
		for (int i=0;i<newrelated.size();i++)
		{
			related_worlds.add(new World2(newrelated.get(i)));
		}
		for (int i=0;i<newrelated.size();i++)
		{
			related_worlds.add(new World2(newrelated.get(i)));
		}
		for (int i=0;i<newbrnch.size();i++)
		{
			related_worlds.add(new World2(newbrnch.get(i)));
		}		
		
	}
	
	public ArrayList<World2> getBranches()
	{
		return branches;
	}
	
 	public ArrayList<World2> getRelated()
	{
		return related_worlds;
	}
	
	public ArrayList<Formula> getFormulas()
	{
		return formulas;
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
		for(int i=0;i<branches.size();i++)
		{
			System.out.println("**This world branches from: "+name);
			branches.get(i).printBranch();
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
	
	
	public ArrayList<World2> branches = new ArrayList<World2>();
	
	private String name;
	public ArrayList<Formula> formulas = new ArrayList<Formula>();
	public ArrayList<World2> related_worlds = new ArrayList<World2>();

}
