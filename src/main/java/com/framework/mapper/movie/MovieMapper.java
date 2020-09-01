package com.framework.mapper.movie;

import com.framework.entity.movie.Movie;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.BaseMapper;

/**
 * MovieMapper
 * @author eidam 
 */
@Mapper
@Component(value = "movieMapper")
public interface MovieMapper extends BaseMapper<Movie> {
    
}