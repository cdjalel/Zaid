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

public class Activity6 extends AppCompatActivity {

    ZaidApplication mApp;
    NumberPicker fullUnclesNP;
    NumberPicker paternalUnclesNP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_6);

        mApp = (ZaidApplication) this.getApplication();

        fullUnclesNP = (NumberPicker) findViewById(R.id.fullUnclesNumberPicker);
        fullUnclesNP.setValue(0);
        fullUnclesNP.setMinValue(0);
        fullUnclesNP.setMaxValue(50);
        fullUnclesNP.setWrapSelectorWheel(false);
        fullUnclesNP.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                mApp.set_ala3mam_alashika(newVal);
            }
        });

        paternalUnclesNP = (NumberPicker) findViewById(R.id.paternalUnclesNumberPicker);
        paternalUnclesNP.setValue(0);
        paternalUnclesNP.setMinValue(0);
        paternalUnclesNP.setMaxValue(50);
        paternalUnclesNP.setWrapSelectorWheel(false);
        paternalUnclesNP.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                mApp.set_ala3mam_li_ab(newVal);
            }
        });

    }

    public void onNextClicked(View view) {
        // start next activity
        Intent intent = new Intent(this, Activity7.class);
        startActivity(intent);
    }
}
