package com.coppermobile.form;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * @author Rohan Taneja
 *         For displaying details of logged in users
 */
public class DetailsFragment extends Fragment {

    @Bind(R.id.recycler_view_personal_details)
    RecyclerView mRecyclerViewPersonal;
    @Bind(R.id.recycler_view_parents_details)
    RecyclerView mRecyclerViewParents;
    @Bind(R.id.recycler_view_academics_details)
    RecyclerView mRecyclerViewAcademic;
    @Bind(R.id.recycler_view_jee_details)
    RecyclerView mRecyclerViewJEE;

    private UserModel loggedInUser;

    private List<String> personalCategoriesList;
    private List<String> parentsCategoriesList;
    private List<String> academicCategoriesList;
    private List<String> jeeCategoriesList;

    private List<String> personalDataList;
    private List<String> parentsDataList;
    private List<String> academicDataList;
    private List<String> jeeDataList;


    public DetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        setHasOptionsMenu(true);
        View v = getView();
        ButterKnife.bind(this, v);

        initialize();

        getLoggedInUser();
        getActivity().setTitle(loggedInUser.getmUserFirstName() + " " + loggedInUser.getmUserLastName() + " - Details");

        setCategoriesLists();
        setDataLists();

        createAndSetAdapters();
    }

    private void setDataLists() {

        //Personal
        personalDataList.add(loggedInUser.getmUserPersonalCandidateName());
        personalDataList.add(loggedInUser.getmUserPersonalStateOfEligibility());
        personalDataList.add(loggedInUser.getmUserPersonalGender());
        personalDataList.add(loggedInUser.getmUserPersonalCategory());
        personalDataList.add(loggedInUser.getmUserPersonalDateOfBirthDate() + " / " + loggedInUser.getmUserPersonalDateOfBirthMonth() + " / " + loggedInUser.getmUserPersonalDateOfBirthYear());
        personalDataList.add(loggedInUser.getmUserPersonalPersonWithDisability());
        personalDataList.add(loggedInUser.getmUserPersonalOnlyGirlChild());
        personalDataList.add(loggedInUser.getmUserPersonalNationality());

        //Parents
        parentsDataList.add(loggedInUser.getmUserParentsFatherName());
        parentsDataList.add(loggedInUser.getmUserParentsFatherEmail());
        parentsDataList.add(loggedInUser.getmUserParentsFatherQualification());
        parentsDataList.add(loggedInUser.getmUserParentsFatherOccupation());
        parentsDataList.add(loggedInUser.getmUserParentsFatherAnnualIncome());

        parentsDataList.add(loggedInUser.getmUserParentsMotherName());
        parentsDataList.add(loggedInUser.getmUserParentsMotherEmail());
        parentsDataList.add(loggedInUser.getmUserParentsMotherQualification());
        parentsDataList.add(loggedInUser.getmUserParentsMotherOccupation());
        parentsDataList.add(loggedInUser.getmUserParentsMotherAnnualIncome());

        parentsDataList.add(loggedInUser.getmUserParentsPermanentResidenceAddress());
        parentsDataList.add(loggedInUser.getmUserParentsPermanentResidenceLocality());
        parentsDataList.add(loggedInUser.getmUserParentsPermanentResidenceCityVillageTown());
        parentsDataList.add(loggedInUser.getmUserParentsPermanentResidenceState());
        parentsDataList.add(loggedInUser.getmUserParentsPermanentResidencePinCode());
        parentsDataList.add(loggedInUser.getmUserParentsPermanentResidenceNumberWithStdCode());

        //Academic
        academicDataList.add(loggedInUser.getmUserAcademicYearOfPassing10th());
        academicDataList.add(loggedInUser.getmUserAcademicYearOfPassing12th());
        academicDataList.add(loggedInUser.getmUserAcademicPlaceOfExam12th());
        academicDataList.add(loggedInUser.getmUserAcademicSchoolBoard12th());
        academicDataList.add(loggedInUser.getmUserAcademicExamName12th());
        academicDataList.add(loggedInUser.getmUserAcademicAggregatePercentage12th());
        academicDataList.add(loggedInUser.getmUserAcademicSchoolName12th());
        academicDataList.add(loggedInUser.getmUserAcademicSchoolAddress12th());

        //JEE
        jeeDataList.add(loggedInUser.getmUserJEEScoreMain());
        jeeDataList.add(loggedInUser.getmUserJEECategoryRankMain());
        jeeDataList.add(loggedInUser.getmUserJEEOverallRankMain());
        jeeDataList.add(loggedInUser.getmUserJEEScoreAdvanced());
        jeeDataList.add(loggedInUser.getmUserJEECategoryRankAdvanced());
        jeeDataList.add(loggedInUser.getmUserJEEOverallRankAdvanced());

        loggedInUser.save();
    }

    private void setCategoriesLists() {

        //Personal
        personalCategoriesList.add("Candidate Name");
        personalCategoriesList.add("State of Eligibility");
        personalCategoriesList.add("Gender");
        personalCategoriesList.add("Category");
        personalCategoriesList.add("Date of Birth");
        personalCategoriesList.add("Person with disability (PwD)");
        personalCategoriesList.add("Only girl child of parents");
        personalCategoriesList.add("Nationality");

        //Parents
        parentsCategoriesList.add("Father's Name");
        parentsCategoriesList.add("Father's Email");
        parentsCategoriesList.add("Father's Qualification");
        parentsCategoriesList.add("Father's Occupation");
        parentsCategoriesList.add("Father's Annual Income");

        parentsCategoriesList.add("Mother's Name");
        parentsCategoriesList.add("Mother's Email");
        parentsCategoriesList.add("Mother's Qualification");
        parentsCategoriesList.add("Mother's Occupation");
        parentsCategoriesList.add("Mother's Annual Income");

        parentsCategoriesList.add("Address");
        parentsCategoriesList.add("Locality");
        parentsCategoriesList.add("City/Town/Village");
        parentsCategoriesList.add("State");
        parentsCategoriesList.add("PIN Code");
        parentsCategoriesList.add("Number with STD code");

        //Academic
        academicCategoriesList.add("Year of passing (Class 10th)");
        academicCategoriesList.add("Year of passing (Class 12th)");
        academicCategoriesList.add("Place of exam (Class 12th)");
        academicCategoriesList.add("School Board (Class 12th)");
        academicCategoriesList.add("Exam Name (Class 12th)");
        academicCategoriesList.add("Aggregate Percentage (Class 12th)");
        academicCategoriesList.add("Name of school (Class 12th)");
        academicCategoriesList.add("Address of school (Class 12th)");

        //JEE
        jeeCategoriesList.add("JEE Main - Score");
        jeeCategoriesList.add("JEE Main - Category Rank");
        jeeCategoriesList.add("JEE Main - Overall Rank");

        jeeCategoriesList.add("JEE Advanced - Score");
        jeeCategoriesList.add("JEE Advanced - Category Rank");
        jeeCategoriesList.add("JEE Advanced - Overall Rank");

    }

    private void initialize() {
        personalCategoriesList = new ArrayList<>();
        parentsCategoriesList = new ArrayList<>();
        academicCategoriesList = new ArrayList<>();
        jeeCategoriesList = new ArrayList<>();

        personalDataList = new ArrayList<>();
        parentsDataList = new ArrayList<>();
        academicDataList = new ArrayList<>();
        jeeDataList = new ArrayList<>();
    }

    private void getLoggedInUser() {

        for (UserModel x : UserModel.listAll(UserModel.class)) {
            if (x.getmUserEmail().equals(getArguments().getString(Constants.LOGGED_IN_USER_EMAIL_ID))) {
                loggedInUser = UserModel.findById(UserModel.class, x.getId());
            }
        }

    }

    private void createAndSetAdapters() {

        List<DetailsItem> detailsPersonalList = new ArrayList<>();
        List<DetailsItem> detailsParentsList = new ArrayList<>();
        List<DetailsItem> detailsAcademicList = new ArrayList<>();
        List<DetailsItem> detailsJEEList = new ArrayList<>();

        for (int i = 0; i < personalDataList.size(); i++) {
            detailsPersonalList.add(new DetailsItem(personalCategoriesList.get(i), personalDataList.get(i)));
        }

        for (int i = 0; i < parentsDataList.size(); i++) {
            detailsParentsList.add(new DetailsItem(parentsCategoriesList.get(i), parentsDataList.get(i)));
        }

        for (int i = 0; i < academicDataList.size(); i++) {
            detailsAcademicList.add(new DetailsItem(academicCategoriesList.get(i), academicDataList.get(i)));
        }

        for (int i = 0; i < jeeDataList.size(); i++) {
            detailsJEEList.add(new DetailsItem(jeeCategoriesList.get(i), jeeDataList.get(i)));
        }

        LinearLayoutManager linearLayoutManagerPersonal = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        LinearLayoutManager linearLayoutManagerParents = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        LinearLayoutManager linearLayoutManagerAcademic = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        LinearLayoutManager linearLayoutManagerJEE = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        DetailsFragmentArrayAdapter mDetailsRecyclerViewArrayAdapter;

        mDetailsRecyclerViewArrayAdapter = new DetailsFragmentArrayAdapter(getActivity(), detailsPersonalList);
        mRecyclerViewPersonal.setAdapter(mDetailsRecyclerViewArrayAdapter);
        mRecyclerViewPersonal.setLayoutManager(linearLayoutManagerPersonal);

        mDetailsRecyclerViewArrayAdapter = new DetailsFragmentArrayAdapter(getActivity(), detailsParentsList);
        mRecyclerViewParents.setAdapter(mDetailsRecyclerViewArrayAdapter);
        mRecyclerViewParents.setLayoutManager(linearLayoutManagerParents);

        mDetailsRecyclerViewArrayAdapter = new DetailsFragmentArrayAdapter(getActivity(), detailsAcademicList);
        mRecyclerViewAcademic.setAdapter(mDetailsRecyclerViewArrayAdapter);
        mRecyclerViewAcademic.setLayoutManager(linearLayoutManagerAcademic);

        mDetailsRecyclerViewArrayAdapter = new DetailsFragmentArrayAdapter(getActivity(), detailsJEEList);
        mRecyclerViewJEE.setAdapter(mDetailsRecyclerViewArrayAdapter);
        mRecyclerViewJEE.setLayoutManager(linearLayoutManagerJEE);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_detail, menu);
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.detail_logout:

                SignInFragment signUpFragment = new SignInFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_for_fragments, signUpFragment).commit();
                Toast.makeText(getActivity(), "Logged Out", Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
