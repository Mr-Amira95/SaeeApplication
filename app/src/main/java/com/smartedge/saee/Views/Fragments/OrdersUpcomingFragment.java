package com.smartedge.saee.Views.Fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.smartedge.saee.Networking.Basic.AppConstants;
import com.smartedge.saee.Networking.Basic.MyApplication;
import com.smartedge.saee.Networking.Models.General;
import com.smartedge.saee.Networking.Models.Orders.OrdersResults;
import com.smartedge.saee.Networking.Network.CallBack;
import com.smartedge.saee.R;
import com.smartedge.saee.Views.Adapters.OrdersAdapter;
import com.smartedge.saee.Views.Dialogs.SignatureDialog;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/* loaded from: classes3.dex */
public class OrdersUpcomingFragment extends Fragment implements CallBack {
    public static Button changeStatus;
    public static File fileSignature;
    public static RecyclerView ordersRecyclerview;
    ImageView noData;
    TextView ordersTotal;
    public static ArrayList<String> selectedIDs = new ArrayList<>();
    public static String clientName = "";
    public static String filePathSignature = "";

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getArguments();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_upcoming_orders, container, false);
        initials(view);
        getOrders();
        clicks();
        return view;
    }

    private void clicks() {
        changeStatus.setOnClickListener(new View.OnClickListener() { // from class: com.smartedge.saee.Views.Fragments.OrdersUpcomingFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Context context = OrdersUpcomingFragment.this.getContext();
                OrdersUpcomingFragment ordersUpcomingFragment = OrdersUpcomingFragment.this;
                SignatureDialog signatureDialog = new SignatureDialog(context, ordersUpcomingFragment, ordersUpcomingFragment.getActivity(), "upcoming");
                signatureDialog.show();
            }
        });
    }

    private void initials(View view) {
        changeStatus = (Button) view.findViewById(R.id.change_status);
        this.noData = (ImageView) view.findViewById(R.id.no_data);
        this.ordersTotal = (TextView) view.findViewById(R.id.orders_total);
        ordersRecyclerview = (RecyclerView) view.findViewById(R.id.orders_recyclerview);
        LinearLayoutManager discountsLinearLayoutManager = new LinearLayoutManager(getActivity());
        discountsLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        ordersRecyclerview.setLayoutManager(discountsLinearLayoutManager);
    }

    public static void changeStatus(Context context, CallBack callBack) {
        MultipartBody.Part[] images = new MultipartBody.Part[1];
        if (filePathSignature.isEmpty()) {
            Toast.makeText(context, context.getString(R.string.please_double_check_the_signature_and_save_it), Toast.LENGTH_SHORT).show();
            return;
        }
        fileSignature = new File(filePathSignature);
        RequestBody surveyBody = RequestBody.create(MediaType.parse("image/*"), fileSignature);
        MultipartBody.Part body = MultipartBody.Part.createFormData("signature_client", fileSignature.getPath(), surveyBody);
        images[0] = body;
        Map<String, RequestBody> params = new HashMap<>();
        for (int i = 0; i < selectedIDs.size(); i++) {
            params.put("ids[" + i + "]", RequestBody.create(MediaType.parse("text/plain"), selectedIDs.get(i)));
        }

        params.put("status", RequestBody.create(MediaType.parse("text/plain"), "received"));
        MyApplication.getInstance().getHttpHelper().setCallback(callBack);
        MyApplication.getInstance().getHttpHelper().postImages(context, AppConstants.Change_Status_URL, AppConstants.Change_Status_TAG, General.class, params, images);
    }

    private void getOrders() {
        HashMap<String, Object> params = new HashMap<>();
        MyApplication.getInstance().getHttpHelper().setCallback(this);
        MyApplication.getInstance().getHttpHelper().get(getContext(), AppConstants.Today_Orders_URL, AppConstants.Today_Orders_TAG, OrdersResults.class, params);
    }

    @Override
    public void onSuccess(int tag, boolean isSuccess, Object result) {
        switch (tag) {
            case AppConstants.Change_Status_TAG:
                General general = (General) result;
                Toast.makeText(getContext(), general.getMessage(), Toast.LENGTH_SHORT).show();
                getOrders();
            case AppConstants.Today_Orders_TAG:
                changeStatus.setVisibility(View.GONE);
                OrdersResults ordersResults = (OrdersResults) result;

                OrdersAdapter ordersAdapter = new OrdersAdapter(getContext(), ordersResults.getData().getItems(), "upcoming");
                ordersRecyclerview.setAdapter(ordersAdapter);

                if (!ordersResults.getStatus()) {
                    noData.setVisibility(View.VISIBLE);
                    Toast.makeText(getContext(), ordersResults.getMessage(), Toast.LENGTH_SHORT).show();
                } else if (ordersResults.getData().getItems().size() == 0) {
                    noData.setVisibility(View.VISIBLE);
                    Toast.makeText(getContext(), ordersResults.getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    noData.setVisibility(View.GONE);
                    ordersTotal.setText(ordersResults.getData().getItems().size() + " " + getString(R.string.orders));
                    selectedIDs.clear();
                }
            default:
        }
    }

    @Override
    public void onFailure(int tag, Object result) {
        this.noData.setVisibility(View.VISIBLE);
        changeStatus.setVisibility(View.GONE);
        try {
            OrdersResults ordersResults = (OrdersResults) result;
            Toast.makeText(getContext(), ordersResults.getMessage(), Toast.LENGTH_SHORT).show();
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
}
