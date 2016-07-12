package com.coppermobile.form;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.coppermobile.form.FormChildFragments.AcademicChildFragment;
import com.coppermobile.form.FormChildFragments.JEEChildFragment;
import com.coppermobile.form.FormChildFragments.ParentsChildFragment;
import com.coppermobile.form.FormChildFragments.PersonalChildFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * @author Rohan Taneja\
 *         Form Filling
 */
public class FormFragment extends Fragment implements View.OnClickListener, PersonalChildFragment.OnPersonalTabSelectedListener, ParentsChildFragment.OnParentsTabSelectedListener, AcademicChildFragment.OnAcademicTabSelectedListener, JEEChildFragment.OnJEETabSelectedListener {

    @Bind(R.id.buttonPersonal)
    Button bPersonal;
    @Bind(R.id.buttonParents)
    Button bParents;
    @Bind(R.id.buttonAcademic)
    Button bAcademic;
    @Bind(R.id.buttonJEE)
    Button bJEE;

    private List<String> addToDatabaseList;
    private UserModel loggedInUser;

    private boolean allPersonalDetailsEntered;
    private boolean allParentsDetailsEntered;
    private boolean allAcademicDetailsEntered;
    private boolean allJEEDetailsEntered;

    private Bundle loginIDBundle;

    public FormFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_form, container, false);
    }

    private void getLoggedInUser() {
        loginIDBundle = getArguments();

        for (UserModel x : UserModel.listAll(UserModel.class)) {
            if (x.getmUserEmail().equals(loginIDBundle.getString(Constants.LOGGED_IN_USER_EMAIL_ID))) {
                loggedInUser = UserModel.findById(UserModel.class, x.getId());
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        View v = getView();
        ButterKnife.bind(this, v);
        initialize();
    }

    private void initialize() {

        getActivity().setTitle("Form");

        getLoggedInUser();

        addToDatabaseList = new ArrayList<>();

        allPersonalDetailsEntered = false;
        allParentsDetailsEntered = false;
        allAcademicDetailsEntered = false;
        allJEEDetailsEntered = false;

        bPersonal.setOnClickListener(this);
        bParents.setOnClickListener(this);
        bAcademic.setOnClickListener(this);
        bJEE.setOnClickListener(this);

        PersonalChildFragment personalChildFragment = new PersonalChildFragment();
        getChildFragmentManager().beginTransaction().replace(R.id.child_fragment_frame_layout, personalChildFragment).commit();
        bPersonal.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.button_rounded_white_border, null));

        bAcademic.setClickable(false);
        bParents.setClickable(false);
        bJEE.setClickable(false);
    }

    @Override
    public void onClick(View v) {

//        switch (v.getId()) {
//
//            case R.id.buttonPersonal:
//                PersonalChildFragment personalChildFragment = new PersonalChildFragment();
//                getChildFragmentManager().beginTransaction().replace(R.id.child_fragment_frame_layout, personalChildFragment).commit();
////                bPersonal.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.button_rounded_white_border, null));
//
//                break;
//
//            case R.id.buttonParents:
//                ParentsChildFragment parentsChildFragment = new ParentsChildFragment();
//                getChildFragmentManager().beginTransaction().replace(R.id.child_fragment_frame_layout, parentsChildFragment).commit();
////                bParents.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.button_rounded_white_border, null));
//
//                break;
//
//            case R.id.buttonAcademic:
//                AcademicChildFragment academicChildFragment = new AcademicChildFragment();
//                getChildFragmentManager().beginTransaction().replace(R.id.child_fragment_frame_layout, academicChildFragment).commit();
//                bAcademic.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.button_rounded_white_border, null));
//
//                break;
//
//            case R.id.buttonJEE:
//
//                JEEChildFragment jeeChildFragment = new JEEChildFragment();
//                getChildFragmentManager().beginTransaction().replace(R.id.child_fragment_frame_layout, jeeChildFragment).commit();
//                bJEE.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.button_rounded_white_border, null));
//
//                break;
//        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.save:

                if (dataFromAllTabsReceived()) {
                    storeAllDataInDatabase();
                    Toast.makeText(getActivity(), "All details saved.", Toast.LENGTH_SHORT).show();
                    loggedInUser.setFormsDetailsSaved(true);
                    loggedInUser.save();

                    DetailsFragment detailsFragment = new DetailsFragment();

                    detailsFragment.setArguments(loginIDBundle);
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_for_fragments, detailsFragment).commit();
                }

                break;

            case R.id.logout:

                SignInFragment signUpFragment = new SignInFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_for_fragments, signUpFragment).commit();
                //Empty sharedPreferences, go back to login fragment, loggedInUser = null
                Toast.makeText(getActivity(), "Logout Clicked", Toast.LENGTH_SHORT).show();
                break;

            case R.id.view_profile:

                ViewProfileFragment viewProfileFragment = new ViewProfileFragment();
                viewProfileFragment.setArguments(loginIDBundle);
                getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.frame_layout_for_fragments, viewProfileFragment).commit();
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    private boolean dataFromAllTabsReceived() {
        if (allPersonalDetailsEntered) {
            if (allParentsDetailsEntered) {
                if (allAcademicDetailsEntered) {
                    if (allJEEDetailsEntered) {
                        return true;
                    } else {
                        Toast.makeText(getActivity(), "JEE Details not saved. Press Save.", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                } else {
                    Toast.makeText(getActivity(), "Academic Details not saved. Press Save.", Toast.LENGTH_SHORT).show();
                    return false;
                }
            } else {
                Toast.makeText(getActivity(), "Parents Details not saved. Press Save.", Toast.LENGTH_SHORT).show();
                return false;
            }
        } else {
            Toast.makeText(getActivity(), "Personal Details not saved. Press Save.", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private void storeAllDataInDatabase() {

        //Personal Tab (10 items)
        loggedInUser.setmUserPersonalCandidateName(addToDatabaseList.get(0));
        loggedInUser.setmUserPersonalStateOfEligibility(addToDatabaseList.get(1));
        loggedInUser.setmUserPersonalGender(addToDatabaseList.get(2));
        loggedInUser.setmUserPersonalCategory(addToDatabaseList.get(3));
        loggedInUser.setmUserPersonalDateOfBirthDate(addToDatabaseList.get(4));
        loggedInUser.setmUserPersonalDateOfBirthMonth(addToDatabaseList.get(5));
        loggedInUser.setmUserPersonalDateOfBirthYear(addToDatabaseList.get(6));
        loggedInUser.setmUserPersonalPersonWithDisability(addToDatabaseList.get(7));
        loggedInUser.setmUserPersonalOnlyGirlChild(addToDatabaseList.get(8));
        loggedInUser.setmUserPersonalNationality(addToDatabaseList.get(9));

        //Parents Tab (16 items)
        loggedInUser.setmUserParentsFatherName(addToDatabaseList.get(10));
        loggedInUser.setmUserParentsFatherEmail(addToDatabaseList.get(11));
        loggedInUser.setmUserParentsFatherQualification(addToDatabaseList.get(12));
        loggedInUser.setmUserParentsFatherOccupation(addToDatabaseList.get(13));
        loggedInUser.setmUserParentsFatherAnnualIncome(addToDatabaseList.get(14));

        loggedInUser.setmUserParentsMotherName(addToDatabaseList.get(15));
        loggedInUser.setmUserParentsMotherEmail(addToDatabaseList.get(16));
        loggedInUser.setmUserParentsMotherQualification(addToDatabaseList.get(17));
        loggedInUser.setmUserParentsMotherOccupation(addToDatabaseList.get(18));
        loggedInUser.setmUserParentsMotherAnnualIncome(addToDatabaseList.get(19));

        loggedInUser.setmUserParentsPermanentResidenceAddress(addToDatabaseList.get(20));
        loggedInUser.setmUserParentsPermanentResidenceLocality(addToDatabaseList.get(21));
        loggedInUser.setmUserParentsPermanentResidenceCityVillageTown(addToDatabaseList.get(22));
        loggedInUser.setmUserParentsPermanentResidenceState(addToDatabaseList.get(23));
        loggedInUser.setmUserParentsPermanentResidencePinCode(addToDatabaseList.get(24));
        loggedInUser.setmUserParentsPermanentResidenceNumberWithStdCode(addToDatabaseList.get(25));

        //Academics (8 items)
        loggedInUser.setmUserAcademicYearOfPassing10th(addToDatabaseList.get(26));
        loggedInUser.setmUserAcademicYearOfPassing12th(addToDatabaseList.get(27));
        loggedInUser.setmUserAcademicPlaceOfExam12th(addToDatabaseList.get(28));
        loggedInUser.setmUserAcademicSchoolBoard12th(addToDatabaseList.get(29));
        loggedInUser.setmUserAcademicExamName12th(addToDatabaseList.get(30));
        loggedInUser.setmUserAcademicAggregatePercentage12th(addToDatabaseList.get(31));
        loggedInUser.setmUserAcademicSchoolName12th(addToDatabaseList.get(32));
        loggedInUser.setmUserAcademicSchoolAddress12th(addToDatabaseList.get(33));

        //JEE (6 items)
        loggedInUser.setmUserJEEScoreMain(addToDatabaseList.get(34));
        loggedInUser.setmUserJEECategoryRankMain(addToDatabaseList.get(35));
        loggedInUser.setmUserJEEOverallRankMain(addToDatabaseList.get(36));
        loggedInUser.setmUserJEEScoreAdvanced(addToDatabaseList.get(37));
        loggedInUser.setmUserJEECategoryRankAdvanced(addToDatabaseList.get(38));
        loggedInUser.setmUserJEEOverallRankAdvanced(addToDatabaseList.get(39));

        loggedInUser.save();
    }

    @Override
    public void onPersonalTabSelected(List<String> personalFragmentDetailsList) {

//        personalFragmentDetailsList.size() = 10
        addToDatabaseList.addAll(0, personalFragmentDetailsList);
        bParents.setClickable(true);
        allPersonalDetailsEntered = true;
        bPersonal.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.button_rounded_green, null));

        ParentsChildFragment parentsChildFragment = new ParentsChildFragment();
        getChildFragmentManager().beginTransaction().replace(R.id.child_fragment_frame_layout, parentsChildFragment).commit();
    }

    @Override
    public void onParentsTabSelected(List<String> parentsFragmentDetailsList) {

//        parentsFragmentDetailsList.size() = 16
        addToDatabaseList.addAll(10, parentsFragmentDetailsList);
        bAcademic.setClickable(true);
        allParentsDetailsEntered = true;
        bParents.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.button_rounded_green, null));

        AcademicChildFragment academicChildFragment = new AcademicChildFragment();
        getChildFragmentManager().beginTransaction().replace(R.id.child_fragment_frame_layout, academicChildFragment).commit();

    }

    @Override
    public void onAcademicTabSelected(List<String> academicFragmentDetailsList) {

//        academicFragmentDetailsList.size() = 8
        addToDatabaseList.addAll(26, academicFragmentDetailsList);
        bJEE.setClickable(true);
        allAcademicDetailsEntered = true;
        bAcademic.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.button_rounded_green, null));


        JEEChildFragment jeeChildFragment = new JEEChildFragment();
        getChildFragmentManager().beginTransaction().replace(R.id.child_fragment_frame_layout, jeeChildFragment).commit();


    }

    @Override
    public void onJEETabSelected(List<String> jeeFragmentDetailsList) {

//        jeeFragmentDetailsList.size() = 6
        addToDatabaseList.addAll(34, jeeFragmentDetailsList);
        allJEEDetailsEntered = true;
        bJEE.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.button_rounded_green, null));

        Toast.makeText(getActivity(), "Click on 'save all data' on top.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_form, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

}
