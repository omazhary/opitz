package entities;

import java.util.ArrayList;

import dbconns.IDBConn;

public class Repository {

	private IDBConn connection;
	private CADModelList model_list;

	public Repository(IDBConn conn) {
		this.connection = conn;
		this.model_list = new CADModelList();
	}

	/**
	 * Synchronizes the model list with the data from the repository.
	 * @return TRUE if the operation was successful, FALSE otherwise.
	 */
	public boolean synchronize() {
		ArrayList<String[]> result = this.connection
				.runSelectQuery("SELECT * FROM model_base;");
		if (result == null || result.size() <= 0) {
			return false;
		} else {
			for (int i = 0; i < result.size(); i++) {
				String[] temp1 = result.get(i);
				CADModel temp2 = new CADModel(temp1[1], temp1[3], temp1[5], temp1[4], temp1[2]);
				if (!modelExists(temp2)) {
					temp2.writeModelData();
				}
			}
		}
		return true;
	}

	/**
	 * Checks to see if a model with the same parameter exists.
	 * 
	 * @param identifier
	 *            The identifier in question.
	 * @return TRUE if a model with the same identifier exists, FALSE otherwise.
	 */
	private boolean modelExists(CADModel model) {
		for (int i = 0; i < this.model_list.getModelListSize(); i++) {
			CADModel temp = this.model_list.getModel(i);
			if (temp.getPartName().equalsIgnoreCase(model.getPartName())
					&& temp.getPartPath().equalsIgnoreCase(model.getPartPath())
					&& temp.getPartGTCode().equalsIgnoreCase(
							model.getPartGTCode())
					&& temp.getPartDescription().equalsIgnoreCase(
							model.getPartDescription())) {
				return true;
			}
		}
		return false;
	}

}
