package nc.project.model.dto;

import lombok.Data;
import nc.project.model.TriggerFlag;

@Data
public class TriggerDTO {
    private int eventId;
    private TriggerFlag triggerFlag;
    private String type;
    private double latitude;
    private double longitude;

    public TriggerDTO(int eventId, TriggerFlag triggerFlag, String type, double latitude, double longitude) {
        this.eventId = eventId;
        this.triggerFlag = triggerFlag;
        this.type = type;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
