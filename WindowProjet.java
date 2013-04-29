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
	}

	public void afficher(){
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension size=new Dimension(1000,1000);
		c=new ClickableDisplay();
		c.setAttachedDisplay(d);
		d.addMouseListener(c);
		d.addMouseMotionListener(c);
		d.setPreferredSize(size);
		d.setMinimumSize(size);
		d.setMaximumSize(size);
		d.setLayout(null);
		setResizable(false);
		setTitle("Projet POO2");
		getContentPane().add(d);
	}
}

