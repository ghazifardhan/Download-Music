package com.ghazifardhan.downloadmusic.models.savedeo;

/**
 * Created by ghazifardhan on 07/03/17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Format {

    @SerializedName("format")
    @Expose
    private String format;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("height")
    @Expose
    private Integer height;
    @SerializedName("width")
    @Expose
    private Integer width;
    @SerializedName("ext")
    @Expose
    private String ext;
    @SerializedName("format_id")
    @Expose
    private String formatId;
    @SerializedName("format_note")
    @Expose
    private String formatNote;
    @SerializedName("preference")
    @Expose
    private Integer preference;
    @SerializedName("acodec")
    @Expose
    private String acodec;
    @SerializedName("container")
    @Expose
    private String container;
    @SerializedName("vcodec")
    @Expose
    private String vcodec;
    @SerializedName("abr")
    @Expose
    private Integer abr;

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getFormatId() {
        return formatId;
    }

    public void setFormatId(String formatId) {
        this.formatId = formatId;
    }

    public String getFormatNote() {
        return formatNote;
    }

    public void setFormatNote(String formatNote) {
        this.formatNote = formatNote;
    }

    public Integer getPreference() {
        return preference;
    }

    public void setPreference(Integer preference) {
        this.preference = preference;
    }

    public String getAcodec() {
        return acodec;
    }

    public void setAcodec(String acodec) {
        this.acodec = acodec;
    }

    public String getContainer() {
        return container;
    }

    public void setContainer(String container) {
        this.container = container;
    }

    public String getVcodec() {
        return vcodec;
    }

    public void setVcodec(String vcodec) {
        this.vcodec = vcodec;
    }

    public Integer getAbr() {
        return abr;
    }

    public void setAbr(Integer abr) {
        this.abr = abr;
    }

}