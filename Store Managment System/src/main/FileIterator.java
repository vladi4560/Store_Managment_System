/*package file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import classes.Product;

public class FileIterator {
	private File file;
	private ObjectOutputStream oOut = null;
	private boolean isAppendable;
	private long positionToDelete;
	private long positionToDeleteLast;
	private FileOutputStream fOut;

	public FileIterator(String fileName) throws FileNotFoundException, IOException {
		file = new File(fileName);
		ISAppendable();
	}

	public void ISAppendable() throws IOException {
		isAppendable = file.exists();
		if (isAppendable) {
			fOut = new FileOutputStream(file, isAppendable);
			oOut = new ObjectOutputStream(fOut) {
				@Override
				protected void writeStreamHeader() throws IOException {
					return;
				}
			};
		} else {
			fOut = new FileOutputStream(file);
			oOut = new ObjectOutputStream(fOut);
		}
	}

	public Iterator<Product> iterator() throws FileNotFoundException, IOException {
		return new MyFileIterator();
	}

	public class MyFileIterator implements Iterator<Product> {
		private Product product;
		private String barCode;
		private ObjectInputStream oIn;
		private FileInputStream fIn;
		private RandomAccessFile raf;

		public MyFileIterator() throws FileNotFoundException, IOException {
			fIn = new FileInputStream(file);
			oIn = new ObjectInputStream(fIn);

		}

		@Override
		public boolean hasNext() {
			try {
				return oIn.available() != 0;
			} catch (IOException e) {
				e.printStackTrace();
			}
			return false;
		}

		@Override
		public Product next() {
			try {
				positionToDelete = fIn.getChannel().position();
				barCode = oIn.readUTF();
				product = (Product) oIn.readObject();
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			return product;
		}

		public String nextBarcode() {
			return barCode;
		}

		public void remove() {
			try {
				raf = new RandomAccessFile(file, "rw");
				long initPos = positionToDelete;
				long pos = fIn.getChannel().position();
				raf.seek(pos);
				byte[] temp = new byte[(int) (file.length() - pos)];
				raf.read(temp);
				raf.setLength(initPos + (file.length() - pos));
				raf.seek(initPos);
				raf.write(temp);
				raf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public long getPositionToDelete() {
			return positionToDelete;
		}

	}

	public HashMap<String, Product> readFromFile() throws FileNotFoundException, IOException {
		HashMap<String, Product> productMap = new HashMap<>();
		Product p;
		String bar;
		MyFileIterator it = (MyFileIterator) iterator();
		while (it.hasNext()) {
			p = (Product) it.next();
			bar = it.nextBarcode();
			productMap.put(bar, p);

		}

		return productMap;
	}

	public void writeToFileAProduct(Product product, String barcode) throws FileNotFoundException, IOException {
		positionToDeleteLast = fOut.getChannel().position();
		oOut.writeUTF(barcode);
		oOut.writeObject(product);
		///////// Checking///////////
		System.out.println("WORKING");
	}
	public void deleteLastFromFile() {
		try {
			RandomAccessFile raf = new RandomAccessFile(file, "rw");
			raf.setLength(positionToDeleteLast);
			raf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void deleteAll() throws IOException {
MyFileIterator mfi = (MyFileIterator) iterator();
while(mfi.hasNext())
{
	//mfi.next();
	mfi.remove();
//	mfi = (MyFileIterator) iterator();
	System.out.println(file.length());
}
	}
}
*/