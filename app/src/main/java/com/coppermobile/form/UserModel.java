package com.coppermobile.form;

import com.orm.SugarRecord;

/**
 * @author Rohan Taneja
 */
public class UserModel extends SugarRecord {

    private boolean detailsSaved;

    //sign up
    private String mUserFirstName;
    private String mUserMiddleName;
    private String mUserLastName;
    private String mUserEmail;
    private String mUserPassword;
    private String mUserPhoneNumber;
    private String mUserImage;

    //personal child fragment
    private String mUserPersonalCandidateName;
    private String mUserPersonalStateOfEligibility;
    private String mUserPersonalGender;
    private String mUserPersonalCategory;
    private String mUserPersonalDateOfBirthDate;
    private String mUserPersonalDateOfBirthMonth;
    private String mUserPersonalDateOfBirthYear;
    private String mUserPersonalPersonWithDisability;
    private String mUserPersonalOnlyGirlChild;
    private String mUserPersonalNationality;

    //parents child fragment
    private String mUserParentsFatherName;
    private String mUserParentsFatherEmail;
    private String mUserParentsFatherQualification;
    private String mUserParentsFatherOccupation;
    private String mUserParentsFatherAnnualIncome;

    private String mUserParentsMotherName;
    private String mUserParentsMotherEmail;
    private String mUserParentsMotherQualification;
    private String mUserParentsMotherOccupation;
    private String mUserParentsMotherAnnualIncome;

    private String mUserParentsPermanentResidenceAddress;
    private String mUserParentsPermanentResidenceLocality;
    private String mUserParentsPermanentResidenceState;
    private String mUserParentsPermanentResidenceCityVillageTown;
    private String mUserParentsPermanentResidencePinCode;
    private String mUserParentsPermanentResidenceNumberWithStdCode;

    //academic child fragment
    private String mUserAcademicYearOfPassing10th;
    private String mUserAcademicYearOfPassing12th;
    private String mUserAcademicPlaceOfExam12th;
    private String mUserAcademicSchoolBoard12th;
    private String mUserAcademicSchoolAddress12th;
    private String mUserAcademicSchoolName12th;
    private String mUserAcademicExamName12th;
    private String mUserAcademicAggregatePercentage12th;

    //jee child fragment
    private String mUserJEEScoreMain;
    private String mUserJEECategoryRankMain;
    private String mUserJEEOverallRankMain;

    private String mUserJEEScoreAdvanced;
    private String mUserJEECategoryRankAdvanced;
    private String mUserJEEOverallRankAdvanced;

    public UserModel() {

    }

    //Setters

    public void setFormsDetailsSaved(boolean detailsSaved) {
        this.detailsSaved = detailsSaved;
    }

    public void setmUserFirstName(String mUserFirstName) {
        this.mUserFirstName = mUserFirstName;
    }

    public void setmUserMiddleName(String mUserMiddleName) {
        this.mUserMiddleName = mUserMiddleName;
    }

    public void setmUserLastName(String mUserLastName) {
        this.mUserLastName = mUserLastName;
    }

    public void setmUserEmail(String mUserEmail) {
        this.mUserEmail = mUserEmail;
    }

    public void setmUserPassword(String mUserPassword) {
        this.mUserPassword = mUserPassword;
    }

    public void setmUserPhoneNumber(String mUserPhoneNumber) {
        this.mUserPhoneNumber = mUserPhoneNumber;
    }

    public void setmUserImage(String mUserImage) {
        this.mUserImage = mUserImage;
    }

    public void setmUserPersonalCandidateName(String mUserPersonalCandidateName) {
        this.mUserPersonalCandidateName = mUserPersonalCandidateName;
    }

    public void setmUserPersonalStateOfEligibility(String mUserPersonalStateOfEligibility) {
        this.mUserPersonalStateOfEligibility = mUserPersonalStateOfEligibility;
    }

    public void setmUserPersonalGender(String mUserPersonalGender) {
        this.mUserPersonalGender = mUserPersonalGender;
    }

    public void setmUserPersonalCategory(String mUserPersonalCategory) {
        this.mUserPersonalCategory = mUserPersonalCategory;
    }

    public void setmUserPersonalDateOfBirthDate(String mUserPersonalDateOfBirthDate) {
        this.mUserPersonalDateOfBirthDate = mUserPersonalDateOfBirthDate;
    }

    public void setmUserPersonalDateOfBirthMonth(String mUserPersonalDateOfBirthMonth) {
        this.mUserPersonalDateOfBirthMonth = mUserPersonalDateOfBirthMonth;
    }

    public void setmUserPersonalDateOfBirthYear(String mUserPersonalDateOfBirthYear) {
        this.mUserPersonalDateOfBirthYear = mUserPersonalDateOfBirthYear;
    }

    public void setmUserPersonalPersonWithDisability(String mUserPersonalPersonWithDisability) {
        this.mUserPersonalPersonWithDisability = mUserPersonalPersonWithDisability;
    }

    public void setmUserPersonalOnlyGirlChild(String mUserPersonalOnlyGirlChild) {
        this.mUserPersonalOnlyGirlChild = mUserPersonalOnlyGirlChild;
    }

    public void setmUserPersonalNationality(String mUserPersonalNationality) {
        this.mUserPersonalNationality = mUserPersonalNationality;
    }

    public void setmUserParentsFatherName(String mUserParentsFatherName) {
        this.mUserParentsFatherName = mUserParentsFatherName;
    }

    public void setmUserParentsFatherEmail(String mUserParentsFatherEmail) {
        this.mUserParentsFatherEmail = mUserParentsFatherEmail;
    }

    public void setmUserParentsFatherQualification(String mUserParentsFatherQualification) {
        this.mUserParentsFatherQualification = mUserParentsFatherQualification;
    }

    public void setmUserParentsFatherOccupation(String mUserParentsFatherOccupation) {
        this.mUserParentsFatherOccupation = mUserParentsFatherOccupation;
    }

    public void setmUserParentsFatherAnnualIncome(String mUserParentsFatherAnnualIncome) {
        this.mUserParentsFatherAnnualIncome = mUserParentsFatherAnnualIncome;
    }

    public void setmUserParentsMotherName(String mUserParentsMotherName) {
        this.mUserParentsMotherName = mUserParentsMotherName;
    }

    public void setmUserParentsMotherEmail(String mUserParentsMotherEmail) {
        this.mUserParentsMotherEmail = mUserParentsMotherEmail;
    }

    public void setmUserParentsMotherQualification(String mUserParentsMotherQualification) {
        this.mUserParentsMotherQualification = mUserParentsMotherQualification;
    }

    public void setmUserParentsMotherOccupation(String mUserParentsMotherOccupation) {
        this.mUserParentsMotherOccupation = mUserParentsMotherOccupation;
    }

    public void setmUserParentsMotherAnnualIncome(String mUserParentsMotherAnnualIncome) {
        this.mUserParentsMotherAnnualIncome = mUserParentsMotherAnnualIncome;
    }

    public void setmUserParentsPermanentResidenceAddress(String mUserParentsPermanentResidenceAddress) {
        this.mUserParentsPermanentResidenceAddress = mUserParentsPermanentResidenceAddress;
    }

    public void setmUserParentsPermanentResidenceLocality(String mUserParentsPermanentResidenceLocality) {
        this.mUserParentsPermanentResidenceLocality = mUserParentsPermanentResidenceLocality;
    }

    public void setmUserParentsPermanentResidenceState(String mUserParentsPermanentResidenceState) {
        this.mUserParentsPermanentResidenceState = mUserParentsPermanentResidenceState;
    }

    public void setmUserParentsPermanentResidenceCityVillageTown(String mUserParentsPermanentResidenceCityVillageTown) {
        this.mUserParentsPermanentResidenceCityVillageTown = mUserParentsPermanentResidenceCityVillageTown;
    }

    public void setmUserParentsPermanentResidencePinCode(String mUserParentsPermanentResidencePinCode) {
        this.mUserParentsPermanentResidencePinCode = mUserParentsPermanentResidencePinCode;
    }

    public void setmUserParentsPermanentResidenceNumberWithStdCode(String mUserParentsPermanentResidenceNumberWithStdCode) {
        this.mUserParentsPermanentResidenceNumberWithStdCode = mUserParentsPermanentResidenceNumberWithStdCode;
    }

    public void setmUserAcademicYearOfPassing10th(String mUserAcademicYearOfPassing10th) {
        this.mUserAcademicYearOfPassing10th = mUserAcademicYearOfPassing10th;
    }

    public void setmUserAcademicYearOfPassing12th(String mUserAcademicYearOfPassing12th) {
        this.mUserAcademicYearOfPassing12th = mUserAcademicYearOfPassing12th;
    }

    public void setmUserAcademicPlaceOfExam12th(String mUserAcademicPlaceOfExam12th) {
        this.mUserAcademicPlaceOfExam12th = mUserAcademicPlaceOfExam12th;
    }

    public void setmUserAcademicSchoolBoard12th(String mUserAcademicSchoolBoard12th) {
        this.mUserAcademicSchoolBoard12th = mUserAcademicSchoolBoard12th;
    }

    public void setmUserAcademicSchoolAddress12th(String mUserAcademicSchoolAddress12th) {
        this.mUserAcademicSchoolAddress12th = mUserAcademicSchoolAddress12th;
    }

    public void setmUserAcademicSchoolName12th(String mUserAcademicSchoolName12th) {
        this.mUserAcademicSchoolName12th = mUserAcademicSchoolName12th;
    }

    public void setmUserAcademicExamName12th(String mUserAcademicExamName12th) {
        this.mUserAcademicExamName12th = mUserAcademicExamName12th;
    }

    public void setmUserAcademicAggregatePercentage12th(String mUserAcademicAggregatePercentage12th) {
        this.mUserAcademicAggregatePercentage12th = mUserAcademicAggregatePercentage12th;
    }

    public void setmUserJEEScoreMain(String mUserJEEScoreMain) {
        this.mUserJEEScoreMain = mUserJEEScoreMain;
    }

    public void setmUserJEECategoryRankMain(String mUserJEECategoryRankMain) {
        this.mUserJEECategoryRankMain = mUserJEECategoryRankMain;
    }

    public void setmUserJEEOverallRankMain(String mUserJEEOverallRankMain) {
        this.mUserJEEOverallRankMain = mUserJEEOverallRankMain;
    }

    public void setmUserJEEScoreAdvanced(String mUserJEEScoreAdvanced) {
        this.mUserJEEScoreAdvanced = mUserJEEScoreAdvanced;
    }

    public void setmUserJEECategoryRankAdvanced(String mUserJEECategoryRankAdvanced) {
        this.mUserJEECategoryRankAdvanced = mUserJEECategoryRankAdvanced;
    }

    public void setmUserJEEOverallRankAdvanced(String mUserJEEOverallRankAdvanced) {
        this.mUserJEEOverallRankAdvanced = mUserJEEOverallRankAdvanced;
    }

    //Getters
    public boolean isDetailsSaved() {
        return detailsSaved;
    }

    public String getmUserFirstName() {
        return mUserFirstName;
    }

    public String getmUserMiddleName() {
        return mUserMiddleName;
    }

    public String getmUserLastName() {
        return mUserLastName;
    }

    public String getmUserEmail() {
        return mUserEmail;
    }

    public String getmUserPassword() {
        return mUserPassword;
    }

    public String getmUserPhoneNumber() {
        return mUserPhoneNumber;
    }

    public String getmUserImage() {
        return mUserImage;
    }

    public String getmUserPersonalCandidateName() {
        return mUserPersonalCandidateName;
    }

    public String getmUserPersonalStateOfEligibility() {
        return mUserPersonalStateOfEligibility;
    }

    public String getmUserPersonalGender() {
        return mUserPersonalGender;
    }

    public String getmUserPersonalCategory() {
        return mUserPersonalCategory;
    }

    public String getmUserPersonalDateOfBirthDate() {
        return mUserPersonalDateOfBirthDate;
    }

    public String getmUserPersonalDateOfBirthMonth() {
        return mUserPersonalDateOfBirthMonth;
    }

    public String getmUserPersonalDateOfBirthYear() {
        return mUserPersonalDateOfBirthYear;
    }

    public String getmUserPersonalPersonWithDisability() {
        return mUserPersonalPersonWithDisability;
    }

    public String getmUserPersonalOnlyGirlChild() {
        return mUserPersonalOnlyGirlChild;
    }

    public String getmUserPersonalNationality() {
        return mUserPersonalNationality;
    }

    public String getmUserParentsFatherName() {
        return mUserParentsFatherName;
    }

    public String getmUserParentsFatherEmail() {
        return mUserParentsFatherEmail;
    }

    public String getmUserParentsFatherQualification() {
        return mUserParentsFatherQualification;
    }

    public String getmUserParentsFatherOccupation() {
        return mUserParentsFatherOccupation;
    }

    public String getmUserParentsFatherAnnualIncome() {
        return mUserParentsFatherAnnualIncome;
    }

    public String getmUserParentsMotherName() {
        return mUserParentsMotherName;
    }

    public String getmUserParentsMotherEmail() {
        return mUserParentsMotherEmail;
    }

    public String getmUserParentsMotherQualification() {
        return mUserParentsMotherQualification;
    }

    public String getmUserParentsMotherOccupation() {
        return mUserParentsMotherOccupation;
    }

    public String getmUserParentsMotherAnnualIncome() {
        return mUserParentsMotherAnnualIncome;
    }

    public String getmUserParentsPermanentResidenceAddress() {
        return mUserParentsPermanentResidenceAddress;
    }

    public String getmUserParentsPermanentResidenceLocality() {
        return mUserParentsPermanentResidenceLocality;
    }

    public String getmUserParentsPermanentResidenceState() {
        return mUserParentsPermanentResidenceState;
    }

    public String getmUserParentsPermanentResidenceCityVillageTown() {
        return mUserParentsPermanentResidenceCityVillageTown;
    }

    public String getmUserParentsPermanentResidencePinCode() {
        return mUserParentsPermanentResidencePinCode;
    }

    public String getmUserParentsPermanentResidenceNumberWithStdCode() {
        return mUserParentsPermanentResidenceNumberWithStdCode;
    }

    public String getmUserAcademicYearOfPassing10th() {
        return mUserAcademicYearOfPassing10th;
    }

    public String getmUserAcademicYearOfPassing12th() {
        return mUserAcademicYearOfPassing12th;
    }

    public String getmUserAcademicPlaceOfExam12th() {
        return mUserAcademicPlaceOfExam12th;
    }

    public String getmUserAcademicSchoolBoard12th() {
        return mUserAcademicSchoolBoard12th;
    }

    public String getmUserAcademicSchoolAddress12th() {
        return mUserAcademicSchoolAddress12th;
    }

    public String getmUserAcademicSchoolName12th() {
        return mUserAcademicSchoolName12th;
    }

    public String getmUserAcademicExamName12th() {
        return mUserAcademicExamName12th;
    }

    public String getmUserAcademicAggregatePercentage12th() {
        return mUserAcademicAggregatePercentage12th;
    }

    public String getmUserJEEScoreMain() {
        return mUserJEEScoreMain;
    }

    public String getmUserJEECategoryRankMain() {
        return mUserJEECategoryRankMain;
    }

    public String getmUserJEEOverallRankMain() {
        return mUserJEEOverallRankMain;
    }

    public String getmUserJEEScoreAdvanced() {
        return mUserJEEScoreAdvanced;
    }

    public String getmUserJEECategoryRankAdvanced() {
        return mUserJEECategoryRankAdvanced;
    }

    public String getmUserJEEOverallRankAdvanced() {
        return mUserJEEOverallRankAdvanced;
    }
}
