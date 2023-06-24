package com.example.demofacebook.Model;

import java.io.Serializable;

public class Service implements Serializable {
    private int serviceId;
    private int imageService;
    private double serviceRating;
    private String ServiceName;
    private String serviceDescription;
    private int priceService;
    private int view;

//    public Service(int serviceId, int imageService, double serviceRating, String serviceName, int priceService, int view) {
//        this.serviceId = serviceId;
//        this.imageService = imageService;
//        this.serviceRating = serviceRating;
//        ServiceName = serviceName;
//        this.priceService = priceService;
//        this.view = view;
//    }

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
}
