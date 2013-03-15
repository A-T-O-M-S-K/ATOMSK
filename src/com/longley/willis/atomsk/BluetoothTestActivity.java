package com.longley.willis.atomsk;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class BluetoothTestActivity extends Activity {
 
 private static final int REQUEST_ENABLE_BT = 1;
 
    ListView listDevicesFound;
 Button btnScanDevice;
 TextView stateBluetooth;
 BluetoothAdapter bluetoothAdapter;
 DatabaseHelper dbHelper = new DatabaseHelper(this);
 SQLiteDatabase dbWriter;
 
 ArrayAdapter<String> btArrayAdapter;
 
 /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth_test);
        dbWriter = openOrCreateDatabase( "Temp.db"        , SQLiteDatabase.CREATE_IF_NECESSARY        , null          );
        
        btnScanDevice = (Button)findViewById(R.id.scandevice);
        
        stateBluetooth = (TextView)findViewById(R.id.bluetoothstate);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        
        listDevicesFound = (ListView)findViewById(R.id.devicesfound);
        btArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        listDevicesFound.setAdapter(btArrayAdapter);
        
        CheckBlueToothState();
        
        btnScanDevice.setOnClickListener(btnScanDeviceOnClickListener);

        registerReceiver(ActionFoundReceiver, 
          new IntentFilter(BluetoothDevice.ACTION_FOUND));
    }
    
    @Override
 protected void onDestroy() {
  // TODO Auto-generated method stub
  super.onDestroy();
  unregisterReceiver(ActionFoundReceiver);
 }

 private void CheckBlueToothState(){
     if (bluetoothAdapter == null){
         stateBluetooth.setText("Bluetooth NOT supported");
        }else{
         if (bluetoothAdapter.isEnabled()){
          if(bluetoothAdapter.isDiscovering()){
           stateBluetooth.setText("Bluetooth is currently in device discovery process.");
          }else{
           stateBluetooth.setText("Bluetooth is Enabled.");
           btnScanDevice.setEnabled(true);
          }
         }else{
          stateBluetooth.setText("Bluetooth is NOT Enabled!");
          Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
             startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
         }
        }
    }
    
    private Button.OnClickListener btnScanDeviceOnClickListener
    = new Button.OnClickListener(){

  @Override
  public void onClick(View arg0) {
   // TODO Auto-generated method stub
   btArrayAdapter.clear();
   bluetoothAdapter.startDiscovery();
  }};

 @Override
 protected void onActivityResult(int requestCode, int resultCode, Intent data) {
  // TODO Auto-generated method stub
  if(requestCode == REQUEST_ENABLE_BT){
   CheckBlueToothState();
  }
 }
    
 private final BroadcastReceiver ActionFoundReceiver = new BroadcastReceiver(){

  @Override
  public void onReceive(Context context, Intent intent) {
   // TODO Auto-generated method stub
   String action = intent.getAction();
   if(BluetoothDevice.ACTION_FOUND.equals(action)) {
             BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
             btArrayAdapter.add(device.getName() + "\n" + device.getAddress());
             try {
                 String sql =
                     "INSERT or replace INTO EnvironData (Latitude, Longitude, DeviceName, DeviceMAC , DeviceRSSI) VALUES('Not Supported', 'Not Supported', '" +device.getName()+ "', '" + device.getAddress() + "', 'Not Supported' )" ;       
                         dbWriter.execSQL(sql);
             } catch (Exception e) {
                  
             }
             btArrayAdapter.notifyDataSetChanged();
         }
  }};
    
}