package nc.project.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class EventCreateDTO {
    private Integer owner_id;
    private String title;
    private String description;
    private Date date_start;
    private Date date_end;
    private String source_uri;
    private String type;
    private String image_url;

    private String name_location;
    private double latitude;
    private double longitude;
}
