package com.example.mapper;

import com.example.model.wp.EmbeddedCategory;
import com.example.model.wp.EmbeddedCategoryCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface EmbeddedCategoryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table embedded_category
     *
     * @mbggenerated Sun Nov 01 12:34:26 PST 2015
     */
    int countByExample(EmbeddedCategoryCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table embedded_category
     *
     * @mbggenerated Sun Nov 01 12:34:26 PST 2015
     */
    int deleteByExample(EmbeddedCategoryCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table embedded_category
     *
     * @mbggenerated Sun Nov 01 12:34:26 PST 2015
     */
    int deleteByPrimaryKey(String keyword);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table embedded_category
     *
     * @mbggenerated Sun Nov 01 12:34:26 PST 2015
     */
    int insert(EmbeddedCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table embedded_category
     *
     * @mbggenerated Sun Nov 01 12:34:26 PST 2015
     */
    int insertSelective(EmbeddedCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table embedded_category
     *
     * @mbggenerated Sun Nov 01 12:34:26 PST 2015
     */
    List<EmbeddedCategory> selectByExampleWithRowbounds(EmbeddedCategoryCriteria example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table embedded_category
     *
     * @mbggenerated Sun Nov 01 12:34:26 PST 2015
     */
    List<EmbeddedCategory> selectByExample(EmbeddedCategoryCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table embedded_category
     *
     * @mbggenerated Sun Nov 01 12:34:26 PST 2015
     */
    EmbeddedCategory selectByPrimaryKey(String keyword);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table embedded_category
     *
     * @mbggenerated Sun Nov 01 12:34:26 PST 2015
     */
    int updateByExampleSelective(@Param("record") EmbeddedCategory record, @Param("example") EmbeddedCategoryCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table embedded_category
     *
     * @mbggenerated Sun Nov 01 12:34:26 PST 2015
     */
    int updateByExample(@Param("record") EmbeddedCategory record, @Param("example") EmbeddedCategoryCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table embedded_category
     *
     * @mbggenerated Sun Nov 01 12:34:26 PST 2015
     */
    int updateByPrimaryKeySelective(EmbeddedCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table embedded_category
     *
     * @mbggenerated Sun Nov 01 12:34:26 PST 2015
     */
    int updateByPrimaryKey(EmbeddedCategory record);
}