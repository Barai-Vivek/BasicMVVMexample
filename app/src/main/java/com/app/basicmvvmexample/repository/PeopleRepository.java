package com.app.basicmvvmexample.repository;

import androidx.lifecycle.MutableLiveData;

import com.app.basicmvvmexample.model.People;

import java.util.ArrayList;
import java.util.List;

public class PeopleRepository {
    private static PeopleRepository peopleRepository;
    private ArrayList<People> dataSet = new ArrayList<>();

    public static PeopleRepository getInstance() {
        if (peopleRepository == null) {
            peopleRepository = new PeopleRepository();
        }
        return peopleRepository;
    }

    public void setPeopleListData() {
        dataSet.add(new People("https://picsum.photos/id/", "Vivek"));
        dataSet.add(new People("https://picsum.photos/id/", "Bhavin"));
        dataSet.add(new People("https://picsum.photos/id/", "Raviraj"));
        dataSet.add(new People("https://picsum.photos/id/", "Mehul"));
        dataSet.add(new People("https://picsum.photos/id/", "Lakhan"));
    }

    //assume if you have call through api
    public MutableLiveData<List<People>> getPeopleListData() {
        setPeopleListData();
        MutableLiveData<List<People>> peopleMutableLiveData = new MutableLiveData<>();
        peopleMutableLiveData.setValue(dataSet);
        System.out.println("People list size11 = " + dataSet.size());
        return peopleMutableLiveData;
    }
}
