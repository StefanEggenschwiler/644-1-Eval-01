package ch.hesso.remembeer.utils;

import android.view.View;

public interface OnClickItem {
    void onItemClick(View v, int position);
    void onItemLongClick(View v, int position);
}
