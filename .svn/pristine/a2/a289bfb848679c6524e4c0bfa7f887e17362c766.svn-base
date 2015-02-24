package com.ita.softserveinc.achiever.service;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ita.softserveinc.achiever.entity.Article;
import com.ita.softserveinc.achiever.exception.InvalidFileTypeException;
import com.ita.softserveinc.achiever.tool.UploadedFileTypeDetector;

/**
 * 
 * @author Ostap Palianytsia
 * 
 */
@Service
@Transactional
public class FileHandlerServiceImpl implements IFileHandlerService {

	private static final Logger LOG = LoggerFactory
			.getLogger(ArticleServiceImpl.class);
	private static final int BUFFER_SIZE = 4096;
	private static final String[] MIME_TYPES = {
			"application/pdf",
			"application/msword",
			"application/vnd.openxmlformats-officedocument.wordprocessingml.document",
			"application/vnd.ms-excel",
			"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
			"application/vnd.ms-powerpoint",
			"application/vnd.openxmlformats-officedocument.presentationml.presentation" };

	@Autowired
	private IArticleService articleService;

	@Override
	public String saveFileOnServer(MultipartFile file, String title)
			throws InvalidFileTypeException {
		if (!isValid(file)) {
			LOG.info("Invalid file type");
			throw new InvalidFileTypeException("Invalid file type");
		}

		String url = null;
		FileOutputStream fileOutputStream = null;
		BufferedOutputStream bufferedOutputStream = null;

		try {
			byte[] bytes = file.getBytes();

			File dir = new File(System.getProperty("catalina.home")
					+ File.separator + "Articles");
			if (!dir.exists()) {
				dir.mkdirs();
			}

			File serverFile = new File(dir.getAbsolutePath() + File.separator
					+ title);
			fileOutputStream = new FileOutputStream(serverFile);
			bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
			bufferedOutputStream.write(bytes);

			url = serverFile.getAbsolutePath();

			LOG.info("File saved successfuly. Server File Location=" + url);

			return url;

		} catch (IOException e) {
			LOG.info("File saving failed");
			return null;
		} finally {
			try {
				bufferedOutputStream.close();
				fileOutputStream.close();
			} catch (IOException e) {
				LOG.info(
						"Something goes wrong, please feel free to contact with site administrator",
						e);
			}
		}
	}

	private boolean isValid(MultipartFile file) {
		String articleMimeType = file.getContentType();
		for (String mimeType : MIME_TYPES) {
			if (articleMimeType.equals(mimeType)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void downloadFileFromServer(HttpServletResponse response, Long id) {
		Article article = articleService.findById(id);
		File downloadFile = new File(article.getUrl());
		Path path = downloadFile.toPath();
		String mimeType = null;
		UploadedFileTypeDetector fileTypeDetector = new UploadedFileTypeDetector();
		try {
			mimeType = fileTypeDetector.probeContentType(path);
		} catch (IOException e) {
			LOG.info("There was a problem in getting mimeType from file", e);
		}
		FileInputStream fileInputStream = null;
		OutputStream outputStream = null;

		try {
			fileInputStream = new FileInputStream(downloadFile);
			response.setContentType(mimeType);
			outputStream = response.getOutputStream();
		} catch (FileNotFoundException e) {
			LOG.info("File not found", e);
		} catch (IOException e) {
			LOG.info(
					"Something goes wrong, please feel free to contact with site administrator",
					e);
		}

		byte[] buffer = new byte[BUFFER_SIZE];
		int bytesRead = -1;

		try {
			while ((bytesRead = fileInputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}
		} catch (IOException e) {
			LOG.info(
					"Something goes wrong, please feel free to contact with site administrator",
					e);
		} finally {
			try {
				fileInputStream.close();
				outputStream.close();
			} catch (IOException e) {
				LOG.info(
						"Something goes wrong, please feel free to contact with site administrator",
						e);
			}
		}
	}

}
