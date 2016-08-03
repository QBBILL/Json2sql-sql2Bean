package cn.qbbill;

import cn.qbbill.dao.CommonDao;
import cn.qbbill.util.SpringContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class MainApplication implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(MainApplication.class);


    @Override
    public void run(String... strings) throws Exception {
        CommonDao bean = SpringContextUtil.getBean(CommonDao.class);
        System.out.println("容器启动!");
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(MainApplication.class, args);
    }



}
