import java.awt.event.MouseAdapter.*;

class ClickableDisplay extends MouseAdapter{
	
	private LinkedList<Grid> gridList;
	private Display attachedDisplay;

	public ClickableDisplay(){
		super();
		gridList=new LinkedList<Grid>;
	}

	public void mouseClicked(MouseEvent e){
		if (e.getButton()==MouseEvent.BUTTON1){
			
		} 
		else if (e.getButton()==MouseEvent.BUTTON2){
		
		} 
	}

	public void updateGridList(Grid g){
		gridList.add(g);
	}

	public void setLinkedList(LinkedList<Grid> gl){
		gridList=gl;
	}

	void setAttachedDisplay(){
		attachedDisplay=(Display)getComponent();
	}

	public void mouseMoved(MouseEvent e){
		int x,y;
		int i;
		Grid currentGrid;

		x=e.getX();
		y=e.getY();
		
		attachedDisplay.getEdgeVector().removeAllElements();

		for (i = 0; i <gridList.size(); i++) {
			currentGrid=gridList.get(i);
			
			if ((currentGrid.getX()+currentGrid.getSizeLength()<x 
					&& currentGrid.getX>x)
					&& (currentGrid.getY()+currentGrid.getSizeLength()<y 
					&& currentGrid.getY>y)){ 
				attachedDisplay.addEdge(currentGrid.getX(),currentGrid.getY(),currentGrid.edgeLength())
				
				for (j = 0; j < currentGrid.sizeList(); j++) {
					currentCase=currentGrid.getCase(j);
					
					if ((currentCase.getX()+currentCase.getSizeLength()<x 
							&& currentCase.getX>x)
							&& (currentCase.getY()+currentCase.getSizeLength()<y 
							&& currentCase.getY>y)){ 
						attachedDisplay.addEdge(currentCase.getX(),currentCase.getY(),currentGrid.edgeLength())
					}
				}
			}
		}

	}


}
	
