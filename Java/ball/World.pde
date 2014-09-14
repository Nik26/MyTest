class World {
    final static int COLUM = 15;
    final static int CELL = 25;
    final static int EMPTY = 0;
    final static int BOX = 1;
    final static int COINS = 3;
    final static int SPEED = 2;
    final static int RESET = 5;
    
    int coins_count;
    
    final static int COINS_SIZE = 25;
    
    final static int SIZE = 32;
    int[][] worldGrid = new int[CELL][COLUM];
    int[][] LEVEL_GRID = { 
                       { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, },
                       { 1, 0, 0, 3, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 1 },
                       { 1, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1 },
                       { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
                       { 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1 },
                       { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
                       { 1, 0, 0, 0, 0, 3, 3, 3, 0, 0, 0, 0, 0, 0, 1 },
                       { 1, 0, 0, 0, 0, 1, 5, 3, 1, 0, 1, 0, 0, 0, 1 },
                       { 1, 0, 0, 0, 0, 3, 3, 3, 3, 0, 0, 0, 0, 0, 1 },
                       { 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1 },
                       { 1, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 1 },
                       { 1, 3, 5, 3, 0, 0, 0, 0, 0, 0, 0, 5, 3, 0, 1 },
                       { 1, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
                       { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
                       { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 1 },
                       { 1, 0, 0, 0, 0, 0, 3, 3, 3, 0, 0, 0, 0, 0, 1 },
                       { 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1 },
                       { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
                       { 1, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 3, 3, 3, 1 },
                       { 1, 0, 0, 0, 0, 3, 3, 5, 3, 0, 0, 1, 1, 1, 1 },
                       { 1, 0, 0, 0, 0, 0, 1, 3, 1, 0, 0, 0, 0, 0, 1 },
                       { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
                       { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1 },
                       { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
                       { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }
                      };
     World(){
     
     }                 
     
     void drawLevel(){
       
       for(int i=0;i<COLUM;i++)
        { // for each column
          for(int ii=0;ii<CELL;ii++)
          { // for each tile in that column
            switch(worldGrid[ii][i])
            { // check which tile type it is
              case BOX:
                drawLine(0, 0, 0, i, ii);
                break;
              case SPEED:
                drawCoins(0, 255, 0, i, ii);
                break;
              case RESET:
                drawLine(255, 0, 0, i, ii);
                break;
              default:
                drawLine(160, 166, 213, i, ii);
                break;
            }
            
            if(worldGrid[ii][i]==COINS){
                drawCoins(255,225,0,i,ii);
            }
          
          }
          
        }
        
    }
    
    void drawCoins(int r, int g, int b,int c, int l){
        fill(r,g,b);
        noStroke();
        ellipse((c*32)+16,(l*32)+16,COINS_SIZE,COINS_SIZE);
    }

    void drawLine(int r, int g, int b, int c, int l){
        noStroke(); 
        fill(r,g,b); 
        rect(c*SIZE,l*SIZE,SIZE,SIZE, 0.5); 
    }

    int checkWorld(PVector thisPosition){
        float spotX = thisPosition.x/SIZE;
        float spotY = thisPosition.y/SIZE;
       
        if(spotX<0){
            return BOX;
        } 
        if(spotX>=COLUM){
            return BOX;
        }
        if(spotY<0){
            return BOX;
        }
        if(spotY>=CELL){
            return BOX;
        }
        
        return worldGrid[int(spotY)][int(spotX)];
    }
    
    void setEmpty(PVector thisPosition, int newTile) {
      int spotX = int(thisPosition.x/SIZE);
      int spotY = int(thisPosition.y/SIZE);
    
      if(spotX<0 || spotX>=COLUM || 
         spotY<0 || spotY>=CELL) {
        return; 
      }
     
      
      worldGrid[spotY][spotX] = newTile;
    }
  
    float checkTop(PVector thisPosition) {
        int thisY = int(thisPosition.y);
        thisY /= SIZE;
        return float(thisY*SIZE);
    }
    float checkBottom(PVector thisPosition) {
      if(thisPosition.y<0) {
        return 0;
    }
      return checkTop(thisPosition)+SIZE;
    }
    float checkLeft(PVector thisPosition) {
        int thisX = int(thisPosition.x);
        thisX /= SIZE;
        return float(thisX*SIZE);
    }
    float checkRight(PVector thisPosition) {
        if(thisPosition.x<0) {
          return 0;
        }
        
        return checkLeft(thisPosition)+SIZE;
    }
   
    void reload(PVector position) {
      coins_count = 0; 

      for(int i=0;i<COLUM;i++) {
        for(int ii=0;ii<CELL;ii++) {
            if(LEVEL_GRID[ii][i]==SPEED){
              worldGrid[ii][i] = EMPTY;
            }
            if(LEVEL_GRID[ii][i]==COINS) {
              coins_count++;
            }
              
            worldGrid[ii][i] = LEVEL_GRID[ii][i];
        }
      }
    } 
}
