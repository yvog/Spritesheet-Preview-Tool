package spritesheetpreviewtool;

import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import spritesheetpreviewtool.controller.BaseController;

public class ViewDirector {

    private Stage stage;
    private String viewLocation;
    private Parent defaultRoot;
    private BorderPane mainRoot;
    private FXMLLoader loader;

    public ViewDirector(Stage stage, String viewLocation, String defaultRootName) {
        this.stage = stage;
        this.viewLocation = viewLocation;

        try {
            this.loader = new FXMLLoader(getClass().getResource(viewLocation + defaultRootName + ".fxml"));
            this.defaultRoot = loader.load();
        } catch (IOException e) {
            System.out.println(e);
        }

    }

    public void activateMainView(String name, BaseController controller) {

        if (!mainRootSet()) {
            BorderPane root = (BorderPane) loadRoot(name, controller);
            mainRoot = root;
            stage.setScene(new Scene(root));
        }

    }

    public void switchView(String name, BaseController baseController) {

        try {

            if (mainRootSet()) {
                Parent root = loadRoot(name, baseController);
                mainRoot.setCenter(root);
            } else {
                throw new Exception("ViewDirector: unable to activate subview " + name + ". Activate the main view first.");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public Scene loadView(String name, BaseController controller) {
        Parent root = loadRoot(name, controller);

        return new Scene(root);
    }

    private Parent loadRoot(String name, BaseController controller) {
        Parent root;

        try {
            URL src = getClass().getResource(viewLocation + name + ".fxml");

            controller.addViewDirector(this);

            if (src == null) {
                root = defaultRoot;
            } else {
                FXMLLoader loader = new FXMLLoader(src);

                loader.setController(controller);
                root = loader.load();
            }

        } catch (IOException e) {
            root = defaultRoot;
        }

        return root;
    }

    private boolean mainRootSet() {
        return (mainRoot != null);
    }
}
