import java.awt.*;
import static KongFuKing.util.Constant.*;

/**
 * @author myry
 * @date 2024-18-14 21:18
 * 子弹类
 */
@SuppressWarnings({"all"})
public class Shot implements Runnable {
    private int x;
    private int y;
    private int speed = 21;
    private int direction;
    private Image image;
    private boolean isLive = true;

    public Shot() {
    }

    public Shot(int x, int y, int direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public Shot(int x, int y, int direction, Image image) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.image = image;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            //根据方向 改变xy坐标
            switch (direction) {
                case 0://向上
                    y -= speed;
                    break;
                case 1://向右
                    x += speed;
                    break;
                case 2://向下
                    y += speed;
                    break;
                case 3://向左
                    x -= speed;
                    break;
            }
            //调试坐标
            if (!(x >= info_width && x <= width & y >= 0 && y <= height && isLive)) {
                isLive = false;
                break;
            }
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }
}
