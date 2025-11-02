package mahdialemiclub.membership;

import mahdialemiclub.service.PricePolicy;;

public class StudentPrice implements PricePolicy {
    private static final double DISCOUNT = 0.15;

    @Override
    public double calculatePrice(double basePrice, int days) {
        double total = basePrice * days;
        return total * (1 - DISCOUNT);
    }

}
