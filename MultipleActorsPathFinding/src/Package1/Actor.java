package Package1;

import java.util.ArrayList;

public class Actor {

	Node start, goal, curr;
	ArrayList<Node> openlist = new ArrayList<>();
	
	boolean goalreached = false;
	
	Node[][] node = Map.getNodeArray();
	int mrow=Map.getMrow();
	int mcol=Map.getMcol();
	
	public Actor() {};
	
	public Actor(int xs,int ys,int xg,int yg)
	{
		setAsStart(xs,ys);
		setAsGoal(xg,yg);
		
	}
	
	
	public void setAsStart(int row, int col)
	{
		node[col][row].setAsStart();
		start= node[col][row];
		curr=start;
		curr.solid=true;
	}
	public void setAsGoal(int row, int col)
	{
		node[col][row].setAsGoal();
		goal= node[col][row];
	}
	public void setCost()
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
		
			node.setText("<html>C:" + node.cost + "</html>");
		
	}
	
	
	public void search()
	{
		while(goalreached== false)
		{
			synchronized(this)
			{
				setCost();
				curr.solid=true;
				try 
				{
					Thread.sleep(100);
				} 
				catch (InterruptedException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				curr.setAsChecked();
				openlist.remove(curr);
				int x = curr.col;
				int y = curr.row;
				for (int i = (x - 1 < 0 ? 0 : x - 1); i < (x + 2 > 15 ? 15 : x + 2); i++)
				{
				    for (int j = (y - 1 < 0 ? 0 : y - 1); j < (y + 2 > 15 ? 15 : y + 2); j++)
				    {
				        if (!openlist.contains(node[i][j]) && node[i][j].solid==false)
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
					curr.solid=false;
					curr=minnode;
					if(minnode == goal)
					{
						goalreached = true;
						track();
						openlist.clear();
						return;
					}
				}
			}
		}
	}
	
	private void openNode(Node node)
	{
		if(!openlist.contains(node) && node.closed == false && node.solid==false)
		{
			node.setAsOpen();
			node.parent = curr;
			openlist.add(node);
		}
	}
	
	private void track()
	{
		Node curr = goal;
		curr.solid=true;
		while(curr != start)
		{
			curr = curr.parent;
			curr.open=false;
			curr.closed=false;
			curr.solid=false;
			
			if(curr!= start)
			{
				curr.setPath();
				
			}
		}
	}
}
