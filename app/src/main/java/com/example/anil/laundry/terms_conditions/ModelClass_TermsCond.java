package com.example.anil.laundry.terms_conditions;

public class ModelClass_TermsCond {

    private String terms, desc;

    public ModelClass_TermsCond() {
    }

    public ModelClass_TermsCond(String terms, String desc) {
        this.terms = terms;
        this.desc = desc;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
