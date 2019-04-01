package appstacks.net.flagkit;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import appstacks.net.flagview.FlagImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FlagImageView imageView = findViewById(R.id.flag_view);
        imageView.setCountryCode("VN");
    }
}
