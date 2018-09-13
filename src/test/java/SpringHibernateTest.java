import com.entity.StudentEntity;
import dao.StudentDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml"})
public class SpringHibernateTest {
  @Autowired
    HibernateTemplate hibernateTemplate;

 @Autowired
 StudentDao studentDao;


            @Test
    public void testGet(){
                StudentEntity studentEntity = hibernateTemplate.get(StudentEntity.class,1);
                System.out.println(studentEntity.getName());
            }

            @Test
    public void testUpdate(){
                StudentEntity studentEntity = new StudentEntity();
            studentEntity.setId(3);
            studentEntity.setAge(12);
            studentEntity.setName("wangwu");
                System.out.println("hello");
            studentDao.update(studentEntity);
            }
        /*    @Test
    public void testInsert(){
                hibernateTemplate
            }*/
}
