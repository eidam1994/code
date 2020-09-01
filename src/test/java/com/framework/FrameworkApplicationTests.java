package com.framework;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.framework.entity.movie.Movie;
import com.framework.mapper.movie.MovieMapper;
import com.framework.util.DataUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.sql.SQLException;

@SpringBootTest
class FrameworkApplicationTests {

    @Resource
    private MovieMapper movieMapper;
    
    @Test
    void contextLoads() {
        String address = "https://movie.douban.com/j/new_search_subjects?sort=R&range=1,10&tags=2020,%E7%94%B5%E5%BD%B1&start=5000";
        JSONObject movieJson = DataUtils.getMovieJson(address);
//        System.out.println(movieJson.toJSONString());
        JSONArray data = (JSONArray)movieJson.get("data");
        if (data.size() == 0) {
            System.out.println("没有数据");
            return;
        }
        for (int i = 0; i < data.size(); i++) {
            JSONObject movieInfo = data.getJSONObject(i);
            Movie movie = new Movie();
            movie.setRate(movieInfo.getString("rate"));
            movie.setTitle(movieInfo.getString("title"));
            movie.setUrl(movieInfo.getString("url"));
            movie.setCover(movieInfo.getString("cover"));
            movie.setMovieId(movieInfo.getString("id"));
            movieMapper.insertSelective(movie);
        }
//        for (int i = 0; i < 3; i++) {
//            try {
//                movieJson.get("data")
//                Movie movie = new Movie();
//                movie.setMovieId(String.valueOf(i));
//                movieMapper.insertSelective(movie);
//            } catch (Exception e) {
//                System.out.println("唯一键重复");
//            }
//        }
    }

}
