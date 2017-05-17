package fancy.hyypaysdk.pay.wxpay;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import org.json.JSONException;
import org.json.JSONObject;

import fancy.hyypaysdk.pay.Constant;
import fancy.hyypaysdk.pay.PayCallBack;

/**
 * Created by Hyy on 2016/10/10.
 */
public class WXPayUtil {
    private String jsonString = "{\"appid\":\"wxad8a7420a00f9c20\",\"noncestr\":\"b2b4480ad7f84146bfee0b9d40ba87c4\",\"package\":\"Sign=WXPay\",\"partnerid\":\"1311953701\",\"prepayid\":\"wx201610101738355347a1b2920036101518\",\"sign\":\"25A31DFFE56845FC1291FBC0302FBC9B\",\"timestamp\":1476092313}";

    private Activity activity;

    private PayCallBack payCallBack;

    private static WXPayUtil instance;

    private WXPayUtil() {
    }

    public static WXPayUtil getInstance() {
        if (instance == null) {
            instance = new WXPayUtil();
        }
        return instance;
    }

    public void wxPay(Activity activity, String jsonString, PayCallBack payCallBack) {
        this.jsonString = jsonString;
        this.activity = activity;
        this.payCallBack = payCallBack;
        if (pay()) {
            registerBoradcastReceiver();
        } else {
            dismissValue();
        }
    }

    private void dismissValue() {
        activity = null;
        payCallBack = null;
    }

    private boolean pay() {
        IWXAPI iwxapi = WXAPIFactory.createWXAPI(activity, Constant.APP_ID, false);
        iwxapi.registerApp(Constant.APP_ID);

        PayReq req = new PayReq();
        JSONObject object;
        try {
            object = new JSONObject(jsonString);

            req.appId = object.getString("appid");

            req.partnerId = object.getString("partnerid");

            req.prepayId = object.getString("prepayid");

            req.packageValue = object.getString("package");

            req.nonceStr = object.getString("noncestr");

            req.timeStamp = object.getString("timestamp");

            req.sign = object.getString("sign");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
        if (iwxapi.isWXAppInstalled()) {
            iwxapi.sendReq(req);
            return true;
        } else {
//            ToastUtil.show("请先安装微信");
            return false;
        }

    }


    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Constant.WXPAY_CALLBACK_NAME)) {
                String result = intent.getStringExtra(Constant.WXPAY_RESULT);
                if (result.equals("0")) {
                    payCallBack.paySuccess();
                } else if (result.equals("-1")) {
                    payCallBack.payFaild();
                } else if (result.equals("-2")) {
                    payCallBack.payCancle();
                }
                activity.unregisterReceiver(mBroadcastReceiver);
                dismissValue();
            }
        }
    };

    public void registerBoradcastReceiver() {
        IntentFilter myIntentFilter = new IntentFilter();
        myIntentFilter.addAction(Constant.WXPAY_CALLBACK_NAME);
        //广播
        activity.registerReceiver(mBroadcastReceiver, myIntentFilter);
    }

}
