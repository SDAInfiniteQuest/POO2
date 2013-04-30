import java.lang.*;
import java.io.File;
import java.io.IOException;

public class FileNode extends File
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

	public boolean isEmpty(){
		return (files==null);
	}

	public boolean isReallyEmpty(){
		return (list()==null);
	}

	public int nbFiles()
	{
		return files.length;
	}
	
	public int getNbFile(){
		int result=0,i;

		for(i=0;i<files.length;i++){
			if(files[i].isFile())
				result++;
		}
		return result;
	}

	public int getNbDirectory(){
		int result=0,i;

		for(i=0;i<files.length;i++){
			if(files[i].isDirectory())
				result++;
		}
		return result;
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
		

		if(isFile()){ 
			System.out.println(getName());
			System.out.println("x "+x+"y "+y);
		}
		else																// Cas dossier
		{
			System.out.println(getName()+"/");
			size=getName().length();
			System.out.println("x "+x+"y "+y);
			System.out.println(files.length);
			
			if(files==null) 									// Cas dossier vide
				return;

			for(i=0;i<files.length;i++)
			{
			
				for(j=0;j<size+3;j++)
					System.out.print(" ");
				
				if(files[i]!=null && files[i].isDirectory())
					files[i].print();
				
				
				if(files[i]!=null && files[i].isFile()){
					System.out.println(files[i].getName());
					System.out.println("x "+files[i].getX()+"y "+files[i].getY());
				}
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
	
	public FileNode[] getFiles(){
		return files;
	}
}
