package nc.project.model.dto;

import nc.project.model.Event;

import java.util.Date;

public class EventUpdateGetDTO {
    private int id;
    private Event event;

    private String url_to_tweet;
    private String text_from_tweet;
    private String url_to_pic_from_tweet;
    private Date last_update_date;

    public EventUpdateGetDTO(int id, Event event, String url_to_tweet, String text_from_tweet, String url_to_pic_from_tweet, Date last_update_date) {
        this.id = id;
        this.event = event;
        this.url_to_tweet = url_to_tweet;
        this.text_from_tweet = text_from_tweet;
        this.url_to_pic_from_tweet = url_to_pic_from_tweet;
        this.last_update_date = last_update_date;
    }

    public EventUpdateGetDTO() {
    }

    @Override
    public String toString() {
        return "EventUpdateGetDTO{" +
                "id=" + id +
                ", event=" + event +
                ", url_to_tweet='" + url_to_tweet + '\'' +
                ", text_from_tweet='" + text_from_tweet + '\'' +
                ", url_to_pic_from_tweet='" + url_to_pic_from_tweet + '\'' +
                ", last_update_date=" + last_update_date +
                '}';
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
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
}
