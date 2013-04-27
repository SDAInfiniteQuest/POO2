import java.lang.*;
import java.io.File;
import java.io.IOException;

public class FileTest 
{
	public static void main(String [] args)
	{
		FileTree f=new FileTree("/usr",2,1);
		f.print();
	}
}
