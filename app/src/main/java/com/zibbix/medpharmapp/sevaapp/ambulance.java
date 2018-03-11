package com.zibbix.medpharmapp.sevaapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ambulance extends AppCompatActivity implements AdapterView.OnItemSelectedListener  {
    String[] bankNames = {"Basic Life support Ambulance", "Advanced Life support Ambulance", "Patient Transport Vehicle (PTV)", "Mortuary Ambulance Services", ""};
    private DatabaseReference userDatabase;
    private EditText edt;
    //defining firebaseauth object
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambulance);
        firebaseAuth = FirebaseAuth.getInstance();
        Spinner spin = (Spinner) findViewById(R.id.spinner3);
        spin.setOnItemSelectedListener(this);
        edt = findViewById(R.id.editText3);
        //Creating the ArrayAdapter instance having the bank name list
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, bankNames);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);


    }


    //Performing action onItemSelected and onNothing selected
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        Toast.makeText(getApplicationContext(), bankNames[position], Toast.LENGTH_LONG).show();
        userDatabase = FirebaseDatabase.getInstance().getReference().child("ambulance");
        String user_id = firebaseAuth.getCurrentUser().getUid();
        DatabaseReference current_user_db = userDatabase.child(user_id);
        current_user_db.child("ambulanceinfo").setValue(bankNames[position]);
    }


    @Override
    public void onNothingSelected(AdapterView<?>a) {
// TODO Auto-generated method stub


    }

    public void inform(View view) {
        String number = getMobileNumber();
        String str = edt.getText().toString();
        userDatabase = FirebaseDatabase.getInstance().getReference().child("ambulance");
        String user_id = firebaseAuth.getCurrentUser().getUid();
        DatabaseReference current_user_db = userDatabase.child(user_id);
        current_user_db.child("patients_strength").setValue(str);
        current_user_db.child("new").setValue(number);


    }

    public String getMobileNumber() {

        TelephonyManager telephonyManager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return "";
        } else {
            String strMobileNumber = telephonyManager.getLine1Number();

// Note : If the phone is dual sim, get the second number using :

// telephonyManager.getLine2Number();

            return strMobileNumber;
        }

    }
    public void gonav(View view)
    {
        Intent intent=new Intent(this,MyLocationUsingHelper.class);
        startActivity(intent);
    }
}

