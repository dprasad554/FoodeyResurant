package com.geekhive.foodey.Cakes.activities;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
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
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.media.ExifInterface;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ItemTouchHelper;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.geekhive.foodey.Cakes.beans.cakeaddtocart.CakeAddtocart;
import com.geekhive.foodey.Cakes.beans.cakemapslocdata.GetDistanceFromMap;
import com.geekhive.foodey.Cakes.custom.SnackBar;
import com.geekhive.foodey.Cakes.utils.ConnectionDetector;
import com.geekhive.foodey.Cakes.utils.OnResponseListner;
import com.geekhive.foodey.Cakes.utils.WebServices;
import com.geekhive.foodey.Food.utils.Prefs;
import com.geekhive.foodey.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.StringTokenizer;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;

import static com.geekhive.foodey.Cakes.utils.util.IMAGE_DIRECTORY_NAME;
import static com.geekhive.foodey.Cakes.utils.util.REQUEST_FOR_STORAGE_PERMISSION;
import static com.geekhive.foodey.Cakes.utils.util.REQUEST_TAKE_PHOTO_PROFILE_PIC;
import static com.geekhive.foodey.Cakes.utils.util.SELECT_FILE_PROFILE_PIC;

public class CakeDetailActivity extends AppCompatActivity implements OnResponseListner {

    Toolbar toolbar;
    TextView displayInteger;
    TextView increase,decrease;
    int minteger = 0;
    ImageView iv_cake1;
    ConnectionDetector mDetector;
    String cakeName,quantity,price,description,url,mrp,discount,storeid,productid,category,lati,longi;
    TextView tv_productName,tv_productOriginal,tv_productCut,tv_productOffer,tv_pPrice,tv_pQty,tv_description;
    Button btn_addCart;
    String item_count;
    LinearLayout tv_note;
    RecyclerView vRAcoDeliverymode;
    ModeAdapter modeAdapter;
    Dialog schedulePopup;
    public Typeface Montserrat_Regular;
    public Typeface Montserrat_SemiBold;
    String selected_datetime = "";
    String selectedDateStr="";
    ArrayList<String> timeslot;
    ImageView iv_uploadPhoto;
    DialogInterface dialog;
    private String filepathcakepic = "";
    Bitmap bitmap;
    Uri fileUri;
    Bitmap bitmapFromGallery;
    String mFileNameGallery;
    String fileNameProfilepic;
    private String uriSting;
    TextView message;
    RelativeLayout image_uplod;
    TextView photoheading;
    TextView vT_ap_sideheader;
    String dis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cake_activity_cake_detail);
        setValues();
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Cakes Detail");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        cakeName = getIntent().getStringExtra("cakeName");
        quantity = getIntent().getStringExtra("quantity");
        price = getIntent().getStringExtra("price");
        description = getIntent().getStringExtra("description");
        url = getIntent().getStringExtra("url");
        mrp = getIntent().getStringExtra("mrp");
        discount = getIntent().getStringExtra("discount");
        storeid = getIntent().getStringExtra("store_id");
        productid = getIntent().getStringExtra("product_id");
        category = getIntent().getStringExtra("category");
        lati = getIntent().getStringExtra("lati");
        longi = getIntent().getStringExtra("longi");

        tv_note = (LinearLayout)findViewById(R.id.tv_note);
        tv_productName = (TextView) findViewById(R.id.tv_productName);
        tv_productOriginal = (TextView) findViewById(R.id.tv_productOriginal);
        tv_productCut = (TextView) findViewById(R.id.tv_productCut);
        tv_productOffer = (TextView) findViewById(R.id.tv_productOffer);
        tv_pPrice = (TextView) findViewById(R.id.tv_pPrice);
        tv_pQty = (TextView) findViewById(R.id.tv_pQty);
        tv_description = (TextView)findViewById(R.id.tv_description);
        btn_addCart = (Button)findViewById(R.id.btn_addCart);
        vRAcoDeliverymode = findViewById(R.id.vR_aco_deliverymode);
        iv_uploadPhoto = findViewById(R.id.iv_uploadPhoto);
        message = (EditText)findViewById(R.id.message);
        image_uplod = (RelativeLayout)findViewById(R.id.image_uplod);
        photoheading = (TextView)findViewById(R.id.photoheading);

        tv_productName.setText(cakeName);
        tv_pQty.setText(quantity);
        tv_pPrice.setText("Rs. "+price);
        tv_productCut.setText("Mrp Rs."+mrp);
        tv_description.setText(description);
        tv_productOffer.setText("Rs."+discount+" OFF");
        tv_productCut.setPaintFlags(tv_productCut.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

       /* if(category.equals("3")){
            tv_note.setVisibility(View.GONE);
            image_uplod.setVisibility(View.VISIBLE);
            photoheading.setVisibility(View.VISIBLE);
        }*/
        iv_cake1 = findViewById(R.id.iv_cake1);

        if (!url.equals("")){
            Picasso.get()
                    .load(url)//download URL
                    .error(R.drawable.foodey_logo_)//if failed
                    .into(iv_cake1);
        }

        increase = findViewById(R.id.btn_increase);
        decrease = findViewById(R.id.btn_decrease);
        displayInteger = (TextView)findViewById(R.id.tv_integerNumber);
        item_count = displayInteger.getText().toString();
        btn_addCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!item_count.equals("0")) {
                    CallAdd(lati,longi);
                   // CallService();
                }else {
                    SnackBar.makeText(CakeDetailActivity.this, "Quantity can't be 0", SnackBar.LENGTH_SHORT).show();
                }

            }
        });
        increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                increaseInteger(view);
            }
        });

        decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decreaseInteger(view);
            }
        });

        modeAdapter = new ModeAdapter();
        vRAcoDeliverymode.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        vRAcoDeliverymode.setAdapter(modeAdapter);

        //For Titles
        timeslot = new ArrayList<String>();
        timeslot.add("10:00AM-2:30AM");
        timeslot.add("2:30AM-7.30PM");
        timeslot.add("7.30PM-9.30PM");

        iv_uploadPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mayRequestGalleryImages()) {
                    return;
                } else {
                    selectImage(SELECT_FILE_PROFILE_PIC, REQUEST_TAKE_PHOTO_PROFILE_PIC);

                }
            }
        });
        String Userid = Prefs.getUserId(this);
        System.out.println("Userid:::::::::"+Userid);
    }

    private void setValues() {
        mDetector = new ConnectionDetector(this);
    }

    private void CallService(String distanceAdd) {
        if (this.mDetector.isConnectingToInternet()) {
            boolean fileImage = false;
            String message_ = message.getText().toString();
            if (!filepathcakepic.equals("")){
                fileImage = true;
            } else {
                fileImage = false;
            }
            new WebServices(this).Addtocart(WebServices.Foodey_Cakelocation_Services,
                    WebServices.ApiType.addtocart,Prefs.getUserId(this),storeid,productid,item_count,price,mrp,message_,selectedDateStr,selected_datetime,filepathcakepic,fileImage,distanceAdd, Prefs.getCityId(this));
                return;
        }
    }
    private void CallMapService(String origin, String destination, String key) {
        if (this.mDetector.isConnectingToInternet()) {
            new WebServices(this).DistanceMap("https://maps.googleapis.com/maps/api/directions/",
                    WebServices.ApiType.mapdistance, origin, destination, key);
        } else {
            SnackBar.makeText(CakeDetailActivity.this, "No internet connectivity", SnackBar.LENGTH_SHORT).show();
        }
    }
    private void CallAdd(String lat, String lang) {

        String str_origin = Prefs.getUserLat(CakeDetailActivity.this) + "," + Prefs.getUserLang(CakeDetailActivity.this);
        // Destination of route
        String str_dest = lat + "," + lang;
        String key = getResources().getString(R.string.google_map_api);
        CallMapService(str_origin, str_dest, key);
    }

    @Override
    public void onResponse(Object response, WebServices.ApiType apiType, boolean isSucces) {

        if (apiType == WebServices.ApiType.addtocart) {
            if (!isSucces) {
                SnackBar.makeText(CakeDetailActivity.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                final CakeAddtocart addtocart = (CakeAddtocart) response;
                if (!isSucces || addtocart == null) {
                    SnackBar.makeText(CakeDetailActivity.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                } else {
                    SnackBar.makeText(CakeDetailActivity.this, addtocart.getMessage(), SnackBar.LENGTH_SHORT).show();
                }
            }
        } else if (apiType == WebServices.ApiType.mapdistance) {
            if (!isSucces) {
                SnackBar.makeText(CakeDetailActivity.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
            } else {
                GetDistanceFromMap getDistanceFromMap = (GetDistanceFromMap) response;
                if (getDistanceFromMap != null||getDistanceFromMap.getStatus() != "ZERO RESULTS") {
                    if (getDistanceFromMap.getRoutes().get(0).getLegs() != null) {
                        String distance = getDistanceFromMap.getRoutes().get(0).getLegs().get(0).getDistance().getText();
                        if (distance.contains("km")){
                            dis = distance.replace(" km", "");
                            CallService(dis);
                        } else {
                            dis = distance.replace(" m", "");
                            CallService(dis);
                        }

                    }

                } else {
                    SnackBar.makeText(CakeDetailActivity.this, getResources().getString(R.string.something_wrong), SnackBar.LENGTH_SHORT).show();
                }
            }
        }
    }
    public void increaseInteger(View view) {
        minteger = minteger + 1;
        display(minteger);
    }

    public void decreaseInteger(View view) {
        if (minteger != 0) {
            minteger = minteger - 1;
            display(minteger);
        }
    }

    private void display(int number) {
        displayInteger.setText(String.valueOf(number));
        item_count = displayInteger.getText().toString();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.cart_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_favorite) {
            Intent intent = new Intent(CakeDetailActivity.this, CakeCheckOutActivityNew.class);
            intent.putExtra("lati", lati);
            intent.putExtra("longi", longi);
            startActivity(intent);
            return true;
        }
        switch (id) {
            case android.R.id.home:
                finish();
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    private void InitializeSchedulepopup() {
        schedulePopup = new Dialog(this);
        schedulePopup.requestWindowFeature(Window.FEATURE_NO_TITLE);
        schedulePopup.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        schedulePopup.setContentView(R.layout.cake_popup_schedule);
        schedulePopup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        schedulePopup.setCancelable(true);
        schedulePopup.setCanceledOnTouchOutside(true);
    }

    private void initializScheduleshowpopup(String head) {
        schedulePopup.setContentView(R.layout.cake_popup_schedule);
        schedulePopup.setCancelable(true);
        schedulePopup.setCanceledOnTouchOutside(true);
        schedulePopup.show();

        final LinearLayout VL_ps_menu = (LinearLayout) schedulePopup.findViewById(R.id.VL_ps_menu);
        final TextView vT_ps_schedule = (TextView) schedulePopup.findViewById(R.id.vT_ps_schedule);
        final TextView vT_ps_done = (TextView) schedulePopup.findViewById(R.id.vT_ps_done);
        final RecyclerView vR_ps_list = (RecyclerView) schedulePopup.findViewById(R.id.vR_ps_list);
        final ImageView vI_ps_close = (ImageView) schedulePopup.findViewById(R.id.vI_ps_close);


        Calendar startDate = Calendar.getInstance();
        selectedDateStr = DateFormat.format("EEE, MMM d, yyyy", startDate).toString();
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.DAY_OF_MONTH, 4);

        vT_ps_schedule.setText(head);
//        HorizontalCalendarView vC_ps_calendarView=schedulePopup.findViewById(R.id.vC_ps_calendarView);
        HorizontalCalendar vC_ps_calendarView = new HorizontalCalendar.Builder(VL_ps_menu, R.id.vC_ps_calendarView)
                .range(startDate, endDate)
                .datesNumberOnScreen(5)
                .mode(HorizontalCalendar.Mode.DAYS)
                .configure()
                .sizeMiddleText(14f)
                .formatMiddleText("EEE")
                .formatBottomText("d MMM")
                .showTopText(false)
                .showBottomText(true)
                .textColor(getResources().getColor(R.color.text_color), getResources().getColor(R.color.white))
                .end()
                .defaultSelectedDate(startDate).build();

        vC_ps_calendarView.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {
                selectedDateStr = DateFormat.format("EEE, MMM d, yyyy", date).toString();
                Toast.makeText(CakeDetailActivity.this, selectedDateStr + " selected!", Toast.LENGTH_SHORT).show();
                Log.e("onDateSelected", selectedDateStr + " - Position = " + position);
            }

        });
        Runnable r = new Runnable() {
            @Override
            public void run() {
                vT_ps_schedule.setTypeface(Montserrat_SemiBold);
                vT_ps_done.setTypeface(Montserrat_Regular);

            }
        };
        r.run();
        int width = getResources().getDisplayMetrics().widthPixels - 100;
        int height = getResources().getDisplayMetrics().heightPixels - 250;
//        schedulePopup.getWindow().setLayout(width, height);
        schedulePopup.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        schedulePopup.getWindow().setGravity(Gravity.BOTTOM);

        ScheduleAdapter filterAdapter = new ScheduleAdapter(this,timeslot);
        vR_ps_list.setLayoutManager(new LinearLayoutManager(this));
        vR_ps_list.setAdapter(filterAdapter);


        vI_ps_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                schedulePopup.dismiss();
            }
        });
        vT_ps_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                schedulePopup.dismiss();
                String dateTime = selectedDateStr+" - "+selected_datetime;
                vT_ap_sideheader.setText(dateTime);
            }
        });
        schedulePopup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        schedulePopup.setCancelable(true);
        schedulePopup.setCanceledOnTouchOutside(true);
        schedulePopup.show();
    }

    public class ModeAdapter extends RecyclerView.Adapter<ModeAdapter.MyViewHolder> {

        String array[] = {
                getResources().getString(R.string.schedule_delivery)};


        int selected = -1;

        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cake_adapter_pay, parent, false));
        }

        public void onBindViewHolder(final MyViewHolder holder, final int position) {

            holder.vT_ap_header.setText(array[position]);

            if (selected == position) {
                holder.vI_ap_select.setBackgroundResource(R.drawable.circle_stroke_green);
            } else {
                holder.vI_ap_select.setBackgroundResource(R.drawable.circle_stroke_grey);
            }

            holder.vL_ap_main.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selected = position;
                    notifyDataSetChanged();
                    if (position == 0) {
                        InitializeSchedulepopup();
                        initializScheduleshowpopup(getResources().getString(R.string.schedule_delivery));
                    }
                }
            });
            //holder.vT_ap_sideheader.setText(selectedDateStr+" - "+selected_datetime);
        }
        public int getItemCount() {
            return 1;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            LinearLayout vL_ap_main;
            ImageView vI_ap_select, vI_ap_edit;
            TextView vT_ap_header;

            public MyViewHolder(View view) {
                super(view);
                this.vL_ap_main = (LinearLayout) view.findViewById(R.id.vL_ap_main);
                this.vI_ap_select = (ImageView) view.findViewById(R.id.vI_ap_select);
                this.vI_ap_edit = (ImageView) view.findViewById(R.id.vI_ap_edit);
                this.vT_ap_header = (TextView) view.findViewById(R.id.vT_ap_header);
                vT_ap_sideheader = (TextView) view.findViewById(R.id.vT_ap_sideheader);

                new Runnable() {
                    public void run() {
                        vT_ap_header.setTypeface(Montserrat_SemiBold);
                        vT_ap_sideheader.setTypeface(Montserrat_Regular);
                    }
                }.run();
            }
        }
    }

    public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.MyViewHolder> {

        private Context mcontext;
        ArrayList<String> titlesNames;
        int selected = -1;
        public ScheduleAdapter(Context context, ArrayList<String> titlesNames) {
            this.titlesNames = titlesNames;
            this.mcontext = context;
        }
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cake_adapter_schedule, parent, false));
        }
        public void onBindViewHolder(final MyViewHolder holder, final int position) {
            holder.vT_ads_header.setText(titlesNames.get(position));

            if (selected == position) {
                holder.vI_ads_select.setBackgroundResource(R.drawable.circle_stroke_green);
            } else {
                holder.vI_ads_select.setBackgroundResource(R.drawable.circle_stroke_grey);
            }

            holder.vT_ads_header.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selected = position;
                    selected_datetime = titlesNames.get(position);
                    notifyDataSetChanged();
                }
            });

        }

        public int getItemCount() {
            return 3;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            LinearLayout vL_ads_main;
            ImageView vI_ads_select;
            TextView vT_ads_header;

            public MyViewHolder(View view) {
                super(view);
                this.vL_ads_main = (LinearLayout) view.findViewById(R.id.vL_ads_main);
                this.vI_ads_select = (ImageView) view.findViewById(R.id.vI_ads_select);
                this.vT_ads_header = (TextView) view.findViewById(R.id.vT_ads_header);

                new Runnable() {
                    public void run() {
                        vT_ads_header.setTypeface(Montserrat_Regular);
                    }
                }.run();
            }
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
    private void selectImage(final int selectfile, final int takephoto) {
        View view = getLayoutInflater().inflate(R.layout.cake_take_image_popup, null);
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
                            photoURI = FileProvider.getUriForFile(CakeDetailActivity.this, getApplicationContext().getPackageName() + ".provider", createImageFile(takephoto));
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
        String imageFileName = "CakeUser"+timeStamp + ".jpg";
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        if (phot == SELECT_FILE_PROFILE_PIC || phot == REQUEST_TAKE_PHOTO_PROFILE_PIC){
            filepathcakepic = storageDir.getAbsolutePath() + "/" + imageFileName;
            File file = new File(filepathcakepic);
            return file;
        }else {
            return null;
        }


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
        } else if (requestCode == SELECT_FILE_PROFILE_PIC) {
            onSelectFromGalleryResult(data, SELECT_FILE_PROFILE_PIC);
            if (mDetector.isConnectingToInternet()) {
//                vIAmpTemp.setVisibility(View.GONE);
//                vLAmpLay.setVisibility(View.GONE);

                iv_uploadPhoto.setImageBitmap(bitmapFromGallery);
                //CallService();
            } else {
                SnackBar.makeText((Context) this, (int) R.string.no_internet, SnackBar.LENGTH_SHORT).show();
            }
        } else if (requestCode == REQUEST_TAKE_PHOTO_PROFILE_PIC&& resultCode == RESULT_OK) {
            // Show the thumbnail on ImageView

            String[] fileTemp = filepathcakepic.split("/");
            fileNameProfilepic = fileTemp[fileTemp.length - 1];
            Uri imageUri = Uri.parse(filepathcakepic);
            filepathcakepic = imageUri.getPath();
            File file = new File(imageUri.getPath());
            try {
                InputStream ims = new FileInputStream(file);
//                vIAmpTemp.setVisibility(View.GONE);
//                vLAmpLay.setVisibility(View.GONE);

                iv_uploadPhoto.setImageBitmap(BitmapFactory.decodeStream(ims));
                // CallService();

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

    public String compressImage(String imageUri, int flag) {
        String filename;
        OutputStream outputStream;
        FileNotFoundException e;
        String filePath = getRealPathFromURI(imageUri);
        Bitmap scaledBitmap = null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        Bitmap bmp = BitmapFactory.decodeFile(filePath, options);
        int actualHeight = options.outHeight;
        int actualWidth = options.outWidth;
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
        canvas.drawBitmap(bmp, middleX - ((float) (bmp.getWidth() / 2)), middleY - ((float) (bmp.getHeight() / 2)), new Paint(2));
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
    private void fetchImageName(String selectedImagePath, int id) {
        mFileNameGallery = "";
        StringTokenizer st = new StringTokenizer(selectedImagePath, "/");
        while (st.hasMoreTokens()) {
            mFileNameGallery = st.nextToken().toString();
        }
        if (id == SELECT_FILE_PROFILE_PIC){
            filepathcakepic = selectedImagePath;
            fileNameProfilepic = mFileNameGallery;
        }

        selectedImagePath = selectedImagePath;
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
