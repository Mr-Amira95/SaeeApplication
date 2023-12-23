package com.smartedge.saee.Views.Fragments;

import static android.app.Activity.RESULT_OK;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import com.smartedge.saee.Networking.Basic.AppConstants;
import com.smartedge.saee.Networking.Basic.MyApplication;
import com.smartedge.saee.Networking.Models.General;
import com.smartedge.saee.Networking.Models.OrderDetails.OrderDetails;
import com.smartedge.saee.Networking.Network.CallBack;
import com.smartedge.saee.Views.Dialogs.SignatureDialog;
import com.smartedge.saee.R;
import com.skydoves.powerspinner.IconSpinnerAdapter;
import com.skydoves.powerspinner.IconSpinnerItem;
import com.skydoves.powerspinner.OnSpinnerItemSelectedListener;
import com.skydoves.powerspinner.PowerSpinnerView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import pub.devrel.easypermissions.EasyPermissions;

/* loaded from: classes3.dex */
public class ChangeStatusFragment extends Fragment implements CallBack {
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    public static File file;
    public static String filePath = "";
    public static String filePathSignature = "";
    public static File fileSignature;
    TextView detailsTitle;
    Button idBtn;
    TextView idTitle;
    private Uri imageUri;
    EditText notes;
    OrderDetails orderDetails;
    PowerSpinnerView reason;
    TextView reasonTitle;
    Button signatureBtn;
    TextView signatureTitle;
    PowerSpinnerView status;
    Button submit;

    public ChangeStatusFragment(OrderDetails orderDetails) {
        this.orderDetails = orderDetails;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getArguments();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_change_status, container, false);
        EasyPermissions.requestPermissions(this, getString(R.string.Please_Accept_Permission), 233, "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.CAMERA");
        initials(view);
        clicks();
        return view;
    }


    private void clicks() {

        signatureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignatureDialog signatureDialog = new SignatureDialog(getContext(), ChangeStatusFragment.this, getActivity(), "change");
                signatureDialog.show();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeStatus();
            }
        });

        this.status.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener<Object>() {
            @Override
            public void onItemSelected(int i, Object o, int i1, Object t1) {
                submit.setVisibility(View.VISIBLE);
                switch (status.getText().toString().toLowerCase()){
                    case "received":
                    case "تم الاستلام":
                        reasonTitle.setVisibility(View.GONE);
                        reason.setVisibility(View.GONE);
                        idTitle.setVisibility(View.GONE);
                        idBtn.setVisibility(View.GONE);
                        idBtn.setVisibility(View.GONE);
                        detailsTitle.setVisibility(View.GONE);
                        notes.setVisibility(View.GONE);
                        signatureTitle.setVisibility(View.VISIBLE);
                        signatureBtn.setVisibility(View.VISIBLE);
                        reason.dismiss();
                        break;

                    case "rejected":
                    case "مرفوض":
                        reasonTitle.setVisibility(View.VISIBLE);
                        reason.setVisibility(View.VISIBLE);
                        idTitle.setVisibility(View.GONE);
                        idBtn.setVisibility(View.GONE);
                        idBtn.setVisibility(View.GONE);
                        detailsTitle.setVisibility(View.VISIBLE);
                        notes.setVisibility(View.VISIBLE);
                        signatureTitle.setVisibility(View.GONE);
                        signatureBtn.setVisibility(View.GONE);
                        break;

                    case "delivered":
                    case "تم التوصيل":
                        reasonTitle.setVisibility(View.GONE);
                        reason.setVisibility(View.GONE);
                        idTitle.setVisibility(View.VISIBLE);
                        idBtn.setVisibility(View.VISIBLE);
                        detailsTitle.setVisibility(View.GONE);
                        notes.setVisibility(View.GONE);
                        signatureTitle.setVisibility(View.VISIBLE);
                        signatureBtn.setVisibility(View.VISIBLE);
                        reason.dismiss();
                        break;

                }

            }
        });

        this.idBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                captureImage();
//                openCamera();
//                dispatchTakePictureIntent();
            }
        });
    }

    private void initials(View view) {
        detailsTitle = view.findViewById(R.id.details_title);
        idBtn = view.findViewById(R.id.id_button);
        idTitle = view.findViewById(R.id.id_title);
        notes = view.findViewById(R.id.details_field);
        reason = view.findViewById(R.id.reason_spinner);
        reasonTitle = view.findViewById(R.id.reason_title);
        signatureBtn = view.findViewById(R.id.signature_button);
        signatureTitle = view.findViewById(R.id.signature_title);
        status = view.findViewById(R.id.status_spinner);
        submit = view.findViewById(R.id.submit_button);

        List<IconSpinnerItem> iconSpinnerItems1 = new ArrayList<>();
        iconSpinnerItems1.add(new IconSpinnerItem(getString(R.string.reason_1)));
        iconSpinnerItems1.add(new IconSpinnerItem(getString(R.string.reason_2)));
        iconSpinnerItems1.add(new IconSpinnerItem(getString(R.string.reason_3)));
        iconSpinnerItems1.add(new IconSpinnerItem(getString(R.string.reason_4)));
        iconSpinnerItems1.add(new IconSpinnerItem(getString(R.string.reason_5)));
        IconSpinnerAdapter iconSpinnerAdapter1 = new IconSpinnerAdapter(reason);
        reason.setSpinnerAdapter(iconSpinnerAdapter1);
        reason.setItems(iconSpinnerItems1);

        List<IconSpinnerItem> iconSpinnerItems = new ArrayList<>();

        switch (orderDetails.getData().getItem().getStatus()) {
            case "at_company":
                status.setHint(getString(R.string.at_company));
                iconSpinnerItems.add(new IconSpinnerItem(getString(R.string.delivered)));
                iconSpinnerItems.add(new IconSpinnerItem(getString(R.string.rejected)));
                break;
            case "confirmed":
                status.setHint(getString(R.string.confirmed));
                iconSpinnerItems.add(new IconSpinnerItem(getString(R.string.received)));
                break;

        }

        IconSpinnerAdapter iconSpinnerAdapter = new IconSpinnerAdapter(status);
        status.setSpinnerAdapter(iconSpinnerAdapter);
        status.setItems(iconSpinnerItems);


    }

    public void changeStatus() {

        switch (status.getText().toString().toLowerCase()){
            case "received":
            case "تم الاستلام":
                MultipartBody.Part[] images = new MultipartBody.Part[1];
                if (filePathSignature.isEmpty()) {
                    Toast.makeText(getContext(), getContext().getString(R.string.please_double_check_the_signature_and_save_it), Toast.LENGTH_SHORT).show();
                    return;
                }
                fileSignature = new File(filePathSignature);
                RequestBody surveyBody = RequestBody.create(MediaType.parse("image/*"), fileSignature);
                MultipartBody.Part body = MultipartBody.Part.createFormData("signature_client", fileSignature.getPath(), surveyBody);
                images[0] = body;
                Map<String, RequestBody> params = new HashMap<>();
                params.put("ids[0]", RequestBody.create(MediaType.parse("text/plain"), String.valueOf(orderDetails.getData().getItem().getId())));
                params.put("status", RequestBody.create(MediaType.parse("text/plain"), "received"));

                MyApplication.getInstance().getHttpHelper().setCallback(this);
                MyApplication.getInstance().getHttpHelper().postImages(getContext(), AppConstants.Change_Status_URL, AppConstants.Change_Status_TAG, General.class, params, images);

                break;

            case "rejected":
            case "مرفوض":

                if (reason.getText().toString().isEmpty() || notes.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), getContext().getString(R.string.all_fields_are_required), Toast.LENGTH_SHORT).show();
                    return;
                }

                Map<String, Object> params1 = new HashMap<>();
                params1.put("ids[0]",String.valueOf(orderDetails.getData().getItem().getId()));
                params1.put("status", "rejected");
                params1.put("reason", reason.getText().toString());
                params1.put("note", notes.getText().toString());

                MyApplication.getInstance().getHttpHelper().setCallback(this);
                MyApplication.getInstance().getHttpHelper().post(getContext(), AppConstants.Change_Status_URL, AppConstants.Change_Status_TAG, General.class, params1);

                break;

            case "delivered":
            case "تم التوصيل":
                MultipartBody.Part[] images1 = new MultipartBody.Part[2];
                if (filePathSignature.isEmpty()) {
                    Toast.makeText(getContext(), getContext().getString(R.string.please_double_check_the_signature_and_save_it), Toast.LENGTH_SHORT).show();
                    return;
                } else if (filePath.isEmpty()){
                    Toast.makeText(getContext(), getContext().getString(R.string.please_capture_the_id_image), Toast.LENGTH_SHORT).show();
                    return;
                }

                fileSignature = new File(filePathSignature);
                RequestBody surveyBody1 = RequestBody.create(MediaType.parse("image/*"), fileSignature);
                MultipartBody.Part body1 = MultipartBody.Part.createFormData("signature", fileSignature.getPath(), surveyBody1);
                images1[0] = body1;

                file = new File(filePath);
                RequestBody surveyBody2 = RequestBody.create(MediaType.parse("image/*"), file);
                MultipartBody.Part body2 = MultipartBody.Part.createFormData("id_image", file.getPath(), surveyBody2);
                images1[1] = body2;

                Map<String, RequestBody> params2 = new HashMap<>();
                params2.put("ids[0]", RequestBody.create(MediaType.parse("text/plain"), String.valueOf(orderDetails.getData().getItem().getId())));
                params2.put("status", RequestBody.create(MediaType.parse("text/plain"), "delivered"));

                MyApplication.getInstance().getHttpHelper().setCallback(this);
                MyApplication.getInstance().getHttpHelper().postImages(getContext(), AppConstants.Change_Status_URL, AppConstants.Change_Status_TAG, General.class, params2, images1);

                break;

        }

    }

    @Override
    public void onSuccess(int tag, boolean isSuccess, Object result) {
        General general = (General) result;
        Toast.makeText(getContext(), general.getMessage(), Toast.LENGTH_SHORT).show();
        getFragmentManager().popBackStack();
    }

    @Override
    public void onFailure(int tag, Object result) {
        try {
            General general = (General) result;
            Toast.makeText(getContext(), general.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(getContext(), getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
        }
    }

    public static Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", (String) null);
        return Uri.parse(path);
    }

    public static String getRealPathFromURI(Uri uri, Context context, String name) {
        try {
            InputStream inputStream = context.getContentResolver().openInputStream(uri);
            File file = createTemporalFileFrom(context, inputStream, name + ".png");
            if (file == null) {
                return "";
            }
            String path = file.getPath();
            return path;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private static File createTemporalFileFrom(Context context, InputStream inputStream, String name) {
        if (inputStream == null) {
            return null;
        }
        File file = new File(context.getCacheDir(), name);
        try {
            try {
                OutputStream output = new FileOutputStream(file);
                try {
                    byte[] buffer = new byte[4096];
                    while (true) {
                        int read = inputStream.read(buffer);
                        if (read == -1) {
                            break;
                        }
                        output.write(buffer, 0, read);
                    }
                    output.flush();
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return file;
                } finally {
                    output.close();
                }
            } catch (IOException e2) {
                e2.printStackTrace();
                try {
                    inputStream.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
                return null;
            }
        } catch (Throwable th) {
            try {
                inputStream.close();
            } catch (IOException e4) {
                e4.printStackTrace();
            }
            throw th;
        }
    }

    public void captureImage() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getContext().getPackageManager()) != null) {
            // Create the File where the photo should go
            try {
                file = createImageFile();
            } catch (IOException ex) {
                // Handle errors while creating the file
                ex.printStackTrace();
            }
            // Continue only if the File was successfully created
            if (file != null) {
                Uri photoURI = FileProvider.getUriForFile(getContext(), "com.smartedge.saee.fileprovider", file);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        filePath = image.getAbsolutePath();
        return image;
    }

    @Override
    public void onPause() {
        super.onPause();
        status.dismiss();
        reason.dismiss();
    }
}
