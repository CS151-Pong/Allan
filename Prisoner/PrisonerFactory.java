package Prisoner;

import CompleteSimStation.SimStationFactory;
import mvc.Model;

public class PrisonerFactory extends SimStationFactory{
	
	public PrisonerFactory(){
		super();
	}
	public String getTitle() { return "Prisoner's Dilemma"; }
	
	public Model makeModel() {
		return new PrisonerSimulation();
	}
}
