import java.lang.Math.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.awt.FontMetrics.*;
import java.awt.Toolkit;

/**
 * Panneau d'affichage de l'arbre
 * @author J. Pallamidessi & S. Andreux
 */
public class Display extends JPanel
{
	private FileTree current;
	private int taille;
	private Vector<Edge> pendingToDraw;
	private ClickableDisplay displayListener;
	private ControlPanel control;
	private int depthLevel=2;

	/**
	 * Crée une instance simple de display créant un panneau pour l'affichage
	 */
	public Display()
	{
		super();
		setLayout(new FlowLayout());
		pendingToDraw=new Vector<Edge>();
		setLayout(new BorderLayout());
		control=new ControlPanel();
		control.setAttachedElement(this);
	}

	/**
	 * Crée une instance de display en lui passant un arbre
	 * @param f
	 * 		Arbre de fichiers
	 */
	public Display(FileTree f)
	{
		super();
		setLayout(new FlowLayout());
		current=f;
		pendingToDraw=new Vector<Edge>();
		setLayout(new BorderLayout());
		control=new ControlPanel();
		control.setAttachedElement(this);
		control.updateDirInfo(f.getRoot().getAbsolutePath(),(int) f.getRoot().length(),f.getRoot().getNbFile(),f.getRoot().getNbDirectory());
	}
	
	/**
	 * Fixe les dimensions du panneau 
	 * @param d
	 * 		Dimensions choisies pour le panneau 
	 */
	public void setDisplayPanelSize(Dimension d)
	{
		Dimension dimensionExplorer=new Dimension((int)d.getHeight(),(int)d.getHeight());
		Dimension dimensionControl=new Dimension((int)d.getWidth()-(int)d.getHeight(),(int)d.getHeight());

		control.setPreferredSize(dimensionControl);
		control.setMinimumSize(dimensionControl);
		control.setMaximumSize(dimensionControl);

		setPreferredSize(dimensionExplorer);
		setMinimumSize(dimensionExplorer);
		setMaximumSize(dimensionExplorer);
	}
	

	/**
	 * Effectue le dessin de l'arbre de fichier 
	 * @param g
	 *		Options graphiques utilisées pour l'affichage
	 */
	public void paint(Graphics g)
	{
		int i;
		FileNode f=current.getRoot();

		paint_aux(g,f,false,new Color(66,227,227),new Color(227,66,66),new Color(147,227,66),0,current.getDepth());

		for (i = 0; i < pendingToDraw.size(); i++) 
		{
			Edge current=pendingToDraw.get(i);
			drawEdge(g,current);
		}
	}

	/**
	 * Fonction auxiliaire utilisée pour l'affichage de l'arbre sur le panneau en fonction de son "type" (i.e. fichier | dossier | autres)
	 * @param g
	 * 		Options graphiques d'affichage
	 * @param f
	 *		Noeud de l'arbre actuellement parcouru
	 * @param isInit
	 *		Variable pour savoir si l'initialisation du background a été faite
	 * @param colorFile
	 *		Couleur utilisée pour les fichiers
 	 * @param colorDirectory
	 *		Couleur utilisée pour les dossiers
	 * @param colorSpecialFile
	 *		Couleur utilisée pour les autres fichiers
	 * @param depth
	 *		Profondeur actuelle
	 * @param depthMax
	 *		Profondeur maximum
	 */
	public void paint_aux(Graphics g,FileNode f,boolean isInit,Color colorFile,Color colorDirectory,Color colorSpecialFile,int depth,int depthMax)
	{
		int i,j;	
		
		/* Premier passage,variable d'environnement*/
		if(isInit==false)
		{
			setBackground(Color.white);
		}

		if(f.isFile())
		{
			g.setColor(colorFile);
			g.fillRect(f.getX(),f.getY(),f.getEdgeSize(),f.getEdgeSize());
			
			setFontSizeForDesiredlength(g,f.getEdgeSize()-3,f.getName());
			
			if(g.getFont().getSize()>9){
				g.setColor(Color.black);
				g.drawString(f.getName(),f.getX(),f.getY()+f.getEdgeSize()-3);
			}
			return;
		}
		else if(f.isDirectory())
		{
			g.setColor(colorDirectory);
			g.fillRect(f.getX(),f.getY(),f.getEdgeSize(),f.getEdgeSize());
			
			if(depthMax==depth || f.isEmpty())
			{
				setFontSizeForDesiredlength(g,f.getEdgeSize()-3,f.getName());
				
				if(g.getFont().getSize()>9)
				{
					g.setColor(Color.black);
					g.drawString(f.getName(),f.getX(),f.getY()+f.getEdgeSize()-3);
				}
			}

			if(f.getFiles()==null)
				return;
			for (i = 0; i <f.nbFiles(); i++) 
			{
				paint_aux(g,f.getSon(i),true,colorFile.darker(),colorDirectory.darker(),colorSpecialFile.darker(),depth+1,depthMax);
			}
		}
		/* Character device,named pipe,block Device, Local Domain socket, etc ..*/
		else
		{
			g.setColor(colorSpecialFile);
			g.fillRect(f.getX(),f.getY(),f.getEdgeSize(),f.getEdgeSize());

			setFontSizeForDesiredlength(g,f.getEdgeSize()-3,f.getName());
			
			if(g.getFont().getSize()>9)
			{
				g.setColor(Color.black);
				g.drawString(f.getName(),f.getX(),f.getY()+f.getEdgeSize()-3);
			}
		}
	}

	/**
	 * Ajoute un coté au vecteur des cotés
	 * @param x
	 *		abscisse du coté
	 * @param y
	 * 		ordonnée du coté
	 * @param edgeLength
	 *		longueur du coté
	 */
	public void addEdge(int x,int y,int edgeLength)
	{
		pendingToDraw.add(new Edge(x,y,edgeLength));
	}

	/**
	 * Dessine un coté sur le panneau
	 * @param g
	 * 		Contecte graphique
	 * @param e
	 * 		Coté à dessiner
	 */
	private void drawEdge(Graphics g,Edge e)
	{
		int x=e.getX();
		int y=e.getY();
		int edgeLength=e.getEdgeLength();
		int thickness=e.getThickness();

		g.setColor(Color.black);
		Graphics2D g2=(Graphics2D) g;
		g2.setStroke(new BasicStroke(thickness));

		g.drawLine(x,y,x+edgeLength,y);
		g.drawLine(x,y,x,y+edgeLength);
		g.drawLine(x,y+edgeLength,x+edgeLength,y+edgeLength);
		g.drawLine(x+edgeLength,y,x+edgeLength,y+edgeLength);
	}

	/**
	 * Ajoute les évènements avec la souris 
	 * @param c
	 *		Evenements disponibles pour la souris
	 */	
	public void	setDisplayListener(ClickableDisplay c)
	{
		displayListener=c;
	}

	/**
	 * Fixe la taille des polices d'affichage
	 * @param g
	 * 		Contexte graphique
	 * @param length
	 * 		Taille disponible pour l'affichage
	 * @param stringToRender
	 *		Chaine de caractères a afficher
	 */	
	private void setFontSizeForDesiredlength(Graphics g,int length,String stringToRender)
	{
		boolean foundCorrectFontSize=false;
		int i=0;
		Font f;
		FontMetrics fm;
		
		while (!foundCorrectFontSize) 
		{
			f=new Font("Arial",Font.PLAIN,i);
			g.setFont(f);
			fm=g.getFontMetrics();

			if(length>fm.stringWidth(stringToRender))
				i++;
			else 
				break;
		}
	}

	/**
	 * Mutateur: fixe un nouvelle arbre à afficher
	 * @param f
	 *		Nouvelle arbre
	 */
	public void setTreeFile(FileTree f)
	{
		if(f.getRoot().isEmpty())
			control.updateDirInfo(f.getRoot().getAbsolutePath(),(int) f.getRoot().length(),0,0);
		else
			control.updateDirInfo(f.getRoot().getAbsolutePath(),(int) f.getRoot().length(),f.getRoot().getNbFile(),f.getRoot().getNbDirectory());
		
		control.updateRecInfo(f.getDepth());
		control.flushFileInfo();	
		current=f;
		pendingToDraw.removeAllElements();	
	}
	
	/**
	 * Mutateur: fixe la profondeur de l'arbre
	 * @param d
	 * 		Nouvelle profondeur
	 */
	public void setDepthLevel(int d)
	{
		depthLevel=d;
	}
	
	/**
	 * Mutateur: Modifie la gestion des évènements relatifs à la souris
	 * /!\ J'ai du mettre un autre nom car le gestionnaire de tooltips utilise aussi
	 * /!\ addMouselisterner et ca faisait des chocapic, car il essaye de caster un
	 * /!\ mouselistener en Clickable
	 * @param l
	 *		MouseListener: classe de gestion de la souris
	 */
	public void addMouseListenerForClickable(MouseListener l)
	{
		super.addMouseListener(l);
		displayListener=(ClickableDisplay)l;
		displayListener.setAttachedDisplay(this);
	}

	/**
	 * Accesseur: retourne l'arbre de fichier courant utilisé pour l'affichage
	 * @return
	 * 		FileTree arbre de fichier courant
	 */
	public FileTree getTree()
	{
		return current;
	}
	
	/**
	 * Accesseur: retourne le niveau de profonceur courant de l'arbre
	 * @return
	 * 		entier: profondeur actuelle de larbre
	 */
	public int getDepthLevel()
	{
		return depthLevel;
	}
	
	/**
	 * Accesseur: retourne le panneau de controle
	 * @return
	 * 		Panneau de controle
	 */
	public ControlPanel getControlPanel()
	{
		return control;
	}
	
	/**
	 * Accesseur: retourne le vecteur de coté que l'on souhaite afficher
	 * @return 
	 *		Vector<Edge> vecteur de coté à afficher sur le panneau
	 */	
	public Vector<Edge> getEdgeVector()
	{
		return pendingToDraw;	
	}
}
