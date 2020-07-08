import java.io.File;

public class Load
{
	private static String[] FileNames;
	private static String path;

	private static int[][] params;

	private static int offset = 0;

	public static int getOffset()
	{
		return offset;
	}

	public static void setOffset(int i)
	{
		offset = i;
	}

	public static String[] getFileNames()
	{
		return FileNames;
	}

	public static void listFiles()
	{
		FileManipulator.createFile("dirF");
		path = FileManipulator.getPath("dirF").substring(0, FileManipulator.getPath("dirF").length() - 5);

		File[] files = FileManipulator.listFiles(path);
		FileNames = new String[] {};

		for (int i = 0; i < files.length; i++)
		{
			if (files[i].isDirectory())
			{
				extendArray(files[i].getName());
			}
		}
		createButtons();
	}

	private static void extendArray(String newData)
	{
		try
		{
			@SuppressWarnings("unused")
			int t = FileNames.length;

			String[] temp = new String[FileNames.length];
			for (int i = 0; i < FileNames.length; i++)
			{
				temp[i] = FileNames[i];
			}
			FileNames = new String[FileNames.length + 1];
			for (int i = 0; i < temp.length; i++)
			{
				FileNames[i] = temp[i];
			}
			FileNames[FileNames.length - 1] = newData;
		} catch (Exception e)
		{
			FileNames = new String[1];
			FileNames[0] = newData;
		}
	}

	public static void loadFile(String file)
	{
		TodoList.deleteList();
		WorkingOnList.deleteList();
		FinishedList.deleteList();

		String TD = "TodoList.txt";
		String WO = "WorkingOnList.txt";
		String FN = "FinishedList.txt";

		String[] data = new String[] {};
		String SL = "";

		String[] TDData = FileManipulator.loadFileS(file + "/" + TD);
		for (int i = 0; i < TDData.length; i++)
		{
			if (TDData[i].equals("**"))
			{
				TodoList.save(data);
				data = new String[] {};
			} else if (TDData[i].equals("*"))
			{
				data = extendData(data, SL);
				SL = "";

			} else
			{
				SL += TDData[i] + " ";
			}
		}

		data = new String[] {};
		SL = "";
		TDData = FileManipulator.loadFileS(file + "/" + WO);
		for (int i = 0; i < TDData.length; i++)
		{
			if (TDData[i].equals("**"))
			{
				WorkingOnList.save(data);
				data = new String[] {};
			} else if (TDData[i].equals("*"))
			{
				data = extendData(data, SL);
				SL = "";

			} else
			{
				SL += TDData[i] + " ";
			}
		}

		data = new String[] {};
		SL = "";
		TDData = FileManipulator.loadFileS(file + "/" + FN);
		for (int i = 0; i < TDData.length; i++)
		{
			if (TDData[i].equals("**"))
			{
				FinishedList.save(data);
				data = new String[] {};
			} else if (TDData[i].equals("*"))
			{
				data = extendData(data, SL);
				SL = "";

			} else
			{
				SL += TDData[i] + " ";
			}
		}
	}

	private static String[] extendData(String[] data, String newData)
	{
		String[] Data = data;
		try
		{
			@SuppressWarnings("unused")
			int t = Data.length;

			String[] temp = new String[Data.length];
			for (int i = 0; i < Data.length; i++)
			{
				temp[i] = Data[i];
			}
			Data = new String[Data.length + 1];
			for (int i = 0; i < temp.length; i++)
			{
				Data[i] = temp[i];
			}
			Data[Data.length - 1] = newData;
		} catch (Exception e)
		{
			Data = new String[1];
			Data[0] = newData;
		}
		return Data;
	}

	public static void createButtons()
	{
		params = new int[FileNames.length][];

		for (int i = 0; i < params.length; i++)
		{
			params[i] = new int[]
			{ 195, 35 + 29 * i + offset, 190, 27 };
		}
	}

	public static int[][] getLoadedButtons()
	{
		return params;
	}
}
