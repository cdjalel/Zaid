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

public class Activity3 extends AppCompatActivity {

    WarathaInput mInput;
    NumberPicker maternalBrothersNP;
    NumberPicker maternalSistersNP;
    NumberPicker fullBrothersNP;
    NumberPicker fullSistersNP;
    NumberPicker paternalBrothersNP;
    NumberPicker paternalSistersNP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        ZaidApplication app = (ZaidApplication) this.getApplication();
        mInput = app.getWarathaInput();

        maternalBrothersNP = findViewById(R.id.maternalBrothersNumberPicker);
        maternalBrothersNP.setMinValue(0);
        maternalBrothersNP.setMaxValue(50);
        maternalBrothersNP.setValue(mInput.get_alikhwa_li_om());
        maternalBrothersNP.setWrapSelectorWheel(false);
        maternalBrothersNP.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

		    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                mInput.set_alikhwa_li_om(newVal);
		    }
	    });

        maternalSistersNP = findViewById(R.id.maternalSistersNumberPicker);
        maternalSistersNP.setMinValue(0);
        maternalSistersNP.setMaxValue(50);
        maternalSistersNP.setValue(mInput.get_alakhawat_li_om());
        maternalSistersNP.setWrapSelectorWheel(false);
        maternalSistersNP.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                mInput.set_alakhawat_li_om(newVal);
            }
        });

        fullBrothersNP = findViewById(R.id.fullBrothersNumberPicker);
        fullBrothersNP.setMinValue(0);
        fullBrothersNP.setMaxValue(50);
        fullBrothersNP.setValue(mInput.get_alikhwa_alashika());
        fullBrothersNP.setWrapSelectorWheel(false);
        fullBrothersNP.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                mInput.set_alikhwa_alashika(newVal);
            }
        });

        fullSistersNP = findViewById(R.id.fullSistersNumberPicker);
        fullSistersNP.setMinValue(0);
        fullSistersNP.setMaxValue(50);
        fullSistersNP.setValue(mInput.get_alakhawat_ashakikat());
        fullSistersNP.setWrapSelectorWheel(false);
        fullSistersNP.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                mInput.set_alakhawat_ashakikat(newVal);
            }
        });

        paternalBrothersNP = findViewById(R.id.paternalBrothersNumberPicker);
        paternalBrothersNP.setMinValue(0);
        paternalBrothersNP.setMaxValue(50);
        paternalBrothersNP.setValue(mInput.get_alikhwa_li_ab());
        paternalBrothersNP.setWrapSelectorWheel(false);
        paternalBrothersNP.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                mInput.set_alikhwa_li_ab(newVal);
            }
        });

        paternalSistersNP = findViewById(R.id.paternalSistersNumberPicker);
        paternalSistersNP.setMinValue(0);
        paternalSistersNP.setMaxValue(50);
        paternalSistersNP.setValue(mInput.get_alakhawat_li_ab());
        paternalSistersNP.setWrapSelectorWheel(false);
        paternalSistersNP.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                mInput.set_alakhawat_li_ab(newVal);
            }
        });
    }


        public void onNextClicked(View view) {
        // start next activity
        Intent intent = new Intent(this, Activity4.class);
        startActivity(intent);
    }
}
