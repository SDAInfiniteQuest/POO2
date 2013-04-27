import java.lang.*;
import java.io.File;
import java.io.IOException;

class FileNode extends File
{
	int nbDir=0;
	FileNode daddy=null;
	FileNode[] files=null;
	protected int x,y;
	protected int edgeSize;

	public FileNode(FileNode dad, String son)
	{
		super(dad.getAbsolutePath(),son);
		daddy=dad;
	}

	public FileNode(String n)
	{
		super(n);			
	}
	
	/*
	 * refus du constructeur vide WTF ?
	public FileNode()
	{
	}
  */

	/*marche parfaitement : par contre pas trop rapide prof de 3 sur 
	 * / environ 10s*/

	public void buildTree(int depth)
	{
		int i,j,k=0,sizeTab;
		FileNode cur;
		String[] f=list();
		
		if(isFile() || depth==0 )
		{
			return ;
		}
		else 
		{
			if(f!=null)
			{
				sizeTab=f.length;
				files=new FileNode[sizeTab];

				j=sizeTab-1;

				for(i=0;i<sizeTab;i++)
				{
					cur=new FileNode(this,f[i]);
					if(cur.isDirectory())
					{
						files[k]=cur;
						cur.buildTree(depth-1);
						nbDir++;
						k++;
					}
					else
					{
						files[j]=cur;
						j--;
					}
				}
			}
		}
		return ;
	}

	public FileNode getSon(int index){
		return files[index];
	}

	public int nbFiles()
	{
		return files.length;
	}

	public void succ()
	{
		if(files!=null)
		{
			int i;
			for(i=0;i<files.length;i++)
			{
				System.out.println(files[i].getAbsolutePath());
			}
		}
	}

	/* Affichage pas top,mais marche parfaitement*/
	public void print()
	{
		int size,i,j;
		int nb_element=0;

		if(!exists()) 
			return ;
		
		if(isFile()) 
			System.out.println(getName());
		else																// Cas dossier
		{
			System.out.println(getName()+"/");
			size=getName().length();
			
			if(files==null) 									// Cas dossier vide
				return;

			for(i=0;i<files.length;i++)
			{
			
				for(j=0;j<size+3;j++)
					System.out.print(" ");
				
				if(files[i]!=null && files[i].isDirectory())
					files[i].print();
				
				
				if(files[i]!=null && files[i].isFile())
					System.out.println(files[i].getName());
				
			}
		}
	}
	
	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}

	public int getEdgeSize(){
		return edgeSize;
	}

}


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
