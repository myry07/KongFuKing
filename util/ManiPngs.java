import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static KongFuKing.util.Constant.*;

/**
 * @author myry
 * @date 2024-15-14 21:15
 * 操作图片
 */

@SuppressWarnings({"all"})
public class ManiPngs {
    public static final BufferedImage bg1 = manipng("background1.png");
    public static final BufferedImage bg2 = manipng("background2.png");
    public static final BufferedImage bg3 = manipng("background3.png");

    public static final BufferedImage cxkprofi = manipng("cxkprofi.png");
    public static final BufferedImage cxk01 = manipng("cxk01.png");
    public static final BufferedImage cxk02 = manipng("cxk02.png");
    public static final BufferedImage cxk03 = manipng("cxk03.png");
    public static final BufferedImage cxk04 = manipng("cxk04.png");
    public static final BufferedImage cxk05 = manipng("cxk05.png");
    public static final BufferedImage basketball = manipng("basketball.png");

    public static final BufferedImage ikunim01 = manipng("ikun01.png");
    public static final BufferedImage ikunim02 = manipng("ikun02.png");
    public static final BufferedImage ikunim03 = manipng("ikun03.png");
    public static final BufferedImage ikunim04 = manipng("ikun04.png");
    public static final BufferedImage ikunim05 = manipng("ikun05.png");
    public static final BufferedImage ikunim06 = manipng("ikun06.png");
    public static final BufferedImage ikunim07 = manipng("ikun07.png");

    public static final BufferedImage mbgprofi = manipng("mbgprofi.png");
    public static final BufferedImage mbg01 = manipng("mbg.png");
    public static final BufferedImage wave0 = manipng("wave0.png");
    public static final BufferedImage wave1 = manipng("wave1.png");
    public static final BufferedImage wave2 = manipng("wave2.png");
    public static final BufferedImage wave3 = manipng("wave3.png");

    public static final BufferedImage bomb1 = manipng("bomb1.png");
    public static final BufferedImage bomb2 = manipng("bomb2.png");
    public static final BufferedImage bomb3 = manipng("bomb3.png");

    public static final BufferedImage partner01 = manipng("partner1.png");
    public static final BufferedImage partner02 = manipng("partner2.png");
    public static final BufferedImage partner03 = manipng("partner3.png");
    public static final BufferedImage partner04 = manipng("partner4.png");
    public static final BufferedImage partner05 = manipng("partner5.png");


    public static BufferedImage manipng(String path) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(absolutPath + path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return image;
    }
}
