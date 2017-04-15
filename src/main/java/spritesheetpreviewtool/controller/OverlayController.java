package spritesheetpreviewtool.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import spritesheetpreviewtool.ui.stage.CreateAnimationStage;

public class OverlayController extends BaseController {

    private CreateAnimationStage createAnimStage;

    @FXML
    private BorderPane borderPane;

    @FXML
    private void onNewAnimation(ActionEvent e) {

        if (createAnimStage == null) {

            final Stage stage = (Stage) borderPane.getScene().getWindow();

            createAnimStage = new CreateAnimationStage(this, getViewDirector(), stage);

        } else {
            createAnimStage.reset();
        }

        createAnimStage.showAndWait();
    }

    @FXML
    private void onExit(ActionEvent e) {
        System.exit(0);
    }

    public void onAnimationNameChosen(String animationName) {
        final ConfigController controller = new ConfigController(animationName);

        getViewDirector().switchView("ConfigView", controller);
    }

}
