package com.rk957019.justjava;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import java.net.URI;
import java.text.NumberFormat;
import java.lang.*;


public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    int quantity = 2;
    boolean hasWhippedCream=false;
    boolean hasChocolate=false;
    int price=10;

    public void submitOrder(View view)
    {
      //  display(quantity);
      //  CheckBox WhippedCream = (CheckBox) findViewById(R.id.checkbox_whipped_cream);
       // boolean hasWhippedCream = WhippedCream.isChecked();
        EditText editTextName = (EditText) findViewById(R.id.Edit_Text_Name);
        String Name = editTextName.getText().toString();
        String priceMessage = "Name: ";
        priceMessage+=Name;
        priceMessage+="\nAdd Whipped Cream? ";
        if(hasWhippedCream)
        {
            priceMessage+="TRUE";
          //  price+=quantity*1;
        }
        else
            priceMessage+="FALSE";
      //  CheckBox Chocolate = (CheckBox) findViewById(R.id.checkbox_chocolate);
      //  boolean hasChololate = Chocolate.isChecked();
        priceMessage+="\nAdd Chocolate ? ";
        if(hasChocolate)
        {
            priceMessage+="TRUE";
           // price+=quantity*2;
        }
        else
            priceMessage+="FALSE";
        priceMessage+="\nQuantity: "+quantity;
        priceMessage+="\nTotal: $" + price;
        priceMessage+="\nThank You!";
        displayMessage(priceMessage);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java Order for "+Name);
        intent.putExtra(Intent.EXTRA_TEXT,priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }
    public void increament(View view)
    {
        quantity++;
        price+=5;
        if(hasChocolate)
        {
            price+=2;
        }
        if(hasWhippedCream)
        {
            price+=1;
        }
        display(quantity);
        displayprice(price);
        TextView price = (TextView) findViewById(R.id.price);
        price.setText("PRICE");
    }
    public void decreament(View view)
    {
        quantity--;
        price-=5;
        if(hasChocolate)
        {
            price-=2;
        }
        if(hasWhippedCream)
        {
            price-=1;
        }
        display(quantity);
        displayprice(price);
        TextView price = (TextView) findViewById(R.id.price);
        price.setText("PRICE");
    }
    public void price_change_whipped_cream(View view)
    {
        CheckBox checkBox = (CheckBox) findViewById(R.id.checkbox_whipped_cream);
        Boolean  hasWhippedCreamPresent = checkBox.isChecked();
        if(hasWhippedCream==true&&hasWhippedCreamPresent==false)
        {
            price-=quantity;
            hasWhippedCream=false;
        }
        else
            {
            price += quantity;
            hasWhippedCream=true;
        }
        display(quantity);
        displayprice(price);
        TextView price = (TextView) findViewById(R.id.price);
        price.setText("PRICE");
    }
    public void price_change_chocolate(View view)
    {
        CheckBox checkBox = (CheckBox) findViewById(R.id.checkbox_chocolate);
        Boolean hasChocolatePresent= checkBox.isChecked();
        if(hasChocolatePresent==false&&hasChocolate==true)
        {
            price-=2*quantity;
            hasChocolate=false;
        }
        else
        {
            price+=2*quantity;
            hasChocolate=true;
        }
        display(quantity);
        displayprice(price);
        TextView price = (TextView) findViewById(R.id.price);
        price.setText("PRICE");
    }
    private int calculatePrice(int quantity)
    {
        int price = quantity * 5;
        return price;
    }
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    private void displayprice(int number)
    {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
    private void displayMessage(String priceMessage)
    {
        TextView price = (TextView) findViewById(R.id.price);
        price.setText("Order Summary");
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(priceMessage);
    }
}
