import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JComponent;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.border.*;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;
import java.io.File;

public class ControlPanel extends JPanel{
	private Display attachedDisplay;

	private JPanel file;
	private JPanel directory;
	private JPanel recursion;

	private JTextField currentPath;
	private JTextField goTo;
	private JLabel sizeOnDiskDir;
	private JLabel nbElem;
	private JLabel nbFiles,nbDirectory;
	
	private JLabel sizeOnDiskFile;
	private JTextField getFilePath;
	private JLabel filename;

	private JLabel currentDepth;
	private JTextField newDepth;

	//private JLabel 

	public void setAttachedElement(Display d){
		attachedDisplay=d;
	}

	public ControlPanel(){
		super();
		

		file=new JPanel();
		directory=new JPanel();
		recursion=new JPanel();

		currentPath=new JTextField("",20);
		goTo=new JTextField(20);
		sizeOnDiskDir=new JLabel();
		nbElem=new JLabel();
		nbFiles=new JLabel();
		nbDirectory=new JLabel();

		getFilePath=new JTextField("",20);
		filename=new JLabel();
		sizeOnDiskFile=new JLabel();
		
		currentDepth=new JLabel();
		newDepth=new JTextField("",5);
	
		
		currentPath.setHorizontalAlignment(JLabel.CENTER);
		sizeOnDiskDir.setHorizontalAlignment(JLabel.CENTER);
		nbElem.setHorizontalAlignment(JLabel.CENTER);
		nbFiles.setHorizontalAlignment(JLabel.CENTER);
		nbDirectory.setHorizontalAlignment(JLabel.CENTER);
		filename.setHorizontalAlignment(JLabel.CENTER);
		sizeOnDiskFile.setHorizontalAlignment(JLabel.CENTER);
		currentDepth.setHorizontalAlignment(JLabel.CENTER);

		BoxLayout principal=new BoxLayout(this,BoxLayout.Y_AXIS);
		//principal.setVgap(50);

		setLayout(principal);
		
		JPanel padding=new JPanel();
		Dimension d=new Dimension(70,30);
		padding.setPreferredSize(d);
		padding.setMaximumSize(d);
		padding.setMinimumSize(d);
		
		JPanel padding2=new JPanel();
		padding2.setPreferredSize(d);
		padding2.setMaximumSize(d);
		padding2.setMinimumSize(d);

		GridLayout secondary=new GridLayout(0,1);
		secondary.setVgap(17);

		directory.setLayout(secondary);
		file.setLayout(secondary);
		recursion.setLayout(secondary);

		//directory.add(padding);
		directory.add(addTitledBorder(currentPath,"Path du repertoire courant"));
		directory.add(addTitledBorder(nbElem,"Nombre total d'element"));
		directory.add(addTitledBorder(nbFiles,"Nombre de fichier "));
		directory.add(addTitledBorder(nbDirectory,"Nombre de dossier"));
		directory.add(addTitledBorder(goTo,"Afficher le repertoire de path suivant :"));
		
		file.add(addTitledBorder(filename,"Nom du fichier selectionne"));
		file.add(addTitledBorder(getFilePath,"Path du fichier selectionner"));
		file.add(addTitledBorder(sizeOnDiskFile,"Taille du fichier selectionne en octets"));
		
		recursion.add(addTitledBorder(currentDepth,"Niveau de recursion actuel"));
		recursion.add(addTitledBorder(newDepth,"Nouveaux niveau de recusion"));
		getFilePath.setEditable(false);
		currentPath.setEditable(false);
	
		TitledBorder titleDirPanel=BorderFactory.createTitledBorder("Information sur le repertoire courant");
		TitledBorder titleFilePanel=BorderFactory.createTitledBorder("Information sur le fichier");
		TitledBorder titleControlPanel=BorderFactory.createTitledBorder("Information sur le niveau de recursion");
		
		directory.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10,10,10,10),titleDirPanel));
		file.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10,10,10,10),titleFilePanel));
		recursion.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10,10,10,10),titleControlPanel));
		
		add(directory);
		add(file);
		add(recursion);
		
		goTo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e ){
				try{
					File test=new File(goTo.getText());
					if(test.isDirectory()){
						attachedDisplay.setTreeFile(new FileTree(goTo.getText(),attachedDisplay.getDepthLevel(),1));
						attachedDisplay.repaint();
					}
					else
						return;
				}
				catch(NullPointerException ex){
					return;
				}
			}
		});

		newDepth.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e ){
				try{
					attachedDisplay.setDepthLevel( Integer.parseInt(newDepth.getText()) );	
					attachedDisplay.setTreeFile(new FileTree(attachedDisplay.getTree().getRoot().getAbsolutePath(),attachedDisplay.getDepthLevel(),1));
					attachedDisplay.repaint();
				}
				catch(NullPointerException ex){
					return;
				}
			}
		});

		setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
	public JComponent addTitledBorder(JComponent c,String name){
		JPanel p=new JPanel();
		Border e=BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		TitledBorder title=BorderFactory.createTitledBorder(e,name);
		title.setTitleJustification(TitledBorder.CENTER);

		
		p.setLayout(new FlowLayout());
		p.add(c);
		p.setBorder(title);
		p.validate();
		return p;
		
	}
	public void updateDirInfo(String currentDirPath,int sizeCurDir,int nbNewFile,int nbNewDirectory){
		currentPath.setText("");
		currentPath.setText(currentDirPath);
		
		sizeOnDiskDir.setText("");
		sizeOnDiskDir.setText(String.valueOf(sizeCurDir));
		
		nbFiles.setText("");
		nbFiles.setText(String.valueOf(nbNewFile));
		
		nbDirectory.setText("");
		nbDirectory.setText(String.valueOf(nbNewDirectory));

		nbElem.setText("");
		nbElem.setText(String.valueOf(nbNewFile+nbNewDirectory));
	}

	public void updateFileInfo(String fileNameClick,int size,String pathFile) {
		sizeOnDiskFile.setText("");
		sizeOnDiskFile.setText(String.valueOf(size));
		
		getFilePath.setText("");
		getFilePath.setText(pathFile);
		
		filename.setText("");
		filename.setText(fileNameClick);
	}

	public void updateRecInfo(int currentRec){
		currentDepth.setText("");
		currentDepth.setText(String.valueOf(currentRec));
	}

	public void flushFileInfo(){
		sizeOnDiskFile.setText("");
		getFilePath.setText("");
		filename.setText("");
	}


}
