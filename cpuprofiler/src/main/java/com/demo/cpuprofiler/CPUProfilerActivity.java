package com.demo.cpuprofiler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class CPUProfilerActivity extends AppCompatActivity {

    private int[] mIntArrays ;

    private static final int sMEMORY_SIZE = 1024*1024*1024*10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cpuprofiler);
    }


    public void testclick(View v){

        //testSleeps();

        //testInts(sMEMORY_SIZE);

        testInts(1024);

        //testSleeps();


    }


    private void testSleeps(){
        try {
            Thread.sleep(100*6);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    private void testInts(int num){
        mIntArrays = new int[num];
        for (int i = 0;i < num;i++){
            mIntArrays[i] = i;
        }
    }

}
