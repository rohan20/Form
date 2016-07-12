package com.coppermobile.form.FormChildFragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.coppermobile.form.Constants;
import com.coppermobile.form.R;
import com.roughike.swipeselector.SwipeItem;
import com.roughike.swipeselector.SwipeSelector;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class PersonalChildFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    @Bind(R.id.candidate_name_edit_text)
    EditText mCandidateName;
    @Bind(R.id.state_of_eligibility_autocomplete_text_view)
    AutoCompleteTextView mStateOfEligibility;
    @Bind(R.id.gender_swipe_selector)
    SwipeSelector mGenderSwipeSelector;
    @Bind(R.id.category_swipe_selector)
    SwipeSelector mCategorySwipeSelector;
    @Bind(R.id.dateSpinner)
    Spinner mDateSpinner;
    @Bind(R.id.monthSpinner)
    Spinner mMonthSpinner;
    @Bind(R.id.yearSpinner)
    Spinner mYearSpinner;
    @Bind(R.id.natonality_autocomplete_edit_text)
    AutoCompleteTextView mNationality;
    @Bind(R.id.yes_no_pwd)
    SwipeSelector mPWDSwipeSelector;
    @Bind(R.id.yes_no_girl_child)
    SwipeSelector mOnlyGirlSwipeSelector;
    @Bind(R.id.personal_tab_next_button)
    Button bPersonalDone;

    private ArrayAdapter<Integer> mDateSpinnerAdapter;
    private ArrayAdapter<String> mMonthSpinnerAdapter;
    private ArrayAdapter<Integer> mYearSpinnerAdapter;

    private ArrayAdapter<String> mNationalityArrayAdapter;
    private ArrayAdapter<String> mStatesArrayAdapter;

    private List<String> personalDetailsToParentFragmentList;

    private OnPersonalTabSelectedListener mOnPersonalTabSelectedListener;

    public PersonalChildFragment() {
        // Required empty public constructor
    }


    @Override
    public void onAttachFragment(Fragment childFragment) {
        super.onAttachFragment(childFragment);

        try {
            mOnPersonalTabSelectedListener = (OnPersonalTabSelectedListener) childFragment;
        } catch (ClassCastException e) {
            Toast.makeText(getActivity(), childFragment.toString() + " must implement OnPersonalTabSelectedListener", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        onAttachFragment(getParentFragment());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_personal_child, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        View v = getView();
        ButterKnife.bind(this, v);

        personalDetailsToParentFragmentList = new ArrayList<>();
        bPersonalDone.setOnClickListener(this);

        createAdapters();
        setAdapters();

    }

    private void setAdapters() {
        mDateSpinner.setAdapter(mDateSpinnerAdapter);
        mMonthSpinner.setAdapter(mMonthSpinnerAdapter);
        mYearSpinner.setAdapter(mYearSpinnerAdapter);
        mStateOfEligibility.setAdapter(mStatesArrayAdapter);
        mNationality.setAdapter(mNationalityArrayAdapter);

    }

    private void createAdapters() {

        mDateSpinnerAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, Constants.listOfDates());
        mMonthSpinnerAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, Constants.listOfMonths());
        mYearSpinnerAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, Constants.listOfYears());
        mNationalityArrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, Constants.listOfCountries());
        mStatesArrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, Constants.listOfStatesInIndia());

        mGenderSwipeSelector.setItems(
                new SwipeItem(0, "Male", null),
                new SwipeItem(1, "Female", null)
        );

        mPWDSwipeSelector.setItems(
                new SwipeItem(0, "No", null),
                new SwipeItem(1, "Yes", null)
        );

        mOnlyGirlSwipeSelector.setItems(
                new SwipeItem(0, "Not Applicable", null),
                new SwipeItem(1, "Yes", null),
                new SwipeItem(2, "No", null)
        );

        mCategorySwipeSelector.setItems(
                new SwipeItem(0, "General", null),
                new SwipeItem(1, "Scheduled Caste (SC)", null),
                new SwipeItem(2, "Scheduled Tribe (ST)", null),
                new SwipeItem(3, "OBC", null)
        );

        mDateSpinner.setOnItemSelectedListener(this);
        mMonthSpinner.setOnItemSelectedListener(this);
        mYearSpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.personal_tab_next_button:

                if (dataIsValid()) {

                    if (mOnPersonalTabSelectedListener != null) {
                        mOnPersonalTabSelectedListener.onPersonalTabSelected(personalDetailsToParentFragmentList);
                    }
                }
                break;
        }
    }

    private boolean dataIsValid() {

        personalDetailsToParentFragmentList.clear();

        if (TextUtils.isEmpty(mCandidateName.getText())) {
            mCandidateName.setError("Enter candidate name");
            return false;
        } else {
            personalDetailsToParentFragmentList.add(0, mCandidateName.getText().toString());
        }

        if (TextUtils.isEmpty(mStateOfEligibility.getText())) {
            mStateOfEligibility.setError("Enter state of eligibility");
        } else {
            personalDetailsToParentFragmentList.add(1, mStateOfEligibility.getText().toString());
        }

        personalDetailsToParentFragmentList.add(2, mGenderSwipeSelector.getSelectedItem().title);
        personalDetailsToParentFragmentList.add(3, mCategorySwipeSelector.getSelectedItem().title);
        personalDetailsToParentFragmentList.add(4, mDateSpinner.getSelectedItem().toString());
        personalDetailsToParentFragmentList.add(5, mMonthSpinner.getSelectedItem().toString());
        personalDetailsToParentFragmentList.add(6, mYearSpinner.getSelectedItem().toString());
        personalDetailsToParentFragmentList.add(7, mPWDSwipeSelector.getSelectedItem().title);
        personalDetailsToParentFragmentList.add(8, mOnlyGirlSwipeSelector.getSelectedItem().title);

        if (TextUtils.isEmpty(mNationality.getText())) {
            mNationality.setError("Enter Nationality");
            return false;
        } else {
            personalDetailsToParentFragmentList.add(9, mNationality.getText().toString());
        }

        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        TextView selectedText = (TextView) parent.getChildAt(0);
        if (selectedText != null) {
            selectedText.setTextColor(ContextCompat.getColor(getActivity(), android.R.color.black));
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public interface OnPersonalTabSelectedListener {
        void onPersonalTabSelected(List<String> personalFragmentDetailsList);
    }

}
