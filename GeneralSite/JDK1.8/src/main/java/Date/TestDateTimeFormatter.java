package Date;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

public class TestDateTimeFormatter {

	
	//DateTimeFormatter 格式化时间/日期
	@Test
	public void test1(){
		DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE;
		LocalDateTime ldt = LocalDateTime.now();
		String strDate = ldt.format(dtf);
		System.out.println(strDate);
		System.out.println("---------------------------");
		DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
		String strDate2 = dtf2.format(ldt);
		System.out.println(strDate2);
		
		LocalDateTime newDate = ldt.parse(strDate2,dtf2);
		System.out.println(newDate);
		
	}
	
	
	
	
	
	
}
