import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
        
        KeyListener keyListener = new MyKeyListener();
        addKeyListener(keyListener);
        setFocusable(true);
    }    
    
    public class MyKeyListener implements KeyListener {
        @Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		//System.out.println("keyPressed="+KeyEvent.getKeyText(e.getKeyCode()));
		if(!board.fastFalling && e.getKeyCode() == KeyEvent.VK_DOWN){
		    board.fastFalling = true;
		    board.sleep = board.sleep/4;
		    update();
		    repaint();
		}
		
		if(e.getKeyCode() == KeyEvent.VK_UP){
		    board.rotateFallingPiece();
		    repaint();
		}
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT){
		    board.moveFallingPiece(new Point(1,0));
		    repaint();
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT){
		    board.moveFallingPiece(new Point(-1,0));
		    repaint();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//System.out.println("keyReleased="+KeyEvent.getKeyText(e.getKeyCode()));
		if(board.fastFalling && e.getKeyCode() == KeyEvent.VK_DOWN){
		    board.fastFalling = false;
		    board.sleep = board.sleep * 4;
		}
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        
        Application ex = new Application();
        ex.setVisible(true);
        
        while (true) {
            
            Thread.sleep(ex.getSleep());
            ex.update();
            ex.repaint();
        }
    }
    
    public void update(){
        board.update();
    }
    
    public int getSleep(){
        return board.getSleep();
    }
}