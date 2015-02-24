package com.ita.softserveinc.achiever.service;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.ita.softserveinc.achiever.exception.InvalidFileTypeException;

/**
 * 
 * @author Ostap Palianytsia
 * 
 */
@Component
public interface IFileHandlerService {
	
	String saveFileOnServer(MultipartFile file, String title)
			throws InvalidFileTypeException;
	
	void downloadFileFromServer(HttpServletResponse response, Long id);
}
