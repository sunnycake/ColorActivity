package com.mynuex.coloractivity;


import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ColorPickerDialogFragment extends DialogFragment {

    //Text to be displayed in the dialog's list
    final CharSequence[] COLOR_CHOICES = {"Magenta", "Black", "Blue", "Cyan"};

    // Array with the color values, as int values
    final int[] COLOR_VALUES = {Color.MAGENTA, Color.BLACK, Color.BLUE, Color.CYAN};

    //Interface for the listener
    interface ColorDialogSelectionListener {
        void colorSelected(int color);
    }

    private ColorDialogSelectionListener mListener;

    //New instance - no arguments just for good practice
    public static ColorPickerDialogFragment newInstance() {
        ColorPickerDialogFragment fragment = new ColorPickerDialogFragment();
        return fragment;
    }

    @Override // Override onAttach to configure listener
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ColorDialogSelectionListener) {
            mListener = (ColorDialogSelectionListener) activity;
        } else {
            throw new RuntimeException(activity.toString() + " must implement ColorDialogSelectionListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Choose a background color")
                .setItems(COLOR_CHOICES, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // which is the index of the item selected from the list
                        // the parallel array is used to return a color value
                        // return the data from the index of this array
                        mListener.colorSelected(COLOR_VALUES[which]);
                    }
                });

        return builder.create();
    }
}

