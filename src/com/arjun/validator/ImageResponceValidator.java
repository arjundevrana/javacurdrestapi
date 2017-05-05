package com.arjun.validator;
/**
 * @author ARJUN SINGH
 */
import com.arjun.bean.ImageInfo;


public class ImageResponceValidator {

	public static boolean checkSaveImage(final ImageInfo imageInfo){
		if(imageInfo.getFileInput() == null){
			return true;
		}
		return false;
		
	}
}
