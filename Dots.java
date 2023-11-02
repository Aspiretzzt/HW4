
/*
 * name: Zhongtian Zhai
 * zzhai4@u.rochester.edu
 * last modified at 2 Nov,2023
 * Assignment name: Dots
 * Lab section: CSC 171-23
 */


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class Coord extends JPanel implements MouseListener, MouseMotionListener, KeyListener {

	private ArrayList<MouseEvent> m;
	private Point start;
	Color cc = new Color(0, 0, 0);
	private int trace = -1;

	public Coord() {
		setFocusable(true);
		m = new ArrayList<>();
		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
	}

	public void setColor(Color newc) {
		this.cc = newc;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < m.size(); i++) {
			if (m.get(i) != null) {
				int r = 4;
				g.setColor(cc);
				g.fillOval(m.get(i).getX() - r, m.get(i).getY() - r, 2 * r, 2 * r);
			}
			if (i > 0 && m.get(i - 1) != null) {
				g.setColor(new Color(0, 0, 0));
				g.drawLine(m.get(i - 1).getX(), m.get(i - 1).getY(), m.get(i).getX(), m.get(i).getY());
			}
		}
	}

	public void mouseClicked(MouseEvent e) {
		System.out.printf("Click detected at (%d,%d). \n", e.getX(), e.getY());
		m.add(e);
		repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		start = e.getPoint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		trace = -1;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	public void mouseMoved(MouseEvent e) {
	}

	public void mouseDragged(MouseEvent e) {
		if (trace == -1) {
			for (int j = 0; j < m.size(); j++) {
				double dist = 0.0;
				dist = Math.sqrt(
						Math.pow(start.getX() - m.get(j).getX(), 2) + Math.pow(start.getY() - m.get(j).getY(), 2));
				if (dist <= 4.0) {
					trace = j;
					break;
				}
			}
		}
		if (trace != -1) {
			m.set(trace, e);
			repaint();
		}

	}
}

public class Dots {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Dots");

		frame.setBounds(100, 100, 900, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JTextArea tex = new JTextArea();
		tex.setBounds(100, 100, 500, 40);
		tex.setBackground(Color.GRAY);

		Coord c = new Coord();
		c.setBounds(0, 150, 900, 750);
		frame.add(c);
		frame.add(tex);

		tex.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == 'r') {
					c.setColor(Color.RED);
				} else if (e.getKeyChar() == 'b') {
					c.setColor(Color.BLUE);
				} else if (e.getKeyChar() == 'g') {
					c.setColor(Color.GREEN);
				} else if (e.getKeyChar() == 'l') {
					c.setColor(Color.BLACK);
				}
				c.repaint();
			}
		});

		frame.setVisible(true);
	}
}