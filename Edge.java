/**
 * Renseigne les informations relatives à un coté et permet leur manipulation
 * @author J. Pallamidessi & S. Andreux
 */
class Edge
{
	private int x,y;
	private int edgeLength,thickness;

	/**
	 * Constructeur vide 
	 */
	public Edge()
	{
	}

	/**
	 * Construit un coté
	 * @param xNew
	 *		Abscisse du coté
 	 * @param yNew
	 *		Ordonnée du coté
	 * @param length
	 * 		Longueur du coté
	 */
	public Edge(int xNew,int yNew,int length)
	{
		x=xNew;
		y=yNew;
		edgeLength=length;
		thickness=2;
	}

	/**
	 * Fixe l'épaisseur du coté
	 * @param t
	 *		Nouvelle épaisseur
	 */
	public void setThickness(int t)
	{
		thickness=t;
	}
	
	/**
	 * Accesseur: récupère la valeur en abscisse
	 * @return
	 * 		abscisse du coté
	 */
	public int getX()
	{
		return x;
	}

	/**
	 * Accesseur: récupère la valeur en ordonnée
	 * @return
	 * 		ordonnée du coté
	 */
	public int getY()
	{
		return y;
	}

	/**
	 * Accesseur: récupère la longueur du coté
	 * @return
	 * 		longueur du coté
	 */
	public int getEdgeLength()
	{
		return edgeLength;
	}

	/**
	 * Accesseur: récupère l'épaisseur du coté
	 * @return
	 * 		épaisseur du coté
	 */
	public int getThickness()
	{
		return thickness;
	}
}

