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

public class RecyclerTouchListenerVisits implements RecyclerView.OnItemTouchListener {
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
