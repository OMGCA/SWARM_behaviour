class Bird {
    /*
     * From the basis of this class you should implement a working
     * Bird robot.
     * You will need to decide how to finish these methods as well as what fields
     * to provide.
     *
     */

    private Canvas canvas; // private field reference to a canvas object
    //private CartesianCoordinate posBird = new CartesianCoordinate ( 300, 400 );
    CartesianCoordinate posStart = new CartesianCoordinate( 400, 300 );
    CartesianCoordinate posEnd = new CartesianCoordinate ( 0, 0 );
    double direction;
    private int penUse;
    CartesianCoordinate position;


    public Bird(Canvas canvas) {
        this.canvas = canvas;
        int spawnX = Utils.randomInt(800);
        int spawnY = Utils.randomInt(600);
        CartesianCoordinate spawnLocation = new CartesianCoordinate ( spawnX, spawnY );
        this.posStart = spawnLocation;
        this.posEnd = posEnd;
        this.direction = direction;
        this.penUse = penUse;
        this.position = position;
    }

    public void putPenUp() {
        penUse = 0;
    }

    public void putPenDown() {
        penUse = 1;
    }

    public void turn(double angle) {
        this.direction = Math.toRadians(angle) + this.direction;
        
        //this.direction %= 2*Math.PI;
        
    }
    
    public double getTurn(){
        return this.direction;
    }


    public void resetTurn ()
    {
        this.direction = 0;
    }


    public void move(double pixels) {
        if ( penUse == 1 )
        {
            posEnd.setX( posStart.getX() + Math.cos(this.direction) * pixels );
            posEnd.setY( posStart.getY() - Math.sin(this.direction) * pixels );
            canvas.drawLineBetweenPoints( posStart, posEnd );

            posStart.setX(posEnd.getX());
            posStart.setY(posEnd.getY());
        }
        else
        {
            posEnd.setX( posStart.getX() + Math.cos(this.direction) * pixels );
            posEnd.setY( posStart.getY() - Math.sin(this.direction) * pixels );

            posStart.setX(posEnd.getX());
            posStart.setY(posEnd.getY());
        }
    }



    public void setStart(CartesianCoordinate Start)
    {
        this.posStart = Start;
    }


    public void draw()
    {

        this.putPenDown();
        for ( int i = 1; i <= 3; i++)
        {
            this.turn(-120);
            this.move(12);          
        }
        //this.direction = 0;
    }

    public void undraw ()
    {

        for ( int i = 1; i <= 3; i++ )
        {
            canvas.removeMostRecentLine();
        }


    }    

    public double getPositionX()
    {
        double BirdPosX;
        BirdPosX = this.posStart.getX();
        return BirdPosX;
    }

    public double getPositionY()
    {
        double BirdPosY;
        BirdPosY = this.posStart.getY();
        return BirdPosY;
    }
    
    public CartesianCoordinate getPosition(){
        double i = this.getPositionX();
        double j = this.getPositionY();
        this.position = new CartesianCoordinate(i,j);
        return this.position;
    }
    
    public double getDistance(Bird A,Bird B){
        double APosX = A.getPositionX();
        double APosY = A.getPositionY();
        double BPosX = B.getPositionX();
        double BPosY = B.getPositionY();
        
        double distance = Math.sqrt(Math.pow(APosX-BPosX,2) + Math.pow(APosY-BPosY,2));
        
        return distance;
    }
    
    

}


