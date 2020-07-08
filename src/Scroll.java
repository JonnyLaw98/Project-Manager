
public class Scroll
{
	private static int scrollSpeed = 15;

	private static boolean TodoScrollUp = true;
	private static boolean TodoScrollDown = true;

	private static int TodoDefaultLocation = TodoList.getOffset()[1];

	public static void scrollTodo()
	{
		int[][] td = Layout.getTodoLayout();

		boolean canScroll = false;

		if (TodoList.getSizeOfAllText() > 27)
		{
			canScroll = true;
		} else
		{
			TodoList.setOffset(TodoList.getOffset()[0], TodoDefaultLocation);
		}
		int size = 0;
		int textLength = 0;
		try
		{
			textLength = TodoList.getText(TodoList.Size() - 1).length;
		} catch (Exception e)
		{
		}

		try
		{
			size = TodoList.getSizeOfAllText() - textLength;
		} catch (Exception e)
		{
		}
		int fontSize = PPmain.getDisplay().getFontSize();
		if ((3 + TodoList.getOffset()[1] - fontSize + size * fontSize)
				+ (textLength * fontSize) < Layout.getTodoLayout()[1][1] + Layout.getTodoLayout()[1][3])
		{
			TodoScrollDown = false;
		} else
		{
			TodoScrollDown = true;
		}

		if ((3 + TodoList.getOffset()[1] - fontSize) > Layout.getTodoLayout()[1][1])
		{
			TodoScrollUp = false;
		} else
		{
			TodoScrollUp = true;
		}

		if (Window.mouseX() > td[1][0] && Window.mouseX() < td[1][0] + td[1][2])
		{
			if (Window.mouseY() > td[1][1] && Window.mouseY() < td[1][1] + td[1][3])
			{
				if (Window.isMouseWheelUp() && canScroll && TodoScrollDown)
				{
					TodoList.setOffset(TodoList.getOffset()[0], TodoList.getOffset()[1] - scrollSpeed);
					Window.setMouseWheelUp(false);
				} else if (Window.isMouseWheelDown() && canScroll && TodoScrollUp)
				{
					TodoList.setOffset(TodoList.getOffset()[0], TodoList.getOffset()[1] + scrollSpeed);
					Window.setMouseWheelDown(false);
				}
			}
		}
	}

	private static boolean WorkingOnScrollUp = true;
	private static boolean WorkingOnScrollDown = true;

	public static void scrollWorkingOn()
	{
		int[][] td = Layout.getWorkingOnLayout();

		boolean canScroll = false;

		if (WorkingOnList.getSizeOfAllText() > 27)
		{
			canScroll = true;
		} else
		{
			WorkingOnList.setOffset(WorkingOnList.getOffset()[0], TodoDefaultLocation);
		}
		int size = 0;
		int textLength = 0;
		try
		{
			textLength = WorkingOnList.getText(WorkingOnList.Size() - 1).length;
		} catch (Exception e)
		{
		}

		try
		{
			size = WorkingOnList.getSizeOfAllText() - textLength;
		} catch (Exception e)
		{
		}
		int fontSize = PPmain.getDisplay().getFontSize();
		if ((3 + WorkingOnList.getOffset()[1] - fontSize + size * fontSize)
				+ (textLength * fontSize) < Layout.getWorkingOnLayout()[1][1] + Layout.getWorkingOnLayout()[1][3])
		{
			WorkingOnScrollDown = false;
		} else
		{
			WorkingOnScrollDown = true;
		}

		if ((3 + WorkingOnList.getOffset()[1] - fontSize) > Layout.getWorkingOnLayout()[1][1])
		{
			WorkingOnScrollUp = false;
		} else
		{
			WorkingOnScrollUp = true;
		}

		if (Window.mouseX() > td[1][0] && Window.mouseX() < td[1][0] + td[1][2])
		{
			if (Window.mouseY() > td[1][1] && Window.mouseY() < td[1][1] + td[1][3])
			{
				if (Window.isMouseWheelUp() && canScroll && WorkingOnScrollDown)
				{
					WorkingOnList.setOffset(WorkingOnList.getOffset()[0], WorkingOnList.getOffset()[1] - scrollSpeed);
					Window.setMouseWheelUp(false);
				} else if (Window.isMouseWheelDown() && canScroll && WorkingOnScrollUp)
				{
					WorkingOnList.setOffset(WorkingOnList.getOffset()[0], WorkingOnList.getOffset()[1] + scrollSpeed);
					Window.setMouseWheelDown(false);
				}
			}
		}
	}

	private static boolean FinishedScrollUp = true;
	private static boolean FinishedScrollDown = true;

	public static void scrollFinished()
	{
		int[][] td = Layout.getFinishedLayout();

		boolean canScroll = false;

		if (FinishedList.getSizeOfAllText() > 27)
		{
			canScroll = true;
		} else
		{
			FinishedList.setOffset(FinishedList.getOffset()[0], TodoDefaultLocation);
		}
		int size = 0;
		int textLength = 0;
		try
		{
			textLength = FinishedList.getText(FinishedList.Size() - 1).length;
		} catch (Exception e)
		{
		}

		try
		{
			size = FinishedList.getSizeOfAllText() - textLength;
		} catch (Exception e)
		{
		}
		int fontSize = PPmain.getDisplay().getFontSize();
		if ((3 + FinishedList.getOffset()[1] - fontSize + size * fontSize)
				+ (textLength * fontSize) < Layout.getFinishedLayout()[1][1] + Layout.getFinishedLayout()[1][3])
		{
			FinishedScrollDown = false;
		} else
		{
			FinishedScrollDown = true;
		}

		if ((3 + FinishedList.getOffset()[1] - fontSize) > Layout.getFinishedLayout()[1][1])
		{
			FinishedScrollUp = false;
		} else
		{
			FinishedScrollUp = true;
		}

		if (Window.mouseX() > td[1][0] && Window.mouseX() < td[1][0] + td[1][2])
		{
			if (Window.mouseY() > td[1][1] && Window.mouseY() < td[1][1] + td[1][3])
			{
				if (Window.isMouseWheelUp() && canScroll && FinishedScrollDown)
				{
					FinishedList.setOffset(FinishedList.getOffset()[0], FinishedList.getOffset()[1] - scrollSpeed);
					Window.setMouseWheelUp(false);
				} else if (Window.isMouseWheelDown() && canScroll && FinishedScrollUp)
				{
					FinishedList.setOffset(FinishedList.getOffset()[0], FinishedList.getOffset()[1] + scrollSpeed);
					Window.setMouseWheelDown(false);
				}
			}
		}
	}

	private static boolean LoadButtonsScrollUp = true;
	private static boolean LoadButtonsScrollDown = true;

	public static void scrollLoadButtons()
	{
		int[] td = Layout.getLoadDropDown();
		// can hold 10 without scrolling
		if (Load.getFileNames().length <= 10)
		{
			Load.setOffset(0);
			return;
		}

		if (Load.getLoadedButtons()[0][1] > td[1])
		{
			Load.setOffset(0);
			LoadButtonsScrollUp = false;
		} else
		{
			LoadButtonsScrollUp = true;
		}

		if (Load.getLoadedButtons()[Load.getLoadedButtons().length - 1][1]
				+ Load.getLoadedButtons()[Load.getLoadedButtons().length - 1][3] < td[1] + td[3])
		{
			LoadButtonsScrollDown = false;
		} else
		{
			LoadButtonsScrollDown = true;
		}

		if (Window.mouseX() > td[0] && Window.mouseX() < td[0] + td[2])
		{
			if (Window.mouseY() > td[1] && Window.mouseY() < td[1] + td[3])
			{
				if (Window.isMouseWheelUp() && LoadButtonsScrollDown)
				{
					Load.setOffset(Load.getOffset() - scrollSpeed);
					Window.setMouseWheelUp(false);
				} else if (Window.isMouseWheelDown() && LoadButtonsScrollUp)
				{
					Load.setOffset(Load.getOffset() + scrollSpeed);
					Window.setMouseWheelDown(false);
				}
			}
		}
	}
}
