package com.example.thenamequizapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.nio.file.attribute.FileTime;
import java.util.ArrayList;

public class AnimalAdapter extends ArrayAdapter<Animal> {

    private Context mContext;
    private int mResource;
    private Animal animalObj = new Animal();
    private AppDatabase db;


    public AnimalAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Animal> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        convertView = layoutInflater.inflate(mResource, parent, false);
        db = AppDatabase.getDatabase(mContext);

        ImageView imageView = convertView.findViewById(R.id.image);
        TextView textView = convertView.findViewById(R.id.name);

        byte[] bytes = getItem(position).getBytes();
        Bitmap image = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        imageView.setImageBitmap(image);
        textView.setText(getItem(position).getName());


        Button delete = (Button) convertView.findViewById(R.id.delete);
        delete.setTag(position);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout linearLayout = (LinearLayout) view.getParent();
                TextView textView = (TextView) linearLayout.findViewById(R.id.name);
                String name = textView.getText().toString();

                deleteAnimal(name);

            }
        });

        return convertView;
    }

    private void deleteAnimal(String name) {

        Animal animal  = null;

        for(int i = 0; i < animalObj.getAnimals().size(); i++){
            if(animalObj.getAnimals().get(i).getName() == name){
                animal = animalObj.getAnimals().get(i);
            }
        }

        db.AnimalDao().delete(animal);
        animalObj.removeAnimal(animal);
        notifyDataSetChanged();

    }
}
