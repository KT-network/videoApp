package com.kt.coffee.cat.ktPlay.ui.Danmuk;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;

import master.flame.danmaku.danmaku.loader.ILoader;
import master.flame.danmaku.danmaku.loader.IllegalDataException;
import master.flame.danmaku.danmaku.parser.IDataSource;
import master.flame.danmaku.danmaku.parser.android.JSONSource;

public class KsJsonLoader implements ILoader {


    private static volatile KsJsonLoader instance;
    private KsJsonSource source;

    public static ILoader instance() {
        if (instance == null) {
            synchronized (KsJsonLoader.class) {
                if (instance == null) {
                    instance = new KsJsonLoader();
                }
            }
        }

        return instance;

    }

    @Override
    public IDataSource<?> getDataSource() {
        return source;
    }

    @Override
    public void load(String uri) throws IllegalDataException {
        load(new StringReader(uri));
    }

    @Override
    public void load(InputStream in) throws IllegalDataException {
        InputStreamReader reader = new InputStreamReader(in);
        load(reader);
    }

    public void load(Reader reader) {
        source = new KsJsonSource(reader);
    }

}
