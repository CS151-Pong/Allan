package MVC;

abstract public class Command {
	protected Model myModel;

	public Command(Model m) {
		super();
		myModel = m;
	}

	public abstract void execute();

}
