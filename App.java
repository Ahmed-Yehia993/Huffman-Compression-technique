package Huffman;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class App extends JFrame {
	private JLabel label;
	private JButton compress;
	private JButton Decompress;
	String path;

	App() {
		super("Huffman");
		Panel f = new Panel();
		setLayout(null);
		setVisible(true);
		setBounds(400, 200, 400, 250);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		compress = new JButton("Compress");
		Decompress = new JButton("DeCompress");
		label = new JLabel("Hello to My hufman");

		label.setBounds(100, 20, 220, 30);
		f.add(label);
		add(label);
		label.setVisible(true);

		compress.setBounds(250, 120, 115, 50);
		f.add(compress);
		add(compress);

		Decompress.setBounds(20, 120, 115, 50);
		f.add(Decompress);
		add(Decompress);
		Decompress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();

				// For Directory
				// fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

				// For File
				// fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

				fileChooser.setAcceptAllFileFilterUsed(false);

				int rVal = fileChooser.showOpenDialog(null);
				if (rVal == JFileChooser.APPROVE_OPTION) {
					path = (fileChooser.getSelectedFile().getAbsolutePath());
					Scanner read = null;
					String input = null;
					String data = "";
					
						try {
							read = new Scanner(new File(path));
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					
						
					while (read.hasNext()) {
						input = read.nextLine();
						data += input + "\n";
					}
					
					ArrayList<node> arr = new ArrayList<>();
					arr = node.CalculateProb(data);
					
					
					//	node.compresion(data, node.CalculateCode(arr));
					
					FileHandler file = new FileHandler();
					
						File xy = new File("output.huffman");
						try {
							FileHandler.readAndDecompress(xy);
						} catch (IOException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
					

				}
			}
		});
		 
		 
		compress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();

				// For Directory
				// fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

				// For File
				// fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

				fileChooser.setAcceptAllFileFilterUsed(false);

				int rVal = fileChooser.showOpenDialog(null);
				if (rVal == JFileChooser.APPROVE_OPTION) {
					path = (fileChooser.getSelectedFile().getAbsolutePath());
					Scanner read = null;
					String input = null;
					String data = "";
					try {
						read = new Scanner(new File(path));
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					while (read.hasNext()) {
						input = read.nextLine();
						data += input + "\n";
					}
					
					ArrayList<node> arr = new ArrayList<>();
					arr = node.CalculateProb(data);
					
					try {
						node.compresion(data, node.CalculateCode(arr));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				

				}
			}
		});

		/*
		 * Scanner read = null; String input = null; String data = "";
		 * 
		 * try { read = new Scanner(new File(path)); } catch
		 * (FileNotFoundException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } while (read.hasNext()) { input =
		 * read.nextLine(); data += input + "\n"; } ArrayList<Tage> k =
		 * main.compress(data); System.out.println(data);
		 */

	}
}