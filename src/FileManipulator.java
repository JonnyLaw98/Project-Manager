import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileManipulator
{
	/**
	 * @param FileName - name of the file you want to create.
	 * 
	 * @description: creates a file with the specified name. if a file with the name
	 *               specified already exsists, it does nothing.
	 */
	public static void createFile(String FileName)
	{
		File file = null;
		try
		{
			file = new File(FileName); // initialize File object and passing path as argument
			@SuppressWarnings(
			{ "unused", "resource" })
			FileWriter fw = new FileWriter(file);
		} catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("could not find file");
		}
		boolean result;
		try
		{
			result = file.mkdirs(); // creates a new file
			if (result) // test if successfully created a new file
			{
				System.out.println("file created " + file.getCanonicalPath()); // returns the path string
			} else
			{
				System.out.println("File already exist at location: " + file.getCanonicalPath());
			}
		} catch (IOException e)
		{
			e.printStackTrace(); // prints exception if any
		}
	}

	/**
	 * @param FileName - name of the file you want to create.
	 * 
	 * @description: creates a folder with the specified name. if a folder with the
	 *               name specified already exsists, it does nothing.
	 */
	public static void createFolder(String FileName)
	{
		File file = null;
		try
		{
			file = new File(FileName); // initialize File object and passing path as argument
		} catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("could not find file");
		}
		boolean result;
		try
		{
			result = file.mkdirs(); // creates a new file
			if (result) // test if successfully created a new file
			{
				System.out.println("file created " + file.getCanonicalPath()); // returns the path string
			} else
			{
				System.out.println("File already exist at location: " + file.getCanonicalPath());
			}
		} catch (IOException e)
		{
			e.printStackTrace(); // prints exception if any
		}
	}

	/**
	 * 
	 * @param FileName - name of file
	 * @return returns if the file exists
	 */
	public static boolean doesFileExist(String FileName)
	{
		File file = new File(FileName);
		return file.exists();
	}

	/**
	 * @param FileName - name of the file you want to save to.
	 * 
	 * @param data     - string you want to save to the file
	 * 
	 * @description: creates a file with the specified name. if a file with the name
	 *               specified already exsists, it does nothing.
	 */
	public static void saveFile(String FileName, String data)
	{
		System.out.println("--SAVING--");
		try
		{
			File newTextFile = new File(FileName);
			FileWriter fw = new FileWriter(newTextFile);

			fw.write(data);

			fw.close();
			System.out.println("--SAVED--TO--FILE--");

		} catch (IOException iox)
		{
			// do stuff with exception
			iox.printStackTrace();
			System.out.println("COULD NOT SAVE");
		}
	}

	/**
	 * @param FileName - name of the file you want to load.
	 * 
	 * @description: gives you an array of strings from the file.
	 * 
	 */
	public static String[] loadFileS(String FileName)
	{
		String[] data = new String[0];
		String[] temp = new String[0];
		int temp2 = 0;
		System.out.println("--LOADING--");

		Scanner sc2 = null;
		try
		{
			sc2 = new Scanner(new File(FileName));
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
			System.out.println("--COULD--NOT--LOAD--");
		}

		try
		{

			while (sc2.hasNextLine())
			{

				temp2++;
				for (int i = 0; i < data.length; i++)
				{
					temp[i] = data[i];
				}
				data = new String[temp2];
				for (int i = 0; i < temp.length; i++)
				{
					data[i] = temp[i];
				}
				data[data.length - 1] = String.valueOf(sc2.next());
				temp = new String[temp2];
				// System.out.print(data[data.length - 1] + " ");
			}
			System.out.println("--LOADED--");
		} catch (Exception e)
		{

		}

		return data;
	}

	public static File[] listFiles(String Path)
	{
		File folder = new File(Path);
		File[] listOfFiles = folder.listFiles();

		return listOfFiles;
	}

	public static String getPath(String FileName)
	{
		File folder = new File(FileName);
		return folder.getAbsolutePath();
	}
}
