package com.carck.cardreader;

import android.nfc.cardemulation.HostApduService;
import android.os.Bundle;

import java.util.Objects;

public class HostCardEmulatorService extends HostApduService {
    @Override
    public byte[] processCommandApdu(byte[] commandApdu, Bundle extras) {
        if (commandApdu == null) {
            return Utils.hexStringToByteArray(Utils.STATUS_FAILED);
        }

        String hexCommandApdu = Utils.toHex(commandApdu);
        if (hexCommandApdu.length() < Utils.MIN_APDU_LENGTH) {
            return Utils.hexStringToByteArray(Utils.STATUS_FAILED);
        }

        if (!Objects.equals(hexCommandApdu.substring(0, 2), Utils.DEFAULT_CLA)) {
            return Utils.hexStringToByteArray(Utils.CLA_NOT_SUPPORTED);
        }

        if (!Objects.equals(hexCommandApdu.substring(2, 4), Utils.SELECT_INS)) {
            return Utils.hexStringToByteArray(Utils.INS_NOT_SUPPORTED);
        }

        if (Objects.equals(hexCommandApdu.substring(10, 24), Utils.AID))  {
            return Utils.hexStringToByteArray(Utils.STATUS_SUCCESS);
        } else {
            return Utils.hexStringToByteArray(Utils.STATUS_FAILED);
        }
    }

    @Override
    public void onDeactivated(int reason) {

    }
}
