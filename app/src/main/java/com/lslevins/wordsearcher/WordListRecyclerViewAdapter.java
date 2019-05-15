package com.lslevins.wordsearcher;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Luke Slevinsky on 2019-05-13.
 */
public class WordListRecyclerViewAdapter extends RecyclerView.Adapter<WordListRecyclerViewAdapter.ViewHolder>{

    final private static String TAG = "GridRecyclerViewAdapter";
    private Context mContext;
    private String[] mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    WordListRecyclerViewAdapter(Context context, String[] data) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the cell layout from xml when needed
    @Override
    @NonNull
    public WordListRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.grid_cell, parent, false);
        return new WordListRecyclerViewAdapter.ViewHolder(view);
    }

    // binds the data to the TextView in each cell
    @Override
    public void onBindViewHolder(@NonNull WordListRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.myTextView.setText(String.valueOf(mData[position]));

        if (GameBoard.getInstance().getFoundWords()[position]) {
            holder.myTextView.setBackgroundColor(Color.GREEN);

        } else {
            holder.myTextView.setBackgroundColor(mContext.getResources().getColor(R.color.lighter_grey));
        }
    }

    // total number of cells
    @Override
    public int getItemCount() {
        return mData.length;
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.LetterView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    String getItem(int id) {
        return mData[id];
    }


    int indexOf(String word) {
        for (int i=0; i<getItemCount();i++) {
            if (word.compareTo(mData[i].toLowerCase()) == 0) {
                return i;
            }
        }
        return -1;
    }

    // allows clicks events to be caught
    void setClickListener(WordListRecyclerViewAdapter.ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
