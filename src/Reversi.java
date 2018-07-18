/*
 * Joshua Abuto
 * Assignment 11
 * Only the save button works, I was not able to get the load button to function accordingly
 * I discussed the functionality of the assignment with James Hortman and Jai Xiong
 */
import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Reversi extends JFrame 
{
	private static final int HEIGHT = 600;
	private static final int WIDTH = 600;
	static ControlPanel cpanel ;
	static ReversiBoard rboard;
	
	public Reversi()
	{
		setTitle("Reversi - CompSci 251");
		setSize(WIDTH, HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		createComponents();
		setVisible(true);
		
	}
	
	public ControlPanel getCPanel()
	{
		return this.cpanel;
	}
	
	public ReversiBoard getRBoard()
	{
		return this.rboard;
	}
	
	public void createComponents()
	{
		cpanel = new ControlPanel();
		add(cpanel.createComponents(), BorderLayout.EAST);
		rboard = new ReversiBoard(cpanel);
		add(rboard.createComponents(), BorderLayout.CENTER);
		
	}
	
	public static void main(String[] args)
	{
		new Reversi();
	}
	
	
	
	
	
}
