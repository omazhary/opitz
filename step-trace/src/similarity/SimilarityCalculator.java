package similarity;

import java.util.ArrayList;
import java.util.Iterator;

import entities.CADModel;
import entities.CADModelList;
import utils.CosineSimCalculator;

public class SimilarityCalculator {
	
	private CosineSimCalculator calculator;
	
	/**
	 * Initializes a SimilarityCalculator object.
	 */
	public SimilarityCalculator() {
		this.calculator = new CosineSimCalculator();
	}
	
	/**
	 * Compares the given code object to the items in the list, and filters out those with a higher similarity than the threshold.
	 * @param code The code to be compared.
	 * @param model_list The list to which the code will be compared.
	 * @param threshold The minimum similarity value to be achieved in order for two models to be similar.
	 * @return A CADModel[] containing all similar models.
	 */
	public CADModel[] getSimilarModelsViaOpitz(GTCode code, CADModelList model_list, double threshold) {
		ArrayList<CADModel> result1 = new ArrayList<CADModel> ();
		
		for (int i = 0; i < model_list.getModelListSize(); i++){
			CADModel temp = model_list.getModel(i);
			Opitz tempCode = new Opitz(temp.getPartGTCode());
			double tempSim = this.calculator.getSimilarity(code.getCode(), tempCode.getCode());
			if (tempSim >= threshold) {
				temp.setSimilarity(tempSim);
				result1.add(temp);
			}
		}
		
		CADModel[] result2 = new CADModel[result1.size()];
		for (int i = 0; i < result1.size(); i++) {
			result2[i] = result1.get(i);
		}
		
		return result2;
	}
	
}
