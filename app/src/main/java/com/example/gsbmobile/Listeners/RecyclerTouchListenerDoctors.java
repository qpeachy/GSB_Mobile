package com.example.gsbmobile.Listeners;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gsbmobile.Interface.RecyclerViewClickListenerDoctors;
import com.example.gsbmobile.Models.GetUsers;

import retrofit2.Callback;

public class RecyclerTouchListenerDoctors implements RecyclerView.OnItemTouchListener {
    private GestureDetector gestureDetector;
    private RecyclerViewClickListenerDoctors clickListener;

    public RecyclerTouchListenerDoctors(Context context, final RecyclerView recyclerView, RecyclerViewClickListenerDoctors clickListener) {
        this.gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e){
                return true;
            }
        });
        this.clickListener = (RecyclerViewClickListenerDoctors) clickListener;
    }

    public RecyclerTouchListenerDoctors(Callback<GetUsers> getUsersCallback, RecyclerView listDoctors, RecyclerViewClickListenerDoctors recyclerViewClickListenerDoctors) {
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
        View child = rv.findChildViewUnder(e.getX(), e.getY());
        if(child!=null&&clickListener!=null&&gestureDetector.onTouchEvent(e)){
            clickListener.onClick(child, rv.getChildAdapterPosition(child));
        }

        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
