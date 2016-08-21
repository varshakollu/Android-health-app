package com.example.varsha.navtry;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

public class SearchQrCode extends AppCompatActivity {

    static final String SCAN = "com.google.zxing.client.android.SCAN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_qr_code);
    }

    public void onclickhome(View v) {
        Intent intent = new Intent(SearchQrCode.this, MainActivity.class);
        startActivity(intent);
    }

    public void ScanQR(View v){

        try{
            Intent in = new Intent (SCAN);
            in.putExtra("SCAN_MODE", "QR_CODE_MODE");
            startActivityForResult(in, 0);
        }catch (ActivityNotFoundException e) {
            showDialog(SearchQrCode.this, "No Scanner Found", "Download a scanner code activity?", "Yes", "No").show();
        }
    }

    private Dialog showDialog(final Activity act, CharSequence title, CharSequence message, CharSequence Yes,  CharSequence No)
    {

        AlertDialog.Builder download = new AlertDialog.Builder(act);
        download.setTitle(title);
        download.setMessage(message);
        download.setPositiveButton(Yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i){
                Uri uri = Uri.parse("market://search?q=pname:" + "com.google.zxing.client.android");
                Intent in = new Intent(Intent.ACTION_VIEW, uri);
                try
                {
                    act.startActivity(in);
                }
                catch(ActivityNotFoundException anfe)
                {

                }
            }
        });
        download.setNegativeButton(No, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int i)
            {
            }
        });
        return download.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent in)
    {

        if(requestCode ==0){
            if(resultCode == RESULT_OK)
            {
                String contents = in.getStringExtra("SCAN_RESULT");
                String format =  in.getStringExtra("SCAN_RESULT_FORMAT") ;
                Toast toast = Toast.makeText(this, "Content:" + contents + " Format:" + format, Toast.LENGTH_LONG);
                toast.show();

                String content = contents.toString();


            }
        }
    }
}