public class Tetromino {
    private int type;
    private Point[] positions;
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
    public final Point[][] TETROMINOES =
    {
        {new Point(0,0), new Point(0,1), new Point(0,2), new Point(0,3)},
        {new Point(0,0), new Point(1,0), new Point(1,1), new Point(2,1)},
        {new Point(0,0), new Point(0,1), new Point(1,1), new Point(1,3)},
        {new Point(0,0), new Point(1,0), new Point(0,1), new Point(1,1)},
        {new Point(0,0), new Point(1,0), new Point(1,1), new Point(1,2)},
        {new Point(1,0), new Point(1,1), new Point(0,2), new Point(1,2)},
        {new Point(1,0), new Point(0,1), new Point(1,1), new Point(1,2)}
    };
        
    
    public Tetromino(int t){
        type = t;
        positions = initializeTetromino();
    }
    
    public Point[] initializeTetromino(){
        //set positions variable
        //set it in middle of top of the board
    }
}