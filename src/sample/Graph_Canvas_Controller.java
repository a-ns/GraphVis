package sample;

import Graph.*;
import Graph.Vertex;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
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

    private int nodeNum = 1;

    private boolean firstNodeSelected = false;
    private int firstNode = -1;

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
                Vertex circ = this.graph.addVertex(xVal, yVal, 10);
                circ.setValue("" + nodeNum++);
                circ.setOnMouseDragged(e -> {
                    circ.setCenterX(e.getSceneX());
                    circ.setCenterY(e.getSceneY());
                });
                this.root.getChildren().add(circ);
                System.out.println(this.graph);
            }
            else if(addEdgeMode) {
                double xVal = event.getSceneX();
                double yVal = event.getSceneY();

                Point2D clickPoint = new Point2D(xVal, yVal);

                int closestCircle = -1;
                double closestDistance = -1;

                for(int i = 0; i < nodeNum; i++){
                    Vertex v = this.graph.getVertex(i+1);

                    if(v != null){
                        Point2D circlePoint = new Point2D(v.getCenterX(), v.getCenterY());

                        double distance = clickPoint.distance(circlePoint);
                        if(distance < 17.0 && (distance < closestDistance || closestDistance == -1)){
                            closestCircle = i+1;
                            closestDistance = distance;
                        }
                    }

                }

                if(closestCircle != -1 && !firstNodeSelected){
                    firstNode = closestCircle;
                    firstNodeSelected = true;
                }
                else if(closestCircle != -1){
                    Edge edge = this.graph.addEdge(firstNode + "", closestCircle + "", true);
                    this.root.getChildren().add(edge);
                }
                System.out.println(this.graph);
            }
        });
        this.bAddEdges.setOnMouseClicked( (MouseEvent event) -> {
            addEdgeMode = true;
            addVertMode = false;
            firstNodeSelected = false;
            firstNode = -1;
            //addEdge();
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
