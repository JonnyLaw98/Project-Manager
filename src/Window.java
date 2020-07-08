import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window implements KeyListener, MouseListener, MouseWheelListener, MouseMotionListener
{
	private static JFrame window;
	private static JPanel panel;

	Window(String title, boolean includePanel)
	{
		window = new JFrame(title);
		window.setSize(400, 430);

		if (includePanel)
		{
			panel = new JPanel();
			panel.setSize(400, 400);
			panel.setLayout(null);
			panel.setVisible(true);

			window.add(panel);
		}

		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Input();
	}

	Window(String title, boolean includePanel, int x, int y)
	{
		window = new JFrame(title);
		window.setSize(x, y + 30);

		if (includePanel)
		{
			panel = new JPanel();
			panel.setSize(x, y);
			panel.setLayout(null);
			panel.setVisible(true);

			window.add(panel);
		}

		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Input();
	}

	public static JFrame getWindow()
	{
		return window;
	}

	public static JPanel getPanel()
	{
		return panel;
	}

	public static void backgroundColor(int r, int g, int b)
	{
		panel.setBackground(new Color(r, g, b));
	}

	@SuppressWarnings("deprecation")
	public static void showWindow()
	{
		window.show();
	}

	public static int width()
	{
		return window.getWidth();
	}

	public static int height()
	{
		return window.getHeight() - 30;
	}

	private static boolean[] keys;
	private static boolean[] mouseButton;
	private static int KeyCode;
	private static char key;

	private static int mousex;
	private static int mousey;

	private static boolean mouseWheelUp;
	private static boolean mouseWheelDown;

	public static boolean isMouseWheelUp()
	{
		return mouseWheelUp;
	}

	public static void setMouseWheelUp(boolean MouseWheelUp)
	{
		mouseWheelUp = MouseWheelUp;
	}

	public static boolean isMouseWheelDown()
	{
		return mouseWheelDown;
	}

	public static void setMouseWheelDown(boolean MouseWheelDown)
	{
		mouseWheelDown = MouseWheelDown;
	}

	private void Input()
	{
		window.addKeyListener(this);
		window.addMouseListener(this);
		window.addMouseWheelListener(this);
		window.addMouseMotionListener(this);
		keys = new boolean[39];
		mouseButton = new boolean[3];
		KeyCode = 0;

	}

	public static boolean getKey(int i)
	{
		return keys[i];
	}

///////////////////////////////////////////////////////////////
	// mouse input

	public static int mouseX()
	{
		return mousex;
	}

	public static int mouseY()
	{
		return mousey;
	}

	public static void disableMouseButton(int i)
	{
		mouseButton[i] = false;
	}

	public static boolean getMouseButton(int i)
	{
		return mouseButton[i];
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e)
	{
		if (e.getPreciseWheelRotation() > 0)
		{
			mouseWheelUp = true;
		}

		if (e.getPreciseWheelRotation() < 0)
		{
			mouseWheelDown = true;
		}

	}

	@Override
	public void mouseDragged(MouseEvent e)
	{
		mousex = e.getX() - 8;
		mousey = e.getY() - 31;
	}

	@Override
	public void mouseMoved(MouseEvent e)
	{
		mousex = e.getX() - 8;
		mousey = e.getY() - 31;
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{

	}

	@Override
	public void mousePressed(MouseEvent e)
	{

		if (e.getButton() == 1)
		{
			mouseButton[0] = true;
		}

		if (e.getButton() == 3)
		{
			mouseButton[1] = true;
		}

		if (e.getButton() == 2)
		{
			mouseButton[2] = true;
		}

		mousex = e.getX() - 8;
		mousey = e.getY() - 31;

	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		if (e.getButton() == 1)
		{
			mouseButton[0] = false;
		}

		if (e.getButton() == 3)
		{
			mouseButton[1] = false;
		}

		if (e.getButton() == 2)
		{
			mouseButton[2] = false;
		}

		mousex = e.getX() - 8;
		mousey = e.getY() - 31;

	}

	@Override
	public void mouseEntered(MouseEvent e)
	{

	}

	@Override
	public void mouseExited(MouseEvent e)
	{

	}

///////////////////////////////////////////////////////////////
	// keyboard input

	public static int getKeyCode()
	{
		return KeyCode;
	}

	public static void resetKeyCode()
	{
		KeyCode = 0;
	}

	public static char getKey()
	{
		return key;
	}

	public static void resetKey()
	{
		key = '\u0000';
	}

	public static void disableKey(int i)
	{
		keys[i] = false;
	}

	// no clue what this one does
	@Override
	public void keyTyped(KeyEvent e)
	{
		key = e.getKeyChar();
	}

	// consistant input when key is held
	@SuppressWarnings("static-access")
	@Override
	public void keyPressed(KeyEvent e)
	{

		KeyCode = e.getKeyCode();

		switch (e.getKeyText(e.getKeyCode()))
		{
		case "A":
			keys[0] = true;
			break;

		case "B":
			keys[1] = true;
			break;

		case "C":
			keys[2] = true;
			break;

		case "D":
			keys[3] = true;
			break;

		case "E":
			keys[4] = true;
			break;

		case "F":
			keys[5] = true;
			break;

		case "G":
			keys[6] = true;
			break;

		case "H":
			keys[7] = true;
			break;

		case "I":
			keys[8] = true;
			break;

		case "J":
			keys[9] = true;
			break;

		case "K":
			keys[10] = true;
			break;

		case "L":
			keys[11] = true;
			break;

		case "M":
			keys[12] = true;
			break;

		case "N":
			keys[13] = true;
			break;

		case "O":
			keys[14] = true;
			break;

		case "P":
			keys[15] = true;
			break;

		case "Q":
			keys[16] = true;
			break;

		case "R":
			keys[17] = true;
			break;

		case "S":
			keys[18] = true;
			break;

		case "T":
			keys[19] = true;
			break;

		case "U":
			keys[20] = true;
			break;

		case "V":
			keys[21] = true;
			break;

		case "W":
			keys[22] = true;
			break;

		case "X":
			keys[23] = true;
			break;

		case "Y":
			keys[24] = true;
			break;

		case "Z":
			keys[25] = true;
			break;

		case "Space":
			keys[26] = true;
			break;

		case "1":
			keys[27] = true;
			break;

		case "2":
			keys[28] = true;
			break;

		case "3":
			keys[29] = true;
			break;

		case "4":
			keys[30] = true;
			break;

		case "5":
			keys[31] = true;
			break;

		case "6":
			keys[32] = true;
			break;

		case "7":
			keys[33] = true;
			break;

		case "8":
			keys[34] = true;
			break;

		case "9":
			keys[35] = true;
			break;

		case "0":
			keys[36] = true;
			break;

		case "Shift":
			keys[37] = true;
			break;

		case "Ctrl":
			keys[38] = true;
			break;
		}
	}

	// activates after key is released
	@SuppressWarnings("static-access")
	@Override
	public void keyReleased(KeyEvent e)
	{
		switch (e.getKeyText(e.getKeyCode()))
		{
		case "A":
			keys[0] = false;
			break;

		case "B":
			keys[1] = false;
			break;

		case "C":
			keys[2] = false;
			break;

		case "D":
			keys[3] = false;
			break;

		case "E":
			keys[4] = false;
			break;

		case "F":
			keys[5] = false;
			break;

		case "G":
			keys[6] = false;
			break;

		case "H":
			keys[7] = false;
			break;

		case "I":
			keys[8] = false;
			break;

		case "J":
			keys[9] = false;
			break;

		case "K":
			keys[10] = false;
			break;

		case "L":
			keys[11] = false;
			break;

		case "M":
			keys[12] = false;
			break;

		case "N":
			keys[13] = false;
			break;

		case "O":
			keys[14] = false;
			break;

		case "P":
			keys[15] = false;
			break;

		case "Q":
			keys[16] = false;
			break;

		case "R":
			keys[17] = false;
			break;

		case "S":
			keys[18] = false;
			break;

		case "T":
			keys[19] = false;
			break;

		case "U":
			keys[20] = false;
			break;

		case "V":
			keys[21] = false;
			break;

		case "W":
			keys[22] = false;
			break;

		case "X":
			keys[23] = false;
			break;

		case "Y":
			keys[24] = false;
			break;

		case "Z":
			keys[25] = false;
			break;

		case "Space":
			keys[26] = false;
			break;

		case "1":
			keys[27] = false;
			break;

		case "2":
			keys[28] = false;
			break;

		case "3":
			keys[29] = false;
			break;

		case "4":
			keys[30] = false;
			break;

		case "5":
			keys[31] = false;
			break;

		case "6":
			keys[32] = false;
			break;

		case "7":
			keys[33] = false;
			break;

		case "8":
			keys[34] = false;
			break;

		case "9":
			keys[35] = false;
			break;

		case "0":
			keys[36] = false;
			break;

		case "Shift":
			keys[37] = false;
			break;

		case "Ctrl":
			keys[38] = false;
			break;
		}
	}

	public static boolean A()
	{
		return keys[0];
	}

	public static boolean B()
	{
		return keys[1];
	}

	public static boolean C()
	{
		return keys[2];
	}

	public static boolean D()
	{
		return keys[3];
	}

	public static boolean E()
	{
		return keys[4];
	}

	public static boolean F()
	{
		return keys[5];
	}

	public static boolean G()
	{
		return keys[6];
	}

	public static boolean H()
	{
		return keys[7];
	}

	public static boolean I()
	{
		return keys[8];
	}

	public static boolean J()
	{
		return keys[9];
	}

	public static boolean K()
	{
		return keys[10];
	}

	public static boolean L()
	{
		return keys[11];
	}

	public static boolean M()
	{
		return keys[12];
	}

	public static boolean N()
	{
		return keys[13];
	}

	public static boolean O()
	{
		return keys[14];
	}

	public static boolean P()
	{
		return keys[15];
	}

	public static boolean Q()
	{
		return keys[16];
	}

	public static boolean R()
	{
		return keys[17];
	}

	public static boolean S()
	{
		return keys[18];
	}

	public static boolean T()
	{
		return keys[19];
	}

	public static boolean U()
	{
		return keys[20];
	}

	public static boolean V()
	{
		return keys[21];
	}

	public static boolean W()
	{
		return keys[22];
	}

	public static boolean X()
	{
		return keys[23];
	}

	public static boolean Y()
	{
		return keys[24];
	}

	public static boolean Z()
	{
		return keys[25];
	}

	public static boolean Space()
	{
		return keys[26];
	}

	public static boolean One()
	{
		return keys[27];
	}

	public static boolean Two()
	{
		return keys[28];
	}

	public static boolean Three()
	{
		return keys[29];
	}

	public static boolean Four()
	{
		return keys[30];
	}

	public static boolean Five()
	{
		return keys[31];
	}

	public static boolean Six()
	{
		return keys[32];
	}

	public static boolean Seven()
	{
		return keys[33];
	}

	public static boolean Eight()
	{
		return keys[34];
	}

	public static boolean Nine()
	{
		return keys[35];
	}

	public static boolean Zero()
	{
		return keys[36];
	}

	public static boolean Shift()
	{
		return keys[37];
	}

	public static boolean Ctrl()
	{
		return keys[38];
	}

	/////////////////////////////////////////////////////////////

	public static void setA(boolean set)
	{
		keys[0] = set;
	}

	public static void setB(boolean set)
	{
		keys[1] = set;
	}

	public static void setC(boolean set)
	{
		keys[2] = set;
	}

	public static void setD(boolean set)
	{
		keys[3] = set;
	}

	public static void setE(boolean set)
	{
		keys[4] = set;
	}

	public static void setF(boolean set)
	{
		keys[5] = set;
	}

	public static void setG(boolean set)
	{
		keys[6] = set;
	}

	public static void setH(boolean set)
	{
		keys[7] = set;
	}

	public static void setI(boolean set)
	{
		keys[8] = set;
	}

	public static void setJ(boolean set)
	{
		keys[9] = set;
	}

	public static void setK(boolean set)
	{
		keys[10] = set;
	}

	public static void setL(boolean set)
	{
		keys[11] = set;
	}

	public static void setM(boolean set)
	{
		keys[12] = set;
	}

	public static void setN(boolean set)
	{
		keys[13] = set;
	}

	public static void setO(boolean set)
	{
		keys[14] = set;
	}

	public static void setP(boolean set)
	{
		keys[15] = set;
	}

	public static void setQ(boolean set)
	{
		keys[16] = set;
	}

	public static void setR(boolean set)
	{
		keys[17] = set;
	}

	public static void setS(boolean set)
	{
		keys[18] = set;
	}

	public static void setT(boolean set)
	{
		keys[19] = set;
	}

	public static void setU(boolean set)
	{
		keys[20] = set;
	}

	public static void setV(boolean set)
	{
		keys[21] = set;
	}

	public static void setW(boolean set)
	{
		keys[22] = set;
	}

	public static void setX(boolean set)
	{
		keys[23] = set;
	}

	public static void setY(boolean set)
	{
		keys[24] = set;
	}

	public static void setZ(boolean set)
	{
		keys[25] = set;
	}

	public static void setSpace(boolean set)
	{
		keys[26] = set;
	}

	public static void setOne(boolean set)
	{
		keys[27] = set;
	}

	public static void setTwo(boolean set)
	{
		keys[28] = set;
	}

	public static void setThree(boolean set)
	{
		keys[29] = set;
	}

	public static void setFour(boolean set)
	{
		keys[30] = set;
	}

	public static void setFive(boolean set)
	{
		keys[31] = set;
	}

	public static void setSix(boolean set)
	{
		keys[32] = set;
	}

	public static void setSeven(boolean set)
	{
		keys[33] = set;
	}

	public static void setEight(boolean set)
	{
		keys[34] = set;
	}

	public static void setNine(boolean set)
	{
		keys[35] = set;
	}

	public static void setZero(boolean set)
	{
		keys[36] = set;
	}

	public static void setShift(boolean set)
	{
		keys[37] = set;
	}

	public static void setCtrl(boolean set)
	{
		keys[38] = set;
	}
}