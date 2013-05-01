import java.lang.*;

/**
 * Noeud d'un arbre de fichiers qui permet un affichage en mode graphique
 * @author J. Pallamidessi & S. Andreux
 */
public class FileSquare extends FileNode
{
	// /!\ Variables static à la classe mais pouvant être définies ailleurs,
	// je les ai juste mises la pour faire les tests sur la classe et
	// pouvoir utiliser les constructeurs.
	private static int DEFAULT_X=0;
	private static int DEFAULT_Y=0;
	private static int SEUIL=2;
	private static int DEFAULT_EDGE_SIZE=1000;

	/**
	 * Constructeur: Crée une instance de FileSquare, utilisable surtout pour la récursion
	 * @param abs
	 *		définit la donnée en abscisse
	 * @param ord
	 *		définit la donnée en ordonnée
	 * @param edge
	 *		définit la taille du coté
	 * @param dad
	 *		référence vers le père
	 * @param son
	 *		nom du fichier courant
	 */
	public FileSquare(int abs,int ord,int edge,FileNode dad,String son)
	{
		super(dad,son);
		x=abs;
		y=ord;
		edgeSize=edge;
	}

	/**
	 * Constructeur: Crée une instance de FileSquare utilisable avant la récursion
	 * @param son
	 *		nom du fichier courant
	 */
	public FileSquare(String n)
	{
		super(n);
		x=DEFAULT_X;
		y=DEFAULT_Y;
		edgeSize=DEFAULT_EDGE_SIZE;
	}

	/**
	 * Construit l'arbre des fichiers en indiquant les valeurs des positions des carrés
	 * @param depth
	 * 		Indique la profondeur de l'arbre souhaitée
	 */
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
			if(nbEdges>0 && (curEdge=edgeSize/nbEdges)>SEUIL) // si erreur remplacer seuil par 1 
			{
				files=new FileSquare[nbFile];
				k=0;
				l=nbFile-1;

				for(i=0;i<nbEdges;i++)
				{
					abs=x+i*curEdge;
					for(j=0;j<nbEdges;j++)
					{
						ord=y+j*curEdge;
						if(i*nbEdges+j<nbFile)
						{
							cur=new FileSquare(abs+2,ord+2,curEdge-2,this,f[i*nbEdges+j]);
							if(cur.isDirectory())
							{
								files[k]=cur;
								cur.buildTree(depth-1);
								nbDir++;
								k++;
							}
							else
							{
								files[l]=cur;
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

	/**
	 * Trouve le noeud à partir de son indice dans la liste des fichiers du répertoire courant
	 * @param index
	 * 		indice du noeud recherché
	 * @return
	 *		FileSquare renvoit le FileSquare associé au noeud
	 */
	public FileSquare getSon(int index)
	{
		return (FileSquare)super.getSon(index);
	}
	
	/**
	 * Rédéfinit la valeur de DEFAULT_EDGE_SIZE
	 * @param newsize
	 * 		Nouvelle valeur pour DEFAULT_EDGE_SIZE
	 */
	public void setDefaultEdgeSize(int newSize)
	{
		DEFAULT_EDGE_SIZE=newSize;
		edgeSize=DEFAULT_EDGE_SIZE;
	}

	/**
	 * Affiche récursivement l'arbre des fichiers sur la sortie standard
	 */
	public void print()
	{
		super.print();
	}
}
