package quctrun.trunn2004.cuoiky;

public class Note {
    private String title;
    private String content;

    public Note() {} // Bắt buộc cho Firestore

    public Note(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() { return title; }
    public String getContent() { return content; }
}

