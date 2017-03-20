package com.codeclan.workoutplannerapp;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by user on 20/03/2017.
 */

public class DeleteWarning extends DialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.delete_warning_dialog, container, false);
        setStyle(DeleteWarning.STYLE_NO_TITLE, R.style.DialogWarning);
        getDialog().setTitle("Delete Warning");
        return rootView;
    }
}
