package com.coppermobile.form;


import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewProfileFragment extends Fragment {

    @Bind(R.id.user_image)
    ImageView mUserImage;
    @Bind(R.id.user_name)
    TextView mUserName;
    @Bind(R.id.user_email)
    TextView mUserEmail;
    @Bind(R.id.user_mobile_number)
    TextView mUserMobileNumber;

    private UserModel loggedInUser;

    public ViewProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("My Profile");
        return inflater.inflate(R.layout.fragment_view_profile, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        View v = getView();
        ButterKnife.bind(this, v);

        getLoggedInUser();

        //set image
        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), Uri.parse(loggedInUser.getmUserImage()));
        } catch (IOException e) {
            mUserImage.setImageResource(R.drawable.placeholder_image);
        }

        mUserImage.setImageBitmap(bitmap);


        //set name
        if (loggedInUser.getmUserMiddleName() == null)
            mUserName.setText(loggedInUser.getmUserFirstName() + " " + loggedInUser.getmUserLastName());
        else
            mUserName.setText(loggedInUser.getmUserFirstName() + " " + loggedInUser.getmUserMiddleName() + " " + loggedInUser.getmUserLastName());

        //set email
        mUserEmail.setText(loggedInUser.getmUserEmail());

        //set number
        mUserMobileNumber.setText(loggedInUser.getmUserPhoneNumber());

    }

    private void getLoggedInUser() {

        for (UserModel x : UserModel.listAll(UserModel.class)) {
            if (x.getmUserEmail().equals(getArguments().getString(Constants.LOGGED_IN_USER_EMAIL_ID))) {
                loggedInUser = UserModel.findById(UserModel.class, x.getId());
            }
        }
    }
}
