package Package1;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Node  extends JButton implements ActionListener
{
	Node parent;
	int col;
	int row;
	int cost;
	boolean start =false;
	boolean goal =false;
	boolean solid =false ;
	boolean open =false ;
	boolean closed=false;
	
	public Node(int col,int row)
	{
		this.col=col;
		this.row=row;
		
		setBackground(Color.white);
		setForeground(Color.green);
		addActionListener(this);
	
	}
	
	public void setAsStart()
	{
		setBackground(Color.BLUE);
		setForeground(Color.white);
		setText("Start");
		start=true;
	}
	
	public void setAsGoal()
	{
		setBackground(Color.red);
		setForeground(Color.white);
		setText("Goal");
		goal=true;
	}
	
	public void setSolid()
	{
		setBackground(Color.black);
		setForeground(Color.black);
		solid=true;
	}
	
	public void setAsOpen()
	{
		open=true;
	}
	
	public void setAsChecked()
	{
		if(start==false && goal==false)
		{
			//setBackground(Color.magenta);
			setForeground(Color.magenta);
		}
		closed=true;
	}
	public void setAsUnchecked()
	{
		if(start==false && goal==false && solid ==false)
		{
			setBackground(Color.white);
			setForeground(Color.green);
			
		}
		closed=false;
		open=false;
	}
	
	public void setPath()
	{
		setBackground(Color.green);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		setBackground(Color.orange);
		
	}
}