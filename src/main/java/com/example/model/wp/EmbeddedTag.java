package com.example.model.wp;

public class EmbeddedTag {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column embedded_tag.keyword
     *
     * @mbggenerated Thu Oct 29 22:05:09 PDT 2015
     */
    private String keyword;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column embedded_tag.tag
     *
     * @mbggenerated Thu Oct 29 22:05:09 PDT 2015
     */
    private String tag;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column embedded_tag.keyword
     *
     * @return the value of embedded_tag.keyword
     *
     * @mbggenerated Thu Oct 29 22:05:09 PDT 2015
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column embedded_tag.keyword
     *
     * @param keyword the value for embedded_tag.keyword
     *
     * @mbggenerated Thu Oct 29 22:05:09 PDT 2015
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword == null ? null : keyword.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column embedded_tag.tag
     *
     * @return the value of embedded_tag.tag
     *
     * @mbggenerated Thu Oct 29 22:05:09 PDT 2015
     */
    public String getTag() {
        return tag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column embedded_tag.tag
     *
     * @param tag the value for embedded_tag.tag
     *
     * @mbggenerated Thu Oct 29 22:05:09 PDT 2015
     */
    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table embedded_tag
     *
     * @mbggenerated Thu Oct 29 22:05:09 PDT 2015
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", keyword=").append(keyword);
        sb.append(", tag=").append(tag);
        sb.append("]");
        return sb.toString();
    }
}