package com.kt.coffee.cat.ktPlay.ui.Danmuk;


import java.io.Reader;

import master.flame.danmaku.danmaku.parser.IDataSource;

public class KsJsonSource implements IDataSource<Reader> {

    Reader reader;

    public KsJsonSource(Reader reader){
        this.reader = reader;
    }


    @Override
    public Reader data() {
        return reader;
    }

    @Override
    public void release() {
        reader = null;
    }
}
