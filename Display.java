import java.lang.Math.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.awt.FontMetrics.*;
import java.awt.Toolkit;

public class Display extends JPanel{
	private FileTree current;
	private int taille;
	private Vector<Edge> pendingToDraw;
	private ClickableDisplay displayListener;
	private ControlPanel control;

	public Display(){
		super();
		setLayout(new FlowLayout());
		pendingToDraw=new Vector<Edge>();
		setLayout(new BorderLayout());
		control=new ControlPanel();
	}

	public Display(FileTree f){
		super();
		setLayout(new FlowLayout());
		current=f;
		pendingToDraw=new Vector<Edge>();
		setLayout(new BorderLayout());
		control=new ControlPanel();
		control.updateDirInfo(f.getRoot().getAbsolutePath(),(int) f.getRoot().length(),f.getRoot().getNbFile(),f.getRoot().getNbDirectory());
	}
	
	public void setDisplayPanelSize(Dimension d){
		Dimension dimensionExplorer=new Dimension((int)d.getHeight(),(int)d.getHeight());
		Dimension dimensionControl=new Dimension((int)d.getWidth()-(int)d.getHeight(),(int)d.getHeight());

		control.setPreferredSize(dimensionControl);
		control.setMinimumSize(dimensionControl);
		control.setMaximumSize(dimensionControl);

		setPreferredSize(dimensionExplorer);
		setMinimumSize(dimensionExplorer);
		setMaximumSize(dimensionExplorer);
	}
	
	public ControlPanel getControlPanel(){
		return control;
	}

	public void paint(Graphics g){
		int i;
		FileNode f=current.getRoot();

		paint_aux(g,f,false,new Color(66,227,227),new Color(227,66,66),0,current.getDepth());

		for (i = 0; i < pendingToDraw.size(); i++) {
			Edge current=pendingToDraw.get(i);
			drawEdge(g,current);
		}

	}
	public Vector<Edge> getEdgeVector(){
		return pendingToDraw;	
	}

	public void addEdge(int x,int y,int edgeLength){
		pendingToDraw.add(new Edge(x,y,edgeLength));
	}

	//J'ai du mettre un autre nom car le gestionnaire de tooltips utilise aussi
	//addMouselisterner et ca faisait des chocapic, car il essaye de caster un
	//mouselistener en Clickable
	public void addMouseListenerForClickable(MouseListener l){
		super.addMouseListener(l);
		displayListener=(ClickableDisplay)l;
		displayListener.setAttachedDisplay(this);
	}
	
	public void setTreeFile(FileTree f){
		if(f.getRoot().isEmpty())
			control.updateDirInfo(f.getRoot().getAbsolutePath(),(int) f.getRoot().length(),0,0);
		else
			control.updateDirInfo(f.getRoot().getAbsolutePath(),(int) f.getRoot().length(),f.getRoot().getNbFile(),f.getRoot().getNbDirectory());
		
		control.flushFileInfo();	
		current=f;	
	}

	private void drawEdge(Graphics g,Edge e){
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
	public void	setDisplayListener(ClickableDisplay c){
		displayListener=c;
	}
	private void setFontSizeForDesiredlength(Graphics g,int length,String stringToRender){
		boolean foundCorrectFontSize=false;
		int i=0;
		Font f;
		FontMetrics fm;
		
		while (!foundCorrectFontSize) {
			f=new Font("Arial",Font.PLAIN,i);
			g.setFont(f);
			fm=g.getFontMetrics();

			if(length>fm.stringWidth(stringToRender))
				i++;
			else 
				break;
		}

	}

	public void paint_aux(Graphics g,FileNode f,boolean isInit,Color colorFile,Color colorDirectory,int depth,int depthMax){
		int i,j;	
		
		/* Premier passage,variable d'environnement*/
		if(isInit==false){
			setBackground(Color.white);
		}

		if(f.isFile()){
			g.setColor(colorFile);
			g.fillRect(f.getX(),f.getY(),f.getEdgeSize(),f.getEdgeSize());
			
			setFontSizeForDesiredlength(g,f.getEdgeSize(),f.getName());
			
			if(g.getFont().getSize()>9){
				g.setColor(Color.black);
				g.drawString(f.getName(),f.getX(),f.getY()+f.getEdgeSize());
			}
			return;
		}
		else if(f.isDirectory()){
			g.setColor(colorDirectory);
			g.fillRect(f.getX(),f.getY(),f.getEdgeSize(),f.getEdgeSize());
			
			if(depthMax==depth || f.isEmpty()){
				setFontSizeForDesiredlength(g,f.getEdgeSize(),f.getName());
				
				if(g.getFont().getSize()>9){
					g.setColor(Color.black);
					g.drawString(f.getName(),f.getX(),f.getY()+f.getEdgeSize());
				}
			}

			if(f.getFiles()==null)
				return;
			for (i = 0; i <f.nbFiles(); i++) {
				paint_aux(g,f.getSon(i),true,colorFile.darker(),colorDirectory.darker(),depth+1,depthMax);
			}
		}
	}

	public FileTree getTree(){
		return current;
	}

		
	}
