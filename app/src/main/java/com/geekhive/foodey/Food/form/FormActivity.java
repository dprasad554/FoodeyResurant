package com.geekhive.foodey.Food.form;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.media.ExifInterface;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;

import com.geekhive.foodey.Food.beans.bookservice.BookService;
import com.geekhive.foodey.Food.home.HomeActivity;
import com.geekhive.foodey.Food.utils.OnResponseListner;
import com.geekhive.foodey.Food.utils.Prefs;
import com.geekhive.foodey.Food.utils.WebServices;
import com.google.android.material.snackbar.Snackbar;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;

import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import com.geekhive.foodey.Food.service.ServiceActivity;
import com.geekhive.foodey.Food.utils.ConnectionDetector;
import com.geekhive.foodey.Food.utils.SnackBar;
import com.geekhive.foodey.R;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.StringTokenizer;

import butterknife.ButterKnife;

import static com.geekhive.foodey.Food.utils.util.IMAGE_DIRECTORY_NAME;
import static com.geekhive.foodey.Food.utils.util.REQUEST_TAKE_PHOTO_IMAGE_FRONT;
import static com.geekhive.foodey.Food.utils.util.SELECT_FILE_IMAGE_FRONT;


public class FormActivity extends AppCompatActivity implements View.OnClickListener, OnResponseListner {

    ImageView calender, time, vI_service_back;
    TextView showDate, showTime;
    EditText et_DBFirstName, et_DBMobile, et_DBEmail, et_problem;
    DatePickerDialog.OnDateSetListener setListenerDate;
    TimePickerDialog.OnTimeSetListener setListenerTime;
    ImageView iv_AddImage, iv_uploadImage;
    Bitmap bitmap;
    Bitmap bitmapFromGallery;
    Uri fileUri;
    String mFileNameGallery;
    private String uriSting;
    TextView et_service_type, tv_DBDate, tv_DBTime;

    Button vB_submit;

    DialogInterface dialog;

    private String filePathPhotoFront = "";
    String fileNameFront;


    public static final int REQUEST_FOR_STORAGE_PERMISSION = 123;

    ConnectionDetector mDetector;
    String itemname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        ButterKnife.bind(this);


        calender = (ImageView) findViewById(R.id.vI_calender);
        time = (ImageView) findViewById(R.id.vI_time);
        showDate = (TextView) findViewById(R.id.tv_DBDate);
        showTime = findViewById(R.id.tv_DBTime);
        iv_AddImage = findViewById(R.id.iv_AddImage);
        iv_uploadImage = findViewById(R.id.iv_uploadImage);
        vB_submit = findViewById(R.id.vB_submit);
        et_service_type = findViewById(R.id.et_service_type);
        vI_service_back = findViewById(R.id.vI_service_back);
        et_DBFirstName = findViewById(R.id.et_DBFirstName);
        et_DBMobile = findViewById(R.id.et_DBMobile);
        et_DBEmail = findViewById(R.id.et_DBEmail);
        et_problem = findViewById(R.id.et_problem);
        tv_DBDate = findViewById(R.id.tv_DBDate);
        tv_DBTime = findViewById(R.id.tv_DBTime);

        iv_AddImage.setOnClickListener(this);
        iv_uploadImage.setOnClickListener(this);
        vI_service_back.setOnClickListener(this);

        mDetector = new ConnectionDetector(this);


        itemname = getIntent().getStringExtra("item");

        et_service_type.setText(itemname);


        calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar calendar = Calendar.getInstance();
                final int year = calendar.get(Calendar.YEAR);
                final int month = calendar.get(Calendar.MONTH);
                final int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(FormActivity.this,
                        setListenerDate, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                datePickerDialog.show();
            }
        });


        setListenerDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = dayOfMonth + "/" + month + "/" + year;
                showDate.setText(date);
            }
        };

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar calendar = Calendar.getInstance();
                final int hour = calendar.get(Calendar.HOUR_OF_DAY);
                final int minute = calendar.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(FormActivity.this,
                        setListenerTime, hour, minute, false);  //false
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                timePickerDialog.show();
            }
        });
        setListenerTime = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String status = "AM";

                if (hourOfDay > 11) {
                    status = "PM";
                }

                int hour_of_12_hour_format;

                if (hourOfDay > 11) {
                    hour_of_12_hour_format = hourOfDay - 12;
                } else {
                    hour_of_12_hour_format = hourOfDay;
                }
                //minute = minute;
                String time = hourOfDay + ":" + minute + " " + status;
                showTime.setText(time);
            }
        };

        vI_service_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FormActivity.this, ServiceActivity.class);
                startActivity(intent);
            }
        });


        vB_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CallService();
                /*Intent intent=new Intent(FormActivity.this,HomeActivity.class);
                startActivity(intent);*/
            }
        });
    }


    private void CallService() {
        if (!et_DBFirstName.getText().toString().equals("") || !et_DBFirstName.getText().toString().isEmpty()) {
            if (!et_DBMobile.getText().toString().equals("") || !et_DBMobile.getText().toString().isEmpty()) {
                if (!et_DBEmail.getText().toString().equals("") || !et_DBEmail.getText().toString().isEmpty()) {
                    if (!et_problem.getText().toString().equals("") || !et_problem.getText().toString().isEmpty()) {
                        if (!tv_DBDate.getText().toString().equals("") || !tv_DBDate.getText().toString().isEmpty()) {
                            if (!tv_DBTime.getText().toString().equals("") || !tv_DBTime.getText().toString().isEmpty()) {
                                if (filePathPhotoFront.isEmpty() || filePathPhotoFront.equals("")) {
                                    new WebServices(this).BookAService(WebServices.Foodey_Services,
                                            WebServices.ApiType.bservice, et_DBFirstName.getText().toString(),
                                            Prefs.getUserId(this),
                                            et_DBMobile.getText().toString(),
                                            et_DBEmail.getText().toString(),
                                            et_service_type.getText().toString(),
                                            et_problem.getText().toString(),
                                            tv_DBDate.getText().toString(),
                                            tv_DBTime.getText().toString(),
                                            "", "");
                                } else {
                                    new WebServices(this).BookAService(WebServices.Foodey_Services,
                                            WebServices.ApiType.bservice, et_DBFirstName.getText().toString(),
                                            Prefs.getUserId(this),
                                            et_DBMobile.getText().toString(),
                                            et_DBEmail.getText().toString(),
                                            et_service_type.getText().toString(),
                                            et_problem.getText().toString(),
                                            tv_DBDate.getText().toString(),
                                            tv_DBTime.getText().toString(),
                                            fileNameFront, filePathPhotoFront);

                                }
                            } else {
                                SnackBar.makeText(this, "Please select a time", SnackBar.LENGTH_SHORT).show();
                            }
                        } else {
                            SnackBar.makeText(this, "Please select a date", SnackBar.LENGTH_SHORT).show();
                        }
                    } else {
                        et_problem.setError("Required");
                    }
                } else {
                    et_DBEmail.setError("Please enter email");
                }
            } else {
                et_DBMobile.setError("Please enter phone number");
            }
        } else {
            et_DBFirstName.setError("Please enter your name");
        }

    }

    @Override
    public void onResponse(Object response, WebServices.ApiType apiType, boolean isSucces) {

        if (apiType == WebServices.ApiType.bservice) {
            if (!isSucces) {
                SnackBar.makeText(FormActivity.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                final BookService bookService = (BookService) response;

                if (!isSucces || bookService == null) {
                    SnackBar.makeText(FormActivity.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                } else {
                    if (bookService != null) {
                        if (!bookService.getMessage().equals("")) {
                            Intent intent = new Intent(FormActivity.this, ServiceActivity.class);
                            startActivity(intent);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_AddImage:
                if (!mayRequestGalleryImages()) {
                    return;
                } else {
                    selectImage(SELECT_FILE_IMAGE_FRONT, REQUEST_TAKE_PHOTO_IMAGE_FRONT);
                }
                break;
            case R.id.iv_uploadImage:
                if (!mayRequestGalleryImages()) {
                    return;
                } else {
                    selectImage(SELECT_FILE_IMAGE_FRONT, REQUEST_TAKE_PHOTO_IMAGE_FRONT);
                }
                break;

        }
    }

    private boolean mayRequestGalleryImages() {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) + ContextCompat
                .checkSelfPermission(this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) + ContextCompat
                .checkSelfPermission(this,
                        Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }

        requestPermissions(
                new String[]{Manifest.permission
                        .WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA},
                REQUEST_FOR_STORAGE_PERMISSION);
        return false;
    }

    private void showPermissionRationaleSnackBar() {

        Snackbar.make(findViewById(android.R.id.content),
                "Please Grant Permissions",
                Snackbar.LENGTH_INDEFINITE).setAction("ENABLE",
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ActivityCompat.requestPermissions(FormActivity.this,
                                new String[]{Manifest.permission
                                        .WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA},
                                REQUEST_FOR_STORAGE_PERMISSION);
                    }
                }).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        switch (requestCode) {
            case REQUEST_FOR_STORAGE_PERMISSION:

                if (grantResults.length > 0) {
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED && grantResults[2] == PackageManager.PERMISSION_GRANTED) {
                        //selectImage();
                    } else {
                        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) ||
                                ActivityCompat.shouldShowRequestPermissionRationale
                                        (this, Manifest.permission.READ_EXTERNAL_STORAGE) || ActivityCompat.shouldShowRequestPermissionRationale
                                (this, Manifest.permission.CAMERA)) {

                            showPermissionRationaleSnackBar();

                        } else {
                            Toast.makeText(this, "Go to settings and enable permission", Toast.LENGTH_LONG).show();
                        }
                    }
                }

                break;

        }

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

        } else {
        }

    }

    private void selectImage(final int selectfile, final int takephoto) {
        View view = getLayoutInflater().inflate(R.layout.take_image_popup, null);
        dialog = null;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setView(view);
        final TextView chooseFromGallery = (TextView) view.findViewById(R.id.choose_from_gallery);
        final TextView tekePhoto = (TextView) view.findViewById(R.id.take_photo);
        final TextView select_photo = (TextView) view.findViewById(R.id.select_photo);
        final TextView cancel = (TextView) view.findViewById(R.id.cancel);
        new Runnable() {
            public void run() {
            }
        }.run();
        chooseFromGallery.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select File"), selectfile);
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });
        tekePhoto.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    File photoFile = null;
                    try {
                        photoFile = createImageFile(takephoto);
                    } catch (IOException ex) {
                        return;
                    }
                    // Continue only if the File was successfully created
                    if (photoFile != null) {
                        Uri photoURI = null;
                        try {
                            photoURI = FileProvider.getUriForFile(FormActivity.this, getApplicationContext().getPackageName() + ".provider", createImageFile(takephoto));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        takePictureIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                        startActivityForResult(takePictureIntent, takephoto);
                    }
                }
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });
        dialog = builder.show();
    }

    private File createImageFile(int phot) throws IOException {

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "deliveryboy" + timeStamp + ".jpg";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        if (phot == SELECT_FILE_IMAGE_FRONT || phot == REQUEST_TAKE_PHOTO_IMAGE_FRONT) {
            filePathPhotoFront = storageDir.getAbsolutePath() + "/" + imageFileName;
            File file = new File(filePathPhotoFront);
            return file;

        } else {
            return null;
        }


    }

    private void onSelectFromGalleryResult(Intent data, int resultImage) {

        Uri selectedImageUri = data.getData();
        String selectedImagePath = getRealPathFromURI(selectedImageUri);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(selectedImagePath, options);
        int scale = 1;
        while ((options.outWidth / scale) / 2 >= ItemTouchHelper.Callback.DEFAULT_DRAG_ANIMATION_DURATION && (options.outHeight / scale) / 2 >= ItemTouchHelper.Callback.DEFAULT_DRAG_ANIMATION_DURATION) {
            scale *= 2;
        }
        options.inSampleSize = scale;
        options.inJustDecodeBounds = false;
        compressImage(selectedImagePath, 2);
        Bitmap bm = BitmapFactory.decodeFile(uriSting, options);
        fetchImageName(selectedImagePath, resultImage);
    }

    private String getRealPathFromURI(Uri contentURI) {
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) { // Source is Dropbox or other similar local file
            // path
            return contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            String path = cursor.getString(idx);
            cursor.close();
            return path;
        }
    }

    private void fetchImageName(String selectedImagePath, int id) {
        mFileNameGallery = "";
        StringTokenizer st = new StringTokenizer(selectedImagePath, "/");
        while (st.hasMoreTokens()) {
            mFileNameGallery = st.nextToken().toString();
        }
        if (id == SELECT_FILE_IMAGE_FRONT) {
            filePathPhotoFront = selectedImagePath;
            fileNameFront = mFileNameGallery;
        }

        selectedImagePath = selectedImagePath;
    }

    public String compressImage(String imageUri, int flag) {
        int actualHeight = 1;
        int actualWidth = 1;
        String filename;
        OutputStream outputStream;
        FileNotFoundException e;
        String filePath = getRealPathFromURI(imageUri);
        Bitmap scaledBitmap = null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        Bitmap bmp = BitmapFactory.decodeFile(filePath, options);
        if (options.outHeight == 0) {
            actualHeight = 1;
        } else {
            actualHeight = options.outHeight;
        }

        if (options.outWidth == 0) {
            actualWidth = 1;
        } else {
            actualHeight = options.outWidth;
        }
        float imgRatio = (float) (actualWidth / actualHeight);
        float maxRatio = 612.0f / 816.0f;
        if (((float) actualHeight) > 816.0f || ((float) actualWidth) > 612.0f) {
            if (imgRatio < maxRatio) {
                actualWidth = (int) (((float) actualWidth) * (816.0f / ((float) actualHeight)));
                actualHeight = (int) 816.0f;
            } else if (imgRatio > maxRatio) {
                actualHeight = (int) (((float) actualHeight) * (612.0f / ((float) actualWidth)));
                actualWidth = (int) 612.0f;
            } else {
                actualHeight = (int) 816.0f;
                actualWidth = (int) 612.0f;
            }
        }
        options.inSampleSize = calculateInSampleSize(options, actualWidth, actualHeight);
        options.inJustDecodeBounds = false;
        options.inPurgeable = true;
        options.inInputShareable = true;
        options.inTempStorage = new byte[16384];
        try {
            bmp = BitmapFactory.decodeFile(filePath, options);
            bitmapFromGallery = bmp;
        } catch (OutOfMemoryError exception) {
            exception.printStackTrace();
        }
        try {
            scaledBitmap = Bitmap.createBitmap(actualWidth, actualHeight, Bitmap.Config.ARGB_8888);
        } catch (OutOfMemoryError exception2) {
            exception2.printStackTrace();
        }
        float ratioX = ((float) actualWidth) / ((float) options.outWidth);
        float ratioY = ((float) actualHeight) / ((float) options.outHeight);
        float middleX = ((float) actualWidth) / 2.0f;
        float middleY = ((float) actualHeight) / 2.0f;
        Matrix scaleMatrix = new Matrix();
        scaleMatrix.setScale(ratioX, ratioY, middleX, middleY);
        Canvas canvas = new Canvas(scaledBitmap);
        canvas.setMatrix(scaleMatrix);
        if (bmp != null) {
            canvas.drawBitmap(bmp, middleX - ((float) (bmp.getWidth() / 2)), middleY - ((float) (bmp.getHeight() / 2)), new Paint(2));
        }
        try {
            int orientation = new ExifInterface(filePath).getAttributeInt("Orientation", 0);
            Matrix matrix = new Matrix();
            if (orientation == 6) {
                matrix.postRotate(90.0f);
            } else if (orientation == 3) {
                matrix.postRotate(180.0f);
            } else if (orientation == 8) {
                matrix.postRotate(270.0f);
            }
            scaledBitmap = Bitmap.createBitmap(scaledBitmap, 0, 0, scaledBitmap.getWidth(), scaledBitmap.getHeight(), matrix, true);
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        if (flag == 1) {
            filename = imageUri;
        } else {
            filename = getFilename();
        }
        try {
            OutputStream fileOutputStream = new FileOutputStream(filename);
            scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 80, fileOutputStream);
            outputStream = fileOutputStream;
        } catch (FileNotFoundException e4) {
            e = e4;
            e.printStackTrace();
            return filename;
        }
        return filename;
    }

    public String getFilename() {
        File file = new File(Environment.getExternalStorageDirectory(), IMAGE_DIRECTORY_NAME);
        if (!file.exists()) {
            file.mkdirs();
        }
        uriSting = file.getAbsolutePath() + "/" + System.currentTimeMillis() + ".jpg";
        return uriSting;
    }

    private String getRealPathFromURI(String contentURI) {
        Uri contentUri = Uri.parse(contentURI);
        Cursor cursor = getContentResolver().query(contentUri, null, null, null, null);
        if (cursor == null) {
            return contentUri.getPath();
        }
        cursor.moveToFirst();
        return cursor.getString(cursor.getColumnIndex("_data"));
    }

    public int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        int height = options.outHeight;
        int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            int heightRatio = Math.round(((float) height) / ((float) reqHeight));
            int widthRatio = Math.round(((float) width) / ((float) reqWidth));
            if (heightRatio < widthRatio) {
                inSampleSize = heightRatio;
            } else {
                inSampleSize = widthRatio;
            }
        }
        while (((float) (width * height)) / ((float) (inSampleSize * inSampleSize)) > ((float) ((reqWidth * reqHeight) * 2))) {
            inSampleSize++;
        }
        return inSampleSize;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != -1) {
            return;
        }
        if (requestCode == 100) {
            if (mDetector.isConnectingToInternet()) {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 8;
                bitmap = BitmapFactory.decodeFile(fileUri.getPath(), options);
                String[] fileTemp = fileUri.getPath().split("/");
                /*fileName = fileTemp[fileTemp.length - 1];
                filePath = fileUri.getPath();*/
                compressImage(fileUri.getPath(), 1);

                //vI_ad_image_display.setImageBitmap(bitmap);

                return;
            }
            SnackBar.makeText((Context) this, (int) R.string.no_internet, SnackBar.LENGTH_SHORT).show();
        } else if (requestCode == SELECT_FILE_IMAGE_FRONT) {
            onSelectFromGalleryResult(data, SELECT_FILE_IMAGE_FRONT);
            if (mDetector.isConnectingToInternet()) {
//                vIAmpTemp.setVisibility(View.GONE);
//                vLAmpLay.setVisibility(View.GONE);

                iv_AddImage.setImageBitmap(bitmapFromGallery);
            } else {
                SnackBar.makeText((Context) this, (int) R.string.no_internet, SnackBar.LENGTH_SHORT).show();
            }
        } else if (requestCode == REQUEST_TAKE_PHOTO_IMAGE_FRONT && resultCode == RESULT_OK) {
            // Show the thumbnail on ImageView

            String[] fileTemp = filePathPhotoFront.split("/");
            fileNameFront = fileTemp[fileTemp.length - 1];
            Uri imageUri = Uri.parse(filePathPhotoFront);
            filePathPhotoFront = imageUri.getPath();
            File file = new File(imageUri.getPath());
            try {
                InputStream ims = new FileInputStream(file);
//                vIAmpTemp.setVisibility(View.GONE);
//                vLAmpLay.setVisibility(View.GONE);

                iv_AddImage.setImageBitmap(BitmapFactory.decodeStream(ims));

            } catch (FileNotFoundException e) {
                return;
            }

            MediaScannerConnection.scanFile(this,
                    new String[]{imageUri.getPath()}, null,
                    new MediaScannerConnection.OnScanCompletedListener() {
                        public void onScanCompleted(String path, Uri uri) {
                        }
                    });
        }
    }
}
