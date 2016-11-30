package org.siqisource.stone.runtime.utils;

import java.io.FileInputStream;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jxls.transformer.XLSTransformer;

import org.apache.poi.ss.usermodel.Workbook;

public class FileUtils {

	public static void exportXls(HttpServletResponse response,
			Map<String, Object> beans, String templateFile, String fileName,
			boolean nameWithTime) {
		try {
			if (nameWithTime) {
				fileName = fileName + System.currentTimeMillis();
			}
			fileName = fileName + ".xls";
			fileName = new String(fileName.getBytes("gb2312"), "iso8859-1");
			// set output header
			ServletOutputStream os = response.getOutputStream();
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ fileName + "\"");

			XLSTransformer transformer = new XLSTransformer();

			Workbook workbook = transformer.transformXLS(new FileInputStream(
					templateFile), beans);
			workbook.write(os);
			os.flush();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
