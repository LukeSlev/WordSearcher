package com.lslevins.wordsearcher;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Luke Slevinsky on 2019-05-12.
 */
public class GridRecyclerViewAdapter extends RecyclerView.Adapter<GridRecyclerViewAdapter.ViewHolder> {

    final private static String TAG = "GridRecyclerViewAdapter";
    private Context mContext;
    private char[] mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    GridRecyclerViewAdapter(Context context, char[] data) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        GameBoard.getInstance().setSelectedCells(new boolean[data.length]);
    }

    // inflates the cell layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.grid_cell, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each cell
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.myTextView.setText(String.valueOf(mData[position]));
        holder.myTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean[] selected = GameBoard.getInstance().getSelectedCells();
                if (selected[position]) {
                    int idx = GameBoard.getInstance().getSelectedChars().indexOf(""+mData[position]);
                    Log.d(TAG, "onClick: idx "+idx);
                    if (idx == 0 || idx == GameBoard.getInstance().getSelectedLength()-1) {
                        GameBoard.getInstance().getSelectedChars().deleteCharAt(idx);
                        selected[position] = !selected[position];
                    }
                } else {
                    selected[position] = true;
                    GameBoard.getInstance().addChar(mData[position]);
                }
                notifyDataSetChanged();
            }
        });

        if (GameBoard.getInstance().getSelectedCells()[position]) {
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
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView myTextView;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.LetterView);
//            itemView.setOnClickListener(this);
        }

//        @Override
//        public void onClick(View view) {
//            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
//        }
    }

    // convenience method for getting data at click position
    char getItem(int id) {
        return mData[id];
    }

    boolean getSelected(int id) {
        return GameBoard.getInstance().getSelectedCells()[id];
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
