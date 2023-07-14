package com.example.demofacebook.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Media_ServicePack implements Serializable {
    @SerializedName("fileId")
    private int fileID;
    @SerializedName("filePath")
    private String filePath;
    @SerializedName("fileIndex")
    private String fileIndex;

    public Media_ServicePack(int fileID, String filePath, String fileIndex) {
        this.fileID = fileID;
        this.filePath = filePath;
        this.fileIndex = fileIndex;
    }

    public int getFileID() {
        return fileID;
    }

    public void setFileID(int fileID) {
        this.fileID = fileID;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileIndex() {
        return fileIndex;
    }

    public void setFileIndex(String fileIndex) {
        this.fileIndex = fileIndex;
    }
}
