/*package file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import classes.Product;
import file.FileIterator.MyFileIterator;

public class FileReader {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		FileIterator fi = new FileIterator("products.txt");
		FileIterator fi2 = new FileIterator("products.txt");
		MyFileIterator mfi = (MyFileIterator) fi.iterator();
		MyFileIterator mfi2 = (MyFileIterator) fi.iterator();
		ArrayList<String> barcodesList = new ArrayList<>();
		HashMap<String, Product> productsMap = new HashMap<>();
		productsMap = fi.readFromFile();
		System.out.println(productsMap.toString());
		for(Entry<String, Product> p : productsMap.entrySet()) {
			barcodesList.add(p.getKey()); 
		}
		System.out.println(barcodesList.toString());
		while(mfi2.hasNext())
		{
			Product p = mfi2.next();
			String barcode = mfi2.nextBarcode();
			if(barcode.matches("b1"))
			{
			mfi2.remove();
			break;
			}
		}
		productsMap = fi2.readFromFile();
		System.out.println(productsMap.toString());
		for(Entry<String, Product> p : productsMap.entrySet()) {
			barcodesList.add(p.getKey()); 
		}
		System.out.println(barcodesList.toString());
		
	
	}

	public static void writeToFile(HashMap<String, Product> productsMap, ArrayList<String> barcodesList)
			throws FileNotFoundException, IOException {
		File file = new File("products.txt");
		ObjectOutputStream oOut = new ObjectOutputStream(new FileOutputStream(file, true));
		for (int i = 0; i < barcodesList.size(); i++) {
			oOut.writeUTF(barcodesList.get(i));
			oOut.writeObject(productsMap.get(barcodesList.get(i)));
		}
		System.out.println("YES");
		oOut.close();
	}

}
*/