package com.example.thenamequizapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class AddEntry extends AppCompatActivity {

    private static final int REQUEST_CODE_PERMISSION = 1;
    private static final int REQUEST_CODE_SELECT_IMAGE = 2;
    private Button picture;
    private Button gallery;
    private ImageView imageView;
    private TextView textView;
    private EditText editText;
    private Animal animalObj = new Animal();
    private AnimalAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addentry);

        imageView = (ImageView) findViewById(R.id.image);
        textView = (TextView) findViewById(R.id.name);
        picture = findViewById(R.id.pic);
        gallery = findViewById(R.id.gallery);
        editText = findViewById(R.id.addName);


        picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ContextCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(AddEntry.this,
                            new String[]{
                                    Manifest.permission.CAMERA,
                            }, REQUEST_CODE_PERMISSION);
                }else {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, REQUEST_CODE_SELECT_IMAGE);

                }
            }
        });

        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ContextCompat.checkSelfPermission(
                        getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(AddEntry.this,
                            new String[]{
                                    Manifest.permission.READ_EXTERNAL_STORAGE,
                            },
                            REQUEST_CODE_PERMISSION);
                } else {
                    selectImage();

                }
            }
        });
    }

    private void selectImage(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(intent, REQUEST_CODE_SELECT_IMAGE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == REQUEST_CODE_PERMISSION && grantResults.length > 0){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                selectImage();
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SELECT_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            if (selectedImageUri != null){
                try{
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    InputStream inputStream = getContentResolver().openInputStream(selectedImageUri);
                    Bitmap bitmapImage = BitmapFactory.decodeStream(inputStream);
                    bitmapImage.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                    byte[] image = stream.toByteArray();
                    String name = editText.getText().toString();
                    int id = animalObj.createId();
                    //Animal animal = new Animal(id,name,image);
                   //animalObj.addAnimal(animal);

                    //insert(animal);

                    startActivity(new Intent(getApplicationContext(), DatabaseActivity.class));
                    adapter.notifyDataSetChanged();

                } catch (Exception exception){
                    //Toast.makeText(this, exception.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }else{

                try {
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    Bitmap bitmapImage = (Bitmap) data.getExtras().get("data");
                    bitmapImage.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                    byte[] image = stream.toByteArray();
                    String name = editText.getText().toString();
                    int id = animalObj.createId();
                    //Animal animal = new Animal(id,name, image);
                   // animalObj.addAnimal(animal);
                    startActivity(new Intent(getApplicationContext(), DatabaseActivity.class));
                    adapter.notifyDataSetChanged();
                } catch (Exception exception){
                    //Toast.makeText(this,exception.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

        }
    }

    public void openDatabase() {
        Intent intent = new Intent(this, DatabaseActivity.class);
        startActivity(intent);
    }


    public void insert(Animal animal){

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
               // AppDatabase.getInstance(getApplicationContext().AnimalDao().insert(animal);
            }
        });

        thread.start();

    }

}