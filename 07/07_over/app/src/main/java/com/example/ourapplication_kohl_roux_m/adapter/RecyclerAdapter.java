package com.example.ourapplication_kohl_roux_m.adapter;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ourapplication_kohl_roux_m.R;
import com.example.ourapplication_kohl_roux_m.dbClass.entities.CarEntity;
import com.example.ourapplication_kohl_roux_m.dbClass.entities.TrajetEntity;
import com.example.ourapplication_kohl_roux_m.util.RecyclerViewItemClickListener;

import java.util.List;
import java.util.Objects;


public class RecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private List<T> mData;
    private RecyclerViewItemClickListener mListener;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView mTextView;
        ViewHolder(TextView textView) {
            super(textView);
            mTextView = textView;
        }
    }

    public RecyclerAdapter(RecyclerViewItemClickListener listener) {
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view, parent, false);
        final ViewHolder viewHolder = new ViewHolder(v);
        v.setOnClickListener(view -> mListener.onItemClick(view, viewHolder.getAdapterPosition()));
        v.setOnLongClickListener(view -> {
            mListener.onItemLongClick(view, viewHolder.getAdapterPosition());
            return true;
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        T item = mData.get(position);
        if (item.getClass().equals(CarEntity.class))
            holder.mTextView.setText(((CarEntity) item).getNickName());
        if (item.getClass().equals(TrajetEntity.class))
            holder.mTextView.setText(((TrajetEntity) item).getName() + " " + ((TrajetEntity) item).getDate() + " " + ((TrajetEntity) item).getKmTot());
    }

    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size();
        } else {
            return 0;
        }
    }

    public void setData(final List<T> data) {
        if (mData == null) {
            mData = data;
            notifyItemRangeInserted(0, data.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return mData.size();
                }

                @Override
                public int getNewListSize() {
                    return data.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    if (mData instanceof CarEntity) {
                        return ((CarEntity) mData.get(oldItemPosition)).equals(((CarEntity) data.get(newItemPosition)));
                    }
                    if (mData instanceof TrajetEntity) {
                        return ((TrajetEntity) mData.get(oldItemPosition)).equals(
                                ((TrajetEntity) data.get(newItemPosition)));
                    }
                    return false;
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    if (mData instanceof CarEntity) {
                        CarEntity newCarEntity = (CarEntity) data.get(newItemPosition);
                        CarEntity oldCarEntity = (CarEntity) mData.get(newItemPosition);
                        return Objects.equals(newCarEntity.getUid(),(oldCarEntity.getUid()))
                                && Objects.equals(newCarEntity.getNickName(), oldCarEntity.getNickName())
                               /* && Objects.equals(newCarEntity.getBalance(), oldCarEntity.getBalance())
                                && newCarEntity.getOwner().equals(oldCarEntity.getOwner()) */ ;
                    }
                    if (mData instanceof TrajetEntity) {
                        TrajetEntity newClient = (TrajetEntity) data.get(newItemPosition);
                        TrajetEntity oldClient = (TrajetEntity) mData.get(newItemPosition);
                        return Objects.equals(newClient.getCarId(), oldClient.getCarId())
                                && Objects.equals(newClient.getName(), oldClient.getName())
                                && Objects.equals(newClient.getDate(), oldClient.getDate())
                               /* && newClient.getPassword().equals(oldClient.getPassword()) */ ;
                    }
                    return false;
                }
            });
            mData = data;
            result.dispatchUpdatesTo(this);
        }
    }
}
