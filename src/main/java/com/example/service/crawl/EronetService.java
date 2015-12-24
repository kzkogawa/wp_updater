package com.example.service.crawl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.xerces.dom.ElementNSImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.html.HTMLAnchorElement;
import org.w3c.dom.html.HTMLElement;
import org.w3c.dom.html.HTMLImageElement;
import org.w3c.dom.html.HTMLLIElement;
import org.w3c.dom.html.HTMLMetaElement;

import com.example.model.PostServiceModel;
import com.example.service.PostService;
import com.example.util.WpUpdaterUtils;

@Component("EronetService")
public class EronetService implements ICrawlService {
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	PostService postService;

	@Transactional
	@Override
	public void doCrawl(final String target) {
		List<PostServiceModel> serviceModels = new ArrayList<PostServiceModel>();
		Document doc = WpUpdaterUtils.getHtmlDocument(target);

		// parse html
		NodeList imgs = doc.getElementById("leftcolumn").getElementsByTagName("IMG");
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
							String tmp = StringUtils.substringAfter(anc.getHref(), "url=");
							try {
								tmp = getPostSourceUrl(tmp);
							} catch (Exception e) {
								log.error("", e);
							} finally {
								serviceModel.setPostSourceUrl(tmp);
							}
						} else if (NumberUtils.isNumber(anc.getTextContent())) {
							serviceModel.addVideoUrls(anc.getHref());
						}
					}
				}
				log.debug(serviceModel.toString());
				serviceModels.add(serviceModel);
				if (5 <= serviceModels.size()) {
					break;
				}
			}
		}
		postService.InsertPosts(serviceModels);
	}

	private String getPostSourceUrl(String orgUrl) {
		Document doc = WpUpdaterUtils.getHtmlDocument(orgUrl);
		if (StringUtils.contains(orgUrl, "/xvideo-jp.com/")) {
			NodeList items = doc.getElementById("post-" + StringUtils.substringAfterLast(orgUrl, "/")).getElementsByTagName("IMG");
			return items.item(0).getAttributes().getNamedItem("src").getNodeValue();
		} else if (StringUtils.contains(orgUrl, "www.youskbe.com")) {
			NodeList items = ((HTMLElement) doc.getElementsByTagName("ARTICLE").item(0)).getElementsByTagName("IMG");
			return items.item(0).getAttributes().getNamedItem("src").getNodeValue();
		} else if (StringUtils.contains(orgUrl, "blog.livedoor.jp/dogazo")) {
			NodeList items = doc.getElementsByTagName("DIV");
			for (int i = 0, n = items.getLength(); i < n; i++) {
				ElementNSImpl div = (ElementNSImpl) items.item(i);
				if (StringUtils.equals(div.getAttribute("class"), "article-body-inner")) {
					return div.getFirstElementChild().getAttribute("src");
				}
			}
			NodeList imgs = ((HTMLElement) doc.getElementsByTagName("DIV").item(0)).getElementsByTagName("IMG");
			return imgs.item(0).getAttributes().getNamedItem("src").getNodeValue();
		} else if (StringUtils.contains(orgUrl, "omatube00.blog19.fc2.com")) {
			NodeList items = doc.getElementsByTagName("IMG");
			for (int i = 0, n = items.getLength(); i < n; i++) {
				ElementNSImpl item = (ElementNSImpl) items.item(i);
				if (StringUtils.equals(((ElementNSImpl) item.getParentNode()).getAttribute("href"), orgUrl)) {
					return item.getAttribute("src");
				}
			}
		} else if (StringUtils.contains(orgUrl, "erotube.org")) {
			NodeList items = doc.getElementsByTagName("IMG");
			for (int i = 0, n = items.getLength(); i < n; i++) {
				ElementNSImpl item = (ElementNSImpl) items.item(i);
				if (StringUtils.equals(item.getAttribute("alt"), "GO!!")) {
					return item.getAttribute("src");
				}
			}
		} else if (StringUtils.contains(orgUrl, "1129rape.blog.fc2.com")) {
			ElementNSImpl item = (ElementNSImpl) doc.getElementById("iimmgg");
			return ((ElementNSImpl) item.getFirstChild().getFirstChild()).getAttribute("src");
		} else if (StringUtils.contains(orgUrl, "xvideos-field5.com")) {
			NodeList items = doc.getElementsByTagName("LI");
			for (int i = 0, n = items.getLength(); i < n; i++) {
				HTMLLIElement item = (HTMLLIElement) items.item(i);
				if (StringUtils.equals(item.getAttribute("class"), "main_kiji")) {
					return ((HTMLImageElement) item.getFirstChild().getFirstChild()).getSrc();
				}
			}
		} else if (StringUtils.contains(orgUrl, "rakuero-douga.com")) {
			NodeList items = doc.getElementsByTagName("META");
			for (int i = 0, n = items.getLength(); i < n; i++) {
				HTMLMetaElement item = (HTMLMetaElement) items.item(i);
				if (StringUtils.equals(item.getAttribute("property"), "og:image")) {
					return item.getAttribute("content");
				}
			}
		} else if (StringUtils.contains(orgUrl, "xvideos-sm.com")) {
			NodeList items = doc.getElementsByTagName("META");
			for (int i = 0, n = items.getLength(); i < n; i++) {
				ElementNSImpl item = (ElementNSImpl) items.item(i);
				if (StringUtils.equals(item.getAttribute("property"), "og:image")) {
					return item.getAttribute("content");
				}
			}
		} else {
			log.info("[{}] has not been parsed", orgUrl);
		}
		return orgUrl;
	}
}
