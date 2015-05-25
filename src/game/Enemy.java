package game;

public class Enemy {
    private int maxHealt, currentHealth, power, speedX, speedY, centerX, centerY;
    private Background bg = Game.getBg1();

    public int getMaxHealt() {
        return this.maxHealt;
    }

    public void setMaxHealt(int maxHealt) {
        this.maxHealt = maxHealt;
    }

    public int getCurrentHealth() {
        return this.currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public int getPower() {
        return this.power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getSpeedX() {
        return this.speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public int getSpeedY() {
        return this.speedY;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    public int getCenterX() {
        return this.centerX;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public int getCenterY() {
        return this.centerY;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    public Background getBg() {
        return this.bg;
    }

    public void setBg(Background bg) {
        this.bg = bg;
    }

    public void update() {
        centerX +=speedX;
        speedX = bg.getSpeedX();
    }

    public void attack() {

    }

    public void die() {

    }
}
