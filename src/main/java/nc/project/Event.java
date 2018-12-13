package nc.project;

public class Event {
    private int id;
    private String content;

    public Event(int id, String content) {
        this.id = id;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
