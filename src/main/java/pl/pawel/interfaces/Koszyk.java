package pl.pawel.interfaces;

import pl.pawel.hworkone.Product;

import java.util.List;

public interface Koszyk {

    public void AddProduct();
    public void ShowAllProduct();

    List<Product> getProductList();
}
