import java.lang.*;

public class FileSquare extends FileNode
{
	// /!\ Variable static à la classe mais pouvant être définie ailleurs,
	// je les ai juste mises la pour faire les tests sur la classe et
	// pouvoir utiliser les constructeurs.
	private static int DEFAULT_X=0;
	private static int DEFAULT_Y=0;
	private static int DEFAULT_EDGE_SIZE=1000;

	public FileSquare(int abs,int ord,int edge,FileNode dad,String son)
	{
		super(dad,son);
		x=abs;
		y=ord;
		edgeSize=edge;
	}

	public FileSquare(String n)
	{
		super(n);
		x=DEFAULT_X;
		y=DEFAULT_Y;
		edgeSize=DEFAULT_EDGE_SIZE;
	}

	// La fonction a l'air de marcher mais je sais pas si les coordonnées
	// sont utilisables (i.e bien calculées) donc il reste ca a vérifier 
	public void buildTree(int depth)
	{
		int i,j;
		int k,l;
		int abs,ord;
		int nbFile,nbEdges;
		int curEdge;
		FileNode cur;
		String[] f=list();

		if(isFile() || depth==0 || f==null) return ;
		else
		{
			nbFile=f.length;
			nbEdges=(int) Math.ceil(Math.sqrt((double) nbFile));

			// /!\J'ai mis 1 ici mais la valeur reste à définir, c'est une
			// valeur seuil pour l'affichage et j'ai hésité à la définir dans
			// la classe FilesSquare
			if(nbEdges>0 && (curEdge=edgeSize/nbEdges)>1) 
			{
				files=new FileSquare[nbFile];
				k=0;
				l=nbFile-1;

				for(i=0;i<nbEdges;i++)
				{
					abs=x+i*curEdge;
					for(j=0;j<nbEdges;j++)
					{
						ord=y+l*curEdge;
						if(i*nbEdges+j<nbFile)
						{
							cur=new FileSquare(x,y,curEdge,this,f[i*nbEdges+j]);
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
								l--;
							}
						}
						else break;
					}
					if(i*nbEdges+j>=nbFiles()) break;
				}
			}
		}

		return ;
	}

	public FileSquare getSon(int index){
		return (FileSquare)super.getSon(index);
	}

	public void print()
	{
		super.print();
	}
}
