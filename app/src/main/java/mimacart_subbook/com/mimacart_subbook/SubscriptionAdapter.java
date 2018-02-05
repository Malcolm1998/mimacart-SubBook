/*
 * SubscriptionAdapter
 *
 * February 4, 2018
 *
 * Copyright © 2018 Team X, CMPUT301, University of Alberta - All rights Reserved.
 * You may use, distribute, or modify this code under terms and conditions of the Code
 * of Student Behaviour at University of Alberta.
 * You can find a copy of the license in this project. Otherwise contact mimacart@ualberta.ca
 */

package mimacart_subbook.com.mimacart_subbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Used for creating row items in ListView
 *
 * @author Malcolm MacArthur
 * @see SubBookActivity
 */
class SubscriptionAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Subscription> subscriptions;

    /**
     * Public constructor for SubscriptionAdapter
     *
     * @param context the activity context
     * @param subscriptions the list
     */
    public SubscriptionAdapter(Context context, ArrayList<Subscription> subscriptions) {
        this.context = context;
        this.subscriptions = subscriptions;
    }

    /**
     * Gets the count
     *
     * @return int length of list
     */
    @Override
    public int getCount() {
        return subscriptions.size();
    }

    /**
     * Gets the Subscription object given by position
     *
     * @param position a place to find a possible Subscription object
     * @return Subscription object found at position
     */
    @Override
    public Subscription getItem(int position) {
        return subscriptions.get(position);
    }

    /**
     * Gets the id given with position
     *
     * @param position a place to find a possible Id
     * @return Id of item found at position
     */
    @Override
    public long getItemId(int position) {
        return 0;
    }

    /**
     * get item view given position
     *
     * @param position a place to find a possible View
     * @param convertView the view group
     * @param parent a view
     * @return View
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String subscriptionName = subscriptions.get(position).getName();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String subscriptionStarted = format.format(subscriptions.get(position).getDateStarted());
        String monthlyCharge = String.valueOf(subscriptions.get(position).getMonthlyCharge());

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item, null);
        }

        TextView name = (TextView)convertView.findViewById(R.id.title);
        TextView summary = (TextView)convertView.findViewById(R.id.summary);

        name.setText(subscriptions.get(position).getName());
        summary.setText("$"+monthlyCharge+"\t"+subscriptionStarted);

        return convertView;
    }
}