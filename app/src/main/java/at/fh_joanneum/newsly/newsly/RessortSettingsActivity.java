package at.fh_joanneum.newsly.newsly;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class RessortSettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ressort_settings);

        TextView text = (TextView) findViewById(R.id.textHeader);
        Typeface type = Typeface.createFromAsset(getAssets(),"fonts/Bringshoot.ttf");
        text.setTypeface(type);
        text.setTextSize(52);
    }
}
