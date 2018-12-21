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


}
