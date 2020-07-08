import java.awt.Color;

public class Colors
{
	private static Color[] colors;

	public static void defaultColors()
	{
		colors = new Color[]
		{ new Color(200, 200, 200), // background
				new Color(0, 0, 0), // text
				new Color(127, 127, 127), // top bar
				new Color(127, 127, 127), // buttons
				new Color(75, 75, 75), // button outlines
				new Color(0, 0, 0), // button outlines (mouse over)
				new Color(100, 100, 100), // text box
				new Color(220, 220, 220), // list headers
				new Color(240, 240, 240), // list boxes
				new Color(225, 225, 225)// text bubble5

		};
	}

	public static void setColor(int index, int R, int G, int B)
	{

	}

	public static Color getColor(int i)
	{
		return colors[i];
	}
}
