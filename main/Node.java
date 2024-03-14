package KongFuKing.main;

/**
 * @author myry
 * @date 2024-53-12 11:53
 * 节点类 用于恢复信息
 */

@SuppressWarnings({"all"})
public class Node {
    private int counttsk;
    private int countikuns;
    private int x;
    private int y;
    private int direction;
    private int hp;


    public Node(int counttsk, int countikuns) {
        this.counttsk = counttsk;
        this.countikuns = countikuns;
    }

    public Node(int x, int y, int direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public Node(int x, int y, int direction, int hp) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.hp = hp;
    }

    public int getCounttsk() {
        return counttsk;
    }

    public void setCounttsk(int counttsk) {
        this.counttsk = counttsk;
    }

    public int getCountikuns() {
        return countikuns;
    }

    public void setCountikuns(int countikuns) {
        this.countikuns = countikuns;
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

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    @Override
    public String toString() {
        return "Node{" +
                "counttsk=" + counttsk +
                ", countikuns=" + countikuns +
                ", x=" + x +
                ", y=" + y +
                ", direction=" + direction +
                ", hp=" + hp +
                '}';
    }
}
