import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.Color;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.image.BufferedImage;

public class FallingObject6 {
    int x, y;
    int width = 30, height = 30;
    int speed = 5;
    Rectangle bounds;
    private BufferedImage image;

    public FallingObject6(int startX) {
        x = startX;
        y = 0 - height;
        bounds = new Rectangle(x, y, width, height);
        try {
            image = ImageIO.read(getClass().getResource("skull.png")); // ใส่ path ของรูปภาพของคุณที่นี่
            width = image.getWidth();
            height = image.getHeight();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        y += speed;
        bounds.setLocation(x, y);
    }

    public void paint(Graphics2D g2) {
        if(image != null) {
            g2.drawImage(image, x, y,70,70, null);
        } else {
            g2.setColor(Color.BLUE);
            g2.fillRect(x, y, width, height);
        }
    }
}
