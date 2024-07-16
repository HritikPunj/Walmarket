package comp3350.Innovator2.presentation;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import comp3350.Innovator2.R;
import comp3350.Innovator2.logic.ICartManager;
import comp3350.Innovator2.logic.StoreHelper;
import comp3350.Innovator2.objects.Item;

/**
 * #### ViewItem
 * Handles item view UI.
 */
public abstract class ViewItem extends RecyclerView.ViewHolder {

    //================================================== Variables

    //UI Elements
    public final TextView itemName;
    public final TextView itemCost;
    public final EditText numInCart;
    public final Button addButton;
    public final Button remButton;
    public final ImageButton specialButton;
    public final ImageView itemImage;

    //Class Links
    public final ICartManager cart;

    //Data
    public Item item;
    public int count;

    //================================================== Creation

    public ViewItem(@NonNull View view)
    {
        super(view);

        //UI Elements
        itemName = view.findViewById(R.id.itemName);
        itemCost = view.findViewById(R.id.itemPrice);
        numInCart = view.findViewById(R.id.cartQuantity);
        addButton = view.findViewById(R.id.incrementCartQuantity);
        remButton = view.findViewById(R.id.decrementCartQuantity);
        specialButton = view.findViewById(R.id.specialButton);
        itemImage = view.findViewById(R.id.itemImage);

        //Class Links
        cart = StoreHelper.getCartManager();

        //Setup Listeners
        setupListeners();
    }

    //================================================== UI Interaction

    //Listeners for buttons.
    private void setupListeners() {
        //Listener for addButton.
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAdd();
            }
        });

        //Listener for remButton.
        remButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRemove();
            }
        });

        //Listener for cartButton.
        specialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                onSpecial();
            }
        });
    }

    //================================================== Interface

    //---------------------------------------- Setters

    /**
     * #### setItem(Item)
     * Displays the given item.
     */
    public void setItem(Item item)
    {
        this.item = item;

        //Create Strings
        String costString = "$ " + item.getCost();

        //Display Data
        itemName.setText(item.getTitle());
        itemCost.setText(costString);
        itemImage.setImageBitmap(item.getImage());
    }

    //================================================== Internals

    //Button press handlers.
    public abstract void onAdd();
    public abstract void onRemove();
    public abstract void onSpecial();

    //Update inCart display.
    public abstract void displayInCart();
}
