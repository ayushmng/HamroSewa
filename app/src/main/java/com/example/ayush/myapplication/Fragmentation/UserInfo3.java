package com.example.ayush.myapplication.Fragmentation;

/**
 * Created by Ayush on 12/14/2017.
 */

public class UserInfo3 {

    public String HospitalName, TotalBed, VaccantBed, Image, Contactnum, Address ;

    public UserInfo3(String hname, String tbed, String vbed, String img, String cntnum, String addr) {

        this.HospitalName = hname;
        this.TotalBed = tbed;
        this.VaccantBed = vbed;
        this.Image = img;
        this.Contactnum = cntnum;
        this.Address = addr;

    }

    public String getHospitalName() {
        return HospitalName;
    }

    public void setId(String hname) {
        HospitalName = hname;
    }

    public String getTotalBed() {
        return TotalBed;
    }

    public void setTotalBed(String tbed) {
        TotalBed = tbed;
    }

    public String getVaccantBed() {
        return VaccantBed;
    }

    public void setVaccantBed(String vbed) {
        VaccantBed = vbed;
    }

    public String getImage() {
        return Image;
    }
    public void setImage(String image) {
        Image = image;
    }

    public String getContact() {
        return Contactnum;
    }
    public void setContact(String contact) {
        Contactnum = contact;
    }

    public String getAddress() {
        return Address;
    }
    public void setAddress(String address) {
        Address = address;
    }

}