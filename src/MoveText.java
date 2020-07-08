
public class MoveText
{
	private static boolean movingText = false;
	private static boolean prevMouseIn = false;

	private static int mx = 0;
	private static int my = 0;

	private static int ID = -1;
	private static int index = -1;

	private static String[] data;

	public static int[] mouseOverTextBox()
	{
		int[] output = new int[]
		{ -1, -1 };

		int[][] td = Layout.getTodoLayout();
		if (Window.mouseX() > td[1][0] && Window.mouseX() < td[1][0] + td[1][2] && !movingText)
		{
			if (Window.mouseY() > td[1][1] && Window.mouseY() < td[1][1] + td[1][3])
			{
				int fontSize = PPmain.getDisplay().getFontSize();
				int size = 0;
				for (int i = 0; i < TodoList.Size(); i++)
				{
					String[] text = TodoList.getText(i);
					if (Window.mouseX() > TodoList.getOffset()[0]
							&& Window.mouseX() < TodoList.getOffset()[0] + Layout.getTodoLayout()[1][2] - 10)
					{
						if (Window.mouseY() > 3 + TodoList.getOffset()[1] - fontSize + size * fontSize
								&& Window.mouseY() < 3 + TodoList.getOffset()[1] - fontSize + size * fontSize
										+ text.length * fontSize)
						{
							output[0] = 1;
							output[1] = i;
							return output;
						}
					}
					size += text.length;
				}
			}
		}

		td = Layout.getWorkingOnLayout();
		if (Window.mouseX() > td[1][0] && Window.mouseX() < td[1][0] + td[1][2] && !movingText)
		{
			if (Window.mouseY() > td[1][1] && Window.mouseY() < td[1][1] + td[1][3])
			{
				data = new String[] {};
				int fontSize = PPmain.getDisplay().getFontSize();
				int size = 0;
				for (int i = 0; i < WorkingOnList.Size(); i++)
				{
					String[] text = WorkingOnList.getText(i);
					if (Window.mouseX() > WorkingOnList.getOffset()[0]
							&& Window.mouseX() < WorkingOnList.getOffset()[0] + Layout.getWorkingOnLayout()[1][2] - 10)
					{
						if (Window.mouseY() > 3 + WorkingOnList.getOffset()[1] - fontSize + size * fontSize
								&& Window.mouseY() < 3 + WorkingOnList.getOffset()[1] - fontSize + size * fontSize
										+ text.length * fontSize)
						{
							output[0] = 2;
							output[1] = i;
							return output;
						}
					}
					size += text.length;
				}
			}
		}

		td = Layout.getFinishedLayout();
		if (Window.mouseX() > td[1][0] && Window.mouseX() < td[1][0] + td[1][2] && !movingText)
		{
			if (Window.mouseY() > td[1][1] && Window.mouseY() < td[1][1] + td[1][3])
			{
				int fontSize = PPmain.getDisplay().getFontSize();
				int size = 0;
				for (int i = 0; i < FinishedList.Size(); i++)
				{
					String[] text = FinishedList.getText(i);
					if (Window.mouseX() > FinishedList.getOffset()[0]
							&& Window.mouseX() < FinishedList.getOffset()[0] + Layout.getFinishedLayout()[1][2] - 10)
					{
						if (Window.mouseY() > 3 + FinishedList.getOffset()[1] - fontSize + size * fontSize
								&& Window.mouseY() < 3 + FinishedList.getOffset()[1] - fontSize + size * fontSize
										+ text.length * fontSize)
						{
							output[0] = 3;
							output[1] = i;
							return output;
						}
					}
					size += text.length;
				}
			}
		}
		return output;
	}

	public static void MoveFromTodo()
	{
		prevMouseIn = Layout.getPrevMouseIn();
		int[][] td = Layout.getTodoLayout();
		if (Window.mouseX() > td[1][0] && Window.mouseX() < td[1][0] + td[1][2] && !movingText)
		{
			if (Window.mouseY() > td[1][1] && Window.mouseY() < td[1][1] + td[1][3])
			{
				if (Window.getMouseButton(0) && !prevMouseIn)
				{
					data = new String[] {};
					int fontSize = PPmain.getDisplay().getFontSize();
					int size = 0;
					for (int i = 0; i < TodoList.Size(); i++)
					{
						String[] text = TodoList.getText(i);
						if (Window.mouseX() > TodoList.getOffset()[0]
								&& Window.mouseX() < TodoList.getOffset()[0] + Layout.getTodoLayout()[1][2] - 10)
						{
							if (Window.mouseY() > 3 + TodoList.getOffset()[1] - fontSize + size * fontSize
									&& Window.mouseY() < 3 + TodoList.getOffset()[1] - fontSize + size * fontSize
											+ text.length * fontSize)
							{
								data = text;
								movingText = true;
								mx = Window.mouseX() - TodoList.getOffset()[0];
								my = Window.mouseY() - (3 + TodoList.getOffset()[1] - fontSize + size * fontSize);
								System.out.println(mx + " " + my);
								ID = 1;
								index = i;
								return;
							}
						}
						size += text.length;
					}
				}
			}
		}
	}

	public static void MoveFromWorkingOn()
	{
		prevMouseIn = Layout.getPrevMouseIn();
		int[][] td = Layout.getWorkingOnLayout();
		if (Window.mouseX() > td[1][0] && Window.mouseX() < td[1][0] + td[1][2] && !movingText)
		{
			if (Window.mouseY() > td[1][1] && Window.mouseY() < td[1][1] + td[1][3])
			{
				if (Window.getMouseButton(0) && !prevMouseIn)
				{
					data = new String[] {};
					int fontSize = PPmain.getDisplay().getFontSize();
					int size = 0;
					for (int i = 0; i < WorkingOnList.Size(); i++)
					{
						String[] text = WorkingOnList.getText(i);
						if (Window.mouseX() > WorkingOnList.getOffset()[0] && Window
								.mouseX() < WorkingOnList.getOffset()[0] + Layout.getWorkingOnLayout()[1][2] - 10)
						{
							if (Window.mouseY() > 3 + WorkingOnList.getOffset()[1] - fontSize + size * fontSize
									&& Window.mouseY() < 3 + WorkingOnList.getOffset()[1] - fontSize + size * fontSize
											+ text.length * fontSize)
							{
								data = text;
								movingText = true;
								mx = Window.mouseX() - WorkingOnList.getOffset()[0];
								my = Window.mouseY() - (3 + WorkingOnList.getOffset()[1] - fontSize + size * fontSize);
								ID = 2;
								index = i;
								return;
							}
						}
						size += text.length;
					}
				}
			}
		}
	}

	public static void MoveFromFinished()
	{
		prevMouseIn = Layout.getPrevMouseIn();
		int[][] td = Layout.getFinishedLayout();
		if (Window.mouseX() > td[1][0] && Window.mouseX() < td[1][0] + td[1][2] && !movingText)
		{
			if (Window.mouseY() > td[1][1] && Window.mouseY() < td[1][1] + td[1][3])
			{
				if (Window.getMouseButton(0) && !prevMouseIn)
				{
					data = new String[] {};
					int fontSize = PPmain.getDisplay().getFontSize();
					int size = 0;
					for (int i = 0; i < FinishedList.Size(); i++)
					{
						String[] text = FinishedList.getText(i);
						if (Window.mouseX() > FinishedList.getOffset()[0] && Window
								.mouseX() < FinishedList.getOffset()[0] + Layout.getFinishedLayout()[1][2] - 10)
						{
							if (Window.mouseY() > 3 + FinishedList.getOffset()[1] - fontSize + size * fontSize
									&& Window.mouseY() < 3 + FinishedList.getOffset()[1] - fontSize + size * fontSize
											+ text.length * fontSize)
							{
								data = text;
								movingText = true;
								mx = Window.mouseX() - FinishedList.getOffset()[0];
								my = Window.mouseY() - (3 + FinishedList.getOffset()[1] - fontSize + size * fontSize);
								ID = 3;
								index = i;
								return;
							}
						}
						size += text.length;
					}
				}
			}
		}
	}

	public static void MovingText()
	{
		if (movingText)
		{
			prevMouseIn = Layout.getPrevMouseIn();
			int[][] td = Layout.getTodoLayout();
			if (Window.mouseX() > td[1][0] && Window.mouseX() < td[1][0] + td[1][2])
			{
				if (Window.mouseY() > td[1][1] && Window.mouseY() < td[1][1] + td[1][3])
				{
					if (prevMouseIn && !Window.getMouseButton(0))
					{
						if (ID == 2)
						{
							WorkingOnList.delete(index);
							WorkingOnList.calculateSizeOfAllText();
						}
						if (ID == 3)
						{
							FinishedList.delete(index);
							FinishedList.calculateSizeOfAllText();
						}

						if (ID != 1)
						{
							TodoList.save(data);
							TodoList.calculateSizeOfAllText();
						}
						data = new String[] {};
						movingText = false;
						ID = -1;
						return;
					}
				}
			}

			td = Layout.getWorkingOnLayout();
			if (Window.mouseX() > td[1][0] && Window.mouseX() < td[1][0] + td[1][2])
			{
				if (Window.mouseY() > td[1][1] && Window.mouseY() < td[1][1] + td[1][3])
				{
					if (prevMouseIn && !Window.getMouseButton(0))
					{
						if (ID == 1)
						{
							TodoList.delete(index);
							TodoList.calculateSizeOfAllText();
						}
						if (ID == 3)
						{
							FinishedList.delete(index);
							FinishedList.calculateSizeOfAllText();
						}

						if (ID != 2)
						{
							WorkingOnList.save(data);
							WorkingOnList.calculateSizeOfAllText();
						}
						data = new String[] {};
						movingText = false;
						ID = -1;
						return;
					}
				}
			}

			td = Layout.getFinishedLayout();
			if (Window.mouseX() > td[1][0] && Window.mouseX() < td[1][0] + td[1][2])
			{
				if (Window.mouseY() > td[1][1] && Window.mouseY() < td[1][1] + td[1][3])
				{
					if (prevMouseIn && !Window.getMouseButton(0))
					{
						if (ID == 1)
						{
							TodoList.delete(index);
							TodoList.calculateSizeOfAllText();
						}
						if (ID == 2)
						{
							WorkingOnList.delete(index);
							WorkingOnList.calculateSizeOfAllText();
						}

						if (ID != 3)
						{
							FinishedList.save(data);
							FinishedList.calculateSizeOfAllText();
						}
						data = new String[] {};
						movingText = false;
						ID = -1;
						return;
					}
				}
			}
			if (prevMouseIn && !Window.getMouseButton(0))
			{
				data = new String[] {};
				movingText = false;
				ID = -1;
			}
		}
	}

	public static int[] getHeldTextLocation()
	{
		int[] location = new int[]
		{ mx, my, ID, index };
		return location;
	}
}

//g.fillRoundRect(TodoList.getOffset()[0], 3 + TodoList.getOffset()[1] - fontSize + size * fontSize,
//		Layout.getTodoLayout()[1][2] - 10, text.length * fontSize, 15, 15);