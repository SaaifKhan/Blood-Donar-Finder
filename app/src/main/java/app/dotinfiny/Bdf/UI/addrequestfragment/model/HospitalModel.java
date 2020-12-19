package app.dotinfiny.Bdf.UI.addrequestfragment.model;

public class HospitalModel {
    public int id;
    public String HospitalName;

    public HospitalModel(String s) {
    }


    public HospitalModel(int id, String hospitalName) {

        this.id = id;
        HospitalName = hospitalName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHospitalName() {
        return HospitalName;
    }

    public void setHospitalName(String hospitalName) {
        HospitalName = hospitalName;
    }
}

