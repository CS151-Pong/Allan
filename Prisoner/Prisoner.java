package Prisoner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import CompleteSimStation.Agent;
import CompleteSimStation.Simulation;

public class Prisoner extends Agent{
private int fitness = 0;
public boolean lastOpponentCooperated=false;;
private Cooperate cooperate;
private Simulation sim;
private Prisoner prisoner;

private Random random = new Random();


public Prisoner(String name) {
	super(name);
}
public boolean cooperate() {
	return cooperate.cooperate();
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
	if(thisGuy==false&&enemy==false) {
		addToFitness(1);
		prisoner.addToFitness(1);
		lastOpponentCooperated=false;
	}
	//enemy cheat
	if(thisGuy==true&&enemy==false) {
		prisoner.addToFitness(5);
		lastOpponentCooperated=false;
	}
	//this prisoner cheats
	if(thisGuy==false&&enemy==true) {
		addToFitness(5);
		lastOpponentCooperated=true;
	}
	
	//end of game
	setRandomHeading();
	int steps = random.nextInt(9)+1;
	move(steps);
	
	
	
	
	if(sim.getClock()%100==0) {
		int fitnessCheaters=0;
		int fitnessCooperators=0;
		int fitnessReciproicator=0;
		int fitnessRandom=0;

		for(Agent a: sim.getAgents()) {
			Prisoner prisoner=(Prisoner)a;
			Cooperate strat=prisoner.getCooperate();
			int cheaters=0;
			int cooperators=0;
			int reciproicator=0;
			int random=0;
			if(strat instanceof AlwaysCooperate) {
				cooperators++;
				fitnessCooperators=(fitnessCooperators+prisoner.getFitness())/cooperators;
				
			}
			if(strat instanceof AlwaysCheat) {
				cheaters++;
				fitnessCheaters=(fitnessCheaters+prisoner.getFitness())/cheaters;
				
			}
			if(strat instanceof lastOpponentCooperate) {
				reciproicator++;
				fitnessReciproicator=(fitnessReciproicator+prisoner.getFitness())/reciproicator;
				
			}
			if(strat instanceof RandomlyCooperate) {
				random++;
				fitnessRandom=(fitnessRandom+prisoner.getFitness())/random;
			}
			
		}
		System.out.println("\n Cooperator's average = " + fitnessCooperators
			+ "\n Cheater's average = " + fitnessCheaters
			+ "\n Reciproicator's average = " + fitnessReciproicator 
			+ "\n Random's average = "+ fitnessRandom);
		}
	}
public void addToFitness(int amt) {
	fitness=fitness+amt;
}
public int getFitness() {
	return fitness;
}
public Cooperate getCooperate() {
	return cooperate;
}
public void setCooperate(Cooperate c) {
	cooperate=c;
}
}
