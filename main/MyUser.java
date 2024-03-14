package KongFuKing.main;

import java.awt.image.BufferedImage;
import java.util.Vector;

import static KongFuKing.util.Constant.*;

/**
 * @author myry
 * @date 2024-17-14 21:17
 * 我方角色类
 */

@SuppressWarnings({"all"})
public class MyUser extends User {
    Shot shot = null;
    Vector<Shot> shots = new Vector<>();
    private int ikunssize = 7;

    public MyUser(int x, int y, String name, int hp, int hit, String hobby, BufferedImage image, int speed, boolean isLive) {
        super(x, y, name, hp, hit, hobby, image, speed, isLive);
    }

    public void createShot() {
        if (shots.size() == 7) {//最多子弹树木
            return;
        }

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

    public void createIkun(User user, Vector<Ikun> ikuns) {
        for (int i = 0; i < ikuns.size(); i++) {
            if (ikuns.get(i).getX() + 45 > user.getX() &&
                    ikuns.get(i).getX() < user.getX() + userwidth &&
                    ikuns.get(i).getY() + 45 > user.getY() &&
                    ikuns.get(i).getY() < user.getY() + userheight) {

                this.setBomb(new Bomb(user.getX() + userwidth / 5, user.getY() + userheight / 5));
                ikuns.remove(i);
                int hp = user.getHp();
                user.setHp(hp - 7);

                if (user.getHp() <= 0) {
                    user.setLive(false);
                }
            }
        }
    }

    public int getIkunssize() {
        return ikunssize;
    }

    public void setIkunssize(int ikunssize) {
        this.ikunssize = ikunssize;
    }
}
