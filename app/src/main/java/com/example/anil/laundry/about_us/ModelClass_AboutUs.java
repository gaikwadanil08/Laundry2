package com.example.anil.laundry.about_us;

public class ModelClass_AboutUs {

    private String heading;
    private String desc;

    public ModelClass_AboutUs() {}

    public ModelClass_AboutUs(String heading, String desc) {
        this.heading = heading;
        this.desc = desc;
    }

    public String getHeading() {
        return heading;
    }

    public String getDesc() {
        return desc;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}


