package app.dotinfiny.Bdf.UI.homeFragment.model;

import android.os.Parcel;
import android.os.Parcelable;

public class RequestsModel implements Parcelable {
    private String id;
    private String Detail;
    private String Location;
    private String Hospital;
    private String BloodGroup;
    private String requestType;
    private String UserName;
    private String userImage;
    public static final Creator<RequestsModel> CREATOR = new Creator<RequestsModel>() {
        @Override
        public RequestsModel createFromParcel(Parcel in) {
            return new RequestsModel(in);
        }

        @Override
        public RequestsModel[] newArray(int size) {
            return new RequestsModel[size];
        }
    };


    public RequestsModel() {
    }

    private String userPhone;

    public RequestsModel(String id, String detail, String location, String hospital, String bloodGroup, String requestType, String userName, String userImage, String userPhone) {
        this.id = id;
        Detail = detail;
        Location = location;
        Hospital = hospital;
        BloodGroup = bloodGroup;
        this.requestType = requestType;
        UserName = userName;
        this.userImage = userImage;
        this.userPhone = userPhone;
    }

    protected RequestsModel(Parcel in) {
        id = in.readString();
        Detail = in.readString();
        Location = in.readString();
        Hospital = in.readString();
        BloodGroup = in.readString();
        requestType = in.readString();
        UserName = in.readString();
        userImage = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(Detail);
        dest.writeString(Location);
        dest.writeString(Hospital);
        dest.writeString(BloodGroup);
        dest.writeString(requestType);
        dest.writeString(UserName);
        dest.writeString(userImage);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getUserImage() {
        return userImage;
    }

    public String getId() {
        return id;
    }

    public String getDetail() {
        return Detail;
    }

    public String getLocation() {
        return Location;
    }

    public String getHospital() {
        return Hospital;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public String getBloodGroup() {
        return BloodGroup;
    }

    public String getRequestType() {
        return requestType;
    }

    public String getUserName() {
        return UserName;
    }
}
