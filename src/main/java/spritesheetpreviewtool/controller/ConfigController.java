package spritesheetpreviewtool.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import spritesheetpreviewtool.animation.Preview;
import spritesheetpreviewtool.animation.model.Sprite;
import spritesheetpreviewtool.helper.MathHelper;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class ConfigController extends BaseController {

    @FXML
    private Canvas canvas;

    @FXML
    private Button chooseImageButton;

    @FXML
    private Label titleLabel;

    @FXML
    private TextField pathField;

    @FXML
    private TextField frameWidthField;

    @FXML
    private TextField frameHeightField;

    @FXML
    private TextField maxHFramesField;

    @FXML
    private TextField maxVFramesField;

    @FXML
    private TextField fpsField;

    @FXML
    private Label errorLabel;

    private FileChooser imageChooser;
    private TextField[] fields;

    private Preview preview;
    private Sprite sprite;
    private Image image;

    private String animationTitle;
    private String previousImagePath;

    public ConfigController(String animationName) {
        super();
        this.animationTitle = animationName;
    }

    @FXML
    public void onPreviewButtonAction(ActionEvent e) {
        final String imagePath = pathField.getText();

        if (imagePath.isEmpty()) {
            errorLabel.setText("Please choose a file first");
        } else {

            if (validateFields()) {
                errorLabel.setText("");

                final int width = getIntFrom(frameWidthField);
                final int height = getIntFrom(frameHeightField);
                final int maxRows = getIntFrom(maxVFramesField);
                final int maxColumns = getIntFrom(maxHFramesField);
                final int fps = getIntFrom(fpsField);

                final int spritePositionX = MathHelper.center((int) canvas.getWidth(), width);
                final int spritePositionY = MathHelper.center((int) canvas.getHeight(), height);

                if (image == null || !previousImagePath.equalsIgnoreCase(imagePath)) {
                    previousImagePath = imagePath;
                    FileInputStream imageFileStream = null;

                    try {
                        imageFileStream = new FileInputStream(new File(imagePath));
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    }

                    image = new Image(imageFileStream);
                }

                if (sprite == null) {
                    sprite = new Sprite(image, 0, 0, spritePositionX, spritePositionY, width, height);
                    preview.setSprite(sprite);
                } else {
                    sprite.initialize(image, 0, 0, spritePositionX, spritePositionY, width, height);
                }

                sprite.configurateAnimation(maxColumns, maxRows);

                preview.startAnimation(fps);
            }

        }

    }

    @FXML
    public void onChooseFile(ActionEvent e) {
        Stage stage = (Stage) chooseImageButton.getScene().getWindow();
        File image = imageChooser.showOpenDialog(stage);

        if (image != null) {
            pathField.setText(image.getAbsolutePath());
        }

    }

    private boolean validateFields() {
        int fieldsLength = fields.length;
        int i;

        for (i = 0; i < fieldsLength; ++i) {

            if (fields[i].getText().isEmpty()) {
                errorLabel.setText("Please fill all fields");

                return false;
            }

        }

        return true;
    }

    private int getIntFrom(TextField field) {
        return Integer.parseInt(field.getText());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        titleLabel.setText("Animation: " + animationTitle);

        imageChooser = new FileChooser();
        imageChooser.setTitle("Choose a spritesheet image");
        imageChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
        );

        TextField[] textFields = {frameWidthField, frameHeightField, maxHFramesField, maxVFramesField, fpsField};
        this.fields = textFields;

        int fieldsLength = fields.length;
        int i;

        for (i = 0; i < fieldsLength; ++i) {
            fields[i].setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
        }

        preview = new Preview(Color.LIGHTGREY, canvas);
        preview.renderBackground();
    }

}
