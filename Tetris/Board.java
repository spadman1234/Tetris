import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import javax.swing.JPanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Board extends JPanel{

    public int sleep = 350;
    int boardwidth = 10;
    int boardheight = 20;
    int blocksize = 20;
    int xoffset = 50;
    int yoffset = 50;
    
    int gamePace = 4; //milliseconds removed from the sleep time for every block paced
    
    int score = 0;
    
    Tetromino fallingPiece;
    
    int[][] board;
    
    boolean gameOver;
    public boolean fastFalling;
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
        
        fallingPiece = new Tetromino((int)(Math.random() * 7));
        gameOver = false;
        fastFalling = false;
        
        
    }
    
    public void moveFallingPiece(Point vector){
        fallingPiece.move(vector);
        if(!validLocation(fallingPiece)){
            fallingPiece.move(new Point(-vector.getX(), -vector.getY()));
        }
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g;
        
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setRenderingHints(rh);

        drawBoard(g2d);
        fallingPiece.draw(g2d, this);
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
                   case -1:
                        c = new Color(255,255,255);
                        break;
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
                        c = new Color(38, 211, 211);
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
        g2d.setColor(new Color(255,255,255));
        //setFont(new java.awt.Font("Century Schoolbook L", 2, 24));
        g2d.drawString("Score: " + score,3,10);
    }
    
    public void rotateFallingPiece(){
        Tetromino ghostPiece = new Tetromino(fallingPiece);
        ghostPiece.rotate();
        if(validLocation(ghostPiece)){
           fallingPiece = ghostPiece;
        }
    }
    
    
    public void update(){
        if(!gameOver){
            
            //check if fallingPiece can fall
            Point[] nextPositions = fallingPiece.nextPositions();
            boolean canFall = true;
            for(Point point : nextPositions){
                if(point.getY() >= boardheight || board[point.getY()][point.getX()] != 0){
                    canFall = false;
                }
            }
            //if so, fallingPiece.fall()
            if(canFall){
                fallingPiece.fall();
            }
            //if not, lock fallingPiece into board[][] and initialize new fallingPiece
            else{
                for(Point point : fallingPiece.getPositions()){
                    board[point.getY()][point.getX()] = fallingPiece.getType() + 1;
                }
                fallingPiece = new Tetromino((int)(Math.random() * 7));
                if(!fastFalling){
                    sleep -= gamePace;
                }
                else{
                    sleep -= gamePace/4;
                }
                if(!validLocation(fallingPiece)) gameOver = true;
            }
            
            //look for whited out lines from last update and move down lines above it
            for(int i = 0; i < boardheight; i++){
                if(board[i][0] == -1){
                    dropLines(i);
                }
            }
            
            //check for line completions, white out completed lines, and update score
            int linesComplete = 0;
            for(int dy = 0; dy < boardheight; dy++){
                boolean lineComplete = true;
                for(int dx = 0; dx < boardwidth; dx++){
                    if(board[dy][dx] <= 0){
                        lineComplete = false;
                    }
                }
                if(lineComplete){
                    for(int dx = 0; dx < boardwidth; dx++){
                        board[dy][dx] = -1;
                    }
                    linesComplete++;
                }
            }
            score += (linesComplete*10)*(linesComplete*10);
        }
    }
    
    public void dropLines(int y){
        for(int i = y; i > 0; i--){
            for(int k = 0; k < boardwidth; k++){
                board[i][k] = board[i-1][k];
            }
        }
    }
    
    public boolean validLocation(Tetromino tetromino){
        for(Point point : tetromino.getPositions()){
            if(point.getX() < 0 || point.getX() >= boardwidth || point.getY() >= boardheight || board[point.getY()][point.getX()] != 0){
                return false;
            }
        }
        return true;
    }
    
    public int getSleep(){
        return sleep;
    }
}