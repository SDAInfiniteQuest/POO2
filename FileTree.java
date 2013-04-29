import java.lang.*;
import java.io.File;
import java.io.IOException;

public class FileTree
{
	static int MAX_DEPTH=3;
	int depth=0;
	FileNode root;

	public FileTree(String path, int dep,int option)
	{
		if (dep>MAX_DEPTH || dep<0) 
			depth=MAX_DEPTH;
		else 
			depth=dep;

		// illustre le fonctionnement des classes
		switch(option)
		{
			case 0: root=new FileNode(path); break;
			case 1: root=new FileSquare(path); break;
			default:
			{
			}
		}

		if(root.exists()&&root.isDirectory())
		{
			root.buildTree(depth);
		}
	}
	
	public FileNode getRoot(){
		return root;
	}

	public int getDepth(){
		return depth;
	}

	public void setMaxDepth(int n)
	{
		MAX_DEPTH=n;
	}

	public void print()
	{
		root.print();
	}

	// A faire : fonctions pour naviguer dans l'arbre (revenir au pere,
	// descendre dans l'arbre etc)
}
