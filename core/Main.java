package core;

import controller.MainMenuController;
import javafx.application.Application;
import javafx.stage.Stage;
import model.database.PatisserieDatabase;
import view.MainMenu;

import java.io.File;

public class Main extends Application {
    public PatisserieDatabase patisserieDatabase;

    public void stop(){
        PatisserieDatabase.save();
    }


    /**
     *
     * @param primaryStage Starts whole menu
     * @throws Exception
     */
    public void start(Stage primaryStage) throws Exception {
        File f = new File(PatisserieDatabase.getDatabaseFileName());
        if(f.exists() && f.length() != 0) {
            PatisserieDatabase.load();
        }else{
            f.createNewFile();
        }
        MainMenu mainMenu = new MainMenu();
        MainMenuController mainMenuController = new MainMenuController(mainMenu);
        primaryStage.setMinWidth(250);
        primaryStage.setMinHeight(200);
        primaryStage.setTitle("Main menu");
        primaryStage.setScene(mainMenu.getScene());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
