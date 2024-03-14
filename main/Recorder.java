package KongFuKing.main;

import java.io.*;
import java.util.Vector;

/**
 * @author myry
 * @date 2024-18-14 21:18
 * 信息的记录
 */

@SuppressWarnings({"all"})
public class Recorder {
    //定义IO对象
    private static FileWriter fw = null;
    private static BufferedWriter bw = null;
    private static BufferedReader br = null;
    private static String recordFile = "/Users/myry/Documents/MyJavaProject/Games/src/KongFuKing/record/record.txt";

    private static String counts = null;
    private static int tskcountrecord = 0;
    private static int ikuncountrecord = 0;

    //得到蔡徐坤对象
    private static MyUser myUser = null;

    //得到马保国对象
    private static EnemyUser enemyUser = null;

    //得到铁山靠对象
    private static MyPartner myPartner = null;

    //得到ikun对象
    private static Vector<Ikun> ikuns = null;

    //用于恢复数据的节点集合
    private static Vector<Node> nodes = new Vector<>();

    //将信息保存至文件
    public static void keepRecord() {
        try {
            bw = new BufferedWriter(new FileWriter(recordFile));
            bw.write(counts);
            bw.newLine();

            if (myUser.isLive()) {
                bw.write(myUser.getX() + " " + myUser.getY() + " " + myUser.getDirection() + " " + myUser.getHp());
                bw.newLine();
            }

            if (enemyUser.isLive()) {
                bw.write(enemyUser.getX() + " " + enemyUser.getY() + " " + enemyUser.getDirection() + " " + enemyUser.getHp());
                bw.newLine();
            }

            if (myPartner.isLive()) {
                bw.write(myPartner.getX() + " " + myPartner.getY() + " " + myPartner.getDirection() + " " + myPartner.getHp());
                bw.newLine();
            }

            for (int i = 0; i < ikuns.size(); i++) {
                Ikun ikun = ikuns.get(i);
                if (ikun != null && ikun.isLive()) {
                    bw.write(ikun.getX() + " " + ikun.getY() + " " + ikun.getDirection());
                    bw.newLine();
                }
            }

            System.out.println("数据写入成功");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    //读取信息
    public static Vector<Node> getNodes() {
        try {
            br = new BufferedReader(new FileReader(recordFile));
            String counts = br.readLine();//读取counts
            String[] temp1 = counts.split(" ");
            tskcountrecord = Integer.parseInt(temp1[0]);
            ikuncountrecord = Integer.parseInt(temp1[1]);

            Node node1 = new Node(tskcountrecord, ikuncountrecord);
            nodes.add(node1);

            String cxk = br.readLine();//读取cxk
            String[] temp2 = cxk.split(" ");
            Node node2 = new Node(Integer.parseInt(temp2[0]), Integer.parseInt(temp2[1]),
                    Integer.parseInt(temp2[2]), Integer.parseInt(temp2[3]));
            nodes.add(node2);

            String mbg = br.readLine();//读取马保国
            String[] temp3 = mbg.split(" ");
            Node node3 = new Node(Integer.parseInt(temp3[0]), Integer.parseInt(temp3[1]),
                    Integer.parseInt(temp3[2]), Integer.parseInt(temp3[3]));
            nodes.add(node3);

            if (tskcountrecord == 0) {
                String tsk = br.readLine();//读取铁山靠
                String[] temp4 = tsk.split(" ");
                Node node4 = new Node(Integer.parseInt(temp4[0]), Integer.parseInt(temp4[1]),
                        Integer.parseInt(temp4[2]), Integer.parseInt(temp4[3]));
                nodes.add(node4);
            }

            if (ikuncountrecord == 0) {
                String ikun = "";
                while ((ikun = br.readLine()) != null) {
                    String[] temp5 = ikun.split(" ");
                    Node node5 = new Node(Integer.parseInt(temp5[0]), Integer.parseInt(temp5[1]),
                            Integer.parseInt(temp5[2]));
                    nodes.add(node5);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return nodes;
    }

    public static String getCounts() {
        return counts;
    }

    public static void setCounts(String counts) {
        Recorder.counts = counts;
    }

    public static EnemyUser getEnemyUser() {
        return enemyUser;
    }

    public static void setEnemyUser(EnemyUser enemyUser) {
        Recorder.enemyUser = enemyUser;
    }

    public static MyUser getMyUser() {
        return myUser;
    }

    public static void setMyUser(MyUser myUser) {
        Recorder.myUser = myUser;
    }

    public static MyPartner getMyPartner() {
        return myPartner;
    }

    public static void setMyPartner(MyPartner myPartner) {
        Recorder.myPartner = myPartner;
    }

    public static Vector<Ikun> getIkuns() {
        return ikuns;
    }

    public static void setIkuns(Vector<Ikun> ikuns) {
        Recorder.ikuns = ikuns;
    }
}
