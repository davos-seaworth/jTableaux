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
		
		Formula q = new Formula("!(([]((a)->(d)))&((b)|(c)))");
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
		
		Formula p = new Formula("((a)|(c))->(b)");//("!((!((a)|(c)))&(b))");//("(a)|((b)|(c))");
		
		System.out.println(p.getMainConnective());
		
		Formula c = new Formula("o");
		//p.printForm();
		World w = new World();
		w.addPremise(p);//w.addPremise(p1);
		w.addConclusion(c);
		w.printFormulas();
		System.out.println();
		w.executeRules();
		
		w.printAll();
		//w.printFormulas();
		
		
		////System.out.println("@@@@");
	//	w.formulas.get(2).printForm();
		////System.out.println("@@@@");
//		System.out.println(w.formulas.get(2).getLeftFormula().getMainConnective()+" WHY");
	//	w.executeRules();
	//	w.printFormulas();
	}
}
