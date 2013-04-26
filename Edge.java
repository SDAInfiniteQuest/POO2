class Edge{
	private int x,y;
	private int edgeLength,thickness;


	public Edge()
	{
	}

	public Edge(int xNew,int yNew,int length){
		x=xNew;
		y=yNew;
		edgeLength=length;
		thickness=2;
	}

	public void setThickness(int t){
		thickness=t;
	}
	
	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}

	public int getEdgeLength(){
		return edgeLength;
	}

	public int getThickness(){
		return thickness;
	}

}

