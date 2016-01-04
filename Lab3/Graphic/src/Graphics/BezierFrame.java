package Graphics;

import java.awt.*;
import java.awt.geom.Point2D;

import javax.swing.*;

public class BezierFrame extends JFrame
{
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				Point2D v = new Point2D.Double(1, 2);
				v.setLocation(4, 5);
				System.out.println(v);
				JFrame frame = new JFrame();
				frame.setTitle("BezierTest");
				frame.setSize(600,600);
				
//				BezierPanel bezier = new BezierPanel();
//				bezier.setPreferredSize(new Dimension(580, 580));
//				frame.add(bezier, BorderLayout.CENTER);
				BnurbsPanel bnurbs = new BnurbsPanel();
				bnurbs.setPreferredSize(new Dimension(580, 580));
				frame.add(bnurbs, BorderLayout.CENTER);
			
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
	
}
