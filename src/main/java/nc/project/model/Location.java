package nc.project.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
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
    private String url;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Event> events;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private City city;

    public Location(){
    }

    public Location(String name, double latitude, double longitude, String street_address,
                    String postal_code,String url){
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.street_address = street_address;
        this.postal_code = postal_code;
        this.url = url;
        events = new ArrayList<>();
    }
    public Location(String name, String url){
        this.name = name;
        this.latitude = 0;
        this.longitude = 0;
        this.street_address = null;
        this.postal_code = null;
        this.url = url;
        events = new ArrayList<>();
    }

    public void addEvent(Event event){
        event.setLocation(this);
        events.add(event);
    }

    public void removeEvent(Event event){
        events.remove(event);
    }

    public int getId() {
        return id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getStreet_address() {
        return street_address;
    }

    public void setStreet_address(String street_address) {
        this.street_address = street_address;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Objects.equals(name, location.name) &&
                Objects.equals(url, location.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, url);
    }
}