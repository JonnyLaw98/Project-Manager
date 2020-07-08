
public class TextTyper
{
	private static char s;

	private static String[] text;
	private static int index;

	private static long TTime;
	private static boolean showBlinker;

	private static int textLength;

	private static long timeBetweenBlinks;

	TextTyper()
	{
		text = new String[]
		{ "" };
		index = 0;

		timeBetweenBlinks = 250;
		TTime = System.currentTimeMillis();

		showBlinker = false;
		textLength = 20;

		s = '\u0000';
	}

	public static void setTextLength(int L)
	{
		textLength = L;
	}

	public static int getTextLength()
	{
		return textLength;
	}

	public static String[] getText()
	{
		return text;
	}

	public static void curentlyTyping()
	{
		if (PPmain.getDisplay().getFontSize() >= 18)
		{
			textLength = 28;
		}
		if (PPmain.getDisplay().getFontSize() >= 22)
		{
			textLength = 24;
		}
		if (PPmain.getDisplay().getFontSize() >= 26)
		{
			textLength = 20;
		}
		// PPmain.getDisplay().getFontSize() / 2 * TextTyper.getTextLength() + 70

		if (PPmain.getState() == 1 || PPmain.getState() == 7)
		{
			if (TTime <= System.currentTimeMillis())
			{
				TTime = System.currentTimeMillis() + timeBetweenBlinks;
				showBlinker = !showBlinker;

				if (showBlinker)
				{
					text[index] += "|";
				} else
				{
					if (text[index].length() > 0)
					{
						text[index] = text[index].substring(0, text[index].length() - 1);
					}
				}
			}
		} else
		{
			showBlinker = false;
		}

		if (Window.getKeyCode() == 0 || Window.getKeyCode() == 16)
		{
			s = '\u0000';
			Window.resetKey();
			Window.resetKeyCode();
			return;
		}

		s = Window.getKey();
		if (s == '\u0008')
		{
			try
			{
				if (showBlinker)
				{
					text[index] = text[index].substring(0, text[index].length() - 2);
					text[index] += "|";
				} else
				{
					text[index] = text[index].substring(0, text[index].length() - 1);
				}
			} catch (Exception e)
			{
				if (index != 0)
				{
					removeTextLine();
				}
			}
			s = '\u0000';
			Window.resetKey();
		} else if (s != '\u0000')
		{
			if (showBlinker)
			{
				if (text[index].length() > 0)
				{
					text[index] = text[index].substring(0, text[index].length() - 1);
				}
				text[index] += s;
				text[index] += "|";
			} else
			{
				text[index] += s;
			}
			s = '\u0000';
			Window.resetKey();
		}
		if (showBlinker)
		{
			if (text[index].length() >= textLength + 1)
			{
				newTextLine();
			}
		} else
		{
			if (text[index].length() >= textLength)
			{
				newTextLine();
			}
		}
	}

	private static void newTextLine()
	{
		int TI = 1;
		String nextLine = "";
		while (true)
		{
			String checkForSpace;
			try
			{
				if (showBlinker)
				{
					checkForSpace = text[index].substring(text[index].length() - TI - 1, text[index].length() - TI);
				} else
				{
					checkForSpace = text[index].substring(text[index].length() - TI, text[index].length() - TI + 1);
				}
			} catch (Exception e)
			{
				if (showBlinker)
				{
					text[index] = text[index].substring(0, text[index].length() - 1);
				} else
				{
					text[index] = text[index].substring(0, text[index].length());
				}
				break;
			}
			if (checkForSpace.equalsIgnoreCase(" "))
			{
				if (showBlinker)
				{
					nextLine = text[index].substring(text[index].length() - TI, text[index].length());
				} else
				{
					nextLine = text[index].substring(text[index].length() - TI + 1, text[index].length());
				}
				text[index] = text[index].substring(0, text[index].length() - TI);
				break;
			}
			TI++;
		}

		String[] temp = new String[text.length];
		for (int i = 0; i < text.length; i++)
		{
			temp[i] = text[i];
		}
		text = new String[text.length + 1];
		for (int i = 0; i < temp.length; i++)
		{
			text[i] = temp[i];
		}
		index++;
		text[index] = nextLine;
	}

	public static void loadText(String[] s)
	{
		text = s;
		index = s.length - 1;
	}

	private static void removeTextLine()
	{
		String[] temp = new String[text.length - 1];
		for (int i = 0; i < temp.length; i++)
		{
			temp[i] = text[i];
		}
		text = new String[text.length - 1];
		for (int i = 0; i < temp.length; i++)
		{
			text[i] = temp[i];
		}
		index--;
	}

	public static void save()
	{
		Window.resetKeyCode();
		if (showBlinker)
		{
			text[index] = text[index].substring(0, text[index].length() - 1);
		}
		TodoList.save(text);
		reset();
	}

	public static void save(int ID)
	{
		Window.resetKeyCode();
		if (showBlinker)
		{
			text[index] = text[index].substring(0, text[index].length() - 1);
		}
		switch (ID)
		{
		case 1:
			TodoList.save(text);
			break;
		case 2:
			WorkingOnList.save(text);
			break;
		case 3:
			FinishedList.save(text);
			break;
		}
		reset();
	}

	public static void FileNameIn()
	{
		String[] temp = new String[1];
		if (text.length > 1)
		{
			temp[0] = text[0];
			text = new String[1];
			text[0] = temp[0];
			index = 0;
		}
	}

	public static String saveFileName()
	{
		Window.resetKeyCode();
		String output = text[0];

		output = text[0].substring(0, text[0].length());

		return output;
	}

	public static void reset()
	{
		text = new String[]
		{ "" };
		index = 0;
		showBlinker = false;
		Window.resetKey();
		Window.resetKeyCode();
		s = '\u0000';
	}
}
