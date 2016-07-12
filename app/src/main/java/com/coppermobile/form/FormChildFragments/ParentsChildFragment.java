package com.coppermobile.form.FormChildFragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
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
public class ParentsChildFragment extends Fragment implements View.OnClickListener {

    @Bind(R.id.father_name_edit_text)
    EditText mFatherName;
    @Bind(R.id.father_email_edit_text)
    EditText mFatherEmail;
    @Bind(R.id.father_qualification_swipe_selector)
    SwipeSelector mFatherQualificationSwipeSelector;
    @Bind(R.id.father_occupation_edit_text)
    EditText mFatherOccupation;
    @Bind(R.id.father_annual_income_swipe_selector)
    SwipeSelector mFatherAnnualIncomeSwipeSelector;

    @Bind(R.id.mother_name_edit_text)
    EditText mMotherName;
    @Bind(R.id.mother_email_edit_text)
    EditText mMotherEmail;
    @Bind(R.id.mother_qualification_swipe_selector)
    SwipeSelector mMotherQualificationSwipeSelector;
    @Bind(R.id.mother_occupation_edit_text)
    EditText mMotherOccupation;
    @Bind(R.id.mother_annual_income_swipe_selector)
    SwipeSelector mMotherAnnualIncomeSwipeSelector;

    @Bind(R.id.home_address_edit_text)
    EditText mHomeAddress;
    @Bind(R.id.home_city_town_village_edit_text)
    EditText mHomeCityTownVillage;
    @Bind(R.id.home_landline_number_edit_text)
    EditText mHomeLandlineNumber;
    @Bind(R.id.home_locality_edit_text)
    EditText mHomeLocality;
    @Bind(R.id.home_pin_code_edit_text)
    EditText mHomePinCode;
    @Bind(R.id.home_state_autocomplete_text_view)
    AutoCompleteTextView mHomeState;
    @Bind(R.id.parents_tab_next_button)
    Button bParentsDone;

    ArrayAdapter<String> statesArrayAdapter;

    private List<String> parentsDetailsToParentFragmentList;

    private OnParentsTabSelectedListener mOnParentsTabSelectedListener;

    public ParentsChildFragment() {
        // Required empty public constructor
    }


    @Override
    public void onAttachFragment(Fragment childFragment) {
        super.onAttachFragment(childFragment);

        try {
            mOnParentsTabSelectedListener = (OnParentsTabSelectedListener) childFragment;
        } catch (ClassCastException e) {
            Toast.makeText(getActivity(), childFragment.toString() + " must implement OnParentsTabSelectedListener", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        onAttachFragment(getParentFragment());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_parents_child, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        View v = getView();
        ButterKnife.bind(this, v);

        bParentsDone.setOnClickListener(this);
        parentsDetailsToParentFragmentList = new ArrayList<>();


        createSwipableItems();
        createAndSetStatesAutoCompleteTextView();
    }

    private void createAndSetStatesAutoCompleteTextView() {

        statesArrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, Constants.listOfStatesInIndia());
        mHomeState.setAdapter(statesArrayAdapter);

    }

    private void createSwipableItems() {

        mFatherQualificationSwipeSelector.setItems(
                new SwipeItem(0, "10th Pass", null),
                new SwipeItem(1, "12th Pass", null),
                new SwipeItem(2, "Diploma", null),
                new SwipeItem(3, "Graduate", null),
                new SwipeItem(4, "Post Graduate", null),
                new SwipeItem(5, "PhD or higher", null),
                new SwipeItem(5, "Uneducated", null)
        );

        mFatherAnnualIncomeSwipeSelector.setItems(
                new SwipeItem(0, "Not Applicable", null),
                new SwipeItem(1, "< 12,000", null),
                new SwipeItem(2, "< 1,20,000", null),
                new SwipeItem(3, "< 12,00,000", null),
                new SwipeItem(4, ">= 12,00,000", null)
        );

        mMotherQualificationSwipeSelector.setItems(
                new SwipeItem(0, "10th Pass", null),
                new SwipeItem(1, "12th Pass", null),
                new SwipeItem(2, "Diploma", null),
                new SwipeItem(3, "Graduate", null),
                new SwipeItem(4, "Post Graduate", null),
                new SwipeItem(5, "PhD or higher", null),
                new SwipeItem(5, "Uneducated", null)
        );

        mMotherAnnualIncomeSwipeSelector.setItems(
                new SwipeItem(0, "Not Applicable", null),
                new SwipeItem(1, "< 12,000", null),
                new SwipeItem(2, "< 1,20,000", null),
                new SwipeItem(3, "< 12,00,000", null),
                new SwipeItem(4, ">= 12,00,000", null)
        );

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.parents_tab_next_button:

                if (dataIsValid()) {

                    if (mOnParentsTabSelectedListener != null) {
                        mOnParentsTabSelectedListener.onParentsTabSelected(parentsDetailsToParentFragmentList);
                    }
                }
                break;
        }
    }

    private boolean dataIsValid() {

        parentsDetailsToParentFragmentList.clear();

        //Father
        if (TextUtils.isEmpty(mFatherName.getText())) {
            mFatherName.setError("Enter father's name");
            return false;
        } else {
            parentsDetailsToParentFragmentList.add(0, mFatherName.getText().toString());
        }

        if (!(android.util.Patterns.EMAIL_ADDRESS.matcher(mFatherEmail.getText().toString()).matches())) {
            mFatherEmail.setError("Incorrect Email");
            return false;
        } else {
            parentsDetailsToParentFragmentList.add(1, mFatherEmail.getText().toString());
        }

        parentsDetailsToParentFragmentList.add(2, mFatherQualificationSwipeSelector.getSelectedItem().title);

        if (TextUtils.isEmpty(mFatherOccupation.getText())) {
            mFatherOccupation.setError("Enter father's occupation");
            return false;
        } else {
            parentsDetailsToParentFragmentList.add(3, mFatherOccupation.getText().toString());
        }

        parentsDetailsToParentFragmentList.add(4, mFatherAnnualIncomeSwipeSelector.getSelectedItem().title);

        //Mother
        if (TextUtils.isEmpty(mMotherName.getText())) {
            mMotherName.setError("Enter mother's name");
            return false;
        } else {
            parentsDetailsToParentFragmentList.add(5, mMotherName.getText().toString());
        }

        if (!(android.util.Patterns.EMAIL_ADDRESS.matcher(mMotherEmail.getText().toString()).matches())) {
            mMotherEmail.setError("Incorrect Email");
            return false;
        } else {
            parentsDetailsToParentFragmentList.add(6, mMotherEmail.getText().toString());
        }

        parentsDetailsToParentFragmentList.add(7, mMotherQualificationSwipeSelector.getSelectedItem().title);

        if (TextUtils.isEmpty(mMotherOccupation.getText())) {
            mMotherOccupation.setError("Enter mother's occupation");
            return false;
        } else {
            parentsDetailsToParentFragmentList.add(8, mMotherOccupation.getText().toString());
        }

        parentsDetailsToParentFragmentList.add(9, mMotherAnnualIncomeSwipeSelector.getSelectedItem().title);

        //Permanent Residence
        if (TextUtils.isEmpty(mHomeAddress.getText())) {
            mHomeAddress.setError("Enter home address");
            return false;
        } else {
            parentsDetailsToParentFragmentList.add(10, mHomeAddress.getText().toString());
        }

        if (TextUtils.isEmpty(mHomeLocality.getText())) {
            mHomeLocality.setError("Enter home locality");
            return false;
        } else {
            parentsDetailsToParentFragmentList.add(11, mHomeLocality.getText().toString());
        }

        if (TextUtils.isEmpty(mHomeCityTownVillage.getText())) {
            mHomeCityTownVillage.setError("Enter home city/town/village");
            return false;
        } else {
            parentsDetailsToParentFragmentList.add(12, mHomeCityTownVillage.getText().toString());
        }

        if (TextUtils.isEmpty(mHomeState.getText())) {
            mHomeState.setError("Enter home state");
            return false;
        } else {
            parentsDetailsToParentFragmentList.add(13, mHomeState.getText().toString());
        }

        if (TextUtils.isEmpty(mHomePinCode.getText()) || mHomePinCode.getText().toString().length() < 6 || !(mHomePinCode.getText().toString().trim().matches(Constants.IS_A_NUMBER))) {
            mHomePinCode.setError("Enter valid home PIN code");
            return false;
        } else {
            parentsDetailsToParentFragmentList.add(14, mHomePinCode.getText().toString());
        }

        if (TextUtils.isEmpty(mHomeLandlineNumber.getText()) || !(mHomeLandlineNumber.getText().toString().trim().matches(Constants.IS_A_NUMBER))) {
            mHomeLandlineNumber.setError("Enter valid home phone number");
            return false;
        } else {
            parentsDetailsToParentFragmentList.add(15, mHomeLandlineNumber.getText().toString());
        }

        return true;
    }

    public interface OnParentsTabSelectedListener {
        void onParentsTabSelected(List<String> parentsFragmentDetailsList);
    }

}