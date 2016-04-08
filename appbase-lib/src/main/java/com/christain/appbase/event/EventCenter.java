package com.christain.appbase.event;


import de.greenrobot.event.EventBus;

public class EventCenter {

    private EventCenter() {

    }

    //静态内部类单例写法
    private static class EventBusLoader {
        private static final EventBus INSTANCE = new EventBus();

    }

    //静态内部类单例写法
    public static EventBus getInstance() {
        return EventBusLoader.INSTANCE;
    }

//    private static final EventBus instance = new EventBus();
//
//    public static final EventBus getInstance() {
//        return instance;
//    }

}
