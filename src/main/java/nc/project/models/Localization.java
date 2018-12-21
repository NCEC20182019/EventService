package nc.project.models;

import javax.persistence.*;

@Entity
@Table(name="Event_localization")
public class Localization {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Id
    private int event_id;

    private String translated_title;
    private String translated_description;

    public String getTranslated_title() {
        return translated_title;
    }

    public void setTranslated_title(String translated_title) {
        this.translated_title = translated_title;
    }

    public String getTranslated_description() {
        return translated_description;
    }

    public void setTranslated_description(String translated_description) {
        this.translated_description = translated_description;
    }
}
