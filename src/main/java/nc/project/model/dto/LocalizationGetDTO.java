package nc.project.model.dto;

import nc.project.model.LocalizationKey;

public class LocalizationGetDTO {
    private LocalizationKey lkey;

    private String translated_title;
    private String translated_description;

    public LocalizationKey getLkey() {
        return lkey;
    }

    public void setLkey(LocalizationKey lkey) {
        this.lkey = lkey;
    }

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
