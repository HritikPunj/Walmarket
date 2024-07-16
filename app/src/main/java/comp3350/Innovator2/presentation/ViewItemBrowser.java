package comp3350.Innovator2.presentation;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import comp3350.Innovator2.R;
import comp3350.Innovator2.logic.exceptions.UIException;
import comp3350.Innovator2.objects.Item;

public class ViewItemBrowser extends ViewItem {

    //================================================== Variables

    //UI Elements
    private final TextView itemStock;

    //================================================== Creation

    public ViewItemBrowser(@NonNull View view)
    {
        super(view);

        //UI Elements
        itemStock = view.findViewById(R.id.itemStock);
    }

    //================================================== Interface

    public void setItem(Item item)
    {
        super.setItem(item);
        displayInCart();
    }

    //================================================== Internals

    @Override
    public void onAdd()
    {
        if (count < (item.getStock() - cart.count(item)))
            count++;
        displayInCart();
    }

    @Override
    public void onRemove()
    {
        if (count > 0)
            count--;
        displayInCart();
    }

    //Add to cart.
    @Override
    public void onSpecial()
    {
        for (int i = 0; i < count; i++) {
            try {
                cart.addItems(1, item);
            }
            catch (UIException e) {
                Toast.makeText(specialButton.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        count = 0;
        displayInCart();
    }

    //Update inCart display.
    public void displayInCart()
    {
        String stockString;
        if (item.getStock() < 1) stockString = "Out of stock";
        else stockString = "In Stock: " + (item.getStock() - cart.count(item));

        String inCartString = Integer.toString(count);

        itemStock.setText(stockString);
        numInCart.setText(inCartString);
    }
}
