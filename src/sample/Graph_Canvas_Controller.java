package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class Graph_Canvas_Controller {



    @FXML
    private JFXTextArea count;
    @FXML
    private JFXButton bAddOne;

    @FXML
    public void initialize () {
        this.bAddOne.setOnMouseClicked( (MouseEvent event) -> {
            Integer currentCount = Integer.parseInt(this.count.getText()) + 1;
            this.setCount(String.valueOf(currentCount));
        });
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
}
