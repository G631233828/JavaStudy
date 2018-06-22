package Date;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.ZoneOffset;

import org.junit.Test;

public class TestLocalDateTime {
	//1.LocalDate LocalTime LocalDateTime
	@Test
	public void test1(){
		//实例化一个LocalDateTime
		LocalDateTime ldt = LocalDateTime.now();
		System.out.println(ldt);
		//自定义一个事件
		LocalDateTime ldt2 = LocalDateTime.of(2018, 10,8,10,8,8);
		System.out.println(ldt2);
		//年份加减
		LocalDateTime ldt3 = ldt.plusYears(2);
		System.out.println(ldt3);
		
		LocalDateTime ldt4 = ldt.minusMinutes(2);
		System.out.println(ldt4);
		
		System.out.println(ldt.getYear());
		System.out.println(ldt.getMonthValue());
		System.out.println(ldt.getDayOfMonth());
		System.out.println(ldt.getHour());
		System.out.println(ldt.getMinute());
		System.out.println(ldt.getSecond());
		
	}
	
	
	@Test
	public void test2(){
		Instant inst1 = Instant.now();//默认获取UTC时区
		System.out.println(inst1);
		
		OffsetDateTime odt = inst1.atOffset(ZoneOffset.ofHours(8));
		System.out.println(odt);

		System.out.println(inst1.toEpochMilli());
		
	}
	
	@Test
	public void test3() throws InterruptedException{
		Instant ins1 = Instant.now();
		
		Thread.sleep(1000);
		
		Instant ins2 = Instant.now();
		
		Duration duration  = Duration.between(ins1,ins2);
		
		System.out.println(duration.toMillis());
	}
	
	
	@Test
	public void test4() throws InterruptedException{
		LocalDate ld1 = LocalDate.of(2018, 1, 1);
		LocalDate ld2 = LocalDate.now();
		Period period = Period.between(ld1, ld2);
		System.out.println(period);
		System.out.println(period.getYears());
		System.out.println(period.getMonths());
		System.out.println(period.getDays());
		
	}
	
	
	
	
	
	
}
