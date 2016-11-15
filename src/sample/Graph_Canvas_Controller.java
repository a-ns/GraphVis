package sample;

import Graph.Graph;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Graph_Canvas_Controller {



    @FXML
    private JFXButton bAddOne;
    private Graph graph;
    private Stage stage;
    private Pane root;

    private boolean addMode = false;

    @FXML
    public void initialize () {
        this.graph = new Graph();
    }

    public void setParent(Pane root){
        this.root = root;
    }
    public Button getbAddOne() {
        return bAddOne;
    }


    public void setHandlers (Pane root) {
        this.bAddOne.setOnMouseClicked( e -> {
            if (!addMode){
                this.bAddOne.setText("Add Nodes Mode");
                this.addMode = !this.addMode;
            }
            else {
                this.bAddOne.setText("Not add Nodes Mode");
                this.addMode = !this.addMode;
            }
        });
        root.setOnMouseClicked( (MouseEvent event) -> {
            if(addMode) {
                double xVal = event.getSceneX();
                double yVal = event.getSceneY();
                Circle circ = this.graph.addVertex(xVal, yVal, 10);
                circ.setOnMouseDragged(e -> {
                    circ.setCenterX(e.getSceneX());
                    circ.setCenterY(e.getSceneY());
                });
                root.getChildren().add(circ);
                System.out.println(this.graph);
            }
        });
    }



    public void setbAddOne(JFXButton bAddOne) {
        this.bAddOne = bAddOne;
    }

    public void setGraph (Graph graph) {
        this.graph = graph;
    }
    public Graph getGraph() {
        return this.graph;
    }
    public void setStage(Stage stage){
        this.stage = stage;
    }
    public Stage getStage() {
        return this.stage;
    }
}
