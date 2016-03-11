package com.jeranfox.peach.ui.views;

import android.content.Context;

import com.jeranfox.peach.entities.Connection;

import java.util.List;

public interface ExploreView {
    void setConnections(List<Connection> connections);
    Context getContext();
}
