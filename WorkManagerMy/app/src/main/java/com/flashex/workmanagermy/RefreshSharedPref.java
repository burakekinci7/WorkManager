package com.flashex.workmanagermy;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class RefreshSharedPref extends Worker {
    Context context;

    public RefreshSharedPref(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.context = context;
    }

    @NonNull
    @Override
    public Result doWork() {
        //work manager is worker
        Data data = getInputData();
        int newNumber = data.getInt("intKey", 0);
        refreshDataBase(newNumber);
        return Result.success();
    }

    private void refreshDataBase(int number) {
        //shared preferences and counter
        SharedPreferences sharedPreferences = context.getSharedPreferences("com.flashex.workmanagermy", Context.MODE_PRIVATE);
        int mySaveNumber = sharedPreferences.getInt("number", 0);
        mySaveNumber = mySaveNumber + number;
        System.out.println(mySaveNumber);
        sharedPreferences.edit().putInt("number", mySaveNumber).apply();
    }
}
