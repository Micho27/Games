import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TicTacToeGUI extends JFrame implements ActionListener,ItemListener
{
	Checkbox comp,player;
	boolean ai,turn;
	JButton buttons[]=new JButton[9];
	JButton reset;
	JPanel board;
	Icon x,xWin,o,oWin,icon;
	boolean won;
	
	//constructor
	public TicTacToeGUI()
	{
		super("Tic Tac Toe by Michael Kane");
		
		CheckboxGroup cbg=new CheckboxGroup();
		comp=new Checkbox("vs computer",cbg,false);
		player=new Checkbox("vs player",cbg,false);
		comp.setBounds(120,80,100,40);
		player.setBounds(120,150,100,40);
		add(comp);add(player);
		comp.addItemListener(this);
		player.addItemListener(this);
		
		turn=true;//true means x false means o
		x=new ImageIcon("C:\\Users\\micha\\Desktop\\Coding java\\Tic Tac Toe\\ic1.jpg");
		o=new ImageIcon("ic2.jpg");
		xWin=new ImageIcon("ic11.jpg");
		oWin=new ImageIcon("ic22.jpg");
		won=false;
		
		setLayout(new BorderLayout());
		setSize(1200,1200);
		setResizable(true);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	//makes buttons display on screen to play game
	public void showButtons()
	{
		
		//adds a board of 9 buttons to center
		board=new JPanel();
		board.setLayout(new GridLayout(3,3));
		for(int i=0;i<buttons.length;i++)
		{
			buttons[i]=new JButton();
			buttons[i].addActionListener(this);
			board.add(buttons[i]);
		}
		
		this.add(board,BorderLayout.CENTER);
		
		//add reset button to bottom
		reset=new JButton("Reset");
		reset.addActionListener(this);
		this.add(reset,BorderLayout.SOUTH);
		
		//makes everything visible
		setVisible(true);
	}
	
	//checkboxes to decide if you play ai or not
	@Override
	public void itemStateChanged(ItemEvent ie)
	{
		if(comp.getState())
			ai=true;
		if(player.getState())
			ai=false;
		//resets jframe to original
		remove(comp);remove(player);
		repaint(0,0,330,450);
		showButtons();
	}
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		//resets board
		if(ae.getSource()==reset)
		{
			for(int i=0;i<buttons.length;i++)
				buttons[i].setIcon(null);
			
			won=false;
			turn=true;
		}
		else if(!won)
		{
			//players logic
			int i;
			for(i=0;i<buttons.length;i++)
			{
				if(ae.getSource()==buttons[i])
				{
					if(buttons[i].getIcon()==null)
					{
						if(turn)
						{icon=x;} 
						else
						{ icon=o;}
						
						buttons[i].setIcon(icon);
						break;
					}
					else
					{
						JOptionPane.showMessageDialog(this,"Please select a tilethat hasnt been chosen before.","Oopsie!",JOptionPane.INFORMATION_MESSAGE);
						return;
					}
				}
			}
			
			if(checkAll(i))//check for a winning move
			{
				if(turn)
					JOptionPane.showMessageDialog(this,"Congratulations X has won.Click reset to play again.","Congratulations!!",JOptionPane.INFORMATION_MESSAGE);
				else
					JOptionPane.showMessageDialog(this,"Congratulations O has won.Click reset to play again.","Congratulations!!",JOptionPane.INFORMATION_MESSAGE);
			}
			else if(NotFull()&&ai)//computer logic(pick random number until free spot)
			{
				turn=!turn;
				Random ran=new Random();
				int opp;
				
				while(true)
				{
					opp=ran.nextInt(8);

					//ensures spot isnt taken
					if(buttons[opp].getIcon()==null)
					{
						buttons[opp].setIcon(o);
						break;
					}
				}
				
				if(checkAll(opp))//check for winning move
					JOptionPane.showMessageDialog(this,"Congratulations O has won.Click reset to play again.","Congratulations!!",JOptionPane.INFORMATION_MESSAGE);
			}
			turn=!turn;
		}
	}
	
	public boolean NotFull()
	{
		for(int i=0;i<buttons.length;i++)
			if(buttons[i].getIcon()==null)//once one free spot is found
				return true;
		
		JOptionPane.showMessageDialog(this, "Game has ended in a Draw. Click reset.","Game Over!!",JOptionPane.INFORMATION_MESSAGE);
		return false;
	}
	
	//logic to check if either player has won
	public boolean checkAll(int spot)
	{
		/* board layout
		 * 0|1|2
		 * -----
		 * 3|4|5
		 * -----
		 * 6|7|8
		 */
		if(spot==0)
		{
			if(checkWin(0,1,2)||checkWin(0,3,6)||checkWin(0,4,8))
				return true;
		}
		else if(spot==1)
		{
			if(checkWin(0,1,2)||checkWin(1,4,7))
				return true;
		}
		else if(spot==2)
		{
			if(checkWin(0,1,2)||checkWin(2,5,8)||checkWin(2,4,6))
				return true;
		}
		else if(spot==3)
		{
			if(checkWin(3,4,5)||checkWin(0,3,6))
				return true;
		}
		else if(spot==4)
		{
			if(checkWin(3,4,5)||checkWin(1,4,7)||checkWin(0,4,8)||checkWin(2,4,6))
				return true;
		}
		else if(spot==5)
		{
			if(checkWin(3,4,5)||checkWin(2,5,8))
				return true;
		}
		else if(spot==6)
		{
			if(checkWin(6,7,8)||checkWin(0,3,6)||checkWin(2,4,6))
				return true;
		}
		else if(spot==7)
		{
			if(checkWin(6,7,8)||checkWin(1,4,7))
				return true;
		}
		else if(spot==8)
		{
			if(checkWin(6,7,8)||checkWin(0,4,8)||checkWin(2,5,8))
				return true;
		}
		
		return false;
	}
	public boolean checkWin(int spot1,int spot2,int spot3)
	{
		if(turn)icon=xWin;
		else icon=oWin;
		
		try{
			//if spot1==spot2 and spot2==spot3.  Then spot 1==spot3 so win
			if(buttons[spot1].getIcon().equals(buttons[spot2].getIcon())&&buttons[spot2].getIcon().equals(buttons[spot3].getIcon()))
			{
				buttons[spot1].setIcon(icon);
				buttons[spot2].setIcon(icon);
				buttons[spot3].setIcon(icon);
				won=true;
				return true;
			}
			else
				return false;
		}//end of try
		catch(java.lang.NullPointerException npe){return false;}
	}
	public static void main(String[]args)
	{new TicTacToeGUI();}
}