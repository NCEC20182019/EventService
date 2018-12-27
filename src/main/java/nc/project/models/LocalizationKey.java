package nc.project.models;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class LocalizationKey implements Serializable {
  private String lang_id;
  private int event_id;

  public LocalizationKey() {
  }

  public LocalizationKey(String lang_id, int event_id) {
    this.lang_id = lang_id;
    this.event_id = event_id;
  }

  public String getLang_id() {
    return lang_id;
  }

  public void setLang_id(String lang_id) {
    this.lang_id = lang_id;
  }

  public int getEvent_id() {
    return event_id;
  }

  public void setEvent_id(int event_id) {
    this.event_id = event_id;
  }
}
