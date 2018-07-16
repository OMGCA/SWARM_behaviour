class LineSegment
{
   private CartesianCoordinate Start;
   private CartesianCoordinate End;

   public LineSegment ( CartesianCoordinate Start, CartesianCoordinate End )
   {
       this.Start = Start;
       this.End = End;
   };

   public CartesianCoordinate getStartPoint()
   {
       return this.Start;
   }

   public CartesianCoordinate getEndPoint()
   {
       return this.End;
   }

   public double length()
   {
       double length;
       double lengthX, lengthY;
       lengthX = Math.abs(Start.getX() - End.getX());
       lengthY = Math.abs(Start.getY() - End.getY());
       length = Math.sqrt(Math.pow( lengthX , 2 ) + Math.pow( lengthY , 2));
       return length;
   }
};
