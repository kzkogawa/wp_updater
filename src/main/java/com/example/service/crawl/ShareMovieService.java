package com.example.service.crawl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.html.dom.HTMLAnchorElementImpl;
import org.apache.html.dom.HTMLDivElementImpl;
import org.apache.html.dom.HTMLImageElementImpl;
import org.apache.xerces.dom.ElementNSImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.example.model.PostServiceModel;
import com.example.service.PostService;
import com.example.util.WpUpdaterUtils;

@Component("ShareMovieService")
public class ShareMovieService implements ICrawlService {
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	PostService postService;

	@Transactional
	@Override
	public void doCrawl(final String target) {
		List<PostServiceModel> serviceModels = new ArrayList<PostServiceModel>();
		Document doc = WpUpdaterUtils.getHtmlDocument(target);
		NodeList imgs = doc.getElementsByTagName("IMG");
		log.debug("{} imgs found", imgs.getLength());
		for (int i = 0, n = imgs.getLength(); i < n; i++) {
			HTMLImageElementImpl img = (HTMLImageElementImpl) imgs.item(i);
			if (img.hasAttribute("class") && StringUtils.endsWithIgnoreCase(img.getAttribute("class"), "thumb")) {
				log.debug("target img found {}", img);
				PostServiceModel serviceModel = new PostServiceModel("sharemovie.ftl");
				serviceModel.addTag("ShareMovie");

				HTMLAnchorElementImpl anc = (HTMLAnchorElementImpl) img.getParentNode();
				serviceModel.setOrgPageUrl(target);
				serviceModel.setPostSourceUrl("http://smv.to" + anc.getHref());
				serviceModel.setPostImageUrl(img.getAttribute("src"));
				serviceModel.setPostTitle(img.getAttribute("alt"));
				serviceModels.add(serviceModel);
				log.debug(serviceModel.toString());
				if (5 <= serviceModels.size()) {
					break;
				}
			}
		}
		postService.InsertPosts(serviceModels);
	}
}
