package com.darkline;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.darkline.model.Posts;
import java.util.List;


public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.myviewHolder>{
    private List<Posts> dataholder;
    private Context context;

    private String imageUrl = "http://192.168.111.153:5000/api/image/";

    public HomeAdapter(List<Posts> dataholder,Context context) {
        this.dataholder = dataholder;
        this.context = context;
    }

    @NonNull
    @Override
    public myviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_recyclerview_item, parent, false);
        return new myviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewHolder holder, int position) {
        Posts posts = dataholder.get(position);
        Glide.with(context).load(imageUrl+posts.getImageName()).into(holder.imageView);
        holder.userName.setText(posts.getUser().getName());
        holder.category.setText(posts.getCategory().getCategoryTitle());
        holder.title.setText(posts.getTitle());
        holder.content.setText(posts.getContent());
//        holder.content.setText(dataholder.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return dataholder.size();
    }

    public class myviewHolder extends RecyclerView.ViewHolder{
        TextView userName, category, title, content;
        ImageView imageView;


        public myviewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.postImage);
            imageView.setVisibility(View.VISIBLE);
            userName = itemView.findViewById(R.id.postUser);
            category = itemView.findViewById(R.id.postCategory);
            title = itemView.findViewById(R.id.postTitle);
            content = itemView.findViewById(R.id.postContent);
        }
    }
}
