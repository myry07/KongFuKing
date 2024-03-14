package KongFuKing.main;

import java.awt.image.BufferedImage;
import static KongFuKing.util.Constant.*;

/**
 * @author myry
 * @date 2024-16-14 21:16
 * ikun类
 */

@SuppressWarnings({"all"})
public class Ikun extends User implements Runnable {
    private int x;
    private int y;
    private int speed = 10;
    private int direction;
    private BufferedImage image;
    private boolean isLive;

    public Ikun() {
    }

    public Ikun(int x, int y, BufferedImage image, boolean isLive) {
        this.x = x;
        this.y = y;
        this.image = image;
        this.isLive = isLive;
    }

    @Override
    public void run() {
        while (true) {
            try {
                createShot();
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            int num = (int) (Math.random() * 4);
            int move = (int) (Math.random() * 10) + 2;

            //根据方向 改变xy坐标
            switch (num) {
                case 0:
                    if (this.y > 0) {
                        y -= move;
                    }
                    break;
                case 1:
                    if (this.x + 50 < width) {
                        x += move;
                    }
                    break;
                case 2:
                    if (this.y + 50 < height) {
                        y += move;
                    }
                    break;
                case 3:
                    if (this.x > info_width) {
                        x -= move;
                    }
                    break;
                case 4:
                    if (this.x > info_width) {
                        x -= move;
                    }
                    break;
            }
        }
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public int getDirection() {
        return direction;
    }

    @Override
    public void setDirection(int direction) {
        this.direction = direction;
    }

    public BufferedImage getImage() {
        return image;
    }


    @Override
    public void setImage(BufferedImage image) {
        this.image = image;
    }

    @Override
    public boolean isLive() {
        return isLive;
    }

    @Override
    public void setLive(boolean live) {
        isLive = live;
    }

}
