package MVC;

public interface AppFactory {
	public Model makeModel();

	public String[] getEditCommands();

	public Command makeEditCommand(Model m, String s);

	public String getTitle();

	public String[] getHelp();

	public String getAbout();
}
