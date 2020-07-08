import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Display extends JPanel
{
	private Font font;
	private Color background = new Color(255, 255, 255);
	private int fontSize;
	private int TextFontSize;
	private Color lightGray;

	Display()
	{
		fontSize = 40;
		font = new Font("Courier", Font.BOLD, fontSize);
	}

	public void setFontSize(int s)
	{
		fontSize = s;
		font = new Font("Courier", Font.BOLD, fontSize);
	}

	public void setTextFontSize(int s)
	{
		TextFontSize = s;
		font = new Font("Courier", Font.BOLD, s);
	}

	public int getFontSize()
	{
		return TextFontSize;
	}

	public void paintComponent(Graphics g)
	{
		int state = PPmain.getState();

		super.paintComponent(g);
		this.setBackground(Colors.getColor(0));

		setFontSize(18);
		g.setFont(font);

		drawRect(g);

		setFontSize(TextFontSize);
		g.setFont(font);

		int[] MT = MoveText.getHeldTextLocation();

		showTodoData(g, MT);
		showWorkingOnData(g, MT);
		showFinishedData(g, MT);

		showMovingFromTodo(g, MT);
		showMovingFromWorkingOn(g, MT);
		showMovingFromFinished(g, MT);

		showPopUpWindows(g, state);

		setFontSize(18);
		g.setFont(font);

		showTopBar(g);

		showButtons(g, state);

		showLoadedButtons(g, state);
	}

	private void drawRect(Graphics g)
	{
		String[] headers = Layout.getHeaders();
		for (int i = 0; i < Layout.getTodoLayout().length; i++)
		{
			int[] rect = Layout.getTodoLayout()[i];
			switch (rect[4])
			{
			case 0:
				g.setColor(Colors.getColor(7));
				break;
			case 1:
				g.setColor(Colors.getColor(8));
				break;
			}
			g.fillRect(rect[0], rect[1], rect[2], rect[3]);
			if (i == 0)
			{
				g.setColor(Color.BLACK);
				g.drawString(headers[0], rect[0] + rect[2] / 2 - headers[0].length() * fontSize / 4,
						rect[1] + fontSize);
			}
		}

		for (int i = 0; i < Layout.getWorkingOnLayout().length; i++)
		{
			int[] rect = Layout.getWorkingOnLayout()[i];
			switch (rect[4])
			{
			case 0:
				g.setColor(Colors.getColor(7));
				break;
			case 1:
				g.setColor(Colors.getColor(8));
				break;
			}
			g.fillRect(rect[0], rect[1], rect[2], rect[3]);
			if (i == 0)
			{
				g.setColor(Color.BLACK);
				g.drawString(headers[1], rect[0] + rect[2] / 2 - headers[1].length() * fontSize / 4,
						rect[1] + fontSize);
			}
		}

		for (int i = 0; i < Layout.getFinishedLayout().length; i++)
		{
			int[] rect = Layout.getFinishedLayout()[i];
			switch (rect[4])
			{
			case 0:
				g.setColor(Colors.getColor(7));
				break;
			case 1:
				g.setColor(Colors.getColor(8));
				break;
			}
			g.fillRect(rect[0], rect[1], rect[2], rect[3]);
			if (i == 0)
			{
				g.setColor(Color.BLACK);
				g.drawString(headers[2], rect[0] + rect[2] / 2 - headers[2].length() * fontSize / 4,
						rect[1] + fontSize);
			}
		}
	}

	private void showTodoData(Graphics g, int[] MT)
	{
		if (TodoList.Size() > 0)
		{
			int size = 0;
			for (int i = 0; i < TodoList.Size(); i++)
			{
				boolean show = true;
				boolean regRectTop = false;
				boolean regRectBottom = false;
				String[] text = TodoList.getText(i);

				int[] location = new int[]
				{ TodoList.getOffset()[0], 3 + TodoList.getOffset()[1] - fontSize + size * fontSize,
						Layout.getTodoLayout()[1][2] - 10, text.length * fontSize };

				if (location[1] + location[3] < Layout.getTodoLayout()[1][1])
				{
					show = false;
				} else if (location[1] < Layout.getTodoLayout()[1][1])
				{
					int offset = Layout.getTodoLayout()[1][1] - location[1];
					location[1] = Layout.getTodoLayout()[1][1];
					location[3] = location[3] - offset;
					regRectTop = true;
				} else if (location[1] > Layout.getTodoLayout()[1][1] + Layout.getTodoLayout()[1][3])
				{
					show = false;
				} else if (location[1] + location[3] > Layout.getTodoLayout()[1][1] + Layout.getTodoLayout()[1][3])
				{
					int offset = (location[1] + location[3])
							- (Layout.getTodoLayout()[1][1] + Layout.getTodoLayout()[1][3]);
					location[3] = location[3] - offset;
					regRectBottom = true;
				}

				if (MT[2] == 1 && MT[3] == i)
				{

				} else if (show)
				{
					g.setColor(Colors.getColor(9));
					g.fillRoundRect(location[0], location[1], location[2], location[3], 15, 15);
					g.setColor(Color.BLACK);
					g.drawRoundRect(location[0], location[1], location[2], location[3], 15, 15);

					if (regRectBottom)
					{
						g.setColor(Colors.getColor(9));
						g.fillRect(location[0], location[1] + location[3] / 2, location[2], location[3] / 2);
					} else if (regRectTop)
					{
						g.setColor(Colors.getColor(9));
						g.fillRect(location[0], location[1], location[2], location[3] / 2);
					}

					g.setColor(Colors.getColor(1));

					for (int x = 0; x < text.length; x++)
					{
						if (x * fontSize + size * fontSize + TodoList.getOffset()[1] > Layout.getTodoLayout()[1][1]
								+ fontSize / 2
								&& x * fontSize + size * fontSize
										+ TodoList.getOffset()[1] < Layout.getTodoLayout()[1][1]
												+ Layout.getTodoLayout()[1][3] + fontSize / 4)
						{
							g.drawString(text[x], TodoList.getOffset()[0],
									x * fontSize + size * fontSize + TodoList.getOffset()[1]);
						}
					}
				}
				size += text.length;
			}
		}
	}

	private void showWorkingOnData(Graphics g, int[] MT)
	{
		if (WorkingOnList.Size() > 0)
		{
			int size = 0;
			for (int i = 0; i < WorkingOnList.Size(); i++)
			{
				boolean show = true;
				boolean regRectTop = false;
				boolean regRectBottom = false;
				String[] text = WorkingOnList.getText(i);

				int[] location = new int[]
				{ WorkingOnList.getOffset()[0], 3 + WorkingOnList.getOffset()[1] - fontSize + size * fontSize,
						Layout.getWorkingOnLayout()[1][2] - 10, text.length * fontSize };

				if (location[1] + location[3] < Layout.getWorkingOnLayout()[1][1])
				{
					show = false;
				} else if (location[1] < Layout.getWorkingOnLayout()[1][1])
				{
					int offset = Layout.getWorkingOnLayout()[1][1] - location[1];
					location[1] = Layout.getWorkingOnLayout()[1][1];
					location[3] = location[3] - offset;
					regRectTop = true;
				} else if (location[1] > Layout.getWorkingOnLayout()[1][1] + Layout.getWorkingOnLayout()[1][3])
				{
					show = false;
				} else if (location[1] + location[3] > Layout.getWorkingOnLayout()[1][1]
						+ Layout.getWorkingOnLayout()[1][3])
				{
					int offset = (location[1] + location[3])
							- (Layout.getWorkingOnLayout()[1][1] + Layout.getWorkingOnLayout()[1][3]);
					location[3] = location[3] - offset;
					regRectBottom = true;
				}

				if (MT[2] == 2 && MT[3] == i)
				{

				} else if (show)
				{
					g.setColor(Colors.getColor(9));
					g.fillRoundRect(location[0], location[1], location[2], location[3], 15, 15);
					g.setColor(Color.BLACK);
					g.drawRoundRect(location[0], location[1], location[2], location[3], 15, 15);

					if (regRectBottom)
					{
						g.setColor(Colors.getColor(9));
						g.fillRect(location[0], location[1] + location[3] / 2, location[2], location[3] / 2);
					} else if (regRectTop)
					{
						g.setColor(Colors.getColor(9));
						g.fillRect(location[0], location[1], location[2], location[3] / 2);
					}

					g.setColor(Colors.getColor(1));

					for (int x = 0; x < text.length; x++)
					{
						if (x * fontSize + size * fontSize + WorkingOnList.getOffset()[1] > Layout.getTodoLayout()[1][1]
								+ fontSize / 2
								&& x * fontSize + size * fontSize
										+ WorkingOnList.getOffset()[1] < Layout.getTodoLayout()[1][1]
												+ Layout.getTodoLayout()[1][3] + fontSize / 4)
						{
							g.drawString(text[x], WorkingOnList.getOffset()[0],
									x * fontSize + size * fontSize + WorkingOnList.getOffset()[1]);
						}
					}
				}
				size += text.length;
			}
		}
	}

	private void showFinishedData(Graphics g, int[] MT)
	{
		if (FinishedList.Size() > 0)
		{
			int size = 0;
			for (int i = 0; i < FinishedList.Size(); i++)
			{
				boolean show = true;
				boolean regRectTop = false;
				boolean regRectBottom = false;
				String[] text = FinishedList.getText(i);

				int[] location = new int[]
				{ FinishedList.getOffset()[0], 3 + FinishedList.getOffset()[1] - fontSize + size * fontSize,
						Layout.getFinishedLayout()[1][2] - 10, text.length * fontSize };

				if (location[1] + location[3] < Layout.getFinishedLayout()[1][1])
				{
					show = false;
				} else if (location[1] < Layout.getFinishedLayout()[1][1])
				{
					int offset = Layout.getFinishedLayout()[1][1] - location[1];
					location[1] = Layout.getFinishedLayout()[1][1];
					location[3] = location[3] - offset;
					regRectTop = true;
				} else if (location[1] > Layout.getFinishedLayout()[1][1] + Layout.getFinishedLayout()[1][3])
				{
					show = false;
				} else if (location[1] + location[3] > Layout.getFinishedLayout()[1][1]
						+ Layout.getFinishedLayout()[1][3])
				{
					int offset = (location[1] + location[3])
							- (Layout.getFinishedLayout()[1][1] + Layout.getFinishedLayout()[1][3]);
					location[3] = location[3] - offset;
					regRectBottom = true;
				}

				if (MT[2] == 3 && MT[3] == i)
				{

				} else if (show)
				{
					g.setColor(Colors.getColor(9));
					g.fillRoundRect(location[0], location[1], location[2], location[3], 15, 15);
					g.setColor(Color.BLACK);
					g.drawRoundRect(location[0], location[1], location[2], location[3], 15, 15);

					if (regRectBottom)
					{
						g.setColor(Colors.getColor(9));
						g.fillRect(location[0], location[1] + location[3] / 2, location[2], location[3] / 2);
					} else if (regRectTop)
					{
						g.setColor(Colors.getColor(9));
						g.fillRect(location[0], location[1], location[2], location[3] / 2);
					}

					g.setColor(Colors.getColor(1));

					for (int x = 0; x < text.length; x++)
					{
						if (x * fontSize + size * fontSize + FinishedList.getOffset()[1] > Layout.getTodoLayout()[1][1]
								+ fontSize / 2
								&& x * fontSize + size * fontSize
										+ FinishedList.getOffset()[1] < Layout.getTodoLayout()[1][1]
												+ Layout.getTodoLayout()[1][3] + fontSize / 4)
						{
							g.drawString(text[x], FinishedList.getOffset()[0],
									x * fontSize + size * fontSize + FinishedList.getOffset()[1]);
						}
					}
				}
				size += text.length;
			}
		}
	}

	private void showMovingFromTodo(Graphics g, int[] MT)
	{
		if (MT[2] == 1)
		{
			int newY = Window.mouseY() - MT[1];
			String[] text = TodoList.getText(MT[3]);
			g.setColor(Colors.getColor(9));
			g.fillRoundRect(Window.mouseX() - MT[0], Window.mouseY() - MT[1], Layout.getTodoLayout()[1][2] - 10,
					text.length * fontSize, 15, 15);
			g.setColor(Color.BLACK);
			g.drawRoundRect(Window.mouseX() - MT[0], Window.mouseY() - MT[1], Layout.getTodoLayout()[1][2] - 10,
					text.length * fontSize, 15, 15);

			g.setColor(Colors.getColor(1));

			for (int x = 0; x < text.length; x++)
			{
				g.drawString(text[x], Window.mouseX() - MT[0], newY + x * fontSize + fontSize - 3);
			}
		}
	}

	private void showMovingFromWorkingOn(Graphics g, int[] MT)
	{
		if (MT[2] == 2)
		{
			int newY = Window.mouseY() - MT[1];
			String[] text = WorkingOnList.getText(MT[3]);
			g.setColor(Colors.getColor(9));
			g.fillRoundRect(Window.mouseX() - MT[0], Window.mouseY() - MT[1], Layout.getWorkingOnLayout()[1][2] - 10,
					text.length * fontSize, 15, 15);
			g.setColor(Color.BLACK);
			g.drawRoundRect(Window.mouseX() - MT[0], Window.mouseY() - MT[1], Layout.getWorkingOnLayout()[1][2] - 10,
					text.length * fontSize, 15, 15);

			g.setColor(Colors.getColor(1));

			for (int x = 0; x < text.length; x++)
			{
				g.drawString(text[x], Window.mouseX() - MT[0], newY + x * fontSize + fontSize - 3);
			}
		}
	}

	private void showMovingFromFinished(Graphics g, int[] MT)
	{
		if (MT[2] == 3)
		{
			int newY = Window.mouseY() - MT[1];
			String[] text = FinishedList.getText(MT[3]);
			g.setColor(Colors.getColor(9));
			g.fillRoundRect(Window.mouseX() - MT[0], Window.mouseY() - MT[1], Layout.getFinishedLayout()[1][2] - 10,
					text.length * fontSize, 15, 15);
			g.setColor(Color.BLACK);
			g.drawRoundRect(Window.mouseX() - MT[0], Window.mouseY() - MT[1], Layout.getFinishedLayout()[1][2] - 10,
					text.length * fontSize, 15, 15);

			g.setColor(Colors.getColor(1));

			for (int x = 0; x < text.length; x++)
			{
				g.drawString(text[x], Window.mouseX() - MT[0], newY + x * fontSize + fontSize - 3);
			}
		}
	}

	private void showPopUpWindows(Graphics g, int state)
	{
		int[] DD;
		int[][] F;
		switch (state)
		{
		case 1:
			int[] textBox = Layout.getnewTextBox();
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(textBox[0], textBox[1], textBox[2], textBox[3]);
			if (TextTyper.getText().length > 0)
			{
				g.setColor(Color.BLACK);
				for (int i = 0; i < TextTyper.getText().length; i++)
				{
					g.drawString(TextTyper.getText()[i], textBox[0], textBox[1] + i * fontSize + fontSize);
				}
			}
			break;
		case 2:
			DD = Layout.getSettingsDropDown();
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(DD[0], DD[1], DD[2], DD[3]);
			g.setColor(Color.GRAY);
			g.drawRect(DD[0], DD[1], DD[2], DD[3]);
			break;
		case 3:
			F = Layout.getNewSaveScreen();
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(F[0][0], F[0][1], F[0][2], F[0][3]);
			g.setColor(Color.GRAY);
			g.drawRect(F[0][0], F[0][1], F[0][2], F[0][3]);

			g.setColor(Color.WHITE);
			g.fillRect(F[1][0], F[1][1], F[1][2], F[1][3]);
			g.setColor(Color.BLACK);
			g.drawRect(F[1][0], F[1][1], F[1][2], F[1][3]);

			g.drawString(TextTyper.getText()[0], F[1][0] + 3, F[1][1] + fontSize);
			break;
		case 5:
			DD = Layout.getLoadDropDown();
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(DD[0], DD[1], DD[2], DD[3]);
			g.setColor(Color.GRAY);
			g.drawRect(DD[0], DD[1], DD[2], DD[3]);
			break;
		case 6:
			DD = Layout.getRightClickmenu();
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(DD[0], DD[1], DD[2], DD[3]);
			g.setColor(Color.GRAY);
			g.drawRect(DD[0], DD[1], DD[2], DD[3]);
			break;
		case 7:
			textBox = Layout.getnewTextBox();
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(textBox[0], textBox[1], textBox[2], textBox[3]);
			if (TextTyper.getText().length > 0)
			{
				g.setColor(Color.BLACK);
				for (int i = 0; i < TextTyper.getText().length; i++)
				{
					g.drawString(TextTyper.getText()[i], textBox[0], textBox[1] + i * fontSize + fontSize);
				}
			}
			break;
		}
	}

	private void showTopBar(Graphics g)
	{
		lightGray = new Color(200, 200, 200);
		g.setColor(lightGray);
		g.fillRect(0, 0, Window.width(), 30);
		g.setColor(Color.BLACK);
		g.drawString("File - " + Layout.getName(), 400, 20);
	}

	private void showButtons(Graphics g, int state)
	{
		for (int i = 0; i < Buttons.getLength(); i++)
		{
			int[] button = Buttons.getLocation(i);
			if (button[4] == -1)
			{
				lightGray = new Color(215, 215, 215);
				g.setColor(lightGray);
				g.fillRect(button[0], button[1], button[2], button[3]);

				lightGray = new Color(150, 150, 150);
				g.setColor(lightGray);
				g.drawRect(button[0], button[1], button[2], button[3]);

				g.setColor(Color.BLACK);
				g.drawString(Buttons.getName(i), button[0] + 10, button[1] + fontSize);

				if (Buttons.isMouseOverButton() == i)
				{
					g.setColor(Color.BLACK);
					g.drawRect(button[0], button[1], button[2], button[3]);
				}
			}

			if (button[4] == state)
			{
				lightGray = new Color(215, 215, 215);
				g.setColor(lightGray);
				g.fillRect(button[0], button[1], button[2], button[3]);

				lightGray = new Color(150, 150, 150);
				g.setColor(lightGray);
				g.drawRect(button[0], button[1], button[2], button[3]);

				g.setColor(Color.BLACK);
				g.drawString(Buttons.getName(i), button[0] + 10, button[1] + fontSize);

				if (Buttons.isMouseOverButton() == i)
				{
					g.setColor(Color.BLACK);
					g.drawRect(button[0], button[1], button[2], button[3]);
				}
			}
		}
	}

	private void showLoadedButtons(Graphics g, int state)
	{
		if (state == 5)
		{
			int[] td = Layout.getLoadDropDown();
			int[][] buttons = Load.getLoadedButtons();
			String[] names = Load.getFileNames();
			for (int i = 0; i < buttons.length; i++)
			{
				int[] button = buttons[i];

				if (button[1] > td[1] && button[1] + button[3] < td[1] + td[3])
				{
					lightGray = new Color(215, 215, 215);
					g.setColor(lightGray);
					g.fillRect(button[0], button[1], button[2], button[3]);

					lightGray = new Color(150, 150, 150);
					g.setColor(lightGray);
					g.drawRect(button[0], button[1], button[2], button[3]);

					g.setColor(Color.BLACK);
					g.drawString(names[i], button[0] + 10, button[1] + fontSize);

					if (Buttons.isMouseOverLoadedButton() == i)
					{
						g.setColor(Color.BLACK);
						g.drawRect(button[0], button[1], button[2], button[3]);
					}
				}
			}
		}
	}
}
