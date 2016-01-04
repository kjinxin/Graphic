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

import java.util.ArrayList;
import java.util.List;

public class Home extends JFrame {

	private JPanel contentPane;
	public MyDrawP panel;
	public JComboBox<String> comboBox_1;
	public JComboBox<String> comboBox;
	public JComboBox<String> comboBox_2;
	int flag = 0, flag1 = 0;
	int window1 = 2, graph1 = 0; 
	List<Point> points = new ArrayList<Point>();
	List<Point> point1 = new ArrayList<Point>();
	List<Vector> vectors = new ArrayList<Vector>();
//	Point point = new Point(0,0);
//	int x = 0, y = 0;
//	int point[][]=new int[50][2];
//	int count = 0;
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
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Window", "Graph"}));
		comboBox.setToolTipText("");
		comboBox.setBounds(70, 10, 103, 25);
		contentPane.add(comboBox);
		
		
		comboBox_2 = new JComboBox<String>();
		comboBox_2.setForeground(Color.BLUE);
		comboBox_2.setModel(new DefaultComboBoxModel<String>(new String[] {"green", "blue", "cyan", "magenta", "orange", "pink", "red", "yellow"}));
		comboBox_2.setBounds(404, 10, 80, 25);
		contentPane.add(comboBox_2);
		
		comboBox_1 = new JComboBox<String>();
		comboBox_1.setModel(new DefaultComboBoxModel<String>(new String[] {"Line", "Triangle", "Rectangle", "Polygon"}));
		comboBox_1.setForeground(Color.BLUE);
		comboBox_1.setBackground(Color.WHITE);
		comboBox_1.setBounds(295, 10, 80, 25);
		contentPane.add(comboBox_1);
		panel = new MyDrawP();
		panel.setBounds(0, 40, 600, 320);
		contentPane.add(panel);
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
//				point[count][0] = e.getX();
//				point[count][1] = e.getY();
//				count ++;
//				System.out.println(count);
				if (comboBox.getSelectedIndex() == 0) {
					if (flag == 0) {
						points.clear();
						flag = 1;
					}
					switch (comboBox_1.getSelectedIndex()) {
						case 1:
							points.add(new Point(e.getX(), e.getY()));
							if (points.size() == 3) {
								flag = 0;
								window1 = 1;
								panel.repaint();
							}
							break;
						case 2:
							points.add(new Point(e.getX(), e.getY()));
							if (points.size() == 2) {
								Point point1 = points.get(0);
								Point point2 = points.get(1);
								points.clear();
								points.add(point1);
								points.add(new Point(point1.getX(), point2.getY()));
								points.add(point2);
								points.add(new Point(point2.getX(), point1.getY()));
								flag = 0;
								window1 = 2;
								panel.repaint();
							}
							break;
						case 3:
							if (e.getClickCount() == 1) {
								points.add(new Point(e.getX(), e.getY()));
							}
							if (e.getClickCount() == 2) {
								flag = 0;
								window1 = 3;
								panel.repaint();
							}
							break;
					}
				}
				else
				{
					switch (comboBox_1.getSelectedIndex()) {
						case 0:
							if (graph1 != 0) {
								graph1 = 0;
								point1.clear();
							}
							point1.add(new Point(e.getX(), e.getY()));
							if (point1.size() % 2 == 0) {
								panel.repaint();
							}
							break;
						case 1:
							if (graph1 != 1){
								graph1 = 1;
								point1.clear();
							}
							point1.add(new Point(e.getX(), e.getY()));
							if (point1.size() % 3 == 0) {
								panel.repaint();
							}
							break;
						case 2:
							if (graph1 != 2){
								graph1 = 2;
								point1.clear();
							}
							point1.add(new Point(e.getX(), e.getY()));
							System.out.println(point1.size());
							if (point1.size() % 2 == 0) {
								Point point_1 = point1.get(point1.size() - 2);
								Point point_2 = point1.get(point1.size() - 1);
								point1.set(point1.size() - 1, new Point(point_1.getX(), point_2.getY()));
								point1.add(point_2);
								point1.add(new Point(point_2.getX(), point_1.getY()));
								panel.repaint();
							}
							break;
						case 3:
							if (flag1 == 0) {
								point1.clear();
								flag1 = 1;
							}
							if (e.getClickCount() == 1) {
								point1.add(new Point(e.getX(), e.getY()));
							}
							if (e.getClickCount() == 2) {
								flag1 = 0;
								graph1 = 3;
								panel.repaint();
							}
							break;
					}
				}
			}
		});
	}
	class MyDrawP extends JPanel{

		public void paintComponent(Graphics g){
			g.setColor(Color.white);
			g.fillRect(0, 0, 600, 320);
//			int xx[] = new int[5];
//			int yy[] = new int[5];
//			g.setColor(Color.red);
//			xx[0] = 50; yy[0] = 50; xx[1] = 50; yy[1] = 150; xx[2] = 60; yy[2] = 60; xx[3] = 150; yy[3] = 50;
//			g.fillPolygon(xx, yy, 4);
			
			// draw the window
			g.setColor(Color.gray);
			int xx[] = new int[100];
			int yy[] = new int[100];
			for (int i = 0; i < points.size(); i ++) {
				xx[i] = (int) points.get(i).getX();
				yy[i] = (int) points.get(i).getY();
			}
			g.drawPolygon(xx, yy, points.size());
			
			// draw the graph
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
				g.setColor(Color.magenta);
				break;
			case 4:
				g.setColor(Color.orange);
				break;
			case 5:
				g.setColor(Color.pink);
				break;
			case 6:
				g.setColor(Color.red);
				break;
			case 7:
				g.setColor(Color.yellow);
				break;
			}
			switch(graph1){
				case 0:
					for (int i = 0; i < point1.size() / 2; i ++){
						g.drawLine((int) point1.get(i * 2).getX(), (int)point1.get(i * 2).getY(), (int)point1.get(i * 2 + 1).getX(), (int)point1.get(i * 2 + 1).getY());
					}
					break;
				case 1:
					for (int i = 0; i < point1.size() / 3; i ++){
						for (int j = 0; j < 3; j ++)
						{
							xx[j] = (int) point1.get(i * 3 + j).getX();
							yy[j] = (int) point1.get(i * 3 + j).getY();
						}
						g.fillPolygon(xx, yy, 3);
					}
					break;
				case 2:
					for (int i = 0; i < point1.size() / 4; i ++){
						for (int j = 0; j < 4; j ++)
						{
							xx[j] = (int) point1.get(i * 4 + j).getX();
							yy[j] = (int) point1.get(i * 4 + j).getY();
						}
						g.fillPolygon(xx, yy, 4);
					}
					break;
				case 3:
					for (int i = 0; i < point1.size(); i ++) {
						xx[i] = (int) point1.get(i).getX();
						yy[i] = (int) point1.get(i).getY();
					}
					g.fillPolygon(xx, yy, point1.size());
					break;
			}
			// draw the cutted graph
			g.setColor(Color.black);
			switch(graph1){
				case 0:
					for (int i = 0; i < point1.size() / 2; i ++){
						vectors.clear();
						vectors.add(new Vector(point1.get(i * 2), point1.get(i * 2 + 1)));
						vectors.add(new Vector(point1.get(i * 2 + 1), point1.get(i * 2)));
						List<Point> result = Sutherland_Hodgeman(points, vectors);
						for (int j = 0; j < result.size(); j ++) {
							xx[j] = (int) result.get(j).getX();
							yy[j] = (int) result.get(j).getY();
						}
						g.fillPolygon(xx, yy, result.size());
					}
					break;
				case 1:
					for (int i = 0; i < point1.size() / 3; i ++){
						vectors.clear();
						vectors.add(new Vector(point1.get(i * 3), point1.get(i * 3 + 1)));
						vectors.add(new Vector(point1.get(i * 3 + 1), point1.get(i * 3 + 2)));
						vectors.add(new Vector(point1.get(i * 3 + 2), point1.get(i * 3)));
						List<Point> result = Sutherland_Hodgeman(points, vectors);
						for (int j = 0; j < result.size(); j ++) {
							xx[j] = (int) result.get(j).getX();
							yy[j] = (int) result.get(j).getY();
						}
						g.fillPolygon(xx, yy, result.size());
					}
					break;
				case 2:
					for (int i = 0; i < point1.size() / 4; i ++){
						vectors.clear();
						vectors.add(new Vector(point1.get(i * 4), point1.get(i * 4 + 1)));
						vectors.add(new Vector(point1.get(i * 4 + 1), point1.get(i * 4 + 2)));
						vectors.add(new Vector(point1.get(i * 4 + 2), point1.get(i * 4 + 3)));
						vectors.add(new Vector(point1.get(i * 4 + 3), point1.get(i * 4)));
						List<Point> result = Sutherland_Hodgeman(points, vectors);
						for (int j = 0; j < result.size(); j ++) {
							xx[j] = (int) result.get(j).getX();
							yy[j] = (int) result.get(j).getY();
						}
						g.fillPolygon(xx, yy, result.size());
					}
					break;
				case 3:
					vectors.clear();
					for (int i = 0; i < point1.size() - 1; i ++) {
						vectors.add(new Vector(point1.get(i), point1.get(i + 1)));
					}
					vectors.add(new Vector(point1.get(point1.size() - 1), point1.get(0)));
					List<Point> result = Sutherland_Hodgeman(points, vectors);
					for (int i = 0; i < result.size(); i ++) {
						xx[i] = (int) result.get(i).getX();
						yy[i] = (int) result.get(i).getY();
					}
					g.fillPolygon(xx, yy, result.size());
					System.out.println("jinxin is a good man!");
					System.out.println(result.size());
					System.out.println(points.size());
					System.out.println(point1.size());
					System.out.println(vectors.size());
					break;
			}
		}
		
	}
	 //裁剪算法
    public static List<Point> Sutherland_Hodgeman(List<Point> points,List<Vector> vectors){
        List<Point> result = new ArrayList<Point>();
        List<Point> cur = new ArrayList<Point>();
         
        int vectorsSize = vectors.size();
        int pointSize = points.size();
         
        Point S = points.get(pointSize-1);
        //初始化操作的集合
        for(int i=0;i<pointSize;i++){
            result.add(points.get(i));
        }
 
        boolean flag;
        for(int j=0;j<vectorsSize;j++){
            //flag = false表示在内侧，flag = true表示在外侧
            if(isInside(S,vectors.get(j)))
                flag = false;
            else
                flag = true;
            int resultSize = result.size();
            for(int i=0;i<resultSize;i++){
                //证明其在当前vector的内
                if(isInside(result.get(i),vectors.get(j))){
                    //如果前一个点在vector的外侧，那么将他们的交点加入到结果集中
                    if(flag){
                        flag = false;
                        cur.add(Intersection(S, result.get(i), vectors.get(j).start, vectors.get(j).end));
                    }
                    //并将当前节点加入到结果集中
                    cur.add(result.get(i));
                }
                else{
                    //前一个点在外侧吗？
                    if(!flag){
                        flag = true;
                        //如果前一个点在vector的内侧，那么将他们的交点加入到结果集中
                        cur.add(Intersection(S, result.get(i), vectors.get(j).start, vectors.get(j).end));
                    }
                }
                //更新首次比较的节点
                S = result.get(i);
            }
            //将本次结果拷贝出来，作为下次对比的样本，并将本次结果进行清空
            int resultLen = cur.size();
            result.clear();
            for(int i=0;i<resultLen;i++){
                result.add(cur.get(i));
            }
            cur.clear();
        }
        return result;
    }
     
    //求一个点是否在一条边的内侧，在点序为逆时针的时候（如果点在线上，也算在内侧）
    public static boolean isInside(Point p,Vector v){
        return Multi(p,v.start,v.end)>=0?true:false;
    }
     
     
    //求叉积p0->p1与p0->p2的叉积
    public static double Multi(Point p0,Point p1,Point p2){
        return (p1.x-p0.x)*(p2.y-p0.y)-(p2.x-p0.x)*(p1.y-p0.y);
    }
     
    public static Point Intersection(Point start0,Point end0,Point start1,Point end1){
        //由正弦定理推出
        double pX = (Multi(start0, end1, start1)*end0.x - Multi(end0, end1, start1)*start0.x)/
                (Multi(start0, end1, start1) - Multi(end0, end1, start1));
        double pY = (Multi(start0, end1, start1)*end0.y - Multi(end0, end1, start1)*start0.y)/
                (Multi(start0, end1, start1) - Multi(end0, end1, start1));
        return new Point(pX,pY);
    }
}
