
import java.util.Date;import com.nikati.manage.WebstackGunsApplication;
import com.nikati.manage.modular.system.dao.VisitorMapper;
import com.nikati.manage.modular.system.model.Visitor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WebstackGunsApplication.class)
public class SpringbootApplicatyionTest {
    @Autowired
    private VisitorMapper visitorMapper;

    @Test
    public void getOne(){
        System.out.println(visitorMapper.selectByPrimaryKey(1));
    }

    @Test
    public void list(){
        System.out.println(visitorMapper.getList(null));
    }

    @Test
    @Transactional
    public void delete(){
        System.out.println(visitorMapper.deleteByPrimaryKey(1));
    }

    @Test
    //@Transactional
    public void update(){
        Visitor visitor = visitorMapper.selectByPrimaryKey(1);
        visitor.setOs("windows");
        int i = visitorMapper.updateByPrimaryKeySelective(visitor);
    }
    @Test
    public void insert(){
        Visitor visitor = new Visitor();
        visitor.setId(0);
        visitor.setIp("47.94.153.206");
        visitor.setOs("linux");
        visitor.setBrowser("QQ浏览器");
        visitor.setAddress("广州");
        visitor.setCreate_time(new Date());
        int i = visitorMapper.insertSelective(visitor);


    }


}
