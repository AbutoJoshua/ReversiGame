import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class ControlPanel extends JPanel 
{
	private JPanel controlP = new JPanel(new GridLayout(18,1)); //The greater panel holding all controls
	private JPanel additionalP = new JPanel(new GridLayout(1, 2)); //to split the turn label that will be alternating to show who's turn it is

	private JLabel turnchange = new JLabel("Turn: ");
	private JTextField stateturn =  new JTextField(" ");

	private JLabel white = new JLabel("White: ");
	private JTextField whitemove = new JTextField(" ");
	private int nowhitemove; //number of white moves

	private JLabel black = new JLabel("black: ");
	private JTextField blackmove = new JTextField(" ");
	private int noblackmove; //number of black moves

	private JButton donemove = new JButton("Done Moving");

	private JLabel firstmove = new JLabel("First Move:");
	private JRadioButton whiteturn = new JRadioButton("White");
	private JRadioButton blackturn = new JRadioButton("Black");

	private boolean isWhiteTurn = true; //This will be used to change the radio buttons

	private JButton newgame = new JButton("New Game");
	private boolean reset;

	private JButton savegame = new JButton("Save Game");

	private JButton loadgame = new JButton("Load Game");

	// Setting who's turn it is

	public void setStateTurn(boolean w)
	{
		if(w == true)
		{
			this.stateturn.setText("White");
		}
		else if(w == false)
		{
			this.stateturn.setText("Black");
		}



	}

	//The boolean method used to change from black to white after moving

	public boolean isWhiteTurn()
	{
		return this.isWhiteTurn;
	}

	//Sets the number of black moves made
	public void setNoBlackMove(int blackmoves)
	{
		this.noblackmove = blackmoves;
	}

	// Updates the textfield for number of black moves
	public void setBlackMove(int noblackmove)
	{
		String blacktext = " " + noblackmove;
		this.blackmove.setText(blacktext);
	}

	//Sets the number of white moves made
	public void setNoWhiteMove(int whitemoves)
	{
		this.nowhitemove = whitemoves;
	}

	// Updates the textfield for number of white moves
	public void setWhiteMove(int nowhitemove)
	{
		String whitetext = " " + nowhitemove;
		this.whitemove.setText(whitetext);
	}


	//Resets when new game will be clicked
	public void reset(boolean b)
	{
		this.reset = b;
	}

	//Return the reset value for confirmation 
	public boolean getReset()
	{
		return this.reset;
	}

	public void setIsWhiteTurn(boolean wt)
	{
		this.isWhiteTurn = wt;
	}


	//Creating the components
	public JPanel createComponents()
	{
		ButtonGroup radiobuttongroup = new ButtonGroup();
		additionalP.add(turnchange);
		additionalP.add(stateturn);
		stateturn.setText("White");
		stateturn.setEnabled(true);
		stateturn.setEditable(false);

		controlP.add(additionalP);
		controlP.add(black);
		controlP.add(blackmove);
		whitemove.setEnabled(true);
		whitemove.setEditable(false);
		whitemove.setText("2");

		controlP.add(white);
		controlP.add(whitemove);
		blackmove.setEnabled(true);
		blackmove.setEditable(false);
		blackmove.setText("2");
		controlP.add(donemove);
		donemove.addActionListener(new ClickListener());

		radiobuttongroup.add(whiteturn);
		radiobuttongroup.add(blackturn);		

		controlP.add(firstmove);
		controlP.add(whiteturn);
		controlP.add(blackturn);

		whiteturn.setSelected(true);

		controlP.add(newgame);
		newgame.addActionListener(new ClickListener());

		controlP.add(savegame);
		savegame.addActionListener(new ClickListener());

		controlP.add(loadgame);
		loadgame.addActionListener(new ClickListener());



		return controlP;
	}



	class ClickListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent ev)
		{
			if((JButton) ev.getSource() == (newgame))
			{
				setNoBlackMove(2);
				setNoWhiteMove(2);

				Reversi.rboard.setBlackPiecesTurned(2);
				Reversi.rboard.setWhitePiecesTurned(2);

				whitemove.setText(Integer.toString(noblackmove));
				blackmove.setText(Integer.toString(noblackmove));

				if(whiteturn.isSelected())
				{
					isWhiteTurn = true;
					stateturn.setText("White");
				} else if (blackturn.isSelected())
				{
					isWhiteTurn = false;
					stateturn.setText("Black");
				}


				Reversi.rboard.reset();

			}

			if((JButton) ev.getSource() == (donemove))
			{
				isWhiteTurn = !isWhiteTurn; //to make it flip it should be isWhiteTurn = !isWhiteTurn; but when i do that it wont even change the first time
				setIsWhiteTurn(isWhiteTurn);
				setStateTurn(isWhiteTurn());


			}

			if ((JButton) ev.getSource() == (savegame))
			{
				String[] str = new String[64];
				for(int i = 0; i < 64; i++)
				{
					if(Reversi.rboard.getButtons()[i].isGray())
					{
						str[i] = "G";
					} else if (Reversi.rboard.getButtons()[i].isBlack())
					{
						str[i] = "B";
					} else if (Reversi.rboard.getButtons()[i].isWhite())
					{
						str[i] ="W";
					}


				}
				String savedgame = "game";

				try(PrintWriter pw = new PrintWriter(savedgame))
				{
					for(int j = 0; j < str.length ; j++)
					{
						pw.println(str[j]);
					}
				} catch(FileNotFoundException rt)
				{
					System.out.println("File not Found");
				}
			}

			if ((JButton) ev.getSource() == (loadgame))
			{
				String[] openinggame =  new String[64];
				try(Scanner sc = new Scanner(new File("game.txt")))
				{
					for(int i=0; i < 64; i++)
					{
						openinggame[i] = sc.nextLine();
					}
					
					Reversi.rboard.resetbuttons(openinggame);
					//Reversi savedgame = new Reversi();
					
//					for(int j=0; j<64; j++)
//					{
//						if(openinggame[j].equalsIgnoreCase("G"))
//						{
//							Reversi.rboard.getButtons()[j].setGray();
//							Reversi.rboard.getButtons()[j].repaint();
//						} else if(openinggame[j].equalsIgnoreCase("B"))
//						{
//							Reversi.rboard.getButtons()[j].setBlack();
//							Reversi.rboard.getButtons()[j].repaint();
//						} else if(openinggame[j].equalsIgnoreCase("W"))
//						{
//							Reversi.rboard.getButtons()[j].setWhite();
//							Reversi.rboard.getButtons()[j].repaint();
//						}
//					}

				} catch (FileNotFoundException e)
				{
					e.getMessage();
				}

			}
		}


	}


}
