package com.cmcc.syw.reflection.dynamic;

/**
 * Created by sunyiwei on 2016/11/9.
 */
public class BirdFactory extends AbstractAnimalFactory {
    @Override
    Animal create() {
        return new Bird();
    }
}
