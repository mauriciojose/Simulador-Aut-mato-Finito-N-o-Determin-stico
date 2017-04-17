import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;

public class Circulo
{
	public double width;
	public double heigth;
	public double x;
	public double y;
        Ellipse2D.Double myEllipse;
        Graphics2D g2g;
        Ellipse2D circle;
        int id;
	public Circulo(int id, double posX, double posY)
	{
                this.id = id;
		width = 50.0;
		heigth = 50.0;
		x = posX;
		y = posY;
	}
	public void draw(boolean desenha, boolean selecionado, Graphics2D g2)
	{
                g2g = g2;
		//myEllipse = new Ellipse2D.Double(x, y, width, heigth);
                Graphics g = g2;
                circle = new Ellipse2D.Float((int)x,(int)y,60,60);
                g2g.draw(circle);
		g2g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		if(selecionado){
			g2g.drawLine( (int)x, (int)y, (int)width, (int)heigth );   
		}
		else{
                        
			g2g.setStroke(new BasicStroke(3));
			g2g.setPaint(Color.YELLOW);
			g2g.fill(circle);
			g2g.setPaint(Color.BLACK);
                        //g2g.setColor(Color.RED);
                        g2g.setFont(new Font("Verdana",Font.PLAIN,20));
                        FontMetrics fm = g2.getFontMetrics();
                        
                        g2g.drawString("q"+id, (int)(x+15), (int)(y+35));
		}
		// Somente desenho caso a minha flag "desenha" esteja setada como true
		if(desenha){
			g2g.draw(circle);
		}
              
	}
	public boolean verificaMouseDentroDoCirculo(int mousePosX, int mousePosY)
	{
            
            System.out.println("X: "+x+"Y:"+y);
            System.out.println("posX: "+mousePosX+"posY:"+mousePosY);
		if(((mousePosX >= (x-3)) && (mousePosY >= (y-3)))
				&& ((mousePosX <= ((width + x)+12)) && (mousePosY <=
				(heigth + (y+10))))){
                    System.out.println("Circulo.verificaMouseDentroDoCirculo()");
			return true;
		}
		return false;
	}
        public void yes(double x, double y)
        {
            g2g.draw(circle);
            
        }
// Métodos get //
//----------------------------------------------------------------------------------------//
	public Double getPosx() {
		return x;
	}
	public Double getPosy() {
		return y;
	}
	public Double getWidth() {
		return width;
	}
	public Double getHeigth() {
		return heigth;
	}
//----------------------------------------------------------------------------------------//
// Métodos set //
//----------------------------------------------------------------------------------------//
	public void setPosx(double newPosx) {
		x = newPosx;
	}
	public void setPosy(double newPosy) {
		y = newPosy;
	}
	public void setWidth(double newWidth) {
		width = newWidth;
	}
	public void setHeigth(double newHeigth) {
		heigth = newHeigth;
	}
//----------------------------------------------------------------------------------------//
}
