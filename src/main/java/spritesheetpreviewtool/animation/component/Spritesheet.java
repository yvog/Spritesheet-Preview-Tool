package spritesheetpreviewtool.animation.component;

import spritesheetpreviewtool.animation.model.Sprite;

public class Spritesheet {

    private Sprite sprite;

    private int defaultFrameX;
    private int defaultFrameY;

    private int maxRows;
    private int maxColumns;

    private int currentRow = 0;
    private int currentColumn = 0;

    public Spritesheet(Sprite sprite) {
        this.sprite = sprite;
        this.defaultFrameX = sprite.getFrameX();
        this.defaultFrameY = sprite.getFrameY();
    }

    public void configurate(int maxColumns, int maxRows) {
        this.maxColumns = Math.max(maxColumns, 1);
        this.maxRows = Math.max(maxRows, 1);
        sprite.syncAnimation(0, 0);
    }

    public void update() {
        updateFrame();

        final int frameX = defaultFrameX + (currentColumn * sprite.getWidth());
        final int frameY = defaultFrameY + (currentRow * sprite.getHeight());

        sprite.syncAnimation(frameX, frameY);
    }

    private void updateFrame() {
        ++currentColumn;

        if (currentColumn >= maxColumns) {
            currentColumn = 0;
            ++currentRow;

            if (currentRow >= maxRows) {
                currentRow = 0;
            }

        }

    }

}
