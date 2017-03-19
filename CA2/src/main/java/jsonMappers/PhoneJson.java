package jsonMappers;

import entity.Phone;

public class PhoneJson {
    public String description, number;
    public PhoneJson(Phone phone) {
        this.description = phone.getDescription();
        this.number = phone.getNumber();
    }
}
