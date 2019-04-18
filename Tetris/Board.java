import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import javax.swing.JPanel;

public class Board extends JPanel{

    int sleep = 500;
    int boardwidth = 10;
    int boardheight = 20;
    int blocksize = 20;
    int xoffset = 50;
    int yoffset = 50;
    
    int[][] board;
    /*
     * 0 - black (empty)
     * 1 - light blue (I block)
     * 2 - red (Z block)
     * 3 - green (S block)
     * 4 - yellow (square block)
     * 5 - dark blue (L block)
     * 6 - orange (mirrored L block)
     * 7 - purple (T block)
     */
    
    public Board(){
        setBackground(Color.BLACK);
        board = new int[boardheight][boardwidth];
        
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g;
        
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setRenderingHints(rh);

        drawBoard(g2d);
    }
    
    public void drawBlock(int boardX, int boardY, Color color, Graphics2D g2d){
        int xpos = xoffset + boardX * blocksize;
        int ypos = yoffset + boardY * blocksize;
        
        g2d.setColor(color);
        g2d.fillRect(xpos, ypos, blocksize, blocksize);
        g2d.setColor(color.white);
        g2d.drawRect(xpos, ypos, blocksize, blocksize);
    }
    
    public void drawBoard(Graphics2D g2d){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                Color c = new Color(0,0,0);
                switch(board[i][j]){
                   case 1:
                        c = new Color(135,206,250);
                        break;
                   case 2:
                        c = new Color(255,0,0);
                        break;
                   case 3:
                        c = new Color(60,179,113);
                        break;
                   case 4:
                        c = new Color(255,215,0);
                        break;
                   case 5:
                        c = new Color(0,0,205);
                        break;
                   case 6:
                        c = new Color(255,140,0);
                        break;
                   case 7:
                        c = new Color(160,32,240);
                        break;
                }
                drawBlock(j,i,c,g2d);
            }
        }
    }
    
    
    public void update(){
        //check for line fills
    }
    
    public int getSleep(){
        return sleep;
    }
}