import java.awt.event.*;

/**
 * Classe de gestion des évènements pour la souris
 * @author J. Pallamidessi & S. Andreux
 */
public class ClickableDisplay extends MouseAdapter
{
	protected Display attachedDisplay;

	/**
	 * Crée une classe de gestion des evènements souris
	 */
	public ClickableDisplay()
	{
		super();
	}

	/**
	 * Test si le pointeur de la souris se trouve dans la case
	 * @param x
	 * 		abscisse de la case
	 * @param y
	 *	 	ordonnée de la case
	 * @param edgeLength
	 *		longueur de la case
	 * @param e
	 *		évènement souris
	 */
	boolean isCursorInSquare(int x,int y,int edgeLength,MouseEvent e)
	{
		int xMouse,yMouse;
		xMouse=e.getX();
		yMouse=e.getY();

		return ((x+edgeLength>xMouse
						&& x<xMouse)
						&& (y+edgeLength>yMouse 
						&& y<yMouse));
	}
	
	/**
	 * Renvoit le FileNode associé à la case lorsque la souris déclenche un évènement	
	 * @param e
	 *		Evènement souris
	 * @return 
	 * 		Filenode associé au fichier
	 */
	public FileNode getReferenceFromDisplay(MouseEvent e)
	{
		FileTree t=attachedDisplay.getTree();
		FileNode fd=t.getRoot();

		return getReferenceFromDisplayAux(e,0,t.getDepth(),fd);
	}

	/**
	 * Fonction auxiliaire permettant de récupérer un noeud de l'arbre actuellement affché en fonction d'un évènement souris
	 * @param e
	 *		évènement souris
	 * @param depth
	 *		profondeur de l'arbre
	 * @param maxDepth
	 * 		profondeur maximale
	 * @param f
	 * 		racine de l'arbre
	 * @return
	 *		noeud associé à l'évènement
	 */
	private FileNode getReferenceFromDisplayAux(MouseEvent e,int depth,int maxDepth,FileNode f)
	{
		FileNode found;
		int i;

		if (isCursorInSquare(f.getX(),f.getY(),f.getEdgeSize(),e))
		{
			if(maxDepth==depth)
				return f;
			else
			{
				if(f.isDirectory() && !f.isEmpty())
				{
					for (i = 0; i <f.nbFiles(); i++) 
					{
						if((found=getReferenceFromDisplayAux(e,depth+1,maxDepth,f.getSon(i)))!=null)
							return found;
					}
				}
				return f;
			}
		}
		return null;
	}

	/**
	 * Déclenche une modification de l'arbre associé lors d'un clic souris
	 * @param e
	 *		évènement souris
	 */
	public void mouseClicked(MouseEvent e)
	{
		FileNode toDetermined;

		if (e.getButton()==MouseEvent.BUTTON1)
		{
			toDetermined=getReferenceFromDisplay(e);	
			
			if (toDetermined==null)
				return;
			else
			{
				if(toDetermined.isFile())
				{
					ControlPanel p=attachedDisplay.getControlPanel();
					p.updateFileInfo(toDetermined.getName(),(int) toDetermined.length(),toDetermined.getAbsolutePath());
				}
				else if(toDetermined.isDirectory())
				{
					attachedDisplay.getEdgeVector().removeAllElements();
					attachedDisplay.setTreeFile(new
					FileTree(toDetermined.getAbsolutePath(),attachedDisplay.getDepthLevel(),1) );
					
					attachedDisplay.repaint();
				}
			}
		} 
		else if (e.getButton()==MouseEvent.BUTTON3)
		{
			String parent=attachedDisplay.getTree().getRoot().getParent();
			
			if(parent==null)
				return;
			else
			{
				ControlPanel p=attachedDisplay.getControlPanel();

				attachedDisplay.getEdgeVector().removeAllElements();
				attachedDisplay.setTreeFile(new FileTree(parent,attachedDisplay.getDepthLevel(),1));
				attachedDisplay.repaint();
			}
		} 
	}

	/**
	 * Mutateur: attache un nouveau display
	 * @param d
	 *		nouveau display
	 */
	public void setAttachedDisplay(Display d)
	{
		attachedDisplay=d;
	}

	/**
	 * Associe une action lors du déplacement de la souris
	 * @param e
	 *		évènement souris
	 */
	public void mouseMoved(MouseEvent e)
	{
		int x,y;
		int i;

		FileTree t=attachedDisplay.getTree();
		FileNode fd=t.getRoot();
		
		attachedDisplay.getEdgeVector().removeAllElements();

		mouseMovedAux(e,0,t.getDepth(),fd,attachedDisplay);
		attachedDisplay.repaint();
	}

	/**
	 * Fonction auxiliaure lors du déplacement de la souris
	 * @param e
	 *		évènement souris
	 * @param depth
	 *		profondeur de l'arbre
	 * @param maxDepth
	 *		profondeur maximum de l'arbre
	 * @param f
	 *		noeud du fichier
	 * @param d
	 *	 	panneau d'affichage de l'arbre
	 */
	private void mouseMovedAux(MouseEvent e,int depth,int maxDepth,FileNode f,Display d)
	{
		int i;
		
		if (isCursorInSquare(f.getX(),f.getY(),f.getEdgeSize(),e))
		{
			attachedDisplay.setToolTipText(f.getName());
			d.addEdge(f.getX(),
								f.getY(),
								f.getEdgeSize());
			if(maxDepth==depth)
				return ;
			else
			{
				if(f.isDirectory() && !f.isEmpty())
				{
					for (i = 0; i <f.nbFiles(); i++) 
					{
						mouseMovedAux(e,depth+1,maxDepth,f.getSon(i),d);
					}
				}
				return; 
			}
		}
	}
}	
