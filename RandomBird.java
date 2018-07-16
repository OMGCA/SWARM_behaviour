class RandomBird extends DynamicBird
{
        RandomBird(Canvas canvas)
        {
            super(canvas);
            this.draw();
        }

        RandomBird(Canvas canvas, double xPosition, double yPosition)
        {
            super(canvas, xPosition, yPosition);
            this.posStart = new CartesianCoordinate ( xPosition, yPosition );
            this.setStart( this.posStart );
        }

        public double randomTurning()
        {
            int intAngle = Utils.randomInt(360);
            this.direction = (double)intAngle;
            return this.direction;
        }

        public void update ( int time, double angle )
        {
            this.undraw();

            int distance = this.speed * time/1000;

            int n = Utils.randomInt(100);
            int j = Utils.randomInt(5);
            
            for ( int i = 0; i <= n; i++)
            {
                this.undraw();
                this.putPenUp();
                   
                this.move(1);             
                this.draw();
                Utils.pause(5);
                this.undraw();
 
                
            }
           this.turn(angle);

           this.draw();

           //Utils.pause(5);
        }
};
