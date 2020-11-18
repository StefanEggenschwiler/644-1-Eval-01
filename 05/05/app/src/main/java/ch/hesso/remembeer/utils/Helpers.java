package ch.hesso.remembeer.utils;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Environment;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ch.hesso.remembeer.R;
import ch.hesso.remembeer.database.entity.BeerEntity;

public class Helpers {


    @RequiresApi(api = Build.VERSION_CODES.N)
    public static String saveBitmapToInternalStorage(Bitmap bitmapImage, Context context){
        ContextWrapper cw = new ContextWrapper(context);
        String datetime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File directory = new File(cw.getDataDir() + "/images");
        if(!directory.exists()) {
            directory.mkdir();
        }
        File path = new File(directory,datetime+"_import.png");

        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(path);
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return path.getAbsolutePath();
    }

    public static void setImageFromPathOrDefault(ImageView image, String path, Object item) {
        File imgFile = new File(path);

        if(imgFile.exists()) {
            Bitmap bitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            image.setImageBitmap(bitmap);
        } else {
            image.setImageResource(0);
            if(item.getClass() == BeerEntity.class) {
                image.setImageResource(R.drawable.beer_default);
            } else {
                image.setImageResource(R.drawable.brasserie_default);

            }
        }
    }
}
