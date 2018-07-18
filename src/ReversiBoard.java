import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ReversiBoard extends JPanel
{
	private JPanel rboard;
	
	private RoundedButton button;
	private RoundedButton[] buttons;
	
	private int blackpiecesturned = 2;
	private int whitepiecesturned = 2;
	
	private ControlPanel cpanel;
	
	public void setBlackPiecesTurned(int blackmovesmade)
	{
		this.blackpiecesturned = blackmovesmade;
	}
	
	public void setWhitePiecesTurned(int whitemovesmade)
	{
		this.whitepiecesturned = whitemovesmade;
	}
	
	public ReversiBoard(ControlPanel ctrlp)
	{
		this.cpanel = ctrlp;
	}
	
	public JPanel createComponents()
	{
		setButtons(new RoundedButton[64]);
		
		rboard = new JPanel(new GridLayout(8,8));
		
		for(int i = 0; i < 64; i++)
		{
			button = new RoundedButton();
			
			getButtons()[i] = button;
			
			rboard.add(getButtons()[i]);
			
			getButtons()[i].addActionListener(new ClickListener());
			
			if(i == 27 || i == 36)
			{
				getButtons()[i].setBlack();
				getButtons()[i].setEnabled(false);
				
			} else if(i == 28 || i == 35)
			{
				getButtons()[i].setWhite();
				getButtons()[i].setEnabled(false);
				
			}
		}
		
		
		return rboard;
	}
	
	public void reset()
	{
		for(int i = 0; i < 64; i++)
		{
			
			if(!(i == 27 || i == 36 || i == 28 || i==35))
			{
				Reversi.rboard.buttons[i].setGray();
				Reversi.rboard.buttons[i].repaint();
			}
			
			
		}
		
	}
	
	public RoundedButton[] getButtons() {
		return buttons;
	}

	public void setButtons(RoundedButton[] buttons) {
		this.buttons = buttons;
	}
	
	public void resetbuttons(String[] openinggame)
	{
		blackpiecesturned = 0;
		whitepiecesturned = 0;
		for(int j=0; j<64; j++)
		{
			if(openinggame[j].equalsIgnoreCase("G"))
			{
				Reversi.rboard.buttons[j].setGray();
				Reversi.rboard.buttons[j].setEnabled(true);
				Reversi.rboard.buttons[j].repaint();
			} else if(openinggame[j].equalsIgnoreCase("B"))
			{
				Reversi.rboard.buttons[j].setBlack();
				Reversi.rboard.buttons[j].setEnabled(false);
				Reversi.rboard.buttons[j].repaint();
				blackpiecesturned++;
				Reversi.cpanel.setBlackMove(blackpiecesturned);
				Reversi.cpanel.setNoBlackMove(blackpiecesturned);
			} else if(openinggame[j].equalsIgnoreCase("W"))
			{
				Reversi.rboard.buttons[j].setWhite();
				Reversi.rboard.buttons[j].setEnabled(false);
				Reversi.rboard.buttons[j].repaint();
				whitepiecesturned++;
				cpanel.setWhiteMove(whitepiecesturned);
				cpanel.setNoWhiteMove(whitepiecesturned);
			}
		}
		
		//return Reversi.rboard;
		
	}

	class ClickListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent ae)
		{
			if(cpanel.isWhiteTurn() == true)
			{
				RoundedButton cbutton = ((RoundedButton) ae.getSource());
				if (cbutton.isGray())
				{
					++whitepiecesturned;
					cbutton.setWhite();
					
					cpanel.setWhiteMove(whitepiecesturned);
					cpanel.setNoWhiteMove(whitepiecesturned);
				}
//				} else
//				{
//					return;
//				}
				
				
				
				
								
			}  
				
			if(cpanel.isWhiteTurn() == false)
			{
				RoundedButton cbutton = ((RoundedButton) ae.getSource());
				
				if(cbutton.isGray())
				{
					++blackpiecesturned;
					cbutton.setBlack();
					cpanel.setBlackMove(blackpiecesturned);
					cpanel.setNoBlackMove(blackpiecesturned);
					
				} else
				{
					return;
				}
				
				
			}
		}
	}
	
}
