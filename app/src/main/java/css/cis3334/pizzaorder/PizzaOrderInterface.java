package css.cis3334.pizzaorder;

/**
 * Created by tgibbons on 2/10/2017.
 */
public interface PizzaOrderInterface {
    String OrderPizza(String topping, String strSize, boolean extraCheese);
    Double getTotalBill();
    Double getSmallPrice();
    Double getMediumPrice();
    Double getLargePrize();
    Double getExtraCheesePrice();
    void setDelivery(boolean deliver);
    boolean getDelivery();

}
