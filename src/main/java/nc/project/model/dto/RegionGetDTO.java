package nc.project.model.dto;

import nc.project.model.Country;

public class RegionGetDTO {
    private int id;
    private String name;
    private CountryGetDTO country;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CountryGetDTO getCountry() {
        return country;
    }

    public void setCountry(CountryGetDTO country) {
        this.country = country;
    }
}
