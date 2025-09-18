import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {

    private Model model = new Model();
    private HistoryManager historyManager = new HistoryManager(model);
    private Rectangle colorDisplay;
    private Label colorLabel;

    @Override
    public void start(Stage stage) {
        CheckBox checkBox = new CheckBox("Click Me!");
        checkBox.selectedProperty().bindBidirectional(model.checkedProperty());
        checkBox.selectedProperty().addListener((obs, oldVal, newVal) -> {
            historyManager.saveState();
        });

        colorDisplay = new Rectangle(100, 50);
        colorLabel = new Label();
        updateColorDisplay();

        model.colorProperty().addListener((obs, oldVal, newVal) -> updateColorDisplay());

        Button changeColorBtn = new Button("Change Color");
        changeColorBtn.setOnAction(e -> {
            model.changeColor();
            historyManager.saveState();
        });

        Button openHistoryBtn = new Button("Show History");
        openHistoryBtn.setOnAction(e -> new HistoryWindow(historyManager));

        Button undoBtn = new Button("Undo (Ctrl+Z)");
        undoBtn.setOnAction(e -> historyManager.undo());

        Button redoBtn = new Button("Redo (Ctrl+Y)");
        redoBtn.setOnAction(e -> historyManager.redo());

        VBox root = new VBox(10, 
            checkBox, 
            colorLabel,
            colorDisplay, 
            changeColorBtn, 
            undoBtn,
            redoBtn,
            openHistoryBtn
        );
        root.setStyle("-fx-padding: 20;");
        
        Scene scene = new Scene(root, 300, 300);

        scene.getAccelerators().put(
                new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN),
                historyManager::undo
        );
        scene.getAccelerators().put(
                new KeyCodeCombination(KeyCode.Y, KeyCombination.CONTROL_DOWN),
                historyManager::redo
        );

        stage.setTitle("Memento Pattern Demo");
        stage.setScene(scene);
        stage.show();

        historyManager.saveState();
    }

    private void updateColorDisplay() {
        String colorName = model.getColor();
        colorLabel.setText("Current Color: " + colorName.toUpperCase());
        
        Color color;
        switch (colorName.toLowerCase()) {
            case "red": color = Color.RED; break;
            case "blue": color = Color.BLUE; break;
            case "green": color = Color.GREEN; break;
            case "yellow": color = Color.YELLOW; break;
            case "purple": color = Color.PURPLE; break;
            case "orange": color = Color.ORANGE; break;
            default: color = Color.GRAY; break;
        }
        
        colorDisplay.setFill(color);
        colorDisplay.setStroke(Color.BLACK);
        colorDisplay.setStrokeWidth(2);
    }

    public static void main(String[] args) {
        launch(args);
    }
}