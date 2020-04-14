/* Created by Andrew 4/5/2020
 * Edited by Catalina 4/10 made getStats
 * 
 * 
 * 
 */

package CompleteSimStation;

import mvc.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;

import java.awt.Frame;
import java.lang.Math;

public class Simulation extends Model{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6607143526781513246L;
	public static int WORLD_SIZE = 250;

	public Simulation() {
		super();
		// TODO Auto-generated constructor stub
	}
	private Timer timer;
	private int clock = 0;

	List<Agent> agent = new ArrayList<Agent>();
	
	
	//Agent ask, neighbor, candidate;
	
	public void addAgent(Agent a) {
		agent.add(a);
		a.setSimulation(this);
	}
	
	public List<Agent> getAgents()
	{
		return agent;
	}
	
	private void startTimer() {
	   timer = new Timer();
       timer.scheduleAtFixedRate(new ClockUpdater(), 1000, 1000);
    }

    private void stopTimer() {
	  timer.cancel();
	  timer.purge();
    }

    private class ClockUpdater extends TimerTask {
    	public void run() {
    		clock++;
		  //changed();
    	}
    }
  // etc.
    
    public void getStats()
    {
    	String stats = "#agents = " + agent.size() + "\n clock = " + clock;
    	JOptionPane.showMessageDialog(
                new Frame(),
                stats);
    }

	public void setClock(int clock) {
		this.clock = clock;
	}
	public int getClock() {
		return clock;
	}
	
	public void start() {
		for(Agent a: agent) {
			a.start();
		}
		startTimer();
	}
	public void stop() {
		for(Agent a: agent) {
			a.stop();
		}
		stopTimer();
	}
	
	public void suspend() {
		for(Agent a: agent) {
			a.suspend();
		}
		stopTimer();
	}
	public void resume() {
		for(Agent a: agent) {
			a.resume();
		}
		startTimer();
		//suspend = false;
		//changed();
	}
	public int distance(int x1, int x2, int y1, int y2) {
		double x = x2-x1;
		double y = y2-y1;
		x = Math.pow(x, 2);
		y = Math.pow(y, 2);
		x += y;
		x = Math.sqrt(x);
		return (int)x;
	}
	public synchronized Agent getNeighbor(Agent a, double radius) {
		int i = Utilities.rng.nextInt(agent.size());
		boolean found = false;
		Agent temporary = null;
		int size = 0;
		while(!found || size < agent.size()) {
			
			temporary = agent.get(i);
			
			if(distance(temporary.getX(),a.getX(),temporary.getY(), a.getY()) < radius){
				found = true;
				changed();
			}
			else {
				i = Utilities.rng.nextInt(agent.size());
			}
			size++;
		}
		return temporary;
	}
	
	public void populate() 
	{
		//override something

	}
	
}
