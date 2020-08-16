package com.emrhmrc.arcorelib;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

/**
 * Created by hamurcuabi on 16,August,2020
 **/
public class PermsHelper {
    private Activity activity;
    private static final int PermRequestCode=111;

    public PermsHelper(Activity activity) {
        this.activity = activity;
    }

    public boolean hasWritePermission() {
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else { //izin yoksa
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    PermRequestCode);
            return false;
        }
    }

    public boolean hasRecordPermission() {
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else { //izin yoksa
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.RECORD_AUDIO},
                    PermRequestCode);
            return false;
        }
    }

    public boolean hasCameraPermission() {
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else { //izin yoksa
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA},
                    PermRequestCode);
            return false;
        }
    }

    public boolean checkPermission() {
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE)
                + ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA)
                + ContextCompat.checkSelfPermission(activity, Manifest.permission.RECORD_AUDIO)
                + ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.READ_EXTERNAL_STORAGE)
                    || ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.CAMERA)
                    || ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.RECORD_AUDIO)
                    || ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)


            ) {

                activity.requestPermissions(
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE
                                , Manifest.permission.CAMERA
                                , Manifest.permission.RECORD_AUDIO
                                , Manifest.permission.WRITE_EXTERNAL_STORAGE
                        },
                        PermRequestCode);

            } else {
                activity.requestPermissions(
                        new String[]{
                                Manifest.permission.READ_EXTERNAL_STORAGE
                                , Manifest.permission.CAMERA
                                , Manifest.permission.RECORD_AUDIO
                                , Manifest.permission.WRITE_EXTERNAL_STORAGE
                        },
                        PermRequestCode);
            }
        } else {
            return true;
        }
        return false;
    }
}
