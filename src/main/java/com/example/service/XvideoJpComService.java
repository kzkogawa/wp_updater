package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.PostServiceModel;
import com.example.util.WpUpdaterUtils;

@Component("XvideoJpComService")
public class XvideoJpComService implements ICrawlService {
	private final Logger log = LoggerFactory.getLogger(getClass());
	private static final int SKIP_SIZE = 11;
	@Autowired
	PostService postService;

	@Transactional
	@Override
	public void doCrawl(String target) {

		List<PostServiceModel> serviceModels = new ArrayList<PostServiceModel>();
		List<String> lines = WpUpdaterUtils.readLines(target);
		for (int i = 1, n = lines.size(); i < n; i += SKIP_SIZE) {
			String dmm = parseLineSafe(lines, n, i, 8);
			if (!StringUtils.isEmpty(dmm)) {
				PostServiceModel serviceModel = new PostServiceModel("eronet.ftl");
				serviceModel.addCategorys("エロ動画");
				serviceModel.setOrgPageUrl(target);
				serviceModel.setPostSourceUrl(parseLineSafe(lines, n, i, 1));
				serviceModel.setPostTitle(parseLineSafe(lines, n, i, 2));
				serviceModel.setPostImageUrl("http://rank.xvideo-jp.com/" + parseLineSafe(lines, n, i, 3));
				serviceModel.addVideoUrls(dmm);
				serviceModels.add(serviceModel);
				log.debug(serviceModel.toString());
				if (5 <= serviceModels.size()) {
					break;
				}
			}
		}
		postService.InsertPosts(serviceModels);
	}

	private String parseLineSafe(List<String> lines, int n, int i, int j) {
		if (i + j < n) {
			return StringUtils.substringBetween(lines.get(i + j), "\"");
		}
		log.debug("Out of index == {}:{}", i + j, n);
		return null;
	}

}
