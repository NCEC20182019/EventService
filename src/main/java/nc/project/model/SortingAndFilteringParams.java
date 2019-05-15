package nc.project.model;

import lombok.Data;

import java.util.Date;
import java.util.List;
@Data
public class SortingAndFilteringParams {
  @Data
  public class Filter {
    @Data
    public class Area {
      private Location center;
      private float radius;
    }
    private Date dateFrom;
    private Date dateTo;
    private Area area;
    private List<String> types;

    public boolean isAreaFilter(){
      return area.getCenter() != null && area.getRadius() != 0;
    }
    public boolean isDateFilter() {
      return dateFrom != null && dateTo != null;
    }
    public boolean isTypeFilter() {
      return types.size() > 0;
    }

  }
  private int sort;
  private Filter filter;

  public boolean isFilter(){
    return filter != null;
  }
}

