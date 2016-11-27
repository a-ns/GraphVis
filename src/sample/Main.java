package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import Graph.*;
public class Main extends Application {

    private Graph_Canvas_Controller gcc;

    @Override
    public void start(Stage primaryStage) throws Exception{


        FXMLLoader graph_canvas_loader = new FXMLLoader();

        Pane root = graph_canvas_loader.load(getClass().getResource("Graph_Canvas.fxml").openStream());

        this.gcc = graph_canvas_loader.getController();
        this.gcc.setParent(root);
        this.gcc.setHandlers();
        this.gcc.setupChoice();
        primaryStage.setTitle("Graph Algorithm Visualization");
        primaryStage.setScene(new Scene(root, 900, 600));

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}