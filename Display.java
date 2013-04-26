import java.lang.Math.*
import java.swing.*
import java.awt.*


public class Display extends JPanel{
	private Grid to_display;
	private FileTree current;
	private int taille;
	private Vector<Edge> pendingToDraw;
	private ClickableDisplay displayListener;

	public Display(){
		super();
		pendingToDraw=new Vector<Edge>();
	}

	public Display(Grid g,FileTree f){
		super();
		to_display=g;
		current=f;
		pendingToDraw=new Vector<Edge>();
	}

	public void paint(Graphic g){
		int i;
		paint_aux(g,0,0,50,true,to_display);

		for (i = 0; i < pendingToDraw.size(); i++) {
			Edge current=pendingToDraw.get(i);

			drawEdge(g,current);
		}

	}
	
	public Vector<Edge> getEdgeVector(){
		return pendingToDraw;	
	}

	public void addEdge(int x,int y,int edgeLength){
		Edge toAdd=new Egde(x,y,edgeLength);
		pendingToDraw.add(toAdd);
	}

	private void drawEdge(Graphics g,Edge e){
		int x,y;
		int edgeLength=e.getEdgelength();
		int thickness=e.getThickness();

		g.setColor(black);
		Graphics2D g2=(Graphics2D) g;
		g2.setStroke(new BasicStroke(thickness));

		g.drawLine(x,y,x+edgeLength,y);
		g.drawLine(x,y,x,y+edgeLength);
		g.drawLine(x,y+edgeLength,x+edgeLength,y+edgeLength);
		g.drawLine(x,y,x+edgeLength,y);

	}
	public void	setDisplayListener(ClickableDisplay c){
		displayListener=c;
	}
	private void setFontSizeForDesiredlength(Graphics g,int length,String stringToRender){
		boolean foundCorrectFontSize=false;
		int i;
		Font f;
		FontMetric fm;
		
		while (!foundCorrectFontSize) {
			f=new Font("Arial",PLAIN,i);
			fm=new FontMetric(f);

			if(length>fm.charsWidth(stringToRender,0,stringToRender.length()))
				i++;
			else 
				break;
		}

		g.setFont(f);
	}

	public void paint_aux(Graphics g,int xOffset,int yOffset,int sideLength,boolean isInit,Grid g){
		int i,j;	
		
		/* Premier passage,variable d'environnement*/
		if(isInit==false){
			setBackground(Color.white);
		}

		for (i = 0; i <to_display.listSize(); i++) {
			if (to_display.getCase() instanceof CaseFile){
				g.setColor(Color.blue);
				g.fillRect(i*sideLenght+xOffset,j*sideLenght+yOffset,sideLength,sideLength);
				
				setFontSizeForDesiredlength();
				g.setColor(black);
				g.drawString(to_display.getCase().getName());
			}
			else if(to_display.getCase() instanceof CaseDirectory){
				g.setColor(Color.red);
				g.fillRect(i*sideLenght+xOffset,j*sideLenght+yOffset,sideLength,sideLength);
				
				setFontSizeForDesiredlength();
				g.setColor(black);
				g.drawString(to_display.getCase().getName());
				
				Grid subDir=new Grid(to_display.getCase().getPointer);
				displayListener.updateGridList(subDir);

				if (!subDir.isEmpty()){
					int newSidelength=(sideLength*sideLength/subDir.listSize())
					paint_aux(g,xOffset+5,yOffset+5,sideLength/,true,subDir);
				}
			}
		}
	}

}

