import javax.swing.*;
import java.awt.*;

public class WindowProjet extends JFrame{
	private FileTree f;
	private Display d;
	private ClickableDisplay c;
	public WindowProjet(){
		super();	
	}

	public WindowProjet(FileTree f){
		super();
		d=new Display(f);
		setLayout(new BorderLayout());
	}

	public void afficher(){
		JPanel controlPanel=new JPanel();
		JPanel screen=new JPanel();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension size=new Dimension((int)
		Toolkit.getDefaultToolkit().getScreenSize().getWidth(),(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()-70);
		c=new ClickableDisplay();
		c.setAttachedDisplay(d);
		d.addMouseListener(c);
		d.addMouseMotionListener(c);
		
		screen.setPreferredSize(size);
		screen.setMinimumSize(size);
		screen.setMaximumSize(size);
		
		d.setDisplayPanelSize(size);

		screen.add(d);
		setResizable(false);
		setTitle("Projet POO2");

		getContentPane().add(d,BorderLayout.WEST);
		getContentPane().add(d.getControlPanel(),BorderLayout.EAST);
	}
}

