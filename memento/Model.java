import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Model {
    private final StringProperty color = new SimpleStringProperty("red");
    private final BooleanProperty checked = new SimpleBooleanProperty(false);
    
    private final String[] colors = {"red", "blue", "green", "yellow", "purple", "orange"};
    private int colorIndex = 0;

    public String getColor() {
        return color.get();
    }

    public void setColor(String color) {
        this.color.set(color);
    }

    public StringProperty colorProperty() {
        return color;
    }

    public boolean isChecked() {
        return checked.get();
    }

    public void setChecked(boolean checked) {
        this.checked.set(checked);
    }

    public BooleanProperty checkedProperty() {
        return checked;
    }

    public void changeColor() {
        colorIndex = (colorIndex + 1) % colors.length;
        setColor(colors[colorIndex]);
    }

    public Memento createMemento() {
        return new Memento(getColor(), isChecked());
    }

    public void restoreFromMemento(Memento memento) {
        setColor(memento.getColor());
        setChecked(memento.isChecked());
        
        for (int i = 0; i < colors.length; i++) {
            if (colors[i].equals(memento.getColor())) {
                colorIndex = i;
                break;
            }
        }
    }
}