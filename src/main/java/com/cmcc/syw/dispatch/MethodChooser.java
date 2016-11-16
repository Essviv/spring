package com.cmcc.syw.dispatch;

/**
 * 演示静态多分派, 动态单分派
 *
 * Created by sunyiwei on 2016/11/16.
 */
public class MethodChooser {
    public static void main(String[] args) {
        Human human = new Human();
        Human man = new Man();
        Human woman = new Woman();
        Animal animal = new Animal();
        Animal duck = new Duck();
        Animal tiger = new Tiger();

        human.sayHello(animal);
        human.sayHello(duck);
        human.sayHello(tiger);
        man.sayHello(animal);
        man.sayHello(duck);
        man.sayHello(tiger);
        woman.sayHello(animal);
        woman.sayHello(duck);
        woman.sayHello(tiger);
    }

    private static class Human {
        void sayHello(Animal animal) {
            System.out.println("Human says: hello, animal.");
        }

        void sayHello(Duck duck) {
            System.out.println("Human says: hello, duck.");
        }

        void sayHello(Tiger tiger) {
            System.out.println("Human says: hello, tiger.");
        }
    }

    private static class Man extends Human {
        void sayHello(Animal animal) {
            System.out.println("Man says: hello, animal.");
        }

        void sayHello(Duck duck) {
            System.out.println("Man says: hello, duck.");
        }

        void sayHello(Tiger tiger) {
            System.out.println("Man says: hello, tiger.");
        }
    }

    private static class Woman extends Human {
        void sayHello(Animal animal) {
            System.out.println("Woman says: hello, animal.");
        }

        void sayHello(Duck duck) {
            System.out.println("Woman says: hello, duck.");
        }

        void sayHello(Tiger tiger) {
            System.out.println("Woman says: hello, tiger.");
        }
    }

    private static class Animal {
        public String getName() {
            return "Animal";
        }
    }

    private static class Tiger extends Animal {
        public String getName() {
            return "Tiger";
        }
    }

    private static class Duck extends Animal {
        public String getName() {
            return "Duck";
        }
    }
}
