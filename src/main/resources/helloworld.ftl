package ${className?substring(0, className?last_index_of("."))};

public class ${className?substring(className?last_index_of(".") + 1)}{
    public static void main(String[] args){
        System.out.println("Hello world");
    }
}