package KongFuKing.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Vector;

import static KongFuKing.util.Constant.*;
import static KongFuKing.util.ManiPngs.*;

/**
 * @author myry
 * @date 2024-17-14 21:17
 * 游戏的绘画
 */

@SuppressWarnings({"all"})
public class MyPanel extends JPanel implements KeyListener, Runnable {
    Vector<Node> nodes = new Vector<>();
    Vector<Ikun> ikuns = new Vector<>();

    int gamestart = 0;

    MyUser cxk = new MyUser(1000, 600, "蔡徐坤", 30, 5, "ctrl", cxkprofi, 14, false);
    EnemyUser mbg = new EnemyUser(300, 600, "马保国", 90, 10, "kongfu", mbgprofi, 5, false);
    MyPartner tsk = new MyPartner(800, 600, "铁山靠", 40, 5, 2, false);

    Ikun ikun01 = new Ikun(cxk.getX() - 70, cxk.getY() - 70, ikunim01, false);
    Ikun ikun02 = new Ikun(cxk.getX() + 70, cxk.getY() - 70, ikunim02, false);
    Ikun ikun03 = new Ikun(cxk.getX() + 150, cxk.getY() + 40, ikunim03, false);
    Ikun ikun04 = new Ikun(cxk.getX() + 120, cxk.getY() + 130, ikunim04, false);
    Ikun ikun05 = new Ikun(cxk.getX() + 50, cxk.getY() + 120, ikunim05, false);
    Ikun ikun06 = new Ikun(cxk.getX() - 60, cxk.getY() + 110, ikunim06, false);
    Ikun ikun07 = new Ikun(cxk.getX() - 70, cxk.getY() + 40, ikunim07, false);

    Bomb bomb = null;

    int ikunsize = 7;
    int tskcount;
    int ikuncount;

    public MyPanel() throws IOException {
        ikuncount = 1;
        tskcount = 1;

        ikuns.add(ikun01);
        ikuns.add(ikun02);
        ikuns.add(ikun03);
        ikuns.add(ikun04);
        ikuns.add(ikun05);
        ikuns.add(ikun06);
        ikuns.add(ikun07);

        //防止重叠
        cxk.getUser(mbg);
        mbg.getUser(cxk);
        mbg.getUser(tsk);
        tsk.getUser(mbg);
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (gamestart == 0) {//界面
            drawInterface(g);
        } else if (gamestart == 1 || gamestart == 2) {//游戏
            try {
                if (cxk.isLive() && mbg.isLive()) {
                    drawInfo(g);
                    drawBg(g);
                    drwaIkun(g, ikuns);
                    if (cxk.isLive()) {
                        drawCxk(g, cxk);
                        drawCxkShot(g, cxk);
                    }

                    if (mbg.isLive()) {
                        drawMbg(g, mbg);
                        drawMbgShot(g, mbg);
                    }

                    if (tsk.isLive()) {
                        drawTsk(g, tsk);
                        drawTskShot(g, tsk);
                    }

                    drawBomb(g);
                } else if (cxk.isLive()) {
                    drawBg(g);
                    drawWin(g, cxk);
                } else if (mbg.isLive()) {
                    drawBg(g);
                    drawWin(g, mbg);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void drawInterface(Graphics g) {
        g.drawImage(bg3, 0, 0, width, height, this);
        g.setColor(Color.RED);
        Font font = new Font("宋体", Font.BOLD, 70);
        g.setFont(font);
        g.drawString("1.new Game", width / 2 - 200, height / 2 - 50);
        g.drawString("2.last Game", width / 2 - 200, height / 2 + 40);
    }

    public void drawWin(Graphics g, User user) {
        g.setColor(Color.YELLOW);
        Font font = new Font("宋体", Font.BOLD, 50);

        g.setFont(font);
        g.drawString(user.getName() + " has won", width / 2 - 200, height / 2);
    }

    public void drawInfo(Graphics g) {
        g.drawString("MyUser ", 5, 20);
        g.drawString("name ", 5, myname);
        g.drawString("hobby ", 5, myhobby);
        g.drawString("hp ", 5, myhp);

        g.drawString("EnemyUser ", 5, height / 2 - 10);
        g.drawString("name ", 5, enemyname);
        g.drawString("hobby ", 5, enemyhobby);
        g.drawString("hp ", 5, enemyhp);
    }

    public void drawBg(Graphics g) throws IOException {
        g.drawImage(bg1, info_width, 0, width, height, this);
    }

    public void drawCxk(Graphics g, MyUser myUser) throws IOException {
        g.setColor(Color.BLACK);
        g.drawImage(myUser.getImage(), 5, 30, 100, 100, this);
        g.drawString(myUser.getName(), 50, myname);
        g.drawString(myUser.getHobby(), 50, myhobby);

        if (myUser.isLive()) {
            if (cxk.getState() == 5) {
                g.drawImage(cxk01, cxk.getX(), cxk.getY(), userwidth, userheight, this);
            } else if (cxk.getState() == 4) {
                g.drawImage(cxk02, cxk.getX(), cxk.getY(), userwidth, userheight, this);
            } else if (cxk.getState() == 3) {
                g.drawImage(cxk03, cxk.getX(), cxk.getY(), userwidth, userheight, this);
            } else if (cxk.getState() == 2) {
                g.drawImage(cxk04, cxk.getX(), cxk.getY(), userwidth, userheight, this);
            } else {
                g.drawImage(cxk05, cxk.getX(), cxk.getY(), userwidth, userheight, this);
            }
            cxk.stateChange();
        }
        g.draw3DRect(50, myhp - 10, 90, 10, false);
        int hp = myUser.getHp();
        int len = 90 / 30 * hp;
        g.setColor(Color.RED);
        g.fillRect(50, myhp - 10, len, 10);
    }

    public void drawCxkShot(Graphics g, MyUser myUser) throws IOException {
        g.setColor(Color.BLACK);
        for (int i = 0; i < myUser.shots.size(); i++) {
            Shot shot = myUser.shots.get(i);

            if (shot != null && shot.isLive()) {
                g.drawImage(basketball, shot.getX(), shot.getY(), shotwidth, shotheight, this);
            } else {
                myUser.shots.remove(shot);
            }
        }
    }

    public void drawMbg(Graphics g, EnemyUser enemyUser) throws IOException {
        g.setColor(Color.BLACK);
        g.drawImage(enemyUser.getImage(), 5, height / 2, 100, 100, this);
        g.drawString(enemyUser.getName(), 50, enemyname);
        g.drawString(enemyUser.getHobby(), 50, enemyhobby);

        if (enemyUser.getHp() > 0) {
            g.drawImage(mbg01, enemyUser.getX(), enemyUser.getY(), userwidth, userheight, this);
        }

        g.draw3DRect(50, enemyhp - 10, 90, 10, false);
        int hp = enemyUser.getHp();
        int len = 90 / 90 * hp;
        g.setColor(Color.RED);
        g.fillRect(50, enemyhp - 10, len, 10);
    }

    public void drawMbgShot(Graphics g, EnemyUser enemyUser) throws IOException {
        for (int i = 0; i < enemyUser.shots.size(); i++) {
            Shot shot = enemyUser.shots.get(i);

            if (shot != null && shot.isLive()) {
                switch (shot.getDirection()) {
                    case 0:
                        g.drawImage(wave0, shot.getX(), shot.getY(), shotwidth, shotheight, this);
                        break;
                    case 1:
                        g.drawImage(wave1, shot.getX(), shot.getY(), shotwidth, shotheight, this);
                        break;
                    case 2:
                        g.drawImage(wave2, shot.getX(), shot.getY(), shotwidth, shotheight, this);
                        break;
                    case 3:
                        g.drawImage(wave3, shot.getX(), shot.getY(), shotwidth, shotheight, this);
                        break;
                }
            } else {
                enemyUser.shots.remove(shot);
            }
        }
    }

    public void drawTsk(Graphics g, MyPartner myPartner) {
        if (myPartner.isLive()) {
            if (tsk.getState() == 5) {
                g.drawImage(partner01, tsk.getX(), tsk.getY(), userwidth, userheight, this);
            } else if (cxk.getState() == 4) {
                g.drawImage(partner02, tsk.getX(), tsk.getY(), userwidth, userheight, this);
            } else if (cxk.getState() == 3) {
                g.drawImage(partner03, tsk.getX(), tsk.getY(), userwidth, userheight, this);
            } else if (cxk.getState() == 2) {
                g.drawImage(partner04, tsk.getX(), tsk.getY(), userwidth, userheight, this);
            } else {
                g.drawImage(partner05, tsk.getX(), tsk.getY(), userwidth, userheight, this);
            }
            tsk.stateChange();
        }
    }

    public void drawTskShot(Graphics g, MyPartner myPartner) {
        for (int i = 0; i < myPartner.shots.size(); i++) {
            Shot shot = myPartner.shots.get(i);

            if (shot != null && shot.isLive()) {
                g.drawImage(basketball, shot.getX(), shot.getY(), shotwidth, shotheight, this);
            } else {
                myPartner.shots.remove(shot);
            }
        }
    }

    public void drawBomb(Graphics g) throws IOException {
        if (cxk.getBomb() != null && cxk.getBomb().isLive) {
            Bomb bomb = cxk.getBomb();
            if (bomb.getLife() > 2) {
                g.drawImage(bomb1, bomb.getX(), bomb.getY(), 70, 70, this);
            } else if (bomb.getLife() > 1) {
                g.drawImage(bomb2, bomb.getX(), bomb.getY(), 70, 70, this);
            } else {
                g.drawImage(bomb3, bomb.getX(), bomb.getY(), 90, 90, this);
            }
            //让炸弹生命减少 使得离散->连续
            bomb.lifeDown();
        }

        if (mbg.getBomb() != null && mbg.getBomb().isLive) {
            Bomb bomb = mbg.getBomb();
            if (bomb.getLife() > 2) {
                g.drawImage(bomb1, bomb.getX(), bomb.getY(), 70, 70, this);
            } else if (bomb.getLife() > 1) {
                g.drawImage(bomb2, bomb.getX(), bomb.getY(), 70, 70, this);
            } else {
                g.drawImage(bomb3, bomb.getX(), bomb.getY(), 90, 90, this);
            }
            //让炸弹生命减少 使得离散->连续
            bomb.lifeDown();
        }

        if (tsk.getBomb() != null && tsk.getBomb().isLive) {
            Bomb bomb = tsk.getBomb();
            if (bomb.getLife() > 2) {
                g.drawImage(bomb1, bomb.getX(), bomb.getY(), 70, 70, this);
            } else if (bomb.getLife() > 1) {
                g.drawImage(bomb2, bomb.getX(), bomb.getY(), 70, 70, this);
            } else {
                g.drawImage(bomb3, bomb.getX(), bomb.getY(), 90, 90, this);
            }
            //让炸弹生命减少 使得离散->连续
            bomb.lifeDown();
        }
    }

    public void drwaIkun(Graphics g, Vector<Ikun> ikuns) {
        for (int i = 0; i < ikuns.size(); i++) {
            if (ikuns.get(i).isLive()) {
                g.drawImage(ikuns.get(i).getImage(), ikuns.get(i).getX(), ikuns.get(i).getY(), ikunwidth, ikunheight, this);
            }
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {
    }

    //处理wsad键
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_1) {//new Game
            gamestart = 1;
            cxk.setLive(true);
            mbg.setLive(true);

            Thread thmbg = new Thread(mbg);
            thmbg.start();
        }

        if (e.getKeyCode() == KeyEvent.VK_2) {//last Game
            gamestart = 2;
            cxk.setLive(true);
            mbg.setLive(true);

            Thread thmbg = new Thread(mbg);
            thmbg.start();

            nodes = Recorder.getNodes();
            tskcount = nodes.get(0).getCounttsk();
            ikuncount = nodes.get(0).getCountikuns();

            cxk.setX(nodes.get(1).getX());
            cxk.setY(nodes.get(1).getY());
            cxk.setHp(nodes.get(1).getHp());

            mbg.setX(nodes.get(2).getX());
            mbg.setY(nodes.get(2).getY());
            mbg.setHp(nodes.get(2).getHp());

            if (tskcount == 0) {
                tsk.setX(nodes.get(3).getX());
                tsk.setY(nodes.get(3).getY());
                tsk.setHp(nodes.get(3).getHp());
                tsk.setLive(true);
                //铁山靠线程启动
                Thread thtsk = new Thread(tsk);
                thtsk.start();
            }

            if (ikuncount == 0 && tskcount == 1) {
                for (int i = 0; i < ikuns.size(); i++) {
                    ikuns.get(i).setX(nodes.get(i + 3).getX());
                    ikuns.get(i).setY(nodes.get(i + 3).getY());
                    ikuns.get(i).setLive(true);
                    new Thread(ikuns.get(i)).start();
                }
            }

            if (ikuncount == 0 && tskcount == 0) {
                for (int i = 0; i < ikuns.size(); i++) {
                    ikuns.get(i).setX(nodes.get(i + 4).getX());
                    ikuns.get(i).setY(nodes.get(i + 4).getY());
                    ikuns.get(i).setLive(true);
                    new Thread(ikuns.get(i)).start();
                }
            }
            System.out.println("数据读取成功");
        }

        if (e.getKeyCode() == KeyEvent.VK_W) {//W 上
            if (cxk.getY() > 0 && !cxk.isUpTouch()) {
                cxk.moveUp();
                cxk.setDirection(0);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_D) {//D 右
            if (cxk.getX() + userwidth < width && !cxk.isRightTouch()) {
                cxk.moveRight();
                cxk.setDirection(1);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_S) {//S 下
            if (cxk.getY() + userheight < height && !cxk.isDownTouch()) {
                cxk.moveDown();
                cxk.setDirection(2);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_A) {//A 左
            if (cxk.getX() > info_width && !cxk.isLeftTouch()) {
                cxk.moveLeft();
                cxk.setDirection(3);
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_J) {
            cxk.createShot();
        }

        if (e.getKeyCode() == KeyEvent.VK_K) {
            if (ikuncount == 1 && gamestart != 0) {
                cxk.createIkun(mbg, ikuns);
                for (int i = 0; i < ikunsize; i++) {
                    ikuns.get(i).setLive(true);
                }
                ikuncount--;

                ikun01.setX(cxk.getX() - 70);
                ikun02.setX(cxk.getX() + 70);
                ikun03.setX(cxk.getX() + 150);
                ikun04.setX(cxk.getX() + 120);
                ikun05.setX(cxk.getX() + 50);
                ikun06.setX(cxk.getX() - 60);
                ikun07.setX(cxk.getX() - 70);
                ikun01.setY(cxk.getY() - 70);
                ikun02.setY(cxk.getY() - 70);
                ikun03.setY(cxk.getY() + 40);
                ikun04.setY(cxk.getY() + 130);
                ikun05.setY(cxk.getY() + 120);
                ikun06.setY(cxk.getY() + 110);
                ikun07.setY(cxk.getY() + 40);


                //ikun线程启动
                for (int i = 0; i < ikuns.size(); i++) {
                    new Thread(ikuns.get(i)).start();
                }
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_L) {
            if (tskcount == 1) {
                tsk.setX(cxk.getX() - 150);
                tsk.setY(cxk.getY());

                tsk.setLive(true);
                tskcount--;

                //铁山靠线程启动
                Thread thtsk = new Thread(tsk);
                thtsk.start();
            }
        }
        //重绘
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void run() {
        while (true) {//100ms重画
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (gamestart != 0) {
                //蔡徐坤攻击马保国
                if (cxk.isLive()) {
                    for (int i = 0; i < cxk.shots.size(); i++) {
                        Shot shot = cxk.shots.get(i);
                        if (shot != null && shot.isLive()) {
                            cxk.attack(shot, mbg);
                        }
                    }
                }

                //马保国攻击蔡徐坤
                if (mbg.isLive()) {
                    for (int i = 0; i < mbg.shots.size(); i++) {
                        Shot shot = mbg.shots.get(i);
                        if (shot != null && shot.isLive()) {
                            mbg.attack(shot, cxk);
                        }
                    }
                }

                //马保国攻击铁山靠
                if (mbg.isLive()) {
                    for (int i = 0; i < mbg.shots.size(); i++) {
                        Shot shot = mbg.shots.get(i);
                        if (shot != null && shot.isLive()) {
                            mbg.attack(shot, tsk);
                        }
                    }
                }

                //爱坤爆炸
                if (ikuns.size() > 0) {
                    for (int i = 0; i < ikuns.size(); i++) {
                        cxk.createIkun(mbg, ikuns);
                    }
                }

                //铁山靠攻击马保国
                if (tsk.isLive()) {
                    for (int i = 0; i < tsk.shots.size(); i++) {
                        Shot shot = tsk.shots.get(i);
                        if (shot != null && shot.isLive()) {
                            tsk.attack(shot, mbg);
                        }
                    }
                }
            }

            //信息记录
            Recorder.setCounts(tskcount + " " + ikuncount);
            Recorder.setMyUser(cxk);
            Recorder.setEnemyUser(mbg);
            Recorder.setMyPartner(tsk);
            Recorder.setIkuns(ikuns);

            this.repaint();
        }
    }
}
