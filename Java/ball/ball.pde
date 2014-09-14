AccelerometerManager ball;
PVector loc,position;
float ax, ay, az;
int w,h;
int r = 15;
int coinsCollected;
float sX = 1.02;
float sY = 1.02;

World WG = new World();

void reset(){
    coinsCollected = 0;
    loc = new PVector( w/2, h-60 ); 
    position = new PVector();
    WG.reload(loc);
    sX = 1.02;
    sY = 1.02;
}

void checkCoinGet(){
    PVector ball;
    ball = new PVector(loc.x,loc.y);
    
    if(WG.checkWorld(loc)==World.COINS){
        WG.setEmpty(loc,World.EMPTY);
        coinsCollected++;
    }
    
    if(WG.checkWorld(loc)==World.SPEED){
        WG.setEmpty(loc,World.EMPTY);
        sX = 0.4;
        sY = 0.4;
    }
    
    if(coinsCollected == WG.coins_count){
        reset(); 
    }
}

void checkWall(){
    PVector top , left, right,bottom,temp;
    top = new PVector();
    left = new PVector();
    right = new PVector();
    bottom = new PVector();
    temp = new PVector(loc.x,loc.y);
    
    top.x = bottom.x = temp.x;
    top.y = temp.y - r;
    bottom.y = temp.y + r;
    left.x = temp.x - r;
    left.y = right.y = temp.y;
    right.x = temp.x + r;
    
    
    if(WG.checkWorld(left)==World.BOX){
        temp.x += 20 ;
        loc.x = WG.checkLeft(temp);
    }
    
    if(WG.checkWorld(right)==World.BOX){
        temp.x -= 20;
        loc.x = WG.checkRight(temp);
    }
    
    if(WG.checkWorld(top)==World.BOX){
        temp.y += 20;
        loc.y = WG.checkTop(temp);
    }
    
    if(WG.checkWorld(bottom)==World.BOX){
        temp.y -= 20;
        loc.y = WG.checkBottom(temp);
    }
      
  
    if(WG.checkWorld(top) == World.RESET || 
        WG.checkWorld(bottom) == World.RESET ||
        WG.checkWorld(left) == World.RESET ||
        WG.checkWorld(right) == World.RESET
    
    ){
        reset();
    }
}

void printText(String text, float x, float y){
    textSize(20);
    fill(255);
    text(text,x,y);
}

void setup(){
    ball = new AccelerometerManager( this );
    
    size(displayWidth, displayHeight);
    w = displayWidth;
    h = displayHeight;
    orientation( PORTRAIT );
    reset();
    WG.reload(loc);
}

void draw(){
    float speedX = -(ax / sX);
    float speedY = (ay / sY);
    
    loc.x += speedX ;
    loc.y += speedY ;
    
    loc.x = constrain( loc.x, r, w - r );
    loc.y = constrain( loc.y, r, h - r );
    
    checkCoinGet();
    checkWall();
    
    fill( 160, 166, 213 );
    rect( 0, 0, width, height );
    
    WG.drawLevel();
    textAlign(LEFT);
    printText("Coins:"+coinsCollected +"/"+WG.coins_count,8, height-780);
    
    fill( 255 );
    noStroke();
    ellipse( loc.x, loc.y, r*2, r*2 );
}

public void accelerationEvent( float x, float y, float z ) {
    ax = x;
    ay = y;
    az = z;
    redraw();
}

