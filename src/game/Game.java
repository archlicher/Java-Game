package game;

import framework.Animation;

import java.applet.Applet;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.ArrayList;

public class Game extends Applet implements Runnable, KeyListener {

    private Player player;
    private Heliboy hb1, hb2;
    private Image image, currentSprite, character, character2, character3, characterDown,
            characterJumped, background, heliboy, heliboy2, heliboy3, heliboy4, heliboy5;
    private URL base;
    private Graphics second;
    private static Background bg1, bg2;
    private Animation anim, hanim;

    @Override
    public void init() {
        setSize(800,480);
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);
        Frame frame = (Frame) this.getParent().getParent();
        frame.setTitle("First-Game");
        try {
            base = getDocumentBase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        character = getImage(base, "data/character.png");
        character2 = getImage(base, "data/character2.png");
        character3 = getImage(base, "data/character3.png");

        characterDown = getImage(base, "data/down.png");
        characterJumped = getImage(base, "data/jumped.png");

        heliboy = getImage(base, "data/heliboy.png");
        heliboy2 = getImage(base, "data/heliboy2.png");
        heliboy3 = getImage(base, "data/heliboy3.png");
        heliboy4 = getImage(base, "data/heliboy4.png");
        heliboy5 = getImage(base, "data/heliboy5.png");


        background = getImage(base, "data/background.png");

        anim = new Animation();
        anim.addFrame(character, 1250);
        anim.addFrame(character2, 50);
        anim.addFrame(character3, 50);
        anim.addFrame(character2, 50);

        hanim = new Animation();
        hanim.addFrame(heliboy, 100);
        hanim.addFrame(heliboy2, 100);
        hanim.addFrame(heliboy3, 100);
        hanim.addFrame(heliboy4, 100);
        hanim.addFrame(heliboy5, 100);
        hanim.addFrame(heliboy4, 100);
        hanim.addFrame(heliboy3, 100);
        hanim.addFrame(heliboy2, 100);

        currentSprite = anim.getImage();
    }

    @Override
    public void start() {
        bg1 = new Background(0,0);
        bg2 = new Background(2160,0);
        player = new Player();
        hb1 = new Heliboy(340, 360);
        hb2 = new Heliboy(700, 360);

        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void run() {
        while (true) {
            player.update();
            if (player.isJumped()) {
                currentSprite = characterJumped;
            } else if (!player.isJumped() && !player.isDucked()) {
                currentSprite = anim.getImage();
            }

            ArrayList projectiles = player.getProjectiles();
            for (int i = 0; i < projectiles.size(); i++) {
                Projectile p = (Projectile) projectiles.get(i);
                if (p.isVisible()) {
                    p.update();
                } else {
                    projectiles.remove(i);
                }
            }

            hb1.update();
            hb2.update();
            bg1.update();
            bg2.update();
            animate();
            repaint();
            try {
                Thread.sleep(17);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update(Graphics g) {
        if (image==null) {
            image = createImage(this.getWidth(), this.getHeight());
            second = image.getGraphics();
        }
        second.setColor(getBackground());
        second.fillRect(0, 0, getWidth(), getHeight());
        second.setColor(getForeground());
        paint(second);

        g.drawImage(image, 0, 0, this);
    }

    public void paint(Graphics g) {
        g.drawImage(background, bg1.getBgX(), bg1.getBgY(), this);
        g.drawImage(background, bg2.getBgX(), bg2.getBgY(), this);

        ArrayList projectile = player.getProjectiles();
        for (int i = 0; i < projectile.size(); i++) {
            Projectile p = (Projectile) projectile.get(i);
            g.setColor(Color.YELLOW);
            g.fillRect(p.getX(), p.getY(), 10, 5);
        }

        g.drawImage(currentSprite, player.getCenterX()-61, player.getCenterY()-63, this);
        g.drawImage(hanim.getImage(), hb1.getCenterX()-48, hb1.getCenterY()-48, this);
        g.drawImage(hanim.getImage(), hb2.getCenterX()-48, hb2.getCenterY()-48, this);
    }

    public void animate() {
        anim.update(10);
        hanim.update(50);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                break;
            case KeyEvent.VK_LEFT:
                player.moveLeft();
                player.setMovingLeft(true);
                break;
            case KeyEvent.VK_RIGHT:
                player.moveRight();
                player.setMovingRight(true);
                break;
            case KeyEvent.VK_DOWN:
                currentSprite = anim.getImage();
                if (!player.isJumped()) {
                    player.setDucked(true);
                    player.setSpeedX(0);
                }
                break;
            case KeyEvent.VK_SPACE:
                player.jump();
                break;
            case KeyEvent.VK_CONTROL:
                if (!player.isDucked() && !player.isJumped()) {
                    player.shoot();
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                break;
            case KeyEvent.VK_LEFT:
                player.stopLeft();
                break;
            case KeyEvent.VK_RIGHT:
                player.stopRight();
                break;
            case KeyEvent.VK_DOWN:
                currentSprite = character;
                player.setDucked(false);
                break;
            case KeyEvent.VK_SPACE:
                break;
        }
    }

    public static Background getBg1() {
        return bg1;
    }

    public static Background getBg2() {
        return bg2;
    }
}
