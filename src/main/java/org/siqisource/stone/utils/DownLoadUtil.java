package org.siqisource.stone.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class DownLoadUtil {

	public static HttpHeaders prepareDownloadHeaders(String fileName) {
		String isoFileName = "Download File";
		try {
			isoFileName = new String(fileName.getBytes("utf-8"), "ISO8859-1");
		} catch (Exception e) {
			// TODO: handle exception
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDispositionFormData("attachment", isoFileName);
		return headers;
	}
}
