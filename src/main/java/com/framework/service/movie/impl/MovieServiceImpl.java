package com.framework.service.movie.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.framework.entity.movie.Movie;
import com.framework.mapper.movie.MovieMapper;
import com.framework.service.movie.IMovieService;
import com.framework.util.DataUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description: 电影类业务层实现
 * @author: xingyuzhang
 * @create: 2020-09-01 14:55
 */
@Service
public class MovieServiceImpl implements IMovieService {
    
    @Resource
    private MovieMapper movieMapper;
    
    @Override
    public void getMovieData() throws InterruptedException {
        int start;
        int total = 0;
        int end = 5000;
        for (start  = 0; start <= end; start += 20)  {
            try {

                String address = "https://movie.douban.com/j/new_search_subjects?sort=R&range=1,10&tags=2020,%E7%94%B5%E5%BD%B1&start=" + start;
                JSONObject movieJson = DataUtils.getMovieJson(address);
                System.out.println(movieJson.toJSONString());
                System.out.println("start:" + start);
                JSONArray data = (JSONArray)movieJson.get("data");
                if (data.size() == 0) {
                    System.out.println("--------已获取全部数据--------");
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
                    total++;
                }
                System.out.println("正在爬取中---共抓取:" + total + "条数据");
            } catch (Exception e) {
                e.printStackTrace();
            }
            Thread.sleep(20000);
        }
    }
}
