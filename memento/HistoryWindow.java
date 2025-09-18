import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HistoryWindow {
    
    public HistoryWindow(HistoryManager historyManager) {
        Stage stage = new Stage();
        stage.setTitle("History");

        ListView<String> listView = new ListView<>();
        
        for (int i = 0; i < historyManager.getHistory().size(); i++) {
            Memento memento = historyManager.getHistory().get(i);
            String prefix = (i == historyManager.getCurrentPosition()) ? "► " : "  ";
            listView.getItems().add(prefix + memento.toString());
        }

        VBox root = new VBox(listView);
        Scene scene = new Scene(root, 400, 300);
        
        stage.setScene(scene);
        stage.show();
    }
}