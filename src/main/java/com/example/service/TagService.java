package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.mapper.WpTermRelationshipsMapper;
import com.example.mapper.WpTermTaxonomyMapper;
import com.example.mapper.WpTermsMapper;
import com.example.model.wp.WpTermRelationships;
import com.example.model.wp.WpTermTaxonomy;
import com.example.model.wp.WpTermTaxonomyCriteria;
import com.example.model.wp.WpTerms;
import com.example.model.wp.WpTermsCriteria;
import com.example.util.WpUpdaterUtils;

@Component
public class TagService {
	private final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private WpTermsMapper termsMapper;
	@Autowired
	private WpTermRelationshipsMapper relationshipsMapper;
	@Autowired
	private WpTermTaxonomyMapper taxonomyMapper;

	public WpTermRelationships getWpTermRelationships(String name) {
		WpTermRelationships ret = new WpTermRelationships();
		ret.setTermOrder(0);
		
		// search tag
		WpTermsCriteria termsCriteria = new WpTermsCriteria();
		termsCriteria.createCriteria().andNameEqualTo(name);
		List<Long> termIds = termsMapper.selectByExample(termsCriteria).stream().map(s -> s.getTermId()).collect(Collectors.toList());

		WpTermTaxonomyCriteria taxonomyCriteria = new WpTermTaxonomyCriteria();
		taxonomyCriteria.createCriteria().andTaxonomyEqualTo("post_tag").andTermIdIn(termIds);
		List<WpTermTaxonomy> taxonomies = termIds.isEmpty() ? new ArrayList<WpTermTaxonomy>() : taxonomyMapper.selectByExample(taxonomyCriteria);
		log.debug("", taxonomies);
		if (taxonomies.isEmpty()) {
			// create new tag
			WpTerms newTerm = new WpTerms();
			newTerm.setName(name);
			newTerm.setSlug(WpUpdaterUtils.urlEncode(name));
			newTerm.setTermGroup(Long.valueOf(0));
			termsMapper.insert(newTerm);

			WpTermTaxonomy taxonomy = new WpTermTaxonomy();
			taxonomy.setTermId(newTerm.getTermId());
			taxonomy.setTaxonomy("post_tag");
			taxonomy.setDescription("");
			taxonomy.setParent(Long.valueOf(0));
			taxonomy.setCount(Long.valueOf(0));
			taxonomyMapper.insert(taxonomy);

			ret.setTermTaxonomyId(taxonomy.getTermTaxonomyId());
		} else {
			// taxonomies.size() should be 1
			ret.setTermTaxonomyId(taxonomies.get(0).getTermTaxonomyId());
		}
		return ret;
	}
}
