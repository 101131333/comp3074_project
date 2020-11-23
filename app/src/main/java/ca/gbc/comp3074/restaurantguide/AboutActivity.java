package ca.gbc.comp3074.restaurantguide;

<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 82d776d... Add Back Arrow in AboutActivity
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import java.util.Objects;
<<<<<<< HEAD
=======
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
>>>>>>> f681f75... add about activity, customize action bar
=======
>>>>>>> 82d776d... Add Back Arrow in AboutActivity

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 82d776d... Add Back Arrow in AboutActivity

        // set action bar
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

    }

    // display back arrow on action bar to navigate back to activity_main
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        return true;
<<<<<<< HEAD
=======
>>>>>>> f681f75... add about activity, customize action bar
=======
>>>>>>> 82d776d... Add Back Arrow in AboutActivity
    }
}