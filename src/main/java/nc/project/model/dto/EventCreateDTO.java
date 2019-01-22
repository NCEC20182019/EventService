package nc.project.model.dto;

import java.util.Date;

public class EventCreateDTO {
    private String title;
    private String description;
    private Date date_start;
    private Date date_end;
    private String source_uri;
    private String name_location;
    private String url_to_location;

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

    public String getName_location() {
        return name_location;
    }

    public void setName_location(String name_location) {
        this.name_location = name_location;
    }

    public String getUrl_to_location() {
        return url_to_location;
    }

    public void setUrl_to_location(String url_to_location) {
        this.url_to_location = url_to_location;
    }
}
