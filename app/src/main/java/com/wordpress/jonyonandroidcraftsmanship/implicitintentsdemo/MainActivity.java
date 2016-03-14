package com.wordpress.jonyonandroidcraftsmanship.implicitintentsdemo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void process(View view) {
        Intent intent = null;
        Intent chooser = null;

        switch (view.getId()) {
            case R.id.btnLaunchMap:
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("geo:19.076,72.8777"));
                chooser = Intent.createChooser(intent, "Launch Maps");
                startActivity(chooser);
                break;
            case R.id.btnLaunchMarket:
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("market://details?id=dolphin.developers.com"));
                chooser = Intent.createChooser(intent, "Launch Market");
                startActivity(chooser);
                break;
            case R.id.btnSendEmail:
                intent = new Intent(Intent.ACTION_SEND);
                intent.setData(Uri.parse("mailto:"));
                String[] to = {"jonyonodesk@gmail.com"};
                intent.putExtra(Intent.EXTRA_EMAIL, to);
                intent.putExtra(Intent.EXTRA_SUBJECT, "Hi, this was sent from my app.");
                intent.putExtra(Intent.EXTRA_TEXT, "Hi, this is my first mail from my app.");
                intent.setType("message/rfc822");
                chooser = Intent.createChooser(intent, "Send Email");
                startActivity(chooser);
                break;
            case R.id.btnSendImage:
                Uri imageUri = Uri.parse("android.resource://com.wordpress.jonyonandroidcraftsmanship.implicitintentsdemo/drawable" + R.drawable.short_hair);
                intent = new Intent(Intent.ACTION_SEND);
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_STREAM, imageUri);
                intent.putExtra(Intent.EXTRA_TEXT, "Hey, I have attached this image.");
                chooser = Intent.createChooser(intent, "Send Image");
                startActivity(chooser);
                break;
            case R.id.btnSendImages:
                File pictures= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                String[] listOfPictures=pictures.list();
                Uri uri=null;
                ArrayList<Uri> uris=new ArrayList<Uri>();
                for (String picture:listOfPictures){
                    uri=Uri.parse("file://"+pictures.toString()+"/"+picture);
                    uris.add(uri);
                }
                intent = new Intent(Intent.ACTION_SEND_MULTIPLE);
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_STREAM, uris);
                chooser = Intent.createChooser(intent, "Send Multiple Images");
                startActivity(chooser);
                break;
            default:
                break;
        }
    }
}