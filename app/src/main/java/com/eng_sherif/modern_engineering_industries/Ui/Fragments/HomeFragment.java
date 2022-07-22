package com.eng_sherif.modern_engineering_industries.Ui.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.eng_sherif.modern_engineering_industries.R;
import com.eng_sherif.modern_engineering_industries.Ui.Settings.ChatActivity;
import com.eng_sherif.modern_engineering_industries.Ui.SignUser.SendOTPActivity;
import com.eng_sherif.modern_engineering_industries.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    private Context mContext;

    private String firstChoose, filing, closingCapping, packing, handlingProcess;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(getLayoutInflater());

        mContext = getContext();

        setSpinnerFirstItems();

        return binding.getRoot();
    }

    private void setSpinnerFirstItems() {

        List<String> first = new ArrayList<>();

        first.add(getString(R.string.selectAnItem));
        first.add(getString(R.string.filing));
        first.add(getString(R.string.closingCapping));
        first.add(getString(R.string.packing));
        first.add(getString(R.string.handlingProcess));

        ArrayAdapter adapter = new ArrayAdapter(mContext, android.R.layout.simple_list_item_1, first);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerChooseFirst.setAdapter(adapter);

        binding.spinnerChooseFirst.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                firstChoose = adapterView.getItemAtPosition(i).toString();

//                Toast.makeText(mContext, "Select: " + firstChoose, Toast.LENGTH_SHORT).show();

                if (firstChoose == getString(R.string.selectAnItem)) {

                    Toast.makeText(mContext, getString(R.string.selectAnItemPlease), Toast.LENGTH_SHORT).show();
                }
                else if (firstChoose == getString(R.string.filing)) {

                    setSpinnerFilingItems();
                }
                else if (firstChoose == getString(R.string.closingCapping)) {

                    setSpinnerClosingCappingItems();
                }
                else if (firstChoose == getString(R.string.packing)) {

                    setSpinnerPackingItems();
                }
                else if (firstChoose == getString(R.string.handlingProcess)) {

                    setSpinnerHandlingProcessItems();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setSpinnerFilingItems() {

        binding.spinnerChooseSecond.setVisibility(View.VISIBLE);

        List<String> filings = new ArrayList<>();

        filings.add(getString(R.string.selectNextItem));
        filings.add(getString(R.string.liquids));
        filings.add(getString(R.string.powder));
        filings.add(getString(R.string.granular));

        ArrayAdapter adapter = new ArrayAdapter(mContext, android.R.layout.simple_list_item_1, filings);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerChooseSecond.setAdapter(adapter);

        binding.spinnerChooseSecond.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                filing = adapterView.getItemAtPosition(i).toString();

//                Toast.makeText(mContext, "Select: " + filing, Toast.LENGTH_SHORT).show();

                if (filing == getString(R.string.selectNextItem)) {

                    Toast.makeText(mContext, getString(R.string.selectAnItemPlease), Toast.LENGTH_SHORT).show();
                }
                else if (filing == getString(R.string.liquids)) {

//                    setSpinnerFilingLiquidsItems();
                    setUpSendUsNowChat(getString(R.string.filing) +
                            " and " + getString(R.string.liquids));
                }
                else if (filing == getString(R.string.powder)) {

                    setUpSendUsNowChat(getString(R.string.filing) +
                            " and " + getString(R.string.powder));
                }
                else if (filing == getString(R.string.granular)) {

                    setUpSendUsNowChat(getString(R.string.filing) +
                            " and " + getString(R.string.granular));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setSpinnerFilingLiquidsItems() {

        binding.spinnerChooseThird.setVisibility(View.VISIBLE);

        List<String> third = new ArrayList<>();

        third.add(getString(R.string.halfAutomatic));
        third.add(getString(R.string.automatic));

//        third.add(getString(R.string.plasticBottle));
//        third.add(getString(R.string.glassBottle));
//        third.add(getString(R.string.cans));
//        third.add(getString(R.string.pauch));

        ArrayAdapter adapter = new ArrayAdapter(mContext, android.R.layout.simple_list_item_1, third);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerChooseThird.setAdapter(adapter);

        binding.spinnerChooseThird.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String item = adapterView.getItemAtPosition(i).toString();

                Toast.makeText(mContext, "Select: " + item, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setSpinnerClosingCappingItems() {

        binding.spinnerChooseSecond.setVisibility(View.VISIBLE);

        List<String> second = new ArrayList<>();

        second.add(getString(R.string.selectNextItem));
        second.add(getString(R.string.preaCap));
        second.add(getString(R.string.scrawCap));
        second.add(getString(R.string.pilferProdCap));

        ArrayAdapter adapter = new ArrayAdapter(mContext, android.R.layout.simple_list_item_1, second);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerChooseSecond.setAdapter(adapter);

        binding.spinnerChooseSecond.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String item = adapterView.getItemAtPosition(i).toString();

//                Toast.makeText(mContext, "Select: " + item, Toast.LENGTH_SHORT).show();

                if (item == getString(R.string.selectNextItem)) {

                    Toast.makeText(mContext, getString(R.string.selectAnItemPlease), Toast.LENGTH_SHORT).show();
                }
                else if (item == getString(R.string.preaCap)) {

                    setUpSendUsNowChat(getString(R.string.closingCapping) +
                            " and " + getString(R.string.preaCap));
                }
                else if (item == getString(R.string.scrawCap)) {

                    setUpSendUsNowChat(getString(R.string.closingCapping) +
                            " and " + getString(R.string.scrawCap));
                }
                else if (item == getString(R.string.pilferProdCap)) {

                    setUpSendUsNowChat(getString(R.string.closingCapping) +
                            " and " + getString(R.string.pilferProdCap));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setSpinnerPackingItems() {

        binding.spinnerChooseSecond.setVisibility(View.VISIBLE);

        List<String> second = new ArrayList<>();

        second.add(getString(R.string.selectNextItem));
        second.add(getString(R.string.shrink));
        second.add(getString(R.string.envelopeSide));
        second.add(getString(R.string.wrapping));
        second.add(getString(R.string.cartooning));

        ArrayAdapter adapter = new ArrayAdapter(mContext, android.R.layout.simple_list_item_1, second);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerChooseSecond.setAdapter(adapter);

        binding.spinnerChooseSecond.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String item = adapterView.getItemAtPosition(i).toString();

//                Toast.makeText(mContext, "Select: " + item, Toast.LENGTH_SHORT).show();

                if (item == getString(R.string.selectNextItem)) {

                    Toast.makeText(mContext, getString(R.string.selectAnItemPlease), Toast.LENGTH_SHORT).show();
                }
                else if (item == getString(R.string.shrink)) {

                    setUpSendUsNowChat(getString(R.string.packing) +
                            " and " + getString(R.string.shrink));
                }
                else if (item == getString(R.string.envelopeSide)) {

                    setUpSendUsNowChat(getString(R.string.packing) +
                            " and " + getString(R.string.envelopeSide));
                }
                else if (item == getString(R.string.wrapping)) {

                    setUpSendUsNowChat(getString(R.string.packing) +
                            " and " + getString(R.string.wrapping));
                }
                else if (item == getString(R.string.cartooning)) {

                    setUpSendUsNowChat(getString(R.string.packing) +
                            " and " + getString(R.string.cartooning));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setSpinnerHandlingProcessItems() {

        binding.spinnerChooseSecond.setVisibility(View.VISIBLE);

        List<String> second = new ArrayList<>();

        second.add(getString(R.string.selectNextItem));
        second.add(getString(R.string.turnTable));
        second.add(getString(R.string.conveyors));
        second.add(getString(R.string.bolts));
        second.add(getString(R.string.elevator));

        ArrayAdapter adapter = new ArrayAdapter(mContext, android.R.layout.simple_list_item_1, second);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerChooseSecond.setAdapter(adapter);

        binding.spinnerChooseSecond.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String item = adapterView.getItemAtPosition(i).toString();

//                Toast.makeText(mContext, "Select: " + item, Toast.LENGTH_SHORT).show();

                if (item == getString(R.string.selectNextItem)) {

                    Toast.makeText(mContext, getString(R.string.selectAnItemPlease), Toast.LENGTH_SHORT).show();
                }
                else if (item == getString(R.string.turnTable)) {

                    setUpSendUsNowChat(getString(R.string.handlingProcess) +
                            " and " + getString(R.string.turnTable));
                }
                else if (item == getString(R.string.conveyors)) {

                    setUpSendUsNowChat(getString(R.string.handlingProcess) +
                            " and " + getString(R.string.conveyors));
                }
                else if (item == getString(R.string.bolts)) {

                    setUpSendUsNowChat(getString(R.string.handlingProcess) +
                            " and " + getString(R.string.bolts));
                }
                else if (item == getString(R.string.elevator)) {

                    setUpSendUsNowChat(getString(R.string.handlingProcess) +
                            " and " + getString(R.string.elevator));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setUpSendUsNowChat(String items) {

        binding.btnSendChat.setEnabled(true);

//        Intent intent = new Intent(requireContext(), ChatActivity.class);
//        startActivity(intent);

        binding.btnSendChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PackageManager pm = getActivity().getPackageManager();

                Intent waIntent = new Intent(Intent.ACTION_VIEW);

                String text = getString(R.string.hello) + "\n" +
                        getString(R.string.iChooseThisMachine) +
                        "\n" +
                        getString(R.string.myDetailsIs) + " " + items;

                waIntent.setData(Uri.parse("http://api.whatsapp.com/send?phone=+201276326918&&text=" + text));

                if (waIntent.resolveActivity(pm) != null) {

                    Toast.makeText(
                            getActivity(),
                            getString(R.string.pleaseInstallWhatsapp),
                            Toast.LENGTH_SHORT)
                            .show();
                    return;
                }

                // Starting Whatsapp
                startActivity(waIntent);
            }
        });
    }
}