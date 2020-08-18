package com.emrhmrc.arcorevideo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.abedelazizshe.lightcompressorlibrary.CompressionListener;
import com.abedelazizshe.lightcompressorlibrary.VideoCompressor;
import com.abedelazizshe.lightcompressorlibrary.VideoQuality;
import com.emrhmrc.arcorelib.HuhuuArcoreLibActivity;
import com.emrhmrc.arcorelib.PermsHelper;
import com.emrhmrc.arcorevideo.databinding.ActivityMainBinding;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final int HuhuuArcoreResultCode = 1995;
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if(new PermsHelper(this).checkPermission())
        startActivityForResult(new Intent(this, HuhuuArcoreLibActivity.class), HuhuuArcoreResultCode);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == HuhuuArcoreResultCode) {
            if (resultCode == Activity.RESULT_OK) {
                String result = data.getStringExtra(HuhuuArcoreLibActivity.ArcoreVidePath);
                Toast.makeText(this, result, Toast.LENGTH_LONG).show();
                binding.videoview.setVideoPath(result);
                binding.videoview.start();
                //    compress(result);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //TODO
            }
        }
    }

    private void compress(String path) {
        File desFile =
                new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "test");
        VideoCompressor.start(path, desFile.getPath(), new CompressionListener() {
            @Override
            public void onStart() {
                // Compression start
                Log.d(TAG, "onStart: ");
            }

            @Override
            public void onSuccess() {
                // On Compression success
                Log.d(TAG, "onSuccess: ");
            }

            @Override
            public void onFailure(String failureMessage) {
                // On Failure
                Log.d(TAG, "onFailure: ");
            }

            @Override
            public void onProgress(float progressPercent) {
                // Update UI with progress value
                runOnUiThread(new Runnable() {
                    public void run() {
                        binding.text.setText(progressPercent + "%");
                        binding.progress.setProgress((int) progressPercent);
                    }
                });
            }

            @Override
            public void onCancelled() {
                // On Cancelled
                Log.d(TAG, "onCancelled: ");
            }
        }, VideoQuality.MEDIUM, false, false);
    }
}