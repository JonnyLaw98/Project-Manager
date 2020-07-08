
public class TextBox
{
	String text[];

	TextBox(String[] data)
	{
		text = new String[data.length];
		for (int i = 0; i < data.length; i++)
		{
			text[i] = data[i];
		}

	}

	public String[] getText()
	{
		return text;
	}

	public void setText(String[] t)
	{
		text = new String[t.length];
		for (int i = 0; i < t.length; i++)
		{
			text[i] = t[i];
		}
	}
}
