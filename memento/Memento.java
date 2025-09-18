import java.time.LocalDateTime;

public class Memento {
    private final String color;
    private final boolean checked;
    private final String timestamp;

    public Memento(String color, boolean checked) {
        this.color = color;
        this.checked = checked;
        this.timestamp = LocalDateTime.now().toString();
    }

    public String getColor() {
        return color;
    }

    public boolean isChecked() {
        return checked;
    }

    public String getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return timestamp + " | Color: " + color + " | Checked: " + checked;
    }
}
