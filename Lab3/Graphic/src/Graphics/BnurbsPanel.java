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

public class BnurbsPanel extends JComponent{
	private ArrayList<Point2D> points;
	private static int SIZE = 10;
	public static int MAX = 100,COUNT = 100;
	public static int degree = 2;
	public static double T[] = new double[MAX];
	public BnurbsPanel()
	{
		points = new ArrayList<Point2D>();
		T[0] = 0.0;
		for( int i = 1 ; i < MAX ; ++ i )
		{
			T[i] = T[i-1] + 1.0 ;
		}
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
		T[0] = 0.0;
		for( int i = 1 ; i < MAX ; ++ i )
		{
			T[i] = T[i-1] + 1.0 ;
		}
		points.clear();
	}
	
	public void Calc(double T[], double t, int j, Point2D V)
	{
		int i , r , temp , temp1;
		Point2D Q[] = new Point2D[MAX];
		double lamta;

		temp = j - degree;
		for(i = 0; i <= degree; i ++)
		{
			Q[i] = new Point2D.Double(points.get(temp+i).getX(),  points.get(temp+i).getY());
		}
		for( r = 1 ; r <= degree ; ++ r )
		{
			for( i = j ; i >= temp + r ; -- i )
			{
				lamta = (t-T[i])/(T[i + degree - r + 1] - T[i]);
				temp1 = i - temp;
				Q[temp1].setLocation(lamta*((double)Q[temp1].getX())+(1.0-lamta)*((double)Q[temp1-1].getX()), lamta*((double)Q[temp1].getY())+(1.0-lamta)*((double)Q[temp1-1].getY())); 
			}
		}
		V.setLocation(Q[degree].getX(), Q[degree].getY());
	}	
	
	public void drawBnurbs(Graphics g, ArrayList<Point2D> p){
		int i , j;
		double deltat , t;
		Point2D V = new Point2D.Double(0.0,0.0),newV = new Point2D.Double(0.0,0.0);

		g.setColor(Color.red);
		
		deltat = (T[points.size()]-T[degree])/COUNT;
		t = T[degree];
		j = degree;
		
		
		Calc(T,t,j,V);
		
		for(i = 1; i < COUNT ; ++ i)
		{
			t += deltat;
			while(t > T[j+1])
			{
				j++;
			}
			Calc(T,t,j,newV);
			
			g.drawLine((int)V.getX(),(int)V.getY(),(int)newV.getX(),(int)newV.getY());
			V.setLocation(newV.getX(), newV.getY());
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
		if(points.size() < 3) return;
		drawBnurbs(g, points);
	}
}

