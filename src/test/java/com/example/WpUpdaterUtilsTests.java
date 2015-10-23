package com.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import com.example.model.PostServiceModel;
import com.example.util.WpUpdaterUtils;

public class WpUpdaterUtilsTests {
	@Test
	public void getContentFromTemplate() {
		PostServiceModel dataModel = new PostServiceModel("eronet.ftl");
		dataModel.setPostTitle("alt");
		dataModel.setPostImageUrl("http://link.com");
		dataModel.setPostSourceUrl("http://src.com");
		dataModel.addVideoUrls("http://video1.com");
		dataModel.addVideoUrls("http://video2.com");
		String content = dataModel.getPostContent();
		Assert.assertNotEquals("", content);
	}

	@Test
	public void ImageTest() throws IOException {
		File f = Paths.get("src/test/java/com/example/1445318654713-2.jpg").toFile();
		FileUtils.deleteQuietly(f);
		FileUtils.copyFile(Paths.get("src/test/java/com/example/1445318654713.jpg").toFile(), f);
		WpUpdaterUtils.resizeImage(f);
	}
}
