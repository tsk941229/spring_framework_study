# spring_framework_study

### spec

- JDK 11
- Tomcat 9
- Spring Framework 5.3.9

---

### 2025-08-10 

프로젝트 생성  

---

### 2025-08-11

스프링은 IoC, AOP를 지원하는 경량의 컨테이너 프레임워크

- IoC  
 컨테이너가 객체 생성, 생명주기를 관리함 (개발자가 하지 않는다는게 포인트)  
 Servlet Container 보면 Servlet 객체를 직접 생성해주고, (클라이언트 요청에 의해 Servlet이 있는지 없는지 보고 없으면 새로 생성, 있으면 있는거 쓰는 등..) doGet 같은 메소드도 호출하는 코드가 없는데 컨테이너가 호출해줌. 이게 IoC

  
- AOP  
 관점 지향 프로그래밍  
 로깅, 보안, 트랜잭션 등의 공통 로직을 분리하고, 코드에 명시하지 않고 선언적으로 처리 --> 응집도 up  
 공통 기능을 분리하여 관리하기 때문에 비즈니스 로직에 집중할 수 있고 유지보수 쉬워짐.
 
 ---

### 2025-08-12

#### 스프링 컨테이너  
역할 :   
 - Bean 생성, 의존성 주입, 라이프사이클 관리 등등.. 
 - 오늘 실습한 applicationContext.xml 처럼 컨테이너 설정 파일을 생성, 설정하여 사용


종류 : 

 - BeanFactory  
 스프링 설정 파일에 등록된 bean 객체를 생성, 관리하는 가장 기본적인 컨테이너 기능만 제공  
 클라이언트의 요청(Lookup)에 의해서만 \<bean> 객체 생성함 (지연로딩)  
 일반 스프링 프로젝트에서 사용할 일 거의 없다고 한다


 - ApplicationContext  (자주씀)  
 BeanFactory 상속  
 컨테이너 구동 시점에 등록된 클래스들의 객체를 생성함 (즉시로딩)


몰랐던 사실  
IoC로 스프링이 객체 생성하면 싱글톤으로 객체를 관리해주는 건 알았는데, xml에서 \<bean> 의 scope 속성으로 singleton이 default이고, scope="prototype" 하면 인스턴스 여러개를 생성 가능

---

### 2025-08-13

#### 어노테이션 기반 설정

[ 컴포넌트 스캔 (component-scan) ]  

스프링 설정파일 applicationContext.xml에 \<context:component-scan /> 태그를 정의하고, 범위(패키지)를 설정하면 (base-package="패키지명")  
\<bean> 등록 없이 설정한 패키지 하위의 @Component 가 설정된 클래스들을 스캔하여 자동으로 객체를 생성해준다 (스프링 컨테이너가 해당 클래스를 bean으로 생성, 관리함)

@Component("id") 이렇게 id를 명시해주거나, 명시하지 않으면 클래스명을 따라간다 (ex: Member -> member)


[ 의존성 주입 어노테이션 ]

@Autowired, @Resources, @Inject, @Qualifier  

생성자 또는 setter없이 DI 간편하게 해주는 어노테이션  
대상 객체도 bean 등록 되어 있어야 함

실무에서 자주 나오는데 무슨차이인지 궁금했었음 (특히 @Autowired랑 @Resources)  
==> @Autowired와 @Resources는 DI 해주는 기능은 똑같지만,  
@Autowired는 변수의 타입을 기준으로 bean 등록된 객체를 검색하는데, 같은 타입의 객체가 여러개 있다면 @Qualifier("id") 어노테이션을 같이 사용해 어떤 객체를 DI 할지 id를 명시해줘야한다  
반면 @Resources는 (name = "") 속성이 있고, 객체의 이름을 기준으로 DI 한다  
@Resources 에 name 속성을 생략하면 @Autowired와 똑같이 타입을 기준으로 찾는다 (완전 똑같음) 

---

### 2025-08-14

비즈니스 컴포넌트 실습 1
 
Service, ServiceImpl, DAO 구조  
어노테이션 기반 IoC, DI 사용하여 게시판 (Board) CRUD 실습  
Driver로 JDBC연결도 복습겸 (JNDI, DBCP는 다음 실습때 복습예정)


---






























