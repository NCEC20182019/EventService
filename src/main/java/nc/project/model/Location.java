package nc.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "locations")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="location_id")
    private int id;

    private String name;
    private double latitude;
    private double longitude;

    private String street_address;
    private String postal_code;

    @JsonIgnore
    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Event> events;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private City city;

    public Location(){
    }

    public Location(String name, double latitude, double longitude, String street_address,
                    String postal_code){
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.street_address = street_address;
        this.postal_code = postal_code;
        events = new ArrayList<>();
    }

    public Location(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Location(String name){
        this.name = name;
        this.latitude = 0;
        this.longitude = 0;
        this.street_address = null;
        this.postal_code = null;
        events = new ArrayList<>();
    }

    public void addEvent(Event event){
        event.setLocation(this);
        events.add(event);
    }

    public void removeEvent(Event event){
        events.remove(event);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Double.compare(location.latitude, latitude) == 0 &&
                Double.compare(location.longitude, longitude) == 0 &&
                Objects.equals(name, location.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, latitude, longitude);
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", street_address='" + street_address + '\'' +
                ", postal_code='" + postal_code + '\'' +
                ", city=" + city +
                '}';
    }
}