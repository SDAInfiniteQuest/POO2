import java.awt.event.MouseAdapter.*;

class ClickableDisplay extends MouseAdapter{
	
	private LinkedList<Grid> gridList;
	private Display attachedDisplay;

	public ClickableDisplay(){
		super();
		gridList=new LinkedList<Grid>;
	}

	boolean isCursorInSquare(int x,int y,int edgeLength,MouseEvent e){
		int xMouse,int yMouse;
		xMouse=e.getX();
		yMouse=e.getY();

		return ((x+edgeLength<xMouse
						&& x>xMouse)
						&& (y+edgeLength<yMouse 
						&& y>yMouse));
	
	}

	Object getReferenceFromDisplay(int x,int y){
			
		int x,y;
		int i;
		Grid currentGrid;
		boolean minIsCase=false;
		Case minCase=null;
		Case minGrid=null;
		int minLenght;

		x=e.getX();
		y=e.getY();
		
		for (i = 0; i <gridList.size(); i++) {
			currentGrid=gridList.get(i);
			

			if (isCursorInSquare(currentGrid.getX(),
														currentGrid.getY(),
														currentGrid.edgeLength,
														x,y)){ 
				
				if (i==0){
					minLenght=currentGrid.getEdgeLength();
					minGrid=currentGrid;
					minIsCase=false;
				} 
				else if(minLenght>currentGrid.getEdgeLength) {
					minLenght=currentGrid.getEdgeLength();	
					minGrid=currentGrid;
					minIsCase=false;
				}

				for (j = 0; j < currentGrid.sizeList(); j++) {
					currentCase=currentGrid.getCase(j);
					
					if (isCursorInSquare(currentCase.getX(),
																currentCase.getY(),
																currentCase.edgeLength,
																x,y)){ 

						//la case avec les cote les plus petit est forcement la 
						//plus profonde dans l'arborecence
						

						if(minLenght>currentCase.getEdgeLength()) {
							minLenght=currentCase.getEdgeLength();	
							minCase=currentCase;
							minIsCase=true;
						}

					}
				}
			}
		}
	
	if (minIsCase)
		return (Object) minCase;
	else if(!minIsCase && (minGrid!=null))
		return (Object) minGrid;
	else
		return null;
	}

	public void mouseClicked(MouseEvent e){
		Object toDetermined;

		if (e.getButton()==MouseEvent.BUTTON1){
			toDetermined=getReferenceFromDisplay(e.getX(),e.getY());	
			
			if (toDetermined==null)
				return;
			else if(toDetermined.getClass().instanceof(Grid))

			else if(toDetermined.getClass().instanceof(Case))
				if(toDetermined.getClass().instanceof(CaseDirectory))

		} 
		else if (e.getButton()==MouseEvent.BUTTON2){
			toDetermined=getReferenceFromDisplay(e.getX(),e.getY());		
		} 
	}

	public void updateGridList(Grid g){
		gridList.add(g);
	}

	public void setLinkedList(LinkedList<Grid> gl){
		gridList=gl;
	}

	public void setAttachedDisplay(Display d){
		attachedDisplay=d;
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
			
			if (isCursorInSquare(currentGrid.getX(),
														currentGrid.getY(),
														currentGrid.edgeLength,
														x,y)){ 

				attachedDisplay.addEdge(currentGrid.getX(),
																currentGrid.getY(),
																currentGrid.edgeLength());
				
				for (j = 0; j < currentGrid.sizeList(); j++) {
					currentCase=currentGrid.getCase(j);
					
					if (isCursorInSquare(currentCase.getX(),
																currentCase.getY(),
																currentCase.edgeLength,
																x,y)){ 
						attachedDisplay.addEdge(currentCase.getX(),
																		currentCase.getY(),
																		currentGrid.edgeLength());
					}
				}
			}
		}

	}


}
	
