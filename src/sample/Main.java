package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.graphicGraph.GraphicEdge;

public class Main extends Application {

    private Graph_Canvas_Controller gcc;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader graph_canvas_loader = new FXMLLoader();

        Parent root = graph_canvas_loader.load(getClass().getResource("Graph_Canvas.fxml").openStream());

        this.gcc = graph_canvas_loader.getController();
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 600, 325));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
