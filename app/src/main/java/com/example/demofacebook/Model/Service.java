package com.example.demofacebook.Model;

public class Service {
    private int imageService;
    private String ServiceName;
    private String ServiceDescription;
    private int priceService;


    public Service(int imageService, String serviceName, String serviceDescription, int priceService) {
        this.imageService = imageService;
        ServiceName = serviceName;
        ServiceDescription = serviceDescription;
        this.priceService = priceService;
    }

    public int getImageService() {
        return imageService;
    }

    public void setImageService(int imageService) {
        this.imageService = imageService;
    }

    public String getServiceName() {
        return ServiceName;
    }

    public void setServiceName(String serviceName) {
        ServiceName = serviceName;
    }

    public String getServiceDescription() {
        return ServiceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        ServiceDescription = serviceDescription;
    }

    public int getPriceService() {
        return priceService;
    }

    public void setPriceService(int priceService) {
        this.priceService = priceService;
    }
}
