package com.mzusman.bluetooth.activities;

import android.app.Activity;
import android.content.Intent;
import android.nfc.FormatException;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.widget.Toast;

import com.mzusman.bluetooth.model.Model;
import com.mzusman.bluetooth.utils.Constants;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;


public class NfcActivity extends Activity {


    @Override
    protected void onResume() {
        super.onResume();
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(getIntent().getAction())) {
            Tag tag = getIntent().getParcelableExtra(NfcAdapter.EXTRA_TAG);
            Ndef ndef = Ndef.get(tag);
            try {
                ndef.connect();
                String msg = new String(Charset.forName("US-ASCII").decode(ByteBuffer.wrap(ndef.getNdefMessage().toByteArray())).array());
                if (!msg.contains(Constants.FLURRY_TAG)) {
                    Toast.makeText(this, "Invalid Tag", Toast.LENGTH_SHORT).show();
                    finish();
                }
                msg = msg.replaceAll("[^\\d]", "");
                Toast.makeText(this, "Welcome!,Id:" + msg,
                        Toast.LENGTH_SHORT).show();
                Model.getInstance().setDriverId(Integer.parseInt(msg)); //important
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                ndef.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (FormatException e) {
                e.printStackTrace();
            }
        }
    }


}
