package com.arjun.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
/**
 * 
 * @author ARJUN SINGH
 *
 */
@XmlRootElement
public class APIResponse {
    private String message;
    private List<Country> country;
    private boolean status;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Country> getCountry() {
        return country;
    }

    public void setCountry(List<Country> country) {
        this.country = country;
    }

}
