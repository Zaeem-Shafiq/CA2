package jsonMappers;

import entity.Hobby;

public class HobbyJson {
    public String description,name;
    public HobbyJson(Hobby hobby) {
        this.name = hobby.getName();
        this.description = hobby.getDescription();
    }   
}
