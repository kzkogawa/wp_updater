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

	/**
	 * Obtain List<WpTermTaxonomy> by tagName.
	 * <p>
	 * When no WpTermTaxonomy found, this returns empty List
	 * </p>
	 * 
	 * @param name
	 *            of tag
	 * @return
	 */
	public List<WpTermTaxonomy> selectByTagName(String name) {
		log.debug(name);
		WpTermsCriteria termsCriteria = new WpTermsCriteria();
		termsCriteria.createCriteria().andNameEqualTo(name);
		List<Long> termIds = termsMapper.selectByExample(termsCriteria).stream().map(s -> s.getTermId()).collect(Collectors.toList());

		WpTermTaxonomyCriteria taxonomyCriteria = new WpTermTaxonomyCriteria();
		taxonomyCriteria.createCriteria().andTaxonomyEqualTo(WpUpdaterUtils.CONST_TAXONOMY_POST_TAG).andTermIdIn(termIds);
		return termIds.isEmpty() ? new ArrayList<WpTermTaxonomy>() : taxonomyMapper.selectByExample(taxonomyCriteria);
	}

	/**
	 * 
	 * @param name
	 *            of tag
	 * @return
	 */
	public WpTermRelationships getWpTermRelationships(String name) {
		WpTermRelationships ret = new WpTermRelationships();
		ret.setTermOrder(0);

		List<WpTermTaxonomy> taxonomies = selectByTagName(name);
		if (taxonomies.isEmpty()) {
			// create new tag
			WpTerms newTerm = new WpTerms();
			newTerm.setName(name);
			newTerm.setSlug(WpUpdaterUtils.urlEncode(name));
			termsMapper.insert(newTerm);
			log.debug(String.format("newTerm=%s", newTerm));

			WpTermTaxonomy taxonomy = new WpTermTaxonomy();
			taxonomy.setTermId(newTerm.getTermId());
			taxonomy.setTaxonomy(WpUpdaterUtils.CONST_TAXONOMY_POST_TAG);
			taxonomyMapper.insert(taxonomy);
			log.debug(String.format("taxonomy=%s", taxonomy));
			ret.setTermTaxonomyId(taxonomy.getTermTaxonomyId());
		} else {
			// taxonomies.size() should be 1
			ret.setTermTaxonomyId(taxonomies.get(0).getTermTaxonomyId());
		}
		return ret;
	}
}
