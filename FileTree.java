import java.lang.*;
import java.io.File;
import java.io.IOException;
import java.awt.Toolkit;

/**
 * Classe générant l'arbre des fichiers convenu selon l'option choisie en utilisant pour cela la généricité
 * @author J. Pallamidessi & S. Andreux
 */
public class FileTree
{
	static int MAX_DEPTH=3;
	int depth=0;
	FileNode root;

	/**
	 * Crée un arbre de fichier en fixant une option d'affichage et une profondeur de parcours
	 * @param path
	 *		Chemin d'origine de l'arbre de fichiers
	 * @param dep
	 *    Profondeur de parcours de l'arbre
	 * @param option
	 * 		Détermine l'option souhaitée (graphique, console etc)
	 */
	public FileTree(String path, int dep,int option)
	{
		if (dep>MAX_DEPTH || dep<0) 
			depth=MAX_DEPTH;
		else 
			depth=dep;

		// illustre le fonctionnement des classes
		switch(option)
		{
			case 0: 
				root=new FileNode(path); 
				break;
			case 1: 
				root=new FileSquare(path);
				((FileSquare) root).setDefaultEdgeSize((int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()-100);
				break;
			default:
			{
			}
		}

		if(root.exists()&&root.isDirectory())
		{
			root.buildTree(depth);
		}
	}

	/**
	 * Retourne la racine de l'arbre des fichiers
	 * @return 
	 *		FileNode: racine de l'arbre des fichiers
	 */	
	public FileNode getRoot()
	{
		return root;
	}

	/**
	 * Renvoit la profondeur de l'arbre des fichiers
	 * @return
	 *		Entier profondeur de l'arbre
	 */
	public int getDepth()
	{
		return depth;
	}

	/**
	 * Fixe la profondeur maximum
	 * @param n
	 * 		Nouvelle profondeurs maximum
	 */
	public void setMaxDepth(int n)
	{
		MAX_DEPTH=n;
	}

	/**
	 * Affiche l'arbre depuis la racine sur la sortie standard
	 */
	public void print()
	{
		root.print();
	}
}
