package com.sbs.tutorial1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
// 개발자가 스프링부트에게 말한다.
// 이 클래스는 웹 요청을 받아서 작업을 한다.
// 해당 클래스는 컨트롤러야!
public class HomeController {
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









}
