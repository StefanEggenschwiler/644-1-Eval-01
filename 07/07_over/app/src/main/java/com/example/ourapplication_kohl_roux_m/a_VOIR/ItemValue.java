package com.example.ourapplication_kohl_roux_m.a_VOIR;

public class ItemValue {

        private double val;

        public ItemValue (double val) {
            setVal(val);
        }

    public double getVal() {
        return val;
    }

    public void setVal(double val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return  String.valueOf(val)  ;
    }
}


