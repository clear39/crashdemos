package com.demo.leakcanary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class LeakCanaryActivity extends AppCompatActivity {

    private static final String TAG = "LeakCanaryActivity";

    private LeakClassThread mLeakClassThread;
    private final static int sMEMORY_SIZE = 1024*1024*1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leak_canary);

        mLeakClassThread = new LeakClassThread();
        mLeakClassThread.start();
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
