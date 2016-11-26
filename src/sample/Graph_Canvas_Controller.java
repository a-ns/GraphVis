package sample;

import Graph.Graph;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Graph_Canvas_Controller {



    @FXML
    private Button bAddOne;
    @FXML
    private Button bAddEdges;
    @FXML
    private Button clear;
    @FXML
    private Label messageBox;
    @FXML
    private ChoiceBox algSelect;
    private Graph graph;
    private String currentAlgorithm;
    private Stage stage;
    private Pane root;

    private boolean addVertMode = false;
    private boolean addEdgeMode = false;

    @FXML
    public void initialize () {
        this.graph = new Graph();
        currentAlgorithm = "";
        algSelect = new ChoiceBox(FXCollections.observableArrayList(
                "Depth First Search", "Breadth First Search", "Kruskal's MST")
        );
    }

    public void setParent(Pane root){
        this.root = root;
    }


    public void setHandlers () {
        this.bAddOne.setOnMouseClicked( e -> {
            //this.bAddOne.setText("Add Nodes");
            this.messageBox.setText("Add nodes by selecting a location on the graph and entering in a value.");
            this.addVertMode = true;
            this.addEdgeMode = false;
            /*
            else {
                this.bAddOne.setText("Not add Nodes Mode");
                this.addVertMode = !this.addVertMode;
            }
            */
        });
        this.clear.setOnMouseClicked( e-> {
            this.root.getChildren().removeAll(this.graph.getEdges());
            this.root.getChildren().removeAll(this.graph.getVertices());
            this.graph = new Graph();
            this.messageBox.setText("Graph cleared. Create a new graph by adding new nodes!");
        });
        this.root.setOnMouseClicked( (MouseEvent event) -> {
            if(addVertMode) {
                double xVal = event.getSceneX();
                double yVal = event.getSceneY();
                Circle circ = this.graph.addVertex(xVal, yVal, 10);
                circ.setOnMouseDragged(e -> {
                    circ.setCenterX(e.getSceneX());
                    circ.setCenterY(e.getSceneY());
                });
                this.root.getChildren().add(circ);
                System.out.println(this.graph);
            }
        });
        this.bAddEdges.setOnMouseClicked( (MouseEvent event) -> {
            addEdgeMode = true;
            addVertMode = false;
            addEdge();
        });
    }



    public void setbAddOne(JFXButton bAddOne) {
        this.bAddOne = bAddOne;
    }
    public void addEdge(){
        
        this.messageBox.setText("Select a starting node for the edge.");
        //wait for them to select a starting node
        this.messageBox.setText("Select an ending node for the edge.");
        //wait for them to select an ending node
        this.messageBox.setText("Edge created! To add more edges, select another starting node.");

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
