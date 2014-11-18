package Huffman;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class node {
	public String code;
	public String cha;
	public int prob;

	node() {
		prob = 0;
		code = "";
		cha = "";
	}

	public node(int p, String co, String ch) {
		prob = p;
		code = co;
		cha = ch;
	}

	public String toString() {
		return cha + " " + prob + "->" + code;
	}

	public static ArrayList<node> CalculateProb(String data) {

		ArrayList<node> proTable = new ArrayList<>();
		HashMap<String, Integer> map = new HashMap<String, Integer>();

		for (int i = 0; i < data.length(); i++) {
			if (!map.containsKey(data.charAt(i) + "")) {
				map.put(data.charAt(i) + "", 1);

			} else {
				map.put(data.charAt(i) + "", map.get(data.charAt(i) + "") + 1);
			}

		}
		for (String x : map.keySet()) {
			node NewNode = new node();
			NewNode.prob = map.get(x);
			NewNode.cha = x;
			proTable.add(NewNode);
		}
		Collections.sort(proTable, new Comparator<node>() {

			@Override
			public int compare(node arg0, node arg1) {

				// TODO Auto-generated method stub
				return -(arg0.prob - arg1.prob);
			}
		});
		// System.out.println(proTable.toString());
		return proTable;
	}

	public static ArrayList<node> CalculateCode(ArrayList<node> arr) {

		if (arr.size() > 2) {
			ArrayList<node> NewArr = new ArrayList<>();
			node temp = new node();
			temp.cha = arr.get(arr.size() - 1).cha
					+ arr.get(arr.size() - 2).cha;
			temp.prob = arr.get(arr.size() - 1).prob
					+ arr.get(arr.size() - 2).prob;

			for (int i = 0; i < arr.size() - 2; i++) {
				node lastnode = new node();
				lastnode = arr.get(i);
				NewArr.add(lastnode);
			}

			NewArr.add(temp);
			Collections.sort(NewArr, new Comparator<node>() {

				@Override
				public int compare(node arg0, node arg1) {

					// TODO Auto-generated method stub
					return -(arg0.prob - arg1.prob);
				}
			});
			// System.out.println(arr.toString());
			ArrayList<node> recersive = CalculateCode(NewArr);
			// System.out.println(recersive.toString());
			// recersive.add(arr.get(arr.size() - 1 ));
			// recersive.add(arr.get(arr.size() - 2));
			// for (int i = 0; i < recersive.size(); i++) {
			// recersive.get(i).code += "0";
			// recersive.get(i).code += "1";
			// }
			for (int i = 0; i < recersive.size(); i++) {
				boolean flag = true;
				for (int j = 0; j < arr.size(); j++) {
					if (arr.get(j).cha.equals(recersive.get(i).cha)) {
						arr.get(j).code = recersive.get(i).code;
					} else if (recersive.get(i).cha.contains(arr.get(j).cha)) {
						if (flag) {
							arr.get(j).code = recersive.get(i).code + "1";
							flag = false;
						} else
							arr.get(j).code = recersive.get(i).code + "0";

					}

				}

			}

		} else if (arr.size() == 2) {
			arr.get(0).code = "0";
			arr.get(1).code = "1";
		}
	//	System.out.println(arr);
		return arr;

	}

	public static String compresion(String data, ArrayList<node> decionary)
			throws IOException {
		String compresd = "";
		HashMap<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < decionary.size(); i++) {
			map.put(decionary.get(i).cha + "", decionary.get(i).code);
		}

		for (int i = 0; i < data.length(); i++) {
			compresd += map.get(data.charAt(i) + "");
		}
		//System.out.println(compresd);
		FileHandler file = new FileHandler();
		file.save(compresd, decionary);
		return compresd;
	}

	public static String deCompress(String date, ArrayList<node> pro) {
		String res = "";
		HashMap<String, String> map = new HashMap<String, String>();

		for (int i = 0; i < pro.size(); i++) {
			map.put(pro.get(i).code, pro.get(i).cha);
		}

		String temp = "";

		for (int i = 0; i < date.length(); i++) {
			temp += date.charAt(i);

			if (map.containsKey(temp)) {
				res += map.get(temp);
				temp = "";
			}
		}
	//	System.out.print(res);

		return res;
	}

	public static void main(String arges[]) throws IOException {
//		String x = "abac";
//		ArrayList<node> arr = new ArrayList<>();
//		arr = CalculateProb(x);
//		File xy = new File("output.huffman");
//		compresion(x, CalculateCode(arr));
//		FileHandler file = new FileHandler();
//		file.readAndDecompress(xy);
//		deCompress(compresion(x, CalculateCode(arr)), arr);
		new App();

	}

}
