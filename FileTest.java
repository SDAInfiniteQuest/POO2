import java.lang.*;
import java.io.File;
import java.io.IOException;

public class FileTest 
{
	public static void main(String [] args)
	{
		FileTree f=new FileTree("/users/info/il3/pallamidessi/SDA_2",3);
		f.print();
	}
}
