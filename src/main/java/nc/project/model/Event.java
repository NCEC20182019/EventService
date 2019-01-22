package nc.project.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "events")
public class Event {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "event_id")
  private int id;

  private String title;

  private String description;
  private Date date_start;
  private Date date_end;
  private String source_uri;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "location_id")
  private Location location;

  @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Localization> localizations;

  public Event() {
  }
  public Event(String title, String description, Date date_start,
               Date date_end, String source_uri){
      this.title = title;
      this.description = description;
      this.date_start = date_start;
      this.date_end = date_end;
      this.source_uri = source_uri;
      localizations = new ArrayList<>();
  }

  public void addLocalization(Localization localization){
    localization.setEvent(this);
    localizations.add(localization);
  }

  public void removeLocalization(Localization localization){
    localizations.remove(localization);
  }

  public int getId() {
      return id;
  }

  public String getTitle() {
      return title;
  }

  public void setTitle(String title) {
      this.title = title;
  }

  public String getDescription() {
      return description;
  }

  public void setDescription(String description) {
      this.description = description;
  }

  public Date getDate_start() {
      return date_start;
  }

  public void setDate_start(Date date_start) {
      this.date_start = date_start;
  }

  public Date getDate_end() {
      return date_end;
  }

  public void setDate_end(Date date_end) {
      this.date_end = date_end;
  }

  public String getSource_uri() {
      return source_uri;
  }

  public void setSource_uri(String source_uri) {
      this.source_uri = source_uri;
  }

  public Location getLocation() {
      return location;
  }

  public void setLocation(Location location) {
      this.location = location;
  }

  public List<Localization> getLocalizations() {
      return localizations;
  }

  public void setLocalizations(List<Localization> localizations) {
        this.localizations = localizations;
    }
}
