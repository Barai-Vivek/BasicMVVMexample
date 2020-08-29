package com.app.basicmvvmexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.icu.lang.UScript;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.app.basicmvvmexample.model.People;
import com.app.basicmvvmexample.viewmodel.MainActivityViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView peopleRecyclerView;
    private ProgressBar progressBar;
    private FloatingActionButton floatingActionButton;
    private PeopleAdapter peopleAdapter;
    private MainActivityViewModel mainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        peopleRecyclerView = findViewById(R.id.peopleRecyclerView);
        progressBar = findViewById(R.id.progressBar);
        floatingActionButton = findViewById(R.id.floatingActionButton);
        progressBar.setVisibility(View.VISIBLE);
        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        mainActivityViewModel.init();

        //initiating obsever to and will notify adapter on change of people list
        mainActivityViewModel.getPeopleList().observe(this, new Observer<List<People>>() {
            @Override
            public void onChanged(List<People> people) {
                System.out.println("People list size = " + people.size());
                peopleAdapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
            }
        });

        //will be seen when data is added from floating button click
        mainActivityViewModel.getIsUpdating().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    progressBar.setVisibility(View.VISIBLE);
                } else {
                    progressBar.setVisibility(View.GONE);
                }
            }
        });

        //adding a value in list
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivityViewModel.addPeople(new People("https://picsum.photos/id/", "Dummy User"));
                peopleRecyclerView.smoothScrollToPosition(0);
            }
        });

        initRecyclerView();
    }

    public void initRecyclerView() {
        peopleAdapter = new PeopleAdapter(MainActivity.this, MainActivity.this, mainActivityViewModel.getPeopleList().getValue());
        peopleRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        peopleRecyclerView.setAdapter(peopleAdapter);
    }
}