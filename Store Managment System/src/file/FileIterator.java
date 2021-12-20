package file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Iterator;

import classes.Customer;
import classes.Product;

public class FileIterator {
	private File file;
	private RandomAccessFile raf;
	private long position;

	public FileIterator(String fileName) {
		this.file = new File(fileName);
		this.position = 0;

	}

	public void writeToFileAProduct(String barcode, Product product) throws IOException {
		raf = new RandomAccessFile(file, "rw");
		raf.seek(position);
		raf.writeUTF(barcode);
		raf.writeUTF(product.getCustomer().getName());
		raf.writeUTF(product.getCustomer().getPhoneNumber());
		raf.writeBoolean(product.getCustomer().isSubscribe());
		raf.writeUTF(product.getName());
		raf.writeInt(product.getStorePrice());
		raf.writeInt(product.getCustomerPrice());
		position = raf.getFilePointer();
		raf.close();

	}

	public HashMap<String, Product> readFromFile() throws FileNotFoundException, IOException {
		HashMap<String, Product> productMap = new HashMap<>();
		Product p;
		String bar;
		MyFileIterator it = (MyFileIterator) iterator();
		while (it.hasNext()) {
			p = (Product) it.next();
			bar = it.getBarcode();
			productMap.put(bar, p);
		}
		return productMap;
	}

	public Iterator<Product> iterator() throws FileNotFoundException, IOException {
		return new MyFileIterator();
	}

	public void removeLast() throws FileNotFoundException, IOException {
		MyFileIterator it = (MyFileIterator) iterator();
		while (it.hasNext())
			it.remove();
	}

	public void removeByBarcode(String barcode) throws FileNotFoundException, IOException {
		MyFileIterator it = (MyFileIterator) iterator();
		while (it.hasNext()) {
			it.next();
			if (it.getBarcode().equals(barcode)) {
				it.remove();
			}
		}
	}

	public void removeAll() throws FileNotFoundException, IOException {
		MyFileIterator it = (MyFileIterator) iterator();
		while (it.hasNext()) {
			it.next();
			it.remove();
		}
	}

	public class MyFileIterator implements Iterator<Product> {
		private long last;
		private long pos;
		private String barcode;

		public MyFileIterator() {
			last = -1;
			pos = 0;
		}

		@Override
		public boolean hasNext() {

			return pos < file.length();
		}

		@Override
		public Product next() {
			try {
				raf = new RandomAccessFile(file, "rw");
				while (hasNext()) {
					last = pos;
					raf.seek(pos);
					barcode = raf.readUTF();
					String cusName = raf.readUTF();
					String cusPhone = raf.readUTF();
					boolean isSub = raf.readBoolean();
					String proName = raf.readUTF();
					int storePrice = raf.readInt();
					int cusPrice = raf.readInt();
					pos = raf.getFilePointer();
					raf.close();
					return new Product(proName, storePrice, cusPrice, new Customer(cusName, cusPhone, isSub));
				}
			} catch (IOException e) {

				e.printStackTrace();
			}
			return null;
		}

		public String getBarcode() {
			return barcode;
		}

		public void remove() {
			try {
				raf = new RandomAccessFile(file, "rw");

				if (file.length() - pos > 0) {
					byte arr[] = new byte[(int) (file.length() - pos)];
					raf.seek(pos);
					raf.read(arr);
					raf.setLength(last);
					raf.write(arr);

				} else {
					raf.setLength(last);
				}
				pos = 0;
				raf.close();

			} catch (IOException e) {

				e.printStackTrace();
			}

		}

	}
}
