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
    private Warith warith;
    private String sharh;
    private int nbr;            // عدد الورثة المتشابهين في كل شيء

    private int bast;           // البسط في نصيب صاحب الفرض
    private int maqam;          // المقام في نصيب صاحب الفرض

    private boolean ta3seeb;    // هل يرث بالتعصيب (مع الفرض أو لا)
    private int ro2os;          // عدد الرؤوس المشتركين في الباقي

    // calculated later
    private int fardh;          // أسهم صاحب الفرض من أصل المسألة
    private int nassib;           // إجمالي نصيب الوارث من أصل المسألة

    // textual form of the solution
    private String irth;
    private String ism;
    private String nassibTxt;

    public Mirath(Warith warith, String sharh, int bast, int maqam, boolean ta3seeb, int nbr, int ro2os) {
        // TODO assert maqam != 0 & tafsir != null
        this.warith = warith;
        this.sharh = sharh;
        this.nbr = nbr;
        this.bast = bast;
        this.maqam = maqam;
        this.ta3seeb = ta3seeb;
        this.ro2os = ro2os;

        fardh = 0;
        nassib = 0;

        irth = null;
        ism = null;
        nassibTxt = null;
    }

    public Mirath(Warith warith, String sharh, int bast, int maqam, boolean ta3seeb, int nbr) {
        this(warith, sharh, bast, maqam, ta3seeb, nbr, nbr);
    }

    public Mirath(Warith warith, String sharh, int bast, int maqam, boolean ta3seeb) {
        this(warith, sharh, bast, maqam, ta3seeb, 1);
    }

    public Mirath(Warith warith, String sharh, int bast, int maqam) {
        this(warith, sharh, bast, maqam, false);
    }

    public Mirath(Warith warith, String hajb) {
        this(warith, hajb, 0, 1);
        // TODO assert hajb != null
    }

    // no default constructor
    private Mirath() {
        this(null, null, 0, 0, false, 0, 0);
    }

    public void addHajib(String hajb) {
        // assert tafsir != null
        // assert hajb != null
        sharh += " و " + hajb;
    }

    public String getSharh() { return sharh; }

    public int getNbr() { return  nbr; }

    public int getBast() {
        return bast;
    }

    public int getMaqam() {
        return maqam;
    }

    public boolean isTa3seeb() {
        return ta3seeb;
    }

    public int getRo2os() { return ro2os; }

    public boolean isShirka() { return (ro2os > 1); }

    public int getFardh() { return fardh; }

    public Warith getWarith() { return warith; }

    public boolean mahjoob() { return (bast == 0) && !ta3seeb; }

    public boolean isFardh() { return (bast != 0) && (maqam != 1); }

    public boolean isTholuthAlbaqi() { return bast == 1 && maqam == 3 && ta3seeb; }

    public void setFardh(int fardh) { this.fardh = fardh; }

    public void setNassib(int nassib) { this.nassib = nassib; }

    public int getNassib() { return this.nassib; }

    public String getIrth() { return irth; }

    public void setIrth(String irth) { this.irth =  irth; }

    public String getIsm() { return ism; }

    public void setIsm(String ism) { this.ism = ism; }

    public String getNassibTxt() { return this.nassibTxt; }

    public void setNassibTxt(String nassibTxt) { this.nassibTxt = nassibTxt; }
}
