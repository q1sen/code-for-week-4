package klaeffer.service;

public class KlaeffDetail{
    private String name;
    private String text;

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

    public Long getUserId() {
        return userId;
    }

    private Long userId;

    public KlaeffDetail(String name, String text, Long userId) {
        this.name = name;
        this.text = text;
        this.userId = userId;
    }
}
