package com.example.android_development_assignment_2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button button_reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Player(false, 8, 3);
        new Player(true, 8, 3);

        button_reset = findViewById(R.id.button_reset);


        button_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                simpleAlertDialog();
                CustomView cv = findViewById(R.id.custom_id);
                ClickEvent.lastEvent = ClickEvent.Type.Event_Nothing;
                cv.reset();

            }
        });
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;

        CustomView cv = (CustomView) findViewById(R.id.custom_id);
        ViewGroup.LayoutParams layoutParams = cv.getLayoutParams();
        layoutParams.width=width;
        (layoutParams.height)=width;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);


        return super.onCreateOptionsMenu(menu);
    }


    private void simpleAlertDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Reset Game!");
        builder.setMessage("Are you sure you want to reset this game!");
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Resetting.......",
                        Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("no", new DialogInterface.OnClickListener() { @Override
        public void onClick(DialogInterface dialog, int which) {
            Toast.makeText(MainActivity.this, "Canceled", Toast.LENGTH_SHORT).show();
        } });
        AlertDialog dialog = builder.create();
        dialog.show();


    }


}
