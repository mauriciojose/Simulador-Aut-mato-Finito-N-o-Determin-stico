
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Mauricio José
 */
public class Linha {

    public static final double angle = Math.PI / 10;	// arrowhead angle to shaft
    /**
     * length of arrowhead
     */
    public static final double len = 10;
    double theta = 0;
    protected int diameter = 12;
    protected int radius = diameter / 2;
    public double width;
    public double heigth;
    public double x;
    public double y;
    Point sw;
    Point ne;
    Graphics2D g2g;
    Ellipse2D circle;
    double phi;
    int barb;
    double ax1, ay1, ax2, ay2;
    String caracter = "";
    String lado = "semlado";
    int movStringX = 0;
    int movStringY = 0;

    public Linha(double posX, double posY) {
        width = posX;
        heigth = posY;
        x = posX;
        y = posY;
    }

    public void draw(Graphics2D g2) {
        g2g = g2;
        g2g.setStroke(new BasicStroke(1.5f));
        
        RenderingHints rh = g2g.getRenderingHints ();
        rh.put (RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);
        g2g.setRenderingHints (rh);
    
        g2g.draw(new Line2D.Double(x, y, width, heigth));
        
        arrHead(x, y, width, heigth);
        g2g.draw(new Line2D.Double(width, heigth, ax1, ay1));
        g2g.draw(new Line2D.Double(width, heigth, ax2, ay2));
        desenhaCaracter(g2);
    }

    public void desenhaCaracter(Graphics2D g2)
    {
        switch (lado){
            case "DIREITO":
                System.out.println("Caracter: "+caracter);
                for (int i = 0; i < caracter.length(); i++) {
                    g2.drawString(caracter.substring(i,i+1), (int) (((x+30)+width)/2)+movStringX, (int) (((y+heigth)/2)-4)+movStringY);
                    movStringY -= 12;
                }
                 movStringY = 0;
                break;
            case "ESQUERDO":
                for (int i = 0; i < caracter.length(); i++) {
                    
                }
                g2.drawString(caracter, (int) (((x-30)+width)/2), (int) ((y+heigth)/2)-4);
                break;
            case "CIMA":
                for (int i = 0; i < caracter.length(); i++) {
                    
                }
                g2.drawString(caracter, (int) ((x+width)/2)+4, (int) ((y+(heigth-30))/2));
                break;
            case "BAIXO":
                for (int i = 0; i < caracter.length(); i++) {
                    
                }
                g2.drawString(caracter, (int) ((x+width)/2)+4, (int) ((y+(heigth+30))/2));
                break;
            default:
                g2.drawString(caracter, (int) ((x+width)/2)+8, (int) ((y+heigth)/2)-8);
        }
        //g2.drawString(caracter, (int) ((x+width)/2)+8, (int) ((y+heigth)/2)-8);
    }

/**
 * Compute the arrowhead endpoints arrow is pointing from x1,y1 to x2,y2 angle
 * to shaft is angle. length of arrowhead is len. return endpoints in ax1, ay1,
 * ax2, ay2.
 */
public void arrHead (double x1, double y1, double x2, double y2){  
    double c,a,beta,theta,phi;

   c = Math.sqrt ((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));
   if (Math.abs(x2-x1) < 1e-6)
	if (y2<y1) theta = Math.PI/2;
	else theta = - Math.PI/2; 
   else
     { if (x2>x1)
   	   theta = Math.atan ((y1-y2)/(x2-x1)) ;
   	else
	   theta = Math.atan ((y1-y2)/(x1-x2));
     }
   a = Math.sqrt (len*len  + c*c - 2*len*c*Math.cos(angle));
   beta = Math.asin (len*Math.sin(angle)/a);
   phi = theta - beta;
   ay1 = y1 - a * Math.sin(phi);		// coordinates of arrowhead endpoint
   if (x2>x1) 
	ax1 = x1 + a * Math.cos(phi);
   else
	ax1 = x1 - a * Math.cos(phi);
   phi = theta + beta;				// second arrowhead endpoint
   ay2 = y1 - a * Math.sin(phi);
   if (x2>x1)
	ax2 = x1 + a * Math.cos(phi);
   else 
	ax2 = x1 - a * Math.cos(phi);
   
}
    
}
