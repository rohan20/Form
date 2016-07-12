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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
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
public class AcademicChildFragment extends Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    @Bind(R.id.academic_year_class_10_spinner)
    Spinner mAcademicSpinnerClass10Year;
    @Bind(R.id.academic_year_class_12_spinner)
    Spinner mAcademicSpinnerClass12Year;
    @Bind(R.id.academic_place_class_12_spinner)
    Spinner mAcademicSpinnerClass12Place;

    @Bind(R.id.academic_name_of_exam_class_12_edit_text)
    EditText mAcademicEditTextClass12NameOfExam;
    @Bind(R.id.academic_percentage_class_12_edit_text)
    EditText mAcademicEditTextClass12Percentage;
    @Bind(R.id.academic_address_of_school_class_12_edit_text)
    EditText mAcademicEditTextClass12SchoolAddress;
    @Bind(R.id.academic_name_of_school_class_12_edit_text)
    EditText mAcademicEditTextClass12SchoolName;
    @Bind(R.id.academic_school_board_class_12_edit_text)
    EditText mAcademicEditTextClass12BoardName;

    @Bind(R.id.academic_tab_next_button)
    Button bAcademicDone;

    ArrayAdapter<Integer> mSpinnerYearsClass10ArrayAdapter;
    ArrayAdapter<Integer> mSpinnerYearsClass12ArrayAdapter;
    ArrayAdapter<String> mSpinnerPlacesArrayAdapter;

    private OnAcademicTabSelectedListener mOnAcademicTabSelectedListener;

    private List<String> academicDetailsToParentFragmentList;

    public AcademicChildFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttachFragment(Fragment childFragment) {
        super.onAttachFragment(childFragment);

        try {
            mOnAcademicTabSelectedListener = (OnAcademicTabSelectedListener) childFragment;
        } catch (ClassCastException e) {
            Toast.makeText(getActivity(), childFragment.toString() + " must implement OnAcademicTabSelectedListener", Toast.LENGTH_SHORT).show();
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_academic_child, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        View v = getView();
        ButterKnife.bind(this, v);

        bAcademicDone.setOnClickListener(this);
        academicDetailsToParentFragmentList = new ArrayList<>();

        createSpinnerAdapters();
        setSpinnerAdapters();

    }

    private void setSpinnerAdapters() {
        mAcademicSpinnerClass10Year.setAdapter(mSpinnerYearsClass10ArrayAdapter);
        mAcademicSpinnerClass12Year.setAdapter(mSpinnerYearsClass12ArrayAdapter);
        mAcademicSpinnerClass12Place.setAdapter(mSpinnerPlacesArrayAdapter);

        mAcademicSpinnerClass10Year.setOnItemSelectedListener(this);
        mAcademicSpinnerClass12Year.setOnItemSelectedListener(this);
        mAcademicSpinnerClass12Place.setOnItemSelectedListener(this);
    }

    private void createSpinnerAdapters() {

        mSpinnerYearsClass10ArrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, Constants.listOfYearsForClaas10());
        mSpinnerYearsClass12ArrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, Constants.listOfYearsForClaas12());
        mSpinnerPlacesArrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, Constants.listOfStatesInIndia());

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

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.academic_tab_next_button:

                if (dataIsValid()) {

                    if (mOnAcademicTabSelectedListener != null) {
                        mOnAcademicTabSelectedListener.onAcademicTabSelected(academicDetailsToParentFragmentList);
                    }
                }
                break;
        }

    }

    private boolean dataIsValid() {

        academicDetailsToParentFragmentList.clear();

        academicDetailsToParentFragmentList.add(0, mAcademicSpinnerClass10Year.getSelectedItem().toString());
        academicDetailsToParentFragmentList.add(1, mAcademicSpinnerClass12Year.getSelectedItem().toString());
        academicDetailsToParentFragmentList.add(2, mAcademicSpinnerClass12Place.getSelectedItem().toString());

        /*Board
          Exam
          %
          School Name
          School Address*/
        if (TextUtils.isEmpty(mAcademicEditTextClass12BoardName.getText())) {
            mAcademicEditTextClass12BoardName.setError("Enter valid board name");
            return false;
        } else {
            academicDetailsToParentFragmentList.add(3, mAcademicEditTextClass12BoardName.getText().toString());
        }

        if (TextUtils.isEmpty(mAcademicEditTextClass12NameOfExam.getText())) {
            mAcademicEditTextClass12NameOfExam.setError("Enter valid exam name");
            return false;
        } else {
            academicDetailsToParentFragmentList.add(4, mAcademicEditTextClass12NameOfExam.getText().toString());
        }

        if (TextUtils.isEmpty(mAcademicEditTextClass12Percentage.getText()) || (Double.parseDouble(mAcademicEditTextClass12Percentage.getText().toString()) < 0) || (Double.parseDouble(mAcademicEditTextClass12Percentage.getText().toString()) > 100)) {
            mAcademicEditTextClass12Percentage.setError("Enter valid percentage");
            return false;
        } else {
            academicDetailsToParentFragmentList.add(5, mAcademicEditTextClass12Percentage.getText().toString());
        }

        if (TextUtils.isEmpty(mAcademicEditTextClass12SchoolName.getText())) {
            mAcademicEditTextClass12SchoolName.setError("Enter valid school name");
            return false;
        } else {
            academicDetailsToParentFragmentList.add(6, mAcademicEditTextClass12SchoolName.getText().toString());
        }

        if (TextUtils.isEmpty(mAcademicEditTextClass12SchoolAddress.getText())) {
            mAcademicEditTextClass12SchoolAddress.setError("Enter valid school address");
            return false;
        } else {
            academicDetailsToParentFragmentList.add(7, mAcademicEditTextClass12SchoolAddress.getText().toString());
        }

        if (Long.parseLong(mAcademicSpinnerClass10Year.getSelectedItem().toString()) >= Long.parseLong(mAcademicSpinnerClass12Year.getSelectedItem().toString())) {
            Toast.makeText(getActivity(), "Class 10th year of passing >= Class 12th", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    public interface OnAcademicTabSelectedListener {
        void onAcademicTabSelected(List<String> academicFragmentDetailsList);
    }

}
