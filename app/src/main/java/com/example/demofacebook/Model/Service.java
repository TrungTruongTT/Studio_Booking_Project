package com.example.demofacebook.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Service implements Serializable {

    @SerializedName("serviceId")
    private int serviceId;
    @SerializedName("servicePack_mediaService")
    private int imageService;
    @SerializedName("rating")
    private double serviceRating;
    @SerializedName("name")
    private String ServiceName;
    @SerializedName("description")
    private String serviceDescription;
    @SerializedName("price")
    private int priceService;
    @SerializedName("view")
    private int view;

    /*@SerializedName("updateDate")
    @SerializedName("soldCount")
    @SerializedName("status")
    @SerializedName("discount")
    @SerializedName("updateBy")
    @SerializedName("createBy")
    @SerializedName("studio")
    @SerializedName("servicePack_orderDetail")
    @SerializedName("servicePack_favorite")*/

    public Service(int serviceId, double serviceRating, String serviceName, String serviceDescription, int priceService, int view) {
        this.serviceId = serviceId;
        this.serviceRating = serviceRating;
        ServiceName = serviceName;
        this.serviceDescription = serviceDescription;
        this.priceService = priceService;
        this.view = view;
    }

    public Service(int serviceId, int imageService, double serviceRating, String serviceName, int priceService, int view) {
        this.serviceId = serviceId;
        this.imageService = imageService;
        this.serviceRating = serviceRating;
        ServiceName = serviceName;
        this.priceService = priceService;
        this.view = view;
    }

    public Service(int serviceId, int imageService, double serviceRating, String serviceName, String serviceDescription, int priceService, int view) {
        this.serviceId = serviceId;
        this.imageService = imageService;
        this.serviceRating = serviceRating;
        ServiceName = serviceName;
        this.serviceDescription = serviceDescription;
        this.priceService = priceService;
        this.view = view;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public int getImageService() {
        return imageService;
    }

    public void setImageService(int imageService) {
        this.imageService = imageService;
    }

    public double getServiceRating() {
        return serviceRating;
    }

    public void setServiceRating(double serviceRating) {
        this.serviceRating = serviceRating;
    }

    public String getServiceName() {
        return ServiceName;
    }

    public void setServiceName(String serviceName) {
        ServiceName = serviceName;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    public int getPriceService() {
        return priceService;
    }

    public void setPriceService(int priceService) {
        this.priceService = priceService;
    }

    @Override
    public String toString() {
        return "Service{" +
                "serviceId=" + serviceId +
                ", imageService=" + imageService +
                ", serviceRating=" + serviceRating +
                ", ServiceName='" + ServiceName + '\'' +
                ", serviceDescription='" + serviceDescription + '\'' +
                ", priceService=" + priceService +
                ", view=" + view +
                '}';
    }
}
