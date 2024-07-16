package comp3350.Innovator2.presentation;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import comp3350.Innovator2.R;
import comp3350.Innovator2.logic.exceptions.UIException;
import comp3350.Innovator2.objects.Item;

public class ViewItemCart extends ViewItem {

    //================================================== Variables

    //UI Elements
    private final TextView itemTotal;

    //Class Links
    private final PageCart cartUI;

    //================================================== Creation

    public ViewItemCart(@NonNull View view, PageCart cartUI)
    {
        super(view);

        //UI Elements
        itemTotal = view.findViewById(R.id.itemTotal);

        //Class Links
        this.cartUI = cartUI;
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
        try {
            cart.addItems(1, item);
        }
        catch (UIException e) {
            Toast.makeText(addButton.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        displayInCart();
    }

    @Override
    public void onRemove()
    {
        cart.remove(item);
        displayInCart();
    }

    //Remove from cart.
    @Override
    public void onSpecial()
    {
        while (cart.count(item) > 0)
            cart.remove(item);

        if (cartUI != null) cartUI.refreshAll();
        else displayInCart();
    }

    //Update inCart display.
    public void displayInCart()
    {
        String totalString = String.format("Total Cost:\t%.2f", (item.getCost() * cart.count(item)));
        String inCartString = Integer.toString(cart.count(item));

        itemTotal.setText(totalString);
        numInCart.setText(inCartString);

        //Refresh UI if linked.
        if (cartUI != null) cartUI.refreshText();
    }
}
