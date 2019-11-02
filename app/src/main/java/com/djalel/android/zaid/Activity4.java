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

    ZaidApplication mApp;
    NumberPicker fullBrothersNP;
    NumberPicker fullSistersNP;
    NumberPicker paternalBrothersNP;
    NumberPicker paternalSistersNP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);

        mApp = (ZaidApplication) this.getApplication();

        fullBrothersNP = (NumberPicker) findViewById(R.id.fullBrothersNumberPicker);
        fullBrothersNP.setValue(0);
        fullBrothersNP.setMinValue(0);
        fullBrothersNP.setMaxValue(50);
        fullBrothersNP.setWrapSelectorWheel(false);
        fullBrothersNP.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                mApp.set_alikhwa_alashika(newVal);
            }
        });

        fullSistersNP = (NumberPicker) findViewById(R.id.fullSistersNumberPicker);
        fullSistersNP.setValue(0);
        fullSistersNP.setMinValue(0);
        fullSistersNP.setMaxValue(50);
        fullSistersNP.setWrapSelectorWheel(false);
        fullSistersNP.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

		    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                mApp.set_alakhawat_ashakikat(newVal);
		    }
	    });

        paternalBrothersNP = (NumberPicker) findViewById(R.id.paternalBrothersNumberPicker);
        paternalBrothersNP.setValue(0);
        paternalBrothersNP.setMinValue(0);
        paternalBrothersNP.setMaxValue(50);
        paternalBrothersNP.setWrapSelectorWheel(false);
        paternalBrothersNP.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                mApp.set_alikhwa_li_ab(newVal);
            }
        });

        paternalSistersNP = (NumberPicker) findViewById(R.id.paternalSistersNumberPicker);
        paternalSistersNP.setValue(0);
        paternalSistersNP.setMinValue(0);
        paternalSistersNP.setMaxValue(50);
        paternalSistersNP.setWrapSelectorWheel(false);
        paternalSistersNP.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                mApp.set_alakhawat_li_ab(newVal);
            }
        });

    }

    public void onNextClicked(View view) {
        // start next activity
        Intent intent = new Intent(this, Activity5.class);
        startActivity(intent);
    }
}
