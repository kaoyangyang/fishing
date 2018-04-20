package Test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.sdtz.adlet.dao.DailRecordDao;

public class TestDailRecord {

	@org.junit.Test
	public void Test(){
	      ApplicationContext ctx = new FileSystemXmlApplicationContext( "classpath:conf/spring-mybatis.xml");  
	      DailRecordDao dailRecordDao=  (DailRecordDao) ctx.getBean("dailRecordDao");  
	      System.out.println(dailRecordDao); 
	}
}
