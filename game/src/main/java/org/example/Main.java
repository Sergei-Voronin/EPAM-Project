package org.example;

import org.example.configuration.Config;
import javafx.application.Application;
import javafx.stage.Stage;
import org.example.view.UserView;

/**
 @author Sergei_Voronin
 This is main class.
 */
public class Main extends Application{
    /**
     Method main is start point of the program.
     @param  args sets the length and height of the playing field, and the number of animation cycles.The values are placed in an object of the Configuration class.
     */
    public static void main(String[] args) {
        Config config = Config.getConfig(args);
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        UserView view = new UserView();
        view.start(stage);
    }
}