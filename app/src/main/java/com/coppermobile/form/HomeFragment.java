package com.coppermobile.form;


import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * @author Rohan Taneja
 *         Offers sign in / sign up functionalities.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {

    @Bind(R.id.sign_in_button)
    Button bSignIn;
    @Bind(R.id.sign_up_button)
    Button bSignUp;
    @Bind(R.id.iit_logo)
    ImageView iitLogoImageView;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();

        initialize();
    }

    private void initialize() {

        bSignIn.setVisibility(View.INVISIBLE);
        bSignIn.setOnClickListener(this);
        bSignUp.setVisibility(View.INVISIBLE);
        bSignUp.setOnClickListener(this);
        setUpCountDownTimer();
    }

    private void setUpCountDownTimer() {

        CountDownTimer mCountDownTimer;

        final Animation in = new AlphaAnimation(0.0f, 1.0f);
        in.setDuration(1500);

        mCountDownTimer = new CountDownTimer(1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {

                Animation translation = AnimationUtils.loadAnimation(getActivity(), R.anim.translate);
                iitLogoImageView.startAnimation(translation);
                bSignIn.setVisibility(View.VISIBLE);
                bSignIn.setAnimation(in);
                bSignUp.setVisibility(View.VISIBLE);
                bSignUp.setAnimation(in);
            }
        };

        mCountDownTimer.start();

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.sign_in_button:
                if (getActivity() != null) {
                    ((MainActivity) getActivity()).switchFragments(new SignInFragment(), true, null);
                }
                break;

            case R.id.sign_up_button:
                SignUpFragment signUpFragment = new SignUpFragment();
                getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.frame_layout_for_fragments, signUpFragment).commit();
        }

    }
}
