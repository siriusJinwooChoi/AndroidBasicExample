package com.example.edu.bt_classic_client;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Set;

public class ScanActivity extends AppCompatActivity {
    ListView listview;
    ArrayAdapter<String> adt;
    BluetoothAdapter btAdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        btAdt = BluetoothAdapter.getDefaultAdapter();
        adt = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        listview = (ListView)findViewById(R.id.list);
        listview.setAdapter(adt);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //이미 item이 나왔다는 것이므로, btAdt는 찾는것을 cancel해줌.
                btAdt.cancelDiscovery();
                String item = adt.getItem(position);
                String name = item.substring(0, item.indexOf(","));       //item으로부터 추출하는 부분 (","를 기준으로)
                String address = item.substring(item.indexOf(",")+1);
                Intent intent = new Intent();
                intent.putExtra("name", name);
                intent.putExtra("address", address);
                //결과값을 저장해줌.(OK, intent)
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        //이미 접속했던 애들에 대한 정보를 가지고 있으므로 그대로 데려올 수 있음.(가지고 있던 애들의 정보만 불러옴)
        Set<BluetoothDevice> devices = btAdt.getBondedDevices();
        for(BluetoothDevice device : devices) {
            adt.add(device.getName() + "," + device.getAddress());
        }

        //만약 이미 discovering 하고있으면 말고, 안하고 있으면 시작함.
        if(btAdt.isDiscovering()) {
            btAdt.cancelDiscovery();
        }

        registerReceiver(scanner, new IntentFilter(BluetoothDevice.ACTION_FOUND));
        registerReceiver(scanner, new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED));

        //실제 블루투스 장치가 어떤 장치가있는지 청취를 하러 다님.
        btAdt.startDiscovery();
    }

    BroadcastReceiver scanner = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            //device를 찾았구나 라는 것을 알게됨.
            if(action.equals(BluetoothDevice.ACTION_FOUND)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                if(device.getBondState() != BluetoothDevice.BOND_BONDED) {
                    adt.add(device.getName() + ","+device.getAddress());
                }
            } else if(action.equals(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)) {      //BLUETOOTH장치가 발견된 것이 없을 때,
                Toast.makeText(context, "scan finished.", Toast.LENGTH_LONG).show();
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(scanner);
    }
}
