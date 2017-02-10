package css.cis3334.pizzaorder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by tgibbons on 2/10/2017.
 */
public class PizzaOrder implements PizzaOrderInterface {

    public static final Double DELIVERY_PRICE = 2.50;

    private updateViewInterface view;           // link to the activity displaying the orders
    private List<Pizza> pizzasInOrder;          // list of pizzas ordered so far
    private boolean delivery;                   // true if customer wants order delivered

    public PizzaOrder (updateViewInterface view) {
        this.view = view;
        pizzasInOrder = new ArrayList<Pizza>();
    }

    @Override
    public String OrderPizza(String topping, String strSize, boolean extraCheese){
        Pizza.pizzaSize size;
        if (strSize.equalsIgnoreCase("small")) {
            size = Pizza.pizzaSize.SMALL;
        } else  if (strSize.equalsIgnoreCase("medium")) {
            size = Pizza.pizzaSize.MEDIUM;
        } else {
            size = Pizza.pizzaSize.LARGE;
        }
        Pizza newPizza = new Pizza(topping, size, extraCheese);
        pizzasInOrder.add(newPizza);
        view.updateView(newPizza.toString() + " added to order");

        return newPizza.toString();             // return a description of what was ordered
    }

    @Override
    public Double getTotalBill() {
        Double total = 0.0;
        for (Pizza p:pizzasInOrder ){
            total += p.getPrice();
        }
        if (delivery) {
            total += DELIVERY_PRICE;
        }
        return total;
    }

    @Override
    public Double getSmallPrice () {
        return Pizza.SMALL_PRICE;
    }

    @Override
    public Double getMediumPrice() {
        return Pizza.MEDIUM_PRICE;
    }

    @Override
    public Double getLargePrize() {
        return Pizza.LARGE_PRICE;
    }

    @Override
    public Double getExtraCheesePrice() {
        return Pizza.EXTRA_CHEESE_PRICE;
    }

    @Override
    public void setDelivery(boolean deliver) {
        this.delivery = deliver;
    }

    @Override
    public boolean getDelivery() {
        return delivery;
    }

    static Integer timer = 0;
    public void startPizzaTimer(){

        final ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleWithFixedDelay(new Runnable()
        {
            @Override
            public void run()
            {
                timer ++;
                view.updateView("Pizza is baking");
            }
        }, 0, 5, TimeUnit.SECONDS);

    }

}
