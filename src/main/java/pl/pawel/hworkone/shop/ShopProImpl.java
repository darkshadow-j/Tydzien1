package pl.pawel.hworkone.shop;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.pawel.interfaces.Koszyk;
import pl.pawel.interfaces.ShopPro;

import java.util.Locale;


@Service
@Profile("pro")
public class ShopProImpl extends ShopPlusImpl implements ShopPro {

    @Value("${shop-parameter.rabat}")
    private float rabat;
    private float rabatPrice;

    public ShopProImpl(Koszyk koszyk) {
        super(koszyk);
        System.out.println("Sklep PRO");
    }

    @EventListener(ApplicationReadyEvent.class)
    public void StartShopPro(){
        super.CountBrutto();
        this.CountRabat();
        System.out.println(messageSource.getMessage("rabat", new Object[]{this.getRabatPrice()}, Locale.forLanguageTag("pl")));
    }

    @Override
    public float CountRabat() {
        this.setRabatPrice(super.getBrutto()*(1-(rabat/100)));
        return this.getRabatPrice();
    }

    public float getRabat() {
        return rabat;
    }

    public void setRabat(float rabat) {
        this.rabat = rabat;
    }

    public float getRabatPrice() {
        return rabatPrice;
    }

    public void setRabatPrice(float rabatPrice) {
        this.rabatPrice = rabatPrice;
    }
}
