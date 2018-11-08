package com.evgkit.pscrud.domain;

public class Chat {
    private long id;
    private boolean isEnabled;
    private String title;
    private String type;

    public Chat() {
    }

    public Chat(long id, boolean isEnabled, String title, String type) {
        this.id = id;
        this.isEnabled = isEnabled;
        this.title = title;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean getEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "id=" + id +
                ", isEnabled=" + isEnabled +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
