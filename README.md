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

#### 비즈니스 컴포넌트 실습 1
 
Service, ServiceImpl, DAO 구조  
어노테이션 기반 IoC, DI 사용하여 게시판 (Board) CRUD 실습  
Driver로 JDBC연결도 복습겸 (JNDI, DBCP는 다음 실습때 복습예정)


---

### 2025-08-16

#### 비즈니스 컴포넌트 실습 2 (실습1 복습)  

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

#### AOP Advice 동작 시점

before, afterReturning, afterThrowing, after, around 등의 Advice 동작 시점 실습  
around에서 위 모든 동작 시점을 핸들링 할 수 있기 때문에 around를 자주 쓰게 될 것 같다

---

### 2025-08-19

#### JoinPoint 파라미터 실습

AOP 횡단 관심 Advice 메소드를 선언할 때 JoinPoint 파라미터를 명시하면 스프링 컨테이너가 Advice 메소드를 호출 할 때 JoinPoint 객체를 생성해서 넘겨준다

ProceedingJoinPoint는 JoinPoint를 상속한 객체고, JoinPoint가 가진 메소드를 지원하며 proceeding() 이 추가된다  
Around Advice만 ProceedingJoinPoint를 매개변수로 사용한다 (Around만 proceed() 메소드가 필요하기 때문)  
나머지(before, afterReturning ...)는 모두 JoinPoint 사용

ProceedingJoinPoint.proceed() 호출부 앞 뒤로 before, after 즉 횡단 관심 소스를 작성하면 대상 핵심 관심 전, 후로 횡단 관심 로직이 실행된다 

---

### 2025-08-20

#### 어노테이션 기반 AOP

AOP (Advice)를 어노테이션으로 선언하려면 \<aop:aspectj-autoproxy> 를 config xml에 선언해야한다 (해당 엘리먼트를 선언하면 스프링 컨테이너는 AOP 관련 어노테이션들을 적절하게 처리해준다)  
@Component 어노테이션 쓸 때 \<component-scan> 선언했던 것 처럼!

공용 Pointcut 등을 선언할 AOP용 config 클래스 하나 만들어서 관리하면 좋을 것 같다 (오늘 실습의 PointcutCommon 같은 클래스)


---

### 2025-08-21

#### 스프링 JDBC

commons.dbcp (DataSource), jdbcTemplate을 bean 등록하여 jdbc 연결 실습 

---

### 2025-08-24

#### 트랜잭션 처리

스프링 트랜잭션은 처리를 컨테이너에게 맡기는 선언적 트랜잭션이다 (트랜잭션 처리를 컨테이너가 자동으로 처리)   

스프링 트랜잭션 설정에는 앞에서 학습한 AOP가 사용된다  

사용법  
\<tx: > 네임스페이스를 추가해야하고,  
\<bean id="txManager"> 관리자 bean 등록,  
\<tx:advice> 등록 (트랜잭션 advice로, 직접 구현 못하고 선언하여 사용한다  
[advice의 id와 method를 모르기 때문에 aop:aspect로 사용하지 못하고 aop:advisor을 사용한다])


---

### 2025-08-25

#### 게시판 실습 (Model 1 아키텍처)

jsp에서 Controller, View 역할, JavaBeans로 Model 역할을 하는 Model 1 게시판 실습 (노잼)

---

### 2025-08-26

#### 게시판 실습 (Model 2 아키텍처)

Model 1은 jsp가 Controller, View 역할을 동시에 맡는 구조라서 프로젝트 규모가 커지면 문제점이 많음  
유지보수가 어렵고, 재사용성이 부족하며 테스트도 어려움  

이를 보완한 Model 2 아키텍처는 Controller를 따로 두어 Controller와 View를 분리함  

Servlet(DispatcherServlet)을 이용한 Controller 생성 등 Model 2 실습

---

### 2025-08-27

#### MVC 프레임워크 실습

Spring MVC 실습 이전에 Model 2 방식보다 조금 더 진보된 MVC 프레임워크 (Spring MVC 전)를 실습

차이점은 DispatcherServlet에서 모든 분기처리를 수행하지 않고,  
요청별로 개별의 Controller가 각자의 역할을 수행하며,  
ViewResolver, HandlerMapping이 등장한다

 - ViewResolver  
 Controller가 리턴한 View 이름으로 실행 될 JSP 경로 (prefix, suffix로 viewName 가공해서 리턴함 ex: ./login.jsp)


 - HandlerMapping  
 클라이언트의 요청을 처리 할 Controller를 매핑

클라이언트 요청이 처리되는 과정 정리  
1. 클라이언트 요청
2. DispatcherServlet의 init() 에서 ViewResolver, HandlerMapping 객체 생성
3. request 객체에서 uri의 path 얻어서 HandlerMapping에 매핑된 Controller 찾음
4. 찾은 Controller 업무 수행 (Controller 수행 완료 후 viewName 리턴)
5. viewName ViewResolver에서 가공 (./login.jsp 이런식으로 viewName 반환)
6. response 객체의 sendRedirect(viewName) 으로 view로 감

---

### 2025-08-28

#### Spring MVC 구조

Spring MVC는 앞서 실습한 MVC 프레임워크와 크게 다르지 않다

차이점을 말하자면  
DispatcherServlet의 init()에서 더이상 객체를 new 하지 않고 ServleConfig -> context 객체로 컨테이너를 구동하고,  
HandlerMapping, ViewResolver 객체를 \<bean> 등록하여 IoC로 컨테이너가 관리하게 하고,   
Controller가 String을 반환하지 않고 ModelAndView 객체를 반환한다

실습에서 더 자세히..

---

### 2025-08-31

#### Spring MVC 실습

MVC 프레임워크 실습 하면서 직접 구현해봤던 DispatcherServlet을 springframework에서 지원하는 걸로 바꾸고,  
HandlerMapping과 ViewResolver를 bean 등록하여 실습  

ViewResolver를 활용하면 클라이언트에서 직접 jsp를 요청하지 못하게 WEB-INF안에 넣고, 정상적인 요청만으로 접근할 수 있게 가능

---

### 2025-09-01

#### Spring MVC 실습 (어노테이션)

어노테이션을 사용하면 많은게 축약된다  
어노테이션을 선언하면 Spring 컨테이너가 알아서 해주는게 많은데,
@Controller 어노테이션으로 컨트롤러를 간편하게 등록하고,  
@RequestMapping 어노테이션을 선언하면 HandlerMapping 객체를 만들지 않아도 요청 url을 매핑해준다  

#### Command 객체  

Command 객체를 사용하지 않았을 땐 servlet request 객체에서 parameter를 하나하나 가져와 vo 객체에 set하고 그 다음 작업을 했었는데,  
Command 객체를 RequestMapping이 선언된 메소드의 파라미터로 지정하면 (ex: BoardVO vo) 요청 payload에 담긴 name, value를 알아서 바인딩 해준다  
이때 Command 객체는 JavaBeans이고, 바인딩을 원하는 필드의 setter가 반드시 있어야 한다  
Spring 컨테이너가 setter로 값을 넣어주기 때문

---

### 2025-09-02

#### Spring MVC 실습 (어노테이션) 2

게시판 실습

Spring MVC 이것저것 실습  
Controller의 return 값은 ModelAndView / String 가능  
Command 객체의 필드에 없는 것은 @RequestParam 어노테이션으로 추가로 받을 수 있음  

@ModelAttribute를 Command 객체 이름 변경이 아닌 View에서 사용 할 데이터를 설정하는 용도로 사용 가능  
파라미터가 아닌 메소드 레벨로 @ModelAttribute를 선언하면 해당 메소드는 @RequestMapping 메소드보다 먼저 호출됨  
@ModelAttribute 메소드의 리턴 객체는 View 페이지에서 사용 가능  
즉, 컨트롤러에서 모든 메소드 -> view 에 필요한 전역객체 같은걸 만들 수 있음 (예를들어 카테고리 리스트라던지.. 검색 조건 리스트 등..) 

---

### 2025-09-03

#### 비즈니스 컴포넌트 DI

기존 boardDAO를 컨트롤러에서 메소드 파라미터로 주입받아 바로 DB에 접근하는 방식으로 실습을 진행했는데,  
boardDAO는 인터페이스가 아닌 실제 구현 클래스라서 결합도가 너무 높음

컨트롤러 필드에서 boardService 인터페이스를 @Autowired로 선언하면 컨테이너가 빈에 등록된 boardService의 구현체를 찾아 주입해주고,  
인터페이스를 사용하기 때문에 boardService의 구현체가 바뀌어도 컨트롤러 소스를 바꿀 필요가 없어진다  
즉 결합도가 낮아진다

---

### 2025-09-04

#### 검색 실습 (like)

easy

검색엔진 실습할 때 자세히..

---

### 2025-09-07

#### 파일 업로드 실습

1. 클라이언트 단에서 input type="file" 을 form 전송  
   이때 form의 enctype="multipart/form-data"로 설정해줘야 함
2. 서버에서 커맨드 객체로 받음 (커맨드 객체의 필드에 MultipartFile로 변수명 name과 매칭하여 선언해줘야함)  
   그냥 받으면 다 null로 들어옴 (스프링 MVC는 기본적으로 application/x-www-form-urlencoded만 처리 가능한데 form 전송을 multipart/form-data로 했기 때문에)  
   그래서 mulipartResolver 를 bean 등록해줘서 스프링 컨테이너가 multipart/form-data를 파싱할 수 있게 해줘야 함
   mulipartResolver은 전송된 multipart를 파싱해주고, 파싱 결과를 일반 필드는 기존과 같이 바인딩 할 수 있게(request.getParameter()처럼 접근 가능하도록)변환해주고 파일 필드는 MultipartFile 객체로 래핑해줌   
3. 컨트롤러에서 주입된 커맨드 객체의 file 필드 사용  
   MultipartFile.transfer(File destFile)으로 지정된 파일 원하는 경로에 업로드 (destFile의 path로 업로드 됨)

#### 예외 처리 실습

@ControllerAdvice와 @ExceptionHandler를 이용한 글로벌 예외 처리 실습  

---

### 2025-09-08

#### 다국어 처리 실습

Spring 다국어 처리

1. classpath에 원하는 폴더 (실습에선 message) 생성 후 properties 파일 생성  
   여기서 파일명을 스프링 설절파일에 명시해야 하니 약속대로 정의해준다 (실습에선 messageResource_ko(en).properties)
2. 스프링 설정 파일에 MessageSource bean을 등록하고 basenames property에 앞서 생성한 properties 파일 명시  
   message.messageSource (패키지명.파일명) <- 약속된 사용법이 있으니 주의  
3. LocaleResolver bean 등록
4. LocaleChangeInterceptor bean 등록
5. 사용 할 jsp에서 taglib 지시자 선언 후 다국어 처리 할 부분 수정  
   ex) <spring:message code="message.user.login.title">

---

### 2025-09-09

#### 데이터 변환

jackson2 라이브러리, @ResponseBody를 이용해 브라우저에 JSON 반환 실습

먼저, @ResponseBody을 명시하면 Spring은 리턴값을 뷰 대신 HTTP body에 직접 씀  
객체가 리턴되면 HttpMessageConverter가 실행되면서 JSON 변환 (정확히 말하면 jackson의 MappingJackson2HttpMessageConverter)  
Content-Type 헤더에 application/json이 세팅되어 브라우저가 JSON으로 인식

HttpMessageConverter 정리  
Spring의 HttpMessageConverter가 리턴타입, 요청헤더 등을 보고 알맞은 Converter를 골라서 실행해줌  
MappingJackson2HttpMessageConverter (jackson2 라이브러리 객체 <-> JSON 변환)  
StringHttpMessageConverter (단순 문자열 처리)  
FormHttpMessageConverter (application/x-www-form-urlencoded 처리)  
등등 다양한 MessageConverter가 있다

---