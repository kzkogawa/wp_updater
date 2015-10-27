package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.xerces.dom.ElementNSImpl;
import org.cyberneko.html.parsers.DOMParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.html.HTMLAnchorElement;
import org.w3c.dom.html.HTMLElement;
import org.w3c.dom.html.HTMLImageElement;

import com.example.model.PostServiceModel;
import com.example.util.WpUpdaterUtils;

@Component
public class EronetService implements ICrawlService {
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	PostService postService;

	@Transactional
	@Override
	public void doCrawl(final String target) {
		log.debug(target);
		int counter = 0;

		List<PostServiceModel> serviceModels = new ArrayList<PostServiceModel>();
		DOMParser neko = WpUpdaterUtils.getDOMParserInstance(target);

		// parse html
		NodeList imgs = neko.getDocument().getElementById("leftcolumn").getElementsByTagName("IMG");
		for (int i = 0, n = imgs.getLength(); i < n; i++) {
			HTMLImageElement gif = (HTMLImageElement) imgs.item(i);
			if (gif.getParentNode().getNodeName().equals("TD")) {
				PostServiceModel serviceModel = new PostServiceModel("eronet.ftl");
				serviceModel.addCategorys("エロ動画");
				if (StringUtils.contains(target, "jk001.html")) {
					serviceModel.addTag("女子高生");
				} else if (StringUtils.contains(target, "kn001.html")) {
					serviceModel.addTag("巨乳");
				} else if (StringUtils.contains(target, "s4001.html")) {
					serviceModel.addTag("Xvideos");
				} else if (StringUtils.contains(target, "s7001.html")) {
					serviceModel.addTag("Xhamster");
				}

				serviceModel.setOrgPageUrl(target);
				for (int j = 0, o = gif.getParentNode().getChildNodes().getLength(); j < o; j++) {
					Node node = gif.getParentNode().getChildNodes().item(j);
					if (node instanceof HTMLAnchorElement) {
						HTMLAnchorElement anc = (HTMLAnchorElement) node;
						if (anc.getFirstChild().getNodeName().equals("IMG")) {
							HTMLImageElement img = (HTMLImageElement) anc.getFirstChild();
							serviceModel.setPostImageUrl(img.getSrc());
							serviceModel.setPostTitle(img.getAlt());
							serviceModel.setPostSourceUrl(getPostSourceUrl(StringUtils.substringAfter(anc.getHref(), "url=")));
						} else if (NumberUtils.isNumber(anc.getTextContent())) {
							serviceModel.addVideoUrls(anc.getHref());
						}
					}
				}
				serviceModels.add(serviceModel);
				if (counter++ > 3) {
					break;
				}
			}
		}
		postService.InsertPosts(serviceModels);
	}

	private String getPostSourceUrl(String orgUrl) {
		DOMParser neko = WpUpdaterUtils.getDOMParserInstance(orgUrl);
		if (StringUtils.contains(orgUrl, "/xvideo-jp.com/")) {
			NodeList imgs = neko.getDocument().getElementById("post-" + StringUtils.substringAfterLast(orgUrl, "/")).getElementsByTagName("IMG");
			return imgs.item(0).getAttributes().getNamedItem("src").getNodeValue();
		} else if (StringUtils.contains(orgUrl, "www.youskbe.com")) {
			NodeList imgs = ((HTMLElement) neko.getDocument().getElementsByTagName("ARTICLE").item(0)).getElementsByTagName("IMG");
			return imgs.item(0).getAttributes().getNamedItem("src").getNodeValue();
		} else if (StringUtils.contains(orgUrl, "blog.livedoor.jp/dogazo")) {
			NodeList divs = neko.getDocument().getElementsByTagName("DIV");
			for (int i = 0, n = divs.getLength(); i < n; i++) {
				ElementNSImpl div = (ElementNSImpl) divs.item(i);
				if (StringUtils.equals(div.getAttribute("class"), "article-body-inner")) {
					return div.getFirstElementChild().getAttribute("src");
				}
			}
			NodeList imgs = ((HTMLElement) neko.getDocument().getElementsByTagName("DIV").item(0)).getElementsByTagName("IMG");
			return imgs.item(0).getAttributes().getNamedItem("src").getNodeValue();
		} else {
			log.info("[%s] has not been parsed", orgUrl);
		}
		return orgUrl;
	}
}
