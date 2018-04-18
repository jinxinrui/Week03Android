package edu.monash.fit3027ffit4039.week03prac;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import edu.monash.fit3027ffit4039.week03prac.models.Person;

/**
 * Created by jxr on 14/3/18.
 */

public class PersonAdapter extends BaseAdapter {
    private Context mCurrentContext;
    private ArrayList<Person> mPersonList;

    public PersonAdapter(Context con, ArrayList<Person> people) {
        mCurrentContext = con;
        mPersonList = people;
    }

    public int getCount() {
        return mPersonList.size();
    }

    public Object getItem(int i) {
        return mPersonList.get(i);
    }

    public long getItemId(int i) {
        return i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        // check if view already exists. if not inflate it
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mCurrentContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // create a list item based off layout definition
            view = inflater.inflate(R.layout.list_person_item, null);
        }

        // Assign values to the TextViews using Person Object
        TextView nameView = (TextView) view.findViewById(R.id.nameTextView);
        TextView continentView = (TextView) view.findViewById(R.id.continentTextView);
        ImageView imageView = (ImageView) view.findViewById(R.id.continentImage);
        nameView.setText(mPersonList.get(i).getName());
        continentView.setText(mPersonList.get(i).getCountry());

        // change colour and image depending on country/ continent
        String continent = mPersonList.get(i).getCountry();
        if (continent.equals("Australia")) {
            continentView.setTextColor(Color.parseColor("#bd00ff")); // Purple
            imageView.setImageResource(R.mipmap.australia); }
        else if (continent.equals("Africa")) {
            continentView.setTextColor(Color.parseColor("#000000")); // Black
            imageView.setImageResource(R.mipmap.africa); }
        else if (continent.equals("Asia")) {
            continentView.setTextColor(Color.parseColor("#c7c7c7")); // Grey
            imageView.setImageResource(R.mipmap.asia); }
        else if (continent.equals("Europe")) {
            continentView.setTextColor(Color.parseColor("#000000")); // Green
            imageView.setImageResource(R.mipmap.europe); }
        else if (continent.equals("South America")) {
            continentView.setTextColor(Color.parseColor("#1f1377")); // Deep Purple
            imageView.setImageResource(R.mipmap.southamerica); }
        else if (continent.equals("North America")) {
            continentView.setTextColor(Color.parseColor("#f2a409")); // Orange
            imageView.setImageResource(R.mipmap.northamerica); }
        return view;
    }

}
