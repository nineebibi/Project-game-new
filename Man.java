/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.awt.Rectangle;

public class Man {
    public int x,y,ManSize;
    public BufferedImage characterImage;
    public Man(int x,int y, int ManSize,String imagePath){
        this.x=x;
        this.y=y;
        this.ManSize = ManSize;
        try {
            this.characterImage = ImageIO.read(getClass().getResource(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
    public void walkleft(){
        this.x -= 20;
        if(this.x<0){
            x=0;
        }
    }
    public void walkright(){
        this.x += 20;
        if(this.x>940){
            x=940;
        }
    }
    public void walkup(){
        this.y -= 20;
        if(this.y<0){
            y=0;
        }
    }
    public void walkdown(){
        this.y += 20;
        if(this.y>520){
            y=520;
        }
    }
    public Rectangle getBounds() {
        return new Rectangle(x, y, ManSize, ManSize);
    }

    public void draw(Graphics g) {
        g.drawImage(characterImage, x, y, ManSize, ManSize, null);
    }
}
