function out() {
    var obj = {'name': 'sunyiwei', 'age': 27, 'schools': ['TJU', 'CAS']};

    console.log(obj.name);
    console.log(obj.age);
    console.log(obj.schools.concat());

    var test = "这个变量已经声明...";
    var test;

    console.log(test);
}

function testArr() {
    var arr = new Array;
    arr[0] = "arr0";
    arr[1] = "arr1";
    arr[2] = "arr2";

    console.log(arr);

    var arr2 = new Array("test1", "test2", "test3");
    console.log(arr2);

    var arr3 = ["sunyiwei", "patrick", "lisa"];
    console.log(arr3);
}

function testObj() {
    var obj = {
        "name": "sunyiwei",
        "age": 27,
        "schools": ["TJU", "CAS", "FZYZ"],
        "testMethod": function () {
            console.log("我是一个方法...")
        }
    };
    console.log(obj.name);
    console.log(obj["age"]);
    console.log(obj.testMethod);

    var pName = "schools";
    var arr = obj[pName];
    for (var com in arr) {
        console.log(arr[com]);
    }
}

function testStr(){
    var str = "this is a short string.";
    console.log(str.concat("hello world, "));
    //console.log(str.prototype.constructor === str.constructor);
    console.log(typeof str.prototype);
    console.log(str.constructor.prototype === String.prototype);
    console.log(Number("5555"));

    //以下是输出constructor
    console.log(3.14.constructor.toString());
    console.log("string".constructor.toString());
    console.log({}.constructor.toString());
    console.log([].constructor.toString());
    console.log(function(){}.constructor.toString());
}

function testClosure2(){
    var name = "testClosure2!";
    var obj = {
        "name": "obj",

       "test": function(){
           console.log(this);
           return name;
       }
    };

    return obj;
}