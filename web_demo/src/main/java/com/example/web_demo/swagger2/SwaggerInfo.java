package com.example.web_demo.swagger2;

/**
 * 功能描述: SwaggerInfo信息
 *
 * @author zhangam
 * @time 2019/5/17 15:04
 * @see
 **/
public class SwaggerInfo {

    private String title;
    private String description;
    private String termsOfServiceUrl;
    private String contact;
    private String version;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTermsOfServiceUrl() {
        return termsOfServiceUrl;
    }

    public void setTermsOfServiceUrl(String termsOfServiceUrl) {
        this.termsOfServiceUrl = termsOfServiceUrl;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

}
