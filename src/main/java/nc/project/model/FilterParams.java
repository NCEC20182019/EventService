package nc.project.model;

import lombok.Data;

import java.util.Date;
import java.util.List;
@Data
public class FilterParams {
    @Data
    public class Area {
        private float longitude;
        private float latitude;
      private float radius;
    }
    private Date dateFrom;
    private Date dateTo;
    private Area area;
    private List<String> types;

    public boolean isAreaFilter(){
        return (area.getLatitude() != 0 && area.getLongitude() != 0) && area.getRadius() != 0;
    }

    public boolean isDateFromFilter() {
        return dateFrom != null;
    }

    public boolean isDateToFilter() {
        return dateTo != null;
    }
    public boolean isDateFilter() {
      return dateFrom != null && dateTo != null;
    }
    public boolean isTypeFilter() {
        return types != null && types.size() > 0;
    }
}

