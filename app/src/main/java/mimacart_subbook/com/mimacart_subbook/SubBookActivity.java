package mimacart_subbook.com.mimacart_subbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class SubBookActivity extends AppCompatActivity {
    private ListView subscriptionListView;
    private ArrayList<Subscription> subscriptionList;
    private Button addSubscriptionButton;
    private ArrayAdapter<Subscription> adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addSubscriptionButton = (Button) findViewById(R.id.addSubscription);
        subscriptionListView = (ListView) findViewById(R.id.subscriptionListView);

        addSubscriptionButton.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {
                setResult(RESULT_OK);
                Intent intent = new Intent(v.getContext(), CreateSubscriptionActivity.class);
                startActivity(intent);

                //adapter.notifyDataSetChanged();
            }
        });

        subscriptionListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                setResult(RESULT_OK);
                Intent intent = new Intent(view.getContext(), SubscriptionDetailsActivity.class);
                startActivity(intent);
            }

        });
    }

    @Override
    protected void onStart(){
        super.onStart();

        //adapter = new ArrayAdapter<Subscription>(this,
        //        R.layout.list_item, subscriptionList);
        //subscriptionListView.setAdapter(adapter);
    }
}
