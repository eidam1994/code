package com.framework;

import com.framework.service.movie.IMovieService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Zhang Xingyu
 * @date 2018/10/8
 * @description 项目启动时调用接口方法
 */
@Component
public class Runner implements CommandLineRunner {

    @Resource
    private IMovieService movieService;

    /**
     * 生成单据代码
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        movieService.getMovieData();
    }
}
