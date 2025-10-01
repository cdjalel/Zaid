/*
 * Copyright © 2019-2020 Djalel Chefrour and the Zaid project contributors.
 * This file is part of Zaid project.
 *
 * Zaid is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Zaid is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 *  along with Zaid.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.djalel.android.zaid.activities;

import android.os.Bundle;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.djalel.android.zaid.R;
import com.djalel.android.zaid.ZaidApplication;
import com.djalel.libjfarayid.Madhab;
import com.djalel.libjfarayid.WarathahInput;

public class InputFragment extends Fragment {
    private WarathahInput mInput;
    private CheckBox alabCB;
    private CheckBox alomCB;
    private CheckBox aljadCB;
    private CheckBox aljadahLiAbCB;
    private CheckBox aljadahLiOmCB;
    private CheckBox azawjCB;
    private EditText tarikaET;
    private TextView azawjatTV;
    private EditText azawjatET;
    private EditText sonsET;
    private EditText daughtersET;
    private EditText sonsOfSonsET;
    private EditText daughtersOfSonsET;
    private EditText maternalBrothersET;
    private EditText maternalSistersET;
    private EditText fullBrothersET;
    private EditText fullSistersET;
    private EditText paternalBrothersET;
    private EditText paternalSistersET;
    private EditText sonsOfFullBrothersET;
    private EditText sonsOfPaternalBrothersET;
    private EditText fullUnclesET;
    private EditText paternalUnclesET;
    private EditText sonsOfFullUnclesET;
    private EditText sonsOfPaternalUnclesET;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_input, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ZaidApplication app = ZaidApplication.getApplication();
        mInput = app.getWarathaInput();

        // update layout
        switch (mInput.get_madhab()) {
            case MALIKI: ((RadioButton)view.findViewById(R.id.radioButtonMaliki)).setChecked(true); break;
            case CHAFI3I: ((RadioButton)view.findViewById(R.id.radioButtonChafi3i)).setChecked(true); break;
            case HAMBALI: ((RadioButton)view.findViewById(R.id.radioButtonHambali)).setChecked(true); break;
            case HANAFI: default: ((RadioButton)view.findViewById(R.id.radioButtonHanafi)).setChecked(true); break;
        }

        ((RadioButton)view.findViewById(mInput.is_male() ? R.id.radioButtonMale : R.id.radioButtonFemale)).setChecked(true);

        alabCB = view.findViewById(R.id.fatherCheckBox);
        alabCB.setChecked(mInput.alab());
        alomCB = view.findViewById(R.id.motherCheckBox);
        alomCB.setChecked(mInput.alom());
        aljadCB = view.findViewById(R.id.aljadCheckBox);
        aljadCB.setChecked(mInput.aljad());
        aljadahLiAbCB = view.findViewById(R.id.aljadahLiAbCheckBox);
        aljadahLiAbCB.setChecked(mInput.aljadah_li_ab());
        aljadahLiOmCB = view.findViewById(R.id.aljadahLiOmCheckBox);
        aljadahLiOmCB.setChecked(mInput.aljadah_li_om());
        azawjCB = view.findViewById(R.id.azawjCheckBox);
        azawjCB.setChecked(mInput.zawj());

        tarikaET = view.findViewById(R.id.tarikaEditText);
        tarikaET.setText(String.valueOf(mInput.getTarika()));

        azawjatTV = view.findViewById(R.id.zawjatTextView);
        azawjatET = view.findViewById(R.id.zawjatEditText);
        azawjatET.setFilters(new InputFilter[]{ new InputFilterMinMax("0", "4")});

        updateAzwaj();

        sonsET = view.findViewById(R.id.sonsEditText);
        sonsET.setText(String.valueOf(mInput.get_alabna()));

        daughtersET = view.findViewById(R.id.daughtersEditText);
        daughtersET.setText(String.valueOf(mInput.get_albanat()));

        sonsOfSonsET = view.findViewById(R.id.sonsOfSonsEditText);
        sonsOfSonsET.setText(String.valueOf(mInput.get_abna_alabna()));

        daughtersOfSonsET = view.findViewById(R.id.daughtersOfSonsEditText);
        daughtersOfSonsET.setText(String.valueOf(mInput.get_banat_alabna()));

        maternalBrothersET = view.findViewById(R.id.maternalBrothersEditText);
        maternalBrothersET.setText(String.valueOf(mInput.get_alikhwa_li_om()));

        maternalSistersET = view.findViewById(R.id.maternalSistersEditText);
        maternalSistersET.setText(String.valueOf(mInput.get_alakhawat_li_om()));

        fullBrothersET = view.findViewById(R.id.fullBrothersEditText);
        fullBrothersET.setText(String.valueOf(mInput.get_alikhwa_alashika()));

        fullSistersET = view.findViewById(R.id.fullSistersEditText);
        fullSistersET.setText(String.valueOf(mInput.get_alakhawat_ashakikat()));

        paternalBrothersET = view.findViewById(R.id.paternalBrothersEditText);
        paternalBrothersET.setText(String.valueOf(mInput.get_alikhwa_li_ab()));

        paternalSistersET = view.findViewById(R.id.paternalSistersEditText);
        paternalSistersET.setText(String.valueOf(mInput.get_alakhawat_li_ab()));

        sonsOfFullBrothersET = view.findViewById(R.id.sonsOfFullBrothersEditText);
        sonsOfFullBrothersET.setText(String.valueOf(mInput.get_abna_alikhwa_alashika()));

        sonsOfPaternalBrothersET = view.findViewById(R.id.sonsOfPaternalBrothersEditText);
        sonsOfPaternalBrothersET.setText(String.valueOf(mInput.get_abna_alikhwa_li_ab()));

        fullUnclesET = view.findViewById(R.id.fullUnclesEditText);
        fullUnclesET.setText(String.valueOf(mInput.get_ala3mam_alashika()));

        paternalUnclesET = view.findViewById(R.id.paternalUnclesEditText);
        paternalUnclesET.setText(String.valueOf(mInput.get_ala3mam_li_ab()));

        sonsOfFullUnclesET = view.findViewById(R.id.sonsOfFullUnclesEditText);
        sonsOfFullUnclesET.setText(String.valueOf(mInput.get_abna_ala3mam_alashika()));

        sonsOfPaternalUnclesET = view.findViewById(R.id.sonsOfPaternalUnclesEditText);
        sonsOfPaternalUnclesET.setText(String.valueOf(mInput.get_abna_ala3mam_li_ab()));

        // register listeners
        ((RadioGroup)view.findViewById(R.id.radioGroupMadhab)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioButtonMaliki) {
                    mInput.set_madhab(Madhab.MALIKI);
                } else if (checkedId == R.id.radioButtonChafi3i) {
                    mInput.set_madhab(Madhab.CHAFI3I);
                } else if (checkedId == R.id.radioButtonHanafi) {
                    mInput.set_madhab(Madhab.HANAFI);
                } else if (checkedId == R.id.radioButtonHambali) {
                    mInput.set_madhab(Madhab.HAMBALI);
                }
            }
        });

        ((RadioGroup)view.findViewById(R.id.radioGroupGender)).setOnCheckedChangeListener( new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioButtonMale) {
                    mInput.set_male(true);
                } else if (checkedId == R.id.radioButtonFemale) {
                    mInput.set_male(false);
                }
                updateAzwaj();
            }
        });

        view.findViewById(R.id.button_hal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mInput.set_alab(alabCB.isChecked());
                mInput.set_alom(alomCB.isChecked());
                mInput.set_aljad(aljadCB.isChecked());
                mInput.set_aljadah_li_ab(aljadahLiAbCB.isChecked());
                mInput.set_aljadah_li_om(aljadahLiOmCB.isChecked());
                mInput.set_zawj(azawjCB.isChecked());
                mInput.setTarika(parseDouble(tarikaET.getText().toString()));
                mInput.set_azawjat(parseInt(azawjatET.getText().toString()));
                mInput.set_alabna(parseInt(sonsET.getText().toString()));
                mInput.set_albanat(parseInt(daughtersET.getText().toString()));
                mInput.set_abna_alabna(parseInt(sonsOfSonsET.getText().toString()));
                mInput.set_banat_alabna(parseInt(daughtersOfSonsET.getText().toString()));
                mInput.set_alikhwa_li_om(parseInt(maternalBrothersET.getText().toString()));
                mInput.set_alakhawat_li_om(parseInt(maternalSistersET.getText().toString()));
                mInput.set_alikhwa_alashika(parseInt(fullBrothersET.getText().toString()));
                mInput.set_alakhawat_ashakikat(parseInt(fullSistersET.getText().toString()));
                mInput.set_alikhwa_li_ab(parseInt(paternalBrothersET.getText().toString()));
                mInput.set_alakhawat_li_ab(parseInt(paternalSistersET.getText().toString()));
                mInput.set_abna_alikhwa_alashika(parseInt(sonsOfFullBrothersET.getText().toString()));
                mInput.set_abna_alikhwa_li_ab(parseInt(sonsOfPaternalBrothersET.getText().toString()));
                mInput.set_ala3mam_alashika(parseInt(fullUnclesET.getText().toString()));
                mInput.set_ala3mam_li_ab(parseInt(paternalUnclesET.getText().toString()));
                mInput.set_abna_ala3mam_alashika(parseInt(sonsOfFullUnclesET.getText().toString()));
                mInput.set_abna_ala3mam_li_ab(parseInt(sonsOfPaternalUnclesET.getText().toString()));

                NavHostFragment.findNavController(InputFragment.this)
                        .navigate(R.id.action_InputFragment_to_OutputFragment);
            }
        });
    }

    private void updateAzwaj() {
        if (mInput.is_male()) {
            mInput.set_zawj(false);
            azawjCB.setEnabled(false);
            azawjCB.setChecked(false);

            azawjatTV.setEnabled(true);
            azawjatET.setEnabled(true);
            azawjatET.setText(String.valueOf(mInput.get_azawjat()));
        }
        else {
            mInput.set_azawjat(0);
            azawjatTV.setEnabled(false);
            azawjatET.setText("0");
            azawjatET.setEnabled(false);

            azawjCB.setEnabled(true);
            azawjCB.setChecked(mInput.zawj());
        }
    }

    private int parseInt(String value) {
        if (value == null || value.isEmpty()) {
            return 0;
        }
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private double parseDouble(String value) {
        if (value == null || value.isEmpty()) {
            return 0.0;
        }
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
}
