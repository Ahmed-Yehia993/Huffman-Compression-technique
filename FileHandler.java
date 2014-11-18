package Huffman;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

public class FileHandler {

	public static void save(String res, ArrayList<node> list)
			throws IOException {

		ArrayList<Integer> bin = new ArrayList<Integer>();
		int num = 0;
		for (int i = 0, j = 1; i < res.length(); i++, j++) {
			if (j % 32 == 0) {
				j = 1;
				bin.add(num);
				num = (int) ((res.charAt(i) == '1' ? 1 : 0) * Math
						.pow(2, j - 1));
			} else
				num += (int) (res.charAt(i) == '1' ? 1 : 0)
						* Math.pow(2, j - 1);
		}
		//System.out.print(bin.size());

		File file = new File("output.huffman");
		FileOutputStream output = new FileOutputStream(file);
		DataOutputStream out = new DataOutputStream(output);
		out.writeShort(list.size());
		for (int i = 0; i < list.size(); i++) {
			out.writeChar(list.get(i).cha.charAt(0));
			out.writeUTF(list.get(i).code);
		}
		out.writeShort(bin.size());
		for (int i = 0; i < bin.size(); i++) {
			out.writeInt(bin.get(i));
		}
		out.close();
		output.close();

	}

	public static String readAndDecompress(File file) throws IOException {
		
		ArrayList<node> list = new ArrayList<node>();
		ArrayList<Integer> bin = new ArrayList<Integer>();

		FileInputStream input = new FileInputStream(file);
		DataInputStream in = new DataInputStream(input);

		int temp = in.readShort();

		for (int i = 0; i < temp; i++) {
			char ch = in.readChar();
			list.add(new node(0, in.readUTF(), ch + ""));
		}

		temp = in.readShort();
		for (int i = 0; i < temp; i++) {
			bin.add(in.readInt());
		}
		input.close();
		in.close();

		String data = "";
		int num;
		for (int i = 0; i < bin.size(); i++) {
			num = bin.get(i);
			for (int j = 0; j < 31; j++) {
				data += (num % 2 == 1 ? "1" : "0");
				num /= 2;
			}
		}

		// if (data.length() > len)
		// data = data.substring(0, len + 1);
		String res = node.deCompress(data, list);
		// System.out.println(res);
//		PrintWriter out = new PrintWriter("Orginal.txt");
//		
//		out.println(res);
		//FileUtils.writeStringToFile(new File("Orginal.txt"), res);
		
       FileWriter newTextFile = new FileWriter("Orginal.txt");

        BufferedWriter fw = new BufferedWriter(newTextFile);
        for (int i = 0; i < res.length(); i++) {
			if(res.charAt(i)!= '\n')
			
			{
				fw.write(res.charAt(i));
			}
			else
			{
				fw.newLine();
			}
		}
        
        fw.close();
	//	System.out.println(res);
		return res;
	}

}
