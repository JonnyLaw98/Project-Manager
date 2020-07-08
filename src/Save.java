public class Save
{
	public static int checkIfNewFile()
	{
		if (Layout.getName().equals("Untitled"))
		{
			return 3;
		} else
		{
			return 4;
		}
	}

	public static void newFileName()
	{
		if (Window.getKeyCode() == 10)
		{
			Window.resetKeyCode();
		}
		TextTyper.curentlyTyping();
		TextTyper.FileNameIn();
	}

	public static void setFileName()
	{
		String FileName = TextTyper.saveFileName().substring(0, TextTyper.saveFileName().length());
		System.out.println(FileName);
		System.out.println(FileName);
		TextTyper.reset();
		FileManipulator.createFolder(FileName);
		Layout.setName(FileName);
	}

	public static void saveData()
	{
		String TD = "TodoList.txt";
		String WO = "WorkingOnList.txt";
		String FN = "FinishedList.txt";

		if (!FileManipulator.doesFileExist(Layout.getName() + "/" + TD))
		{
			FileManipulator.createFile(Layout.getName() + "/" + TD);
		}
		if (!FileManipulator.doesFileExist(Layout.getName() + "/" + WO))
		{
			FileManipulator.createFile(Layout.getName() + "/" + WO);
		}
		if (!FileManipulator.doesFileExist(Layout.getName() + "/" + FN))
		{
			FileManipulator.createFile(Layout.getName() + "/" + FN);
		}

		String FileName = Layout.getName().substring(0, Layout.getName().length());

		String data = "";
		if (TodoList.Size() > 0)
		{
			for (int i = 0; i < TodoList.Size(); i++)
			{

				for (int x = 0; x < TodoList.getText(i).length; x++)
				{
					String t = TodoList.getText(i)[x].substring(0, TodoList.getText(i)[x].length());
					if (i == TodoList.Size() - 1 && x == TodoList.getText(i).length - 1)
					{
						data += t;
					} else
					{
						data += t;
					}
					data += " * ";
				}
				if (i == TodoList.Size() - 1)
				{
					data += "**";
				} else
				{
					data += "**\n";
				}
			}
		}
		FileManipulator.saveFile(FileName + "/" + TD, data);

		data = "";
		if (WorkingOnList.Size() > 0)
		{
			for (int i = 0; i < WorkingOnList.Size(); i++)
			{

				for (int x = 0; x < WorkingOnList.getText(i).length; x++)
				{
					String t = WorkingOnList.getText(i)[x].substring(0, WorkingOnList.getText(i)[x].length());
					if (i == WorkingOnList.Size() - 1 && x == WorkingOnList.getText(i).length - 1)
					{
						data += t;
					} else
					{
						data += t;
					}
					data += " * ";
				}
				if (i == WorkingOnList.Size() - 1)
				{
					data += "**";
				} else
				{
					data += "**\n";
				}
			}
		}
		FileManipulator.saveFile(FileName + "/" + WO, data);

		data = "";
		if (FinishedList.Size() > 0)
		{
			for (int i = 0; i < FinishedList.Size(); i++)
			{

				for (int x = 0; x < FinishedList.getText(i).length; x++)
				{
					String t = FinishedList.getText(i)[x].substring(0, FinishedList.getText(i)[x].length());
					if (i == FinishedList.Size() - 1 && x == FinishedList.getText(i).length - 1)
					{
						data += t;
					} else
					{
						data += t;
					}
					data += " * ";
				}
				if (i == FinishedList.Size() - 1)
				{
					data += "**";
				} else
				{
					data += "**\n";
				}
			}
		}
		FileManipulator.saveFile(FileName + "/" + FN, data);
	}
}
