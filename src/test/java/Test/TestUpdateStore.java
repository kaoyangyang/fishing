package Test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.sdtz.adlet.dao.StoreDao;

public class TestUpdateStore {
    String[] conf=new String[]{"classpath:conf/spring-mvc.xml","classpath:conf/spring-mybatis.xml"};
	@Test
    public void test(){
		ApplicationContext ctx = new FileSystemXmlApplicationContext(conf);  
		StoreDao storedao=  (StoreDao) ctx.getBean("storeDao");  
	    System.out.println(storedao.getStore(14).getPhotos());
	}
}
