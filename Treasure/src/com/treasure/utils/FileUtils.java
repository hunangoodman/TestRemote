package com.treasure.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.treasure.bean.Area;

public class FileUtils {
	/**
	 * 根据字符串创建本地目录 并按照日期建立子目录返回
	 * 
	 * @param path
	 * @return
	 */
	public static String getFolderByDate(String path) {
		SimpleDateFormat formater = new SimpleDateFormat("yyyyMMdd");
		path += "/" + formater.format(new Date());
		File dir = new File(path);
		if (!dir.exists()) {
			try {
				dir.mkdirs();
			} catch (Exception e) {
				return "";
			}
		}
		return path + "/";
	}
	public static String getFolder(String path) {
		File dir = new File(path+"/");
		if (!dir.exists()) {
			try {
				dir.mkdirs();
			} catch (Exception e) {
				return "";
			}
		}
		return path + "/";
	}

	public static String getReadPath(String savePath, String readPath, String separator) {
		String url = "";
		if (StringUtils.isNotBlank(savePath) && savePath.indexOf(separator) >= 0) {
			String[] ps = savePath.split(separator);
			url = readPath + "/" + separator + ps[ps.length - 1];
		} else {
			url = readPath + "/" + savePath;
		}
		return url;
	}

	public static String getExtName(String path) {
		return path.substring(path.lastIndexOf(".") + 1);
	}

	/**
	 * 获取图片宽度
	 * 
	 * @param file
	 *            图片文件
	 * @return 宽度
	 */
	public static Area getImgArea(MultipartFile file) {
		InputStream is = null;
		BufferedImage src = null;
		Area area = new Area();
		try {
			is = file.getInputStream();
			src = javax.imageio.ImageIO.read(is);
			area.setWidth(src.getWidth(null));// 得到源图宽
			area.setHeight(src.getHeight(null));// 得到源图高
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return area;
	}

	public static Area getImgArea(InputStream is) {
		BufferedImage src = null;
		Area area = new Area();
		try {
			src = javax.imageio.ImageIO.read(is);
			area.setWidth(src.getWidth(null));// 得到源图宽
			area.setHeight(src.getHeight(null));// 得到源图高
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return area;
	}

	public static BufferedImage zoomImage(String path) {
		try {
			URL url = new URL(path);
			InputStream is = url.openStream();
			Area area = FileUtils.getImgArea(is);
			if (null != area) {
				Integer width = area.getWidth();
				Integer height = area.getHeight();
				if (null == width) {
					width = 0;
				}
				if (null == height) {
					height = 0;
				}
				area = getArea(width, height);
				if (null != area) {
					width = area.getWidth();
					height = area.getHeight();
					is = url.openStream();
					try {
						BufferedImage src = ImageIO.read(is);
						BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
						tag.getGraphics().drawImage(src, 0, 0, width, height, null);
						is.close();
						return tag;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static Area getArea(Integer width, Integer height) {
		if (width < 550 || height < 550) {
			Area area = new Area();
			Integer min = width > height ? height : width;
			Integer percent = (550 + 1) * 1000 / min;
			area.setWidth(width * percent / 1000);
			area.setHeight(height * percent / 1000);
			return area;
		}
		return null;
	}
}
