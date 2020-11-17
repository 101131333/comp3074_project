package ca.gbc.comp3074.restaurantguide;

<<<<<<< HEAD
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import java.util.Objects;
=======
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
>>>>>>> f681f75... add about activity, customize action bar

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
<<<<<<< HEAD

        // set action bar
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

    }

    // display back arrow on action bar to navigate back to activity_main
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        return true;
=======
>>>>>>> f681f75... add about activity, customize action bar
    }
}