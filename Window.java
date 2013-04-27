import javax.swing.*;
import java.awt.*;

public class Window extends JFrame{
	private FileTree f;
	private Display d;
	
	public Window(){
		super();	
	}

	public Window(FileTree f){
		d=new Display(t);
	}

	public void show(){
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		d.setLayout(null);
		getContentPane().add(d);
	}
}

