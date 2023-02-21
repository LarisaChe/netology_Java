package javacore36.lache.api;

import javacore36.lache.db.MyEntity;
import javacore36.lache.service.MyService;

public class Main {
    public static void main(String[] args) {
        MyService myService = new MyService();
        System.out.println(myService.getMyEntity());
        System.out.println(myService.setMyEntity(new MyEntity("second")));
        System.out.println(myService.getMyEntity());
    }
}