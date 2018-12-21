package nc.project.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Regions")
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "region_id")
    private int id;

    @Column(name = "region_name")
    private String name;

    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<City> cities;

    public void addCity(City city){
        city.setRegion(this);
        cities.add(city);
    }

    public void removeCity(City city){
        cities.remove(city);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }
}
