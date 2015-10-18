package com.example.service;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.cyberneko.html.parsers.DOMParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.html.HTMLAnchorElement;
import org.w3c.dom.html.HTMLImageElement;

import com.example.mapper.WpPostmetaMapper;
import com.example.model.EronetModel;
import com.example.util.WpUpdaterUtils;

@Component
public class EronetService implements ICrawlService {
	private final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private WpPostmetaMapper postmetaMapper;

	@Transactional
	@Override
	public void doCrawl() {
		DOMParser neko = WpUpdaterUtils.getDOMParserInstance("http://xxeronetxx.info/ranking1day.html");
		NodeList imgs = neko.getDocument().getElementById("leftcolumn").getElementsByTagName("IMG");
		for (int i = 0, n = imgs.getLength(); i < n; i++) {
			HTMLImageElement gif = (HTMLImageElement) imgs.item(i);
			if (gif.getParentNode().getNodeName().equals("TD")) {
				EronetModel eronetModel = new EronetModel();
				for (int j = 0, o = gif.getParentNode().getChildNodes().getLength(); j < o; j++) {
					Node node = gif.getParentNode().getChildNodes().item(j);
					if (node instanceof HTMLAnchorElement) {
						HTMLAnchorElement anc = (HTMLAnchorElement) node;
						if (anc.getFirstChild().getNodeName().equals("IMG")) {
							HTMLImageElement img = (HTMLImageElement) anc.getFirstChild();
							eronetModel.setImageSrc(img.getSrc());
							eronetModel.setImageAlt(img.getAlt());
							eronetModel.setImageLink(StringUtils.substringAfter(anc.getHref(), "url="));
						} else if (NumberUtils.isNumber(anc.getTextContent())) {
							eronetModel.getVideoUrls().add(anc.getHref());
						}
					}
				}
				insertRecode(eronetModel);
			}
		}
	}

	private void insertRecode(final EronetModel eronetModel) {
		log.debug("", eronetModel);

	}
}
