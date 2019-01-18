package nc.project.models;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    LocalizationKey that = (LocalizationKey) o;
    return event_id == that.event_id &&
            Objects.equals(lang_id, that.lang_id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(lang_id, event_id);
  }
}
