package com.example.util;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.net.URLCodec;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.cyberneko.html.parsers.DOMParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class WpUpdaterUtils {
	private static final Logger log = LoggerFactory.getLogger(WpUpdaterUtils.class);

	private WpUpdaterUtils() {
	}

	public static final String CONST_BASE_URL = "http://www.x-videos.space/";
	public static final String CONST_IMAGE_URL = CONST_BASE_URL + "wp-content/uploads/";
	public static final String CONST_IMAGE_STORE_DIR = "wp-content/uploads/";
	public static final String CONST_DOC_ROOT_RID = "/var/www/html/xvideos/";

	public static final String CONST_POST_STATUS_PUBLISH = "publish";
	public static final String CONST_POST_STATUS_INHERIT = "inherit";
	public static final String CONST_POST_STATUS_TRASH = "trash";
	public static final String CONST_COMMENT_STATUS_CLOSED = "closed";
	public static final String CONST_PING_STATUS_CLOSED = "closed";
	public static final String CONST_TAXONOMY_CATEGORY = "category";
	public static final String CONST_TAXONOMY_POST_TAG = "post_tag";
	public static final String CONST_POST_TYPE_POST = "post";
	public static final String CONST_POST_TYPE_REVISION = "revision";
	public static final String CONST_POST_TYPE_ATTACH = "attachment";

	public static final String CONST_POST_META_EDIT_LAST = "_edit_last";
	public static final String CONST_POST_META_EDIT_LOCK = "_edit_lock";
	public static final String CONST_POST_META_THUMB_ID = "_thumbnail_id";
	public static final String CONST_POST_META_JRPC = "_jetpack_related_posts_cache";
	public static final String CONST_POST_META_ATTACH_FILE = "_wp_attached_file";
	public static final String CONST_POST_META_ATTACH_META = "_wp_attachment_metadata";

	public static Document getHtmlDocument(String string) {
		DOMParser neko = new DOMParser();
		try (InputStream is = getConnection(string).getInputStream()) {
			neko.parse(new InputSource(is));
		} catch (Exception e) {
			log.error("", e);
		}
		return neko.getDocument();
	}

	public static Document getXmlDocument(String string) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try (InputStream is = getConnection(string).getInputStream()) {
			DocumentBuilder builder = factory.newDocumentBuilder();
			return builder.parse(new InputSource(is));
		} catch (Exception e) {
			log.error("", e);
		}
		return null;
	}

	public static List<String> readLines(String string) {
		List<String> lines = new ArrayList<String>();
		try (InputStream is = getConnection(string).getInputStream()) {
			lines = IOUtils.readLines(is, "UTF-8");
		} catch (Exception e) {
			log.error("", e);
		}
		return lines;
	}

	private static URLConnection getConnection(String string) throws MalformedURLException, IOException {
		URLConnection urlConnection = new URL(string).openConnection();
		urlConnection.setRequestProperty("User-Agent", "curl/7.45.0");
		return urlConnection;
	}

	public static String urlEncode(String name) {
		URLCodec codec = new URLCodec("UTF-8");
		try {
			return codec.encode(name);
		} catch (EncoderException e) {
			log.error("", e);
		}
		return name;
	}

	public static Date getCurrentUtcTime() {
		return getCurrentDate("UTC");
	}

	public static Date getCurrentGmtTime() {
		return getCurrentDate("GMT");
	}

	private static Date getCurrentDate(String timeZone) {
		String DATE_CHANGE_FORMAT = "yyyyMMdd HH:mm:ss";
		Date jst = Calendar.getInstance().getTime();
		String utc = DateFormatUtils.format(jst, DATE_CHANGE_FORMAT, TimeZone.getTimeZone(timeZone));
		try {
			return DateUtils.parseDate(utc, DATE_CHANGE_FORMAT);
		} catch (ParseException e) {
			log.error("", e);
		}
		return jst;
	}

	public static String getPostGuid(long postId) {
		return CONST_BASE_URL + String.format("?p=%s", postId);
	}

	public static String getRevisionGuid(long postId) {
		return CONST_BASE_URL + String.format("archives/%s", postId);
	}

	public static String getAttachGuidBase(String fileName) {
		return CONST_IMAGE_STORE_DIR + getAttacheMetaValue(fileName);
	}

	public static String getAttachGuid(String fileName) {
		return CONST_BASE_URL + getAttachGuidBase(fileName);
	}

	public static String getContentFromTemplate(String name, Object dataModel) {
		String ret = "";
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
		try {
			cfg.setDirectoryForTemplateLoading(Paths.get("src/main/resources/freemarker").toFile());
			cfg.setDefaultEncoding("UTF-8");
			Template template = cfg.getTemplate(name);
			try (StringWriter output = new StringWriter()) {
				template.process(dataModel, output);
				ret = output.toString();
			} catch (Exception e) {
				throw e;
			}
		} catch (Exception e) {
			log.error("", e);
		}
		log.debug(ret);
		return ret;
	}

	public static File getUploadFile(String fileNameEx) {
		return new File(CONST_DOC_ROOT_RID + getAttachGuidBase(fileNameEx));
	}

	public static String getAttacheMetaValue(String fileName) {
		Calendar c = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		return String.format("%s/%02d/%s", c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1, fileName);
	}

	public static Map<String, Integer> saveImage(String postImageUrl, String imageFileNameWithExtension) {
		try {
			FileUtils.copyInputStreamToFile(getConnection(postImageUrl).getInputStream(), getUploadFile(imageFileNameWithExtension));
			return resizeImage(getUploadFile(imageFileNameWithExtension));
		} catch (Exception e) {
			log.error("", e);
		}
		return null;
	}

	public static Map<String, Integer> resizeImage(File uploadFile) throws IOException {
		Map<String, Integer> imageInfo = new java.util.HashMap<String, Integer>();
		BufferedImage bImage = ImageIO.read(uploadFile);
		int width = bImage.getWidth(), height = bImage.getHeight();
		imageInfo.put("width", width);
		imageInfo.put("height", height);
		int bigger = width > height ? width : height, smaller = width > height ? height : width;
		int tmpBigger = 250, tmpSmaller = (int) (tmpBigger * ((double) smaller / bigger));
		if (bigger * smaller < tmpBigger * tmpSmaller) {
			log.info("[{}] width={}, height={} will be resized", uploadFile.getName(), width, height);
			if (width > height) {
				width = tmpBigger;
				height = tmpSmaller;
			} else {
				width = tmpSmaller;
				height = tmpBigger;
			}
			imageInfo.put("width", width);
			imageInfo.put("height", height);
			BufferedImage newBImage = new BufferedImage(width, height, bImage.getType());
			Graphics2D graphics2d = newBImage.createGraphics();
			graphics2d.drawImage(bImage.getScaledInstance(width, height, bImage.getType()), 0, 0, null);
			graphics2d.dispose();
			ImageIO.write(newBImage, FilenameUtils.getExtension(uploadFile.getPath()), uploadFile);
		}
		return imageInfo;
	}
}
