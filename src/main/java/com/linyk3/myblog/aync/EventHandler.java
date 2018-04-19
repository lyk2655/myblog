package com.linyk3.myblog.aync;

import java.util.List;

/**
 * 异步事件处理
 */
public interface EventHandler {
    void doHandler(EventModel model);

    List<EventType> getSupportEventTypes();
}
