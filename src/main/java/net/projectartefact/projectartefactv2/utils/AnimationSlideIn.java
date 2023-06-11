package net.projectartefact.projectartefactv2.utils;

public class AnimationSlideIn {

    private final long startTime = System.currentTimeMillis();
    private final int startX;
    private final int startY;
    private final int endX;
    private final int endY;
    private final int slidedInY;
    private final int slidedInX;

    private int currentX;
    private int currentY;

    public AnimationSlideIn(int startX, int startY, int endX, int endY, int width, int height, int duration) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.slidedInY = height * duration;
        this.slidedInX = width * duration;
    }

    public void render() {
        long time = System.currentTimeMillis() - startTime;
        double offsetY;
        double offsetX;
        if (time < this.slidedInY && endY != startY) {
            offsetY = Math.tanh(time / (double) (this.slidedInY) * 3.0) * (startY - endY);
        } else {
            offsetY = startY - endY;
        }
        if (time < this.slidedInX && endX != startX) {
            offsetX = Math.tanh(time / (double) (this.slidedInX) * 3.0) * (startX - endX);
        } else {
            offsetX = startX - endX;
        }

        currentX = (int) (startX - offsetX);
        currentY = (int) (startY - offsetY);
    }

    public int getCurrentX() {
        return currentX;
    }

    public int getCurrentY() {
        return currentY;
    }

}
