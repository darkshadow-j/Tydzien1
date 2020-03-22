package pl.pawel.hworkone.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.pawel.hworkone.Product;
import pl.pawel.interfaces.Koszyk;
import pl.pawel.interfaces.ShopStart;

import java.util.List;
import java.util.Locale;

@Profile("start")
@Service
public class ShopStartImpl implements ShopStart {

    private Koszyk koszyk;
    private List<Product> productToBuy;
    private int TotalPrice;
    MessageSource messageSource;


    @Autowired
    public ShopStartImpl(Koszyk koszyk) {
        this.setProductToBuy(koszyk.getProductList());
        this.CountNetto();
    }


    @EventListener(ApplicationReadyEvent.class)
    public void StartStopStart(){
        this.ShowProductToBuy();
        System.out.println(messageSource.getMessage("netto", new Object[]{this.getTotalPrice()}, Locale.forLanguageTag("pl")));
    }

    @Override
    public int CountNetto() {
        TotalPrice=0;
        for(Product p : productToBuy){
            TotalPrice+=p.getPrice();
        }
        return TotalPrice;

    }

    @Override
    public void ShowProductToBuy() {
        productToBuy.forEach((p)-> System.out.println(p));
    }


    public Koszyk getKoszyk() {
        return koszyk;
    }

    public void setKoszyk(Koszyk koszyk) {
        this.koszyk = koszyk;
    }



    public List<Product> getProductToBuy() {
        return productToBuy;
    }

    public void setProductToBuy(List<Product> productToBuy) {
        this.productToBuy = productToBuy;
    }

    public int getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        TotalPrice = totalPrice;
    }

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
}
