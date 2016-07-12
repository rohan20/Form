package com.coppermobile.form;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author Rohan Taneja
 */
public class Constants {

    public static final int CHOOSE_PHOTO_FROM_GALLERY = 200;
    public static final int CAMERA_PHOTO = 300;
    public static final int PIC_CROP = 400;
    public static final int REQUEST_CODE_ASK_CAMERA_PERMISSION = 700;
    public static final int REQUEST_CODE_ASK_STORAGE_WRITE_PERMISSION = 800;
    public static final int REQUEST_CODE_ASK_STORAGE_READ_PERMISSION = 900;
    public static final String LOGGED_IN_USER_EMAIL_ID = "email_logged_in_user";
    public static final String IS_A_NUMBER = "^[0-9]*$";
    public static final String MOBILE_PHONE_NUMBER_REGEX = "^(?:(?:\\+|0{0,2})91(\\s*[\\-]\\s*)?|[0]?)?[789]\\d{9}$";

    /**
     * provides list of all dates of a month
     * @return List<Integer>
     */
    public static List<Integer> listOfDates() {

        List<Integer> datesList = new ArrayList<>();
        for (int i = 1; i <= 31; i++) {
            datesList.add(i);
        }

        return datesList;
    }

    /**
     * provides list of all months
     * @return List<String>
     */
    public static List<String> listOfMonths() {
        List<String> monthsList = new ArrayList<>();
        monthsList.add("Jan");
        monthsList.add("Feb");
        monthsList.add("March");
        monthsList.add("April");
        monthsList.add("May");
        monthsList.add("June");
        monthsList.add("July");
        monthsList.add("Aug");
        monthsList.add("Sept");
        monthsList.add("Oct");
        monthsList.add("Nov");
        monthsList.add("Dec");

        return monthsList;
    }

    /**
     * provides list of all years (1900-2000)
     * @return List<Integer>
     */
    public static List<Integer> listOfYears() {

        List<Integer> yearsList = new ArrayList<>();
        for (int i = 2000; i > 1900; i--) {
            yearsList.add(i);
        }

        return yearsList;

    }

    /**
     * provides list of all years (1900-2014)
     * @return List<Integer>
     */
    public static List<Integer> listOfYearsForClaas10() {

        List<Integer> yearsList = new ArrayList<>();
        for (int i = 2014; i > 1900; i--) {
            yearsList.add(i);
        }

        return yearsList;

    }

    /**
     * provides list of all years (1900-2016)
     * @return List<Integer>
     */
    public static List<Integer> listOfYearsForClaas12() {

        List<Integer> yearsList = new ArrayList<>();
        for (int i = 2016; i > 1900; i--) {
            yearsList.add(i);
        }

        return yearsList;

    }

    /**
     * provides list of all states of India
     * @return List<String>
     */
    public static List<String> listOfStatesInIndia() {
        List<String> statesList = new ArrayList<>();

        statesList.add("Andhra Pradesh");
        statesList.add("Arunachal Pradesh");
        statesList.add("Assam");
        statesList.add("Bihar");
        statesList.add("Chhattisgarh");

        statesList.add("Goa");
        statesList.add("Gujarat");
        statesList.add("Haryana");
        statesList.add("Himachal Pradesh");
        statesList.add("Jammu and Kashmir");

        statesList.add("Jharkhand");
        statesList.add("Karnataka");
        statesList.add("Kerala");
        statesList.add("Madhya Pradesh");
        statesList.add("Maharashtra");

        statesList.add("Manipur");
        statesList.add("Meghalaya");
        statesList.add("Mizoram");
        statesList.add("Nagaland");
        statesList.add("New Delhi");

        statesList.add("Orissa");
        statesList.add("Punjab");
        statesList.add("Rajasthan");
        statesList.add("Sikkim");
        statesList.add("Tamil Nadu");

        statesList.add("Telangana");
        statesList.add("Tripura");
        statesList.add("Uttaranchal");
        statesList.add("Uttar Pradesh");
        statesList.add("West Bengal");

        return statesList;
    }

    /**
     * provides list of all countries
     * @return List<String>
     */
    public static List<String> listOfCountries() {

        List<String> countriesList = new ArrayList<>();
        String[] isoCountries = Locale.getISOCountries();
        for (String country : isoCountries) {
            Locale locale = new Locale("en", country);
            countriesList.add(locale.getDisplayCountry());
        }

        return countriesList;
    }

}
