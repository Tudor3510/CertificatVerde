package com.tudor.certificatverde;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ShareCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private static final String FILE_PROVIDER_NAME = "fileprovider";
    private static final int REQUEST_CODE = 753;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        openPdf();
    }

    @Override
    public void onResume(){
        super.onResume();

        openPdf();
    }
/*
    private void checkStoragePermission()
    {
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.MANAGE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.MANAGE_EXTERNAL_STORAGE }, REQUEST_CODE);
        }
    }

 */

    private void openPdf(){
        String url = Environment.getExternalStorageDirectory().getAbsolutePath()+"/CertificatVerde/Certificat Verde.pdf";
        Uri pdfUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + "." + FILE_PROVIDER_NAME, new File(url));

        Intent shareIntent = new Intent(Intent.ACTION_VIEW);
        shareIntent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
        shareIntent.setDataAndType(pdfUri, "application/pdf");

        startActivity(shareIntent);
    }


}