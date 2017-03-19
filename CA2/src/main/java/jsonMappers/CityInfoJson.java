package jsonMappers;

import entity.CityInfo;

public class CityInfoJson {

    public String city;
    public Integer zip;

    public CityInfoJson(CityInfo cityInfo) {
        this.city = cityInfo.getCity();
        this.zip = cityInfo.getZip();
    }

}
