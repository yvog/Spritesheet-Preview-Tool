package spritesheetpreviewtool;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import spritesheetpreviewtool.controller.EntryController;
import spritesheetpreviewtool.controller.OverlayController;

public class SpritesheetPreviewTool extends Application {
 
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Spritesheet Preview Tool");

        stage.getIcons().addAll(
                new Image(getClass().getResourceAsStream("/images/icons/icon64x64.png")),
                new Image(getClass().getResourceAsStream("/images/icons/icon32x32.png")),
                new Image(getClass().getResourceAsStream("/images/icons/icon16x16.png"))
        );

        stage.setWidth(640);
        stage.setHeight(400);

        final ViewDirector viewDirector = new ViewDirector(stage, "/fxml/view/", "ErrorView");

        viewDirector.activateMainView("OverlayView", new OverlayController());
        viewDirector.switchView("EntryView", new EntryController());

        stage.setResizable(false);
        stage.sizeToScene();

        stage.show();
    }

}
