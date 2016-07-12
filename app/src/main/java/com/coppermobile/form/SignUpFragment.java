package com.coppermobile.form;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */

public class SignUpFragment extends Fragment implements View.OnClickListener {

    @Bind(R.id.first_name_sign_up_edit_text)
    EditText mEditTextFirstName;
    @Bind(R.id.middle_name_sign_up_edit_text)
    EditText mEditTextMiddleName;
    @Bind(R.id.last_name_sign_up_edit_text)
    EditText mEditTextLastName;
    @Bind(R.id.email_sign_up_edit_text)
    EditText mEditTextEmail;
    @Bind(R.id.password_sign_up_edit_text)
    EditText mEditTextPassword;
    @Bind(R.id.confirm_password_sign_up_edit_text)
    EditText mEditTextConfirmPassword;
    @Bind(R.id.phone_number_edit_text)
    EditText mEditTextPhoneNumber;
    @Bind(R.id.new_user_image)
    ImageView mUserImageView;
    @Bind(R.id.sign_up_submit_button)
    Button bSignUp;

    private AlphaAnimation blinkImageViewAnimation;
    private Uri imageUri;

    public SignUpFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        View v = getView();
        ButterKnife.bind(this, v);
        mUserImageView.setOnClickListener(this);
        bSignUp.setOnClickListener(this);
        setImageViewAnimation();
    }

    private void setImageViewAnimation() {
        blinkImageViewAnimation = new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
        blinkImageViewAnimation.setDuration(200); // duration - half a second
        blinkImageViewAnimation.setInterpolator(new LinearInterpolator()); // do not alter animation rate
        blinkImageViewAnimation.setRepeatCount(4);
        blinkImageViewAnimation.setRepeatMode(Animation.REVERSE);
    }

    private boolean formHasValidData() {

        if (TextUtils.isEmpty(mEditTextFirstName.getText())) {
            mEditTextFirstName.setError("First Name not filled correctly");
            return false;
        }

        if (TextUtils.isEmpty(mEditTextLastName.getText())) {
            mEditTextLastName.setError("Last Name not filled correctly");
            return false;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(mEditTextEmail.getText().toString()).matches()) {
            mEditTextEmail.setError("Incorrect email format");
            return false;
        }

        for (UserModel x : UserModel.listAll(UserModel.class)) {
            if (x.getmUserEmail().equals(mEditTextEmail.getText().toString())) {
                mEditTextEmail.setError("Email id already exists.");
                return false;
            }
        }

        if (mEditTextPassword.getText().toString().length() < 6) {
            mEditTextPassword.setError("Password must be atleast 6 characters long");
            return false;
        }

        if (!(mEditTextPassword.getText().toString().equals(mEditTextConfirmPassword.getText().toString()))) {
            mEditTextConfirmPassword.setError("Passwords do not match");
            return false;
        }

        for (UserModel x : UserModel.listAll(UserModel.class)) {
            if (x.getmUserPhoneNumber().equals(mEditTextPhoneNumber.getText().toString())) {
                mEditTextPhoneNumber.setError("Mobile number already exists.");
                return false;
            }
        }

        if (!Pattern.matches(Constants.MOBILE_PHONE_NUMBER_REGEX, mEditTextPhoneNumber.getText())) {
            mEditTextPhoneNumber.setError("Invalid phone number");
            return false;
        }

        if (mUserImageView.getDrawable().getConstantState().equals(ResourcesCompat.getDrawable(getResources(), R.drawable.add_user_image, null).getConstantState())) {
            mUserImageView.startAnimation(blinkImageViewAnimation);
            return false;
        }

        return true;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.new_user_image:


                int hasCameraPermission = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA);
                if (hasCameraPermission != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, Constants.REQUEST_CODE_ASK_CAMERA_PERMISSION);
                    return;
                }

                int hasWriteStoragePermission = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
                if (hasWriteStoragePermission != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, Constants.REQUEST_CODE_ASK_STORAGE_WRITE_PERMISSION);
                    return;
                }

                int hasReadStoragePermission = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE);
                if (hasReadStoragePermission != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, Constants.REQUEST_CODE_ASK_STORAGE_READ_PERMISSION);
                    return;
                }


                List<String> choice = new ArrayList<>();
                choice.add("Camera");
                choice.add("Gallery");

                new MaterialDialog.Builder(getActivity())
                        .title("Choose action")
                        .items(choice)
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                                switch (which) {
                                    case 0:
                                        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                                        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                                            File photoFile = null;

                                            try {
                                                photoFile = createImageFile();
                                            } catch (IOException ex) {
                                                // Error occurred while creating the File
                                                Toast.makeText(getActivity(), "Problem saving photo", Toast.LENGTH_SHORT).show();
                                            }
                                            // Continue only if the File was successfully created
                                            if (photoFile != null) {

                                                Uri fileUri = Uri.fromFile(photoFile);

                                                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

                                                startActivityForResult(takePictureIntent, Constants.CAMERA_PHOTO);
                                            }
                                        }

                                        break;

                                    case 1:
                                        Intent chooseFromGallery = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                                        chooseFromGallery.addCategory(Intent.CATEGORY_OPENABLE);
                                        chooseFromGallery.setType("image/*");
                                        startActivityForResult(chooseFromGallery, Constants.CHOOSE_PHOTO_FROM_GALLERY);
                                        break;
                                }
                            }
                        }).show();

                break;

            case R.id.sign_up_submit_button:

                if (formHasValidData()) {
                    //change fragment to login fragment
                    createNewUser();
                    Toast.makeText(getActivity(), "Sign up successful!", Toast.LENGTH_SHORT).show();
                    SignInFragment signInFragment = new SignInFragment();
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_for_fragments, signInFragment).commit();
                    break;
                }
        }
    }

    //TODO -->SHA1 encryption for passwords
    private void createNewUser() {
        UserModel userModel = new UserModel();
        userModel.setmUserFirstName(mEditTextFirstName.getText().toString());
        if (mEditTextMiddleName.getText().toString().isEmpty()) {
            userModel.setmUserMiddleName("");
        } else {
            userModel.setmUserMiddleName(mEditTextMiddleName.getText().toString());
        }
        userModel.setmUserLastName(mEditTextLastName.getText().toString());
        userModel.setmUserEmail(mEditTextEmail.getText().toString());
        userModel.setmUserPassword(mEditTextPassword.getText().toString());
        userModel.setmUserPhoneNumber(mEditTextPhoneNumber.getText().toString());
        userModel.setmUserImage(imageUri.toString());

        userModel.setFormsDetailsSaved(false);
        userModel.save();
    }

    private File createImageFile() throws IOException {
        // Create an image file name

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        if (storageDir != null && !storageDir.exists()) {
            if (!storageDir.mkdirs()) {
                Toast.makeText(getActivity(), "Cannot create file", Toast.LENGTH_SHORT)
                        .show();
            }
        }

        return File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode != AppCompatActivity.RESULT_OK) {
            Toast.makeText(getActivity(), "Error in result (RESULT_OK)", Toast.LENGTH_SHORT).show();
            return;
        }

        if (requestCode == Constants.CHOOSE_PHOTO_FROM_GALLERY && data != null && data.getData() != null) {

            Uri pickedImage = data.getData();
            imageUri = data.getData();
            Picasso.with(getActivity()).load(pickedImage).fit().placeholder(R.drawable.placeholder_image).into(mUserImageView);
            return;

        }

        if (requestCode == Constants.CAMERA_PHOTO && data != null) {

            Uri fileUri = data.getData();
            imageUri = fileUri;
            performCrop(fileUri);
            return;

        }

        if (requestCode == Constants.PIC_CROP) {

            if (data != null) {

                imageUri = data.getData();

                try {
                    Bitmap croppedBitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imageUri);
                    mUserImageView.setImageBitmap(croppedBitmap);
                } catch (IOException e) {
                    Toast.makeText(getActivity(), "Unable to get bitmap from Uri", Toast.LENGTH_SHORT).show();
                }

            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void performCrop(Uri imageUri) {
        Intent cropIntent = new Intent("com.android.camera.action.CROP");
        // indicate image type and Uri
        cropIntent.setDataAndType(imageUri, "image/*");
        // set crop properties
        cropIntent.putExtra("crop", "true");
        // indicate aspect of desired crop
        cropIntent.putExtra("aspectX", 1);
        cropIntent.putExtra("aspectY", 1);
        // indicate output X and Y
        cropIntent.putExtra("outputX", 256);
        cropIntent.putExtra("outputY", 256);
        // retrieve data on return
        cropIntent.putExtra("return-data", true);
        // start the activity - we handle returning in onActivityResult
        startActivityForResult(cropIntent, Constants.PIC_CROP);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case Constants.REQUEST_CODE_ASK_CAMERA_PERMISSION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted

                } else {
                    // Permission Denied
                    Toast.makeText(getActivity(), "Requires Camera Permission", Toast.LENGTH_SHORT).show();
                }

                break;

            case Constants.REQUEST_CODE_ASK_STORAGE_READ_PERMISSION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted

                } else {
                    // Permission Denied
                    Toast.makeText(getActivity(), "Requires Read Storage Permission", Toast.LENGTH_SHORT).show();
                }

                break;

            case Constants.REQUEST_CODE_ASK_STORAGE_WRITE_PERMISSION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted

                } else {
                    // Permission Denied
                    Toast.makeText(getActivity(), "Requires Write Storage Permission", Toast.LENGTH_SHORT).show();
                }

                break;


            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }


    }
}
