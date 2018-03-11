package com.zibbix.medpharmapp.sevaapp;

import android.app.PendingIntent;
import android.content.Intent;
import android.location.Location;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class panic  extends   AppCompatActivity{
    private FirebaseAuth firebaseAuth;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panic);
        firebaseAuth = FirebaseAuth.getInstance();
        btn=findViewById(R.id.button4);
        String user_id = firebaseAuth.getCurrentUser().getUid();

        DatabaseReference databaseRefDept = FirebaseDatabase.getInstance().getReference().child("contacts").child(user_id).child("contact");
btn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        databaseRefDept.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                DatabaseReference databaseRefloc = FirebaseDatabase.getInstance().getReference().child("ambulance").child(user_id).child("Location");
                databaseRefloc.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dSnapshot) {
                        String value = dataSnapshot.getValue(String.class);
                        String loc = dSnapshot.getValue().toString();


                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);
                        //Get the SmsManager instance and call the sendTextMessage method to send message
                        SmsManager sms = SmsManager.getDefault();
                        sms.sendTextMessage(value, null, loc, pi, null);

                        Toast.makeText(getApplicationContext(), "Message Sent successfully!",
                                Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });



                }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
});

    }

    public void clicked(View view)
    {
        sendsms();
        Toast.makeText(panic.this,"Panic Mode Deactivated",Toast.LENGTH_SHORT).show();
    }

    public void sendsms()
    {

    }
}
