import java.lang.Math.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.awt.FontMetrics.*;

public class Display extends JPanel{
	private FileTree current;
	private int taille;
	private Vector<Edge> pendingToDraw;
	private ClickableDisplay displayListener;

	public Display(){
		super();
		pendingToDraw=new Vector<Edge>();
	}

	public Display(FileTree f){
		super();
		current=f;
		pendingToDraw=new Vector<Edge>();
	}

	public void paint(Graphics g){
		int i;
		FileNode f=current.getRoot();

		paint_aux(g,f,false,new Color(66,227,227),new Color(227,66,66));

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
	
	public void addMouseListener(MouseListener l){
		super.addMouseListener(l);
		displayListener=(ClickableDisplay)l;
		displayListener.setAttachedDisplay(this);
	}
	
	public void setTreeFile(FileTree f){
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

	public void paint_aux(Graphics g,FileNode f,boolean isInit,Color colorFile,Color colorDirectory){
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
			
			setFontSizeForDesiredlength(g,f.getEdgeSize(),f.getName());
			
			if(g.getFont().getSize()>9){
				g.setColor(Color.black);
				g.drawString(f.getName(),f.getX(),f.getY()+f.getEdgeSize());
			}
			
			if(f.getFiles()==null)
				return;

			for (i = 0; i <f.nbFiles(); i++) {
				paint_aux(g,f.getSon(i),true,colorFile.darker(),colorDirectory.darker());
			}
		}
	}

	public FileTree getTree(){
		return current;
	}

		
	}
