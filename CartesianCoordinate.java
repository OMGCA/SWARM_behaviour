class CartesianCoordinate
{
    private double xPosition;
    private double yPosition;

    public CartesianCoordinate( double x, double y )
    {
        this.xPosition = x;
        this.yPosition = y;
    };

    public double getX()
    {
        return this.xPosition;
    }

    public double getY()
    {
        return this.yPosition;
    }

    public void setX( double xPosition )
    {
        this.xPosition = xPosition;
    }

    public void setY( double yPosition )
    {
        this.yPosition = yPosition;
    }

}
