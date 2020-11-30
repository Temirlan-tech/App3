package com.example.app3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result();
    }

    public void result() {
        if (getIntent() != null) {
            String text = getIntent().getStringExtra(MainActivity2.EXTRA_TEXT1);
            textView = (TextView) findViewById(R.id.txtView);
            textView.setText(text);
            imageView = (ImageView) findViewById(R.id.image);
            imageView.setImageURI(getIntent().getData());
        }
    }

    public void openSecondActivity(View view) {
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        startActivity(intent);
    }

    public void sendTextGmail(View view) {
        Intent email= new Intent(Intent.ACTION_VIEW);
        email.setType("message/rfc822")
                .setData(Uri.parse("mailto:your.email@gmail.com"))
                .putExtra(Intent.EXTRA_EMAIL, "your.email@gmail.com")
                .putExtra(Intent.EXTRA_SUBJECT, "Тема")
                .putExtra(Intent.EXTRA_TEXT, textView.getText().toString())
                .setPackage("com.google.android.gm");
        startActivity(email);
    }
}