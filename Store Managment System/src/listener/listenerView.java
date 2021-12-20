package listener;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface listenerView {
	void lisSortChoice(int temp) throws FileNotFoundException, IOException;

	void showAllProductsOnView();

	void addProductFromView(String barcode, String productName, int storePrice, int customerPrice, boolean sub,
			String customerName, String phone) throws FileNotFoundException, IOException;
	void ProductFoundByBarcodeFromView(String barcode);
	void deleteLastFromView() throws FileNotFoundException, IOException;

	void deleteAllFromView() throws FileNotFoundException, IOException;

	void deleteByBarcodeFromView(String barcode) throws FileNotFoundException, IOException;

	void SendMessageFromView(String commercial) throws FileNotFoundException, IOException;

	void approveMessageFromView() throws FileNotFoundException, IOException;

}
