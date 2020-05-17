package com.lsk.imagewithtext;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

public class FileChooser implements ActionListener {

	private static final long serialVersionUID = 1L;
	JButton open=null;
	private File selectedFile = null;
	private int mode = JFileChooser.FILES_AND_DIRECTORIES;
	private File defaultFile = new File("C:\\Users\\s\\Documents\\");
	public FileChooser(int mode, File defaultFile) {
		this.mode = mode;
		this.defaultFile = defaultFile;
	}
	public File select() {
		actionPerformed(null);
		return this.selectedFile;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JFileChooser jfc=new JFileChooser();
		jfc.setCurrentDirectory(defaultFile);
		jfc.setFileSelectionMode(mode);
		jfc.showDialog(new JLabel(), "选择");
		File file=jfc.getSelectedFile();
		if(file.isDirectory()){
			System.out.println("文件夹:"+file.getAbsolutePath());
		}else if(file.isFile()){
			System.out.println("文件:"+file.getAbsolutePath());
		}
		System.out.println(jfc.getSelectedFile().getName());
		this.selectedFile = file;
	}

}
