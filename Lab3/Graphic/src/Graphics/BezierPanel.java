package Graphics;

import javax.swing.*;

import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;

class BezierPanel extends JComponent
{
	private static int SIZE = 10;
	private int current;
	//private static Random generator = new Random();
	private Point2D[] points;

	public BezierPanel()
	{
		initPoints(4);
		
		
		addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent event)
			{
				Point2D p =event.getPoint();
				for(int i = 0; i < points.length; i++)
				{
					double x = points[i].getX() - SIZE/2;
					double y = points[i].getY() - SIZE/2;
					Rectangle2D r = new Rectangle2D.Double(x, y, SIZE, SIZE);
					if(r.contains(p))
					{
						current = i;
						return;
					}
				}
			}
			
			public void mouseReleased(MouseEvent event)
			{
				current = -1;
			}
		});
		
		addMouseMotionListener(new MouseMotionAdapter()
		{
			public void mouseDragged(MouseEvent event)
			{
				if(current == -1)
					return;
				points[current] = event.getPoint();
				repaint();
			}
		});
		current = -1;
	}
	
	public void initPoints(int num)
	{
		Point2D p1= new Point2D.Double(70,400);
		Point2D p2= new Point2D.Double(200,100);
		Point2D p3= new Point2D.Double(400,100);
		Point2D p4= new Point2D.Double(500,400);
		points = new Point2D[]{p1,p2,p3,p4};
//		for(int i = 0; i < points.length; i++)
//		{
//			double x = generator.nextDouble()*600;
//			double y = generator.nextDouble()*600;
//			points[i] = new Point2D.Double(x, y);
//		}
	}
	
	public Point2D cubicBezier(double t, Point2D[] p)
	{
		Point2D[] temp = new Point2D[p.length];
		for(int k=0; k < p.length; k++)
			temp[k]=p[k];
		for(int i=0; i< 3; i++)
		{
			for(int j = 0; j < 4-i-1 ; j++)
			{
				double x = (1-t)*temp[j].getX() + t*temp[j+1].getX();
				double y = (1-t)*temp[j].getY()+ t*temp[j+1].getY();
				temp[j] = new Point2D.Double(x,y);
			}
		}
		return temp[0];
	}
	
	public void drawBezier(Graphics g, Point2D[] p)
	{
		g.setColor(Color.red);
		for(double t = 0; t < 1; t+=0.002)
		{
			Point2D p1= cubicBezier(t,p);
			Point2D p2 = cubicBezier(t+0.001,p);
			g.drawLine((int)p1.getX(),(int)p1.getY(),(int)p2.getX(),(int)p2.getY());
		}
		g.setColor(Color.blue);
		g.drawLine((int)points[0].getX(), (int)points[0].getY(), (int)points[1].getX(), (int)points[1].getY());
		g.drawLine((int)points[1].getX(), (int)points[1].getY(), (int)points[2].getX(), (int)points[2].getY());
		g.drawLine((int)points[2].getX(), (int)points[2].getY(), (int)points[3].getX(), (int)points[3].getY());
	}
	
	public void paintComponent(Graphics g)
	{
		if(points == null) return;
		Graphics2D g2 = (Graphics2D) g;
		for(int i = 0; i < points.length; i++)
		{
			double x = points[i].getX() - SIZE/2;
			double y = points[i].getY() - SIZE/2;
			g.setColor(Color.cyan);
			g2.fill(new Rectangle2D.Double(x, y, SIZE, SIZE));
			g.setColor(Color.MAGENTA);
			g.drawString(new String("("+points[i].getX()+","+(points[i].getY()-30)+")"), (int)points[i].getX(), (int)points[i].getY());
		}
		
		drawBezier(g,points);
	}
}
