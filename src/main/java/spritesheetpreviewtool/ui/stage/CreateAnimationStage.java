package spritesheetpreviewtool.ui.stage;

import javafx.scene.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import spritesheetpreviewtool.ViewDirector;
import spritesheetpreviewtool.controller.CreateAnimationController;
import spritesheetpreviewtool.controller.OverlayController;
import spritesheetpreviewtool.helper.MathHelper;

public class CreateAnimationStage extends Stage {

    private CreateAnimationController controller;
    private Stage parent;

    public CreateAnimationStage(OverlayController overlayController, ViewDirector viewDirector, Stage parent) {
        this.parent = parent;
        this.controller = new CreateAnimationController(overlayController);

        setTitle("Create new animation");

        setWidth(300);
        setHeight(125);

        initModality(Modality.APPLICATION_MODAL);
        setResizable(false);

        Scene scene = viewDirector.loadView("CreateAnimationView", this.controller);
        setScene(scene);

        setRelPosition();
    }

    public void reset() {
        controller.reset();
        setRelPosition();
    }

    private void setRelPosition() {
        setX(parent.getX() + MathHelper.center((int) parent.getWidth(), (int) getWidth()));
        setY(parent.getY() + MathHelper.center((int) parent.getHeight(), (int) getHeight()));
    }

}
