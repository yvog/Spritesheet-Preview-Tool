package spritesheetpreviewtool.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import spritesheetpreviewtool.ui.stage.CreateAnimationStage;

public class CreateAnimationController extends BaseController {

    @FXML
    private GridPane gridPane;

    @FXML
    private TextField animNameField;

    @FXML
    private Label errorLabel;

    private OverlayController overlayController;

    public CreateAnimationController(OverlayController overlayController) {
        super();

        this.overlayController = overlayController;
    }

    @FXML
    private void createAnimation(ActionEvent e) {
        String text = animNameField.getText().trim();

        Node source = (Node) e.getSource();
        CreateAnimationStage stage = (CreateAnimationStage) source.getScene().getWindow();

        if (text.isEmpty()) {
            errorLabel.setText("Enter an animation name");
        } else {
            errorLabel.setText("");

            overlayController.onAnimationNameChosen(text);

            stage.close();
        }

    }

    public void reset() {
        errorLabel.setText("");
        animNameField.setText("");
    }

}
