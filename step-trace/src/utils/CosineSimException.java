/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utils;

/**
 *
 * @author omazhary
 */
public class CosineSimException extends Exception {
    
    private String message;
    
    public CosineSimException() {
        this.message = "Cosine Similarity Exception Occurred.";
        this.printMessage();
    }
    
    public CosineSimException(String message) {
        this.message = "Cosine Similarity Exception Occurred: " + message;
        this.printMessage();
    }
    
    private void printMessage() {
        System.err.println(this.message);
    }
    
}
