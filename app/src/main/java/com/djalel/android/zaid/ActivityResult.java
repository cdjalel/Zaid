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

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ActivityResult extends AppCompatActivity {

    private LinearLayout mResultTableLayout;
    private TextView mResultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        mResultTextView = findViewById(R.id.resultTextView);
        mResultTableLayout = findViewById(R.id.resultTableLayout);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //reinitialisation
        mResultTableLayout.removeAllViews();

        ZaidApplication app = (ZaidApplication) this.getApplication();
        mResultTextView.setText(app.hissabMawarith());
        createTable(app);
    }

    public void onRestartClicked(View view) {
        ZaidApplication app = (ZaidApplication) this.getApplication();
        app.getWarathaInput().resetWarathahInput();

        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.startActivity(intent);
    }

    public void onChangeMassalaClicked(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        this.startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void createTable(ZaidApplication app) {
        if (app.getMassala().getMawarith().isEmpty()) { return; }

        double tarika = app.getWarathaInput().getTarika();
        Massala massala = app.getMassala();
        TableRow row = new TableRow(this);
        TextView tvAsl;
        if (massala.getAwl() != 0) {
            //row of awl
            row.addView(createHeadTextView(String.valueOf(massala.getAwl()), false),0);
            row.addView(createHeadTextView("", false),0);
            row.addView(createHeadTextView("", true),0);
            mResultTableLayout.addView(row);

            //row of asl
            row = new TableRow(this);
            tvAsl = createHeadAwlTextView(massala.getAsl());
        } else {
            tvAsl = createHeadTextView(String.valueOf(massala.getAsl()), false);
        }
        row.addView(tvAsl, 0);
        row.addView(createHeadTextView(String.valueOf(massala.getMissah()), false),0);
        row.addView(createHeadTextView(String.format("%.2f", tarika), true),0);
        mResultTableLayout.addView(row);

        boolean jadahFirst = true;
        boolean shirkatTa3seebFirst = true;
        boolean waladAlomFirst = true;
        boolean last_row = false;
        int i = 0;
        for (Mirath m : massala.getMawarith()) {
            if (++i == massala.getMawarith().size()) { last_row = true; }

            StringBuilder partStr = new StringBuilder();
            partStr.append(String.format("%.2f", m.getNassib() * tarika / massala.getMissah()));
            if (m.getNbr() > 1) { partStr.append("(*").append(m.getNbr()).append(")");}

            row = new TableRow(this);
            row.addView(createCellTextView(partStr, true, last_row));
            row.addView(createCellTextView(m.getNassibFardi(), false, last_row));
            if (m.isJadah() && m.isShirka()) {
                if (jadahFirst) {
                    row.addView(createCellTextView(m.getNassibMojmal() + " ↓", false, last_row));
                    jadahFirst = false;
                } else {
                    row.addView(createCellTextView("", false, last_row, true));
                }
            } else if (m.isWaladAlom() && massala.isJinsayAwladAlom()) {
                if (waladAlomFirst) {
                    row.addView(createCellTextView(m.getNassibMojmal() + " ↓", false, last_row));
                    waladAlomFirst = false;
                } else {
                    row.addView(createCellTextView("", false, last_row, true));
                }
            } else if (m.isTa3seeb() && massala.isShirkaTa3seeb()) {
                if (shirkatTa3seebFirst) {
                    row.addView(createCellTextView(m.getNassibMojmal() + " ↓", false, last_row));
                    shirkatTa3seebFirst = false;
                } else {
                    row.addView(createCellTextView("", false, last_row, true));
                }
            } else {
                row.addView(createCellTextView(m.getNassibMojmal(), false, last_row));
            }
            row.addView(createCellTextView(m.getIsm(), false, last_row));
            row.addView(createCellTextView(m.getHokom(), false, last_row));
            mResultTableLayout.addView(row);
        }
    }

    private TextView createHeadTextView(CharSequence txt, boolean last_column) {
        TextView head = prepareTextView();

        head.setBackgroundResource((last_column)? R.drawable.last_head_borders : R.drawable.head_borders);
        head.setText(txt);
        return head;
    }

    private TextView createHeadAwlTextView(int awl) {
        TextView head = prepareTextView();

        head.setBackgroundResource((awl < 10) ? R.drawable.head_awl1 : R.drawable.head_awl2);
        head.setText(String.valueOf(awl));
        return head;
    }

    private TextView createCellTextView(CharSequence txt, boolean last_column, boolean last_row) {
        TextView cell = prepareTextView();

        if (last_row) {
            cell.setBackgroundResource((last_column)? R.drawable.last_row_and_column_borders : R.drawable.last_row_borders);
        } else {
            cell.setBackgroundResource((last_column)? R.drawable.last_column_borders : R.drawable.cell_borders);
        }
        cell.setText(txt);
        return cell;
    }

    private TextView createCellTextView(CharSequence txt, boolean last_column, boolean last_row, boolean is_shirka) {
        TextView cell = prepareTextView();

        if (last_row) {
            cell.setBackgroundResource((is_shirka)? R.drawable.last_row_shirka_borders : R.drawable.last_row_borders);
        } else {
            cell.setBackgroundResource((is_shirka)? R.drawable.shirka_cell_borders : R.drawable.cell_borders);
        }
        cell.setText(txt);
        return cell;
    }

    private TextView prepareTextView() {
        TextView tv = new TextView(this);

        tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.font_size));
        tv.setTextColor(Color.BLACK);
        tv.setGravity(Gravity.RIGHT);
        tv.setTextDirection(View.TEXT_DIRECTION_RTL);
        return tv;
    }
}
