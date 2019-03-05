package nc.project.model.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventGetDTO {

    private int id;

    private String title;

    private String description;
    private Date date_start;
    private Date date_end;
    private String source_uri;
    private String type;

    private LocationGetDTO location;

    private List<LocalizationGetDTO> localizations;

    public EventGetDTO(){}

    public EventGetDTO(int id, String title, String description, Date date_start, Date date_end, String source_uri,
                       String type) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date_start = date_start;
        this.date_end = date_end;
        this.source_uri = source_uri;
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        int localizationsCount = getLocalizations() != null ? getLocalizations().size():0; //костыль какой-то
        return "EventGetDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", date_start=" + date_start +
                ", date_end=" + date_end +
                ", source_uri='" + source_uri + '\'' +
                ", type='" + type + '\'' +
                ", location=" + location.getName() +
                ", localizations=" + localizationsCount +
                '}';
    }
}
