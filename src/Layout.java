public class Layout
{
	private static int[][] TodoBoxes;
	private static int[][] WorkingOnBoxes;
	private static int[][] FinishedBoxes;

	private static int[] newText;
	private static int[] SettingsDropDown;
	private static int[] loadDropDown;

	private static int[][] newSave;

	private static int[] rightClickMenu;

	private static String[] headers;

	private static boolean prevMouseIn = false;
	private static boolean prevMouseInRight = false;

	private static String FileName = "Untitled";

	Layout()
	{

		TodoBoxes = new int[][]
		{
				{ 10, 35, 330, 30, 0 },
				{ 10, 65, 330, Window.height() - 100, 1 } };

		TodoList.setOffset(TodoBoxes[1][0] + 5, TodoBoxes[1][1] + PPmain.getDisplay().getFontSize());

		WorkingOnBoxes = new int[][]
		{
				{ 350, 35, 330, 30, 0 },
				{ 350, 65, 330, Window.height() - 100, 1 } };

		WorkingOnList.setOffset(WorkingOnBoxes[1][0] + 5, WorkingOnBoxes[1][1] + PPmain.getDisplay().getFontSize());

		FinishedBoxes = new int[][]
		{
				{ 690, 35, 330, 30, 0 },
				{ 690, 65, 330, Window.height() - 100, 1 } };

		FinishedList.setOffset(FinishedBoxes[1][0] + 5, FinishedBoxes[1][1] + PPmain.getDisplay().getFontSize());

		newText = new int[]
		{ 355, 100, 320, 400 };

		SettingsDropDown = new int[]
		{ 5, 31, 200, 300 };

		newSave = new int[][]
		{
				{ 250, 275, 500, 85 },
				{ 260, 285, 300, 27 } };

		loadDropDown = new int[]
		{ 190, 31, 200, 300 };

		rightClickMenu = new int[]
		{ 0, 0, 95, 70 };

		headers = new String[]
		{ "To Do", "Working On", "Finished" };

	}

	public static int[][] getTodoLayout()
	{
		return TodoBoxes;
	}

	public static int[][] getWorkingOnLayout()
	{
		return WorkingOnBoxes;
	}

	public static int[][] getFinishedLayout()
	{
		return FinishedBoxes;
	}

	public static int[] getnewTextBox()
	{
		return newText;
	}

	public static int[] getSettingsDropDown()
	{
		return SettingsDropDown;
	}

	public static int[] getLoadDropDown()
	{
		return loadDropDown;
	}

	public static int[] getRightClickmenu()
	{
		return rightClickMenu;
	}

	private static void setRightClickMenuLocation(int x, int y)
	{
		rightClickMenu[0] = x;
		rightClickMenu[1] = y;
	}

	public static int[][] getNewSaveScreen()
	{
		return newSave;
	}

	public static String[] getHeaders()
	{
		return headers;
	}

	public static String getName()
	{
		return FileName;
	}

	public static void setName(String name)
	{
		FileName = name;
	}

	public static void resize()
	{
		TodoBoxes[1][3] = Window.height() - 100;
		WorkingOnBoxes[1][3] = Window.height() - 100;
		FinishedBoxes[1][3] = Window.height() - 100;
	}

	public static void MouseIn()
	{
		prevMouseIn = Window.getMouseButton(0);
		prevMouseInRight = Window.getMouseButton(1);
	}

	public static boolean getPrevMouseIn()
	{
		return prevMouseIn;
	}

	private static void delete(int ID, int index, boolean reset)
	{
		System.out.println(ID + " " + index);
		switch (ID)
		{
		case 1:
			TodoList.delete(index);
			break;
		case 2:
			WorkingOnList.delete(index);
			break;
		case 3:
			FinishedList.delete(index);
			break;
		}
		if (reset)
		{
			ID = -1;
			index = -1;
		}
	}

	private static String[] getData(int ID, int index)
	{
		switch (ID)
		{
		case 1:
			return TodoList.getText(index);
		case 2:
			return WorkingOnList.getText(index);
		default:
			return FinishedList.getText(index);
		}

	}

	private static int ID = -1;
	private static int index = -1;

	public static int stateSwitcher(int state)
	{
		int newState = state;
		switch (state)
		{
		case 0:
			switch (Buttons.isMouseOverButton())
			{
			case 0: // settings button
				if (Window.getMouseButton(0) && !prevMouseIn)
				{
					newState = 2;
				}
				break;
			case 1: // new button
				if (Window.getMouseButton(0) && !prevMouseIn)
				{
					newState = 1;
				}
				break;
			case 3: // Save button
				if (Window.getMouseButton(0) && !prevMouseIn)
				{
					newState = Save.checkIfNewFile();
				}
				break;
			case 4: // Load button
				if (Window.getMouseButton(0) && !prevMouseIn)
				{
					Load.listFiles();
					newState = 5;
				}
				break;
			}

			MoveText.MoveFromTodo();
			MoveText.MoveFromWorkingOn();
			MoveText.MoveFromFinished();
			MoveText.MovingText();

			Scroll.scrollTodo();
			Scroll.scrollWorkingOn();
			Scroll.scrollFinished();

			if (MoveText.mouseOverTextBox()[0] != -1 && MoveText.mouseOverTextBox()[1] != -1)
			{
				if (Window.getMouseButton(1) && !prevMouseInRight)
				{
					setRightClickMenuLocation(Window.mouseX(), Window.mouseY());
					Buttons.setLocation(Window.mouseX(), Window.mouseY());
					ID = MoveText.mouseOverTextBox()[0];
					index = MoveText.mouseOverTextBox()[1];
					newState = 6;
				}
			}
			break;
		case 1: // add new todo
			if (Buttons.isMouseOverButton() == 2)
			{
				if (Window.getMouseButton(0) && !prevMouseIn)
				{
					newState = 0;
				}
			}

			if (Window.getKeyCode() == 10)
			{
				TextTyper.save();
				TodoList.calculateSizeOfAllText();
				newState = 0;
			}

			TextTyper.curentlyTyping();
			break;
		case 2: // settings tab
			if (Buttons.isMouseOverButton() == 0 && !prevMouseIn)
			{
				if (Window.getMouseButton(0))
				{
					newState = 0;
				}
			}
			switch (Buttons.isMouseOverButton())
			{

			}
			break;
		case 3: // name new file to be saved
			if (Window.getMouseButton(0) && !prevMouseIn && Buttons.isMouseOverButton() == 6)
			{
				newState = 0;
				TextTyper.reset();
			}

			if (Window.getMouseButton(0) && !prevMouseIn && Buttons.isMouseOverButton() == 5)
			{
				Save.setFileName();
				newState = 4;
			} else
			{
				Save.newFileName();
			}

			break;
		case 4: // save data
			Save.saveData();
			newState = 0;
			break;
		case 5: // list of files to load tab
			Load.createButtons();
			Scroll.scrollLoadButtons();
			if (Window.getMouseButton(0) && !prevMouseIn && Buttons.isMouseOverLoadedButton() != -1)
			{
				Load.loadFile(Load.getFileNames()[Buttons.isMouseOverLoadedButton()]);
				Layout.setName(Load.getFileNames()[Buttons.isMouseOverLoadedButton()]);
				newState = 0;
			}
			if (Window.getMouseButton(0) && !prevMouseIn && Buttons.isMouseOverButton() == 4)
			{
				newState = 0;
			}
			break;
		case 6: // right click menu
			if (Window.getMouseButton(0) && !prevMouseIn && Buttons.isMouseOverButton() == 7)
			{
				TextTyper.loadText(getData(ID, index));
				delete(ID, index, false);
				newState = 7;
			}
			if (Window.getMouseButton(0) && !prevMouseIn && Buttons.isMouseOverButton() == 8)
			{
				delete(ID, index, true);
				switch (ID)
				{
				case 1:
					TodoList.calculateSizeOfAllText();
					break;
				case 2:
					WorkingOnList.calculateSizeOfAllText();
					break;
				case 3:
					FinishedList.calculateSizeOfAllText();
					break;
				}
				newState = 0;
			}
			break;
		case 7: // right click menu
			if (Window.getKeyCode() == 10)
			{
				TextTyper.save(ID);
				switch (ID)
				{
				case 1:
					TodoList.calculateSizeOfAllText();
					break;
				case 2:
					WorkingOnList.calculateSizeOfAllText();
					break;
				case 3:
					FinishedList.calculateSizeOfAllText();
					break;
				}
				ID = -1;
				index = -1;
				newState = 0;
			}
			TextTyper.curentlyTyping();
			break;
		}
		return newState;
	}
}
