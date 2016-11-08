package com.example.android.icoffee;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        /*wipped cream*/
        CheckBox wippedCream = (CheckBox) findViewById(R.id.notify_me_checkbox);/*find checkbox view*/
        boolean cream = wippedCream.isChecked();/*convert to boolean var named cream*/
        //Log.v("MainActivity", "The cream is " + cream);/*check output*/

        /*milk*/
        CheckBox addMilk = (CheckBox) findViewById(R.id.notify_me_checkbox2);
        boolean milk = addMilk.isChecked();

        /*enter name*/
        TextView enterName = (TextView) findViewById(R.id.enterName);
        String name = enterName.getText().toString();

        double price = calculatePrice(milk, cream);
        String priceMessage = Summary(price, cream, milk, name);
        displayMessage(priceMessage);
    }

    /*
    * Create summary of the order
    *
    * @param price of the order
    * @return text summary
    * */
    private String Summary(double price, boolean cream, boolean milk, String name) {
        String sum = "Name: "
                + name + "\n"
                + "Quantity: "
                + quantity + "\n"
                + "Add whipped cream? "
                + cream + "\n"
                + "Add milk? "
                + milk + "\n"
                + "Total: "
                + price + "$\n"
                + "Thank you!";
        return sum;
    }

    /**
     * Calculates the price of the order.
     *
     * @return total price
     */
    private double calculatePrice(boolean milk, boolean cream) {
        if (milk == true && cream == true) {
            return quantity * 1 + 0.20;
        } else if (milk || cream == true ) {
            return quantity * 1 + 0.10;
        } else {
            return quantity * 1;
        }
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        quantity = quantity - 1;
        displayQuantity(quantity);
    }
}