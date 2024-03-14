import java.awt.image.BufferedImage;
import java.util.Vector;

/**
 * @author myry
 * @date 2024-16-14 21:16
 * 地方角色
 */

@SuppressWarnings({"all"})
public class EnemyUser extends User implements Runnable {
    Shot shot = null;
    Vector<Shot> shots = new Vector<>();

    public EnemyUser(int x, int y, String name, int hp, int hit, String hobby, BufferedImage image, int speed, boolean isLive) {
        super(x, y, name, hp, hit, hobby, image, speed, isLive);
    }

    public void createShot() {
        if (getHp() > 0 && shots.size() < 100) {
            switch (getDirection()) {
                case 0://向上
                    shot = new Shot(getX() + 35, getY() - 30, 0);
                    break;
                case 1://向右
                    shot = new Shot(getX() + 100, getY() + 50, 1);
                    break;
                case 2://向下
                    shot = new Shot(getX() + 30, getY() + 100, 2);
                    break;
                case 3://向左
                    shot = new Shot(getX() - 25, getY() + 50, 3);
                    break;
            }
            shots.add(shot);
            Thread t = new Thread(shot);
            t.start();//启动线程
        }
    }

    @Override
    public void run() {
        while (true) {
            if (this.isLive()) {
                move();
                createShot();
                setDirection((int) (Math.random() * 4));
                if (getHp() <= 0) {
                    break;
                }
            }
        }
    }
}
