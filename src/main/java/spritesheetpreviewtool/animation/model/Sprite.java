package spritesheetpreviewtool.animation.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import spritesheetpreviewtool.animation.component.Spritesheet;

public class Sprite {

    private Image image;
    private int positionX;
    private int positionY;
    private int width;
    private int height;
    private int frameX;
    private int frameY;
    private Spritesheet spritesheet;

    public Sprite(Image image, int frameX, int frameY, int positionX, int positionY, int width, int height) {
        this.initialize(image, frameX, frameY, positionX, positionY, width, height);
        this.spritesheet = new Spritesheet(this);
    }

    public void initialize(Image image, int frameX, int frameY, int positionX, int positionY, int width, int height) {
        this.image = image;
        this.frameX = frameX;
        this.frameY = frameY;
        this.positionX = positionX;
        this.positionY = positionY;
        this.width = width;
        this.height = height;
    }

    public void configurateAnimation(int maxColumns, int maxRows) {
        this.spritesheet.configurate(maxColumns, maxRows);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getFrameX() {
        return frameX;
    }

    public int getFrameY() {
        return frameY;
    }

    public void syncAnimation(int frameX, int frameY) {
        this.frameX = frameX;
        this.frameY = frameY;
    }

    public void update() {
        this.spritesheet.update();
    }

    public void render(GraphicsContext ctx) {
        ctx.clearRect(positionX, positionY, width, height);
        ctx.drawImage(image, frameX, frameY, width, height, positionX, positionY, width, height);
    }

}
