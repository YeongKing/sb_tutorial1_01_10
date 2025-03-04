package com.sbs.tutorial1.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
// 개발자가 스프링부트에게 말한다.
// 이 클래스는 웹 요청을 받아서 작업을 한다.
// 해당 클래스는 컨트롤러야!
public class HomeController {
    List<Person> personList;

    public HomeController(){

        personList = new ArrayList<>();
    }


    @GetMapping("/home/main")
    // 개발자가 /home/main 이르는 요청을 보내면 아래 메서드를 실행
    @ResponseBody
    // 아래 메서드를 실행 후 리턴값을 응답으로 삼아
    // body에 출력해줘
    public String showHome(){
        return "어서오세요.";
    }

    @GetMapping("/home/main2")
    @ResponseBody
    public String showHome2(){
        return "환영합니다.";
    }

    @GetMapping("/home/main3")
    @ResponseBody
    public String showHome3(){
        return "스프링부트 획기적이다.";
    }

    @GetMapping("/home/plus")
    @ResponseBody
    // @RequestParam은 생략 가능 (int a 는 생략되어있는 상태)
    // Settings에 Java Compiler 에서 Additional command line parameters에 *parameters를 입력하지않으면 전부 붙여줘야함
    // @RequestParam(defaultValue = "10") : b라는 이름으로 파라미터를 넘기지 않으면 기본값 10으로 b에 입력됨
    // DefaultValue = "값" 입력해주면 기본값으로 사용 가능
    // 웹 브라우저는 데이터형을 무조건 String으로 인식 -> 내가 정수값으로 리턴해도 화면에서는 문자열 형식으로 출력된다.
    public int showPlus(int a, @RequestParam(defaultValue = "10") int b){

        return a+b;
    }

    @GetMapping("/home/returnBoolean")
    @ResponseBody
    public boolean showBoolean(){

        return false;
        //return true;
    }


    @GetMapping("/home/returnDouble")
    @ResponseBody
    public Double showReturnDouble(){

        return Math.PI;
        //return true;
    }


    @GetMapping("/home/returnArray")
    @ResponseBody
    // 원래라면 arr는 주소값이 나와야하는데 스프링부트가 내부적으로 도와줘서 주소가 아닌 배열로 변환해서 출력해줌
    // 스프링부트는 기본적으로 JSON 형태로 변환해주는 라이브러리가 탑제되어있다.
    public int[] showReturnArray(){

        int[] arr=new int[]{10,20,34};
        System.out.println(arr);

        return arr;

    }


    @GetMapping("/home/returnList")
    @ResponseBody
    // 해당 리스트를 리턴하면 배열과 동일하게 출력된다.
    // 해당 방식은 익명 내부 클래스(Anonymous Inner Class)를 사용하여 ArrayList를 초기화 하였음. - 이중 괄호 초기화(Double Brace Initialization)
    // 일반적으로는 리스트 초기화시 이렇게 사용함
    /*  List<Integer> list = new ArrayList<>();
        list.add(30);
        list.add(20);
        list.add(10);*/

    //동작 방식
    //new ArrayList<>()를 호출하면 새로운 ArrayList<Integer> 객체가 생성됨.
    //익명 내부 클래스가 만들어짐 (ArrayList<Integer>를 상속하는 익명 클래스).
    //{} 블록 내부에서 add(30), add(20), add(10)이 실행됨.
    //초기화된 리스트가 list 변수에 저장됨.
    public List<Integer> showReturnList(){

        List<Integer> list=new ArrayList<>(){{
            add(30);
            add(20);
            add(10);
        }};
        System.out.println(list);

        return list;

    }



    @GetMapping("/home/returnMap")
    @ResponseBody
    // HashMap은 순서를 보장하지 않는다.
    // 순서를 보장하고 싶으면 LinkedHashMap 사용
    // articleNo쪽에는 익명 내부 클랫 사용
    public Map<String,Object> showReturnMap(){

        Map<String, Object> map =new LinkedHashMap<>(){{
            put("id",1);
            put("subject","스프링부트는 무엇인가요?");
            put("content","스프링부트는 무엇이고 어떻게?...");
            put("articleNo",new ArrayList<>(){{
                add(1);
                add(2);
                add(3);
            }});


        }};
        System.out.println(map);

        return map;

    }


    @GetMapping("/home/returnArticle")
    @ResponseBody
    // 객체를 반환해도 map과 비슷하게 출력된다.

    public Article1 showReturnArticle(){
        Article1 article = new Article1(1, "제목1","내용1",new ArrayList<>(){{
            add(1);
            add(2);
            add(3);
        }});

        return article;

    }

    @GetMapping("/home/returnArticle2")
    @ResponseBody
    // 객체를 반환해도 map과 비슷하게 출력된다.

    public Article1 showReturnArticle2(){
        Article1 article = new Article1(2, "제목2","내용2",new ArrayList<>(){{
            add(4);
            add(5);
            add(6);
        }});

        return article;

    }

    @GetMapping("/home/returnArticleMapList")
    @ResponseBody
    public List<Map<String,Object>> showReturnMapList(){
        //객체 자체를 출력하나 , 맵 으로 출력하나 결과는 동일하다.
        Map<String,Object> articleMap1 =new LinkedHashMap<>(){{
            put("id",1);
            put("subject","제목1");
            put("content","내용1");
            put("articleNo",new ArrayList<>(){{
                add(1);
                add(2);
                add(3);
            }});
        }};

        Map<String,Object> articleMap2 =new LinkedHashMap<>(){{
            put("id",2);
            put("subject","제목2?");
            put("content","내용2");
            put("articleNo",new ArrayList<>(){{
                add(4);
                add(5);
                add(6);
            }});
        }};

        List<Map<String,Object>> list =new ArrayList<>(){{
            add(articleMap1);
            add(articleMap2);

        }};



        return list;

    }

    @GetMapping("/home/returnArticleList")
    @ResponseBody
    public List<Article2> showReturnArticleList(){



        Article2 article1 = new Article2(1, "제목1","내용1",new ArrayList<>(){{
            add(1);
            add(2);
            add(3);
        }});



        Article2 article2 = new Article2(2, "제목2","내용2",new ArrayList<>(){{
            add(4);
            add(5);
            add(6);
        }});

        List<Article2> list =new ArrayList<>();

        list.add(article1);
        list.add(article2);



        return list;
    };


    @GetMapping("/home/addPerson")
    @ResponseBody
    public String addPerson(String name, int age){


        Person p = new Person(name,age);

        personList.add(p);

        return "%d번 사람이 추가되었습니다.".formatted(p.getId());



    }

    @GetMapping("/home/showPeople")
    @ResponseBody
    public List<Person> showPeople(){



        return personList;
    }








}

@Getter
@Setter
@AllArgsConstructor
@ToString
class Person{
    private static int lastId;
    private final int id;
    private String name;
    private int age;


    static{
        lastId = 0;

    }

    public Person(String name,int age){
        this(++lastId,name,age);

    }


}









class Article1 {
    private final int id;
    private  String subject;
    private  String content;
    private List<Integer> articleNo;

    public Article1(int id, String subject, String content, List<Integer> articleNo) {
        this.id = id;
        this.subject = subject;
        this.content = content;
        this.articleNo = articleNo;
    }

    public int getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Integer> getArticleNo() {
        return articleNo;
    }

    public void setArticleNo(List<Integer> articleNo) {
        this.articleNo = articleNo;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                ", articleNo=" + articleNo +
                '}';
    }






}


@Getter
@Setter
@AllArgsConstructor
@ToString
class Article2 {
    private final int id;
    private  String subject;
    private  String content;
    private List<Integer> articleNo;


}
