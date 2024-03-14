import java.awt.image.BufferedImage;

import static KongFuKing.util.Constant.*;

/**
 * @author myry
 * @date 2024-18-14 21:18
 * 角色类 抽象类
 */
@SuppressWarnings({"all"})
public abstract class User {
    private int x;
    private int y;
    private int speed;
    private int direction;
    private String name;
    private int hp;
    private int hit;
    private String hobby;
    private BufferedImage image;
    private int state = 5;
    private boolean isLive;
    private User user;
    private Bomb bomb;

    public User() {
    }

    public User(int x, int y, String name, int hp, int hit, int speed, boolean isLive) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.hp = hp;
        this.hit = hit;
        this.speed = speed;
        this.isLive = isLive;
    }

    public User(int x, int y, String name, int hp, int hit, String hobby, BufferedImage image, int speed, boolean isLive) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.hp = hp;
        this.hit = hit;
        this.hobby = hobby;
        this.image = image;
        this.speed = speed;
        this.isLive = isLive;
    }

    public void stateChange() {
        if (state > 0) {
            state--;
        } else {
            state = 5;
        }
    }

    //在mypanel中得到其余对象
    public void getUser(User user) {
        this.user = user;
    }

    //判断是否发送碰撞 防止重叠
    public boolean isUpTouch() {
        if (this.getX() >= user.getX() - userwidth
                && this.getX() <= user.getX() + userwidth
                && this.getY() >= user.getY()
                && this.getY() <= user.getY() + userheight + 10) {
            return true;
        }
        return false;
    }

    public boolean isRightTouch() {
        if (this.getX() >= user.getX() - userwidth - 10
                && this.getX() <= user.getX() + userwidth
                && this.getY() >= user.getY() - userheight
                && this.getY() <= user.getY() + userheight) {
            return true;
        }
        return false;
    }

    public boolean isDownTouch() {
        if (this.getX() >= user.getX() - userwidth
                && this.getX() <= user.getX() + userwidth
                && this.getY() + userheight + 10 >= user.getY()
                && this.getY() <= user.getY()) {
            return true;
        }
        return false;
    }

    public boolean isLeftTouch() {
          if (this.getX() <= user.getX() + userwidth + 10
                && this.getX() >= user.getX()
                && this.getY() >= user.getY() - userheight
                && this.getY() <= user.getY() + userheight) {
            return true;
        }
        return false;
    }

    public void move() {
        int time = (int) (Math.random() * 40) + 1;
        switch (getDirection()) {
            case 0://上
                for (int i = 0; i < time; i++) {
                    if (getY() > 0 && !isUpTouch()) {
                        moveUp();
                    }
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                break;
            case 1://右
                for (int i = 0; i < time; i++) {
                    if (getX() + info_width < width && !isRightTouch()) {
                        moveRight();
                    }
                    try {//务必休眠 否则右bug
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                break;
            case 2://下
                for (int i = 0; i < time; i++) {
                    if (getY() + userheight < height && !isDownTouch()) {
                        moveDown();
                    }
                    try {//务必休眠 否则右bug
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                break;
            case 3://左
                for (int i = 0; i < time; i++) {
                    if (getX() > info_width && isLeftTouch()) {
                        moveLeft();
                    }
                    try {//务必休眠 否则右bug
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                break;
            //随机改变方向
        }
    }

    public void createShot() {}

    public void attack(Shot shot, User user) {
        if (user.isLive()) {
            if (shot.getDirection() == 0 || shot.getDirection() == 2) {
                if (shot.getX() + shotwidth >= user.getX()
                        && shot.getX() <= user.getX() + userwidth
                        && shot.getY() >= user.getY()
                        && shot.getY() <= user.getY() + userheight) {
                    shot.setLive(false);
                    int hp = user.getHp();
                    user.setHp(hp - this.getHit());

                    System.out.println(this.getName() + "对" + user.getName() + " 造成了 " + this.getHit());

                    if (user.getHp() <= 0) {
                        user.setLive(false);
                    }

                    bomb = new Bomb(user.getX() + userwidth / 5, user.getY() + userheight / 5);
                }
            } else if (shot.getDirection() == 1 || shot.getDirection() == 3) {
                if (shot.getY() + shotheight >= user.getY()
                        && shot.getY() <= user.getY() + userheight
                        && shot.getX() >= user.getX()
                        && shot.getX() <= user.getX() + userwidth) {
                    shot.setLive(false);
                    int hp = user.getHp();
                    user.setHp(hp - this.getHit());

                    System.out.println(this.getName() + "对" + user.getName() + " 造成了 " + hit);

                    if (user.getHp() <= 0) {
                        user.setLive(false);
                    }
                    bomb = new Bomb(user.getX() + userwidth / 5, user.getY() + userheight / 5);
                }
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getHit() {
        return hit;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public void moveUp() {//向上
        y -= speed;
    }

    public void moveDown() {//向下
        y += speed;
    }

    public void moveLeft() {//向左
        x -= speed;
    }

    public void moveRight() {//向右
        x += speed;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Bomb getBomb() {
        return bomb;
    }

    public void setBomb(Bomb bomb) {
        this.bomb = bomb;
    }
}
