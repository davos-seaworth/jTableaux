package jTableaux;

public class Main {
	public static void main(String[] args)
	{
	//	Formula f = new Formula("a'");
	//	Formula f2 = new Formula("<>(a')");
	//	Formula f3 = new Formula("(a)&(b)");
		
		/**Formula f4 = new Formula("((a)&(b))|(c)");
		System.out.println(f4.getLeftFormula().getLeftFormula().getAtom());
		System.out.println(f4.getLeftFormula().getRightFormula().getAtom());
		System.out.println(f4.getRightFormula().getAtom());**/
		
		Formula p = new Formula("((a)->(d))&((b)|(c))");
		Formula c = new Formula("b");
		//p.printForm();
		World w = new World();
		w.addPremise(p);
		w.addConclusion(c);
		w.printFormulas();
	}
}
