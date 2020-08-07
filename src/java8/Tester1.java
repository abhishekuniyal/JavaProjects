package java8;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Tester1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> listInt = Arrays.asList(1, 2, 3, 4, 5);
		
		//collectorsMethod();

		sortOnValue();
		//could use LongStream
		OptionalInt optionalInt  = IntStream.range(1, 50).min();
		System.out.println(optionalInt.isPresent() ? optionalInt.getAsInt() : 0);
		
		OptionalInt optionalIntMax = IntStream.range(1, 50).max();
		System.out.println(optionalIntMax.isPresent()? optionalIntMax.getAsInt() : 0);
		
		IntStream.range(1, 50).count();
		
		/** Map to Int******/
		listInt.stream().mapToInt(i -> i).sum();
		listInt.stream().mapToInt(Integer::intValue).sum();
		listInt.stream().mapToInt(i -> i).average();
		
		/** Map to Object******/
		IntStream.range(1, 50).mapToObj(i -> {
			return new Integer(i);
		}).collect(Collectors.toList());

		int[] arrInt = { 1, 2, 3, 4, 5 };
		List<Integer> sum = IntStream.of(arrInt).boxed().collect(Collectors.toList());
		List<Integer> sum21 = IntStream.of(arrInt).mapToObj(i -> {
			return new Integer(i);
		}).collect(Collectors.toList());
		// .sum();
		System.out.println("::::" + sum);
		System.out.println("::::11111111" + sum21);

		int sum1 = listInt.stream().mapToInt(x -> x).sum();
		System.out.println("::::" + sum1);

		List<String> indiaPlaces = Arrays.asList("Delhi", "Noida", "Pune");
		List<String> usPlaces = Arrays.asList("NY", "Nj", "WA", "Noida");

		List<List<String>> cities = Arrays.asList(indiaPlaces, usPlaces);

		indiaPlaces.stream().map(String::toUpperCase).forEach(System.out::println);
		System.out.println("*****************************8");
		cities.stream().flatMap(List::stream).map(String::toUpperCase).collect(Collectors.toSet())
				.forEach(System.out::println);
		
		collectorsMethod();

	}

	private static void sortOnValue() {
		System.out.println("********Sort on values ***********");
		Map<String, String> map33 = new HashMap<>();
		map33.put("B", "B");
		map33.put("A", "A");
		
		map33.entrySet()
			.stream()
			.sorted(Map.Entry.comparingByValue())
			.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
		
		map33.entrySet()
			.stream()
			.forEach(System.out::println);
		
		
		System.out.println("********Sort on values ***********");
	}

	private static void collectorsMethod() {
		List<Integer> listInt = Arrays.asList(1, 2, 3, 4, 5);
		List<Employee> empList = Arrays.asList(new Employee("Emp1", 30, "male"), new Employee("Emp2", 20, "female"), new Employee("Emp3", 20, "female"));
		/**************Joining****************/	
		System.out.println("Joining******************");
		String coll1 = empList.stream()
				.map(Employee::getName)
				.collect(Collectors.joining("-", "(", ")"));
		System.out.println(coll1); //(Emp1-Emp2-Emp3)
	
		/**************Counting****************/	
		long coll2 = empList.stream()
					.filter(emp -> emp.getAge()>20)
					.collect(Collectors.counting());
		System.out.println(coll2); // 1
		
		/**************Mapping****************/	
		List<String> coll3 = empList.stream()
					//reduced .map(Employee:getName
					.collect(Collectors.mapping(Employee::getName, Collectors.toList()));
		Set<String> coll4 = empList.stream()
				//reduced .map(Employee:getName
				.collect(Collectors.mapping(Employee::getName, Collectors.toSet()));
		System.out.println("mapping:::"+coll3); // [Emp1, Emp2, Emp3]
		
		/**************MinBy & MaxBy****************/
		Optional<Employee> coll5 = empList.stream()
						.collect(Collectors.minBy(Comparator.comparing(Employee::getAge)));
		System.out.println(coll5.isPresent() ? coll5.get(): null);
		Optional<Employee> coll6 = empList.stream()
				.collect(Collectors.maxBy(Comparator.comparing(Employee::getAge)));
		System.out.println(coll6.isPresent() ? coll6.get(): null);
		
		/**************Sum & Avg*******************/
		int coll7 = empList.stream()
						.collect(Collectors.summingInt(Employee::getAge));
		double avg = empList.stream()
						.collect(Collectors.averagingDouble(Employee::getAge));
		
		/**************GroupingBy Type1 Group always reurn Map<>*******************/
		Map<String, List<Employee>> coll8 = empList.stream()
					.collect(Collectors.groupingBy(Employee::getGender));
		System.out.println("Grouping by::"+coll8);
//Grouping by::{female=[Employee [name=Emp2, age=20, gender=female], Employee [name=Emp3, age=20, gender=female]], male=[Employee [name=Emp1, age=30, gender=male]]}
		
		Map<String, List<Employee>> coll9 = empList.stream()
				.collect(Collectors.groupingBy(emp -> emp.getAge()==20 ? "Fresher" : "Exp"));
		System.out.println(coll9);
//{Fresher=[Employee [name=Emp2, age=20, gender=female], Employee [name=Emp3, age=20, gender=female]], Exp=[Employee [name=Emp1, age=30, gender=male]]}		
	
		/**************GroupingBy Type2*******************/
		Map<String, Map<String,List<Employee>>> coll10 = empList.stream()
					.collect(Collectors.groupingBy(Employee::getGender,
							 Collectors.groupingBy(emp -> emp.getAge()==20 ? "Fresher" : "Exp")));
		System.out.println("type2::"+coll10);
//type2::{female={Fresher=[Employee [name=Emp2, age=20, gender=female], Employee [name=Emp3, age=20, gender=female]]}, male={Exp=[Employee [name=Emp1, age=30, gender=male]]}}		
		Map<String, Integer> coll11 = empList.stream()
				.collect(Collectors.groupingBy(Employee::getGender,
						 Collectors.summingInt(Employee::getAge)));
		System.out.println(coll11);
//{female=40, male=30}
		
		/**************GroupingBy Type3*******************/
		Map<String, Set<Employee>> coll12 = empList.stream()
				.collect(Collectors.groupingBy(Employee::getGender,LinkedHashMap::new,Collectors.toSet()));
		System.out.println(coll12);
//{male=[Employee [name=Emp1, age=30, gender=male]], female=[Employee [name=Emp2, age=20, gender=female], Employee [name=Emp3, age=20, gender=female]]}						
		
		/*************Grouping MaxBy*********************/
		Map<String,Optional<Employee>> coll13 =empList.stream()
							.collect(Collectors.groupingBy(Employee::getGender,
							 Collectors.maxBy(Comparator.comparing(Employee::getAge))));
		System.out.println(coll13);
		
		//Removing ptional directly
		Map<String,Employee> coll14 =empList.stream()
				.collect(Collectors.groupingBy(Employee::getGender,
						Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparing(Employee::getAge))
													 ,Optional::get)));
		System.out.println(coll14);
		
		/*************Grouping MinBy*********************/
		Map<String,Optional<Employee>> coll15 =empList.stream()
							.collect(Collectors.groupingBy(Employee::getGender,
							 Collectors.minBy(Comparator.comparing(Employee::getAge))));
		System.out.println(coll15);
		
		//Removing Optional directly
		Map<String,Employee> coll16 =empList.stream()
				.collect(Collectors.groupingBy(Employee::getGender,
						Collectors.collectingAndThen(Collectors.minBy(Comparator.comparing(Employee::getAge))
													 ,Optional::get)));
		System.out.println(coll16);
		
		/*************Partitioning By Always return Map<boolean, Collectors>*********************/
		Predicate<Employee> predicte = (employee) -> employee.getAge()>25;
		Map<Boolean, List<Employee>> coll17 = empList.stream()
					.collect(Collectors.partitioningBy(predicte));
		System.out.println(coll17);
		
		Map<Boolean, Set<Employee>> coll18 = empList.stream()
				.collect(Collectors.partitioningBy(predicte,
												Collectors.toSet()));
		System.out.println(coll18);
		
		
		List<String> lii = Arrays.asList("B","C","A");
		lii.sort(Comparator.naturalOrder());
		System.out.println(lii);
		lii.stream()
			.sorted()
			.forEach(System.out::println);
		
		//dateTimeApis();
		
	}

	private static void dateTimeApis() {
		LocalDate localDate = LocalDate.now(); //date is Immutable
		System.out.println("localdate: "+localDate);
		localDate = localDate.of(2020, 8, 21);
		System.out.println("localdate: "+localDate);
		localDate = localDate.minus(1, ChronoUnit.YEARS);
		System.out.println("localdate: "+localDate);
		
		
		
		LocalTime localTime= LocalTime.now();
		System.out.println("localTime::"+localTime);
		localTime = localTime.minus(1,ChronoUnit.MINUTES);
		System.out.println("localTime::"+localTime);
		localTime = localTime.with(LocalTime.NOON);
		System.out.println("localTime::"+localTime);
		localTime = localTime.with(ChronoField.HOUR_OF_DAY, 22);
		System.out.println("localTime::"+localTime);
		
		
		LocalDateTime localDateTime = LocalDateTime.now();
		System.out.println("LocaldateTime::"+localDateTime);
		
		/**Converting LocalDate to LocalTime and viceversa***/
		//LocalTime newLocalTime = localDate.atTime(LocalTime.of(12, 12, 12));
		
		System.out.println(localDate.atTime(LocalTime.of(12, 12, 12)));
		System.out.println(localTime.atDate(localDate));
		System.out.println(localDateTime.toLocalDate());
		System.out.println(localDateTime.toLocalTime());
		
		/*******Period class (for dates comparission) , mainly used to calculate between two dates(compatible with LocalDate*****/
		LocalDate localDate1 = LocalDate.now().plusDays(10).plusMonths(3).plusYears(2);
		Period period = localDate.until(localDate1);
		System.out.println(period.getDays()+"::months::"+period.getMonths()+"::years::"+period.getYears());
		
		Period period1 =Period.between(localDate, localDate1);
		System.out.println(period1.getDays()+"::months::"+period1.getMonths()+"::years::"+period1.getYears());
		
		/****Duration class (for time comparision, compatible with LocalTime and LocalDateTime********/
		
		LocalTime localTime1 = LocalTime.now().plusHours(28);
		
		long diff=  localTime.until(localTime1, ChronoUnit.MINUTES);
		System.out.println(diff);
		
		Duration duration = Duration.between(localTime1, localTime);
		System.out.println(duration.toMinutes()+"::sec::"+duration.getSeconds()+"::"+duration.toDays());
		
		/*******Instant class (for machine readable time from Epoch time(Jan-1-1970)*****************************/
		
		Instant instant = Instant.now();
		System.out.println(instant);
		
		Instant instant1 = Instant.now();
		Duration du = Duration.between(instant, instant);
		System.out.println(du.getNano());
		
		/******************ZoneDateTime**************************************************/
		ZonedDateTime zonedDateTime = ZonedDateTime.now();
		System.out.println("zonedDateTime::"+zonedDateTime);
		System.out.println("zoneOffset:::"+zonedDateTime.getOffset());
		System.out.println("zoneId:::"+zonedDateTime.getZone());
		System.out.println("AvailableZones:::"+ZoneId.getAvailableZoneIds());
		
		System.out.println("America/Chicago:::"+ZonedDateTime.now(ZoneId.of("America/Chicago")));
		System.out.println("America/Chicago:::"+ZonedDateTime.now(ZoneId.of("America/Detroit")));
		
		System.out.println("America/Chicago: Using clock:::"
									+ZonedDateTime.now(Clock.system(ZoneId.of("America/Detroit"))));
		
		LocalDateTime localTime2 =  LocalDateTime.now(ZoneId.of("America/Detroit"));
		System.out.println("localTime2::"+localTime2);
		LocalDateTime localTime3 =  LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
		System.out.println("localTime3::"+localTime3);
		
		/***************Convert LocalDateTime to ZOneDateTime***********************/
		ZonedDateTime zonedDateTime2 = localDateTime.atZone(ZoneId.of("America/Detroit"));
		ZonedDateTime zonedDateTime3 = Instant.now().atZone(ZoneId.of("America/Detroit"));
		
		/***************Convert java.util.date to LocalDate and ViceVersa***********************/
		Date date = new Date();
		System.out.println("date::"+date);
		LocalDate localDate2 = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
		date = new Date().from(localDate2.atTime(LocalTime.now()).atZone(ZoneId.systemDefault()).toInstant());
		System.out.println("date::"+date);
		
		/***************Convert java.sql.date to LocalDate and ViceVersa***********************/
		java.sql.Date sqlDate = java.sql.Date.valueOf(localDate2);
		System.out.println("sqlDate::"+sqlDate);
		
		LocalDate localDate4 = sqlDate.toLocalDate();
		
		
		/**********************************DateTimeFormatter Date**************************************/
		
		String strDate = "2020-07-27";
		LocalDate localDate3 = LocalDate.parse(strDate, DateTimeFormatter.ISO_LOCAL_DATE);//default is also ISO_LOCAL_DATE
		System.out.println("localDate3:::"+localDate3);
		
		String strDate1 = "20200727";
		LocalDate localDate5 = LocalDate.parse(strDate1, DateTimeFormatter.BASIC_ISO_DATE);//default is also ISO_LOCAL_DATE
		System.out.println("localDate3:::"+localDate5);
		
		String strDate2 = "2020|07|27";
		DateTimeFormatter dateTimeFormatter =  DateTimeFormatter.ofPattern("yyyy|MM|dd");
		LocalDate localDate6 = LocalDate.parse(strDate2, dateTimeFormatter);//default is also ISO_LOCAL_DATE
		System.out.println("localDate3:::"+localDate6);
		System.out.println("localDate3:::"+localDate6);
		
		
		System.out.println(localDate5.format(DateTimeFormatter.ISO_LOCAL_DATE));
		System.out.println(localDate5.format(dateTimeFormatter));
		
		/**********************************DateTimeFormatter Time**************************************/
		String time = "13:00";
		LocalTime localTime4 = LocalTime.parse(time, DateTimeFormatter.ISO_LOCAL_TIME);
		System.out.println("localTime4::"+localTime4);
		
		String time1 = "13|00";
		DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern("HH|mm");
		LocalTime localTime5 = LocalTime.parse(time1, dateTimeFormatter2);
		System.out.println("localTime4::"+localTime5);
		
		DateTimeFormatter dateTimeFormatter3 = DateTimeFormatter.ofPattern("HH**mm");
		System.out.println(localTime5.format(dateTimeFormatter3));
		
		/**********************************DateTimeFormatter DateTime**************************************/
		
		String dateTime = "2020-07-21T13|00|00";
		DateTimeFormatter dateTimeFormatter4 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH|mm|ss");
		LocalDateTime localDateTime2 = LocalDateTime.parse(dateTime, dateTimeFormatter4);
		System.out.println("localDateTime2::"+localDateTime2);
		DateTimeFormatter dateTimeFormatter5 = DateTimeFormatter.ofPattern("yyyy-MM-dd'abc'HH*mm*ss");
		System.out.println(localDateTime2.format(dateTimeFormatter5));
		
		
	}
}
