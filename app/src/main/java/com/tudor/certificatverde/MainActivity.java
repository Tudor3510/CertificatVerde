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
import android.provider.Settings;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private static final String FILE_PROVIDER_NAME = "fileprovider";
    private static final int REQUEST_CODE = 753;
    private boolean isReadable = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!canRead()){
            isReadable = false;
            requestRead();
        }
    }

    @Override
    public void onResume(){
        super.onResume();

        if (isReadable) openPdf();
    }

    private boolean canRead() {
        // Impractical must first ask for useless Storage permission...
        File exSD = Environment.getExternalStorageDirectory();
        return exSD.canRead(); // this test works only if Storage permission was granted.
    }

    private void requestRead(){
         Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
         Uri uri = Uri.fromParts("package", getPackageName(), null);
         intent.setData(uri);
         startActivity(intent);
    }



    private void openPdf(){
        String url = Environment.getExternalStorageDirectory().getAbsolutePath()+"/CertificatVerde/Certificat Verde.pdf";
        Uri pdfUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + "." + FILE_PROVIDER_NAME, new File(url));

        Intent shareIntent = new Intent(Intent.ACTION_VIEW);
        shareIntent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
        shareIntent.setDataAndType(pdfUri, "application/pdf");

        startActivity(shareIntent);
    }


}