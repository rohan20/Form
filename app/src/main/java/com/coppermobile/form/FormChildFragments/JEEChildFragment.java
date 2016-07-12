package com.coppermobile.form.FormChildFragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.coppermobile.form.Constants;
import com.coppermobile.form.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class JEEChildFragment extends Fragment implements View.OnClickListener {

    @Bind(R.id.jee_main_score_edit_text)
    EditText mJEEMainScore;
    @Bind(R.id.jee_main_category_rank_edit_text)
    EditText mJEEMainCategoryRank;
    @Bind(R.id.jee_main_overall_rank_edit_text)
    EditText mJEEMainOverallRank;

    @Bind(R.id.jee_adv_score_edit_text)
    EditText mJEEAdvancedScore;
    @Bind(R.id.jee_adv_category_rank_edit_text)
    EditText mJEEAdvancedCategoryRank;
    @Bind(R.id.jee_adv_overall_rank_edit_text)
    EditText mJEEAdvancedOverallRank;

    @Bind(R.id.jee_tab_next_button)
    Button bJEEDone;

    private List<String> jeeDetailsToParentFragmentList;

    private OnJEETabSelectedListener mOnJEETabSelectedListener;

    public JEEChildFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttachFragment(Fragment childFragment) {
        super.onAttachFragment(childFragment);

        try {
            mOnJEETabSelectedListener = (OnJEETabSelectedListener) childFragment;
        } catch (ClassCastException e) {
            Toast.makeText(getActivity(), childFragment.toString() + " must implement OnJEETabSelectedListener", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        onAttachFragment(getParentFragment());
    }

    @Override
    public void onStart() {
        super.onStart();

        View v = getView();
        ButterKnife.bind(this, v);

        jeeDetailsToParentFragmentList = new ArrayList<>();
        bJEEDone.setOnClickListener(this);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_jee_child, container, false);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.jee_tab_next_button:

                if (dataIsValid()) {

                    if (mOnJEETabSelectedListener != null) {
                        mOnJEETabSelectedListener.onJEETabSelected(jeeDetailsToParentFragmentList);
                    }
                }
                break;
        }
    }

    private boolean dataIsValid() {

        //JEE Main
        try {
            Long.parseLong(mJEEMainScore.getText().toString());
        } catch (NumberFormatException e) {
            mJEEMainScore.setError("JEE Score doesn't contain decimal");
            return false;
        }

        if (TextUtils.isEmpty(mJEEMainScore.getText()) || (!(mJEEMainScore.getText().toString().trim().matches(Constants.IS_A_NUMBER))) || (Long.parseLong(mJEEMainScore.getText().toString()) < 0) || (Long.parseLong(mJEEMainScore.getText().toString()) > 360)) {
            mJEEMainScore.setError("Enter valid score");
            return false;
        } else {
            jeeDetailsToParentFragmentList.add(0, mJEEMainScore.getText().toString());
        }

        if (TextUtils.isEmpty(mJEEMainCategoryRank.getText()) || (!(mJEEMainCategoryRank.getText().toString().trim().matches(Constants.IS_A_NUMBER)))) {
            mJEEMainCategoryRank.setError("Enter valid rank");
            return false;
        } else {
            jeeDetailsToParentFragmentList.add(1, mJEEMainCategoryRank.getText().toString());
        }

        if (TextUtils.isEmpty(mJEEMainOverallRank.getText()) || (!(mJEEMainOverallRank.getText().toString().trim().matches(Constants.IS_A_NUMBER)))) {
            mJEEMainOverallRank.setError("Enter valid rank");
            return false;
        } else {
            jeeDetailsToParentFragmentList.add(2, mJEEMainOverallRank.getText().toString());
        }

        //JEE Advanced
        try {
            Long.parseLong(mJEEAdvancedScore.getText().toString());
        } catch (NumberFormatException e) {
            mJEEAdvancedScore.setError("JEE Score doesn't contain decimal");
            return false;
        }

        if (TextUtils.isEmpty(mJEEAdvancedScore.getText()) || (!(mJEEAdvancedScore.getText().toString().trim().matches(Constants.IS_A_NUMBER))) || (Long.parseLong(mJEEAdvancedScore.getText().toString()) < 0) || (Long.parseLong(mJEEAdvancedScore.getText().toString()) > 480)) {
            mJEEAdvancedScore.setError("Enter valid score");
            return false;
        } else {
            jeeDetailsToParentFragmentList.add(3, mJEEAdvancedScore.getText().toString());
        }

        if (TextUtils.isEmpty(mJEEAdvancedCategoryRank.getText()) || (!(mJEEAdvancedCategoryRank.getText().toString().trim().matches(Constants.IS_A_NUMBER)))) {
            mJEEAdvancedCategoryRank.setError("Enter valid rank");
            return false;
        } else {
            jeeDetailsToParentFragmentList.add(4, mJEEAdvancedCategoryRank.getText().toString());
        }

        if (TextUtils.isEmpty(mJEEAdvancedOverallRank.getText()) || (!(mJEEAdvancedOverallRank.getText().toString().trim().matches(Constants.IS_A_NUMBER)))) {
            mJEEAdvancedOverallRank.setError("Enter valid rank");
            return false;
        } else {
            jeeDetailsToParentFragmentList.add(5, mJEEAdvancedOverallRank.getText().toString());
        }

        if (Long.parseLong(mJEEMainCategoryRank.getText().toString()) > Long.parseLong(mJEEMainOverallRank.getText().toString())) {
            mJEEMainCategoryRank.setError("Category rank is greater than Overall Rank");
            return false;
        }

        if (Long.parseLong(mJEEAdvancedCategoryRank.getText().toString()) > Long.parseLong(mJEEAdvancedOverallRank.getText().toString())) {
            mJEEAdvancedCategoryRank.setError("Category rank is greater than Overall Rank");
            return false;
        }

        return true;
    }

    public interface OnJEETabSelectedListener {
        void onJEETabSelected(List<String> jeeFragmentDetailsList);
    }

}
