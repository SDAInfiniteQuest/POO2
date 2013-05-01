import javax.swing.*;
import java.awt.*;

/**
 * Gestion de la fenêtre d'affichage
 * @author J. Pallamidessi & S. Andreux
 */
public class WindowProjet extends JFrame
{
	private FileTree f;
	private Display d;
	private ClickableDisplay c;
	
	/**
	 * Constructeur: crée une fenêtre simple
	 */
	public WindowProjet()
	{
		super();	
	}

	/**
	 * Constructeur: crée une fenêtre pour affiche l'arbre des fichiers
	 * @param f
	 *  	Arbre des fichiers à afficher
	 */
	public WindowProjet(FileTree f)
	{
		super();
		d=new Display(f);
		setLayout(new BorderLayout());
	}

	/**
	 * Affiche l'arbre des fichiers
	 */
	public void afficher()
	{
		JPanel controlPanel=new JPanel();
		JPanel screen=new JPanel();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension size=new Dimension((int)((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth()*0.75),((int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()-100));
		c=new ClickableDisplay();
		c.setAttachedDisplay(d);
		d.addMouseListenerForClickable(c);
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

