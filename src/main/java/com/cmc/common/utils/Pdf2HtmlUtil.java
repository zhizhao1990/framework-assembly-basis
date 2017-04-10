package com.cmc.common.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.util.Args;
import org.slf4j.Logger;

/**
 * 调用系统服务 pdf2html，所以要保证宿主机中安装有pdf2html.
 * @author ucmed
 */
public final class Pdf2HtmlUtil {

	private static final int DEFAULT_ERROR_CODE = -1;
	public static final Logger LOG = org.slf4j.LoggerFactory.getLogger(Pdf2HtmlUtil.class);

	private Pdf2HtmlUtil() {
		// 工具类不允许初始化
		throw new RuntimeException("Tye initialize a util class.");
	}

	/**
	 * @param sourcePath
	 *            the path of directory where the PDF file exists. Not null.
	 * @param destPath
	 *            the path of directory for converted html file. Not null.
	 * @param tempDirPath
	 *            the path of directory for tmp file. Not null.
	 * @param fileName
	 *            the file name of PDF file.
	 * @return result string of successful excution of command, or throw
	 *         exception if something take wrong infomation.
	 */
	public static String pdf2Html(String sourcePath, String destPath, String tempDirPath, String fileName) {
		Args.notBlank(sourcePath, "源文件地址不可为空");
		Args.notBlank(destPath, "目标文件地址不可为空");
		Args.notBlank(tempDirPath, "暂存地址不可为空");
		String cmd = "pdf2htmlEX --tmp-dir " + tempDirPath + " --zoom 1.3 --dest-dir " + destPath + " " + sourcePath + " " + Args.notNull(fileName, "待转化文件")// fileName
				+ ".html";
		// String cmd = "pdf2htmlEX --help";
		LOG.debug("CMD is :" + cmd);
		String result = "";
		BufferedReader br = null;
		Process p = null;
		Runtime run = Runtime.getRuntime();
		int exitValue = DEFAULT_ERROR_CODE;
		try {
			String lineStr;
			p = run.exec(cmd);
			exitValue = p.waitFor();
			if (exitValue != 0) {
				br = new BufferedReader(new InputStreamReader(p.getErrorStream()));
			} else {
				br = new BufferedReader(new InputStreamReader(new BufferedInputStream(p.getInputStream())));
			}
			while ((lineStr = br.readLine()) != null) {
				result += lineStr;
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					LOG.error(e.getMessage(), e);
				}
			}
			LOG.debug("ExitValue is :" + exitValue);
			LOG.debug("Translate resutl is :" + result);
			LOG.debug("ShellUtil.ExeShell=>" + result);
		}
		if (0 == exitValue) {
			return result;
		} else {
		}
		return result;
	}

}
