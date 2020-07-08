import java.util.ArrayList;

public class FinishedList
{
	private static int[] offset;
	private static ArrayList<TextBox> list;

	private static int size = 0;

	FinishedList()
	{
		offset = new int[]
		{ 0, 0 };
		list = new ArrayList<TextBox>();
	}

	public static void save(String[] text)
	{
		list.add(new TextBox(text));
	}

	public static String[] getText(int index)
	{
		return list.get(index).getText();
	}

	public static void delete(int i)
	{
		list.remove(i);
	}

	public static int Size()
	{
		return list.size();
	}

	public static void deleteList()
	{
		list = new ArrayList<TextBox>();
	}

	public static int[] getOffset()
	{
		return offset;
	}

	public static void setOffset(int x, int y)
	{
		offset = new int[]
		{ x, y };
	}

	public static int getSizeOfAllText()
	{
		return size;
	}

	public static void calculateSizeOfAllText()
	{
		size = 0;
		for (int i = 0; i < list.size(); i++)
		{
			size += list.get(i).text.length;
		}
	}
}
