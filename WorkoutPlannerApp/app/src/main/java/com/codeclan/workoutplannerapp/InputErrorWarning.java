package com.codeclan.workoutplannerapp;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by user on 21/03/2017.
 */

public class InputErrorWarning  extends DialogFragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.input_error_warning, container, false);
        setStyle(DeleteWarning.STYLE_NO_TITLE, R.style.DialogWarning);
        getDialog().setTitle("Input Error");

        Button dismiss = (Button) rootView.findViewById(R.id.warning_read_button);
        dismiss.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return rootView;
    }
}
