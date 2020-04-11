/* Created by Catalina Lamboglia 4/4/2020
 * Created setRandomHeading 4/6 by Catalina
 * Edited run 4/10 Catalina, added setThread
 * 
 * 
 */

package CompleteSimStation;

import java.util.Random;

import mvc.Utilities;
import mvc.*;


public abstract class Agent extends Model implements Runnable 
{
	
	private static final long serialVersionUID = 8402972125725120175L;
	private transient Thread thread;
	protected String name;
	protected Heading heading;
	protected Simulation sim;
	private volatile int x;
	private volatile int y;
	private AgentState state;
	private static int WORLD_SIZE = 250;
	private Random rand;
	//private boolean suspend = false;
	
	public Agent(String name) 
	{
		this.name = name;
		state = AgentState.READY;
		setThread(new Thread(this));
		
		rand = new Random();
		setRandomHeading();
		x = rand.nextInt(WORLD_SIZE + 1);
		y = rand.nextInt(WORLD_SIZE + 1);
	}
	
	public synchronized String toString() { return name + ".state = " + state; }
	public synchronized int getX() {return x;}
	public synchronized int getY() {return y;}
	public void setThread(Thread thread) {this.thread = thread;}
	
	public synchronized AgentState getState() { return state; }
	
	private boolean isStopped()
	{
		switch (state) // could be shorter
		{
			case STOPPED:
				return true;
			default:
				return false;
		}
		
	}

	private boolean isSuspended()
	{
		switch (state)
		{
			case SUSPENDED:
				return true;
			default:
				return false;
		}
		
	}
	
	public void setRandomHeading()
	{
		int rand= Utilities.rng.nextInt(4);
		if (rand == 0)
			heading = Heading.NORTH;
		else if (rand == 1)
			heading = Heading.EAST;
		else if (rand == 2)
			heading = Heading.SOUTH;
		else if (rand == 3)
			heading = Heading.WEST;
		else // shouldn't be a fourth case, just set a default if this occurs
			heading = Heading.NORTH;

		changed();
	}
	
	public void setHeading(Heading newHeading)
	{
		heading=newHeading;
	}
	
	public void setSimulation(Simulation sim) // set this in Simlation when Sim adds Agents
	{
		this.sim = sim; // agent.SetSimlation(this); goes within addAgent within Simulation
	}
	
	public String getName() {
		return name;
	}

	public Heading getHeading() {
		return heading;
	}

	public Simulation getSim() {
		return sim;
	}

	@Override
	public void run() {
		//thread = Thread.currentThread(); // catch my thread
		while(!isStopped()) 
		{
			update();
			
			try 
			{
				Thread.sleep(100); // be cooperative
				synchronized(this) {
					while(isSuspended()) { wait(); }
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			changed();
		}
		
	}
	
	public void start()
	{
		//thread = new Thread(this);
		thread.start();
		state = AgentState.RUNNING;
		changed();
	}
	
	public void suspend()
	{
		state = AgentState.SUSPENDED;
	}
	
	public synchronized void resume() {
		if (!isStopped()) {
			state = AgentState.RUNNING;
			notify();
		}
		changed();
	}
	public void stop()
	{
		state = AgentState.STOPPED;
	}
	
	public void move(int steps)
	{
		switch(heading)
		{
			case NORTH:
			{
				y = y - steps; // move up
				if (y < 0) { // don't go above the screen
					y = 0;
				}
				break;
			}
			case SOUTH:
			{
				y = y + steps; // move down
				if (y > WORLD_SIZE) { // don't go below screen
					y = WORLD_SIZE;}
				break;
			}
			case EAST:
			{

				x = x + steps; // move right
				if (x > WORLD_SIZE) { // don't go off the right size
					x = WORLD_SIZE;}
				break;
			}
				
			case WEST:
			{
				x = x - steps; // move left
				if (x < 0) { // don't go off the left side
					x = 0;}
				break;
			}
		}
		changed();
	}
	
	public abstract void update();
	//to be overrided
	
	
}
