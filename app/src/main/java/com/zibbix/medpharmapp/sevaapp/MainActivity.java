package com.zibbix.medpharmapp.sevaapp;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import static android.Manifest.permission.CALL_PHONE;
import static android.Manifest.permission.READ_CONTACTS;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.SEND_SMS;


public class MainActivity extends AppCompatActivity {
    public static final int RequestPermissionCode = 7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(!CheckingPermissionIsEnabledOrNot())
        {
            RequestMultiplePermission();
        }
    }

    public void panic(View view)
    {
        int id=view.getId();
switch (id)
{
    case R.id.imageView8:
        Intent intent = getPackageManager().getLaunchIntentForPackage("io.hypertrack.sendeta");
        if (intent != null) {
            // We found the activity now start the activity
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else {
            // Bring user to the market or let them choose an app?
            intent = new Intent(Intent.ACTION_VIEW);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setData(Uri.parse("market://details?id=" + "io.hypertrack.sendeta"));
            startActivity(intent);
        }break;
    case  R.id.imageView6:
        Intent intent1=new Intent(this,ambulance.class);
        startActivity(intent1);break;
    case R.id.imageView:
        Intent intent11=new Intent(this,PoliceActivity.class);
        startActivity(intent11);break;
    case  R.id.imageView7:
        Intent intent12=new Intent(this,LawActivity.class);
        startActivity(intent12);break;
    case R.id.imageView4:
        Intent intent120=new Intent(this,HelpdeskActivity.class);
        startActivity(intent120);break;

}
    }
    public void pan (View view)
    {
        Intent  i=new Intent(this,panic.class);
        startActivity(i);
    }
    private void RequestMultiplePermission() {

        // Creating String Array with Permissions.
        ActivityCompat.requestPermissions(MainActivity.this, new String[]
                {

                        SEND_SMS,
                        CALL_PHONE,
                        READ_CONTACTS,
                        READ_EXTERNAL_STORAGE
                }, RequestPermissionCode);

    }

    // Calling override method.
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {

            case RequestPermissionCode:

                if (grantResults.length > 0) {

                    boolean SendSMSPermission = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean GetAccountsPermission = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    boolean Cont=grantResults[2]==PackageManager.PERMISSION_GRANTED;
                    boolean Contt=grantResults[3]==PackageManager.PERMISSION_GRANTED;
                    if (SendSMSPermission && GetAccountsPermission && Cont&&Contt) {

                        Toast.makeText(MainActivity.this, "Permission Granted", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Permission Denied", Toast.LENGTH_LONG).show();

                    }
                }

                break;
        }
    }

    // Checking permission is enabled or not using function starts from here.
    public boolean CheckingPermissionIsEnabledOrNot() {


        int SecondPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), CALL_PHONE);
        int ThirdPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), SEND_SMS);
        int contact=ContextCompat.checkSelfPermission(getApplicationContext(),READ_CONTACTS);
        int Contt=ContextCompat.checkSelfPermission(getApplicationContext(),READ_EXTERNAL_STORAGE);
        return SecondPermissionResult == PackageManager.PERMISSION_GRANTED &&
                ThirdPermissionResult == PackageManager.PERMISSION_GRANTED&&
                contact==PackageManager.PERMISSION_GRANTED&&Contt==PackageManager.PERMISSION_GRANTED;

    }

}
