
package com.geekhive.foodey.Grocery.beans.grocerystorename;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Store_ implements Serializable, Parcelable
{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("firebase_id")
    @Expose
    private String firebaseId;
    @SerializedName("sub_admin_id")
    @Expose
    private String subAdminId;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("owner_name")
    @Expose
    private String ownerName;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("sleep_mode")
    @Expose
    private String sleepMode;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("timing")
    @Expose
    private String timing;
    @SerializedName("delivery_time")
    @Expose
    private String deliveryTime;
    @SerializedName("bar_code")
    @Expose
    private String barCode;
    @SerializedName("take_away")
    @Expose
    private String takeAway;
    @SerializedName("open_status")
    @Expose
    private String openStatus;
    public final static Creator<Store_> CREATOR = new Creator<Store_>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Store_ createFromParcel(Parcel in) {
            return new Store_(in);
        }

        public Store_[] newArray(int size) {
            return (new Store_[size]);
        }

    }
    ;
    private final static long serialVersionUID = 3323076853724847372L;

    protected Store_(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.firebaseId = ((String) in.readValue((String.class.getClassLoader())));
        this.subAdminId = ((String) in.readValue((String.class.getClassLoader())));
        this.date = ((String) in.readValue((String.class.getClassLoader())));
        this.status = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.ownerName = ((String) in.readValue((String.class.getClassLoader())));
        this.mobile = ((String) in.readValue((String.class.getClassLoader())));
        this.email = ((String) in.readValue((String.class.getClassLoader())));
        this.latitude = ((String) in.readValue((String.class.getClassLoader())));
        this.longitude = ((String) in.readValue((String.class.getClassLoader())));
        this.address = ((String) in.readValue((String.class.getClassLoader())));
        this.image = ((String) in.readValue((String.class.getClassLoader())));
        this.password = ((String) in.readValue((String.class.getClassLoader())));
        this.sleepMode = ((String) in.readValue((String.class.getClassLoader())));
        this.location = ((String) in.readValue((String.class.getClassLoader())));
        this.timing = ((String) in.readValue((String.class.getClassLoader())));
        this.deliveryTime = ((String) in.readValue((String.class.getClassLoader())));
        this.barCode = ((String) in.readValue((String.class.getClassLoader())));
        this.takeAway = ((String) in.readValue((String.class.getClassLoader())));
        this.openStatus = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Store_() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirebaseId() {
        return firebaseId;
    }

    public void setFirebaseId(String firebaseId) {
        this.firebaseId = firebaseId;
    }

    public String getSubAdminId() {
        return subAdminId;
    }

    public void setSubAdminId(String subAdminId) {
        this.subAdminId = subAdminId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSleepMode() {
        return sleepMode;
    }

    public void setSleepMode(String sleepMode) {
        this.sleepMode = sleepMode;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getTakeAway() {
        return takeAway;
    }

    public void setTakeAway(String takeAway) {
        this.takeAway = takeAway;
    }

    public String getOpenStatus() {
        return openStatus;
    }

    public void setOpenStatus(String openStatus) {
        this.openStatus = openStatus;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(firebaseId);
        dest.writeValue(subAdminId);
        dest.writeValue(date);
        dest.writeValue(status);
        dest.writeValue(name);
        dest.writeValue(ownerName);
        dest.writeValue(mobile);
        dest.writeValue(email);
        dest.writeValue(latitude);
        dest.writeValue(longitude);
        dest.writeValue(address);
        dest.writeValue(image);
        dest.writeValue(password);
        dest.writeValue(sleepMode);
        dest.writeValue(location);
        dest.writeValue(timing);
        dest.writeValue(deliveryTime);
        dest.writeValue(barCode);
        dest.writeValue(takeAway);
        dest.writeValue(openStatus);
    }

    public int describeContents() {
        return  0;
    }

}
