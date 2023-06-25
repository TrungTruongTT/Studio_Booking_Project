package com.example.demofacebook.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class Service implements Serializable {


/*
    @SerializedName("serviceId")
    private int serviceId;

    @SerializedName("name")
    private String name;

    @SerializedName("createDate")
    private Timestamp createDate;

    @SerializedName("price")
    private int price;

    @SerializedName("description")
    private String description;

    @SerializedName("soldCount")
    private int soldCount;

    @SerializedName("status")
    private String status;

    @SerializedName("updateDate")
    private Timestamp updateDate;

    @SerializedName("view")
    private Integer view;

    @SerializedName("discount")
    private Integer discount;

    @SerializedName("rating")
    private double rating;

    @SerializedName("updateBy")
    private String updateBy;

    @SerializedName("createBy")
    private String createBy;

    @SerializedName("studio")
    private String studio;

    @SerializedName("servicePack_mediaService")
    private List<ServicePackMediaService> servicePackMediaService;

    @SerializedName("servicePack_orderDetail")
    private List<ServicePackOrderDetail> servicePackOrderDetail;

    @SerializedName("servicePack_favorite")
    private List<ServicePackFavorite> servicePackFavorite;
*/



    @SerializedName("serviceId")
    private int serviceId;
    @SerializedName("name")
    private String ServiceName;

    @SerializedName("description")
    private String serviceDescription;
    @SerializedName("servicePack_mediaService")
    private int imageService;
    @SerializedName("rating")
    private double serviceRating;
    @SerializedName("price")
    private int priceService;
    @SerializedName("view")
    private int view;

    /*
    @SerializedName("createDate")
    @SerializedName("updateDate")
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
        this.ServiceName = serviceName;
        this.serviceDescription = serviceDescription;
        this.priceService = priceService;
        this.view = view;
    }

    public Service(int serviceId, int imageService, double serviceRating, String serviceName, int priceService, int view) {
        this.serviceId = serviceId;
        this.imageService = imageService;
        this.serviceRating = serviceRating;
        this.ServiceName = serviceName;
        this.priceService = priceService;
        this.view = view;
    }

    public Service(int serviceId, int imageService, double serviceRating, String serviceName, String serviceDescription, int priceService, int view) {
        this.serviceId = serviceId;
        this.imageService = imageService;
        this.serviceRating = serviceRating;
        this.ServiceName = serviceName;
        this.serviceDescription = serviceDescription;
        this.priceService = priceService;
        this.view = view;
    }

    public Service(int serviceId, String serviceName, String serviceDescription, int imageService, double serviceRating, int priceService, int view) {
        this.serviceId = serviceId;
        ServiceName = serviceName;
        this.serviceDescription = serviceDescription;
        this.imageService = imageService;
        this.serviceRating = serviceRating;
        this.priceService = priceService;
        this.view = view;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
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

    public int getPriceService() {
        return priceService;
    }

    public void setPriceService(int priceService) {
        this.priceService = priceService;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

}
