import java.awt.EventQueue;
import javax.swing.JFrame;

public class Application extends JFrame {
    Board board = new Board();
    public Application() {
        initUI();
    }

    private void initUI() {
        
        add(board);

        setSize(300,500);

        setTitle("Tetris by Will Spadafora");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }    
    
    public static void main(String[] args) throws InterruptedException {
        
        Application ex = new Application();
        ex.setVisible(true);
        
        while (true) {
            ex.update();
            ex.repaint();
            Thread.sleep(ex.getSleep());
        }
    }
    
    public void update(){
        board.update();
    }
    
    public int getSleep(){
        return board.getSleep();
    }
}