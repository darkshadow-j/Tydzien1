package pl.pawel.hworkone.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.pawel.interfaces.Koszyk;
import pl.pawel.interfaces.ShopPlus;

import java.util.Locale;


@Service
@Profile("plus")
public class ShopPlusImpl extends ShopStartImpl implements ShopPlus {

    @Value("${shop-parameter.vat}")
    public float vat;
    private float brutto;

    @Autowired
    public ShopPlusImpl(Koszyk koszyk) {
        super(koszyk);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void StartShopPlus(){
        this.CountBrutto();
        System.out.println(messageSource.getMessage("brutto", new Object[]{this.getBrutto()}, Locale.forLanguageTag("pl")));
    }

    @Override
    public float CountBrutto() {
        this.setBrutto(super.getTotalPrice() * ((vat/100)+1));
        return this.getBrutto();
    }

    public float getVat() {
        return vat;
    }

    public void setVat(float vat) {
        this.vat = vat;
    }

    public float getBrutto() {
        return brutto;
    }

    public void setBrutto(float brutto) {
        this.brutto = brutto;
    }

}
