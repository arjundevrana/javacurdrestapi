/**
 * 
 * @author ARJUN SINGH 
 *
 */
package com.arjun.bean;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class ImageInfo {
	private int iamgeId;
	private byte[] fileInput;
	public int getIamgeId() {
		return iamgeId;
	}
	public void setIamgeId(int iamgeId) {
		this.iamgeId = iamgeId;
	}
	public byte[] getFileInput() {
		return fileInput;
	}
	public void setFileInput(byte[] fileInput) {
		this.fileInput = fileInput;
	}
	

}
