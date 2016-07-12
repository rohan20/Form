package com.coppermobile.form;


import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Asks for user id and password
 */
public class SignInFragment extends Fragment implements View.OnClickListener {

    @Bind(R.id.email_edit_text)
    EditText mEditTextEmailMobileNumber;
    @Bind(R.id.password_edit_text)
    EditText mEditTextPassword;
    @Bind(R.id.haveNotRegisteredYetTextView)
    TextView mTextViewSignUpNow;

    public SignInFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        View v = inflater.inflate(R.layout.fragment_sign_in, container, false);
        ButterKnife.bind(this, v);

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();

        getActivity().setTitle("IIT Admission Portal");
        mTextViewSignUpNow.setOnClickListener(this);
    }

    //ButterKnife OnClick Annotation
    @OnClick(R.id.submit_sign_in_button)
    void onSubmitClicked() {

        ArrayList<UserModel> users = new ArrayList<>();

        try {
            users.addAll(UserModel.listAll(UserModel.class));
        } catch (SQLiteException e) {
            Toast.makeText(getActivity(), "User doesn't exist. Sign up first.", Toast.LENGTH_SHORT).show();
            return;
        }

        UserModel currentUser;

        for (UserModel x : users) {
            if (x.getmUserEmail().equals(mEditTextEmailMobileNumber.getText().toString()) || x.getmUserPhoneNumber().equals(mEditTextEmailMobileNumber.getText().toString())) {
                if (x.getmUserPassword().equals(mEditTextPassword.getText().toString())) {
                    currentUser = UserModel.findById(UserModel.class, x.getId());

                    Bundle b = new Bundle();
                    b.putString(Constants.LOGGED_IN_USER_EMAIL_ID, currentUser.getmUserEmail());

                    if (currentUser.isDetailsSaved()) {

                        DetailsFragment detailsFragment = new DetailsFragment();
                        detailsFragment.setArguments(b);
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_for_fragments, detailsFragment).commit();

                    } else {

                        FormFragment formFragment = new FormFragment();
                        formFragment.setArguments(b);
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_for_fragments, formFragment).commit();
                    }

                    Toast.makeText(getActivity(), "Hello " + currentUser.getmUserFirstName() + " " + currentUser.getmUserLastName(), Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        }

        Toast.makeText(getActivity(), "Invalid email/number/password", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.haveNotRegisteredYetTextView:
                SignUpFragment signUpFragment = new SignUpFragment();
                getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.frame_layout_for_fragments, signUpFragment).commit();
        }

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_sign_in, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.all_users:

                AllUsersFragment allUsersFragment = new AllUsersFragment();
                getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.frame_layout_for_fragments, allUsersFragment).commit();
                break;

            case R.id.remove_account:

                DeleteAccountFragment deleteAccountFragment = new DeleteAccountFragment();
                getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.frame_layout_for_fragments, deleteAccountFragment).commit();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
