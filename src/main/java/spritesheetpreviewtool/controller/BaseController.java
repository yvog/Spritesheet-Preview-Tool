package spritesheetpreviewtool.controller;

import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import spritesheetpreviewtool.ViewDirector;

public class BaseController implements Initializable {

    private ViewDirector viewDirector;

    public void addViewDirector(ViewDirector director) {
        viewDirector = director;
    }

    protected ViewDirector getViewDirector() {
        return viewDirector;
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //
    }

}
