package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class OrderMaker {

    private Scene scene;
    private ProgressBar progressBar = new ProgressBar(0);
    private Button workButton = new Button("Work!");
    private Text text = new Text();

    public Button getWorkButton() {
        return workButton;
    }

    public Scene getScene() {
        return scene;
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public Text getText() {
        return text;
    }

    /**
     * Small window to display working progress
     */
    public OrderMaker(){
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        text.setFont(new Font(17));
        vBox.getChildren().addAll(text, workButton, progressBar);
        scene = new Scene(vBox);
    }
}
