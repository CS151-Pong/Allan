/* Created by Catalina 4/4/2020
 * Edited by Catalina 4/10 Changed execute to Simulation instead of Agent
 * 
 * 
 * 
 */
package CompleteSimStation;


import mvc.Command;
import mvc.Model;

public class SuspendCommand extends Command
{
	public SuspendCommand(Model model) {
		super(model);
	}
	
	public void execute()
	{
		Simulation sim = (Simulation)model;
		sim.suspend();
	}

}
