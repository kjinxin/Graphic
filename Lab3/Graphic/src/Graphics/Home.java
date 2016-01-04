package Graphics;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Home extends JFrame {
	private JComboBox<String> comboBox;
	private BezierPanel bezier;
	private CardinalPanel cardinal;
	private BnurbsPanel bnurbs;
	public Home() {
		setTitle("Curve");
		setSize(600,600);
		
		bezier = new BezierPanel();
		cardinal = new CardinalPanel();
		bnurbs = new BnurbsPanel();

		
		comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Cardinal", "Bezier", "BNurbs"}));
		comboBox.setBounds(0, 0, 74, 21);
		getContentPane().add(comboBox, BorderLayout.NORTH);
		getContentPane().add(cardinal,BorderLayout.CENTER);
		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switch (comboBox.getSelectedIndex()){
				case 0:
					getContentPane().removeAll();
					getContentPane().add(comboBox, BorderLayout.NORTH);
					cardinal.initPoints();
					getContentPane().add(cardinal,BorderLayout.CENTER);
					getContentPane().revalidate();
					getContentPane().repaint();
					repaint();
					break;
				case 1:
					getContentPane().removeAll();
					getContentPane().add(comboBox, BorderLayout.NORTH);
					bezier.initPoints(4);
					getContentPane().add(bezier,BorderLayout.CENTER);
					getContentPane().revalidate();
					getContentPane().repaint();
					break;
				case 2:
				//	new BLine().init();
					getContentPane().removeAll();
					getContentPane().add(comboBox, BorderLayout.NORTH);
					bnurbs.initPoints();
					getContentPane().add(bnurbs, BorderLayout.CENTER);
					getContentPane().revalidate();
					getContentPane().repaint();
					break;
				}
			}
		});
	
	}
	public static void main(String[] args)
	{
		Home frame = new Home();
		//frame.getContentPane().add(frame.cardinal,BorderLayout.CENTER);
		//frame.go();
		frame.setVisible(true);
	}
	
}
