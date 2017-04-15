package curvePlotter;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import jsat.linear.Matrix;

class Point
{
    double X, Y;
    public Point(double X, double Y)
    {
        this.X = X;
        this.Y = Y;
    }
}

public class GraphPanel extends JPanel
{   
    Main data;
    Solve solve;
    int leftLimit, rightLimit, upLimit, downLimit, unit, midPointX, midPointY, division;
    double leftX, rightX, mouseX, mouseY;
    int preX, preY;

    boolean isTrack, isXYCurve, isPolarCurve, isIntegral, isTangent, isRelation, isPoints;
    double lowLim, highLim, tPoint, from, to;
    Matrix points;
    String relation;
    Image background;
    int  k  = 1;
    private static final int STRIP_PER_UNIT = 10;
    private static final double ACCURACY = 0.1;									 // difference between two consecutive value of X and theta
    public static final double NOTFOUND = 0.123456789;
    
    ArrayList<String> postfixXY, postfixPolar, postfixL, postfixR;
    
    public GraphPanel(Matrix points, int width, int height)
    {
        setBackground(Color.WHITE);
        this.points = points;
        resetAll();
        isPoints = true;
        rightLimit = width;
        downLimit = height;

        this.addMouseListener(
            new MouseAdapter()
            {
                public void mousePressed(MouseEvent e) 
                {
                    preX = e.getX();
                    preY = e.getY();
                }
            }
        );
        this.addMouseMotionListener(
            new MouseMotionListener()
            {
                public void mouseMoved(MouseEvent e)
                {
                    mouseX = e.getX();
                    mouseY = e.getY();
                    if(isTrack)
                    {
                    	repaint();
//                        	drawTracking(getGraphics()); 
                	}
                }
                
                public void mouseDragged(MouseEvent e)
                {
                    int cur_X = e.getX();
                    int cur_Y = e.getY();
                    midPointX += (double)(cur_X - preX) / unit;
                    midPointY += (double)(cur_Y - preY) / unit;
                    repaint();
                }
            }
        );
        setButtons();
    }
    
    public GraphPanel(Main data, Solve solve)
    {
        this.data  = data;
        this.solve  = solve;
        setBackground(Color.WHITE);
        resetAll();
        
        this.addMouseListener(
            new MouseAdapter()
            {
                public void mousePressed(MouseEvent e) 
                {
                    preX = e.getX();
                    preY = e.getY();
                }
            }
        );
        this.addMouseMotionListener(
            new MouseMotionListener()
            {
                public void mouseMoved(MouseEvent e)
                {
                    mouseX = e.getX();
                    mouseY = e.getY();
                    if(isTrack)
                    {
                    	repaint();
//                    	drawTracking(getGraphics()); 
                	}
                }
                
                public void mouseDragged(MouseEvent e)
                {
                    int cur_X = e.getX();
                    int cur_Y = e.getY();
                    midPointX += (double)(cur_X - preX) / unit;
                    midPointY += (double)(cur_Y - preY) / unit;
                    repaint();
                }
            }
        );
        setButtons();
    }
   
    private void resetAll()
    {
        leftLimit = 0;
        if (data != null)
        	rightLimit = data.widthLimit; 
        upLimit = 0;
        
        if (data != null)
        	downLimit = data.heightLimit;
        
        midPointX = (rightLimit + leftLimit) / 2;
        midPointY = (downLimit + upLimit) / 2;
        
        division = 20;
        
        isTrack      = false;
        isXYCurve    = false;
        isPolarCurve = false;
        isIntegral   = false;
        isTangent    = false;
        isRelation   = false;
        isPoints 	 = false;
    }
    
    private void setButtons()
    {
        JButton reset = new JButton();
        reset.setText("Reset");
        reset.addActionListener(
	        new ActionListener()
	        {
	        	public void actionPerformed(ActionEvent e)
	        	{
	        		resetAll();
	        		repaint();
	        	}
	        }
        );
        this.add(reset);
    
        JButton scaleUp = new JButton();
        scaleUp.setText("Magnify +");
        scaleUp.addActionListener(
    		new ActionListener()
	        {
	        	public void actionPerformed(ActionEvent e)
	        	{
	        		division -= 2;
	        		repaint();
	        	}
	        }
        );
        JButton scaleDown = new JButton();
        scaleDown.setText("Magnify -");
        scaleDown.addActionListener(
    		new ActionListener()
	        {
	        	public void actionPerformed(ActionEvent e)
	        	{
	        		division += 2;
	        		repaint();
	        	}
	        }
        );
        this.add(scaleUp);
        this.add(scaleDown);
        
        final JButton track = new JButton();
        track.setText("Track : Yes");
        track.addActionListener(
    		new ActionListener()
	        {
	        	public void actionPerformed(ActionEvent e)
	        	{
		//            background = getScreenShot(this);
		            isTrack = !isTrack;
		            if(isTrack)     track.setText("Track : No");
		            else            track.setText("Track : Yes");
		            repaint();                                                              
	        	}
	        }
        );
        this.add(track);
    }
    
    private void drawAxes(Graphics g)
    {       
        Graphics2D g2d = (Graphics2D)g;
        g.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));
        g.drawLine(midPointX, upLimit, midPointX, downLimit);
        g.drawLine(leftLimit, midPointY, rightLimit, midPointY);

        g2d.setStroke(new BasicStroke(1));
        int tempX, tempY;
        g.setColor(Color.LIGHT_GRAY);
        tempY = midPointY;
        while(tempY > upLimit)
        {
            tempY -= unit; 
            g.drawLine(leftLimit, tempY, rightLimit, tempY);
        }
        tempY = midPointY;
        while(tempY < downLimit)
        {
            tempY += unit;
            g.drawLine(leftLimit, tempY, rightLimit, tempY);
        }
        
        tempX = midPointX;
        while(tempX > leftLimit)
        {
            tempX -= unit; 
            g.drawLine(tempX, upLimit, tempX, downLimit);
        }
        tempX = midPointX;
        while(tempX < rightLimit)
        {
            tempX += unit;
            g.drawLine(tempX, upLimit, tempX, downLimit);
        }
    }

    private Point adjust(Point p)														 // Converts X, Y to screen's X, Y
    {
        double X, Y;
        X = midPointX + unit*p.X;
        Y = midPointY - unit*p.Y;														 //  - because screen's Y is inverted
        return new Point(X, Y);
    }
    
    private void drawLine(Graphics2D g, Point p1, Point p2)
    {
        p1 = adjust(p1);
        p2 = adjust(p2);
        g.drawLine((int)p1.X, (int)p1.Y, (int)p2.X, (int)p2.Y);
    }
    
    private void drawCurve(Graphics g)
    {
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Color.RED);
        g2d.setStroke(new BasicStroke(2));
        Point p1, p2;
        double X1, Y1, X2, Y2;
        
        leftX  = (leftLimit - midPointX) / unit;                
        rightX = (rightLimit - midPointX) / unit;
        X1 = leftX;
        Y1 = solve.evaluate(postfixXY, X1);                      
        p1 = new Point(X1, Y1);
        
        for(X2 = leftX; X2 <= rightX; X2 += ACCURACY)
        {
            Y2 = solve.evaluate(postfixXY, X2);                  
            p2 = new Point(X2, Y2);
            drawLine(g2d, p1, p2);
            p1 = p2;
        }
    }
    
    private void drawPoints(Graphics g)
    {
    	Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Color.RED);
        g2d.setStroke(new BasicStroke(2));

        for (int i = 0; i < points.rows(); i++)
        {
        	Point p = new Point(points.get(i, 0), points.get(i, 1));
        	p = adjust(p);
    		g2d.fillOval((int)p.X, (int)p.Y, 10, 10);
        }    
    }
    
    public static BufferedImage getScreenShot(Component component) 
    {
	    BufferedImage image = new BufferedImage(component.getWidth(), component.getHeight(), BufferedImage.TYPE_INT_RGB);
	    // call the Component's paint method, using
	    // the Graphics object of the image.
	    component.paint( image.getGraphics() ); 
	    return image;
    }
    
    private void drawTracking(Graphics g)
    {
//    	g.drawImage(background, 0, 0, this);
        g.setColor(Color.BLUE);
        mouseX = (double)(mouseX - midPointX) / unit; 
        
        if(mouseX < leftX || mouseX > rightX)              								  //  If out of Screen
            mouseX = 0;
        
        double Y = solve.evaluate(postfixXY, mouseX);
        Point p = adjust(new Point(mouseX, Y));
        g.fillOval((int)p.X - 4, (int)p.Y - 4, 8, 8);
        
        Graphics2D g2d = (Graphics2D)g;
        Stroke dashed = new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{8}, 0);
        g2d.setStroke(dashed);																		  //  stroke for Dashed line	
        Point pX = adjust(new Point(mouseX, 0));
        Point pY = adjust(new Point(0, Y));
        
        g2d.drawLine((int)p.X, (int)p.Y, (int)pX.X, (int)pX.Y);
        g2d.drawLine((int)p.X, (int)p.Y, (int)pY.X, (int)pY.Y);
        
        g2d.setColor(Color.BLACK);
        g2d.drawString((double)((int)(mouseX*100)) / 100 + "", (int)pX.X, (int)pX.Y + 15);            //  precision up to 2 digit
        g2d.drawString((double)((int)(Y*100)) / 100 + "", (int)pY.X - 30, (int)pY.Y);                
    }
    
    private void drawPolarTracking(Graphics g)
    {
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Color.BLUE);
        double mousePX = (double)(mouseX - midPointX) / unit; 
        double mousePY = (double)(mouseY - midPointY) / unit; 
    
        double theta = Math.atan2(-mousePY, mousePX);           
        if(theta < 0.0)
            theta = 2*Math.PI + theta;
        if(theta < from || theta > to)
        	return;
        
        double r, X, Y;
        r = solve.evaluate(postfixPolar, theta);
        X = r * Math.cos(theta);
        Y = r * Math.sin(theta); 
        Point p = adjust(new Point(X, Y));
        g.fillOval((int)p.X - 4, (int)p.Y - 4, 8, 8);									 // oval positioning for "on curve ball"
        
        Stroke dashed = new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{8}, 0);
        g2d.setStroke(dashed);
        
        theta = (double)(theta * 180) / Math.PI;    
        g2d.drawLine((int)p.X, (int)p.Y, (int)midPointX, (int)midPointY);
        
        g2d.setColor(Color.BLACK);
        g2d.drawString((double)((int)(r*100)) / 100 + "", (int)(p.X + midPointX)/2, (int)(p.Y + midPointY)/2);
        g2d.drawString((double)((int)(theta*100)) / 100 + "", (int)midPointX + 10, (int)midPointY - 10);
    }
    
    private void drawPolarCurve(Graphics g)
    {
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(new Color(0, 130, 0));
        g2d.setStroke(new BasicStroke(2));
        double theta, r, X1, Y1, X2, Y2;
        Point p1, p2;
        r  = solve.evaluate(postfixPolar, from);
        X1 = r * Math.cos(from);
        Y1 = r * Math.sin(from);            
        p1 = new Point(X1, Y1);
        
        for(theta = from; theta <= to; theta += ACCURACY)
        {
            r  = solve.evaluate(postfixPolar, theta);
            X2 = r * Math.cos(theta);
            Y2 = r * Math.sin(theta);         
            p2 = new Point(X2, Y2);
            drawLine(g2d, p1, p2);
            p1 = p2;
        }
        
        r  = solve.evaluate(postfixPolar, to);
        X2 = r * Math.cos(to);
        Y2 = r * Math.sin(to);         
        p2 = new Point(X2, Y2);
        drawLine(g2d, p1, p2);
    }
    
    private void drawArea(Graphics g, double a, double b)
    {
        Graphics2D g2d = (Graphics2D)g;
        Point p1, p2;
        
        g2d.setColor(Color.GREEN);
        double dif = 1.0 / STRIP_PER_UNIT;                      			
        for(double X = a; X <= b; X += dif)
        {
            p1 = adjust(new Point(X, solve.evaluate(postfixXY, X)));
            p2 = adjust(new Point(X, 0));
            g2d.drawLine((int)p1.X, (int)p1.Y, (int)p2.X, (int)p2.Y);
        }
        
        g2d.setColor(Color.BLACK);              										 //  drawing boundary
        p1 = adjust(new Point(a, solve.evaluate(postfixXY, a)));
        p2 = adjust(new Point(a, 0));
        g2d.drawLine((int)p1.X, (int)p1.Y, (int)p2.X, (int)p2.Y);

        p1 = adjust(new Point(b, solve.evaluate(postfixXY, b)));
        p2 = adjust(new Point(b, 0));
        g2d.drawLine((int)p1.X, (int)p1.Y, (int)p2.X, (int)p2.Y);
    }
    
    private void drawTangent(Graphics g, double X)
    {
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Color.MAGENTA);
        double Y = solve.evaluate(postfixXY, X);                        				 
        double m = solve.derivative(postfixXY, X);                     					 //  Y = mX + c
        double c = Y - m*X;
        leftX  = (leftLimit - midPointX) / unit;                
        rightX = (rightLimit - midPointX) / unit;
        
        Point p1 = adjust(new Point(leftX, (m*leftX + c)));          
        Point p2 = adjust(new Point(rightX, (m*rightX + c)));
        g2d.drawLine((int)p1.X, (int)p1.Y, (int)p2.X, (int)p2.Y);
    }
    
    private void drawRelation(Graphics g)
    {
    	Graphics2D g2d = (Graphics2D)g; 
        double left  = (leftLimit - midPointX) / unit;                
        double right = (rightLimit - midPointX) / unit;
        double up = (upLimit - midPointY) / unit;        
        double down = (downLimit - midPointY) / unit;        
        
        double deltaH = 3*ACCURACY;
        double a, b;
        Point p;
        g2d.setColor(Color.BLUE);
        for(double X = left; X <= right; X += deltaH)
        {
            for(double Y = -down; Y <= -up; Y += deltaH)
            {
            	a = solve.evaluate2variable(postfixL, X, Y);
            	b = solve.evaluate2variable(postfixR, X, Y);
            	if(solve.checkRelation(relation, a, b))
            	{
            		p = adjust(new Point(X, Y));
            		g2d.fillOval((int)p.X, (int)p.Y, 6, 6);
            	}
            }
        }
    }
    
    public void drawPoints(Matrix m)
    {
    	isPoints = true;
    }
    public void drawXYCurve(String equation)
    {
        postfixXY = solve.infixToPostfix(equation);
        isXYCurve = true;
        repaint();
    }                    
    public void clearXYCurve()
    {
        isXYCurve = false;
        repaint();
    }
    public double getIntegral(String equation, double lowLim, double highLim)
    {
        if(isXYCurve)
        {
            return solve.integral(postfixXY, lowLim, highLim);
        }
        return NOTFOUND;
    }
    public void shadeArea(String equation, double lowLim, double highLim)
    {
        if(isXYCurve)
        {
            this.lowLim = lowLim;
            this.highLim = highLim;
            isIntegral = true;
            repaint();
        }
    }
    public void clearArea()
    {
        isIntegral = false;
        repaint();
    }
    public void drawTangent(String equation, double tPoint)
    {
        if(isXYCurve)
        {
            this.tPoint = tPoint;
            isTangent = true;
            repaint();
        }
    }
    public void clearTangent()
    {
        isTangent = false;
        repaint();
    }
    public double getSlope(String equation, double tPoint)
    {
        if(isXYCurve)
        {
            return solve.derivative(postfixXY, tPoint);
        }
        return NOTFOUND;
    }
    public void drawPolarCurve(String polarEquation, double from, double to)
    {
        postfixPolar = solve.infixToPostfix(polarEquation);
        this.from = from;
        this.to = to;
        isPolarCurve = true;
        repaint();
    }
    public void clearPolarCurve()
    {
        isPolarCurve = false;
        repaint();
    }                        
    public void drawRelation(String LHS, String relation, String RHS)
    {
    	postfixL = solve.infixToPostfix(LHS);
    	postfixR = solve.infixToPostfix(RHS);
    	this.relation = relation;
    	isRelation = true;
    	repaint();
    }
    public void clearRelation()
    {
        isRelation = false;
        repaint();
    }

    public void paint(Graphics g)
    {
    	super.paint(g);
        unit = ((downLimit - upLimit) / division);                                     //  unit of graph ,  height = downLimit - upLimit 
        
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        drawAxes(g);
        if(isXYCurve)       drawCurve(g);
        if(isPolarCurve)    drawPolarCurve(g);
        if(isIntegral)      drawArea(g, lowLim, highLim);
        if(isTangent)       drawTangent(g, tPoint);
        if(isRelation)      drawRelation(g);
        if(isPoints)		drawPoints(g);
        if(isTrack)         
        {
            if(isXYCurve)
                drawTracking(g);
            else if(isPolarCurve)
                drawPolarTracking(g);
        }
    }
}