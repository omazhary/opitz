package entities;

import java.util.Map;

public abstract class AbstractEntity {

    private String id;

    public static Map<String, String> linesMap;

    /**
     * Empty constructor.
     */
    public AbstractEntity() {
        //
    }

    /**
     * Parameterized constructor for the AbstractEntity which initializes it
     * with the given ID.
     *
     * @param entityId The corresponding entity key in the STEP file hashmap.
     */
    public AbstractEntity(String entityId) {
        if (!linesMap.get(entityId).startsWith(getEntityName())) {
            throw new RuntimeException("wrong entity name");
        }
        id = entityId;
    }

    /**
     * Getter for the key.
     *
     * @return String containing the entity's ID.
     */
    public String getLineId() {
        return id;
    }

    public abstract String getEntityName();

}
