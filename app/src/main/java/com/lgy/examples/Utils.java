package com.lgy.examples;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Utils {
    public static void showGuildDialog(Context context, final View.OnClickListener listener) {
        final AlertDialog alertDialog = new AlertDialog.Builder(context, R.style.CustomDialog).setCancelable(true).create();
        if (((Activity) context).isFinishing()) {
            return;
        }
        alertDialog.show();
        alertDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        alertDialog.setContentView(R.layout.newer_guide);

        Window w = alertDialog.getWindow();

        android.view.WindowManager.LayoutParams lp = w.getAttributes();
        lp.y = 150;
        lp.x = 100;

        Spinner spinner = (Spinner) alertDialog.findViewById(R.id.sp);
        String[] spinnerItems = {"10","200","400"};
// Create an ArrayAdapter using the string array and a default spinner layout
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context,
//                R.array.planets_array, android.R.layout.simple_spinner_item);
//// Specify the layout to use when the list of choices appears
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(context,
                R.layout.item_select, spinnerItems);
// Apply the adapter to the spinner
        spinnerAdapter.setDropDownViewResource(R.layout.item_drop);
        spinner.setAdapter(spinnerAdapter);
//        LinearLayout linearLayout = (LinearLayout) alertDialog.findViewById(R.id.ll_newer_guide);
//        TextView empty = (TextView) alertDialog.findViewById(R.id.tv_empty);
//        empty.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                alertDialog.dismiss();
//            }
//        });
//        linearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                listener.onClick(view);
//                alertDialog.dismiss();
//            }
//        });
    }
}
