package nc.project.model.dto;

import nc.project.model.Event;

import java.util.Date;

public class EventUpdateCreateDTO {
    private int event_id;

    private String url_to_tweet;
    private String text_from_tweet;
    private String url_to_pic_from_tweet;
    private Date last_update_date;

    public EventUpdateCreateDTO() {
    }

    public EventUpdateCreateDTO(int id, int event_id, String url_to_tweet, String text_from_tweet
            , String url_to_pic_from_tweet, Date last_update_date) {

        this.event_id = event_id;
        this.url_to_tweet = url_to_tweet;
        this.text_from_tweet = text_from_tweet;
        this.url_to_pic_from_tweet = url_to_pic_from_tweet;
        this.last_update_date = last_update_date;
    }


    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public String getUrl_to_tweet() {
        return url_to_tweet;
    }

    public void setUrl_to_tweet(String url_to_tweet) {
        this.url_to_tweet = url_to_tweet;
    }

    public String getText_from_tweet() {
        return text_from_tweet;
    }

    public void setText_from_tweet(String text_from_tweet) {
        this.text_from_tweet = text_from_tweet;
    }

    public String getUrl_to_pic_from_tweet() {
        return url_to_pic_from_tweet;
    }

    public void setUrl_to_pic_from_tweet(String url_to_pic_from_tweet) {
        this.url_to_pic_from_tweet = url_to_pic_from_tweet;
    }

    public Date getLast_update_date() {
        return last_update_date;
    }

    public void setLast_update_date(Date last_update_date) {
        this.last_update_date = last_update_date;
    }

    @Override
    public String toString() {
        return "EventUpdateCreateDTO{" +

                ", event_id=" + event_id +
                ", url_to_tweet='" + url_to_tweet + '\'' +
                ", text_from_tweet='" + text_from_tweet + '\'' +
                ", url_to_pic_from_tweet='" + url_to_pic_from_tweet + '\'' +
                ", last_update_date=" + last_update_date +
                '}';
    }



}
