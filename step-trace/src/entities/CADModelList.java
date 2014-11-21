package entities;

import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Iterator;

import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class CADModelList {
	
	private CADModel[] models;
	
	public CADModelList() {
		SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File(this.getModelsFilePath());
		org.jdom2.Document doc;
		int counter = 0;
		try {
			doc = (org.jdom2.Document) builder
					.build(xmlFile);
			Element rootNode = doc.getRootElement();
			this.models = new CADModel[rootNode.getChildren().size()];
			Iterator<Element> model = rootNode.getChildren().iterator();
			while (model.hasNext()) {
				Element node = (Element) model.next();
				this.models[counter] = new CADModel(node.getChildText("identifier"));
				counter++;
			}
		} catch (JDOMException e) {
			System.err.println("Error: Unable to parse XML file.");
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println("Error: Unable to open XML file.");
			System.err.println(e.getMessage());
		}
	}
	
	/**
	 * Gets the current directory.
	 * 
	 * @return A string containing the canonical path to the current directory.
	 */
	private String getCurrentDirectory() {
		String curDir_Path = "";
		File curDir = new File(".");
		try {
			curDir_Path = curDir.getCanonicalPath();
		} catch (IOException e1) {
			System.err.println("Error: Unable to establish current directory.");
			System.err.println(e1.getMessage());
		}
		return curDir_Path;
	}

	/**
	 * Returns the path to the models.xml file.
	 * 
	 * @return A string containing the path to the model repository.
	 */
	private String getModelsFilePath() {
		return this.getCurrentDirectory() + "/bin/xml/models.xml";
	}
	
	/**
	 * Gets a specific model from the model list.
	 * @param index The index of the desired model.
	 * @return A CADModel object containing the model information.
	 */
	public CADModel getModel(int index) {
		return this.models[index];
	}
	
	/**
	 * Gets the current size of the model list.
	 * @return An int containing the model list size.
	 */
	public int getModelListSize() {
		return this.models.length;
	}
	
	/**
	 * Exports the model list to the specified directory.
	 * @param newPath The new directory to which the model list should be exported.
	 * @return TRUE if successful, FALSE otherwise.
	 */
	public boolean exportModelList(String newPath) {
		try {
			Files.copy(new File(this.getModelsFilePath()).toPath(), new File(newPath).toPath(), StandardCopyOption.REPLACE_EXISTING);
			return true;
		} catch (IOException e) {
			System.err.println("Error: Unable to copy to target directory.");
			System.err.println(e.getMessage());
			return false;
		}
	}
	
	/**
	 * Imports the model list from the specified directory.
	 * @param newPath The directory from which the model list should be imported.
	 * @return TRUE if successful, FALSE otherwise.
	 */
	public boolean importModelList(String newPath) {
		try {
			Files.copy(new File(newPath).toPath(), new File(this.getModelsFilePath()).toPath(), StandardCopyOption.REPLACE_EXISTING);
			return true;
		} catch (IOException e) {
			System.err.println("Error: Unable to copy to target directory.");
			System.err.println(e.getMessage());
			return false;
		}
	}
	
}
