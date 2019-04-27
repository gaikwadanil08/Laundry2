package com.example.anil.laundry.why_us;

public class ModelClass_WhyUs {
    private String heading, desc;

    public ModelClass_WhyUs() {
    }

    public ModelClass_WhyUs(String heading, String desc) {
        this.heading = heading;
        this.desc = desc;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
