package ch.hesso.remembeer.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;

import ch.hesso.remembeer.R;
import ch.hesso.remembeer.database.entity.BeerEntity;
import ch.hesso.remembeer.utils.Helpers;
import ch.hesso.remembeer.utils.OnClickItem;

import java.util.List;

/**
* Création de la vue dynamique de la liste des bières
 */
public class BeerRecyclerViewAdapter extends RecyclerView.Adapter<BeerRecyclerViewAdapter.BeerHolder> {

    private List<BeerEntity> mData;
    private OnClickItem mListener;
    private Context context;

    public BeerRecyclerViewAdapter(Context context, OnClickItem mListener) {
        this.context = context;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public BeerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_card, parent,false);
        MaterialCardView card = view.findViewById(R.id.card_view);
        ImageView img = (ImageView) card.findViewById(R.id.badge_like);
        BeerHolder viewHolder = new BeerHolder(view);

        card.setOnClickListener(v -> mListener.onItemClick(view,
                viewHolder.getAdapterPosition()));
        card.setOnLongClickListener(v -> {
            mListener.onItemLongClick(v, viewHolder.getAdapterPosition());
            return true;
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final BeerHolder holder, int position) {
        BeerEntity b = mData.get(position);
        holder.setDetails(b);
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0: mData.size();
    }

    public class BeerHolder extends RecyclerView.ViewHolder {
        private TextView txtName;
        private ImageView img;
        private ImageView imgBeer;
        public BeerHolder(View view) {
            super(view);
            txtName = view.findViewById(R.id.textViewName);
            img = view.findViewById(R.id.badge_like);
            imgBeer = view.findViewById(R.id.image_elmt);

        }
        void setDetails(BeerEntity b) {
            txtName.setText(b.getName());
            img.setBackgroundResource(b.isFavoris()? R.drawable.like_yellow_full:
                    R.drawable.like_yellow_empty);
            Helpers.setImageFromPathOrDefault(imgBeer, b.getImage(), b);

        }

        @Override
        public String toString() {
            return super.toString() + " '" + txtName.getText() + "'";
        }
    }

    public void setData(List<BeerEntity> data) {
        this.mData = data;
    }

}
