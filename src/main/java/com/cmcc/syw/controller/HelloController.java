package com.cmcc.syw.controller;

import com.cmcc.syw.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.velocity.VelocityView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/")
public class HelloController {
    @RequestMapping(value = "hi", method = RequestMethod.GET)
    public String printWelcome() {
        return "welcome";
    }

    @RequestMapping(value = "{name}", method = RequestMethod.GET)
    public String index(ModelMap modelMap, @PathVariable Map<String, String> map) {
        Set<String> keys = map.keySet();
        for (String key : keys) {
            modelMap.addAttribute(key, map.get(key));
        }

        return "hello";
    }

    @RequestMapping("world")
    public String index() {
        return "dom";
    }

    @RequestMapping("/ajax/{name}")
    public void getName(HttpServletResponse response, @PathVariable String name) throws IOException {
        response.getWriter().write(name);
    }

    @RequestMapping("{name}/{age}")
    @ResponseBody
    public Student test(@PathVariable String name, @PathVariable int age) {
        return new Student(name, age);
    }

    @RequestMapping("mv/{name}/{age}")
    public String testMatrixVariable(ModelMap modelMap,
                                     @PathVariable String name,
                                     @MatrixVariable(pathVar = "name") int q,
                                     @MatrixVariable(pathVar = "age", value = "q", defaultValue = "9999") int qAge,
                                     @MatrixVariable Map<String, LinkedList<String>> vars) {
        Set<String> keys = vars.keySet();
        for (String key : keys) {
//            System.out.println(vars.get(key));
            System.out.println(key + ": " + vars.get(key).toString());
//            System.out.println(vars.get(key));
        }

        System.out.println("Q: " + q);
        System.out.println("Q_AGE: " + qAge);

        modelMap.addAttribute("name", name);
        return "hello";
    }

    @RequestMapping("pv/{name}/{age}")
    public String testPathVariableObj(@PathVariable String name, @PathVariable int age,
                                      HttpSession session, ModelMap modelMap) {
        System.out.println(session.getId());

        System.out.println(name);
        System.out.println(age);

        modelMap.addAttribute("name", name);
        return "hello";
    }

    @RequestMapping("tw/{name}")
    public void testWriter(Writer writer, @PathVariable String name) throws IOException {
        writer.write("Hello, " + name + "\n");
//        writer.write("You are using " + method.name());
        writer.flush();
    }

    @RequestMapping("tr")
    public void testRequestHeaderAndParams(Writer writer,
                                           @RequestHeader("Accept-Encoding") String acceptedEncoding,
                                           @RequestParam("name") String name,
                                           @RequestBody String body) throws IOException {
        writer.write("Hello, " + name + "\n");
        writer.write("Acceped-Encoding: " + acceptedEncoding + "\n");
        writer.write("Request Body: " + body);
        System.out.println("Message Body: " + body);
        writer.flush();
    }

//    @ModelAttribute
//    public void initAttr(ModelMap modelMap, @RequestParam String name){
//        modelMap.addAttribute("name", name);
//    }

//    @ModelAttribute("customObj")
//    public Student initAttr2(@RequestParam String name, @RequestParam int age){
//        return new Student(name, age);
//    }
//
//    @RequestMapping("ma")
//    public String testModelAttribute(ModelMap modelMap, Student student){
//        System.out.println(student.toString());
//        modelMap.addAttribute("name", student.getName());
//
//        return "hello";
//    }

    /**
     * 演示ModelAttribute和RequestMapping一起使用，此时返回的值存储于model中，key为modelAtribute中设置的值，使用RequestToViewNameTranslator来解析视图
     *
     * @return
     */
//    @RequestMapping("hello")
//    @ModelAttribute("name")
//    public String testModelAttribute2(){
//        return "Michal Jordan";
//    }

    @RequestMapping("hello")
    public Model testMAV(@RequestParam String name, @RequestParam int age, @RequestBody String body){
        Model model = new ExtendedModelMap();
        model.addAttribute("name", name);
        System.out.println("RequestBody: " + body);

        return model;
    }
}

