package com.christain.appbase.common;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * 通过IMEI和MAC经过MD5加密，得到的基本算唯一的Android设备唯一表示码
 * 用于激活量的统计功能
 * Created by Administrator on 2015/12/15.
 */
public class DeviceIDUtil {

    private static String uuid;

    public static String getDeviceId(Context context) {
        if (!TextUtils.isEmpty(uuid)) {
            return uuid;
        }
        //获取imei号
        TelephonyManager TelephonyMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String imei = TelephonyMgr.getDeviceId();
        //获取mac地址
        WifiManager wm = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        String mac = wm.getConnectionInfo().getMacAddress();

        //重新组合的deviceId
        String deviceId = imei + mac;
        if (deviceId != null) {
            MessageDigest m = null;
            try {
                m = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                return UUID.randomUUID().toString();
            }
            m.update(deviceId.getBytes(), 0, deviceId.length());
            byte p_md5Data[] = m.digest();
            String uniqueID = new String();
            for (int i = 0; i < p_md5Data.length; i++) {
                int b = (0xFF & p_md5Data[i]);
                if (b <= 0xF)
                    uniqueID += "0";
                uniqueID += Integer.toHexString(b);
            }
            uniqueID = uniqueID.toUpperCase();
            if (uniqueID != null) {
                uuid = uniqueID;
                return uniqueID;
            }
        }
        return UUID.randomUUID().toString();
    }
}
