package com.cmcc.syw.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunyiwei on 2015/10/22.
 */
class JsonThirdObject {
    private int age = 81;
    private String name = "Michael Caine";
    private List<String> messages;

    public JsonThirdObject() {
        this.messages = new ArrayList<String>() {
            {
                add("You wouldn't hit a man with no trousers..");
                add("At this point, I'd set you up with a..");
                add("You know, your bobby dangler, giggle stick,..");
            }
        };
    }

    @Override
    public String toString() {
        return "JsonThirdObject{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", messages=" + messages +
                '}';
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
}


class JsonSecondObject {
    private int age = 83;
    private String name = "Clint Eastwood";
    private JsonThirdObject jsnTO = new JsonThirdObject();
    private List<String> messages;

    public JsonSecondObject() {
        this.messages = new ArrayList<String>() {
            {
                add("This is the AK-47 assault..");
                add("Are you feeling lucky..");
                add("When a naked man's chasing a..");
            }
        };
    }

    @Override
    public String toString() {
        return "JsonSecondObject{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", jsnTO=" + jsnTO +
                ", messages=" + messages +
                '}';
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JsonThirdObject getJsnTO() {
        return jsnTO;
    }

    public void setJsnTO(JsonThirdObject jsnTO) {
        this.jsnTO = jsnTO;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
}


class JsonFirstObject {
    private int age = 76;
    private String name = "Morgan Freeman";
    private JsonSecondObject jsnSO = new JsonSecondObject();
    private List<String> messages;

    public JsonFirstObject() {
        this.messages = new ArrayList<String>() {
            {
                add("I once heard a wise man say..");
                add("Well, what is it today? More..");
                add("Bruce... I'm God. Circumstances have..");
            }
        };
    }

    @Override
    public String toString() {
        return "JsonFirstObject{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", jsnSO=" + jsnSO +
                ", messages=" + messages +
                '}';
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JsonSecondObject getJsnSO() {
        return jsnSO;
    }

    public void setJsnSO(JsonSecondObject jsnSO) {
        this.jsnSO = jsnSO;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
}

@XmlRootElement(name = "json")
public class Json {
    @XmlElement
    private int age = 52;

    @XmlElement
    private String name = "Jim Carrey";

    @XmlElement
    private JsonFirstObject jsnFO = new JsonFirstObject();

    @XmlElementWrapper(name = "messages")
    @XmlElement(name = "message")
    private List<String> messages;

    public Json() {
        this.messages = new ArrayList<String>() {
            {
                add("Hey, maybe I will give you..");
                add("Excuse me, I'd like to..");
                add("Brain freeze. Alrighty Then I just..");
            }
        };
    }

    @Override
    public String toString() {
        return "Json{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", jsnFO=" + jsnFO +
                ", messages=" + messages +
                '}';
    }
}
