package ch.hesso.remembeer.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.List;
import ch.hesso.remembeer.R;
import ch.hesso.remembeer.database.entity.BreweryEntity;
import ch.hesso.remembeer.utils.Helpers;
import ch.hesso.remembeer.utils.OnClickItem;



/**
 * Création de la vue dynamique de la liste des brasseries
 */
public class BreweryRecyclerViewAdapter extends RecyclerView.Adapter<BreweryRecyclerViewAdapter.BreweryHolder> {

        private List<BreweryEntity> mData;
        private OnClickItem mListener;

        public BreweryRecyclerViewAdapter(OnClickItem mListener) {
            this.mListener = mListener;
        }

        @NonNull
        @Override
        public BreweryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_card, parent,false);
            MaterialCardView card = view.findViewById(R.id.card_view);
            BreweryRecyclerViewAdapter.BreweryHolder viewHolder = new BreweryRecyclerViewAdapter.BreweryHolder(view);
            card.setOnClickListener(v -> mListener.onItemClick(view,
                    viewHolder.getAdapterPosition()));
            card.setOnLongClickListener(v -> {
                mListener.onItemLongClick(v, viewHolder.getAdapterPosition());
                return true;
            });
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(final BreweryHolder holder, int position) {
            BreweryEntity b = mData.get(position);
            holder.setDetails(b);
        }

        @Override
        public int getItemCount() {
            return mData == null ? 0: mData.size();
        }

        public class BreweryHolder extends RecyclerView.ViewHolder {
            private TextView txtName;
            private ImageView imgBrewery;
            public BreweryHolder(View view) {
                super(view);
                txtName = view.findViewById(R.id.textViewName);
                imgBrewery = view.findViewById(R.id.image_elmt);

            }
            void setDetails(BreweryEntity b) {
                txtName.setText(b.getName());
                Helpers.setImageFromPathOrDefault(imgBrewery, b.getImage(),b);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + txtName.getText() + "'";
            }
        }

        public void setData(List<BreweryEntity> data) {
            this.mData = data;
        }

}

