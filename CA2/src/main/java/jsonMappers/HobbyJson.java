package jsonMappers;

import entity.Hobby;

/**
 *
 * @author Joacim
 */
public class HobbyJson {
    public String description,name;
    public HobbyJson(Hobby hobby) {
        this.name = hobby.getName();
        this.description = hobby.getDescription();
    }   
}
