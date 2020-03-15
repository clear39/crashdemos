package com.demo.crash;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

public class JavaLeakMemoryActivity extends Activity {

    private static final String TAG = "JavaLeakMemoryActivity";

    private final static int sMEMORY_SIZE = 1024*1024*1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLeakClass = new LeakClass();
    }

    private LeakClass mLeakClass;

    private class LeakClass {
        private int[] mIntArray;
        public LeakClass(){
            mIntArray = new int[sMEMORY_SIZE];
            Log.d(TAG + " " + this.getClass().getCanonicalName(), "constructor");
        }


        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            Log.d(TAG + " " + this.getClass().getCanonicalName(), "finalize");
        }
    }


    private class LeakClassThread extends Thread {

        private int[] mIntArray;
        private int mIntRunCount;
        public LeakClassThread(){
            mIntArray = new int[sMEMORY_SIZE];
            Log.d(TAG + " " + this.getClass().getCanonicalName(), "constructor");
            mIntRunCount = 0;
        }


        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            Log.d(TAG + " " + this.getClass().getCanonicalName(), "finalize");
        }

        @Override
        public void run() {
            while (true){
                try {
                    Log.d(TAG + " " + this.getClass().getCanonicalName(), "run mIntRunCount:" + ++mIntRunCount);
                    Thread.sleep(60*60*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
