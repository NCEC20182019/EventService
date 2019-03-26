package nc.project.model.dto;

import lombok.Data;
import nc.project.model.TriggerFlags;

@Data
public class TriggerDTO {
    private int eventId;
    private TriggerFlags triggerFlag;
    private String type;
    private double latitude;
    private double longitude;

    public TriggerDTO(int eventId, TriggerFlags triggerFlag, String type, double latitude, double longitude) {
        this.eventId = eventId;
        this.triggerFlag = triggerFlag;
        this.type = type;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
