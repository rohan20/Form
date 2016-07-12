package com.coppermobile.form;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class DeleteAccountFragment extends Fragment implements View.OnClickListener {

    @Bind(R.id.delete_account_email_mobile_number_edit_text)
    EditText mEditTextEmailOrMobileDelete;
    @Bind(R.id.delete_account_password_edit_text)
    EditText mEditTextPasswordDelete;
    @Bind(R.id.delete_account_button)
    Button bDeleteMyAccount;

    public DeleteAccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_delete_account, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        View v = getView();
        ButterKnife.bind(this, v);

        bDeleteMyAccount.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.delete_account_button:

                long deletedID = 0;

                for (UserModel x : UserModel.listAll(UserModel.class)) {
                    if (mEditTextEmailOrMobileDelete.getText().toString().equals(x.getmUserEmail()) && mEditTextPasswordDelete.getText().toString().equals(x.getmUserPassword())) {
                        deletedID = x.getId();
                        break;
                    } else if (mEditTextEmailOrMobileDelete.getText().toString().equals(x.getmUserPhoneNumber()) && mEditTextPasswordDelete.getText().toString().equals(x.getmUserPassword())) {
                        deletedID = x.getId();
                        break;
                    }
                }

                if (deletedID != 0) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("Are you sure?");

                    final long finalDeletedID = deletedID;
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            UserModel.findById(UserModel.class, finalDeletedID).delete();

                            Toast.makeText(getActivity(), "Account removed.", Toast.LENGTH_SHORT).show();

                            SignInFragment signInFragment = new SignInFragment();
                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_for_fragments, signInFragment).commit();
                        }
                    });

                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()

                            {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            }

                    );

                    builder.create().show();

                } else {
                    Toast.makeText(getActivity(), "No such user exists.", Toast.LENGTH_SHORT).show();
                }


                break;

        }
    }
}
