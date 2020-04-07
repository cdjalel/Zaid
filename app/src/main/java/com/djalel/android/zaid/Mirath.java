/*
 * Copyright © 2019 Djalel Chefrour
 * Copyright © 2019 Haihtem Guefassa
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

public class Mirath {
    // TODO: add who/warith field of enum type {ab, jad, etc.}
    private String tafsir;
    private int nbr;            // عدد الورثة المتشابهين في كل شيء

    private int bast;           // البسط في سهم صاحب الفرض
    private int maqam;          // المقام في سهم صاحب الفرض

    private boolean baqi;           // هل يرث بالباقي (مع الفرض أو لا)
    private int ro2os;          // عدد الرؤوس المشتركين في الباقي

    public Mirath(String tafsir, int bast, int maqam, boolean baqi, int nbr, int ro2os) {
        // TODO assert maqam != 0 & tafsir != null
        this.tafsir = tafsir;
        this.nbr = nbr;
        this.bast = bast;
        this.maqam = maqam;
        this.baqi = baqi;
        this.ro2os = ro2os;
    }

    public Mirath(String tafsir, int bast, int maqam, boolean baqi, int nbr) {
        this(tafsir, bast, maqam, baqi, nbr, 1);
    }

    public Mirath(String tafsir, int bast, int maqam, boolean baqi) {
        this(tafsir, bast, maqam, baqi, 1);
    }

    public Mirath( String tafsir, int bast, int maqam) {
        this(tafsir, bast, maqam, false);
    }

    public Mirath(String hajb) {
        this(hajb, 0, 1);
        // TODO assert hajb != null
        // TODO: move hajb method inside this class?
    }

    // no default constructor
    private Mirath() {
        this(null,0, 0, false, 0, 0);
    }


    public boolean mahjoob() {
        if (bast == 0 && !baqi) {
            return true;
        }
        else {
            return false;
        }
    }

    public void addHajib(String hajb) {
        // assert tafsir != null
        // assert hajb != null
        tafsir += " و " + hajb;
    }

    public int getBast() {
        return bast;
    }

    public int getMaqam() {
        return maqam;
    }

    public boolean baqi() {
        return baqi;
    }

    public int getNbr() { return  nbr; }

    public int getRo2os() { return ro2os; }

    public String getTafsir() { return tafsir; }

    public void setBast(int bast) {
        this.bast = bast;
    }

    public void setMaqam(int maqam) {
        this.maqam = maqam;
    }

    public void setBaqi(boolean baqi) {
        this.baqi = baqi;
    }

    public void setTafsir(String tafsir) { this.tafsir = tafsir; }
}
