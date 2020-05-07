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

package com.djalel.android.zaid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {
    WarathaInput mInput;
    CheckBox alabCB;
    CheckBox alomCB;
    CheckBox aljadCB;
    CheckBox aljadahLiAbCB;
    CheckBox aljadahLiOmCB;
    CheckBox azawjCB;
    EditText tarikaET;
    EditText azawjatET;
    EditText sonsET;
    EditText daughtersET;
    EditText sonsOfSonsET;
    EditText daughtersOfSonsET;
    EditText maternalBrothersET;
    EditText maternalSistersET;
    EditText fullBrothersET;
    EditText fullSistersET;
    EditText paternalBrothersET;
    EditText paternalSistersET;
    EditText sonsOfFullBrothersET;
    EditText sonsOfPaternalBrothersET;
    EditText fullUnclesET;
    EditText paternalUnclesET;
    EditText sonsOfFullUnclesET;
    EditText sonsOfPaternalUnclesET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ZaidApplication app = (ZaidApplication) this.getApplication();
        mInput = app.getWarathaInput();

        // update layout
        updateLayout();
    }

    public void updateLayout(){
        switch (mInput.get_madhab()) {
            case MALIKI: ((RadioButton)findViewById(R.id.radioButtonMaliki)).setChecked(true); break;
            case CHAFI3I: ((RadioButton)findViewById(R.id.radioButtonChafi3i)).setChecked(true); break;
            case HAMBALI: ((RadioButton)findViewById(R.id.radioButtonHambali)).setChecked(true); break;
            case HANAFI: default: ((RadioButton)findViewById(R.id.radioButtonHanafi)).setChecked(true); break;
        }

        ((RadioButton)findViewById(mInput.is_male() ?
                R.id.radioButtonMale : R.id.radioButtonFemale)).setChecked(true);

        alabCB = findViewById(R.id.fatherCheckBox);
        alabCB.setChecked(mInput.alab());
        alomCB = findViewById(R.id.motherCheckBox);
        alomCB.setChecked(mInput.alom());
        aljadCB = findViewById(R.id.aljadCheckBox);
        aljadCB.setChecked(mInput.aljad());
        aljadahLiAbCB = findViewById(R.id.aljadahLiAbCheckBox);
        aljadahLiAbCB.setChecked(mInput.aljadah_li_ab());
        aljadahLiOmCB = findViewById(R.id.aljadahLiOmCheckBox);
        aljadahLiOmCB.setChecked(mInput.aljadah_li_om());
        azawjCB = findViewById(R.id.azawjCheckBox);
        azawjCB.setChecked(mInput.zawj());

        tarikaET = findViewById(R.id.tarikaEditText);
        tarikaET.setText(String.valueOf(mInput.getTarika()));

        azawjatET = findViewById(R.id.zawjatEditText);
        azawjatET.setFilters(new InputFilter[]{ new InputFilterMinMax("0", "4")});

        if (mInput.is_male()) {
            mInput.set_zawj(false);
            azawjCB.setEnabled(false);
            azawjCB.setChecked(false);

            findViewById(R.id.zawjatTextView).setEnabled(true);
            azawjatET.setEnabled(true);
            azawjatET.setText(String.valueOf(mInput.get_azawjat()));
        }
        else {
            mInput.set_azawjat(0);
            azawjatET.setText("0");
            azawjatET.setEnabled(false);
            findViewById(R.id.zawjatTextView).setEnabled(false);

            azawjCB.setEnabled(true);
            azawjCB.setChecked(mInput.zawj());
        }

        sonsET = findViewById(R.id.sonsEditText);
        sonsET.setText(String.valueOf(mInput.get_alabna()));

        daughtersET = findViewById(R.id.daughtersEditText);
        daughtersET.setText(String.valueOf(mInput.get_albanat()));

        sonsOfSonsET = findViewById(R.id.sonsOfSonsEditText);
        sonsOfSonsET.setText(String.valueOf(mInput.get_abna_alabna()));

        daughtersOfSonsET = findViewById(R.id.daughtersOfSonsEditText);
        daughtersOfSonsET.setText(String.valueOf(mInput.get_banat_alabna()));

        maternalBrothersET = findViewById(R.id.maternalBrothersEditText);
        maternalBrothersET.setText(String.valueOf(mInput.get_alikhwa_li_om()));

        maternalSistersET = findViewById(R.id.maternalSistersEditText);
        maternalSistersET.setText(String.valueOf(mInput.get_alakhawat_li_om()));

        fullBrothersET = findViewById(R.id.fullBrothersEditText);
        fullBrothersET.setText(String.valueOf(mInput.get_alikhwa_alashika()));

        fullSistersET = findViewById(R.id.fullSistersEditText);
        fullSistersET.setText(String.valueOf(mInput.get_alakhawat_ashakikat()));

        paternalBrothersET = findViewById(R.id.paternalBrothersEditText);
        paternalBrothersET.setText(String.valueOf(mInput.get_alikhwa_li_ab()));

        paternalSistersET = findViewById(R.id.paternalSistersEditText);
        paternalSistersET.setText(String.valueOf(mInput.get_alakhawat_li_ab()));

        sonsOfFullBrothersET = findViewById(R.id.sonsOfFullBrothersEditText);
        sonsOfFullBrothersET.setText(String.valueOf(mInput.get_abna_alikhwa_alashika()));

        sonsOfPaternalBrothersET = findViewById(R.id.sonsOfPaternalBrothersEditText);
        sonsOfPaternalBrothersET.setText(String.valueOf(mInput.get_abna_alikhwa_li_ab()));

        fullUnclesET = findViewById(R.id.fullUnclesEditText);
        fullUnclesET.setText(String.valueOf(mInput.get_ala3mam_alashika()));

        paternalUnclesET = findViewById(R.id.paternalUnclesEditText);
        paternalUnclesET.setText(String.valueOf(mInput.get_ala3mam_li_ab()));

        sonsOfFullUnclesET = findViewById(R.id.sonsOfFullUnclesEditText);
        sonsOfFullUnclesET.setText(String.valueOf(mInput.get_abna_ala3mam_alashika()));

        sonsOfPaternalUnclesET = findViewById(R.id.sonsOfPaternalUnclesEditText);
        sonsOfPaternalUnclesET.setText(String.valueOf(mInput.get_abna_ala3mam_li_ab()));
    }

    public void onMadhabButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.radioButtonMaliki:
                if (checked)
                    mInput.set_madhab(Madhab.MALIKI);
                break;

            case R.id.radioButtonChafi3i:
                if (checked)
                    mInput.set_madhab(Madhab.CHAFI3I);
                break;

            case R.id.radioButtonHanafi:
                if (checked)
                    mInput.set_madhab(Madhab.HANAFI);
                break;

            case R.id.radioButtonHambali:
                if (checked)
                    mInput.set_madhab(Madhab.HAMBALI);
                break;
        }
    }

    public void onGenderButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.radioButtonMale:
                if (checked)
                    mInput.set_male(true);
                    mInput.set_zawj(false);
                break;

            case R.id.radioButtonFemale:
                if (checked)
                    mInput.set_male(false);
                    mInput.set_azawjat(0);
                break;
        }
        updateLayout();
    }

    public void onHalClicked(View view) {
        mInput.set_alab(alabCB.isChecked());
        mInput.set_alom(alomCB.isChecked());
        mInput.set_aljad(aljadCB.isChecked());
        mInput.set_aljadah_li_ab(aljadahLiAbCB.isChecked());
        mInput.set_aljadah_li_om(aljadahLiOmCB.isChecked());
        mInput.set_zawj(azawjCB.isChecked());
        mInput.setTarika(Double.parseDouble(tarikaET.getText().toString()));
        mInput.set_azawjat(Integer.parseInt(azawjatET.getText().toString()));
        mInput.set_alabna(Integer.parseInt(sonsET.getText().toString()));
        mInput.set_albanat(Integer.parseInt(daughtersET.getText().toString()));
        mInput.set_abna_alabna(Integer.parseInt(sonsOfSonsET.getText().toString()));
        mInput.set_banat_alabna(Integer.parseInt(daughtersOfSonsET.getText().toString()));
        mInput.set_alikhwa_li_om(Integer.parseInt(maternalBrothersET.getText().toString()));
        mInput.set_alakhawat_li_om(Integer.parseInt(maternalSistersET.getText().toString()));
        mInput.set_alikhwa_alashika(Integer.parseInt(fullBrothersET.getText().toString()));
        mInput.set_alakhawat_ashakikat(Integer.parseInt(fullSistersET.getText().toString()));
        mInput.set_alikhwa_li_ab(Integer.parseInt(paternalBrothersET.getText().toString()));
        mInput.set_alakhawat_li_ab(Integer.parseInt(paternalSistersET.getText().toString()));
        mInput.set_abna_alikhwa_alashika(Integer.parseInt(sonsOfFullBrothersET.getText().toString()));
        mInput.set_abna_alikhwa_li_ab(Integer.parseInt(sonsOfPaternalBrothersET.getText().toString()));
        mInput.set_ala3mam_alashika(Integer.parseInt(fullUnclesET.getText().toString()));
        mInput.set_ala3mam_li_ab(Integer.parseInt(paternalUnclesET.getText().toString()));
        mInput.set_abna_ala3mam_alashika(Integer.parseInt(sonsOfFullUnclesET.getText().toString()));
        mInput.set_abna_ala3mam_li_ab(Integer.parseInt(sonsOfPaternalUnclesET.getText().toString()));

        // start next activity
        Intent intent = new Intent(this, ActivityResult.class);
        startActivity(intent);
    }
}
