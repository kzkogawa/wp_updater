package com.example.service.crawl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
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

@Component("ThisAvService")
public class ThisAvService implements ICrawlService {
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	PostService postService;

	@Transactional
	@Override
	public void doCrawl(final String target) {
		List<PostServiceModel> serviceModels = new ArrayList<PostServiceModel>();
		Document doc = WpUpdaterUtils.getHtmlDocument(target);
		NodeList divs = doc.getElementsByTagName("DIV");
		log.debug("{} divs found", divs.getLength());
		for (int i = 0, n = divs.getLength(); i < n; i++) {
			ElementNSImpl div = (ElementNSImpl) divs.item(i);
			if (div.hasAttribute("class") && StringUtils.endsWithIgnoreCase(div.getAttribute("class"), "video_box")) {
				log.debug("target div found {}", div);
				PostServiceModel serviceModel = new PostServiceModel("thisav.ftl");
				serviceModel.addTag("ThisAv");
				ElementNSImpl anc = (ElementNSImpl) div.getFirstElementChild();
				String sourceUrl = anc.getAttribute("href");
				serviceModel.setPostSourceUrl(sourceUrl);
				ElementNSImpl img = (ElementNSImpl) anc.getFirstElementChild();
				serviceModel.setPostImageUrl(img.getAttribute("src"));
				serviceModel.setPostTitle(img.getAttribute("title"));

				// find flush source
				Document subDoc = WpUpdaterUtils.getHtmlDocument(sourceUrl);
				NodeList scripts = subDoc.getElementsByTagName("SCRIPT");
				log.debug("{} scripts found", scripts.getLength());
				for (int j = 0, o = scripts.getLength(); j < o; j++) {
					ElementNSImpl script = (ElementNSImpl) scripts.item(j);
					String content = script.getTextContent();
					if (script.hasAttribute("type") && !script.hasAttribute("src") && StringUtils.contains(content, "SWFObject")) {
						log.debug("flash content information - {}", content);
						serviceModel.addMap("flvUrl", StringUtils.substringBetween(content, "file','", "'"));
						break;
					}
				}
				log.debug(serviceModel.toString());
				serviceModels.add(serviceModel);
			}
		}
		postService.InsertPosts(serviceModels);
	}
}
