import javax.swing.*;
import java.util.ArrayList;

class DynamicBird extends Bird
{
        DynamicBird(Canvas canvas)
        {
            super(canvas);
            this.draw();
        }

        DynamicBird(Canvas canvas, double xPosition, double yPosition)
        {
            super(canvas);
            this.posStart = new CartesianCoordinate ( xPosition, yPosition );
            this.setStart( this.posStart );
        }

        int speed = 200;

        public int getSpeed()
        {
            return this.speed;
        }

        public void setSpeed( int speed )
        {
            this.speed = speed;
        }

        public void update ( int time ){
            this.undraw();

            double probability = Utils.randomDouble();
            double angle;
            if ( probability < 0.05 ){
                angle = 360 * Utils.randomDouble();
                this.turn(angle);
            }
            
            
            int distance = this.speed * time/1000;
            
            this.putPenUp();
                   
            this.move(distance);                       
            
        }

        
        public void wallCollision( JFrame j ){
            int windowHeight = j.getHeight();
            int windowWidth = j.getWidth();
            if ( (this.getPositionX() <= 0) || (this.getPositionX() >= windowWidth) || (this.getPositionY() <=0 ) || (this.getPositionY() >= windowHeight)){
                this.turn(180);
            }
        }
        
        
        public void cohesion_separation(ArrayList<DynamicBird> B){
            int inAreaAgent = 0;
            for ( int i = 0; i < B.size(); i++ ){
                double currentAgentPosX = B.get(i).getPositionX();
                double currentAgentPosY = B.get(i).getPositionY();
                double targetPosX = 0;
                double targetPosY = 0;
                double totalX = 0;
                double totalY = 0;
                double targetDistance = 0;
                double targetDirRad = 0;
                double targetDirDeg = 0;
                double detectionDistance = 0;
                int cohesionC = 0;
                for ( int j = 0; j < B.size(); j++){
                    double comparisonAgentPosX = B.get(j).getPositionX();
                    double comparisonAgentPosY = B.get(j).getPositionY();
                    detectionDistance = Math.sqrt(Math.pow(currentAgentPosX - comparisonAgentPosX, 2) + Math.pow(currentAgentPosY - comparisonAgentPosY, 2));
                    if ( B.get(j) != B.get(i) ){
                        if ( detectionDistance <= 100 ){
                            inAreaAgent++;
                            totalX += comparisonAgentPosX;
                            totalY += comparisonAgentPosY;
                        }
                        if (detectionDistance <=100 && detectionDistance >= 30)
                        {
                            cohesionC = 1;
                        }
                        else
                        {
                            cohesionC = 0;
                        }
                    }                    
                }
                if ( inAreaAgent == 0 ){
                    B.get(i).update(20);
                }
                else{

                    targetPosX = totalX / inAreaAgent;
                    targetPosY = totalY / inAreaAgent;
                
                    targetDirRad = Math.atan2(targetPosY, targetPosX);
                    targetDirDeg = Math.toDegrees(targetDirRad);
                
                    targetDistance = Math.sqrt(Math.pow(currentAgentPosX - targetPosX, 2) + Math.pow(currentAgentPosY - targetPosY, 2));
                    if ( cohesionC == 1 ){
                        B.get(i).resetTurn();
                        B.get(i).undraw();
                        B.get(i).turn(targetDirDeg);
                        B.get(i).putPenUp();
                        B.get(i).move(targetDistance);
                    }
                    else{
                        B.get(i).turn(-10);
                        B.get(i).move(2);
                    }
                
                }
            
        }
        
    }
    
    public void alignment(ArrayList<DynamicBird> B){
        int inAreaAgent = 0;
        double totalDesiredDir = 0;
        for ( int i = 0; i < B.size(); i++ ){
            double currentAgentDir = B.get(i).getTurn();
            double currentAgentPosX = B.get(i).getPositionX();
            double currentAgentPosY = B.get(i).getPositionY();
            for ( int j = 0; j < B.size(); j++ ){
                    double comparisonAgentPosX = B.get(j).getPositionX();
                    double comparisonAgentPosY = B.get(j).getPositionY();
                    double detectionDistance = Math.sqrt(Math.pow(currentAgentPosX - comparisonAgentPosX, 2) + Math.pow(currentAgentPosY - comparisonAgentPosY, 2));
                    if ( detectionDistance <= 100 ){
                        if ( B.get(j) != B.get(i) ){
                            inAreaAgent++;
                            totalDesiredDir += B.get(j).getTurn();
                        }
                    }
            }
            double desiredDir = totalDesiredDir / inAreaAgent;
            B.get(i).turn(desiredDir - B.get(i).getTurn());
        }
    }
}
