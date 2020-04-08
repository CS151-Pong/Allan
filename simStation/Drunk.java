package simStation;

import MVC.Utilities;

public class Drunk extends Agent{
private int steps;
	public Drunk() {
		super();
		steps= Utilities.rng.nextInt(10)+1;
	}
	public void update() {

		String[] headings= new String[4];
		headings[0]= "North";
		headings[1]= "South";
		headings[2]= "East";
		headings[3]= "West";
		int randomHeading= Utilities.rng.nextInt(4)+1;
		heading= headings[randomHeading];
		steps=Utilities.rng.nextInt(9)+1;
		move(steps);
	}
	
	public int getSteps() {
		return steps;
	}
}
