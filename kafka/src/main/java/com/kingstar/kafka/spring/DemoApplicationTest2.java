package com.kingstar.kafka.spring;

import com.kingstar.kafka.mapper.MyTestMapper;
import com.kingstar.kafka.spring.configuration.HelloConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author xiayc
 * @date 2020/9/8 9:45
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {HelloConfiguration.class})
public class DemoApplicationTest2 {

    @Resource
    HelloService helloService;

    @Resource
    MyTestMapper countryMapper;

    /**
     * @Author haien
     * @Description 扫描到helloService
     * @Date 2019/6/11
     * @Param []
     * @return void
     **/
    @Test
    public void contextLoads(){
        //System.out.println(helloService.getClass());
        System.out.println(countryMapper.getClass());
    }
}
