package Prisoner;

import java.util.Random;

public class RandomlyCooperate extends Cooperate {
	public RandomlyCooperate(Prisoner p) {
		super(p);
		// TODO Auto-generated constructor stub
	}
	Random rand=new Random();
public boolean cooperate() {
	int random=rand.nextInt(2);
	if(random==1) {
		return true;
	}else {
	return false;
	}

}
}
