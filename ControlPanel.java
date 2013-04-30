public class ControlPanel extends JPanel(){
	private JLabel infoDirectory;
	private JLabel currentPath;
	private JTextArea goTo;
	private JLabel sizeOnDiskDir;
	private jLabel nbFiles,nbDirectory;
	
	private JLabel infoFileClickedOn;
	private JLabel sizeOnDiskFile;
	private JTextArea getFilePath;
	private JLabel filename;

	public ControlPanel(){
		super();
		
		currentPath=new JLabel();
		infoDirectory=new JLabel();
		goTo=new JTextArea(5,35);
		sizeOnDiskDir=new JLabel();
		nbFiles=new JLabel();
		nbDirectory=new JLabel();

		infoFileClickedOn=new JLabel();
		getFilePath=new JTextArea(5,35);
		filename=new JLabel(5,20);
		sizeOnDiskFile=new JLabel();

		setLayout(new GridLayout(10,1));
	}

	public void updateDirInfo(String currentDirPath,int sizeCurDir,int nbNewFile,int nbNewDirectory){
		currentPath.setText("");
		currentPath.setText(currentDirPath);
		
		sizeOnDisk.setText("");
		sizeOnDisk.setText(String.valuesOf(sizeCurDir));
		
		nbFiles.setText("");
		nbFiles.setText(String.valuesOf(nbNewFile));
		
		nbDirectory.setText("");
		nbDirectory.setText(String.valuesOf(nbNewDirectory));
	}

	public void updateFileInfo(String fileNameClick,int size,String pathFile) {
		sizeOnDisk.setText("");
		sizeOnDisk.setText(String.valuesOf(size));
		
		getFilePath.setText("");
		getFilePath.setText(pathFile);
		
		fileName.setText("");
		fileName.setText(fileNameClick);
	}

	public void flushFileInfo{
		sizeOnDisk.setText("");
		getFilePath.setText("");
		fileName.setText("");
	}

	public void set {
	
	}
	public void set {
	
	}
	public void set {
	
	}
	public void set {
	
	}

}
