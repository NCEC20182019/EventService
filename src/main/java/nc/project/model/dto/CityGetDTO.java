package nc.project.model.dto;

public class CityGetDTO {
    private int id;
    private String name;
    private RegionGetDTO region;

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

    public RegionGetDTO getRegion() {
        return region;
    }

    public void setRegion(RegionGetDTO region) {
        this.region = region;
    }
}
