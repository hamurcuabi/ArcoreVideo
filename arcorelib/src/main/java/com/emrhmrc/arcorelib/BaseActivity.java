package com.emrhmrc.arcorelib;

import android.Manifest;
import android.content.pm.PackageManager;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.Snackbar;

/**
 * Created by hamurcuabi on 09,August,2020
 **/
public class BaseActivity extends AppCompatActivity {

    private static final String ApiKey = "681D6B8E-0B98-45C3-AF7C-810AB9850F0C";

    public boolean hasWritePermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else { //izin yoksa
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    100);
            return false;
        }
    }

    public boolean hasRecordPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else { //izin yoksa
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO},
                    100);
            return false;
        }
    }

    public boolean hasCameraPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else { //izin yoksa
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
                    100);
            return false;
        }
    }
    public boolean checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                + ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                + ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
                + ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                    || ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)
                    || ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECORD_AUDIO)
                    || ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)


            )

            {

                Snackbar.make(this.findViewById(android.R.id.content),
                        "İzinleri vermelsiiniz",
                        Snackbar.LENGTH_INDEFINITE).setAction("VER",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                requestPermissions(
                                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE
                                                , Manifest.permission.CAMERA
                                                , Manifest.permission.RECORD_AUDIO
                                                , Manifest.permission.WRITE_EXTERNAL_STORAGE
                                        },
                                        111);
                            }
                        }).show();
            } else {
                requestPermissions(
                        new String[]{
                                Manifest.permission.READ_EXTERNAL_STORAGE
                                , Manifest.permission.CAMERA
                                , Manifest.permission.RECORD_AUDIO
                                , Manifest.permission.WRITE_EXTERNAL_STORAGE
                        },
                        111);
            }
        } else {
           return true;
        }
        return false;
    }
}
