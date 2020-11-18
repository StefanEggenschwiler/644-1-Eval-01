package com.example.ourapplication_kohl_roux_m.a_VOIR;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ourapplication_kohl_roux_m.R;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*public class MyAdapterElectricityEntries extends RecyclerView.Adapter<MyAdapterElectricityEntries.MyViewHolder>{

    private ItemValue defaultValue = new ItemValue(0.0);
    private List <ItemValue> electricityValues;

    public List<ItemValue> getElectricityValues() {
        return electricityValues;
    }

    public void setElectricityValues(List<ItemValue> electricityValues) {
        this.electricityValues = electricityValues;
    }

    @Override
        public int getItemCount() {
            return electricityValues.size();
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.activity_inputs_electricity, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            ItemValue electValue = electricityValues.get(position);
            holder.display(electValue);
        }

        public static class MyViewHolder extends RecyclerView.ViewHolder {
            private final TextView value;
            private ItemValue currentValue;

            public MyViewHolder(final View itemView) {
                super(itemView);
                value = ((TextView) itemView.findViewById(R.id.itemValue));
 /*               itemView.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        new AlertDialog.Builder(itemView.getContext())
                                .setTitle(currentValue.first)
                                .setMessage(currentPair.
                                .show();
                    }
                });

  */
/*            }

            public void display(ItemValue electValue) {
                currentValue = electValue;
            }

        }

}
*/