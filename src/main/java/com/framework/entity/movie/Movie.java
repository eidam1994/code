package com.framework.entity.movie;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * movie
 * @author 
 */
@Data
public class Movie implements Serializable {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 评分
     */
    private String rate;

    /**
     * 电影名称
     */
    private String title;

    /**
     * 豆瓣详情页链接
     */
    private String url;

    /**
     * 豆瓣电影id
     */
    private String movieId;

    /**
     * 电影封面
     */
    private String cover;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}