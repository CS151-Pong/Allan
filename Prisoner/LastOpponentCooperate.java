package Prisoner;

public class LastOpponentCooperate extends Cooperate{
public LastOpponentCooperate(Prisoner p) {
		super(p);
	}

public boolean cooperate() {
	return prisoner.lastOpponentCooperated;
}
}
