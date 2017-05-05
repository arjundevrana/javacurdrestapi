package com.arjun.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.arjun.bean.ImageInfo;
import com.arjun.bean.ImageInfoResponce;
import com.arjun.dao.ImageDao;
import com.arjun.util.ApplicationProperty;
import com.arjun.util.JDBCConnection;
import com.arjun.validator.ImageResponceValidator;

public class ImageInfoService {

	Connection connection = JDBCConnection.getDBConnection(ApplicationProperty.DBASENAME,
			ApplicationProperty.DBUSERNAME, ApplicationProperty.DBPASSWORD);
	ImageDao imageDao = new ImageDao(connection);

	public ImageInfoResponce saveImage(final ImageInfo imageInfo) {
		ImageInfoResponce imageInfoResponce = new ImageInfoResponce();
		if (ImageResponceValidator.checkSaveImage(imageInfo)) {
			imageInfoResponce.setImageInfos(null);
			imageInfoResponce.setMessage("Imahe Should not be null");
			imageInfoResponce.setStatus(false);
			return imageInfoResponce;
		} else {
			try {
				if (imageDao.saveImage(imageInfo))
					imageInfoResponce.setImageInfos(null);
				imageInfoResponce.setMessage("Image Save Succee full");
				imageInfoResponce.setStatus(true);
			} catch (SQLException e) {
				imageInfoResponce.setImageInfos(null);
				imageInfoResponce.setMessage(" Error in data base insertation");
				imageInfoResponce.setStatus(false);
				return imageInfoResponce;
			}

		}
		return imageInfoResponce;
	}

}
