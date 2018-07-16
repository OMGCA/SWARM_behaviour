import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.*;
import javax.swing.event.*;

class Main{
    private JFrame frame;
    private Canvas canvas;
    private JPanel lowerPanel;
    private JPanel appBar;
    private JButton addBird;
    private JLabel appTitle;
    private ArrayList<DynamicBird> Birds;
    private ImageIcon birdAdd;
    private JSlider speedChange;
    private JSlider cohesionK;
    private JSlider separationK;
    private JSlider alignmentK;
    
    Main(){
        /*JFrame initialization*/
        this.frame = new JFrame();
        this.frame.setTitle("Flocking Simulator");
        this.frame.setSize(800,600);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setVisible(true);
        
        /*Canvas initialization*/
        this.canvas = new Canvas();
        
        /*JPanels initialization*/
        this.lowerPanel = new JPanel();
        this.appBar = new JPanel();
        
        /*Birds initialization*/
        this.Birds = new ArrayList<DynamicBird>();
        Birds.add(new DynamicBird(canvas));
        Birds.add(new DynamicBird(canvas));
        Birds.add(new DynamicBird(canvas));


                        
        /*ImageIcon initialization*/
        this.birdAdd = new ImageIcon("shape.png");
        
        /*JButton initialization*/
        addBird = new JButton(birdAdd);
        addBird.setBorderPainted(false);
        addBird.addActionListener( new ButtonListener());
        
        
        
        /*JLabel initalization*/
        appTitle = new JLabel("       Flocking Simulator");
        appTitle.setForeground(new Color(249,249,249));
        appTitle.setFont(new Font("random", Font.PLAIN, 25));
        
        /*JSlider initialization*/
        speedChange = new JSlider(0,400);
        speedChange.addChangeListener( new SliderListener());
        
        cohesionK = new JSlider(0,1);
        separationK = new JSlider(0,1);
        alignmentK = new JSlider(0,1);
        
        /*JComponents settings*/
        FlowLayout newFlowLayout = new FlowLayout();
        GridLayout lowerGridLayout = new GridLayout(2,5);
        GridLayout appBarGridLayout = new GridLayout(3,3);

        lowerPanel.setLayout(lowerGridLayout);
        lowerPanel.setBackground(new Color(238,238,238));
        appBar.setLayout(appBarGridLayout);
        appBar.setBackground(new Color(57,73,171));
        
        this.frame.add(canvas);
        
        
        lowerPanel.add(cohesionK);
        lowerPanel.add(separationK);
        lowerPanel.add(alignmentK);
        lowerPanel.add(new Label("                  Speed"));
        lowerPanel.add(new Label(" "));
        lowerPanel.add(new Label("              Cohesion"));
        lowerPanel.add(new Label("              Alignment"));
        lowerPanel.add(new Label("              Separation"));
        
        
        
        addBird.setBackground(null);
        lowerPanel.add(speedChange);
        lowerPanel.add(addBird);
        
        appBar.add(new JLabel(" "));
        appBar.add(appTitle);
        
        this.frame.add(lowerPanel, BorderLayout.PAGE_END);
        this.frame.add(appBar, BorderLayout.PAGE_START);
        
        this.gameLoop();
        
    }
    
    private void gameLoop(){
        int deltaTime = 30;
        int randomAngle = Utils.randomInt(100);

        
        while(true){
            for ( DynamicBird Dumi : Birds ){
                Dumi.undraw();
            }
            
            for ( DynamicBird Dumi : Birds ){
                Dumi.update(deltaTime);
            }
            
            /*
            for ( DynamicBird Dumi : Birds ){
                Dumi.cohesion_separation(Birds);
            }
            
            for ( DynamicBird Dumi : Birds ){
                Dumi.alignment(Birds);
            }
            */

            for ( DynamicBird Dumi : Birds ){
                Dumi.draw();
            }    
            
            for ( DynamicBird Dumi : Birds ){
                Dumi.wallCollision(frame);
            }
           
           Utils.pause(15);
           
        }
    }
   
    public static void main(String[] args){
        new Main();
    }
    
    class ButtonListener implements ActionListener{
        public void actionPerformed (ActionEvent event) {
            Birds.add(new DynamicBird(canvas));
        }
    }
    
    class SliderListener implements ChangeListener{
        public void stateChanged(ChangeEvent event){
            int newSpeed = speedChange.getValue();
            for ( DynamicBird Dumi : Birds ){
                Dumi.setSpeed(newSpeed);
            }
        }
    }
    
    
}
