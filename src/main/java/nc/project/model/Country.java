package nc.project.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "counties")
public class Country {
    @Id
    @Column(name = "country_id")
    private String id;

    @Column(name = "country_name")
    private String name;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Region> regions;

    public Country(){
    }

    public Country(String name){
        this.name = name;
    }

    public void addLocation(Region region){
        region.setCountry(this);
        regions.add(region);
    }

    public void removeLocation(Region region){regions.remove(region);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) { // какие-то ограничения наложить наверно
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Region> getRegions() {
        return regions;
    }

    public void setRegions(List<Region> regions) {
        this.regions = regions;
    }
}
