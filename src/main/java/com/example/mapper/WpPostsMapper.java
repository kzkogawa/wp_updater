package com.example.mapper;

import com.example.model.wp.WpPosts;
import com.example.model.wp.WpPostsCriteria;
import com.example.model.wp.WpPostsWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface WpPostsMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wp_posts
	 * @mbggenerated  Thu Oct 29 22:05:09 PDT 2015
	 */
	int countByExample(WpPostsCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wp_posts
	 * @mbggenerated  Thu Oct 29 22:05:09 PDT 2015
	 */
	int deleteByExample(WpPostsCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wp_posts
	 * @mbggenerated  Thu Oct 29 22:05:09 PDT 2015
	 */
	int deleteByPrimaryKey(Long id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wp_posts
	 * @mbggenerated  Thu Oct 29 22:05:09 PDT 2015
	 */
	int insert(WpPostsWithBLOBs record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wp_posts
	 * @mbggenerated  Thu Oct 29 22:05:09 PDT 2015
	 */
	int insertSelective(WpPostsWithBLOBs record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wp_posts
	 * @mbggenerated  Thu Oct 29 22:05:09 PDT 2015
	 */
	List<WpPostsWithBLOBs> selectByExampleWithBLOBsWithRowbounds(WpPostsCriteria example, RowBounds rowBounds);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wp_posts
	 * @mbggenerated  Thu Oct 29 22:05:09 PDT 2015
	 */
	List<WpPostsWithBLOBs> selectByExampleWithBLOBs(WpPostsCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wp_posts
	 * @mbggenerated  Thu Oct 29 22:05:09 PDT 2015
	 */
	List<WpPosts> selectByExampleWithRowbounds(WpPostsCriteria example, RowBounds rowBounds);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wp_posts
	 * @mbggenerated  Thu Oct 29 22:05:09 PDT 2015
	 */
	List<WpPosts> selectByExample(WpPostsCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wp_posts
	 * @mbggenerated  Thu Oct 29 22:05:09 PDT 2015
	 */
	WpPostsWithBLOBs selectByPrimaryKey(Long id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wp_posts
	 * @mbggenerated  Thu Oct 29 22:05:09 PDT 2015
	 */
	int updateByExampleSelective(@Param("record") WpPostsWithBLOBs record, @Param("example") WpPostsCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wp_posts
	 * @mbggenerated  Thu Oct 29 22:05:09 PDT 2015
	 */
	int updateByExampleWithBLOBs(@Param("record") WpPostsWithBLOBs record, @Param("example") WpPostsCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wp_posts
	 * @mbggenerated  Thu Oct 29 22:05:09 PDT 2015
	 */
	int updateByExample(@Param("record") WpPosts record, @Param("example") WpPostsCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wp_posts
	 * @mbggenerated  Thu Oct 29 22:05:09 PDT 2015
	 */
	int updateByPrimaryKeySelective(WpPostsWithBLOBs record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wp_posts
	 * @mbggenerated  Thu Oct 29 22:05:09 PDT 2015
	 */
	int updateByPrimaryKeyWithBLOBs(WpPostsWithBLOBs record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wp_posts
	 * @mbggenerated  Thu Oct 29 22:05:09 PDT 2015
	 */
	int updateByPrimaryKey(WpPosts record);
}