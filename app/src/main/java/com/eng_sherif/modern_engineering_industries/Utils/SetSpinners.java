package com.eng_sherif.modern_engineering_industries.Utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.View;
import android.widget.AbsSpinner;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import com.eng_sherif.modern_engineering_industries.R;
import java.util.ArrayList;
import java.util.List;

public class SetSpinners {

    private Context context;
    private String firstChoose;

    public SetSpinners(Context context) {

        this.context = context;
    }

//    private void setSpinnerFirstItems(Spinner spinnerChooseFirst) {
//
//        List<String> first = new ArrayList<>();
//
//        first.add(context.getString(R.string.filing));
//        first.add(context.getString(R.string.closingCapping));
//        first.add(context.getString(R.string.packing));
//        first.add(context.getString(R.string.handlingProcess));
//
//        ArrayAdapter adapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, first);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerChooseFirst.setAdapter(adapter);
//
//        spinnerChooseFirst.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//
//                firstChoose = adapterView.getItemAtPosition(i).toString();
//
//                Toast.makeText(context, "Select: " + firstChoose, Toast.LENGTH_SHORT).show();
//
//                if (firstChoose == context.getString(R.string.filing)) {
//
//                    setSpinnerFilingItems(spinnerChooseSecond);
//                }
//                else if (firstChoose == context.getString(R.string.closingCapping)) {
//
//                    setSpinnerClosingCappingItems(spinnerChooseSecond);
//                }
//                else if (firstChoose == context.getString(R.string.packing)) {
//
//                    setSpinnerPackingItems(spinnerChooseSecond);
//                }
//                else if (firstChoose == context.getString(R.string.handlingProcess)) {
//
//                    setSpinnerHandlingProcessItems(spinnerChooseSecond);
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
//    }
//
//    private void setSpinnerFilingItems(Spinner spinnerChooseSecond) {
//
//        spinnerChooseSecond.setVisibility(View.VISIBLE);
//
//        List<String> filing = new ArrayList<>();
//
//        filing.add(context.getString(R.string.liquids));
//        filing.add(context.getString(R.string.powder));
//        filing.add(context.getString(R.string.granular));
//
//        ArrayAdapter adapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, filing);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerChooseSecond.setAdapter(adapter);
//
//        spinnerChooseSecond.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//
//                String item = adapterView.getItemAtPosition(i).toString();
//
//                Toast.makeText(context, "Select: " + item, Toast.LENGTH_SHORT).show();
//
//                switch (item) {
//
//                    case "2-1":
//
//                        setUpSendUsNowChat(btnSendChat);
//                        break;
//
//                    case "2-2":
//
//                        setUpSendUsNowChat(btnSendChat);
//                        break;
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
//    }
//
//    private void setSpinnerClosingCappingItems(Spinner spinnerChooseSecond) {
//
//        spinnerChooseSecond.setVisibility(View.VISIBLE);
//
//        List<String> second = new ArrayList<>();
//
//        second.add(context.getString(R.string.preaCap));
//        second.add(context.getString(R.string.scrawCap));
//        second.add(context.getString(R.string.pilferProdCap));
//
//        ArrayAdapter adapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, second);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerChooseSecond.setAdapter(adapter);
//
//        spinnerChooseSecond.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//
//                String item = adapterView.getItemAtPosition(i).toString();
//
//                Toast.makeText(context, "Select: " + item, Toast.LENGTH_SHORT).show();
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
//    }
//
//    private void setSpinnerPackingItems(Spinner spinnerChooseSecond) {
//
//        spinnerChooseSecond.setVisibility(View.VISIBLE);
//
//        List<String> second = new ArrayList<>();
//
//        second.add(context.getString(R.string.preaCap));
//        second.add(context.getString(R.string.scrawCap));
//        second.add(context.getString(R.string.pilferProdCap));
//
//        ArrayAdapter adapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, second);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerChooseSecond.setAdapter(adapter);
//
//        spinnerChooseSecond.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//
//                String item = adapterView.getItemAtPosition(i).toString();
//
//                Toast.makeText(context, "Select: " + item, Toast.LENGTH_SHORT).show();
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
//    }
//
//    private void setSpinnerHandlingProcessItems(Spinner spinnerChooseSecond) {
//
//        spinnerChooseSecond.setVisibility(View.VISIBLE);
//
//        List<String> second = new ArrayList<>();
//
//        second.add(context.getString(R.string.turnTable));
//        second.add(context.getString(R.string.conveyors));
//        second.add(context.getString(R.string.bolts));
//        second.add(context.getString(R.string.elevator));
//
//        ArrayAdapter adapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, second);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerChooseSecond.setAdapter(adapter);
//
//        spinnerChooseSecond.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//
//                String item = adapterView.getItemAtPosition(i).toString();
//
//                Toast.makeText(context, "Select: " + item, Toast.LENGTH_SHORT).show();
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
//    }
//
//    private void setUpSendUsNowChat(Button btnSendChat) {
//
//        btnSendChat.setEnabled(true);
//
////        Intent intent = new Intent(requireContext(), ChatActivity.class);
////        startActivity(intent);
//
//        btnSendChat.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                PackageManager pm = context.getPackageManager();
//
//                Intent waIntent = new Intent(Intent.ACTION_VIEW);
//
//                String text = "Hello,\nI choose this machine,\nMy details is, " + firstChoose;
//
//                waIntent.setData(Uri.parse("http://api.whatsapp.com/send?phone=+201276326918&&text=" + text));
//
//                if (waIntent.resolveActivity(pm) != null) {
//
//                    Toast.makeText(
//                            context,
//                            "Please install whatsapp first.",
//                            Toast.LENGTH_SHORT)
//                            .show();
//                    return;
//                }
//
//                // Starting Whatsapp
//                context.startActivity(waIntent);
//            }
//        });
//    }
}
