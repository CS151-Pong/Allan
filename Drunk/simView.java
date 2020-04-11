/* Created by Allan 4/6/2020
 * Edited by Catalina 4/10 Made global Simulation, rewrote both constructor and paintComponent
 * 
 * 
 * 
 */

package Drunk;

import java.awt.Color;
import java.awt.Graphics;

import CompleteSimStation.Agent;
import CompleteSimStation.Simulation;
//import MVC.Model;
//import MVC.View;
import mvc.*;

public class simView extends View {

	/**
	 * 
	 */
	Simulation sim = new Simulation();
	private static final long serialVersionUID = 4890019208920195218L;
	
	
	public simView(Model model) {
		super(model);
		setSize(Simulation.WORLD_SIZE, Simulation.WORLD_SIZE);
		sim = (Simulation)model;
		sim.populate();
		
	}
	
	public void paintComponent(Graphics gc) 
	{
		
		Color oldColor = gc.getColor();
		gc.setColor(Color.RED); 	// fills the circle to a new color then sets it back
		for (Agent a : sim.getAgents())
		{
			gc.fillOval(a.getX(), a.getY(), 5, 5);
		}
		
		gc.setColor(oldColor);
	}
}
