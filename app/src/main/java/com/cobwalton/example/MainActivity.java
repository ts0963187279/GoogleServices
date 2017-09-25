package com.cobwalton.example;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cobwalton.googleservices.model.GoogleData;
import com.cobwalton.googleservices.processor.ConnectGoogleAccount;
import com.cobwalton.googleservices.processor.GoogleServices;
import com.cobwalton.googleservices.processor.SelectGoogleAccount;
import com.google.gdata.client.contacts.ContactsService;

public class MainActivity extends AppCompatActivity {
    GoogleData googleData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.cobwalton.example.R.layout.activity_main);
        GoogleServices googleServices = new GoogleServices(new ContactsService("GoogleServices"));
        googleData = googleServices.getGoogleData();
        googleData.setOnBackGroundResult(new ShowContractList());
        SelectGoogleAccount selectGoogleAccount = new SelectGoogleAccount(googleData,this);
        selectGoogleAccount.select();
    }
    protected void onActivityResult(final int requestCode,final int resultCode,final Intent data){
        ConnectGoogleAccount connectGoogleAccount = new ConnectGoogleAccount(requestCode,resultCode,data);
        connectGoogleAccount.connect(googleData);
    }
}
