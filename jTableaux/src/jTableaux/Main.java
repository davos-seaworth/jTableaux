package jTableaux;

public class Main {
	public static void main(String[] args)
	{
	//	Formula f = new Formula("a'");
	//	Formula f2 = new Formula("<>(a')");
	//	Formula f3 = new Formula("!((a)|(b))");
		
		/**Formula f4 = new Formula("((a)&(b))|(c)");
		System.out.println(f4.getLeftFormula().getLeftFormula().getAtom());
		System.out.println(f4.getLeftFormula().getRightFormula().getAtom());
		System.out.println(f4.getRightFormula().getAtom());**/
		
	//	Formula q = new Formula("!(([]((a)->(d)))&((b)|(c)))");
		
	//	System.out.println(q.renderAsString());
		
		
		
	//	System.out.println("______________^_____________________");
		
		
		
		//Formula p = new Formula("!((a)->((c)|(d)))");
		//Formula p = new Formula("((c)|(d))->(a)");
		
	/**	Formula x = new Formula("<>(a)");
		System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
		x.printForm();System.out.println(x.getMainConnective()+"}{"+x.getLeftFormula().getAtom());
		System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");**/
		
		
		//Formula p = new Formula("!((!((a)|(b)))->(!((c)&(d))))");
		
		//Formula p = new Formula("!([](!(<>(a))))");
		
		//Formula p = new Formula("!([](a))");
		//Formula p1 = new Formula("!(<>((b)->(c)))");
		
		//Formula p = new Formula("((a)|(c))->(b)");//("!((!((a)|(c)))&(b))");//("(a)|((b)|(c))");
		
		//System.out.println(p.getMainConnective());
		
		/**Formula f = new Formula("(a)&(b)");
		Formula c = new Formula("b");
		//p.printForm();
		World w = new World();
	//	w.addPremise(p);//w.addPremise(p1);
		w.addConclusion(c);w.addPremise(f);
		w.printFormulas();
		System.out.println();
		w.executeRules();**/
		
		World w = new World();
	//	w.addPremise(new Formula("([](p))->([](q))"));    //"(a)&([](b))"
	//	w.addConclusion(new Formula("[]((p)->(q))"));     //"[](b)"
		
		w.addPremise(new Formula("[](((p)&(q))->(r))"));
		w.addPremise(new Formula("[](p)"));
		w.addConclusion(new Formula("[]((q)->(r))"));
		
		w.sortFormulas();
		
		//w.printFormulas();
		w.executeRules();
		
		//w.printAll();
		w.contradictionSweep();
		w.printAll();
		System.out.println(w.fullIsValid());
		
		//w.printFormulas();
		
		
		////System.out.println("@@@@");
	//	w.formulas.get(2).printForm();
		////System.out.println("@@@@");
//		System.out.println(w.formulas.get(2).getLeftFormula().getMainConnective()+" WHY");
	//	w.executeRules();
	//	w.printFormulas();
	
	}
}
