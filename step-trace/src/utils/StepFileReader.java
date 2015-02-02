package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import entities.AbstractEntity;
import entities.ClosedShell;

public class StepFileReader {

    private Map<String, String> linesMap;

    /**
     * Reads a STEP file with the given name.
     *
     * @param fileName The name of the STEP file to be read.
     */
    public StepFileReader(String fileName) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        linesMap = new HashMap<String, String>();
        try {
            String line = null;
            try {
                line = br.readLine();
                while (line != null) {
                    // This is for elements within the "DATA" section:
                    if (line.startsWith("#")) {
                        String arr[] = line.split("=");
                        // lineNum == '#10'
                        String lineNum = arr[0].trim();
                        // lineVal == 'AXIS2_PLACEMENT_3D ( 'NONE', #135, #136, #137 )'
                        String lineVal = arr[1].substring(0, arr[1].length() - 1).trim();
                        linesMap.put(lineNum, lineVal);
                    }
                    line = br.readLine();
                }
            } finally {
                br.close();
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        AbstractEntity.linesMap = linesMap;
    }

    /**
     * Getter for the line-map.
     *
     * @return A hashmap containing the lines of the step-file using their line
     * numbers as keys.
     */
    public Map<String, String> getLinesMap() {
        return linesMap;
    }

    /**
     * Gets the CLOSED_SHELL entity of the STEP file or throws an exception if there are multiple such entities.
     * @return The hashmap key corresponding to the line that contains the CLOSED_SHELL information.
     */
    public String getClosedShellLineId() {
        int i = 0;
        for (Entry<String, String> e : linesMap.entrySet()) {
            if (e.getValue().startsWith(ClosedShell._CLOSED_SHELL)) {
                if (++i > 1) {
                    throw new RuntimeException("more than one CLOSED_SHELL");
                }
                return e.getKey();
            }
        }
        return null;
    }

}
