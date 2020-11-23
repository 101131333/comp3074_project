package ca.gbc.comp3074.restaurantguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.Objects;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        // set action bar
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);


        // This is only for test, could be deleted later
        Button btnOpenTimHortons = (Button) findViewById(R.id.btn_test);
        btnOpenTimHortons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTimHortons();
            }
        });


    }

    // display back arrow on action bar to navigate back to activity_main
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        return true;
    }


    //opens Tim Hortons, this only for test,could be deleted later
    private void openTimHortons(){
        Intent start = new Intent(getApplicationContext(), TimHortons.class);
        startActivity(start);
    }


}