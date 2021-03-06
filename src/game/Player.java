package game;

import java.awt.Graphics;
import java.util.ArrayList;

public class Player {
    final int JUMPSPEED = -15;
    final int MOVESPEED = 5;
    final int GROUND = 382;

    private int centerX = 100;
    private int centerY = 382;
    private boolean jumped = false;
    private boolean movingLeft = false;
    private boolean movingRight = false;
    private boolean ducked = false;

    private static Background bg1 = Game.getBg1();
    private static Background bg2 = Game.getBg2();

    private int speedX = 0;
    private int speedY = 1;

    private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();

    public int getCenterX() {
        return this.centerX;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public int getCenterY() {
        return  this.centerY;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    public boolean isJumped() {
        return jumped;
    }

    public void setJumped(boolean jumped) {
        this.jumped = jumped;
    }

    public int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public int getSpeedY() {
        return speedY;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    public boolean isMovingLeft() {
        return this.movingLeft;
    }

    public void setMovingLeft(boolean moveLeft) {
        this.movingLeft = moveLeft;
    }

    public boolean isMovingRight() {
        return this.movingRight;
    }

    public void setMovingRight(boolean moveRight) {
        this.movingRight = moveRight;
    }

    public boolean isDucked() {
        return this.ducked;
    }

    public void setDucked(boolean ducked) {
        this.ducked = ducked;
    }

    public ArrayList<Projectile> getProjectiles() {
        return projectiles;
    }

    public void setProjectiles(ArrayList<Projectile> projectiles) {
        this.projectiles = projectiles;
    }

    public void update() {
        if (speedX < 0) {
            centerX += speedX;
        }
        if (speedX == 0 || speedX < 0) {
            bg1.setSpeedX(0);
            bg2.setSpeedX(0);
        }
        if (centerX <= 200 && speedX > 0) {
            centerX += speedX;
        }
        if (speedX > 0 && centerX > 200){
            bg1.setSpeedX(-MOVESPEED);
            bg2.setSpeedX(-MOVESPEED);
        }

        centerY += speedY;
        if (centerY + speedY >= GROUND) {
            centerY=GROUND;
        }

        if (jumped) {
            centerY += 1;
            if (centerY + speedY >= GROUND) {
                centerY = GROUND;
                speedY = 0;
                jumped = false;
            }
        }

        if (centerX+speedX<=60) {
            centerX=61;
        }
    }

    public void moveRight() {
        if (!ducked) {
            speedX = MOVESPEED;
        }
    }

    public void moveLeft() {
        if (!ducked) {
            speedX = -MOVESPEED;
        }
    }

    public void stopRight() {
        setMovingRight(false);
        stop();
    }

    public void stopLeft() {
        setMovingLeft(false);
        stop();
    }

    public void stop() {
        if (!isMovingRight() && !isMovingLeft()) {
            setSpeedX(0);
        }
        if (!isMovingRight() && isMovingLeft()) {
            moveLeft();
        }
        if (isMovingRight() && !isMovingLeft()) {
            moveRight();
        }
    }

    public void jump() {
        if(!jumped) {
            speedY=JUMPSPEED;
            jumped=true;
        }
    }

    public void shoot() {
        Projectile p = new Projectile(centerX+50, centerY+50);
        projectiles.add(p);
    }
}
