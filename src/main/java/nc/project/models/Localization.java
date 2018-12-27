package nc.project.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="Event_localization")
//@IdClass(LocalizationKey.class)
public class Localization implements Serializable{

    public Localization() {
    }

    public Localization(LocalizationKey lkey) {
        this.lkey = lkey;
    }
    @EmbeddedId
    private LocalizationKey lkey;
    /*@AttributeOverrides({
        @AttributeOverride(name = "lang_id",
        column = @Column(name = "lang_id")),
        @AttributeOverride(name = "event_id",
        column = @Column(name = "event_id"))
    })
    private String lang_id;*/

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id",insertable = false, updatable=false)
    private Event event;

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

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public LocalizationKey getLkey() {
        return lkey;
    }
}
