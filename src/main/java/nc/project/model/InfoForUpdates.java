package nc.project.model;

import java.util.Date;

public class InfoForUpdates {
    private int event_id;
    private String title;
    private Date date_start;
    private Date date_end;
    private String type;

    public InfoForUpdates() {
    }

    public InfoForUpdates(int event_id, String title, Date date_start, Date date_end, String type) {
        this.event_id = event_id;
        this.title = title;
        this.date_start = date_start;
        this.date_end = date_end;
        this.type = type;
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "InfoForUpdates{" +
                "event_id=" + event_id +
                ", title='" + title + '\'' +
                ", date_start=" + date_start +
                ", date_end=" + date_end +
                ", type='" + type + '\'' +
                '}';
    }
}
