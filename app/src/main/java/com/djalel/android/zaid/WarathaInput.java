/*
 * Copyright © 3019 Djalel Chefrour
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

public class WarathaInput {
    private Madhab madhab;
    private boolean male;
    private boolean alab;
    private boolean alom;
    private boolean aljad;
    private boolean aljadah_li_ab;
    private boolean aljadah_li_om;
    private boolean zawj;

    private int azawjat;
    private int alabna;
    private int albanat;
    private int abna_alabna;
    private int banat_alabna;
    private int alikhwa_li_om;
    private int alakhawat_li_om;
    private int alikhwa_alashika;
    private int alakhawat_ashakikat;
    private int alikhwa_li_ab;
    private int alakhawat_li_ab;
    private int abna_alikhwa_alashika;
    private int abna_alikhwa_li_ab;
    private int ala3mam_alashika;
    private int ala3mam_li_ab;
    private int abna_ala3mam_alashika;
    private int abna_ala3mam_li_ab;

    public  WarathaInput () {}

    public void resetWarathahInput(){
        madhab = Madhab.MALIKI;
        male = true;
        alab = false;
        alom = false;
        aljad = false;
        aljadah_li_ab = false;
        aljadah_li_om = false;
        zawj = false;

        azawjat = 0;
        alabna = 0;
        albanat = 0;
        abna_alabna = 0;
        banat_alabna = 0;
        alikhwa_li_om = 0;
        alakhawat_li_om = 0;
        alikhwa_alashika = 0;
        alakhawat_ashakikat = 0;
        alikhwa_li_ab = 0;
        alakhawat_li_ab = 0;
        abna_alikhwa_alashika = 0;
        abna_alikhwa_li_ab = 0;
        ala3mam_alashika = 0;
        ala3mam_li_ab = 0;
        abna_ala3mam_alashika = 0;
        abna_ala3mam_li_ab = 0;
    }

    public void set_madhab(Madhab m) {
        madhab = m;
    }

    public Madhab get_madhab() {
        return madhab;
    }

    public boolean is_male() {
        return male;
    }

    public void set_male(boolean b) {
        male = b;
    }

    public boolean alab() {
        return alab;
    }

    public void set_alab(boolean b) {
        alab = b;
    }

    public boolean alom() {
        return alom;
    }

    public void set_alom(boolean b) {
        alom = b;
    }

    public boolean aljad() {
        return aljad;
    }

    public void set_aljad(boolean b) {
        aljad = b;
    }

    public boolean aljadah_li_ab() {
        return aljadah_li_ab;
    }

    public void set_aljadah_li_ab(boolean b) {
        aljadah_li_ab = b;
    }

    public boolean aljadah_li_om() {
        return aljadah_li_om;
    }

    public void set_aljadah_li_om(boolean b) {
        aljadah_li_om = b;
    }

    public boolean zawj() {
        return zawj;
    }

    public void set_zawj(boolean b) {
        zawj = b;
    }

    public void set_azawjat(int n) {
        azawjat = n;
    }

    public int get_azawjat() {
        return azawjat;
    }

    public void set_alabna(int n) {
        alabna = n;
    }

    public int get_alabna() {
        return alabna;
    }

    public void set_albanat(int n) {
        albanat = n;
    }

    public int get_albanat() {
        return albanat;
    }

    public void set_abna_alabna(int n) {
        abna_alabna = n;
    }

    public int get_abna_alabna() {
        return abna_alabna;
    }

    public void set_banat_alabna(int n) {
        banat_alabna = n;
    }

    public int get_banat_alabna() {
        return banat_alabna;
    }

    public void set_alikhwa_li_om(int n) {
        alikhwa_li_om = n;
    }

    public int get_alikhwa_li_om() {
        return alikhwa_li_om;
    }

    public void set_alakhawat_li_om(int n) {
        alakhawat_li_om = n;
    }

    public int get_alakhawat_li_om() {
        return alakhawat_li_om;
    }

    public void set_alikhwa_alashika(int n) {
        alikhwa_alashika = n;
    }

    public int get_alikhwa_alashika() {
        return alikhwa_alashika;
    }

    public void set_alakhawat_ashakikat(int n) {
        alakhawat_ashakikat = n;
    }

    public int get_alakhawat_ashakikat() {
        return alakhawat_ashakikat;
    }

    public void set_alikhwa_li_ab(int n) {
        alikhwa_li_ab = n;
    }

    public int get_alikhwa_li_ab() {
        return alikhwa_li_ab;
    }

    public void set_alakhawat_li_ab(int n) {
        alakhawat_li_ab = n;
    }

    public int get_alakhawat_li_ab() {
        return alakhawat_li_ab;
    }

    public void set_abna_alikhwa_alashika(int n) {
        abna_alikhwa_alashika = n;
    }

    public int get_abna_alikhwa_alashika() {
        return abna_alikhwa_alashika;
    }

    public void set_abna_alikhwa_li_ab(int n) {
        abna_alikhwa_li_ab = n;
    }

    public int get_abna_alikhwa_li_ab() {
        return abna_alikhwa_li_ab;
    }

    public void set_ala3mam_alashika(int n) {
        ala3mam_alashika = n;
    }

    public int get_ala3mam_alashika() { return ala3mam_alashika; }

    public void set_ala3mam_li_ab(int n) {
        ala3mam_li_ab = n;
    }

    public int get_ala3mam_li_ab() {
        return ala3mam_li_ab;
    }

    public void set_abna_ala3mam_alashika(int n) {
        abna_ala3mam_alashika = n;
    }

    public int get_abna_ala3mam_alashika() {
        return abna_ala3mam_alashika;
    }

    public void set_abna_ala3mam_li_ab(int n) {
        abna_ala3mam_li_ab = n;
    }

    public int get_abna_ala3mam_li_ab() { return abna_ala3mam_li_ab; }
}