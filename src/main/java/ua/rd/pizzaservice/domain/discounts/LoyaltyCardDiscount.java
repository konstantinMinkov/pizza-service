package ua.rd.pizzaservice.domain.discounts;


import org.springframework.stereotype.Component;
import ua.rd.pizzaservice.domain.LoyaltyCard;
import ua.rd.pizzaservice.domain.Order;


public class LoyaltyCardDiscount implements Discount {

    private final long ONE_HUNDRED_PERCENTS = 100;
    private final long DISCOUNT_PERCENTAGE;
    private final long TOTAL_PRICE_PERCENTAGE;

    public LoyaltyCardDiscount(final long discountPercentage,
                               final long totalPricePercentage) {
        DISCOUNT_PERCENTAGE = discountPercentage;
        TOTAL_PRICE_PERCENTAGE = totalPricePercentage;
    }

    @Override
    public Long apply(Order order) {
        LoyaltyCard loyaltyCard = order.getCustomer().getLoyaltyCard();

        if (loyaltyCard == null) return 0L;
        if (cardIsEmpty(loyaltyCard)) return 0L;

        long maxDiscount = maxDiscountForOrder(order);
        long discount = discountFromCard(loyaltyCard);

        if (discount > maxDiscount) {
            return maxDiscount;
        }
        return discount;
    }

    private long maxDiscountForOrder(Order order) {
        long totalPrice = order.totalPrice();
        return totalPrice * TOTAL_PRICE_PERCENTAGE / ONE_HUNDRED_PERCENTS;
    }

    private long discountFromCard(LoyaltyCard card) {
        long loyaltyCardBalance = card.getBalance();
        return loyaltyCardBalance * DISCOUNT_PERCENTAGE / ONE_HUNDRED_PERCENTS;
    }

    private boolean cardIsEmpty(LoyaltyCard card) {
        final Long ZERO = 0L;
        return ZERO.equals(card.getBalance());
    }
}
