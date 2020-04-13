package Prisoner;

public class lastOpponentCooperate extends Cooperate{
public lastOpponentCooperate(Prisoner p) {
		super(p);
	}

public boolean cooperate() {
	return prisoner.lastOpponentCooperated;
}
}
