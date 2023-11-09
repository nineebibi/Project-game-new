// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import javax.swing.JFrame;


public class Main extends JFrame{
    public Main(){
        super("Who want to be a millionaire");
        this.setSize(1000, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(300, 200);
        this.setVisible(true);
        this.getContentPane().setLayout(null);
        this.getContentPane().add(new Game());

    }

    public static void main(String[] arg){
        Main display = new Main();
    }
}