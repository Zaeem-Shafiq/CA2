package jsonMappers;

/**
 *
 * @author Joacim
 */
public class Hobby {
    String description;

    public Hobby(entity.Hobby hobby) {
        this.description = hobby.getDescription();
    }
    
    
}
