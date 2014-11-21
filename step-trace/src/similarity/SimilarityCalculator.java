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
	public CADModel[] getSimilarModelsViaOpitz(GTCode code, CADModelList model_list, double threshold, Integer[] weights) {
		ArrayList<CADModel> result1 = new ArrayList<CADModel> ();
		
		for (int i = 0; i < model_list.getModelListSize(); i++){
			CADModel temp = model_list.getModel(i);
			Opitz tempCode = new Opitz(temp.getPartGTCode());
			double tempSim = this.calculator.getSimilarity(this.applyWeights(code.getCode(), weights), this.applyWeights(tempCode.getCode(), weights)); 
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
	
	/**
	 * Applies the weights to the given code by multiplying the code digit by 10^weight.
	 * @param code The code to which the weight should be applied.
	 * @param weights The weights that will be applied.
	 * @return An Integer[] containing the weighted values of the code.
	 */
	private Double[] applyWeights(Integer[] code, Integer[] weights) {
		Double[] result = new Double[weights.length];
		for (int i = 0; i < weights.length; i++) {
			result[i] = new Double(Double.parseDouble(code[i].toString()) * Double.parseDouble(weights[i].toString()));//Math.pow(10, Double.parseDouble(weights[i].toString())));
		}
		return result;
	}
	
}
