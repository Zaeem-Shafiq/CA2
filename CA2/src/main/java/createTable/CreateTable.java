package createTable;

import javax.persistence.Persistence;

public class CreateTable {

    public static void main(String[] args) {
        Persistence.generateSchema("PU", null);
    }

}
