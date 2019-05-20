package com.example.dreamteam.common;

import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import com.example.dreamteam.R;

public class Utils {
    /*
    * HIDE STATUS BAR
    * */
    public static void setTranslateStatusBar(AppCompatActivity context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            context.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            Window window = context.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);

        }
    }
    public static Fragment replaceFragment(Fragment newFrag, int containerID, FragmentManager fragmentManager, String tag) {

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //noinspection ResourceType
        fragmentTransaction.setCustomAnimations(R.anim.slide_right2left, 0, 0, 0);
        if (newFrag.isAdded()) {
            fragmentTransaction.show(newFrag);
        } else {

            fragmentTransaction.replace(containerID, newFrag);
        }
        if (!isValueNullOrEmpty(tag))
            fragmentTransaction.addToBackStack(tag);

        fragmentTransaction.commitAllowingStateLoss();
        //fragmentManager.executePendingTransactions();


        return newFrag;
    }
    public static boolean isValueNullOrEmpty(String value) {
        boolean isValue = false;
        if (value == null || value.equals("")
                || value.equals("null") || value.trim().length() == 0) {
            isValue = true;
        }
        return isValue;
    }
    public static boolean isEditTextIsEmpty(EditText editText){
         if (editText!=null){
             String value= editText.getText().toString().trim();
             if (isValueNullOrEmpty(value))
                 return true;
             else return false;
         }
         return false;
    }
}
