package Prisoner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import CompleteSimStation.Agent;
import CompleteSimStation.Simulation;

public class Prisoner extends Agent{
private int fitness = 0;
public boolean lastOpponentCooperated=false;
private Cooperate strategy;
private Simulation sim;
private Prisoner prisoner;

private Random random = new Random();


public Prisoner(String name) {
	super(name);
}
public boolean cooperate() {
	return strategy.cooperate();
}
public void update() {
	sim=this.getSim();
	prisoner=(Prisoner)sim.getNeighbor(this);
	boolean thisGuy=cooperate();
	boolean enemy=prisoner.cooperate();
	//both cooperate
	if(thisGuy==true&&enemy==true) {
		addToFitness(3);
		prisoner.addToFitness(3);	
		lastOpponentCooperated=true;
	}
	//both cheat
	else if(thisGuy==false&&enemy==false) {
		addToFitness(1);
		prisoner.addToFitness(1);
		lastOpponentCooperated=false;
	}
	//enemy cheat
	else if(thisGuy==true&&enemy==false) {
		prisoner.addToFitness(5);
		lastOpponentCooperated=false;
	}
	//this prisoner cheats
	else if(thisGuy==false&&enemy==true) {
		addToFitness(5);
		lastOpponentCooperated=true;
	}
	
	//end of game
	setRandomHeading();
	int steps = random.nextInt(10);
	move(steps);
	
	
	
	if(sim.getClock()%100==0) {
		int fitnessCheaters=0;
		int fitnessCooperators=0;
		int fitnessReciproicator=0;
		int fitnessRandom=0;



		for(Agent a: sim.getAgents()) {
			Prisoner prisoners=(Prisoner)a;
			Cooperate strat=prisoners.getCooperate();
			
			int cooperators=0;
			int reciproicator=0;
			int random=0;
			int cheaters=0;
			if(strat instanceof AlwaysCheat) {
				cheaters++;
				fitnessCheaters=(fitnessCheaters+prisoners.getFitness())/cheaters;
				
			}
			else if(strat instanceof AlwaysCooperate) {
				cooperators++;
				fitnessCooperators=(fitnessCooperators+prisoners.getFitness())/cooperators;
				
			}
			else if(strat instanceof LastOpponentCooperate) {
				reciproicator++;
				fitnessReciproicator=(fitnessReciproicator+prisoners.getFitness())/reciproicator;
				
			}
			else if(strat instanceof RandomlyCooperate) {
				random++;
				fitnessRandom=(fitnessRandom+prisoners.getFitness())/random;
			}
			
		}
//System.out.println(cheaters+"/"+cooperators+"/"+reciproicator+"/"+random);
		System.out.println("\n Cooperator's average = " + fitnessCooperators
			+ "\n Cheater's average = " + fitnessCheaters
			+ "\n Reciproicator's average = " + fitnessReciproicator 
			+ "\n Random's average = "+ fitnessRandom);
		}
	}
public void addToFitness(int amt) {
	fitness+=amt;
}
public int getFitness() {
	return fitness;
}
public Cooperate getCooperate() {
	return strategy;
}
public void setCooperate(Cooperate c) {
	strategy=c;
}
}
