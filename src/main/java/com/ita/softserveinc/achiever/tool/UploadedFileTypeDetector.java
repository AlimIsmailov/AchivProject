package com.ita.softserveinc.achiever.tool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.spi.FileTypeDetector;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.microsoft.ooxml.OOXMLParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

/**
 * 
 * @author Ostap Palianytsia
 * 
 */
public class UploadedFileTypeDetector extends FileTypeDetector {

	private static final Tika TIKA = new Tika();
	private static final String DEFAULT_TIKA_MIME_TYPE = "application/x-tika-ooxml";

	@Override
	public String probeContentType(Path path) throws IOException {
		File file = path.toFile();
		String mimeType = TIKA.detect(file);
		FileInputStream fileInputStream = null;

		try {
			if (mimeType.equals(DEFAULT_TIKA_MIME_TYPE)) {
				ContentHandler contentHandler = new BodyContentHandler();
				Metadata metadata = new Metadata();
				metadata.set(Metadata.RESOURCE_NAME_KEY, path.toFile()
						.getName());
				Parser parser = new OOXMLParser();
				fileInputStream = new FileInputStream(file);
				parser.parse(fileInputStream, contentHandler, metadata,
						new ParseContext());
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (TikaException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fileInputStream != null) {
					fileInputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return mimeType;
	}
}
