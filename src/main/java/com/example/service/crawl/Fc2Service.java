package com.example.service.crawl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.example.model.PostServiceModel;
import com.example.service.PostService;
import com.example.util.WpUpdaterUtils;

@Component("Fc2Service")
public class Fc2Service implements ICrawlService {
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	PostService postService;

	@Transactional
	@Override
	public void doCrawl(final String target) {
		List<PostServiceModel> serviceModels = new ArrayList<PostServiceModel>();
		Document doc = WpUpdaterUtils.getXmlDocument(target);
		// parse xml
		NodeList items = doc.getElementsByTagName("item");
		for (int i = 0, n = items.getLength(); i < n; i++) {
			PostServiceModel serviceModel = new PostServiceModel("fc2.ftl");
			serviceModel.setOrgPageUrl(target);
			serviceModel.addTag("FC2");

			Node item = items.item(i);
			NodeList children = item.getChildNodes();
			for (int j = 0, o = children.getLength(); j < o; j++) {
				Node child = children.item(j);
				if (StringUtils.equalsIgnoreCase(child.getNodeName(), "title")) {
					serviceModel.setPostTitle(child.getFirstChild().getNodeValue());
				} else if (StringUtils.equalsIgnoreCase(child.getNodeName(), "link")) {
					serviceModel.setPostSourceUrl(child.getFirstChild().getNodeValue());
				} else if (StringUtils.equalsIgnoreCase(child.getNodeName(), "dc:image")) {
					serviceModel.setPostImageUrl(child.getFirstChild().getNodeValue());
				}
			}
			serviceModels.add(serviceModel);
		}
		postService.InsertPosts(serviceModels);
	}

}
