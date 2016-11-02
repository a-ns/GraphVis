package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Graph.*;
public class Main extends Application {

    private Graph_Canvas_Controller gcc;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Graph<Integer> graph = new Graph();
        graph.addVertex(5);
        graph.addVertex(25);
        graph.addVertex(16);
        graph.addEdge(5, 25, true);
        graph.addEdge(5, 16);
        graph.addVertex(100);
        graph.addEdge(100, 5);
        graph.addEdge(100, 16);
        graph.addEdge(25, 16, true);
        System.out.println(graph);
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
