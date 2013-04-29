import java.lang.*;
import java.io.File;
import java.io.IOException;

public class TestAffichage 
{
	public static void main(String [] args)
	{
		FileTree f=new FileTree("/usr",2,1);
		WindowProjet w=new WindowProjet(f);
		

		w.afficher();
		w.pack();
		w.setVisible(true);
		
	}
}
