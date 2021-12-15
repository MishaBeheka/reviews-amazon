package boot.project.analyze_reviews_from_amazon.dto;

public class TextDto {
    private String text;

    public TextDto(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
