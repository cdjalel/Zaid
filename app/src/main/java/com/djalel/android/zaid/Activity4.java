/*
 * Copyright © 2019 Djalel Chefrour
 * This file is part of Zaid.
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
import android.view.View;
import android.widget.NumberPicker;


public class Activity4 extends AppCompatActivity {
    WarathaInput mInput;
    NumberPicker sonsOfFullBrothersNP;
    NumberPicker sonsOfPaternalBrothersNP;
    NumberPicker fullUnclesNP;
    NumberPicker paternalUnclesNP;
    NumberPicker sonsOfFullUnclesNP;
    NumberPicker sonsOfPaternalUnclesNP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);

        ZaidApplication app = (ZaidApplication) this.getApplication();
        mInput = app.getWarathaInput();

        sonsOfFullBrothersNP = findViewById(R.id.sonsOfFullBrothersNumberPicker);
        sonsOfFullBrothersNP.setMinValue(0);
        sonsOfFullBrothersNP.setMaxValue(50);
        sonsOfFullBrothersNP.setValue(mInput.get_abna_alikhwa_alashika());
        sonsOfFullBrothersNP.setWrapSelectorWheel(false);
        sonsOfFullBrothersNP.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                mInput.set_abna_alikhwa_alashika(newVal);
            }
        });

        sonsOfPaternalBrothersNP = findViewById(R.id.sonsOfPaternalBrothersNumberPicker);
        sonsOfPaternalBrothersNP.setMinValue(0);
        sonsOfPaternalBrothersNP.setMaxValue(50);
        sonsOfPaternalBrothersNP.setValue(mInput.get_abna_alikhwa_li_ab());
        sonsOfPaternalBrothersNP.setWrapSelectorWheel(false);
        sonsOfPaternalBrothersNP.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                mInput.set_abna_alikhwa_li_ab(newVal);
            }
        });

        fullUnclesNP = findViewById(R.id.fullUnclesNumberPicker);
        fullUnclesNP.setMinValue(0);
        fullUnclesNP.setMaxValue(50);
        fullUnclesNP.setValue(mInput.get_ala3mam_alashika());
        fullUnclesNP.setWrapSelectorWheel(false);
        fullUnclesNP.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                mInput.set_ala3mam_alashika(newVal);
            }
        });

        paternalUnclesNP = findViewById(R.id.paternalUnclesNumberPicker);
        paternalUnclesNP.setMinValue(0);
        paternalUnclesNP.setMaxValue(50);
        paternalUnclesNP.setValue(mInput.get_ala3mam_li_ab());
        paternalUnclesNP.setWrapSelectorWheel(false);
        paternalUnclesNP.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                mInput.set_ala3mam_li_ab(newVal);
            }
        });

        sonsOfFullUnclesNP = findViewById(R.id.sonsOfFullUnclesNumberPicker);
        sonsOfFullUnclesNP.setMinValue(0);
        sonsOfFullUnclesNP.setMaxValue(50);
        sonsOfFullUnclesNP.setValue(mInput.get_abna_ala3mam_alashika());
        sonsOfFullUnclesNP.setWrapSelectorWheel(false);
        sonsOfFullUnclesNP.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                mInput.set_abna_ala3mam_alashika(newVal);
            }
        });

        sonsOfPaternalUnclesNP = findViewById(R.id.sonsOfPaternalUnclesNumberPicker);
        sonsOfPaternalUnclesNP.setMinValue(0);
        sonsOfPaternalUnclesNP.setMaxValue(50);
        sonsOfPaternalUnclesNP.setValue(mInput.get_abna_ala3mam_li_ab());
        sonsOfPaternalUnclesNP.setWrapSelectorWheel(false);
        sonsOfPaternalUnclesNP.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                mInput.set_abna_ala3mam_li_ab(newVal);
            }
        });
    }

    public void onNextClicked(View view) {
        // start next activity
        Intent intent = new Intent(this, Activity5.class);
        startActivity(intent);
    }
}
