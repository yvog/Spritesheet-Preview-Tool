package spritesheetpreviewtool.helper;

public class MathHelper {

    public static int center(int container, int child) {
        return (int) (Math.floor(container * .5) - Math.floor(child * .5));
    }

}
