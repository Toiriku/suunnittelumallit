import java.util.ArrayList;
import java.util.List;

public class HistoryManager {
    private final Model model;
    private final List<Memento> history = new ArrayList<>();
    private int currentPosition = -1;

    public HistoryManager(Model model) {
        this.model = model;
    }

    public void saveState() {
        while (history.size() > currentPosition + 1) {
            history.remove(history.size() - 1);
        }
        
        history.add(model.createMemento());
        currentPosition++;
    }

    public void undo() {
        if (canUndo()) {
            currentPosition--;
            model.restoreFromMemento(history.get(currentPosition));
        }
    }

    public void redo() {
        if (canRedo()) {
            currentPosition++;
            model.restoreFromMemento(history.get(currentPosition));
        }
    }

    public boolean canUndo() {
        return currentPosition > 0;
    }

    public boolean canRedo() {
        return currentPosition < history.size() - 1;
    }

    public List<Memento> getHistory() {
        return new ArrayList<>(history);
    }

    public int getCurrentPosition() {
        return currentPosition;
    }
}