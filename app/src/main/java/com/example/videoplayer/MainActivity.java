package com.example.videoplayer;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Adapter;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    public static int REQUEST_PERMISSION = 1;
    File directory;
    boolean bolean_permission;
    private Context context;
    public static ArrayList<File> fileArrayList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);

        //Phone memory access
        directory = new File("/mnt/");

        //For SD Card access
       // directory = new File("/storage/");

        GridLayoutManager manager = new GridLayoutManager(MainActivity.this, 2);
        recyclerView.setLayoutManager(manager);
        
        permissionForVideo();
    }

    private void permissionForVideo() {

        if ((ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)){

    if ((ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
            Manifest.permission.READ_EXTERNAL_STORAGE))){

        }

        else{
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_PERMISSION);
            }
    }

    else{
          bolean_permission = true;
          getFile(directory);
         // MyAdapter obj_adapter = new MyAdapter(MainActivity.this,fileArrayList);
         // recyclerView.setAdapter(obj_adapter);
        }
}

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_PERMISSION){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                bolean_permission = true;
                getFile(directory);
                //obj_adapter =  new MyAdapter(getApplicationContext() , fileArrayList);
               // recyclerView.setAdapter(obj_adapter);

            }
            else {
                Toast.makeText(this, "Please Allow the permission", Toast.LENGTH_SHORT ).show();
            }

        }
    }

   public ArrayList<File> getFile(File directory) {

       File listFile[] = directory.listFiles();
       if (listFile != null && listFile.length > 0) {

           for (int i = 0; i < listFile.length; i++) {
               if (listFile[i].isDirectory()) {
                   getFile(listFile[i]);

               } else {
                   bolean_permission = false;
                   if (listFile[i].getName().endsWith(".mp4")) {

                       for (int j = 0; j < fileArrayList.size(); i++) {
                           if (fileArrayList.get(j).getName().equals(listFile[i].getName())) {
                               bolean_permission = true;

                           } else {

                           }
                       }
                       if (bolean_permission){
                           bolean_permission = false;
                       }
                            else {
                               fileArrayList.add(listFile[i]);
                           }
                       }
                   }

               }
           }
           return fileArrayList;

       }
   }




