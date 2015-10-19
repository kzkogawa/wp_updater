package com.example;

import org.junit.Assert;
import org.junit.Test;

import com.example.model.EronetModel;
import com.example.util.WpUpdaterUtils;

public class WpUpdaterUtilsTests {
	@Test
	public void getContentFromTemplate() {
		EronetModel dataModel = new EronetModel();
		dataModel.setImageAlt("alt");
		dataModel.setImageLink("http://link.com");
		dataModel.setImageSrc("http://src.com");
		dataModel.getVideoUrls().add("http://video1.com");
		dataModel.getVideoUrls().add("http://video2.com");
		String content = WpUpdaterUtils.getContentFromTemplate("eronet.ftl", dataModel);
		Assert.assertNotEquals("", content);
	}
}
