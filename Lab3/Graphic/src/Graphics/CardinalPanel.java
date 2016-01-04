package Graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JComponent;

public class CardinalPanel extends JComponent{
	private ArrayList<Point2D> points;
	private static int SIZE = 10;
	public CardinalPanel()
	{
		points = new ArrayList<Point2D>();
		addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent event)
			{
				Point2D p =event.getPoint();
				points.add(p);
				repaint();
			}
	
		});
	}
	public void initPoints()
	{
		points.clear();
	}
	
	public Point2D cubicCardical(double u, Point2D p1, Point2D p2, Point2D p3, Point2D p4){
		double x, y;
		x = p1.getX() * (-0.5 * u * u * u + u * u - 0.5 * u) + p2.getX() * (1.5 * u * u * u - 2.5 * u * u + 1) + p3.getX() * (-1.5 * u * u * u + 2 * u * u + 0.5 * u)+ p4.getX() * (0.5 * u * u * u - 0.5 * u * u);
		y = p1.getY() * (-0.5 * u * u * u + u * u - 0.5 * u) + p2.getY() * (1.5 * u * u * u - 2.5 * u * u + 1) + p3.getY() * (-1.5 * u * u * u + 2 * u * u + 0.5 * u)+ p4.getY() * (0.5 * u * u * u - 0.5 * u * u);
		Point2D p = new Point2D.Double(x,y);
		return p;
	}
	public void drawCarnical(Graphics g, ArrayList<Point2D> p){
		g.setColor(Color.red);
		for(int i = 0; i < p.size() - 3; i ++){
			for(double t = 0; t < 1; t+=0.002)
			{
				Point2D p1= cubicCardical(t, p.get(i), p.get(i + 1), p.get(i + 2), p.get(i + 3));
				Point2D p2 = cubicCardical(t+0.001, p.get(i), p.get(i + 1), p.get(i + 2), p.get(i + 3));
				g.drawLine((int)p1.getX(),(int)p1.getY(),(int)p2.getX(),(int)p2.getY());
			}
		}
	}
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		for(int i = 0; i < points.size(); i++)
		{
			double x = points.get(i).getX() - SIZE/2;
			double y = points.get(i).getY() - SIZE/2;
			g.setColor(Color.cyan);
			g2.fill(new Rectangle2D.Double(x, y, SIZE, SIZE));
			g.setColor(Color.MAGENTA);
			g.drawString(new String("("+points.get(i).getX()+","+(points.get(i).getY()-30)+")"), (int)points.get(i).getX(), (int)points.get(i).getY());
		}
		
		g.setColor(Color.BLUE);
		for(int i = 0; i < points.size() - 1; i++)
		{
			g.drawLine((int)points.get(i).getX(), (int)points.get(i).getY(), (int)points.get(i + 1).getX(), (int)points.get(i + 1).getY());
		}
		if(points.size() < 4) return;
		drawCarnical(g, points);
	}
}
