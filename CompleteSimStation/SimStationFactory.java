/* Created by Catalina Lamboglia 4/4/2020
 * Added getView 4/6 by Catalina
 * 
 * 
 * 
 */


package CompleteSimStation;

import mvc.*;

public class SimStationFactory implements SimFactory
{


	public String[] getEditCommands() { return new String[] {"Start", "Stats", "Stop", "Suspend", "Resume"}; }

	public Command makeEditCommand(Model model, String type) {
		if (type == "Start")
			return new StartCommand(model);
		else if (type == "Stats")
			return new StatsCommand(model);
		else if (type == "Stop")
			return new StopCommand(model);
		else if (type == "Suspend")
			return new SuspendCommand(model);
		else if (type == "Resume")
			return new ResumeCommand(model);
		
		return null;
	}

	public String getTitle() { return "SimStation"; }

	public String[] getHelp() {
		return new String[] {"Simulates Agents moving in an area"};
	}

	public String about() {
		return "SimStation programmed by Catalina Lamboglia, Allan Chen, Andrew Yu in 2020.";
	}

	@Override
	public Model makeModel() {
		//return new Agent();
		return new Simulation();
	}

	@Override
	public View getView(Model m) {
		View view = new View(m);
		return view;
	}
}