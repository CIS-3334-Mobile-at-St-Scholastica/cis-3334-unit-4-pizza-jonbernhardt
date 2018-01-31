package css.cis3334.pizzaorder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements updateViewInterface {

    RadioButton rbSmall;
    RadioButton rbMedium;
    RadioButton rbLarge;
    CheckBox chkbxCheese;
    CheckBox chkbxDelivery;
    TextView txtTotal;
    TextView txtStatus;
    TextView txtPizzasOrdered;
    Spinner spinnerToppings;
    PizzaOrderInterface pizzaOrderSystem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pizzaOrderSystem = new PizzaOrder(this);
        rbSmall = (RadioButton) findViewById(R.id.radioButtonSmall);
        rbMedium = (RadioButton) findViewById(R.id.radioButtonMedium);
        rbLarge = (RadioButton) findViewById(R.id.radioButtonLarge);
        rbSmall.append(" -- Price: $" + pizzaOrderSystem.getPrice(PizzaOrderInterface.PizzaSizes.SMALL));
        rbMedium.append(" -- Price: $" + pizzaOrderSystem.getPrice(PizzaOrderInterface.PizzaSizes.MEDIUM));
        rbLarge.append(" -- Price: $" + pizzaOrderSystem.getPrice(PizzaOrderInterface.PizzaSizes.LARGE));

        chkbxCheese = (CheckBox) findViewById(R.id.checkBoxCheese);
        chkbxDelivery = (CheckBox) findViewById(R.id.checkBoxDeluvery);

        txtTotal = (TextView) findViewById(R.id.textViewTotal);
        txtStatus = (TextView) findViewById(R.id.textViewStatus);
        txtPizzasOrdered = (TextView) findViewById(R.id.textViewPizzasOrdered);
        spinnerToppings = (Spinner) findViewById(R.id.spinnerToppings);

    }

    @Override
    public void updateOrderStatusInView(String orderStatus) {

        txtStatus.setText("Order Status: " + orderStatus);
    }

    public void onClickOrder(View view) {
        pizzaOrderSystem.setDelivery( chkbxDelivery.isChecked() );
        String topping = spinnerToppings.getSelectedItem().toString();
        String size = "large";
        if (rbSmall.isChecked()) {
            size = "small";
        }
        if (rbMedium.isChecked()) {
            size = "medium";
        }
        String orderDescription = pizzaOrderSystem.OrderPizza(topping, size, chkbxCheese.isChecked());
        //display a pop up message for a long period of time
        Toast.makeText(getApplicationContext(), "You have ordered a "+orderDescription , Toast.LENGTH_LONG).show();
        txtTotal.setText("Total Due: " + pizzaOrderSystem.getTotalBill().toString());
        txtPizzasOrdered.append(orderDescription+"\n");

    }
}
