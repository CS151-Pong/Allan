/* Created by Allan 4/4/2020
 * Edited by Catalina 4/10 Changed randomHeading to setRandomHeading and random number generations
 * 
 * 
 * 
 */
package Drunk;

import java.util.Random;
//import java.util.concurrent.ThreadLocalRandom;

import CompleteSimStation.Agent;

public class Drunk extends Agent
{
	private int steps;
	private Random random = new Random();

	public Drunk(String name) {
		super(name);
		steps= random.nextInt(10)+1;
	}
	
	public void update() 
	{
		//ThreadLocalRandom.current().nextInt(min, max + 1);
		setRandomHeading();
		steps = random.nextInt(9)+1;
		move(steps);
		//changed();
	}
	
	public int getSteps() {
		return steps;
	}
}
