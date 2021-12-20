package listener;

import classes.Product;

public interface listenerModel {
void sendProducts(String str);
void SuccesAddingToModel();
void duplicateBarcode();
void noBarcodeFound();
void succesRemovingFromModel();
void messageSent();
void messageApprove(String name);
void foundProductByBarcode(Product product);
void errorMessage();
}
