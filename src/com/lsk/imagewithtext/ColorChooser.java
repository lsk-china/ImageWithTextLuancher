package com.lsk.imagewithtext;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ColorChooser implements ActionListener {

	JButton open=null;
	private Color selectedColor = null;
	private JFrame container;
	
	public ColorChooser(JFrame container) {
		this.container = container;
	}
	public Color select() {
		actionPerformed(null);
		return this.selectedColor;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Color color = JColorChooser.showDialog(container, "Ñ¡ÔñÑÕÉ«", Color.white);
		System.out.println(color.toString());
		this.selectedColor = color;
	}
}
