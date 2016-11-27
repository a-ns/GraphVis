package sample;

import Algorithms.DepthFirstSearch;
import Graph.*;
import Graph.Vertex;
import com.jfoenix.controls.JFXButton;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.Optional;

public class Graph_Canvas_Controller {



    @FXML
    private Button bAddOne;
    @FXML
    private Button bAddEdges;
    @FXML
    private Button clear;
    @FXML
    private Button bRunAlg;
    @FXML
    private Label messageBox;
    @FXML
    private ChoiceBox algSelect;
    private Graph graph;
    final String[] algorithms = new String[]{"dfs", "bfs", "kruskals"};
    private String currentAlgorithm;
    private Stage stage;
    private Pane root;

    private boolean addVertMode = false;
    private boolean addEdgeMode = false;

    private int nodeNum = 1;

    private boolean firstNodeSelected = false;
    private String firstNode = "";

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
        this.algSelect.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>(){
            public void changed(ObservableValue ov, Number value, Number new_value){
                currentAlgorithm = algorithms[new_value.intValue()];
            }
        });
        this.bRunAlg.setOnMouseClicked(e-> {
            //run the currentAlgorithm
            if(currentAlgorithm.equals("dfs")){
                //run dfs
                //ASK TO SPECIFY START NODE, then pass in that vertex to DFS
                Vertex start =
                DepthFirstSearch dfs = new DepthFirstSearch(this.graph, start);
                //for loop for showing the states stored in dfs as a list of graphs.
                //draw graph in the for loop for the length of the list
                //if they want to step, just wait for next mouse click on next arrow to show the step. do it in a while loop.
                //while not reached final step, show current graph and wait for a mouseclick on either "next" or "play to end" or something
            }
            else if(currentAlgorithm.equals("bfs")){
                //run bfs
            }
            else if(currentAlgorithm.equals("kruskals")){
                //run kruskals
            }
            else if(currentAlgorithm.equals("dijkstras")){
                //run dijkstras
            }
        });
        this.root.setOnMouseClicked( (MouseEvent event) -> {
            if(addVertMode) {
                double xVal = event.getSceneX();
                double yVal = event.getSceneY();

                Point2D clickPoint = new Point2D(xVal, yVal);

                String closestCircle = "";
                double closestDistance = -1;

                for(int i = 0; i < nodeNum; i++){
                    Vertex v = this.graph.getVertex(i+1);

                    if(v != null){
                        Point2D circlePoint = new Point2D(v.getCenterX(), v.getCenterY());

                        double distance = clickPoint.distance(circlePoint);
                        if(distance < 12.0 && (distance < closestDistance || closestDistance == -1)){
                            closestCircle = v.getValue();
                            closestDistance = distance;
                        }
                    }

                }

                System.out.println(closestDistance + "");

                if(closestDistance == -1) {
                    TextInputDialog dialog = new TextInputDialog("");
                    dialog.setTitle("Vertex Name");
                    //dialog.setHeaderText("Look, a Text Input Dialog");
                    dialog.setContentText("Please enter the vertex name:");

                    // Traditional way to get the response value.
                    String result = dialog.showAndWait().orElse("n/a");
                    if (result.matches("^[a-zA-Z0-9]*$") && this.graph.getVertex(result) == null) {
                        Vertex circ = this.graph.addVertex(xVal, yVal, 10);
                        circ.setValue(result);
                        circ.setOnMouseDragged(e -> {
                            circ.setCenterX(e.getSceneX());
                            circ.setCenterY(e.getSceneY());
                        });
                        this.root.getChildren().add(circ);
                        nodeNum++;
                        System.out.println(this.graph);

                    }
                }
            }
            else if(addEdgeMode) {
                double xVal = event.getSceneX();
                double yVal = event.getSceneY();

                Point2D clickPoint = new Point2D(xVal, yVal);

                String closestCircle = "";
                double closestDistance = -1;

                for(int i = 0; i < nodeNum; i++){
                    Vertex v = this.graph.getVertex(i+1);

                    if(v != null){
                        Point2D circlePoint = new Point2D(v.getCenterX(), v.getCenterY());

                        double distance = clickPoint.distance(circlePoint);
                        if(distance < 17.0 && (distance < closestDistance || closestDistance == -1)){
                            closestCircle = v.getValue();
                            closestDistance = distance;
                        }
                    }

                }

                if(closestCircle.length() > 0 && !firstNodeSelected){
                    firstNode = closestCircle;
                    firstNodeSelected = true;
                    this.messageBox.setText("Select an ending node for the edge.");
                }
                else if(closestCircle.length() > 0){
                    TextInputDialog dialog = new TextInputDialog("1");
                    dialog.setTitle("Edge Weight");
                    //dialog.setHeaderText("Look, a Text Input Dialog");
                    dialog.setContentText("Please the edge weight:");

                    // Traditional way to get the response value.
                    String result = dialog.showAndWait().orElse("n/a");
                    if (result.matches("^[1-9]\\d*$")){
                        Edge edge = this.graph.addEdge(firstNode, closestCircle, true);

                        if (edge != null)
                            this.root.getChildren().add(edge);
                        firstNodeSelected = false;
                        firstNode = "";
                        this.messageBox.setText("Edge created! To add more edges, select another starting node.");
                        if (edge == null)
                            this.messageBox.setText("That edge already exists, select another starting node.");
                    }
                    else{
                        firstNodeSelected = false;
                        firstNode = "";
                        this.messageBox.setText("That edge was not created, select another starting node.");
                    }
                }
                System.out.println(this.graph);
            }
        });
        this.bAddEdges.setOnMouseClicked( (MouseEvent event) -> {
            addEdgeMode = true;
            addVertMode = false;
            firstNodeSelected = false;
            firstNode = "";

            this.messageBox.setText("Select a starting node for the edge.");

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

    public void setupChoice() {
        algSelect.setLayoutX(14);
        algSelect.setLayoutY(287);
        algSelect.setMinWidth(108);
        algSelect.setPrefWidth(108);
        root.getChildren().add(algSelect);
    }
}
