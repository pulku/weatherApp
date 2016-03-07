package pulku.stormy.ui;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Parcelable;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

import pulku.stormy.R;
import pulku.stormy.adapters.DayAdapter;
import pulku.stormy.weather.Day;


public class DailyForecastActivity extends ListActivity {

    private Day[] mDays;

    private TextView mLocationLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_forecast);




        Intent intent = getIntent();

        Parcelable[] parcelables = intent.getParcelableArrayExtra(MainActivity.DAILY_FORECAST);
        mDays = Arrays.copyOf(parcelables, parcelables.length, Day[].class);

        mLocationLabel = (TextView) findViewById(R.id.locationLabel2);
        mLocationLabel.setText(mDays[0].getTimeZone());

        DayAdapter adapter = new DayAdapter(this, mDays);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        String dayOfTheWeek =mDays[position].getDayOfTheWeek();
        String conditions = mDays[position].getSummary();
        String highTemp = mDays[position].getTemperatureMax() + "";
        String message = String.format("On %s the high temperature will be %s and it will be %s",
                dayOfTheWeek, highTemp, conditions);
        Toast.makeText(DailyForecastActivity.this, message, Toast.LENGTH_LONG).show();
    }
}
