package com.emrhmrc.arcorevideo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.emrhmrc.arcorelib.HuhuuArcoreLibActivity;
import com.emrhmrc.arcorelib.PermsHelper;
import com.emrhmrc.arcorevideo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

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
                Toast.makeText(this,result,Toast.LENGTH_LONG).show();
                binding.videoview.setVideoPath(result);
                binding.videoview.start();
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //TODO
            }
        }
    }
}