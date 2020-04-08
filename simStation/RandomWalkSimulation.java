package simStation;

import MVC.Model;

public class RandomWalkSimulation extends SimStationFactory {
public RandomWalkSimulation(){
	super();
}
public Model makeModel() {
	return new Drunk();
}
}
