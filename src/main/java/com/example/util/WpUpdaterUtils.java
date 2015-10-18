package com.example.util;

import java.io.InputStream;
import java.net.URL;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.net.URLCodec;
import org.cyberneko.html.parsers.DOMParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;

public class WpUpdaterUtils {
	private static final Logger log = LoggerFactory.getLogger(WpUpdaterUtils.class);

	public static DOMParser getDOMParserInstance(String string) {
		DOMParser neko = new DOMParser();
		try (InputStream is = new URL("http://xxeronetxx.info/ranking1day.html").openConnection().getInputStream()) {
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

}
