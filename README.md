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

### 2025-08-16

비즈니스 컴포넌트 실습 2 (실습1 복습)  

bean 직접등록 (어노테이션X) 및 setter DI로 실습  
setter 주입은 불변성을 보장할 수 없다는 단점이 있음 (그럴일은 잘 없겠지만 어디선가 set해서 주입하면 의존 객체가 바뀔 수 있음)

---

### 2025-08-17

#### 스프링 AOP

IoC는 결합도와 관련된 기능이라면, AOP는 응집도와 관련된 기능이라 할 수 있다

AOP는 관점 지향 프로그래밍으로 공통으로 등장하는 로깅, 예외처리, 트랜잭션 등의 부가적인 로직을 따로 분리하여 개발자가 비즈니스(서비스) 로직에만 집중할 수 있도록 돕는다

AOP 용어 정리

 - 횡단 관심  
   로깅, 예외처리, 트랜잭션 등의 부가적인 로직을 "횡단관심"이라고 함


 - 핵심 관심  
   비즈니스(서비스) 로직을 "핵심관심"이라고 한다


 - 조인포인트 (Joinpoint)  
   클라이언트가 호출하는 모든 비즈니스 메소드 (예제에서의 BoardServiceImpl, UserServiceImpl 클래스의 모든 메소드를 조인포인트라고 생각하면 된다)
   

 - 포인트컷 (Pointcut)  
   필터링된 조인포인트. 예를 들어 트랜잭션을 처리하는 공통(횡단관심) 기능을 만들 때 등록, 수정, 삭제 기능의 비즈니스 메소드에 대해서 동작하고, 조회 기능의 메소드에 대해선 동작할 필요가 없을 때  
   즉, 수많은 비즈니스 메소드 중에서 원하는 특정 메소드에서만 공통 기능을 수행시키기 위해서 포인트컷이 필요함 


 - 위빙 (Weaving)  
   포인트컷으로 지정한 핵심 관심 메소드가 호출될 때 횡단 관심 메소드가 삽입되는 과정 


  - 어드바이스 (Advice)  
    부가 로직(횡단 관심)의 실제 코드 (부가 로직)  
    예를 들어 비즈니스 로직(핵심 관심)이 "커피 만들기"라면  
    Advice: "커피 만들기 전 손 씻기" / "완성 후 컵 닦기"  
    Aspect: "손 씻기 + 컵 닦기 규칙을 바리스타 업무에 적용한다"라는 전체 묶음

    

--- 

### 2025-08-18

AOP Advice 동작 시점

before, afterReturning, afterThrowing, after, around 등의 Advice 동작 시점 실습  
around에서 위 모든 동작 시점을 핸들링 할 수 있기 때문에 around를 자주 쓰게 될 것 같다

---

### 2025-08-19

JoinPoint 파라미터 실습

AOP 횡단 관심 Advice 메소드를 선언할 때 JoinPoint 파라미터를 명시하면 스프링 컨테이너가 Advice 메소드를 호출 할 때 JoinPoint 객체를 생성해서 넘겨준다

ProceedingJoinPoint는 JoinPoint를 상속한 객체고, JoinPoint가 가진 메소드를 지원하며 proceeding() 이 추가된다  
Around Advice만 ProceedingJoinPoint를 매개변수로 사용한다 (Around만 proceed() 메소드가 필요하기 때문)  
나머지(before, afterReturning ...)는 모두 JoinPoint 사용

ProceedingJoinPoint.proceed() 호출부 앞 뒤로 before, after 즉 횡단 관심 소스를 작성하면 대상 핵심 관심 전, 후로 횡단 관심 로직이 실행된다 

---

### 2025-08-20

어노테이션 기반 AOP

AOP (Advice)를 어노테이션으로 선언하려면 \<aop:aspectj-autoproxy> 를 config xml에 선언해야한다 (해당 엘리먼트를 선언하면 스프링 컨테이너는 AOP 관련 어노테이션들을 적절하게 처리해준다)  
@Component 어노테이션 쓸 때 \<component-scan> 선언했던 것 처럼!

공용 Pointcut 등을 선언할 AOP용 config 클래스 하나 만들어서 관리하면 좋을 것 같다 (오늘 실습의 PointcutCommon 같은 클래스)


---

### 2025-08-21

스프링 JDBC

commons.dbcp (DataSource), jdbcTemplate을 bean 등록하여 jdbc 연결 실습 














