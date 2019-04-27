package com.example.anil.laundry.how_it_works;

public class ModelClass_HowItWorks {
    String heading, desc;

    public ModelClass_HowItWorks() {}

    public ModelClass_HowItWorks(String heading, String desc) {
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
