package jTableaux;

import java.util.ArrayList;

public class Valuation {
	
	public Valuation(World w)
	{
		branches.add(w);
	}

	private static ArrayList<World> branches = new ArrayList<World>();
}
