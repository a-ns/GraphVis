package sample;

import Graph.Graph;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Graph_Canvas_Controller {



    @FXML
    private JFXTextArea count;
    @FXML
    private JFXButton bAddOne;
    private Graph graph;
    private Stage stage;
    private Parent root;
    @FXML
    public void initialize () {
        this.bAddOne.setOnMouseClicked( (MouseEvent event) -> {
            Integer currentCount = Integer.parseInt(this.count.getText()) + 1;
            this.setCount(String.valueOf(currentCount));
        });
    }
    public void setParent(Parent root){
        this.root = root;
    }
    public Button getbAddOne() {
        return bAddOne;
    }

    public void setbAddOne(JFXButton bAddOne) {
        this.bAddOne = bAddOne;
    }
    public JFXTextArea getCount () {
        return count;
    }
    public void setCount(String newCount) {
        this.count.setText(newCount);
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
