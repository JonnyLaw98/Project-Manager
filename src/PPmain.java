
public class PPmain
{
	private static Display d;
	private static int state;

	public static void main(String[] args)
	{
		d = new Display();
		state = 0;
		new Window("Progress Manager", false, 1050, 600);
		Window.getWindow().add(d);
		Window.showWindow();
		new TodoList();
		new WorkingOnList();
		new FinishedList();
		new TextTyper();
		new Buttons();

		d.setTextFontSize(18);
		new Layout();

		Window.getWindow().setResizable(false);

		Colors.defaultColors();

		while (true)
		{
			run();
			try
			{
				Thread.sleep(50);
			} catch (Exception e)
			{

			}
		}
	}

	public static Display getDisplay()
	{
		return d;
	}

	public static int getState()
	{
		return state;
	}

	public static void setState(int s)
	{
		state = s;
	}

	private static void run()
	{
		Layout.resize();
		d.repaint();
		state = Layout.stateSwitcher(state);
		Layout.MouseIn();

		Window.resetKeyCode();
		Window.resetKey();
		Window.setMouseWheelDown(false);
		Window.setMouseWheelUp(false);
		// TODO add scroling to load buttons
		// TODO add way to navigate around currently typing text
	}
}
