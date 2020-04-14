package Prisoner;

import java.awt.Frame;

import javax.swing.JOptionPane;

import CompleteSimStation.Agent;
import CompleteSimStation.Simulation;
import CompleteSimStation.simPanel;


public class PrisonerSimulation extends Simulation{
	public void populate()
	{
		Prisoner prisoner;
		for (int i = 0; i<40; i++)
		{
			prisoner = new Prisoner("Prisoner #" + i);
			this.addAgent(prisoner);
			if(i%4==0) {
				prisoner.setCooperate(new AlwaysCheat(prisoner));
			}
			else if(i%4==1) {
				prisoner.setCooperate(new AlwaysCooperate(prisoner));
			}
			else if(i%4==2) {
				prisoner.setCooperate(new RandomlyCooperate(prisoner));
			}
			else if(i%4==3) {
				prisoner.setCooperate(new LastOpponentCooperate(prisoner));
			}
		}
	}
	
	public void getStats()
    {
		int fitnessCheaters=0;
		int fitnessCooperators=0;
		int fitnessReciproicator=0;
		int fitnessRandom=0;
		int cheaters=0;
		int cooperators=0;
		int reciproicator=0;
		int random=0;
		
		for(Agent a: this.getAgents()) {
			Prisoner prisoner=(Prisoner)a;
			Cooperate strat=prisoner.getCooperate();
			if(strat instanceof AlwaysCooperate) {
				cooperators++;
				fitnessCooperators=(fitnessCooperators+prisoner.getFitness())/cooperators;
				
			}
			else if(strat instanceof AlwaysCheat) {
				cheaters++;
				fitnessCheaters=(fitnessCheaters+prisoner.getFitness())/cheaters;
				
			}
			else if(strat instanceof LastOpponentCooperate) {
				reciproicator++;
				fitnessReciproicator=(fitnessReciproicator+prisoner.getFitness())/reciproicator;
				
			}
			else if(strat instanceof RandomlyCooperate) {
				random++;
				fitnessRandom=(fitnessRandom+prisoner.getFitness())/random;
			}
			
		}
	  	String stats = "#agents = " + getAgents().size() 
	  			+ "\n clock = " + getClock()
	  			+ "\n Cooperator's average = " + fitnessCooperators
	  			+ "\n Cheater's average = " + fitnessCheaters
	  			+ "\n Reciproicator's average = " + fitnessReciproicator 
	  			+ "\n Random's average = "+ fitnessRandom;
    	JOptionPane.showMessageDialog(
                new Frame(),
                stats);
    }
	
	public static void main(String[] args) {
		PrisonerFactory factory = new PrisonerFactory();
		simPanel panel = new simPanel(factory);
		panel.display();
	}

}
