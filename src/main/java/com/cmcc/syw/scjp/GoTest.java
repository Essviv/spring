package com.cmcc.syw.scjp;

interface Go {
    void go();
}

/**
 * Created by sunyiwei on 2016/10/26.
 */
public class GoTest {
    public static void main(String[] args) {
        Sente a = new Sente();
        a.go();

        Goban b = new Goban();
        b.go();

        Stone c = new Stone();
        c.go();
    }
}

class Sente implements Go {
    @Override
    public void go() {
        System.out.println("go in Sente.");
    }
}

class Goban extends Sente {
    @Override
    public void go() {
        System.out.println("go in Goban.");
    }
}

class Stone extends Goban implements Go {

}
