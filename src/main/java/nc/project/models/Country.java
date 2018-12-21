package nc.project.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Counties")
public class Country {
    @Id
    @Column(name = "country_id")
    private String id;

    @Column(name = "country_name")
    private String name;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Location> locations;

    public Country(){
    }

    public Country(String name){
        this.name = name;
    }

    public void addLocation(Location location){
        location.setCountry(this);
        locations.add(location);
    }

    public void removeLocation(Location location){
        locations.remove(location);
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

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }
}
