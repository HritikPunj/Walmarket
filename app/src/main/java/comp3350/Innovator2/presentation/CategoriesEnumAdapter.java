package comp3350.Innovator2.presentation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import comp3350.Innovator2.R;
import comp3350.Innovator2.objects.utils.Category;

public class CategoriesEnumAdapter extends ArrayAdapter<Category> {
    private final Category[] cats;
    private final LayoutInflater inflater;

    public CategoriesEnumAdapter(Context context, int resource, Category[] cats){
        super(context, resource, cats);
        this.cats = cats;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent){
        return getCustomDropDownView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent){
        return getCustomDropDownView(position, convertView, parent);
    }

    private View getCustomDropDownView(int position, View convertView, ViewGroup parent){
        View view = convertView;
        if (view == null){
            view = inflater.inflate(R.layout.dropdown_textstyle, parent, false);
        }
        TextView textView = view.findViewById(android.R.id.text1);
        textView.setText(cats[position].toString());
        return view;
    }
}
