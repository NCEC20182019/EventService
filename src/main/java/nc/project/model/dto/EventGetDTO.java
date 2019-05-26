package nc.project.model.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class EventGetDTO {

    private int id;
    private Integer owner_id;

    private String title;

    private String description;
    private Date date_start;
    private Date date_end;
    private String source_uri;
    private String typeOfEvent;
    private String image_url;

    private LocationGetDTO location;

    private List<LocalizationGetDTO> localizations;

    public EventGetDTO(){}

    public EventGetDTO(int id, String title, String description, Date date_start, Date date_end, String source_uri,
                       String typeOfEvent, String image_url, int owner_id) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date_start = date_start;
        this.date_end = date_end;
        this.source_uri = source_uri;
        this.typeOfEvent = typeOfEvent;
        this.image_url = image_url;
        this.owner_id = owner_id;
        this.location = new LocationGetDTO();
        this.localizations = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
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

    public LocationGetDTO getLocation() {
        return location;
    }

    public void setLocation(LocationGetDTO location) {
        this.location = location;
    }

    public List<LocalizationGetDTO> getLocalizations() {
        return localizations;
    }

    public void setLocalizations(List<LocalizationGetDTO> localizations) {
        this.localizations = localizations;
    }

    public String getTypeOfEvent() {
        return typeOfEvent;
    }

    public void setTypeOfEvent(String typeOfEvent) {
        this.typeOfEvent = typeOfEvent;
    }

    public Integer getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(Integer owner_id) {
        this.owner_id = owner_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventGetDTO that = (EventGetDTO) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "EventGetDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", owner_id='" + owner_id + '\'' +
                ", description='" + description + '\'' +
                ", date_start=" + date_start +
                ", date_end=" + date_end +
                ", source_uri='" + source_uri + '\'' +
                ", typeOfEvent='" + typeOfEvent + '\'' +
                ", image_url='" + image_url + '\'' +
                ", location=" + location +
                ", localizations=" + localizations +
                '}';
    }
}
