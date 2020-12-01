package com.example.app3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
        textView = (TextView) findViewById(R.id.txtView);
        imageView = (ImageView) findViewById(R.id.image);
    }

    public void openSecondActivity(View view) {
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        startActivityForResult(intent, 22);
    }

    public void sendTextGmail(View view) {
        Intent email = new Intent(Intent.ACTION_VIEW);
        email.setType("message/rfc822")
                .setData(Uri.parse("mailto:your.email@gmail.com"))
                .putExtra(Intent.EXTRA_EMAIL, "your.email@gmail.com")
                .putExtra(Intent.EXTRA_SUBJECT, "тема")
                .putExtra(Intent.EXTRA_TEXT, textView.getText().toString())
                .setPackage("com.google.android.gm");
        startActivity(email);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 22 && resultCode == RESULT_OK && data != null) {
            Uri imageUri = Uri.parse(data.getStringExtra("text"));
            imageView.setImageURI(imageUri);
            String text = data.getStringExtra(MainActivity2.EXTRA_TEXT1);
            textView.setText(text);
            Log.d("TAG", "onActivityResult: " + text);
            Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(this, "failer", Toast.LENGTH_SHORT).show();

    }
}
