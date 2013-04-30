import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JComponent;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import javax.swing.border.*;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;

public class ControlPanel extends JPanel{
	private JPanel file;
	private JPanel directory;
	private JPanel recursionControl;

	private JLabel currentPath;
	private JTextArea goTo;
	private JLabel sizeOnDiskDir;
	private JLabel nbElem;
	private JLabel nbFiles,nbDirectory;
	
	private JLabel sizeOnDiskFile;
	private JTextArea getFilePath;
	private JLabel filename;

	//private JLabel 

	public ControlPanel(){
		super();
		
		file=new JPanel();
		directory=new JPanel();
		recursionControl=new JPanel();

		currentPath=new JLabel();
		goTo=new JTextArea(1,20);
		sizeOnDiskDir=new JLabel();
		nbElem=new JLabel();
		nbFiles=new JLabel();
		nbDirectory=new JLabel();

		getFilePath=new JTextArea(1,20);
		filename=new JLabel();
		sizeOnDiskFile=new JLabel();
		
	
		
		currentPath.setHorizontalAlignment(JLabel.CENTER);
		sizeOnDiskDir.setHorizontalAlignment(JLabel.CENTER);
		nbElem.setHorizontalAlignment(JLabel.CENTER);
		nbFiles.setHorizontalAlignment(JLabel.CENTER);
		nbDirectory.setHorizontalAlignment(JLabel.CENTER);
		filename.setHorizontalAlignment(JLabel.CENTER);
		sizeOnDiskFile.setHorizontalAlignment(JLabel.CENTER);
		
		GridLayout principal=new GridLayout(0,1);
		principal.setVgap(50);

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
		secondary.setVgap(25);

		directory.setLayout(secondary);
		file.setLayout(secondary);
		
		directory.add(padding);
		directory.add(addTitledBorder(currentPath,"Path du repertoire courant",false));
		//directory.add(addTitledBorder(sizeOnDiskDir);
		directory.add(addTitledBorder(nbElem,"Nombre d'element du repertoire courant",false));
		directory.add(addTitledBorder(nbFiles,"Nombre de fichier du repertoirecourant",false));
		directory.add(addTitledBorder(nbDirectory,"Nombre de dossier du repertoire courant",false));
		directory.add(addTitledBorder(goTo,"Afficher le repertoire de path suivant :",true));
		
		file.add(padding2);
		file.add(addTitledBorder(filename,"Nom du fichier selectionne",false));
		file.add(addTitledBorder(getFilePath,"Path du fichier selectionner",false));
		file.add(addTitledBorder(sizeOnDiskFile,"Taille du fichier selectionne en octets",true));
	
		TitledBorder titleDirPanel=BorderFactory.createTitledBorder("Information sur le repertoire courant");
		TitledBorder titleFilePanel=BorderFactory.createTitledBorder("Information sur le fichier");
		
		directory.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10,10,10,10),titleDirPanel));
		file.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10,10,10,10),titleFilePanel));
		
		add(directory);
		add(file);

		setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
	public JComponent addTitledBorder(JComponent c,String name,boolean isLast){
		JPanel p=new JPanel();
		Border e=BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		TitledBorder title=BorderFactory.createTitledBorder(e,name);
		title.setTitleJustification(TitledBorder.CENTER);
		CompoundBorder com;

		if(isLast){
			com=BorderFactory.createCompoundBorder(new EmptyBorder(20,20,20,20),title);
		}
		else {
			com=BorderFactory.createCompoundBorder(new EmptyBorder(20,20,0,20),title);
		}

		p.add(c);
		p.setBorder(com);
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

	public void flushFileInfo(){
		sizeOnDiskFile.setText("");
		getFilePath.setText("");
		filename.setText("");
	}


}
