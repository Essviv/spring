package com.cmcc.syw.controller;

import com.alibaba.fastjson.JSON;
import com.cmcc.syw.model.Json;
import com.cmcc.syw.model.Student;
import com.cmcc.syw.service.AuthorService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.io.FileUtils;
import org.openid4java.consumer.ConsumerException;
import org.openid4java.consumer.ConsumerManager;
import org.openid4java.discovery.DiscoveryException;
import org.openid4java.discovery.DiscoveryInformation;
import org.openid4java.message.AuthRequest;
import org.openid4java.message.MessageException;
import org.openid4java.message.ax.FetchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
    @Autowired
    AuthorService service;

    public final ConsumerManager manager = new ConsumerManager();

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
    public String testConversion(HttpServletRequest request, @PathVariable("name") Student student, @PathVariable int age){
        System.out.println(request.getQueryString());

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

    @RequestMapping("openid/showLogin")
    public void showLogin(HttpServletResponse response) throws IOException {
        response.sendRedirect("http://localhost:8080/spring/tc/sunyiwei/27.html#query=434&name=patrick");
    }

    @RequestMapping(value = "openid/login", method = RequestMethod.POST)
    public void login(String openID, HttpServletRequest request, HttpServletResponse response, UriComponentsBuilder ucb) throws MessageException, IOException, ConsumerException, DiscoveryException {
        System.out.println(openID);


        String returnUrl  = "http://www.baidu.com";

        // perform discovery on the user-supplied identifier
        List discoveries = manager.discover(openID);

        // attempt to associate with the OpenID provider
        // and retrieve one service endpoint for authentication
        DiscoveryInformation discovered = manager.associate(discoveries);

        // store the discovery information in the user's session
        request.getSession().setAttribute("openid-disc", discovered);

        // obtain a AuthRequest message to be sent to the OpenID provider
        AuthRequest authReq = manager.authenticate(discovered, returnUrl);

        // attribute Exchange
        FetchRequest fetch = FetchRequest.createFetchRequest();
        fetch.addAttribute("email", false);
        fetch.addAttribute("firstName", false);
        fetch.addAttribute("lastName", false);

        // attach the extension to the authentication request
        authReq.addExtension(fetch);

        if (!discovered.isVersion2()) {
            // Option 1: GET HTTP-redirect to the OpenID Provider endpoint
            // The only method supported in OpenID 1.x
            // redirect-URLTest usually limited ~2048 bytes
            response.sendRedirect(authReq.getDestinationUrl(true));
        } else {
            // Option 2: HTML FORM Redirection (Allows payloads >2048 bytes)
            response.sendRedirect(authReq.getDestinationUrl(true));
        }
    }

    public static void main(String[] args) {
        Json json = new Json();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String jsonStr = gson.toJson(json);
        System.out.println(jsonStr);
    }

    @RequestMapping("testAOP")
    public String testAOP(){
//        service.insert(new Author(234L, "sunyiwei", 27));
        return "welcome";
    }
}



