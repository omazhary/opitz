package entities;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.jdom2.Content;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;
import org.xml.sax.SAXException;

public class CADModel {

	private String identifier;
	private String name;
	private String gtCode;
	private String imagePath;
	private String description;
	private String path;

	/**
	 * Returns an instance of the CADModel class populated with the identifier's
	 * information.
	 * 
	 * @param identifier
	 *            The model's unique identifier.
	 */
	public CADModel(String identifier) {
		this.identifier = identifier;
		if (this.fetchModelData()) {
			System.out.println("Fetch succeeded!!");
		}
	}

	/**
	 * Returns an instance of the CADModel class populated with the given
	 * information.
	 * 
	 * @param name
	 *            The name of the part in the model.
	 * @param gtCode
	 *            The group technology code of the model.
	 * @param imagePath
	 *            The path to the model's image.
	 * @param description
	 *            A short description about the model.
	 * @param path
	 *            The path the model's STEP file.
	 */
	public CADModel(String name, String gtCode, String imagePath,
			String description, String path) {
		this.identifier = null;
		this.name = name;
		this.gtCode = gtCode;
		this.imagePath = imagePath;
		this.description = description;
		this.path = path;
	}

	/**
	 * Fetches the model's data from the XML file.
	 * 
	 * @return TRUE if the fetch was successful, FALSE otherwise.
	 */
	private boolean fetchModelData() {

		File model_list = new File(this.getModelsFilePath());
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = null;
		try {
			db = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			System.err
					.println("Error: Unable to get DocumentBuilder instance.");
			return false;
		}
		Document doc = null;
		try {
			doc = db.parse(model_list);
		} catch (SAXException e) {
			System.err.println("Error: Unable to parse XML file.");
			System.err.println(e.getMessage());
			return false;
		} catch (IOException e) {
			System.err.println("Error: Unable to open XML file.");
			System.err.println(e.getMessage());
			return false;
		}

		doc.getDocumentElement().normalize();
		System.out.println("Root Element: "
				+ doc.getDocumentElement().getNodeName());

		return true;
	}

	public boolean writeModelData() {
		try {
			SAXBuilder builder = new SAXBuilder();
			File xmlFile = new File(this.getModelsFilePath());
			org.jdom2.Document doc = (org.jdom2.Document) builder
					.build(xmlFile);
			Element rootNode = doc.getRootElement();
			Element newModel = new Element("model");
			Element xml_identifier = new Element("identifier");
			xml_identifier
					.setText(this.getNewIdentifier(rootNode.getChildren()));
			Element xml_partName = new Element("part_name");
			xml_partName.setText(this.name);
			Element xml_partFilePath = new Element("part_file_path");
			xml_partFilePath.setText(this.path);
			Element xml_partGTCode = new Element("part_gt_code");
			xml_partGTCode.setText(this.gtCode);
			Element xml_partDescription = new Element("part_description");
			xml_partDescription.setText(this.description);
			Element xml_partImageFilePath = new Element("part_image_path");
			xml_partImageFilePath.setText(this.imagePath);
			newModel.addContent(xml_identifier);
			newModel.addContent(xml_partName);
			newModel.addContent(xml_partFilePath);
			newModel.addContent(xml_partGTCode);
			newModel.addContent(xml_partDescription);
			newModel.addContent(xml_partImageFilePath);
			rootNode.getChildren().add(newModel);
			XMLOutputter xmloutput = new XMLOutputter();
			xmloutput.output(doc, new FileWriter(this.getModelsFilePath()));
		} catch (JDOMException e) {
			System.err.println("Error: Unable to parse XML file.");
			System.err.println(e.getMessage());
			return false;
		} catch (IOException e) {
			System.err.println("Error: Unable to open XML file.");
			System.err.println(e.getMessage());
			return false;
		}
		return true;
	}

	/**
	 * Getter for the part identifier.
	 * 
	 * @return A string containing the part identifier.
	 */
	public String getPartIdentifier() {
		return this.identifier;
	}

	/**
	 * Getter for the part name.
	 * 
	 * @return A string containing the part name.
	 */
	public String getPartName() {
		return this.name;
	}

	/**
	 * Getter for the part group technology code.
	 * 
	 * @return A string containing the part group technology code.
	 */
	public String getPartGTCode() {
		return this.gtCode;
	}

	/**
	 * Getter for the part image path.
	 * 
	 * @return A string containing the part image path.
	 */
	public String getPartImagePath() {
		return this.imagePath;
	}

	/**
	 * Getter for the part description.
	 * 
	 * @return A string containing the part description.
	 */
	public String getPartDescription() {
		return this.description;
	}

	/**
	 * Getter for the part STEP file path.
	 * 
	 * @return A string containing the part STEP file path.
	 */
	public String getPartPath() {
		return this.path;
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
	 * Gets a new identifier which is typically the largest identifier + 1.
	 * 
	 * @param parent
	 *            The root-element's children.
	 * @return A String containing the new identifier.
	 */
	private String getNewIdentifier(List<Element> parent) {
		Integer max = -1;
		Iterator<Element> iterator = parent.iterator();
		while (iterator.hasNext()) {
			Element temp_child = iterator.next();
			Integer temp = Integer.parseInt(temp_child
					.getChildText("identifier"));
			if (temp > max) {
				max = temp;
			}
		}
		if (max <= 0) {
			return "1";
		} else {
			max = max + 1;
			return max.toString();
		}
	}

	/**
	 * Checks if there's a duplicate element within the root-element based on
	 * the identifier.
	 * 
	 * @param parent
	 *            The root element of the XML document.
	 * @param suspect_child
	 *            The child element we suspect exists.
	 * @return TRUE if it is duplicated, FALSE otherwise.
	 */
	private boolean checkDuplicate(List<Element> parent, Element suspect_child) {
		Iterator<Element> iterator = parent.iterator();
		while (iterator.hasNext()) {
			Element temp_child = iterator.next();
			if (temp_child.getChildText("identifier").equalsIgnoreCase(
					suspect_child.getChildText("identifier"))) {
				return true;
			}
		}
		return false;
	}

}
