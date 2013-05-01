import java.lang.*;
import java.io.File;
import java.io.IOException;

/**
 * Classe de test d'affichage en console
 * @author J. Pallamidessi & S. Andreux
 */
public class FileTest 
{
	public static void main(String [] args)
	{
		FileTree f=new FileTree("/",2,1);
		f.print();
	}
}
