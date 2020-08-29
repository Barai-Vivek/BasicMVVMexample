package com.app.basicmvvmexample;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.basicmvvmexample.model.People;
import com.bumptech.glide.Glide;

import java.io.SyncFailedException;
import java.util.ArrayList;
import java.util.List;

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.MyViewHolder> {
    private Activity activity;
    private Context context;
    private List<People> peopleList;

    public PeopleAdapter(Activity activity, Context context, List<People> peopleList) {
        this.activity = activity;
        this.context = context;
        this.peopleList = peopleList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewGroup listItem = (ViewGroup) layoutInflater.inflate(R.layout.people_adapter_item, parent, false);
        return new MyViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        System.out.println("people list position = " + position);
        if (peopleList.get(position) != null) {
            People people = peopleList.get(position);
            String dummyImg = people.getImage_url() + position + "/120/120";
            Glide.with(context)
                    .asBitmap()
                    .load(dummyImg)
                    .into(holder.ivPeople);

            holder.tvPeopleName.setText(people.getName());
        }
    }

    @Override
    public int getItemCount() {
        return peopleList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private final ImageView ivPeople;
        private final TextView tvPeopleName;

        public MyViewHolder(View view) {
            super(view);
            ivPeople = view.findViewById(R.id.ivPeople);
            tvPeopleName = view.findViewById(R.id.tvPeopleName);
        }
    }
}
