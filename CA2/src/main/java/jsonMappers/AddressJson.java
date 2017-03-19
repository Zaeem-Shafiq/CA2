package jsonMappers;

import entity.Address;

public class AddressJson {
    public String street, additionalInfo;
    public AddressJson(Address address) {
        this.street = address.getStreet();
        this.additionalInfo = address.getAdditionalInfo();
    }
}
