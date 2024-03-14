import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import static KongFuKing.util.Constant.*;

/**
 * @author myry
 * @date 2024-16-14 21:16
 * 游戏执行界面
 */


@SuppressWarnings({"all"})
public class Game extends JFrame {
    MyPanel mp = null;

    public static void main(String[] args) throws IOException {
        Game game = new Game();
    }

    public Game() throws IOException {
        mp = new MyPanel();
        //将mp放入Thread 并启动
        Thread thread = new Thread(mp);
        thread.start();

        this.add(mp);
        this.setTitle(title);
        this.setSize(width, height + 50);
        this.setLocation(Fram_X,Fram_Y);
        this.setVisible(true);

        this.addKeyListener(mp);//增加监听事件 JFrame监听mp
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        //增加响应关闭窗口的监听
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("监听到窗口关闭");
                Recorder.keepRecord();
                System.exit(0);
            }
        });
    }
}
