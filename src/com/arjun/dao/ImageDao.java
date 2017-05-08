package com.arjun.dao;

/**
 * @author ARJUN SINGH
 */
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.codec.binary.Base64;

import com.arjun.bean.ImageInfo;

public class ImageDao {
	private Connection connection;

	public ImageDao(Connection connection) {
		super();
		this.connection = connection;
	}

	/**
	 * 
	 * @param imageInfo
	 *            This method will take ImageInfo type value as parameter.
	 * @return This method will return true/false if value success insert it
	 *         will return true otherwise false.
	 * @throws SQLException
	 *             It may be threw SQL injection.
	 */
	public boolean saveImage(ImageInfo imageInfo) throws SQLException {
		String sql = "INSERT INTO image_info(IMAGE_KEY,IMAGE_VALUE) values (?, ?)";
		PreparedStatement preparedStatement;
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, imageInfo.getIamgeId());
		InputStream inputStream = new ByteArrayInputStream(imageInfo.getFileInput());
		preparedStatement.setBlob(2, inputStream);
		preparedStatement.executeUpdate();
		return true;
	}

	/**
	 * 
	 * @param IMAGE_KEY
	 *            This method will take INT type IMAGE_KEY as parameter.
	 * @return It will return ImageInfo type value.
	 * @throws SQLException
	 */
	public ImageInfo getImage(int IMAGE_KEY) throws SQLException {
		String sql = "SELECT * FROM image_info WHERE IMAGE_KEY =?";
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		ImageInfo imageInfo = new ImageInfo();
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, IMAGE_KEY);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			imageInfo.setIamgeId(resultSet.getInt("IMAGE_KEY"));
			// (assuming you have a ResultSet named RS)
			Blob blob = resultSet.getBlob("IMAGE_VALUE");
			int blobLength = (int) blob.length();
			byte[] blobAsBytes = blob.getBytes(1, blobLength);
			byte[] base64EncodedData = Base64.encodeBase64(blobAsBytes, true);
			imageInfo.setFileInput(base64EncodedData);
			// release the blob and free up memory. (since JDBC 4.0)
			blob.free();
		}
		return imageInfo;

	}
}
