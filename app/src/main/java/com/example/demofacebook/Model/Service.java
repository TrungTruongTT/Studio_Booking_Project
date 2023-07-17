package com.example.demofacebook.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
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
    @SerializedName("soldCount")
    private int soldCount;
    @SerializedName("rating")
    private double serviceRating;
    @SerializedName("price")
    private int priceService;
    @SerializedName("view")
    private int view;
    @SerializedName("discount")
    private int discount;
    @SerializedName("status")
    private String statusService;
    @SerializedName("studio")
    private Studio studio;
    @SerializedName("servicePack_mediaService")
    private List<Media_ServicePack> mediaServicePackList;

    /*
    @SerializedName("createDate")
    @SerializedName("updateDate")

    @SerializedName("discount")
    @SerializedName("updateBy")
    @SerializedName("createBy")

    @SerializedName("servicePack_orderDetail")
    @SerializedName("servicePack_favorite")*/

    public Service(int serviceId, String serviceName, String serviceDescription, int soldCount, double serviceRating, int priceService, int view, int discount, String statusService, Studio studio_id, List<Media_ServicePack> mediaServicePackList) {
        this.serviceId = serviceId;
        ServiceName = serviceName;
        this.serviceDescription = serviceDescription;
        this.soldCount = soldCount;
        this.serviceRating = serviceRating;
        this.priceService = priceService;
        this.view = view;
        this.discount = discount;
        this.statusService = statusService;
        this.studio = studio_id;
        this.mediaServicePackList = mediaServicePackList;
    }

    public Service(int serviceId, double serviceRating, String serviceName, String serviceDescription, int priceService, int view, int discount) {
        this.serviceId = serviceId;
        this.serviceRating = serviceRating;
        this.ServiceName = serviceName;
        this.serviceDescription = serviceDescription;
        this.priceService = priceService;
        this.view = view;
        this.discount = discount;
    }

    public Service(int serviceId, double serviceRating, String serviceName, int priceService, int view, int discount) {
        this.serviceId = serviceId;
        this.serviceRating = serviceRating;
        this.ServiceName = serviceName;
        this.priceService = priceService;
        this.view = view;
        this.discount = discount;
    }

    public Service(int serviceId, int imageService, double serviceRating, String serviceName, String serviceDescription, int priceService, int view, int discount) {
        this.serviceId = serviceId;
        //this.imageService = imageService;
        this.serviceRating = serviceRating;
        this.ServiceName = serviceName;
        this.serviceDescription = serviceDescription;
        this.priceService = priceService;
        this.view = view;
        this.discount = discount;
    }

    public Service(int serviceId, String serviceName, String serviceDescription, int imageService, double serviceRating, int priceService, int view, int discount) {
        this.serviceId = serviceId;
        ServiceName = serviceName;
        this.serviceDescription = serviceDescription;
        //this.imageService = imageService;
        this.serviceRating = serviceRating;
        this.priceService = priceService;
        this.view = view;
        this.discount = discount;
    }

    public Service(int serviceId, String serviceName, String serviceDescription, int soldCount, double serviceRating, int priceService, int view, int discount, String statusService) {
        this.serviceId = serviceId;
        ServiceName = serviceName;
        this.serviceDescription = serviceDescription;
        this.soldCount = soldCount;
        this.serviceRating = serviceRating;
        this.priceService = priceService;
        this.view = view;
        this.discount = discount;
        this.statusService = statusService;
    }

    public List<Media_ServicePack> getMediaServicePackList() {
        return mediaServicePackList;
    }

    public void setMediaServicePackList(List<Media_ServicePack> mediaServicePackList) {
        this.mediaServicePackList = mediaServicePackList;
    }

    public Studio getStudio() {
        return studio;
    }

    public void setStudio(Studio studio) {
        this.studio = studio;
    }

    public int getSoldCount() {
        return soldCount;
    }

    public void setSoldCount(int soldCount) {
        this.soldCount = soldCount;
    }

    public String getStatusService() {
        return statusService;
    }

    public void setStatusService(String statusService) {
        this.statusService = statusService;
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

    /*public int getImageService() {
        return imageService;
    }

    public void setImageService(int imageService) {
        this.imageService = imageService;
    }*/

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

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
