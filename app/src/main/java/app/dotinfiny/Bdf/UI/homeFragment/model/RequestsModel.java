package app.dotinfiny.Bdf.UI.homeFragment.model;

public class RequestsModel {
    private String id;
    private String Detail;
    private String Location;
    private String Hospital;
    private String BloodGroup;
    private String requestType;
    private String UserName;
    private String userImage;


    public RequestsModel() {
    }

    public RequestsModel(String id, String detail, String location, String hospital, String bloodGroup, String requestType, String userName, String userImage) {
        this.id = id;
        Detail = detail;
        Location = location;
        Hospital = hospital;
        BloodGroup = bloodGroup;
        this.requestType = requestType;
        UserName = userName;
        this.userImage = userImage;
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
