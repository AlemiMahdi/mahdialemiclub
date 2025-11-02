package mahdialemiclub.membership;

import mahdialemiclub.service.PricePolicy;;

public class PremiumPrice implements PricePolicy {
    private static final double DISCOUNT = 0.10;

    @Override
    public double calculatePrice(double basePrice, int days) {
        double total = basePrice * days;
        return total * (1 - DISCOUNT);
    }

}
