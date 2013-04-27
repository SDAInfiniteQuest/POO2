import java.lang.*;
import java.io.File;
import java.io.IOException;

public class FileTest 
{
	public static void main(String [] args)
	{
		FileTree f=new FileTree("/usr",2,1);
		Window w=new Windows(f);
		
		w.show();
		w.pack();
		w.setVisisble(true);
		
		
	}
}
