package spritesheetpreviewtool.animation;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import spritesheetpreviewtool.animation.model.Sprite;
import java.util.Timer;
import java.util.TimerTask;

public class Preview {

    private Color background;
    private Sprite sprite;

    private Timer timer;

    private boolean animating = false;

    private Canvas canvas;
    private GraphicsContext ctx;

    public Preview(Color background, Canvas canvas) {
        this.background = background;
        this.canvas = canvas;
        this.ctx = canvas.getGraphicsContext2D();
    }

    public void startAnimation(int fps) {

        if (animating) {
            timer.purge();
            timer.cancel();
        }

        timer = new Timer(true);

        float msPerFrame = (1000 / fps);

        timer.schedule(new TimerTask() {
            public void run() {
                tick();
            }
        }, 0, msPerFrame);

        animating = true;
    }

    public void stopAnimation() {

        if (animating) {
            timer.cancel();
            timer.purge();
            animating = false;
        }

    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public void tick() {
        sprite.update();
        renderBackground();
        sprite.render(ctx);
    }

    public void renderBackground() {
        ctx.setFill(background);
        ctx.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

}
