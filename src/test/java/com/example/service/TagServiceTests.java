package com.example.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.TestBase;
import com.example.model.wp.WpTermRelationships;
import com.example.service.TermService;

public class TagServiceTests extends TestBase {
	@Autowired
	TermService tagService;

	@Test
	public void tag_select() {
		WpTermRelationships relationships = tagService.getWpTermRelationshipsByTagName("手コキ");
		Assert.assertEquals(relationships.getTermTaxonomyId(), Long.valueOf(17));
	}

	@Test
	public void tag_insert_select() {
		WpTermRelationships relationships = tagService.getWpTermRelationshipsByTagName("手コキ手コキ");
		Assert.assertNotEquals(relationships.getTermTaxonomyId(), Long.valueOf(17));
	}
}
