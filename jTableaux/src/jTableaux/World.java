package jTableaux;

import java.util.ArrayList;

public class World {
	
	public World()
	{
		
	}
	
	public World(Formula f)
	{
		
	}
	
	public void add(Formula f)
	{
		formulas.add(f);
	}
	
	public void addPremise(Formula f)
	{
		formulas.add(f);
	}
	
	public void addConclusion(Formula f)
	{
		formulas.add(new Formula(f, "!"));
	}
	
	public void printFormulas()
	{
		for(int i =0;i<formulas.size();i++)
		{
			formulas.get(i).printForm();
			System.out.println();
		}
	}
	
	public void executeRules()
	{
		
		
		for(int i =0; i<formulas.size();i++)
		{
			for(int k=0;k<branches.size();k++)
				branches.get(k).executeRules();
			
			Formula f = formulas.get(i);
			if(!f.hasBeenused())
			{//System.out.println("made it: "+f.getMainConnective());
				switch(f.getMainConnective())
				{
				case "&":
					f.toggleUsed();
					formulas.add(f.getLeftFormula());
					formulas.add(f.getRightFormula());
					break;
				case "!|":
					f.toggleUsed();
					formulas.add(new Formula(f.getLeftFormula().getLeftFormula(),"!"));
					formulas.add(new Formula(f.getLeftFormula().getRightFormula(),"!"));
					break;
				case "!->":
					f.toggleUsed();
					formulas.add(f.getLeftFormula().getLeftFormula());
					formulas.add(new Formula(f.getLeftFormula().getRightFormula(),"!"));
					//formulas.add(f.getRightFormula());
					break;
				case "!!":
					f.toggleUsed();
					if(!f.getLeftFormula().getLeftFormula().isSingleConnective())
						formulas.add(new Formula(f.getLeftFormula().getLeftFormula().getLeftFormula(), f.getLeftFormula().getLeftFormula().getRightFormula(), f.getLeftFormula().getLeftFormula().getMainConnective()));
					else
						formulas.add(new Formula(f.getLeftFormula().getLeftFormula().getLeftFormula(), f.getLeftFormula().getLeftFormula().getMainConnective()));

					break;
				case "![]":
					f.toggleUsed();
					World v = new World();
					v.add(new Formula(f.getLeftFormula().getLeftFormula(),"!"));
					related_worlds.add(v);
					break;
				case "<>":
					f.toggleUsed();//System.out.println("::::::::^^)");
					World u = new World();
					if(!f.getLeftFormula().isAtomic())
						u.add(new Formula(f.getLeftFormula().getLeftFormula(), f.getLeftFormula().getRightFormula(), f.getLeftFormula().getMainConnective()));
					else
						u.add(new Formula(f.getLeftFormula().getAtom()));
					related_worlds.add(u);
					break;
				case "[]":
					f.toggleUsed();
					for(int i1 =0;i1<related_worlds.size();i1++)
					{
						related_worlds.get(i1).add(new Formula(f.getLeftFormula()));
					}
					break;
				case "!<>":
					f.toggleUsed();
					for(int i1=0;i1<related_worlds.size();i1++)
					{
						related_worlds.get(i1).add(new Formula(f.getLeftFormula().getLeftFormula(),"!"));
					}
					break;
				case "|":
					f.toggleUsed();
					World vp = new World();
					for(int i1=0;i1<formulas.size();i1++)
					{
						vp.add(formulas.get(i1));
					}
					for(int i1=0;i1<related_worlds.size();i1++)
					{
						vp.addW(related_worlds.get(i1));
					}
					
					formulas.add(f.getLeftFormula());
					vp.formulas.add(f.getRightFormula());
					if (!branches.contains(vp))
					{
						branches.add(vp);
					}
					break;
				case "!&":
					f.toggleUsed();
					World vp1 = new World();
					for(int i1=0;i1<formulas.size();i1++)
					{
						vp1.add(formulas.get(i1));
					}
					for(int i1=0;i1<related_worlds.size();i1++)
					{
						vp1.addW(related_worlds.get(i1));
					}
					formulas.add(new Formula(f.getLeftFormula().getLeftFormula(), "!"));
					vp1.formulas.add(new Formula(f.getLeftFormula().getRightFormula(), "!"));
					if (!branches.contains(vp1))
					{
						branches.add(vp1);
					}
					break;
				case "->":
					f.toggleUsed();
					World vp11 = new World();
					for(int i1=0;i1<formulas.size();i1++)
					{
						vp11.add(formulas.get(i1));
					}
					for(int i1=0;i1<related_worlds.size();i1++)
					{
						vp11.addW(related_worlds.get(i1));
					}
					formulas.add(new Formula(f.getLeftFormula(), "!"));
					vp11.formulas.add(f.getRightFormula());
					if (!branches.contains(vp11))
					{
						branches.add(vp11);
					}
					break;
				}
					
			}
		}
	}
	
	private void addW(World w)
	{
		related_worlds.add(w);
	}
	
	public void printPath()
	{
		printFormulas();
		System.out.println("--------------------");
		for(int i = 0; i<related_worlds.size();i++)
		{
			related_worlds.get(i).executeRules();
			related_worlds.get(i).printPath();
		}
	}
	
	public void printAll()
	{
		printPath();
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%");
		for(int i =0;i<branches.size();i++)
		{
			branches.get(i).printAll();
		}
	}
	
	private static Valuation brn;
	public ArrayList<World> branches = new ArrayList<World>();
	public ArrayList<Formula> formulas = new ArrayList<Formula>();
	public ArrayList<World> related_worlds = new ArrayList<World>();
	
}
