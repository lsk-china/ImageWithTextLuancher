package com.lsk.imagewithtext;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {
	
	private static JFrame frame = new JFrame("图片P字");
	private static JPanel mainContainer = new JPanel();
	private static JLabel textLable = new JLabel("文字");
	private static JTextField text = new JTextField(20);
	private static JLabel fontSizeLable = new JLabel("字体大小");
	private static JTextField fontSize = new JTextField(2);
	private static JLabel fontLocationLable = new JLabel("文字位置");
	private static JTextField fontLocation = new JTextField(7);
	private static JLabel fontColorLable = new JLabel("文字颜色");
	private static JLabel fontColorShowLable = new JLabel("example");
	private static JLabel sourceLocationLable = new JLabel("图片路径");
	private static JLabel saveLoactionLabe = new JLabel("保存路径");
	private static JButton runButton = new JButton("运行");
	private static JButton chooseColorButton = new JButton("...");
	private static JButton chooseSaveLocationButton = new JButton("...");
	private static JButton chooseSourceLocationButton = new JButton("...");
	
	private static File source = null;
	private static File save = null;
	private static Color textColor = Color.black;
	
	private static void initGUI() {
		String lookAndFeel = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
		try {
			UIManager.setLookAndFeel(lookAndFeel);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(950, 75);
    	frame.setLocation(getLocation(frame.getSize()));
    	frame.setVisible(true);
    	frame.add(mainContainer);
    	
    	mainContainer.add(textLable);
    	mainContainer.add(text);
    	mainContainer.add(fontSizeLable);
    	mainContainer.add(fontSize);
    	mainContainer.add(fontLocationLable);
    	mainContainer.add(fontLocation);
    	mainContainer.add(fontColorLable);
    	mainContainer.add(fontColorShowLable);
    	mainContainer.add(chooseColorButton);
    	mainContainer.add(sourceLocationLable);
    	mainContainer.add(chooseSourceLocationButton);
    	mainContainer.add(saveLoactionLabe);
    	mainContainer.add(chooseSaveLocationButton);
    	mainContainer.add(runButton);
    	
    	fontColorShowLable.setSize(5, 5);
    	fontColorShowLable.setForeground(textColor);
    	
    	chooseColorButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					ColorChooser chooser = new ColorChooser(frame);
					textColor = chooser.select();
					fontColorShowLable.setForeground(textColor);
				}catch(NullPointerException npe) {
					
				}
			}
		});
    	chooseSaveLocationButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					FileChooser chooser = new FileChooser(JFileChooser.DIRECTORIES_ONLY,new File("D://"));
					save = chooser.select();
				}catch (NullPointerException npe) {
					// TODO: handle exception
				}
			}
		});
    	chooseSourceLocationButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					FileChooser chooser = new FileChooser(JFileChooser.FILES_ONLY,null);
					source = chooser.select();
				}catch(NullPointerException npe) {
					
				}
			}
		});
    	runButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String fontSizeString = fontSize.getText();
				String locationString = fontLocation.getText();
				String word = text.getText();
				if(isEmpty(fontSizeString) || isEmpty(word) || isEmpty(locationString) || textColor == null || source == null || save == null) {
					JOptionPane.showMessageDialog(frame, "请填写所有参数","错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
				String command = "python D://imagewithword.py "+source.getAbsolutePath()+" "+word+" "+locationString.replace(",", " ")+" "+fontSizeString+" "+textColor.getRed()+" "+textColor.getGreen()+" "+textColor.getBlue()+" "+save.getAbsolutePath();
				System.out.println(command);
				try {
					Runtime runtime = Runtime.getRuntime();
					Process process = runtime.exec(command);
					BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
					String result = "";
					while((result = bufferedReader.readLine()) != null) {
						System.out.println(result);
					}
				}catch (IOException ioe) {
					// TODO: handle exception
					ioe.printStackTrace();
					JOptionPane.showMessageDialog(frame, "未知错误"+ioe.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
				}
				JOptionPane.showMessageDialog(frame, "生成成功", "成功", JOptionPane.INFORMATION_MESSAGE);
			}
		});
	}
	private static Point getLocation(Dimension componentSize) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screenSize.width / 2) - (componentSize.width / 2);
		int y = (screenSize.height / 2) - (componentSize.height / 2);
		return new Point(x, y);
	}
	public static boolean isEmpty(String s) {
		s = s.trim();
		if(s == null || s.equals("")) {
			return true;
		}
		return false;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		initGUI();
	}

}
