package com.tobio.tobioutils.utils;

import java.io.Serializable;

public class Pair<T, S> implements Serializable {

    private static final long serialVersionUID = 1L;

    private transient T       first            = null;
    private transient S       second           = null;


    // public Pair() {
    //
    // }

    public Pair(T first, S second) {
        this.first = first;
        this.second = second;
    }


    public Object getFirst() {
        return this.first;
    }


    public void setFirst(T first) {
        this.first = first;
    }


    public S getSecond() {
        return this.second;
    }


    public void setSecond(S second) {
        this.second = second;
    }
}
