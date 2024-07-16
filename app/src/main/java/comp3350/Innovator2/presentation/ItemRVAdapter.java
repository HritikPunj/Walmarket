package comp3350.Innovator2.presentation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import comp3350.Innovator2.R;
import comp3350.Innovator2.objects.Item;

/**
 * #### ItemRVAdapter
 * Displays a list of items in the browser UI.
 */
public class ItemRVAdapter extends RecyclerView.Adapter<ViewItem>
{
    //================================================== Variables

    //General
    private Context context;
    private List<Item> itemList;
    private int itemViewType;

    //PageCart exclusive
    private PageCart cartUI;

    //================================================== Creation

    public ItemRVAdapter(Context context, List<Item> itemList, int itemViewType)
    {
        this.itemViewType = itemViewType;
        this.context = context;
        this.itemList = itemList;
    }

    public void linkCart(PageCart cartUI)
    {
        this.cartUI = cartUI;
    }

    //================================================== Interface

    /**
     * #### public int getItemCount()
     * Returns the number of displayed items.
     */
    @Override
    public int getItemCount()
    {
        return itemList.size();
    }

    //================================================== Internals

    //Creates new holder.
    @NonNull
    @Override
    public ViewItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(context);

        if (itemViewType == 0) { //PageBrowser
            View view = inflater.inflate(R.layout.item_view, parent, false);
            return new ViewItemBrowser(view);
        } else { //PageCart
            View view = inflater.inflate(R.layout.cart_item_view, parent, false);
            return new ViewItemCart(view, cartUI);
        }
    }

    //Fills holder with data.
    @Override
    public void onBindViewHolder(@NonNull ViewItem holder, int position)
    {
        holder.setItem(itemList.get(position));
    }
}
