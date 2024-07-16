package comp3350.Innovator2.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import comp3350.Innovator2.R;
import comp3350.Innovator2.logic.ICartManager;
import comp3350.Innovator2.objects.Item;
import comp3350.Innovator2.logic.StoreHelper;

public class PageCart extends AppCompatActivity {

    //================================================== Variables

    //UI Elements
    private RecyclerView cartItemsRV;
    private TextView subText;
    private TextView gstText;
    private TextView pstText;
    private TextView delText;
    private TextView totText;

    //Class Links
    private final ICartManager cart = StoreHelper.getCartManager();

    //Multipliers
    private final double GST = 0.05;
    private final double PST = 0.07;
    private final double DELIVERY = 0.1;
    private final double TOT_MULT = 1 + GST + PST + DELIVERY;

    //================================================== Creation

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        //Display the activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_view);

        //UI Elements
        cartItemsRV = findViewById(R.id.cartItems);
        subText = findViewById(R.id.orderSubtotal);
        gstText = findViewById(R.id.GST);
        pstText = findViewById(R.id.PST);
        delText = findViewById(R.id.deliveryFee);
        totText = findViewById(R.id.orderTotal);

        //Load cart items
        refreshAll();
    }

    @Override
    public void onResume ()
    {
        //Refresh
        super.onResume();
        refreshAll();
    }

    //================================================== UI Interaction

    public void onGoToItemBrowserPage(View view) {
        Intent intent = new Intent(PageCart.this, PageBrowser.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

    //Opens a payment ui page, not yet implemented
    public void onProceedToPayment(View view) {
        if (cart.getItems().isEmpty()){
            Toast.makeText(this, "Please add items to your cart before paying!", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(PageCart.this, PagePayment.class);
            startActivity(intent);
        }
    }

    //Clear the contents of the cart
    public void onClearCart(View view) {
        cart.empty();
        refreshAll();
    }

    //================================================== Interface

    /**
     * #### refresh()
     * Refresh display.
     */
    public void refreshText()
    {
        updateText();
    }

    /**
     * #### refresh()
     * Refresh display.
     */
    public void refreshAll()
    {
        updateRV();
        updateText();
    }

    //================================================== Internals

    //Display items in cart.
    private void updateRV()
    {
        //Get item list.
        List<Item> list = cart.getItems();

        //Fill recycler view.
        ItemRVAdapter cartRVA = new ItemRVAdapter(this, list, 1);
        cartRVA.linkCart(this);
        cartItemsRV.setAdapter(cartRVA);
        cartItemsRV.setLayoutManager(new LinearLayoutManager(this));
    }

    //Update display text.
    private void updateText()
    {
        //Create strings.
        String subStr = String.format("Subtotal:\t%.2f", (cart.total()));
        String gstStr = String.format("GST:\t%.2f", (cart.total() * GST));
        String pstStr = String.format("PST:\t%.2f", (cart.total() * PST));
        String delStr = String.format("Delivery Fee:\t%.2f", (cart.total() * DELIVERY));
        String totStr = String.format("Total:\t%.2f", (cart.total() * TOT_MULT));

        //Display tax.
        subText.setText(subStr);
        gstText.setText(gstStr);
        pstText.setText(pstStr);
        delText.setText(delStr);
        totText.setText(totStr);
    }
}