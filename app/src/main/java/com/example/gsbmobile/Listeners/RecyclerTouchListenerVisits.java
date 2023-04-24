package com.example.gsbmobile.Listeners;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gsbmobile.Interface.RecyclerViewClickListenerVisits;
import com.example.gsbmobile.Models.GetUsers;

import retrofit2.Callback;

public class RecyclerTouchListenerVisits {
    private GestureDetector gestureDetector;
    private RecyclerViewClickListenerVisits clickListener;

    public RecyclerTouchListenerVisits(Context context, final RecyclerView recyclerView, RecyclerViewClickListenerVisits clickListener) {
        this.gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e){
                return true;
            }
        });
        this.clickListener = (RecyclerViewClickListenerVisits) clickListener;
    }

    public RecyclerTouchListenerVisits(Callback<GetUsers> getUsersCallback, RecyclerView listVisits, RecyclerViewClickListenerVisits RecyclerViewClickListenerVisits) {
    }

}
