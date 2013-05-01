import java.lang.*;
import java.io.File;
import java.io.IOException;

/**
 * Classe testant l'affichage en mode graphique
 * @author J. Pallamidessi
 */
public class TestAffichage 
{
	public static void main(String [] args)
	{
		FileTree f=new FileTree("/",2,1);
		WindowProjet w=new WindowProjet(f);
		
		w.afficher();
		w.pack();
		w.setVisible(true);
	}
}
