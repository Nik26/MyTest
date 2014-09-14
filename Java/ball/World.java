class World{
    final static int WORLD_COLUMN = 15;
    final static int WORLD_LINE = 25;
    
    final static int BLACK_LINE = 1;
    final static int COINS = 3;
    final static int SPEED = 2;
    
    final static int COINS_SIZE = 25;
    
    final static int GRID_UNIT_SIZE = 32;
    int[][] worldGrid = new int[WORLD_LINE][WORLD_COLUMN];
    int[][] LEVEL_GRID = { 
                       { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
                       { 0, 0, 0, 3, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0 },
                       { 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
                       { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                       { 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 },
                       { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                       { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                       { 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0 },
                       { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                       { 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0 },
                       { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
                       { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                       { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                       { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                       { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                       { 0, 0, 0, 0, 0, 0, 3, 3, 3, 0, 0, 0, 0, 0, 0 },
                       { 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0 },
                       { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                       { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                       { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1 },
                       { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                       { 0, 0, 0, 0, 0, 2, 3, 3, 2, 0, 0, 0, 0, 0, 0 },
                       { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 3, 0, 0 },
                       { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                       { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1 }
                      };
     World(){
     
     }                 
     
     void drawLevel(){
     for(int i=0;i<WORLD_COLUMN;i++)
      { // for each column
        for(int ii=0;ii<WORLD_LINE;ii++)
        { // for each tile in that column
          switch(LEVEL_GRID[ii][i])
          { // check which tile type it is
            case BLACK_LINE:
              drawLine(0, 0, 0, i, ii);
              break;
            case COINS:
              drawCoins(i,ii);
              break;
            case SPEED:
              drawLine(0, 255, 0, i, ii);
              break;
            default:
              drawLine(160, 166, 213, i, ii);
              break;
          }
        }
      }
    }
    
    void drawCoins(int c, int l){
        fill(255,215,0);
        noStroke();
        ellipse((c*32)+16,(l*32)+16,COINS_SIZE,COINS_SIZE);
    }

    void drawLine(int r, int g, int b, int c, int l){
        noStroke(); 
        fill(r,g,b); 
        rect(c*GRID_UNIT_SIZE,l*GRID_UNIT_SIZE, // x,y of top left corner to draw rectangle
             GRID_UNIT_SIZE,GRID_UNIT_SIZE, 0.5); // width, height of rectangle
    }
    
    int worldLineAt(PVector thisPosition){
        float spotX = thisPosition.x/GRID_UNIT_SIZE;
        float spotY = thisPosition.y/GRID_UNIT_SIZE;
       
        if(spotX<0){
            return BLACK_LINE;
        } 
        if(spotX>=WORLD_COLUMN){
            return BLACK_LINE;
        }
        if(spotY<0){
            return BLACK_LINE;
        }
        if(spotY>=WORLD_LINE){
            return BLACK_LINE;
        }
    }
}