package com.harejo.appsas.myapplication.Items;

import android.widget.ImageView;

import com.harejo.appsas.myapplication.DownloadFilesAsyncTask;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by Haroldas on 2015-04-19.
 */
public class Item {

    public String getBitmapPath() {
        return bitmapPath;
    }

    public void setBitmapPath(String bitmapPath) {
        this.bitmapPath = bitmapPath;
    }

    String bitmapPath;

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    int foto;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    String name;

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    int type;

    public enum Type {
        CONTACT(1),
        ACTION(2),
        ARTICLE(3);

        public int type;

        Type(int type) {
            this.type = type;
        }

        public int getType() {
            return type;
        }

        private static final List<Type> VALUES =
                Collections.unmodifiableList(Arrays.asList(values()));
        private static final int SIZE = VALUES.size();
        private static final Random RANDOM = new Random();

        public static Type randomType() {
            return VALUES.get(RANDOM.nextInt(SIZE));
        }

    }

    public void downloadImage(ImageView imageView){
        new DownloadFilesAsyncTask(imageView).execute(bitmapPath);
    }

}
