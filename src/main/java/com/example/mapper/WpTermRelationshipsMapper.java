package com.example.mapper;

import com.example.model.wp.WpTermRelationships;
import com.example.model.wp.WpTermRelationshipsCriteria;
import com.example.model.wp.WpTermRelationshipsKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface WpTermRelationshipsMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wp_term_relationships
	 * @mbggenerated  Sun Nov 01 12:34:26 PST 2015
	 */
	int countByExample(WpTermRelationshipsCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wp_term_relationships
	 * @mbggenerated  Sun Nov 01 12:34:26 PST 2015
	 */
	int deleteByExample(WpTermRelationshipsCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wp_term_relationships
	 * @mbggenerated  Sun Nov 01 12:34:26 PST 2015
	 */
	int deleteByPrimaryKey(WpTermRelationshipsKey key);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wp_term_relationships
	 * @mbggenerated  Sun Nov 01 12:34:26 PST 2015
	 */
	int insert(WpTermRelationships record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wp_term_relationships
	 * @mbggenerated  Sun Nov 01 12:34:26 PST 2015
	 */
	int insertSelective(WpTermRelationships record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wp_term_relationships
	 * @mbggenerated  Sun Nov 01 12:34:26 PST 2015
	 */
	List<WpTermRelationships> selectByExampleWithRowbounds(WpTermRelationshipsCriteria example, RowBounds rowBounds);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wp_term_relationships
	 * @mbggenerated  Sun Nov 01 12:34:26 PST 2015
	 */
	List<WpTermRelationships> selectByExample(WpTermRelationshipsCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wp_term_relationships
	 * @mbggenerated  Sun Nov 01 12:34:26 PST 2015
	 */
	WpTermRelationships selectByPrimaryKey(WpTermRelationshipsKey key);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wp_term_relationships
	 * @mbggenerated  Sun Nov 01 12:34:26 PST 2015
	 */
	int updateByExampleSelective(@Param("record") WpTermRelationships record, @Param("example") WpTermRelationshipsCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wp_term_relationships
	 * @mbggenerated  Sun Nov 01 12:34:26 PST 2015
	 */
	int updateByExample(@Param("record") WpTermRelationships record, @Param("example") WpTermRelationshipsCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wp_term_relationships
	 * @mbggenerated  Sun Nov 01 12:34:26 PST 2015
	 */
	int updateByPrimaryKeySelective(WpTermRelationships record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wp_term_relationships
	 * @mbggenerated  Sun Nov 01 12:34:26 PST 2015
	 */
	int updateByPrimaryKey(WpTermRelationships record);
}