package main;

import java.io.File;
import java.io.IOException;
import java.io.FileOutputStream;

import gui.MainWindow;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Check for the xml/models.xml file.
		File models = new File("xml/models.xml");
		if (!models.exists()) {
			try {
				models.getParentFile().mkdirs();
				models.createNewFile();
				FileOutputStream out = new FileOutputStream(models);
				out.write(new String("<?xml version=\"1.0\" encoding=\"UTF-8\"?><model_base></model_base>").getBytes());
				out.flush();
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.err.println("Error: Unable to create model index file.");
			}
		}
		
		MainWindow window = new MainWindow();
		//window.show();
		window.setVisible(true);
	}

}
