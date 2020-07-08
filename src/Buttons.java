public class Buttons
{
	private static int[][] params;
	private static String[] names;

	Buttons()
	{
		params = new int[][]
		{
				// x, y, width, height, state
				// 0 = all states
				{ 5, 1, 110, 27, -1 },
				{ 260, 1, 50, 27, -1 },
				{ 564, 73, 110, 27, 1 },
				{ 120, 1, 65, 27, -1 },
				{ 190, 1, 65, 27, -1 },
				{ 600, 285, 140, 27, 3 },
				{ 450, 325, 85, 27, 3 },
				{ 0, 0, 85, 27, 6 },
				{ 0, 0, 85, 27, 6 } };

		names = new String[]
		{ "settings", "Add", "Close", "Save", "Load", "Create File", "Cancel", "Edit", "Delete" };
	}

	public static int isMouseOverButton()
	{
		for (int i = 0; i < params.length; i++)
		{
			if (Window.mouseX() > params[i][0] && Window.mouseX() < params[i][0] + params[i][2])
			{
				if (Window.mouseY() > params[i][1] && Window.mouseY() < params[i][1] + params[i][3])
				{
					// 7,8
					return i;
				}
			}
		}
		return -1;
	}

	public static int isMouseOverLoadedButton()
	{
		int[][] data = Load.getLoadedButtons();
		int[] td = Layout.getLoadDropDown();

		for (int i = 0; i < data.length; i++)
		{
			if (Window.mouseX() > data[i][0] && Window.mouseX() < data[i][0] + data[i][2])
			{
				if (Window.mouseY() > data[i][1] && Window.mouseY() < data[i][1] + data[i][3])
				{
					int[] button = data[i];

					if (button[1] > td[1] && button[1] + button[3] < td[1] + td[3])
					{
						return i;
					}
				}
			}
		}

		return -1;
	}

	public static void setLocation(int x, int y)
	{
		params[7][0] = x + 5;
		params[7][1] = y + 5;

		params[8][0] = x + 5;
		params[8][1] = y + params[7][3] + 8;
	}

	public static int getLength()
	{
		return params.length;
	}

	public static int[] getLocation(int i)
	{
		return params[i];
	}

	public static String getName(int i)
	{
		return names[i];
	}
}
