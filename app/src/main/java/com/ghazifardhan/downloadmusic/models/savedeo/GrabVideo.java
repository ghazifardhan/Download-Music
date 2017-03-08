package com.ghazifardhan.downloadmusic.models.savedeo;

/**
 * Created by ghazifardhan on 07/03/17.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GrabVideo {

    @SerializedName("upload_date")
    @Expose
    private Integer uploadDate;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("view_count")
    @Expose
    private String viewCount;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("webpage_url")
    @Expose
    private String webpageUrl;
    @SerializedName("formats")
    @Expose
    private List<Format> formats = null;
    @SerializedName("duration")
    @Expose
    private Integer duration;
    @SerializedName("uploader")
    @Expose
    private String uploader;
    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;

    public Integer getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Integer uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getViewCount() {
        return viewCount;
    }

    public void setViewCount(String viewCount) {
        this.viewCount = viewCount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWebpageUrl() {
        return webpageUrl;
    }

    public void setWebpageUrl(String webpageUrl) {
        this.webpageUrl = webpageUrl;
    }

    public List<Format> getFormats() {
        return formats;
    }

    public void setFormats(List<Format> formats) {
        this.formats = formats;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

}
