package com.example.videoplayer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<videoHolder> {

private ArrayList<File> videoArrayList;
private Context context;


    public MyAdapter(ArrayList<File> videoArrayList, Context context) {
        this.videoArrayList = videoArrayList;
        this.context = context;
    }

    @Override
    public videoHolder onCreateViewHolder( ViewGroup viewGroup, int i) {

        View mview = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.video_item,viewGroup,false);

        return new videoHolder(mview);
    }

    @Override
    public void onBindViewHolder(@NonNull final videoHolder videoHolder, int i) {

        videoHolder.txtFileName.setText(MainActivity.fileArrayList.get(i).getName());
        Bitmap bitmapThumbnail = ThumbnailUtils.createVideoThumbnail(videoArrayList.get(i).getPath()
                , MediaStore.Images.Thumbnails.MINI_KIND);
        videoHolder.imageThumbnail.setImageBitmap(bitmapThumbnail);

        videoHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context , videoPlayerActivity.class);
                intent.putExtra("Position" , videoHolder.getAdapterPosition());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (videoArrayList.size()>0){
            return videoArrayList.size();
        }
        else return 1;

    }
}

 class videoHolder extends RecyclerView.ViewHolder{

    TextView txtFileName;
    ImageView imageThumbnail;
    CardView cardView;

    videoHolder(View view){
        super(view);

        txtFileName = view.findViewById(R.id.text_view);
        imageThumbnail = view.findViewById(R.id.thumbnail);
        cardView = view.findViewById(R.id.mycardview);
    }

}
