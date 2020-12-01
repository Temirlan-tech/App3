package com.example.app3;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    private ImageView myImage;
    private EditText editText;
    private static int REQUEST_QODE = 100;
    public static final String EXTRA_TEXT1 = "text2";
    private static final int IMAGE_PICK_CODE = 1000;
    public Uri textImage;
    public String imageText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        init();
    }

    private void init() {
        editText = findViewById(R.id.etText);

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
        String messageText = editText.getText().toString();
        Intent intent = new Intent();
        intent.putExtra(EXTRA_TEXT1, messageText);
        Log.d("TAG", "openFirstActivity: "+messageText);
        intent.putExtra("text", imageText);
        setResult(RESULT_OK,intent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        if (requestCode == IMAGE_PICK_CODE && resultCode == RESULT_OK && imageReturnedIntent != null) {
            Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
                final Uri imageUri = imageReturnedIntent.getData();
                imageText = imageUri.toString();
                myImage.setImageURI(imageUri);
        }
    }
}


