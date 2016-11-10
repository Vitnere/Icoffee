package com.example.android.icoffee;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 1;

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
        //Log.v("MainActivity", "The cream is " + cream);*//*check output*//*

        /*milk*/
        CheckBox addMilk = (CheckBox) findViewById(R.id.notify_me_checkbox2);
        boolean milk = addMilk.isChecked();

        /*enter name*/
        TextView enterName = (TextView) findViewById(R.id.enterName);
        String name = enterName.getText().toString();

        double price = calculatePrice(milk, cream);
        String priceMessage = Summary(price, cream, milk, name);

        /*
        * share order
        *
        tested on google inbox, gmail, viber,
         samsung stack sms app, google keep,
         facebook litle, hangouts, google translate*/
        Intent intent2 = new Intent();
        intent2.setAction(Intent.ACTION_SEND);
        intent2.setType("text/plain");
        intent2.putExtra(Intent.EXTRA_SUBJECT, "Coffee order");
        intent2.putExtra(Intent.EXTRA_TEXT, priceMessage);
        startActivity(Intent.createChooser(intent2, "Share via"));
    }

    /*
    * Create summary of the order
    *
    * @param price of the order
    * @return text summary
    * */
    private String Summary(double price, boolean cream, boolean milk, String name) {
       /*if(cream) {
            String creamx = Boolean.toString(cream);
            creamx = "yes";
           return creamx;
        }*///convert boolen to string and print yes*/

        String sum = getString(R.string.name)
                + name + "\n"
                + getString(R.string.quantity)
                + quantity + "\n"
                + getString(R.string.cream)
                + cream + "\n"
                + getString(R.string.milk)
                + milk + "\n"
                + getString(R.string.total)
                + price + "$\n"
                + getString(R.string.thank_you);
        return sum;
    }

    /**
     * Calculates the price of the order.
     *
     * @return total price
     */
    private double calculatePrice(boolean milk, boolean cream) {
        if (milk && cream) {
            return quantity * 1.20;
        } else if (milk || cream) {
            return quantity * 1.10;
        } else {
            return quantity;
        }
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
        if (quantity < 100) {
            quantity += 1;
            displayQuantity(quantity);
        } else {
            Context context = getApplicationContext();
            CharSequence text = getString(R.string.toast_max);
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        if (quantity > 1) {
            quantity -= 1;
            displayQuantity(quantity);
        } else {
            Context context = getApplicationContext();
            CharSequence text = getString(R.string.toast_min);
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }
}