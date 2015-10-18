package com.example.mapper;

import com.example.model.WpTermTaxonomy;
import com.example.model.WpTermTaxonomyCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface WpTermTaxonomyMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wp_term_taxonomy
     *
     * @mbggenerated Sat Oct 17 20:06:25 PDT 2015
     */
    int countByExample(WpTermTaxonomyCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wp_term_taxonomy
     *
     * @mbggenerated Sat Oct 17 20:06:25 PDT 2015
     */
    int deleteByExample(WpTermTaxonomyCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wp_term_taxonomy
     *
     * @mbggenerated Sat Oct 17 20:06:25 PDT 2015
     */
    int deleteByPrimaryKey(Long termTaxonomyId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wp_term_taxonomy
     *
     * @mbggenerated Sat Oct 17 20:06:25 PDT 2015
     */
    int insert(WpTermTaxonomy record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wp_term_taxonomy
     *
     * @mbggenerated Sat Oct 17 20:06:25 PDT 2015
     */
    int insertSelective(WpTermTaxonomy record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wp_term_taxonomy
     *
     * @mbggenerated Sat Oct 17 20:06:25 PDT 2015
     */
    List<WpTermTaxonomy> selectByExampleWithBLOBsWithRowbounds(WpTermTaxonomyCriteria example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wp_term_taxonomy
     *
     * @mbggenerated Sat Oct 17 20:06:25 PDT 2015
     */
    List<WpTermTaxonomy> selectByExampleWithBLOBs(WpTermTaxonomyCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wp_term_taxonomy
     *
     * @mbggenerated Sat Oct 17 20:06:25 PDT 2015
     */
    List<WpTermTaxonomy> selectByExampleWithRowbounds(WpTermTaxonomyCriteria example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wp_term_taxonomy
     *
     * @mbggenerated Sat Oct 17 20:06:25 PDT 2015
     */
    List<WpTermTaxonomy> selectByExample(WpTermTaxonomyCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wp_term_taxonomy
     *
     * @mbggenerated Sat Oct 17 20:06:25 PDT 2015
     */
    WpTermTaxonomy selectByPrimaryKey(Long termTaxonomyId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wp_term_taxonomy
     *
     * @mbggenerated Sat Oct 17 20:06:25 PDT 2015
     */
    int updateByExampleSelective(@Param("record") WpTermTaxonomy record, @Param("example") WpTermTaxonomyCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wp_term_taxonomy
     *
     * @mbggenerated Sat Oct 17 20:06:25 PDT 2015
     */
    int updateByExampleWithBLOBs(@Param("record") WpTermTaxonomy record, @Param("example") WpTermTaxonomyCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wp_term_taxonomy
     *
     * @mbggenerated Sat Oct 17 20:06:25 PDT 2015
     */
    int updateByExample(@Param("record") WpTermTaxonomy record, @Param("example") WpTermTaxonomyCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wp_term_taxonomy
     *
     * @mbggenerated Sat Oct 17 20:06:25 PDT 2015
     */
    int updateByPrimaryKeySelective(WpTermTaxonomy record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wp_term_taxonomy
     *
     * @mbggenerated Sat Oct 17 20:06:25 PDT 2015
     */
    int updateByPrimaryKeyWithBLOBs(WpTermTaxonomy record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wp_term_taxonomy
     *
     * @mbggenerated Sat Oct 17 20:06:25 PDT 2015
     */
    int updateByPrimaryKey(WpTermTaxonomy record);
}