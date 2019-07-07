package cn.ljtnono.root;

import cn.ljtnono.root.bean.Person;
import cn.ljtnono.root.mapper.ReBlogDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 可以自动注入了
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class DemoControllerTest {

    @Autowired
    private ReBlogDao reBlogDao;

    @Autowired
    private Person person;

    @Test
    public void test(){
        System.out.println(reBlogDao);
        System.out.println(person);
    }

}
