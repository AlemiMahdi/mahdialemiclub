package mahdialemiclub.membership;

import mahdialemiclub.service.PricePolicy;

public class StandardPrice implements PricePolicy {

    @Override
    public double calculatePrice(double basePrice, int days) {
        return basePrice * days;
    }

}
