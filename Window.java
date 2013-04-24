import java.swing.*
import java.awt.*

public class Window extend JFrame{
	private Grid g;
	private Display d;

	public Window(){
		super();	
	}

	public void show(Grid to_display){
		g=to_display;

		d=new Display(to_display);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		d.setLayout(null);
		getContentPane().add(d);
	}
}

