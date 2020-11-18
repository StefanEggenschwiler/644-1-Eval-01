package ch.hesso.remembeer.utils;

public interface OnAsyncEventListener {
    void onSuccess();
    void onFailure(Exception e);
}
