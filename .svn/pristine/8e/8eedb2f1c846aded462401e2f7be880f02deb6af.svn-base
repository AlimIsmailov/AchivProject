package com.ita.softserveinc.achiever.tool;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.ita.softserveinc.achiever.service.EventServiceImpl;

/**
 * 
 * @author Vasyl Zazuliak
 * 
 */
@Component
public class FileUploaderDownloader {
		
	/** The Constant logger. */
	private static final Logger logger = LoggerFactory
			.getLogger(EventServiceImpl.class);
	/* 
	 * Handle CSV File from JSP Form and write it to storage
	 */
	public void uploadFile(String name, MultipartFile file) {

		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();

				// Creating the directory to store file
				String rootPath = "D:/Programs/eclipse";
				File dir = new File(rootPath + File.separator);
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath()
						+ File.separator + name);
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				logger.info("Server File Location="
						+ serverFile.getAbsolutePath());
				logger.info("You successfully uploaded file=" + name);
			} catch (Exception e) {
				logger.info("You failed to upload " + name + " => "
						+ e.getMessage());
			}
		} else {
			logger.info("You failed to upload " + name
					+ " because the file was empty.");
		}
	}

	/* 
	 * Download file from storage to user
	 */

	public void downloadFile(HttpServletResponse response) {

		String filePath = "D:/Programs/eclipse/events.csv";
		File downloadFile = new File(filePath);

		Path path = downloadFile.toPath();
		String mimeType = null;
		UploadedFileTypeDetector fileTypeDetector = new UploadedFileTypeDetector();
		try {
			mimeType = fileTypeDetector.probeContentType(path);
		} catch (IOException e) {
			logger.info("There was a problem in getting mimeType from file");
		}
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"",
				Consts.CSV_FILENAME);
		FileInputStream fileInputStream = null;
		OutputStream outputStream = null;

		try {
			fileInputStream = new FileInputStream(downloadFile);
			response.setContentType(mimeType);
			response.setHeader(headerKey, headerValue);
			outputStream = response.getOutputStream();
		} catch (FileNotFoundException e) {
			logger.info("File not found");
		} catch (IOException e) {
			e.printStackTrace();
		}

		byte[] buffer = new byte[Consts.BUFFER_SIZE];
		int bytesRead = -1;
		try {
			while ((bytesRead = fileInputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fileInputStream.close();
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
