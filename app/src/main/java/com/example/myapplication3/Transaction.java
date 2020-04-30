package com.example.myapplication3;

public class Transaction {

    private String License;
    private String url;
    private String TrxAmount;
    private String TrxCurrency;
    private String TrxReference;


    public String getLicense() {
        return License;
    }

    public void setLicense(String license) {
        License = license;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTrxAmount() {
        return TrxAmount;
    }

    public void setTrxAmount(String trxAmount) {
        TrxAmount = trxAmount;
    }

    public String getTrxCurrency() {
        return TrxCurrency;
    }

    public void setTrxCurrency(String trxCurrency) {
        TrxCurrency = trxCurrency;
    }

    public String getTrxReference() {
        return TrxReference;
    }

    public void setTrxReference(String trxReference) {
        TrxReference = trxReference;
    }

    public Transaction() {

    }

}
