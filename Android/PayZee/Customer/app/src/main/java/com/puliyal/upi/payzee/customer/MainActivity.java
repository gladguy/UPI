package com.puliyal.upi.payzee.customer;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public class MainActivity extends Activity {

    static final String ACTION_SCAN = "com.google.zxing.client.android.SCAN";

    String amt,OrderID,PVA,qry_payeeName,ipaddress,appname;

    public static final String ARG_ITEM_ID = "item_id";

    EditText amount;
    EditText payeeName;
    EditText payeeVirtualAddress;
    EditText WebOrderID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amount              = (EditText) findViewById(R.id.amount);
        payeeVirtualAddress = (EditText) findViewById(R.id.payeeVirtualAddress);
        WebOrderID          = (EditText) findViewById(R.id.WebOrderID);
        payeeName           = (EditText) findViewById(R.id.payeeName);

    }

    public void sendMessage(View view) {
        Log.v("Waheed", "TEST");
        int orderAmount = 0;
        String DEEPLINKING_URL_BASE = "upi://pay";
        StringBuilder urlBuilder = new StringBuilder();

        String webOrderID = WebOrderID.getText().toString();
        String pva        = payeeVirtualAddress.getText().toString();
        String payee      = payeeName.getText().toString();
        try
        {
            orderAmount = Integer.parseInt(amount.getText().toString());   //new Random().nextInt(9999);
        }
        catch(Exception e)
        {
            orderAmount = 786;
            Log.v("Waheed","786" + amount.getText());
        }
        int orderId = 100001 + new Random().nextInt(9999);


        //urlBuilder.append("upi://pay").append("?").append("amount").append("=").append("100").append("&").append("ORDERID").append("=").append(orderId).append("&").append("credit_flag").append("=").append("0").append("&").append("appname").append("=").append("MGSAPP").append("&").append("am").append("=").append("100").append("&").append("tn").append("=").append(orderId).append("&").append("tr").append("=").append("Test for UPI").append("&").append("ti").append("=").append(orderId).append("&").append("appid").append("=").append("1116").append("&").append("cu").append("=").append("RS").append("&").append("gcode").append("=").append("123466454").append("&").append("location").append("=").append("Mumbai,Maharashtra").append("&").append("ip").append("=").append("154fer53urn").append("&").append("os").append("=").append("android").append("&").append("payerAcAddressType").append("=").append("49234903292323").append("&").append("capability").append("=").append("453453d4f5343434df354").append("&").append("payeeName").append("=").append("Waheed Rahuman").append("&").append("payeeType").append("=").append("PERSON").append("&").append("PVA").append("=").append("rahuman@mapp").append("&").append("PayerVA").append("=").append("waheed");
        //urlBuilder.append("upi://pay").append("?").append("amount").append("=").append("100").append("&").append("ORDERID").append("=").append(orderId).append("&").append("credit_flag").append("=").append("1").append("&").append("appname").append("=").append("MGSAPP").append("&payerName=Mohamed Rehan").append("&").append("am").append("=").append("100").append("&").append("tn").append("=").append(orderId).append("&").append("tr").append("=").append("Test for UPI").append("&").append("ti").append("=").append(orderId).append("&").append("appid").append("=").append("1116").append("&").append("cu").append("=").append("RS").append("&").append("gcode").append("=").append("123466454").append("&").append("location").append("=").append("Mumbai,Maharashtra").append("&").append("ip").append("=").append("154fer53urn").append("&").append("os").append("=").append("android").append("&").append("payerAcAddressType").append("=").append("49234903292323").append("&").append("capability").append("=").append("453453d4f5343434df354").append("&").append("payeeName").append("=").append("Waheed Rahuman").append("&").append("payeeType").append("=").append("PERSON").append("&").append("PVA").append("=").append("rahuman@mapp").append("&").append("PayerVA").append("=").append("Rahuman");
        //urlBuilder.append("upi://pay?").append("amount=").append("100").append("&").append("ORDERID=").append(orderId).append("&").append("credit_flag=1").append("&").append("appname=MGSAPP").append("&payerName=Mohamed Rehan").append("&").append("am=").append("999").append("&").append("tn=").append(orderId).append("&").append("tr=").append("Test for UPI").append("&").append("ti=").append(orderId).append("&").append("appid=").append("1116").append("&").append("cu").append("=").append("RS").append("&").append("gcode").append("=").append("123466454").append("&").append("location").append("=").append("Mumbai,Maharashtra").append("&").append("ip=127.0.0.1").append("&").append("os=android").append("&").append("payerAcAddressType").append("=").append("Current").append("&").append("capability").append("=").append("453453d4f5343434df354").append("&").append("payeeName=Waheed Rahuman").append("&").append("payeeType=").append("PERSON").append("&").append("PVA=").append("rahuman@mapp").append("&").append("PayerVA=").append("Rahuman");

        urlBuilder.append("upi://pay?amount=100&").append("PVA=").append(pva).append("&").append("ORDERID=").append(orderId).append("&PayerVA=PayZee").append("&").append("am=").append(orderAmount).append("&payeeName=").append(payee).append("&ti=").append(orderId).append("&tn=").append(orderId).append("&appid=1116&appname=").append("MGSAPP").append("&capability=453453d4f5343434df354&credit_flag=1&cu=RS&gcode=123466454&ip=").append(ipaddress).append("&location=Mumbai,Maharashtra&os=android&payeeType=PERSON&payerAcAddressType=Current&tr=Team-PayZee");
        urlBuilder.append("upi://pay?").append("amount=").append(orderAmount).append("&").append("ORDERID=").append(orderId).append("&").append("credit_flag=1").append("&").append("appname=MGSAPP").append("&payerName=Mohamed Rehan").append("&").append("am=").append(orderAmount).append("&").append("tn=").append(orderId).append("&").append("tr=").append("Team PayZee").append("&").append("ti=").append(orderId).append("&").append("appid=").append("1116").append("&").append("cu").append("=").append("RS").append("&").append("gcode").append("=").append("123466454").append("&").append("location").append("=").append("Mumbai,Maharashtra").append("&").append("ip=").append(ipaddress).append("&").append("os=android").append("&").append("payerAcAddressType").append("=").append("Current").append("&").append("capability").append("=").append("453453d4f5343434df354").append("&").append("payeeName=").append(payeeName.getText()).append("&").append("payeeType=").append("PERSON").append("&").append("PVA=").append(pva).append("&").append("PayerVA=").append(payeeVirtualAddress.getText());
        Log.v("Waheed","urlBuilder real" + urlBuilder.toString());

        /*
        PVA=waheed@mapp&payeeName=Amazon Corp&appname=FlipKart&am=100


        String amount,OrderID,PVA,payeeName,ipaddress;
        Uri uri = "PVA=amazon@mapp&payeeName=Amazon Corp&appname=Amazon&am=100&OrderID=10011;
        amount = uri.getQueryParameter("am");
        OrderID = uri.getQueryParameter("OrderID");
        PVA = uri.getQueryParameter("PVA");
        payeeName = uri.getQueryParameter("payeeName");
        ipaddress=uri.getQueryParameter("ip");



        * */

        String deepLinkUrl = urlBuilder.toString();
        Log.v("waheed",deepLinkUrl);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(deepLinkUrl));
        Intent chooser = Intent.createChooser(intent, "Pay With");
        if (intent.resolveActivity(MainActivity.this.getPackageManager()) != null) {
            startActivityForResult(chooser,0);
        }

    }

    public void testMessage(View view) {
     Log.v("Waheed", "TEST");
        int orderAmount = 0;
        String DEEPLINKING_URL_BASE = "upi://pay";
        StringBuilder urlBuilder = new StringBuilder();

        String webOrderID = WebOrderID.getText().toString();
        String pva        = payeeVirtualAddress.getText().toString();
        String payee      = payeeName.getText().toString();
        try
        {
            orderAmount = Integer.parseInt(amount.getText().toString());   //new Random().nextInt(9999);
        }
        catch(Exception e)
        {
            orderAmount = 786;
            Log.v("Waheed","786" + amount.getText());
        }

        int orderId = 100001 + new Random().nextInt(9999);
        //urlBuilder.append("upi://pay").append("?").append("amount").append("=").append("100").append("&").append("ORDERID").append("=").append(orderId).append("&").append("credit_flag").append("=").append("0").append("&").append("appname").append("=").append("MGSAPP").append("&").append("am").append("=").append("100").append("&").append("tn").append("=").append(orderId).append("&").append("tr").append("=").append("Test for UPI").append("&").append("ti").append("=").append(orderId).append("&").append("appid").append("=").append("1116").append("&").append("cu").append("=").append("RS").append("&").append("gcode").append("=").append("123466454").append("&").append("location").append("=").append("Mumbai,Maharashtra").append("&").append("ip").append("=").append("154fer53urn").append("&").append("os").append("=").append("android").append("&").append("payerAcAddressType").append("=").append("49234903292323").append("&").append("capability").append("=").append("453453d4f5343434df354").append("&").append("payeeName").append("=").append("Waheed Rahuman").append("&").append("payeeType").append("=").append("PERSON").append("&").append("PVA").append("=").append("rahuman@mapp").append("&").append("PayerVA").append("=").append("waheed");
        //urlBuilder.append("upi://pay").append("?").append("amount").append("=").append("100").append("&").append("ORDERID").append("=").append(orderId).append("&").append("credit_flag").append("=").append("1").append("&").append("appname").append("=").append("MGSAPP").append("&payerName=Mohamed Rehan").append("&").append("am").append("=").append("100").append("&").append("tn").append("=").append(orderId).append("&").append("tr").append("=").append("Test for UPI").append("&").append("ti").append("=").append(orderId).append("&").append("appid").append("=").append("1116").append("&").append("cu").append("=").append("RS").append("&").append("gcode").append("=").append("123466454").append("&").append("location").append("=").append("Mumbai,Maharashtra").append("&").append("ip").append("=").append("154fer53urn").append("&").append("os").append("=").append("android").append("&").append("payerAcAddressType").append("=").append("49234903292323").append("&").append("capability").append("=").append("453453d4f5343434df354").append("&").append("payeeName").append("=").append("Waheed Rahuman").append("&").append("payeeType").append("=").append("PERSON").append("&").append("PVA").append("=").append("rahuman@mapp").append("&").append("PayerVA").append("=").append("Rahuman");
        urlBuilder.append("upi://pay?").append("amount=").append("100").append("&").append("ORDERID=").append(orderId).append("&").append("credit_flag=1").append("&").append("appname=MGSAPP").append("&payerName=Mohamed Rehan").append("&").append("am=").append("999").append("&").append("tn=").append(orderId).append("&").append("tr=").append("Test for UPI").append("&").append("ti=").append(orderId).append("&").append("appid=").append("1116").append("&").append("cu").append("=").append("RS").append("&").append("gcode").append("=").append("123466454").append("&").append("location").append("=").append("Mumbai,Maharashtra").append("&").append("ip=127.0.0.1").append("&").append("os=android").append("&").append("payerAcAddressType").append("=").append("Current").append("&").append("capability").append("=").append("453453d4f5343434df354").append("&").append("payeeName=Waheed Rahuman").append("&").append("payeeType=").append("PERSON").append("&").append("PVA=").append("rahuman@mapp").append("&").append("PayerVA=").append("Rahuman");

        //urlBuilder.append("upi://pay?").append("PVA=").append(pva).append("&PayerVA=PayZee").append("&").append("am=").append(orderAmount).append("&payeeName=").append(payee).append("&ti=").append(webOrderID).append("&tn=").append(webOrderID).append("&appid=1116&appname=").append(appname).append("&capability=453453d4f5343434df354&credit_flag=1&cu=RS&gcode=123466454&ip=").append(ipaddress).append("&location=Mumbai,Maharashtra&os=android&payeeType=PERSON&payerAcAddressType=Current&tr=Team-PayZee");
        Log.v("Waheed","urlBuilder test" + urlBuilder.toString());

        /*
        PVA=waheed@mapp&payeeName=Amazon Corp&appname=FlipKart&am=100


        String amount,OrderID,PVA,payeeName,ipaddress;
        Uri uri = "PVA=amazon@mapp&payeeName=Amazon Corp&appname=Amazon&am=100&OrderID=10011;
        amount = uri.getQueryParameter("am");
        OrderID = uri.getQueryParameter("OrderID");
        PVA = uri.getQueryParameter("PVA");
        payeeName = uri.getQueryParameter("payeeName");
        ipaddress=uri.getQueryParameter("ip");



        * */

        String deepLinkUrl = urlBuilder.toString();
        Log.v("waheed",deepLinkUrl);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(deepLinkUrl));
        Intent chooser = Intent.createChooser(intent, "Pay With");
        if (intent.resolveActivity(MainActivity.this.getPackageManager()) != null) {
            startActivityForResult(chooser,0);
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void scanQR(View v) {
        try {
            Intent intent = new Intent(ACTION_SCAN);
            intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
            startActivityForResult(intent, 0);
        } catch (ActivityNotFoundException anfe) {
            showDialog(this, "No Scanner Found", "Download a scanner code activity?", "Yes", "No").show();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

        Log.v("Waheed", "FragmentResult" + requestCode + "and" + resultCode);
        if (requestCode == 0) {
            if (resultCode == Activity.RESULT_OK) {

                String contents = intent.getStringExtra("SCAN_RESULT");
                String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
                CharSequence toastText = "Content:" + contents + " Format:" + format;
                Toast toast = Toast.makeText(getApplicationContext(), toastText, Toast.LENGTH_LONG);
                toast.show();

                Log.v("Waheed", "URL " + contents);

                Uri uri = Uri.parse("payzee://result?"+contents);
                amt             = uri.getQueryParameter("am");
                OrderID         = uri.getQueryParameter("OrderID");
                PVA             = uri.getQueryParameter("PVA");
                qry_payeeName   = uri.getQueryParameter("payeeName");
                ipaddress       = uri.getQueryParameter("ip");
                appname         = uri.getQueryParameter("appname");

                amount.setText(amt);
                payeeName.setText(qry_payeeName);
                payeeVirtualAddress.setText(PVA);
                WebOrderID.setText(OrderID);

                Log.v("Waheed", "Order ID = " + OrderID + " Amount = " + amt + " PVA = " + PVA + " Payee Name =" + payeeName + "IP Address="+ipaddress);

            }
        }
    }

    public static Map<String, String> splitQuery(URL url) throws UnsupportedEncodingException {
        Map<String, String> query_pairs = new LinkedHashMap<String, String>();
        String query = url.getQuery();
        String[] pairs = query.split("&");
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            query_pairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"), URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
        }
        return query_pairs;
    }

    private static AlertDialog showDialog(final Activity act, CharSequence title, CharSequence message, CharSequence buttonYes, CharSequence buttonNo) {
        AlertDialog.Builder downloadDialog = new AlertDialog.Builder(act);
        downloadDialog.setTitle(title);
        downloadDialog.setMessage(message);
        downloadDialog.setPositiveButton(buttonYes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                Uri uri = Uri.parse("market://search?q=pname:" + "com.google.zxing.client.android");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                try {
                    act.startActivity(intent);
                } catch (ActivityNotFoundException anfe) {

                }
            }
        });
        downloadDialog.setNegativeButton(buttonNo, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        return downloadDialog.show();
    }

}
