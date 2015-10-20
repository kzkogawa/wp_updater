package com.example.util;

import java.io.File;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.net.URLCodec;
import org.cyberneko.html.parsers.DOMParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	public static DOMParser getDOMParserInstance(String string) {
		DOMParser neko = new DOMParser();
		try (InputStream is = new URL(string).openConnection().getInputStream()) {
			neko.parse(new InputSource(is));
		} catch (Exception e) {
			log.error("", e);
		}
		return neko;
	}

	public static String urlEncode(String name) {
		URLCodec codec = new URLCodec("utf-8");
		try {
			return codec.encode(name);
		} catch (EncoderException e) {
			log.error("", e);
		}
		return name;
	}

	public static Date getCurrentUtcTime() {
		return Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTime();
	}

	public static Date getCurrentGmtTime() {
		return Calendar.getInstance(TimeZone.getTimeZone("GMT")).getTime();
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
		return String.format("%s/%02d/%s", c.get(Calendar.YEAR), c.get(Calendar.MONTH), fileName);
	}
}
