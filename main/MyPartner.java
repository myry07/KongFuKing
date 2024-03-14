import java.util.Vector;

/**
 * @author myry
 * @date 2024-19-14 21:19
 */

@SuppressWarnings({"all"})
public class MyPartner extends User implements Runnable {
    Shot shot = null;
    Vector<Shot> shots = new Vector<>();
    private int state = 5;

    public MyPartner() {
    }

    public MyPartner(int x, int y, String name, int hit, int hp, int speed, boolean isLive) {
        super(x, y, name, hit, hp, speed, isLive);
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
            move();
            createShot();
            setDirection((int) (Math.random() * 4));
            if (getHp() == 0) {
                break;
            }
        }
    }
}
