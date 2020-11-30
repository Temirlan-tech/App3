package com.example.app3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity2 extends AppCompatActivity {

    private ImageView myImage;
    private EditText editText;
    private static int REQUEST_QODE = 100;
    public static final String EXTRA_TEXT1 = "text";
    public static final String EXTRA_IMAGE = "image";
    private static final int IMAGE_PICK_CODE = 1000;
    public Uri textImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        init();
    }

    private void init() {
        myImage = findViewById(R.id.image2);
        myImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentImage = new Intent(Intent.ACTION_PICK);
                intentImage.setType("image/*");
                startActivityForResult(intentImage, IMAGE_PICK_CODE);
            }
        });
    }

    public void openFirstActivity(View view) {
        editText = findViewById(R.id.etText);
        String messageText = editText.getText().toString();
        Intent intent = new Intent(MainActivity2.this, MainActivity.class);
        intent.putExtra(EXTRA_TEXT1, messageText);
        intent.setData(textImage);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        if (requestCode == IMAGE_PICK_CODE && resultCode == RESULT_OK && imageReturnedIntent != null) {
            Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
                final Uri imageUri = imageReturnedIntent.getData();
                textImage = imageUri;
                myImage.setImageURI(imageUri);
        }
    }
}


