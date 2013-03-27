import java.lang.*;
import java.io.File;
import java.io.IOException;

public class FileTest 
{
	public static void main(String [] args)
	{
		FileTree f=new FileTree("/",2);
		f.root.succ();
//		f.print();
	}
}
