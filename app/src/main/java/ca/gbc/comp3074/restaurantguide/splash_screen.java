package ca.gbc.comp3074.restaurantguide;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class splash_screen extends AppCompatActivity {

    // Splash screen animation variables
    Animation topAnim, bottomAnim;
    ImageView image;
    ProgressBar progress;

    private static int SPLASH_SCREEN = 6000; // 6sec to MainActivity
    private ProgressBar progressBar;
    private TextView loading;
    private int status = 0;
    private Handler handler = new Handler();

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // hide status and action bar
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        // Animations
        topAnim = AnimationUtils.loadAnimation(this, R.animator.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.animator.bottom_animation);

        image = findViewById(R.id.imageView);
        progress = findViewById(R.id.progressbar);

        image.setAnimation(topAnim);
        progress.setAnimation(bottomAnim);

        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        loading = (TextView) findViewById(R.id.LoadingComplete);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (status < 100) {
                    status ++;
                    android.os.SystemClock.sleep(50); // delays loading of the application
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(status); //updates progress bar
                        }
                    });
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        loading.setVisibility(View.VISIBLE);
                    }
                });
            }
        }).start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(splash_screen.this, MainActivity.class);
                startActivity(intent);
                finish(); // to avoid going back to splash screen
            }
        }, SPLASH_SCREEN);
    }
}