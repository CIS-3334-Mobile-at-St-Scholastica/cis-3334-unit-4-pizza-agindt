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
    PizzaOrderInterface newOrder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newOrder = new PizzaOrder(this);

        // Set up our radio buttons
        rbSmall = (RadioButton) findViewById(R.id.radioButtonSmall);
        rbSmall.setText(rbSmall.getText() + " - " + newOrder.getPrice(Pizza.pizzaSize.SMALL).toString());
        rbMedium = (RadioButton) findViewById(R.id.radioButtonMedium);
        rbMedium.setText(rbMedium.getText() +  " - " + newOrder.getPrice(Pizza.pizzaSize.MEDIUM).toString());
        rbLarge = (RadioButton) findViewById(R.id.radioButtonLarge);
        rbLarge.setText(rbLarge.getText() +  " - " + newOrder.getPrice(Pizza.pizzaSize.LARGE).toString());

        // Set up the Check Boxes
        chkbxCheese = (CheckBox) findViewById(R.id.checkBoxCheese);
        chkbxDelivery = (CheckBox) findViewById(R.id.checkBoxDeluvery);

        // Set up the TextViews
        txtTotal = (TextView) findViewById(R.id.textViewTotal);
        txtStatus = (TextView) findViewById(R.id.textViewStatus);
        txtPizzasOrdered = (TextView) findViewById(R.id.textViewPizzasOrdered);
        // Set up the Spinner
        spinnerToppings = (Spinner) findViewById(R.id.spinnerToppings);

    }

    @Override
    public void updateOrderStatusInView(String orderStatus) {

        txtStatus.setText("Order Status: " + orderStatus);
    }

    public void onClickOrder(View view) {
        // ****** For the Assignment, students need to add code here to get information from the UI widgets...

        String orderDescription = "No orders yet";
        String topping = spinnerToppings.getSelectedItem().toString();
        String pSize = "Small";

        if (rbSmall.isChecked())
        {
            pSize = "Small";
        }
        else if (rbMedium.isChecked())
        {
            pSize = "Medium";
        }
        else if (rbLarge.isChecked())
        {
            pSize = "Large";
        }

        Boolean cheesiness = false;

        if (chkbxCheese.isChecked())
        {
            cheesiness = true;
        }

        boolean forDelivery = false;

        if (chkbxDelivery.isChecked())
        {
            forDelivery = true;
        }

        // ****** For the Practice Activity, students need to call to OrderPizza here
        orderDescription = newOrder.OrderPizza(topping, pSize, cheesiness);
        newOrder.setDelivery(forDelivery);
        txtTotal.setText("Your total is: " + newOrder.getTotalBill().toString());


        // ****** For the Assignment, students will modify the order to fit the type of pizza the user selects using the UI widgets

        //display a pop up message for a long period of time
        Toast.makeText(getApplicationContext(), "You have ordered a "+orderDescription , Toast.LENGTH_LONG).show();
        // add this pizza to the textview the lists the pizzas
        txtPizzasOrdered.append(orderDescription+"\n");

    }
}
