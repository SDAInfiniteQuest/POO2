import java.awt.event.*;

public class ClickableDisplay extends MouseAdapter{
	
	protected Display attachedDisplay;
	private boolean activateToolTip=false;
	public ClickableDisplay(){
		super();
	}

	boolean isCursorInSquare(int x,int y,int edgeLength,MouseEvent e){
		int xMouse,yMouse;
		xMouse=e.getX();
		yMouse=e.getY();

		return ((x+edgeLength>xMouse
						&& x<xMouse)
						&& (y+edgeLength>yMouse 
						&& y<yMouse));
	
	}

	public FileNode getReferenceFromDisplay(MouseEvent e){
			
		FileTree t=attachedDisplay.getTree();
		FileNode fd=t.getRoot();

		return getReferenceFromDisplayAux(e,0,t.getDepth(),fd);
	}

	private FileNode getReferenceFromDisplayAux(MouseEvent e,int depth,int maxDepth,FileNode f){
	
		FileNode found;
		int i;

		if (isCursorInSquare(f.getX(),f.getY(),f.getEdgeSize(),e)){
			if(maxDepth==depth)
				return f;
			else{
				if(f.isDirectory() && !f.isEmpty()){
					for (i = 0; i <f.nbFiles(); i++) {
						if((found=getReferenceFromDisplayAux(e,depth+1,maxDepth,f.getSon(i)))!=null)
							return found;
					}
				}
				return f;
			}
		}
		return null;
	}

	public void mouseClicked(MouseEvent e){
		FileNode toDetermined;

		if (e.getButton()==MouseEvent.BUTTON1){
			toDetermined=getReferenceFromDisplay(e);	
			
			if (toDetermined==null)
				return;
			else{
				if(toDetermined.isFile()){
					ControlPanel p=attachedDisplay.getControlPanel();
					p.updateFileInfo(toDetermined.getName(),(int) toDetermined.length(),toDetermined.getAbsolutePath());
				}
				else if(toDetermined.isDirectory() ){
					attachedDisplay.getEdgeVector().removeAllElements();
					attachedDisplay.setTreeFile(new FileTree(toDetermined.getAbsolutePath(),2,1) );
					
					attachedDisplay.repaint();
				}
			}
		} 
		else if (e.getButton()==MouseEvent.BUTTON3){

			String parent=attachedDisplay.getTree().getRoot().getParent();
			
			if(parent==null)
				return;
			else{
				ControlPanel p=attachedDisplay.getControlPanel();

				attachedDisplay.getEdgeVector().removeAllElements();
				attachedDisplay.setTreeFile(new FileTree(parent,2,1) );
				attachedDisplay.repaint();
			}
		} 
	}



	public void setAttachedDisplay(Display d){
		attachedDisplay=d;
	}

	public void mouseMoved(MouseEvent e){
		int x,y;
		int i;


		FileTree t=attachedDisplay.getTree();
		FileNode fd=t.getRoot();
		
		attachedDisplay.getEdgeVector().removeAllElements();

		mouseMovedAux(e,0,t.getDepth(),fd,attachedDisplay);
		attachedDisplay.repaint();

	}

	private void mouseMovedAux(MouseEvent e,int depth,int maxDepth,FileNode f,Display d){
		int i;
		
		if (isCursorInSquare(f.getX(),f.getY(),f.getEdgeSize(),e)){
			attachedDisplay.setToolTipText(f.getName());
			d.addEdge(f.getX(),
								f.getY(),
								f.getEdgeSize());
			if(maxDepth==depth)
				return ;
			else{
				if(f.isDirectory() && !f.isEmpty()){
					for (i = 0; i <f.nbFiles(); i++) {
						mouseMovedAux(e,depth+1,maxDepth,f.getSon(i),d);
					}
				}
				return; 
			}
		}

		return ;
	}
	
	public void setActivateToolTip(boolean b){
		activateToolTip=b;
	}

}
	
