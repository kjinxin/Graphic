package Graphics;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class Home extends JFrame {

	private JPanel contentPane;
	public MyDrawP panel;
	public JComboBox<String> comboBox_1;
	public JComboBox<String> comboBox;
	public JComboBox<String> comboBox_2;
	int x = 0, y = 0;
	int point[][]=new int[50][2];
	int count = 0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
					Home frame = new Home();
					frame.setVisible(true);
					frame.go();
	}

	/**
	 * Create the frame.
	 */
	public Home() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		setTitle("Drawing Board");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(Color.white);
		
		comboBox = new JComboBox<String>();
		
		comboBox.setBackground(Color.WHITE);
		comboBox.setForeground(Color.BLUE);
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Drawgraph", "Fillgraph"}));
		comboBox.setToolTipText("");
		comboBox.setBounds(65, 10, 103, 25);
		contentPane.add(comboBox);
		
		comboBox_1 = new JComboBox<String>();
		comboBox_1.setModel(new DefaultComboBoxModel<String>(new String[] {"Line", "Circle", "Oral", "Rec", "Polygon"}));
		comboBox_1.setForeground(Color.BLUE);
		comboBox_1.setBackground(Color.WHITE);
		comboBox_1.setBounds(208, 10, 80, 25);
		System.out.print(comboBox.getSelectedIndex());
		contentPane.add(comboBox_1);
		panel = new MyDrawP();
		panel.setBounds(0, 40, 600, 320);
		contentPane.add(panel);
		
		JButton btnNewButton = new JButton("Finish");
		btnNewButton.setForeground(Color.RED);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//System.out.println("jinxin hello world!");
				panel.repaint();
			}
		});
		btnNewButton.setBounds(440, 10, 80, 25);
		contentPane.add(btnNewButton);
		
		comboBox_2 = new JComboBox<String>();
		comboBox_2.setForeground(Color.BLUE);
		comboBox_2.setModel(new DefaultComboBoxModel<String>(new String[] {"green", "blue", "cyan", "black", "magenta", "orange", "pink", "red", "yellow"}));
		comboBox_2.setBounds(330, 10, 80, 25);
		contentPane.add(comboBox_2);
	}
	public void go()
	{
		
		/*for (int i = 0 ; i< 124; i++,x++,y++)
		{
			x++;
			panel.repaint();
			try{
				Thread.sleep(50);
			} catch(Exception ex){}
		}*/
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				point[count][0] = e.getX();
				point[count][1] = e.getY();
				count ++;
				System.out.println(count);
			}
		});
	}
	class MyDrawP extends JPanel{

		public void paintComponent(Graphics g){
			g.setColor(Color.white);
			g.fillRect(0, 0, 600, 320);
			switch(comboBox_2.getSelectedIndex()){
			case 0:
				g.setColor(Color.green);
				break;
			case 1:
				g.setColor(Color.blue);
				break;
			case 2:
				g.setColor(Color.cyan);
				break;
			case 3:
				g.setColor(Color.black);
				break;
			case 4:
				g.setColor(Color.magenta);
				break;
			case 5:
				g.setColor(Color.orange);
				break;
			case 6:
				g.setColor(Color.pink);
				break;
			case 7:
				g.setColor(Color.red);
				break;
			case 8:
				g.setColor(Color.yellow);
				break;
			}
			//g.fillRect(x1, y1, x2 - x1, y2 - y1);
			System.out.println(comboBox_1.getSelectedIndex());
			switch(comboBox_1.getSelectedIndex()){
				case 0:
					if (count > 1)
					drawline(g, point[0][0], point[0][1], point[1][0], point[1][1]);
					count = 0;
					break;
				case 1:
					if (count > 1)
					drawcircle(g);
					count = 0;
					break;
				case 2:
					if (count > 2)
					draworal(g);
					count = 0;
					break;
				case 3:
					if (count > 1)
					drawrec(g);
					count = 0;
					break;
				case 4:
					if (count > 1)
					drawpologon(g);
					count = 0;
					break;
			}
		}
		public void drawline(Graphics g, int x1, int y1, int x2, int y2){
			int dx = x2 - x1;
		     int dy = y2 - y1;
		     int ux;
		     if (dx > 0) ux = 1; else ux = -1;
		     int uy;
		     if (dy > 0) uy = 1; else uy = -1;
		     int x = x1, y = y1, eps;//eps为累加误差
		     eps = 0;
		     if (dx < 0) dx = -dx;
		     if (dy < 0) dy = -dy;
		     if (dx > dy) 
		     {
		         for (x = x1; x != x2+ux; x += ux)
		         {
		              //SetPixel(img, x, y);
		        	 g.drawLine(x, y, x, y);
		              eps += dy;
		              if ((eps << 1) >= dx)
		              {
		                   y += uy; eps -= dx;
		              }
		         }
		     }
		     else
		     {
		         for (y = y1; y != y2+uy; y += uy)
		         {
		              g.drawLine(x, y, x, y);
		              eps += dx;
		              if ((eps << 1) >= dy)
		              {
		                   x += ux; eps -= dy;
		              }
		         }
		     }             
		}
		public void drawcircle(Graphics g){
			int xc = point[0][0], yc = point[0][1];
			int r = (point[0][0] - point[1][0]) * (point[0][0] - point[1][0]) + (point[0][1] - point[1][1]) * (point[0][1] - point[1][1]);
			r = (int) Math.sqrt(r);
			int xk = 0, yk = r;
			double pk = 5 / 4 - r;
			for (;xk <= yk; xk ++)
			{
				g.drawLine(xk + xc, yk + yc, xk + xc, yk + yc);
				g.drawLine(yk + xc, xk + yc, yk + xc, xk + yc);
				g.drawLine(yk + xc, -xk + yc, yk + xc, -xk + yc);
				g.drawLine(xk + xc, -yk + yc, xk + xc, -yk + yc);
				g.drawLine(-xk + xc, -yk + yc, -xk + xc, -yk + yc);
				g.drawLine(-yk + xc, -xk + yc, -yk + xc, -xk + yc);
				g.drawLine(-yk + xc,  xk + yc, -yk + xc, xk + yc);
				g.drawLine(-xk + xc, yk + yc, -xk + xc, yk + yc);
				if (comboBox.getSelectedIndex() == 1)
				{
					g.drawLine(xk + xc, yk + yc, xk + xc, -yk + yc);
					g.drawLine(yk + xc, xk + yc, yk + xc, -xk + yc);
					g.drawLine(-yk + xc, xk + yc, -yk + xc, -xk + yc);
					g.drawLine(-xk + xc, yk + yc, -xk + xc, -yk + yc);
				}
				if (pk < 0){
					pk = pk + 2 * (xk + 1) + 1;
				}
				else{
					pk = pk + 2 * (xk + 1) + 1 - 2 * (yk - 1);
					yk --;
				}
			}
		}
		public void draworal(Graphics g){
			int xc = point[0][0], yc = point[0][1];
			int rx = (point[0][0] - point[1][0]) * (point[0][0] - point[1][0]) + (point[0][1] - point[1][1]) * (point[0][1] - point[1][1]);
			rx = (int) Math.sqrt(rx);
			int ry = (point[0][0] - point[2][0]) * (point[0][0] - point[2][0]) + (point[0][1] - point[2][1]) * (point[0][1] - point[2][1]);
			ry = (int) Math.sqrt(ry);
			int xk = 0, yk = ry;
			System.out.println("Hello World!");
			System.out.println(count);
			double p1k = ry * ry - rx * rx * ry + rx * rx / 4;
			for (;ry * ry * xk <= rx * rx * yk; xk ++){
				//System.out.println(xk);
				g.drawLine(xk + xc, yk + yc, xk + xc, yk + yc);
				g.drawLine(xk + xc, -yk + yc, xk + xc, -yk + yc);
				g.drawLine(-xk + xc, -yk + yc, -xk + xc, -yk + yc);
				g.drawLine(-xk + xc, yk + yc, -xk + xc, yk + yc);
				
				if (comboBox.getSelectedIndex() == 1)
				{			
					g.drawLine(xk + xc, yk + yc, xk + xc, -yk + yc);
					g.drawLine(-xk + xc, yk + yc, -xk + xc, -yk + yc);
				}
				if (p1k < 0){
					p1k = p1k + 2 * ry * ry * (xk + 1) + ry * ry;
				}
				else{
					p1k = p1k + 2 * ry * ry * (xk + 1) - 2 * rx * rx * (yk - 1)+ ry * ry;
					yk --;
				}
			}
			double p2k = ry * ry * (xk + 1 / 2) * (xk + 1 / 2) + rx * rx * (yk - 1) * (yk - 1) - rx * rx * ry * ry;
			//double p2k = ry * (xk + 1 / 2) * 2 + rx * (yk - 1) * 2 - rx * ry * 2 + 1 / 2;
			for (;xk < rx || yk >= 0; ) {
				g.drawLine(xk + xc, yk + yc, xk + xc, yk + yc);
				g.drawLine(xk + xc, -yk + yc, xk + xc, -yk + yc);
				g.drawLine(-xk + xc, -yk + yc, -xk + xc, -yk + yc);
				g.drawLine(-xk + xc, yk + yc, -xk + xc, yk + yc);
				
				if (comboBox.getSelectedIndex() == 1)
				{			
					g.drawLine(xk + xc, yk + yc, xk + xc, -yk + yc);
					g.drawLine(-xk + xc, yk + yc, -xk + xc, -yk + yc);
				}
				if (p2k > 0) {
					p2k = p2k - 2 * rx * rx * (yk - 1) + rx * rx;
					yk --;
				} 
				else {
					p2k = p2k + 2 * ry * ry * (xk + 1) - 2 * rx * rx * (yk - 1) + rx * rx;
					xk ++; yk --;
				}
			}
		}
		public void drawrec(Graphics g){
			int x1 = point[0][0], y1 = point[0][1], x2 = point[1][0], y2 = point[1][1];
			drawline(g, x1, y1, x1, y2);
			drawline(g, x1, y2, x2, y2);
			drawline(g, x2, y2, x2, y1);
			drawline(g, x2, y1, x1, y1);
			if (comboBox.getSelectedIndex() == 1){
				int t = y2; int t1 = y2;
				if (y1 < y2) t = y1;
				else t1 = y1;
				for (int i = 0; i <= t1 - t ; i ++){
					g.drawLine(x1, t + i, x2, t + i);
				}
			}
			
		}
		public void drawpologon(Graphics g){
			System.out.println(count);
			for (int i = 0; i < count; i ++) {
				System.out.println(i);
				drawline(g, point[i][0], point[i][1], point[(i + 1) % count][0], point[(i + 1) % count][1]);
			}
		}
	}
}
