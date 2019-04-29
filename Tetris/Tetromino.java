 

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import javax.swing.JPanel;

public class Tetromino {
    private int type;
    private Point[] positions;
    private Point anchor;
    private int rotation;
    
    public final Point[][][] TETROMINOES = 
    {
        { //I block
            {new Point(0,1), new Point(1,1), new Point(2,1), new Point(3,1)},
            {new Point(2,0), new Point(2,1), new Point(2,2), new Point(2,3)},
            {new Point(0,2), new Point(1,2), new Point(2,2), new Point(3,2)},
            {new Point(1,0), new Point(1,1), new Point(1,2), new Point(1,3)}
        },
        { //Z block
            {new Point(0,0), new Point(1,0), new Point(1,1), new Point(2,1)},
            {new Point(2,0), new Point(2,1), new Point(1,1), new Point(1,2)},
            {new Point(0,1), new Point(1,1), new Point(1,2), new Point(2,2)},
            {new Point(1,0), new Point(1,1), new Point(0,1), new Point(0,2)}
        },
        { //S block
            {new Point(0,1), new Point(1,1), new Point(1,0), new Point(2,0)},
            {new Point(1,0), new Point(1,1), new Point(2,1), new Point(2,2)},
            {new Point(0,2), new Point(1,2), new Point(1,1), new Point(2,1)},
            {new Point(0,0), new Point(0,1), new Point(1,1), new Point(1,2)}
        },
        { //square block
            {new Point(1,0), new Point(2,0), new Point(1,1), new Point(2,1)},
            {new Point(1,0), new Point(2,0), new Point(1,1), new Point(2,1)},
            {new Point(1,0), new Point(2,0), new Point(1,1), new Point(2,1)},
            {new Point(1,0), new Point(2,0), new Point(1,1), new Point(2,1)}
        },
        { //L block
            {new Point(0,1), new Point(1,1), new Point(2,1), new Point(2,0)},
            {new Point(1,0), new Point(1,1), new Point(1,2), new Point(2,2)},
            {new Point(0,1), new Point(0,2), new Point(1,1), new Point(2,1)},
            {new Point(0,0), new Point(1,0), new Point(1,1), new Point(1,2)}
        },
        { //mirrored L block
            {new Point(0,0), new Point(0,1), new Point(1,1), new Point(2,1)},
            {new Point(1,0), new Point(2,0), new Point(1,1), new Point(1,2)},
            {new Point(0,1), new Point(1,1), new Point(2,1), new Point(2,2)},
            {new Point(1,0), new Point(1,1), new Point(1,2), new Point(0,2)}
        },
        { //T block
            {new Point(1,0), new Point(0,1), new Point(1,1), new Point(2,1)},
            {new Point(1,0), new Point(1,1), new Point(1,2), new Point(2,1)},
            {new Point(0,1), new Point(1,1), new Point(1,2), new Point(2,1)},
            {new Point(0,1), new Point(1,0), new Point(1,1), new Point(1,2)}
        }
    };
    
    public Tetromino(int t){
        type = t;
        anchor = new Point(4,0);
        rotation = 0;
        positions = initializePositions();
        
        
    }
    
    //copy constructor
    public Tetromino(Tetromino t){
        this.type = t.type;
        //this.positions = t.positions;
        this.positions = new Point[4];
        for(int i = 0; i < 4; i++){
            this.positions[i] = new Point(t.positions[i]);
        }
        this.rotation = t.rotation;
        this.anchor = new Point(t.anchor);
    }
    
    public void rotate(){
        rotation = (rotation + 1) % 4;
        positions = TETROMINOES[type][rotation];
        for(Point point : positions){
            point.add(anchor);
        }
        //System.out.println(TETROMINOES);
    }
    
    //returns type of piece [0,6]
    public int getType(){
        return type;
    }
    
    public void draw(Graphics2D g2d, Board board){
        for(Point point : positions){
            
                Color c = new Color(0,0,0);
                switch(type + 1){
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
                board.drawBlock(point.getX(),point.getY(),c,g2d);
            
        }
    }
    
    public Point[] initializePositions(){
        //set positions variable
        //set it in middle of top of the board
        positions = TETROMINOES[type][rotation];
        for(Point point : positions){
            point.add(anchor);
        }
        return positions;
    }
    
    public Point[] getPositions(){
        return positions;
    }
    
    public void setPositions(Point[] dpositions){
        for(int i = 0; i < positions.length; i++){
            positions[i] = new Point(dpositions[i]);
        }
    }
    
    public void fall(){
        anchor.add(new Point(0,1));
        for(Point point : positions){
            point.add(new Point(0,1));
        }
    }
    
    public void move(Point velocity){
        anchor.add(velocity);
        for(Point point : positions){
            point.add(velocity);
        }
    }
    
    // public Point[] movePositions(Point velocity){
        // Point[] points = new Point[4];
        // for(int i = 0; i < 4; i++){
            // points[i] = new Point(positions[i]);
        // }
        // for(Point p : points){
            // p.add(velocity);
        // }
        
        // return points;
    // }
    
    public Point[] nextPositions(){
        Point[] points = new Point[4];
        for(int i = 0; i < 4; i++){
            points[i] = new Point(positions[i]);
        }
        for(Point p : points){
            p.add(new Point(0,1));
        }
        return points;
    }
}