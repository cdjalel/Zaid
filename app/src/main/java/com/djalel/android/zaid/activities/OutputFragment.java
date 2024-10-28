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

import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.djalel.android.zaid.R;
import com.djalel.android.zaid.ZaidApplication;
import com.djalel.libjfarayid.Mas2ala;
import com.djalel.libjfarayid.Mirath;

import java.util.ArrayList;
import java.util.Locale;

public class OutputFragment extends Fragment {

    private TableLayout mResultTableLayout;
    private TextView mResultTextView;

    enum CELL {EMPTY, NORMAL, SHIRKA}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_output, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mResultTextView = view.findViewById(R.id.resultTextView);
        mResultTableLayout = view.findViewById(R.id.resultTableLayout);

        view.findViewById(R.id.button_restart).setOnClickListener(view1 -> {
            ZaidApplication app = ZaidApplication.getApplication();
            app.getWarathaInput().resetWarathahInput();

            NavHostFragment.findNavController(OutputFragment.this)
                    .navigate(R.id.action_OutputFragment_to_InputFragment);
        });

        view.findViewById(R.id.button_change).setOnClickListener(view12 -> NavHostFragment.findNavController(OutputFragment.this)
                .navigate(R.id.action_OutputFragment_to_InputFragment));
    }

    @Override
    public void onResume() {
        super.onResume();

        //reinitialisation
        mResultTableLayout.removeAllViews();

        ZaidApplication app = ZaidApplication.getApplication();
        mResultTextView.setText(app.hissabMawarith());
        createTable(app);
    }

    private void createTable(ZaidApplication app) {
        Mas2ala mas2ala = app.getMassala();
        if (mas2ala.getMawarith().isEmpty()) { return; }

        // The table head and its number of columns depend on Naw3 Mas2ala
        switch (mas2ala.getNaw3()) {
            case NAW3_RAD_3ALA_WAHED:
                createTableWithRad(app);
                break;

            case NAW3_RAD_3ALA_MUTAJANISEEN:
                createTableWithRadWaTashih(app);
                break;

            case NAW3_RAD_3ALA_WAHED_ZAWJIA:
            case NAW3_RAD_3ALA_MUKHTALIFEEN:
            case NAW3_RAD_3ALA_MUTAJANISEEN_ZAWJIA:
                createTableWithRadWaJami3a(app);
                break;

            case NAW3_RAD_3ALA_MUKHTALIFEEN_ZAWJIA: // TODO reduce enums
                createTableWithRadWaJami3aWaZawjia(app);
                break;

            default:
                createTableWithNoRad(app);
                break;
        }
    }

    private void createTableWithNoRad(ZaidApplication app) {
        Mas2ala mas2ala = app.getMassala();
        ArrayList<Mirath> mawarith = mas2ala.getMawarith();
        double tarika = app.getWarathaInput().getTarika();
        boolean hissabFardiColumn = mas2ala.isHissabFardi();

        // table head
        int col = 2;        // skip columns 0 & 1
        TextView cell;
        TableRow head = new TableRow(getActivity());

        if (mas2ala.getAwl() != 0) {
            //row of awl
            cell = createHeadCell(String.valueOf(mas2ala.getAwl()), !hissabFardiColumn && tarika == 0);
            head.addView(cell, new TableRow.LayoutParams(col));
            mResultTableLayout.addView(head);

            //row of asl
            head = new TableRow(getActivity());
            cell = createHeadAwlCell(mas2ala.getAsl());
        } else {
            cell = createHeadCell(String.valueOf(mas2ala.getAsl()), !hissabFardiColumn && tarika == 0);
        }
        head.addView(cell, new TableRow.LayoutParams(col++));

        if (hissabFardiColumn) {
            head.addView(createHeadCell(String.valueOf(mas2ala.getMissah()), tarika == 0), new TableRow.LayoutParams(col++));
        }

        if (tarika != 0) {
            head.addView(createHeadCell(String.format(Locale.ROOT, "%.2f", tarika), true), new TableRow.LayoutParams(col));
        }
        mResultTableLayout.addView(head);

        // table body
        boolean jadahColumn0FirstRow = true;
        boolean jadahColumn2FirstRow = true;
        boolean waladAlomColumn0FirstRow = true;
        boolean waladAlomColumn2FirstRow = true;
        boolean shirkaTa3seebFirstRow = true;
        boolean last_row = false;
        boolean last_column = !hissabFardiColumn && tarika == 0;
        int i = 0;
        for (Mirath m : mawarith) {

            TableRow row = new TableRow(getActivity());
            if (++i == mawarith.size()) { last_row = true; }

            // columns 0
            CELL cellType = CELL.EMPTY;
            if (m.isJadah() && m.isShirka()) {
                if (jadahColumn0FirstRow) {
                    cellType = CELL.SHIRKA;
                    jadahColumn0FirstRow = false;
                }
            }
            else if ((m.isWaladAlom() && mas2ala.isJinsayAwladAlom())
                    || (m.isWaladOmAwShakik() && mas2ala.isMushtarika())) {
                if (waladAlomColumn0FirstRow ) {
                    cellType = CELL.SHIRKA;
                    waladAlomColumn0FirstRow = false;
                }
            }
            else {
                cellType = CELL.NORMAL;
            }
            if (cellType == CELL.EMPTY) {
                cell = createEmptyCell(last_row);
            }
            else {
                String cellTxt = m.getHokom();
                if (cellType == CELL.SHIRKA) { cellTxt += " ↓"; }
                cell = createCell(cellTxt, last_column, last_row);
            }
            row.addView(cell);

            // column 1
            row.addView(createCell(m.getIsm(), last_column, last_row));

            // column 2: nassib mojmal
            cellType = CELL.EMPTY;
            if (m.isJadah() && m.isShirka()) {
                if (jadahColumn2FirstRow) {
                    cellType = CELL.SHIRKA;
                    jadahColumn2FirstRow = false;
                }
            }
            else if ((m.isWaladAlom() && mas2ala.isJinsayAwladAlom())
                    || (m.isWaladOmAwShakik() && mas2ala.isMushtarika())) {
                if (waladAlomColumn2FirstRow ) {
                    cellType = CELL.SHIRKA;
                    waladAlomColumn2FirstRow = false;
                }
            }
            else if (m.isTa3seeb() && mas2ala.isShirkaTa3seeb()) {
                if (shirkaTa3seebFirstRow) {
                    cellType = CELL.SHIRKA;
                    shirkaTa3seebFirstRow = false;
                }
            }
            else {
                cellType = CELL.NORMAL;
            }
            if (cellType == CELL.EMPTY) {
                cell = createEmptyCell(last_row);
            }
            else {
                String cellTxt =  m.getNassibMojmal();
                if (cellType == CELL.SHIRKA) { cellTxt += " ↓"; }
                cell = createCell(cellTxt, last_column, last_row);
            }
            row.addView(cell);

            // column 3: nassib fardi
            if (hissabFardiColumn) {
                row.addView(createCell(m.nassibFardiSahihToString(), tarika == 0, last_row));
            }

            if (tarika != 0) {
                // column 4: nassib fardi mina tarika
                StringBuilder nassibTarikaStr = new StringBuilder();
                nassibTarikaStr.append(String.format(Locale.ROOT, "%.2f", m.getNassibFardiSahih() * tarika / mas2ala.getMissah()));
                if (m.getNbr() > 1) {
                    nassibTarikaStr.append("(*").append(m.getNbr()).append(")");
                }
                row.addView(createCell(nassibTarikaStr, true, last_row));
            }
            mResultTableLayout.addView(row);
        }
    }

    private void createTableWithRad(ZaidApplication app) {
        Mas2ala mas2ala = app.getMassala();
        ArrayList<Mirath> mawarith = mas2ala.getMawarith();
        double tarika = app.getWarathaInput().getTarika();

        // table head
        int col = 2;        // skip columns 0 & 1
        TextView cell;
        TableRow head = new TableRow(getActivity());
        cell = createHeadCell(String.valueOf(mas2ala.getAsl()), tarika == 0);
        head.addView(cell, new TableRow.LayoutParams(col++));

        if (tarika != 0) {
            head.addView(createHeadCell(String.format(Locale.ROOT, "%.2f", tarika), true), new TableRow.LayoutParams(col));
        }
        mResultTableLayout.addView(head);

        // table body
        boolean last_row = false;
        int i = 0;
        for (Mirath m : mawarith) {
            TableRow row = new TableRow(getActivity());
            if (++i == mawarith.size()) { last_row = true; }

            // columns 0 & 1
            row.addView(createCell(m.getHokom(), true, last_row));
            row.addView(createCell(m.getIsm(), true, last_row));

            // column 2: fardh + rad
            String cellTxt = m.isMahjoob() ? "--" :
                    m.getRad() != 0 ? m.getRad() + "+" + m.getFardh() : "" + m.getFardh();
            cell = createCell(cellTxt, tarika == 0, true);
            row.addView(cell);

            if (tarika != 0) {
                // column 3: nassib mina tarika
                StringBuilder nassibTarikaStr = new StringBuilder();
                nassibTarikaStr.append(String.format(Locale.ROOT, "%.2f", m.getNassibFardiSahih() * tarika / mas2ala.getMissah()));
                row.addView(createCell(nassibTarikaStr, true, last_row));
            }
            mResultTableLayout.addView(row);
        }
    }

    private void createTableWithRadWaTashih(ZaidApplication app) { // TODO refactor with others
        Mas2ala mas2ala = app.getMassala();
        ArrayList<Mirath> mawarith = mas2ala.getMawarith();
        double tarika = app.getWarathaInput().getTarika();
        boolean hissabFardiColumn = mas2ala.isHissabFardi();

        // table head
        int col = 2;        // skip columns 0 & 1
        TextView cell;
        TableRow head = new TableRow(getActivity());

        cell = createHeadCell(String.valueOf(mas2ala.getAsl()), !hissabFardiColumn && tarika == 0);
        head.addView(cell, new TableRow.LayoutParams(col++));

        if (hissabFardiColumn) {
            head.addView(createHeadCell(String.valueOf(mas2ala.getMissah()), tarika == 0), new TableRow.LayoutParams(col++));
        }

        if (tarika != 0) {
            head.addView(createHeadCell(String.format(Locale.ROOT, "%.2f", tarika), true), new TableRow.LayoutParams(col));
        }
        mResultTableLayout.addView(head);

        // table body
        boolean jadahColumn0FirstRow = true;
        boolean jadahColumn2FirstRow = true;
        boolean waladAlomColumn0FirstRow = true;
        boolean waladAlomColumn2FirstRow = true;
        boolean last_row = false;
        boolean last_column = !hissabFardiColumn && tarika == 0;
        int i = 0;
        for (Mirath m : mawarith) {

            TableRow row = new TableRow(getActivity());
            if (++i == mawarith.size()) { last_row = true; }

            // column 0
            CELL cellType = CELL.EMPTY;
            if (m.isJadah() && m.isShirka()) {
                if (jadahColumn0FirstRow) {
                    cellType = CELL.SHIRKA;
                    jadahColumn0FirstRow = false;
                }
            }
            else if ((m.isWaladAlom() && mas2ala.isJinsayAwladAlom())
                    || (m.isWaladOmAwShakik() && mas2ala.isMushtarika())) {
                if (waladAlomColumn0FirstRow ) {
                    cellType = CELL.SHIRKA;
                    waladAlomColumn0FirstRow = false;
                }
            }
            else {
                cellType = CELL.NORMAL;
            }
            if (cellType == CELL.EMPTY) {
                cell = createEmptyCell(last_row);
            }
            else {
                String cellTxt = m.getHokom();
                if (cellType == CELL.SHIRKA) { cellTxt += " ↓"; }
                cell = createCell(cellTxt, false, last_row);
            }
            row.addView(cell);

            // column 1
            row.addView(createCell(m.getIsm(), false, last_row));

            // column 2: fardh + rad
            cellType = CELL.EMPTY;
            if (m.isJadah() && m.isShirka()) {
                if (jadahColumn2FirstRow) {
                    cellType = CELL.SHIRKA;
                    jadahColumn2FirstRow = false;
                }
            }
            else if ((m.isWaladAlom() && mas2ala.isJinsayAwladAlom())
                    || (m.isWaladOmAwShakik() && mas2ala.isMushtarika())) {
                if (waladAlomColumn2FirstRow ) {
                    cellType = CELL.SHIRKA;
                    waladAlomColumn2FirstRow = false;
                }
            }
            else {
                cellType = CELL.NORMAL;
            }
            if (cellType == CELL.EMPTY) {
                cell = createEmptyCell(last_row);
            }
            else {
                String cellTxt = m.isMahjoob() ? "--" :
                        m.getRad() != 0 ? m.getRad() + "+" + m.getFardh() : "" + m.getFardh();
                if (cellType == CELL.SHIRKA) { cellTxt += " ↓"; }
                cell = createCell(cellTxt, last_column, last_row);
            }
            row.addView(cell);

            // column 3: tashih
            if (hissabFardiColumn) {
                row.addView(createCell(m.nassibFardiSahihToString(), tarika == 0, last_row));
            }

            if (tarika != 0) {
                // column 4: nassib fardi mina tarika
                StringBuilder nassibTarikaStr = new StringBuilder();
                nassibTarikaStr.append(String.format(Locale.ROOT, "%.2f", m.getNassibFardiSahih() * tarika / mas2ala.getMissah()));
                if (m.getNbr() > 1) {
                    nassibTarikaStr.append("(*").append(m.getNbr()).append(")");
                }
                row.addView(createCell(nassibTarikaStr, true, last_row));
            }
            mResultTableLayout.addView(row);
        }
    }

    private void createTableWithRadWaJami3a(ZaidApplication app) {
        Mas2ala mas2ala = app.getMassala();
        ArrayList<Mirath> mawarith = mas2ala.getMawarith();
        double tarika = app.getWarathaInput().getTarika();
        boolean hissabFardiColumn = mas2ala.isHissabFardi();

        // table head
        int col = 2;        // skip columns 0 & 1
        TextView cell;
        TableRow head = new TableRow(getActivity());
        cell = createHeadCell(String.valueOf(mas2ala.getAsl()), false);
        head.addView(cell, new TableRow.LayoutParams(col++));

        cell = createHeadCell(String.valueOf(mas2ala.getAslJami3a()), !hissabFardiColumn && tarika == 0);
        head.addView(cell, new TableRow.LayoutParams(col++));

        if (hissabFardiColumn) {
            head.addView(createHeadCell(String.valueOf(mas2ala.getMissah()), tarika == 0), new TableRow.LayoutParams(col++));
        }

        if (tarika != 0) {
            head.addView(createHeadCell(String.format(Locale.ROOT, "%.2f", tarika), true), new TableRow.LayoutParams(col));
        }
        mResultTableLayout.addView(head);

        // table body
        boolean jadahColumn0FirstRow = true;
        boolean jadahColumn2FirstRow = true;
        boolean waladAlomColumn0FirstRow = true;
        boolean waladAlomColumn2FirstRow = true;
        boolean last_row = false;
        boolean last_column = !hissabFardiColumn && tarika == 0;
        int i = 0;
        for (Mirath m : mawarith) {

            TableRow row = new TableRow(getActivity());
            if (++i == mawarith.size()) { last_row = true; }

            // column 0
            CELL cellType = CELL.EMPTY;
            if (m.isJadah() && m.isShirka()) {
                if (jadahColumn0FirstRow) {
                    cellType = CELL.SHIRKA;
                    jadahColumn0FirstRow = false;
                }
            }
            else if ((m.isWaladAlom() && mas2ala.isJinsayAwladAlom())
                    || (m.isWaladOmAwShakik() && mas2ala.isMushtarika())) {
                if (waladAlomColumn0FirstRow ) {
                    cellType = CELL.SHIRKA;
                    waladAlomColumn0FirstRow = false;
                }
            }
            else {
                cellType = CELL.NORMAL;
            }
            if (cellType == CELL.EMPTY) {
                cell = createEmptyCell(last_row);
            }
            else {
                String cellTxt = m.getHokom();
                if (cellType == CELL.SHIRKA) { cellTxt += " ↓"; }
                cell = createCell(cellTxt, false, last_row);
            }
            row.addView(cell);

            // column 1
            row.addView(createCell(m.getIsm(), false, last_row));

            // column 2: fardh only // TODO DELTA
            // column 3: jami3a
            cellType = CELL.EMPTY;
            if (m.isJadah() && m.isShirka()) {
                if (jadahColumn2FirstRow) {
                    cellType = CELL.SHIRKA;
                    jadahColumn2FirstRow = false;
                }
            }
            else if ((m.isWaladAlom() && mas2ala.isJinsayAwladAlom())
                    || (m.isWaladOmAwShakik() && mas2ala.isMushtarika())) {
                if (waladAlomColumn2FirstRow ) {
                    cellType = CELL.SHIRKA;
                    waladAlomColumn2FirstRow = false;
                }
            }
            else {
                cellType = CELL.NORMAL;
            }
            TextView cell2;
            if (cellType == CELL.EMPTY) {
                cell = createEmptyCell(last_row);
                cell2 = createEmptyCell(last_row);
            }
            else {
                String cellTxt = m.isMahjoob() ? "--" : "" + m.getFardh();
                String cellTxt2 = m.getNassibMojmal();
                if (cellType == CELL.SHIRKA) { cellTxt += " ↓"; cellTxt2 += " ↓"; }
                cell = createCell(cellTxt, last_column, last_row);
                cell2 = createCell(cellTxt2, last_column, last_row);
            }
            row.addView(cell);
            row.addView(cell2);

            // column 4: nassib fardi
            if (hissabFardiColumn) {
                row.addView(createCell(m.nassibFardiSahihToString(), tarika == 0, last_row));
            }

            if (tarika != 0) {
                // column 4: nassib fardi mina tarika
                StringBuilder nassibTarikaStr = new StringBuilder();
                nassibTarikaStr.append(String.format(Locale.ROOT, "%.2f", m.getNassibFardiSahih() * tarika / mas2ala.getMissah()));
                if (m.getNbr() > 1) {
                    nassibTarikaStr.append("(*").append(m.getNbr()).append(")");
                }
                row.addView(createCell(nassibTarikaStr, true, last_row));
            }
            mResultTableLayout.addView(row);
        }
    }

    private void createTableWithRadWaJami3aWaZawjia(ZaidApplication app) {
        Mas2ala mas2ala = app.getMassala();
        ArrayList<Mirath> mawarith = mas2ala.getMawarith();
        double tarika = app.getWarathaInput().getTarika();
        boolean hissabFardiColumn = mas2ala.isHissabFardi();

        // table head
        int col = 2;        // skip columns 0 & 1
        TextView cell, cell2, cell3, cell4, cell5;       // # is column order
        TableRow head = new TableRow(getActivity());
        cell2 = createHeadCell(String.valueOf(mas2ala.getAsl()), false);
        head.addView(cell2, new TableRow.LayoutParams(col++));

        cell3 = createHeadCell(String.valueOf(mas2ala.getAslZawjia()), false);
        head.addView(cell3, new TableRow.LayoutParams(col++));

        cell4 = createHeadCell(String.valueOf(mas2ala.getAslRad()), false);
        head.addView(cell4, new TableRow.LayoutParams(col++));

        cell5 = createHeadCell(String.valueOf(mas2ala.getAslJami3a()), !hissabFardiColumn && tarika == 0);
        head.addView(cell5, new TableRow.LayoutParams(col++));

        if (hissabFardiColumn) {
            head.addView(createHeadCell(String.valueOf(mas2ala.getMissah()), tarika == 0), new TableRow.LayoutParams(col++));
        }

        if (tarika != 0) {
            head.addView(createHeadCell(String.format(Locale.ROOT, "%.2f", tarika), true), new TableRow.LayoutParams(col+1));
        }
        mResultTableLayout.addView(head);

        // table body
        boolean jadahColumn0FirstRow = true;
        boolean jadahColumn2FirstRow = true;
        boolean waladAlomColumn0FirstRow = true;
        boolean waladAlomColumn2FirstRow = true;
        boolean last_row = false;
        boolean last_column = !hissabFardiColumn && tarika == 0;
        int i = 0;
        for (Mirath m : mawarith) {

            TableRow row = new TableRow(getActivity());
            if (++i == mawarith.size()) { last_row = true; }

            // column 0
            CELL cellType = CELL.EMPTY;
            if (m.isJadah() && m.isShirka()) {
                if (jadahColumn0FirstRow) {
                    cellType = CELL.SHIRKA;
                    jadahColumn0FirstRow = false;
                }
            }
            else if ((m.isWaladAlom() && mas2ala.isJinsayAwladAlom())
                    || (m.isWaladOmAwShakik() && mas2ala.isMushtarika())) {
                if (waladAlomColumn0FirstRow ) {
                    cellType = CELL.SHIRKA;
                    waladAlomColumn0FirstRow = false;
                }
            }
            else {
                cellType = CELL.NORMAL;
            }
            if (cellType == CELL.EMPTY) {
                cell = createEmptyCell(last_row);
            }
            else {
                String cellTxt = m.getHokom();
                if (cellType == CELL.SHIRKA) { cellTxt += " ↓"; }
                cell = createCell(cellTxt, false, last_row);
            }
            row.addView(cell);

            // column 1
            row.addView(createCell(m.getIsm(), false, last_row));

            // column 2: fardh only // TODO DELTA
            // column 3: zawjia
            // column 4: rad
            // column 5: jami3a
            switch (i) {
                case 1: // Zawj or zawjat are always first in table
                    cell3 = createCell("1", false, last_row);
                    break;
                case 2:
                    String cellTxt = mas2ala.getBaqiZawjia() + "";
                    if (mawarith.size() > 2) { cellTxt += " ↓"; }
                    cell3 = createCell(cellTxt, false, last_row);
                    break;
                default:
                    cell3 = createEmptyCell(last_row);
                    break;
            }

            cellType = CELL.EMPTY;     // used for cell2, cell4 & cell5 only
            if (m.isJadah() && m.isShirka()) {
                if (jadahColumn2FirstRow) {
                    cellType = CELL.SHIRKA;
                    jadahColumn2FirstRow = false;
                }
            }
            else if ((m.isWaladAlom() && mas2ala.isJinsayAwladAlom())
                    || (m.isWaladOmAwShakik() && mas2ala.isMushtarika())) {
                if (waladAlomColumn2FirstRow ) {
                    cellType = CELL.SHIRKA;
                    waladAlomColumn2FirstRow = false;
                }
            }
            else {
                cellType = CELL.NORMAL;
            }
            if (cellType == CELL.EMPTY) {
                cell2 = createEmptyCell(last_row);
                cell4 = createEmptyCell(last_row);
                cell5 = createEmptyCell(last_row);
            }
            else {
                String cellTxt2 = m.isMahjoob() ? "--" : "" + m.getFardh();
                String cellTxt4 = i == 1 ? "--" : m.getRad() != 0 ? m.getRad() + "" : "--";
                String cellTxt5 = m.getNassibMojmal();
                if (cellType == CELL.SHIRKA) { cellTxt2 += " ↓"; cellTxt4 += " ↓"; cellTxt5 += " ↓"; }
                cell2 = createCell(cellTxt2, last_column, last_row);
                cell4 = createCell(cellTxt4, last_column, last_row);
                cell5 = createCell(cellTxt5, last_column, last_row);
            }
            row.addView(cell2);
            row.addView(cell3);
            row.addView(cell4);
            row.addView(cell5);

            // column 6: nassib fardi
            if (hissabFardiColumn) {
                row.addView(createCell(m.nassibFardiSahihToString(), tarika == 0, last_row));
            }

            if (tarika != 0) {
                // column 7: nassib fardi mina tarika
                StringBuilder nassibTarikaStr = new StringBuilder();
                nassibTarikaStr.append(String.format(Locale.ROOT, "%.2f", m.getNassibFardiSahih() * tarika / mas2ala.getMissah()));
                if (m.getNbr() > 1) {
                    nassibTarikaStr.append("(*").append(m.getNbr()).append(")");
                }
                row.addView(createCell(nassibTarikaStr, true, last_row));
            }
            mResultTableLayout.addView(row);
        }
    }

    private TextView createHeadCell(CharSequence txt, boolean last_column) {
        TextView head = prepareTextView();

        head.setBackgroundResource((last_column)? R.drawable.last_head_borders : R.drawable.head_borders);
        head.setText(txt);
        return head;
    }

    private TextView createHeadAwlCell(int awl) {
        TextView head = prepareTextView();

        head.setBackgroundResource((awl < 10) ? R.drawable.head_awl1 : R.drawable.head_awl2);
        head.setText(String.valueOf(awl));
        return head;
    }

    private TextView createCell(CharSequence txt, boolean last_column, boolean last_row) {
        TextView cell = prepareTextView();

        if (last_row) {
            cell.setBackgroundResource((last_column)? R.drawable.last_row_and_column_borders : R.drawable.last_row_borders);
        } else {
            cell.setBackgroundResource((last_column)? R.drawable.last_column_borders : R.drawable.cell_borders);
        }
        cell.setText(txt);
        return cell;
    }

    private TextView createEmptyCell(boolean last_row) {
        TextView cell = prepareTextView();

        cell.setBackgroundResource(last_row ? R.drawable.last_row_shirka_borders : R.drawable.shirka_cell_borders);
        cell.setText("");
        return cell;
    }

    private TextView prepareTextView() {
        TextView tv = new TextView(getActivity());

        tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.font_size));
        tv.setTextColor(Color.BLACK);
        tv.setTextDirection(View.TEXT_DIRECTION_RTL);
        tv.setGravity(Gravity.START);
        return tv;
    }
}
