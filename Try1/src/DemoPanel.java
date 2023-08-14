import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

public class DemoPanel extends JPanel{
 
	final int mcol = 15;
	final int mrow = 15;
	final int node_size = 60;
	final int screenx = node_size * mcol;
	final int screeny= node_size * mrow;
	
	
	Node[][] node = new Node[mcol][mrow];
	Node start, goal, curr;
	ArrayList<Node> openlist = new ArrayList<>();
	
	
	boolean goalreached = false;
	
	public DemoPanel()
	{
		this.setPreferredSize(new Dimension(screenx, screeny));
		this.setBackground(Color.black);
		this.setLayout(new GridLayout(mrow,mcol));
		this.addKeyListener(new KeyHandler(this));
		this.setFocusable(true);
		
		int col=0;
		int row=0;
		
		while(col<mcol && row < mrow)
		{
			node[col][row] = new Node(col,row);
			this.add(node[col][row]);
			
			col++;
			if(col == mcol)
			{
				col =0;
				row++;
			}
		}
		
		setAsStart(10,2);
		setAsGoal(3,10);
		setAsSolid( 2,3);
		setAsSolid( 3,3);
		setAsSolid( 4,4);
		setAsSolid( 5,4);
		setAsSolid( 6,6);
		setAsSolid( 5,6);
		setAsSolid( 7,6);
		setAsSolid( 7,7);
		setAsSolid( 5,5);
		setAsSolid( 8,7);
		setAsSolid( 9,7);
		setAsSolid( 10,7);
		setAsSolid( 11,7);
		
		
		setCost();
	}
	
	private void setAsStart(int row, int col)
	{
		node[col][row].setAsStart();
		start= node[col][row];
		curr=start;
	}
	private void setAsGoal(int row, int col)
	{
		node[col][row].setAsGoal();
		goal= node[col][row];
	}
	
	private void setAsSolid(int row, int col)
	{
		node[col][row].setAsSolid();
	}

	private void setCost()
	{
		for(int i=0;i<mcol;i++)
		{
			for(int j=0;j<mrow;j++)
			{
				getCost(node[i][j]);
			}
		}
	}
	
	
	private void getCost(Node node)
	{
		node.cost=Math.abs(node.col-start.col) + Math.abs(node.row-start.row) + Math.abs(node.col - goal.col) + Math.abs(node.row - goal.row);
		if(node != start && node!= goal)
		{
			node.setText("<html>F:" + node.cost + "</html>");
		}
	}
	
	public void search()
	{
		if(goalreached== false)
		{
			int col = curr.col;
			int row = curr.row;
			
			curr.setAsChecked();
			openlist.remove(curr);
			
			int x = curr.col;
			int y = curr.row;
			for (int i = (x - 1 < 0 ? 0 : x - 1); i < (x + 2 > 15 ? 15 : x + 2); i++)
			{
			    for (int j = (y - 1 < 0 ? 0 : y - 1); j < (y + 2 > 15 ? 15 : y + 2); j++)
			    {
			        if (node[i][j].open == false && node[i][j].solid==false)
			        {
			            openNode(node[i][j]);
			        }
			    }
			}
			
			if(!openlist.isEmpty())
			{
			Node minnode = openlist.get(0);
			
			for(Node node : openlist)
			{
				if(node.cost < minnode.cost)
				{
					minnode=node;
				}
			}
			
			curr=minnode;
			
			if(minnode == goal)
			{
				goalreached = true;
				track();
			}
			}
		}
	}
	
	private void openNode(Node node)
	{
		if(node.open ==false && node.closed == false && node.solid==false)
		{
			node.setAsOpen();
			node.parent = curr;
			openlist.add(node);
		}
	}
	
	private void track()
	{
		Node curr = goal;
		while(curr != start)
		{
			curr = curr.parent;
			if(curr!= start)
			{
				curr.setPath();
			}
		}
	}
	
}
