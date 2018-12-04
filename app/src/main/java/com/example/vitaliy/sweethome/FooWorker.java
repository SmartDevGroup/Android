package com.example.vitaliy.sweethome;

import android.support.annotation.NonNull;

import javax.xml.transform.Result;

import androidx.work.Worker;

class FooWorker extends Worker {
    String s;

    public FooWorker(String s) {
        this.s = s;
    }

    @NonNull
    @Override
    public Result doWork() {
        return Result.SUCCESS;
    }
}