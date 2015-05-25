package game;

public class Background {
    private int bgX, bgY, speedX;

    public Background(int x, int y) {
        this.setBgX(x);
        this.setBgY(y);
        this.setSpeedX(0);
    }

    public int getBgX() {
        return this.bgX;
    }

    public void setBgX(int bgX) {
        this.bgX = bgX;
    }

    public int getBgY() {
        return this.bgY;
    }

    public void setBgY(int bgY) {
        this.bgY = bgY;
    }

    public int getSpeedX() {
        return this.speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public void update() {
        bgX+=speedX;
        if (bgX<=-2160) {
            bgX+=4320;
        }
    }
}
