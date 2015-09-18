package com.cmcc.syw.controller;

import com.alibaba.fastjson.JSON;
import com.cmcc.syw.model.Student;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.transform.Source;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/")
@SessionAttributes("myName")
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
     * ��ʾModelAttribute��RequestMappingһ��ʹ�ã���ʱ���ص�ֵ�洢��model�У�keyΪmodelAtribute�����õ�ֵ��ʹ��RequestToViewNameTranslator��������ͼ
     *
     * @return
     */
//    @RequestMapping("hello")
//    @ModelAttribute("name")
//    public String testModelAttribute2(){
//        return "Michal Jordan";
//    }
    @RequestMapping("ma/{myName}")
    public String testModelAttribute3(@ModelAttribute("myName") Student student) {
        System.out.println(student.toString());
        return "hello";
    }

    @RequestMapping("sa")
    public String testModelAttribute4(@ModelAttribute("myName") String name) {
        System.out.println(name);
        return "hello";
    }

    @RequestMapping("hello")
    @ModelAttribute("myName")
    public String testModelAttribute5() {
        return "patrick";
    }

//    @RequestMapping("hello")
//    public Model testMAV(@RequestParam String name, @RequestParam int age, @RequestBody String body) {
//        Model model = new ExtendedModelMap();
//        model.addAttribute("name", name);
//        System.out.println("RequestBody: " + body);
//
//        return model;
//    }

    @RequestMapping("rb")
    public String testReqRespBody(ModelMap modelMap, @RequestBody Student student) {
        modelMap.addAttribute("name", student.getName());
        return "hello";
    }

    @RequestMapping("json/{name}/{age}")
    @ResponseBody
    public Student testJSON(@PathVariable String name, @PathVariable int age) {
        return new Student(name, age);
    }

    @RequestMapping("testXmlVR")
    public String testXmlViewResolver() {
        return "testXmlViewResolver";
    }

    @RequestMapping("testRV")
    public String testRedirectView() {
        return "redirect:http://www.baidu.com/";
    }

    @RequestMapping("testJSON")
    public String testMultiVR(Model model) {
        model.addAttribute("list", getList());
        model.addAttribute("student", new Student("Essviv", 27));

        return "welcome";
    }

    private List<Student> getList() {
        final int count = 10;
        List<Student> students = new LinkedList<Student>();
        for (int i = 0; i < count; i++) {
            students.add(new Student("Patrick_" + i, 27));
        }

        return students;
    }

    @RequestMapping("rs/{word}")
    @ResponseBody
    public String testRS(Model model, @PathVariable String word) {
        model.addAttribute("word", word);
        return word;
    }

    @RequestMapping("fu")
    public String testUpload() {
        return "upload";
    }

    @RequestMapping(value = "upload", method = RequestMethod.POST)
    @ResponseBody
    public String uploadSave(@RequestParam("file")MultipartFile file) throws IOException {
        String filename = file.getOriginalFilename();
        String basedir = "C:\\Users\\Lenovo\\Desktop\\copy\\";

        FileUtils.writeByteArrayToFile(new File(getName(basedir, filename)), file.getBytes());
        return "OK";
    }

    private String getName(String baseDir, String oriName){
        return baseDir + oriName;
    }

    @RequestMapping("tc/{name}/{age}")
    @ResponseBody
    public String testConversion(@PathVariable("name") Student student, @PathVariable int age){
        student.setAge(age);
        return JSON.toJSONString(student);
    }

    @RequestMapping("hmc/show")
    public String showHMC(){
        return "trb";
    }

    @RequestMapping(value = "hmc/{name}/{age}", produces = "text/html")
    @ResponseBody
    public Student testHMC(@RequestBody Student student){
        return student;
    }
}



