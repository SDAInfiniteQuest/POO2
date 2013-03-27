import java.lang.*;
import java.io.File;
import java.io.IOException;

class FileNode extends File
{
	int nbDir=0;
	FileNode daddy=null;
	FileNode [] files=null;
	
	public FileNode(FileNode dad, String son)
	{
		super(dad.getAbsolutePath(),son);
		daddy=dad;
	}

	public FileNode(String n)
	{
		super(n);			
	}	

	public String [] list()
	{
		return super.list();
	}

	public FileNode [] listfiles()
	{
		int i,j,sizeTab;
		FileNode cur;
		String [] f=list();
		if(!((files!=null)||(f=list())==null))	
		{
			sizeTab=f.length;
			files=new FileNode[sizeTab];
			for(i=0,j=(sizeTab-1);i<=j;)
			{
				cur=new FileNode(this,f[i]);
				if(cur.isDirectory())
				{
					files[i]=cur;
					nbDir++;
					i++;
				}
				else
				{
					files[j]=cur;
					j--;
				}
			}
		}
		return files;
	}

	public void buildNode(int depth)
	{
		if(isFile()||depth<1) return  ;
		else 
		{
			int i,j;
			FileNode cur;
			String [] f=super.list();
			if(f==null) return ;
			int sizeTab=f.length;
			System.out.println(sizeTab);
			for(i=0;i<f.length;i++)
			{
				//System.out.println(getAbsolutePath() + "/" + f[i]);
			}
			files=new FileNode[sizeTab];
			for(i=0, j=(sizeTab-1);i<j;)
			{
				cur=new FileNode(this,f[i]);
				System.out.println(cur.getName());
				if(cur.isDirectory())
				{
					files[i]=cur;
					files[i].buildNode(depth-1);
					nbDir++;
					i++;
				}
				else
				{
					files[j]=cur;
					//System.out.println(files[j].getName());
					j--;
				}
			}
			return ;
		}
	}

	public void succ()
	{
		if(files!=null)
		{
			int i;
			for(i=0;i<files.length;i++)
			{
				System.out.println(files[i].getAbsolutePath() + "/" +	files[i].getName());
			}
		}
	}

	public void print()
	{
		int size,i,j;
		if(!exists()) return ;
		if(isFile()) System.out.println(getName()+"/");
		else
		{
			System.out.print(getName()+"/");
			size=getName().length();
			for(i=0;i<files.length;i++)
			{
				if(i==0) 
				{
					if(files[i]!=null&&files[i].exists()) files[i].print();
				}
				else
				{
					for(j=0;j<size;j++)
						System.out.print(" ");
					if(files[i]!=null&&files[i].exists()) files[i].print();
				}
			}
		}
	}
}

public class FileTree
{
	static int MAX_DEPTH=5;
	int depth=0;
	FileNode root;

	public FileTree(String path, int dep)
	{
		depth=dep;
		root=new FileNode(path);
		if(root.exists()&&root.isDirectory())
		{
			root.listfiles();
		}
	}

	public void setMaxDepth(int n)
	{
		MAX_DEPTH=n;
	}

	public void print()
	{
		root.print();
	}
}
