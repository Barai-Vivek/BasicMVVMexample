package com.app.basicmvvmexample.viewmodel;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app.basicmvvmexample.model.People;
import com.app.basicmvvmexample.repository.PeopleRepository;

import java.util.List;

public class MainActivityViewModel extends ViewModel {
    private MutableLiveData<List<People>> mPeopleList;
    private MutableLiveData<Boolean> isUpdating = new MutableLiveData<>();
    private PeopleRepository peopleRepository;

    public void init() {
        if(mPeopleList != null){
            return;
        }
        peopleRepository = PeopleRepository.getInstance();
        //you are retrieving whole rom Api/database
        mPeopleList = peopleRepository.getPeopleListData();
    }


    //This is the operation thing
    public void addPeople(final People people){
        isUpdating.setValue(true);
        new AsyncTask() {
            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                List<People> peopleList = mPeopleList.getValue();
                peopleList.add(0, people);
                mPeopleList.postValue(peopleList);
                isUpdating.setValue(false);
            }

            @Override
            protected Object doInBackground(Object[] objects) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
    }

    public LiveData<List<People>> getPeopleList() {
        return mPeopleList;
    }
    public LiveData<Boolean> getIsUpdating() {
        return isUpdating;
    }
}
