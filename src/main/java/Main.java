import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.cfg.Configuration;
import org.hibernate.metadata.ClassMetadata;

import java.util.Map;

public class Main {
    private static final SessionFactory ourSessionFactory;

    static {
        try {
           /* Configuration configuration = new Configuration();
            configuration.configure();*/
            ourSessionFactory = new Configuration().
                    configure("hibernate.cfg.xml").
                    buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public static void main(final String[] args) throws Exception {
        final Session session = getSession();
        try {
            System.out.println("querying all the managed entities...");
            //获取元数据
            final Map metadataMap = session.getSessionFactory().getAllClassMetadata();
            for (Object key : metadataMap.keySet()) {
                //获取所有实体类
                final ClassMetadata classMetadata = (ClassMetadata) metadataMap.get(key);
                //获取实体类名字
                final String entityName = classMetadata.getEntityName();
                //执行查询
                final Query query = session.createQuery("from " + entityName);
                //获取实体对应表的所有的数据
                System.out.println("executing: " + query.getQueryString());
                for (Object o : query.list()) {
                    System.out.println("  " + o);
                }
            }
        } finally {
            session.close();
        }
    }
}
