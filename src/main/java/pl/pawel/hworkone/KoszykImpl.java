package pl.pawel.hworkone;

import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;
import org.springframework.stereotype.Service;
import pl.pawel.interfaces.Koszyk;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class KoszykImpl implements Koszyk {

    List<Product> productList;

    public KoszykImpl() {
        productList = new ArrayList<>();
        this.AddProduct();
    }

    @Override
    public void AddProduct() {
        productList.add(new Product("A", this.GeneratePrice()));
        productList.add(new Product("A", this.GeneratePrice()));
        productList.add(new Product("A", this.GeneratePrice()));
        productList.add(new Product("A", this.GeneratePrice()));
        productList.add(new Product("A", this.GeneratePrice()));
    }

    private int GeneratePrice(){
        Random rand = new Random();
        return rand.nextInt(300) + 50;
    }

    @Override
    public void ShowAllProduct() {
        productList.forEach((p)->System.out.println(p));

    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}