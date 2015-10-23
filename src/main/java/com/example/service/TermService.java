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
public class TermService {
	private final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private WpTermsMapper termsMapper;
	@Autowired
	private WpTermRelationshipsMapper relationshipsMapper;
	@Autowired
	private WpTermTaxonomyMapper taxonomyMapper;

	public List<WpTermTaxonomy> selectByTagName(String name) {
		return selectByName(name, WpUpdaterUtils.CONST_TAXONOMY_POST_TAG);
	}

	public List<WpTermTaxonomy> selectByCategoryName(String name) {
		return selectByName(name, WpUpdaterUtils.CONST_TAXONOMY_CATEGORY);
	}

	public WpTermRelationships getWpTermRelationshipsByTagName(String name) {
		return getWpTermRelationships(name, WpUpdaterUtils.CONST_TAXONOMY_POST_TAG);
	}

	public WpTermRelationships getWpTermRelationshipsByCategoryName(String name) {
		return getWpTermRelationships(name, WpUpdaterUtils.CONST_TAXONOMY_CATEGORY);
	}

	private WpTermRelationships getWpTermRelationships(String name, String tax) {
		WpTermRelationships ret = new WpTermRelationships();
		ret.setTermOrder(0);
		List<WpTermTaxonomy> taxonomies = selectByName(name, tax);
		if (taxonomies.isEmpty()) {
			WpTermTaxonomy taxonomy = createNewTerm(name, tax);
			ret.setTermTaxonomyId(taxonomy.getTermTaxonomyId());
		} else {
			ret.setTermTaxonomyId(taxonomies.get(0).getTermTaxonomyId());
		}
		return ret;
	}

	private List<WpTermTaxonomy> selectByName(String name, String tax) {
		log.debug(name);
		WpTermsCriteria termsCriteria = new WpTermsCriteria();
		termsCriteria.createCriteria().andNameEqualTo(name);
		List<Long> termIds = termsMapper.selectByExample(termsCriteria).stream().map(s -> s.getTermId()).collect(Collectors.toList());
		WpTermTaxonomyCriteria taxonomyCriteria = new WpTermTaxonomyCriteria();
		taxonomyCriteria.createCriteria().andTaxonomyEqualTo(tax).andTermIdIn(termIds);
		return termIds.isEmpty() ? new ArrayList<WpTermTaxonomy>() : taxonomyMapper.selectByExample(taxonomyCriteria);
	}

	private WpTermTaxonomy createNewTerm(String name, String tax) {
		// create new tag
		WpTerms term = new WpTerms();
		term.setName(name);
		term.setSlug(WpUpdaterUtils.urlEncode(name));
		termsMapper.insert(term);
		log.debug("term=%s", term);

		WpTermTaxonomy taxonomy = new WpTermTaxonomy();
		taxonomy.setTermId(term.getTermId());
		taxonomy.setTaxonomy(tax);
		taxonomyMapper.insert(taxonomy);
		log.debug("taxonomy=%s", taxonomy);
		return taxonomy;
	}
}
