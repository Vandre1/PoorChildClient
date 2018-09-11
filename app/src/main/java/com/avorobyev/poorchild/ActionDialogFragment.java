package com.avorobyev.poorchild;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

public class ActionDialogFragment extends DialogFragment {

    public static final int REQUEST_CODE = 1;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        dialogBuilder
                .setTitle(getArguments().getString("title"))
                .setMessage(getArguments().getString("message"))
                .setPositiveButton("Да, запомнил", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ActionDialogResultListener actionDialogResultListener = (ActionDialogResultListener)getActivity();
                        actionDialogResultListener.ActionDialogResult(Activity.RESULT_OK);
                    }
                })
                .setNegativeButton("Нет, не успел", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ActionDialogResultListener actionDialogResultListener = (ActionDialogResultListener)getActivity();
                        actionDialogResultListener.ActionDialogResult(Activity.RESULT_CANCELED);
                    }
                });
        // Create the AlertDialog object and return it
        return dialogBuilder.create();
    }

    public static ActionDialogFragment newInstance(String title, String message) {
        ActionDialogFragment dialogFragment = new ActionDialogFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("message", message);
        dialogFragment.setArguments(args);

        return dialogFragment;
    }
}
