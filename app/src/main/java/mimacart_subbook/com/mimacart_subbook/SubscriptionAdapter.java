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

class SubscriptionAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Subscription> subscriptions;

    public SubscriptionAdapter(Context context, ArrayList<Subscription> subscriptions) {
        this.context = context;
        this.subscriptions = subscriptions;
    }

    @Override
    public int getCount() {
        return subscriptions.size();
    }

    @Override
    public Subscription getItem(int position) {
        return subscriptions.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

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
        TextView summary=(TextView)convertView.findViewById(R.id.summary);

        name.setText(subscriptions.get(position).getName());
        summary.setText("$"+monthlyCharge+"\t"+subscriptionStarted);

        return convertView;
    }
}