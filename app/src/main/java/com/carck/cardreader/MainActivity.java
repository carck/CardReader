package com.carck.cardreader;

import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.IsoDep;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements NfcAdapter.ReaderCallback {
    private NfcAdapter nfcAdapter;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
    }

    @Override
    public void onTagDiscovered(Tag tag) {
        try {
            IsoDep isoDep = IsoDep.get(tag);
            isoDep.connect();
            final byte[] response = isoDep.transceive(Utils.hexStringToByteArray("00A4040007A0000002471001"));
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    textView.append("\nCard Response: " + Utils.toHex(response));
                }
            });
            isoDep.close();
        } catch (Exception e) {

        }
    }

    public void onResume() {
        super.onResume();
        nfcAdapter.enableReaderMode(this, this,
                NfcAdapter.FLAG_READER_NFC_A |
                        NfcAdapter.FLAG_READER_SKIP_NDEF_CHECK,
                null);
    }

    public void onPause() {
        super.onPause();
        nfcAdapter.disableReaderMode(this);
    }
}
