package com.arjun.bean;

import java.util.List;
/**
 * @author ARJUN SINGH
 */
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class ImageInfoResponce {
	private String message;
	private List<byte[]> imageInfos;
	private boolean status;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<byte[]> getImageInfos() {
		return imageInfos;
	}

	public void setImageInfos(List<byte[]> imageInfos) {
		this.imageInfos = imageInfos;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
