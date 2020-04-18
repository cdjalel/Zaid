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
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    WarathaInput mInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ZaidApplication app = (ZaidApplication) this.getApplication();
        mInput = app.getWarathaInput();

        // update layout
        switch (mInput.get_madhab()) {
            case MALIKI: ((RadioButton)findViewById(R.id.radioButtonMaliki)).setChecked(true); break;
            case CHAFI3I: ((RadioButton)findViewById(R.id.radioButtonChafi3i)).setChecked(true); break;
            case HAMBALI: ((RadioButton)findViewById(R.id.radioButtonHambali)).setChecked(true); break;
            case HANAFI: default: ((RadioButton)findViewById(R.id.radioButtonHanafi)).setChecked(true); break;
        }

        ((RadioButton)findViewById(mInput.is_male() ?
                R.id.radioButtonMale : R.id.radioButtonFemale)).setChecked(true);

        ((RadioButton)findViewById(mInput.alab() ?
                R.id.radioButtonFatherYes : R.id.radioButtonFatherNo)).setChecked(true);

        ((RadioButton)findViewById(mInput.alom() ?
                R.id.radioButtonMotherYes : R.id.radioButtonMotherNo)).setChecked(true);

        ((RadioButton)findViewById(mInput.aljad() ?
                R.id.radioButtonGrandFatherYes : R.id.radioButtonGrandFatherNo)).setChecked(true);

        ((RadioButton)findViewById(mInput.aljadah_li_ab() ?
                R.id.radioButtonPaternalGrandMotherYes : R.id.radioButtonPaternalGrandMotherNo)).setChecked(true);

        ((RadioButton)findViewById(mInput.aljadah_li_om() ?
                R.id.radioButtonMaternalGrandMotherYes : R.id.radioButtonMaternalGrandMotherNo)).setChecked(true);
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
    }

    public void onFatherButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.radioButtonFatherYes:
                if (checked)
                    mInput.set_alab(true);
                break;

            case R.id.radioButtonFatherNo:
                if (checked)
                    mInput.set_alab(false);
                break;
        }
    }

    public void onMotherButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.radioButtonMotherYes:
                if (checked)
                    mInput.set_alom(true);
                break;

            case R.id.radioButtonMotherNo:
                if (checked)
                    mInput.set_alom(false);
                break;
        }
    }

    public void onGrandFatherButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.radioButtonGrandFatherYes:
                if (checked)
                    mInput.set_aljad(true);
                break;

            case R.id.radioButtonGrandFatherNo:
                if (checked)
                    mInput.set_aljad(false);
                break;
        }
    }

    public void onPaternalGrandMotherButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.radioButtonPaternalGrandMotherYes:
                if (checked)
                    mInput.set_aljadah_li_ab(true);
                break;

            case R.id.radioButtonPaternalGrandMotherNo:
                if (checked)
                    mInput.set_aljadah_li_ab(false);
                break;
        }
    }

    public void onMaternalGrandMotherButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.radioButtonMaternalGrandMotherYes:
                if (checked)
                    mInput.set_aljadah_li_om(true);
                break;

            case R.id.radioButtonMaternalGrandMotherNo:
                if (checked)
                    mInput.set_aljadah_li_om(false);
                break;
        }
    }

    public void onNextClicked(View view) {
        // start next activity
        Intent intent = new Intent(this, Activity2.class);
        startActivity(intent);
    }
}
