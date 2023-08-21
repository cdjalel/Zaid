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

package com.djalel.libjfarayid;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Mirath {
    private final Warith warith;
    private String sharh;
    private final int nbr;            // عدد الورثة المتشابهين في كل شيء

    private final int bast;           // البسط في نصيب صاحب الفرض
    private final int maqam;          // المقام في نصيب صاحب الفرض

    private final boolean ta3seeb;    // هل يرث بالتعصيب (مع الفرض أو لا)
    private final int ro2os;          // عدد الرؤوس المشتركين في الباقي

    private final boolean thuluth_albaqi;
    private final boolean ashakikat_tarithna_albaqi_ila_alfardh;

    // calculated later
    private int fardh;          // أسهم صاحب الفرض من أصل المسألة
    private int rad;            // أسهم الوارث في مسألة الرد دون أحد الزوجين
    private int sahmJami3a;     // أسهم الوارث في مسألة الرد الجامعة وفيها الفرض زائد الرد لغير الزوجين
    private double nassib;      // النصيب الفردي لكل وارث قبل تصحيح الانكسار
    private double nassibSahih; // النصيب الفردي لكل وارث بعد التصحيح

    // short textual form of the solution to display in a table
    private final String hokom;
    private String ism;
    private String nassibMojmal;   // إجمالي نصيب الوارث (أو الورثة المتشابهين) من أصل المسألة
    private String nassibFardiText;    // تفصيل النصيب الفردي لكل وارث
    private String nassibFardiSahihText; // النصيب الفردي لكل وارث بعد التصحيح

    private static final DecimalFormat df = new DecimalFormat("0.00");

    public Mirath(Warith warith, int nbr, String sharh, int bast, int maqam, boolean ta3seeb, int ro2os) {
        // TODO assert maqam != 0 & sharh != null
        this.warith = warith;
        this.nbr = nbr;
        this.sharh = sharh;
        this.bast = bast;
        this.maqam = maqam;
        this.ta3seeb = ta3seeb;
        this.ro2os = ro2os;

        boolean ashakikat_tarithna_albaqi_ila_alfardh1 = false;
        boolean thuluth_albaqi1 = false;

        switch (bast) {
            case 1:
                switch (maqam) {
                    case 2:
                        if (ta3seeb && (warith == Warith.ALAKHAWAT_ASHAKIKAT || warith == Warith.ALAKHAWAT_LI_AB)) {
                            hokom = "الباقي إلى 1\\2" ;
                            ashakikat_tarithna_albaqi_ila_alfardh1 = true;
                        }
                        else {
                            hokom = "1\\2"; //"½";
                        }
                        break;
                    case 3:
                        // من يأخذ ثلث الباقي: الأم في الغراوين أو بعض حالات الجد مع الإخوة
                        if (ta3seeb && (warith == Warith.ALOM || warith == Warith.ALJAD)) {
                            hokom = "1\\3 الباقي";
                            thuluth_albaqi1 = true;
                        }
                        else {
                            hokom = "1\\3"; //"⅓" ;
                        }
                        break;
                    case 4:
                        hokom = "1\\4"; // "¼";
                        break;
                    case 6:
                        hokom = ta3seeb? "1\\6 + تعصيب" : "1\\6"; //"⅙";
                        // الأب والجد يمكن أن يرثا السدس زائد الباقي
                        break;
                    case 8:
                        hokom = "1\\8"; //"⅛";
                        break;
                    default:  // assert(0)
                        hokom = "";
                        break;
                }
                break;
            case 2:         // assert maqam == 3
                if (ta3seeb && (warith == Warith.ALAKHAWAT_ASHAKIKAT || warith == Warith.ALAKHAWAT_LI_AB)) {
                    hokom = "الباقي إلى 2\\3" ;
                    ashakikat_tarithna_albaqi_ila_alfardh1 = true;
                }
                else {
                    hokom = "2\\3"; //"⅔";
                }
                break;
            case 0:
            default:
                hokom = ta3seeb ? "تعصيب" : "حجب";
                break;
        }

        ashakikat_tarithna_albaqi_ila_alfardh = ashakikat_tarithna_albaqi_ila_alfardh1;
        thuluth_albaqi = thuluth_albaqi1;

        if (warith != null) {
            StringBuilder tmp = new StringBuilder();
            tmp.append(warith.getName());
            if (nbr > 1) {
                tmp.append("(*").append(nbr).append(")");
            }
            ism = tmp.toString();
        }

        // calculated later
        fardh = 0;
        rad = 0;
        sahmJami3a = 0;
        nassib = 0;
        nassibSahih = 0;
        nassibFardiText = null;
        nassibFardiSahihText = null;
        nassibMojmal = null;

        df.setRoundingMode(RoundingMode.FLOOR);
    }

    // وارث واحد يرث بالفرض فقط
    public Mirath(Warith warith, String sharh, int bast, int maqam) {
        this(warith, 1, sharh, bast, maqam, false, 1);
    }

    // الجدة ترث بالفرض فقط وربما مقاسمة مع الجدة الأخرى
    public Mirath(Warith warith, String sharh, int bast, int maqam, int ro2os) {
        this(warith, 1, sharh, bast, maqam, false, ro2os);
    }
    // عدة ورثة من نفس النوع (مثلا 3 زوجات أو بنتين دون ابن) يرثون بالفرض فقط
    public Mirath(Warith warith, int nbr, String sharh, int bast, int maqam) {
        this(warith, nbr, sharh, bast, maqam, false, nbr);
    }

    // عدة ورثة  (أولاد الأم) يرثون بالفرض فقط
    // أو حالة الأخ الشقيق يشترك في الثلث مع الإخوة لإم في المسألة المشتركة
    public Mirath(Warith warith, int nbr, String sharh, int bast, int maqam, int ro2os) {
        this(warith, nbr, sharh, bast, maqam, false, ro2os);
    }

    // عدة ورثة متشابهين يرثون بالتصعيب بالتساوي
    public Mirath(Warith warith, int nbr, String sharh) {
        this(warith, nbr, sharh, 0, 1, true, nbr);
    }

    // عدة ورثة يرثون بالتصعيب بالتساوي (الإخوة مع الجد) أو لذكر مثل حظ الأنثيين (الإخوة مع الأخوات)
    public Mirath(Warith warith, int nbr, String sharh, int ro2os) {
        this(warith, nbr, sharh, 0, 1, true, ro2os);
    }

    // وارث واحد بالفرض والتعصيب لوحده (الأب والجد)
    public Mirath(Warith warith, String sharh, int bast, int maqam, boolean ta3seeb) {
        this(warith, 1, sharh, bast, maqam, ta3seeb, 1);
    }

    // حالة وارث واحد بالفرض والتعصيب مقاسمة وهو الجد مع الإخوة
    // أو حالة من يأخذ ثلث الباقي وهي الأم في مسألة الغرواين
    public Mirath(Warith warith, String sharh, int bast, int maqam, boolean ta3seeb, int ro2os) {
        this(warith, 1, sharh, bast, maqam, ta3seeb, ro2os);
    }

    public Mirath(Warith warith, String hajb, int nbr) {
        this(warith, nbr, hajb, 0, 1);
        // TODO assert hajb != null
    }

    // no default constructor
    /* private Mirath() {
        this(null, 0, null, 0, 0, false, 0);
    } */

    // copy constructor
    public Mirath(Mirath src) {
        this.warith = src.warith;
        this.nbr = src.nbr;
        this.bast = src.bast;
        this.maqam = src.maqam;
        this.ta3seeb = src.ta3seeb;
        this.ro2os = src.ro2os;

        this.fardh = src.fardh;
        this.nassib = src.nassib;
        this.nassibSahih = src.nassibSahih;
        this.rad = src.rad;
        this.sahmJami3a = src.sahmJami3a;

        this.sharh = src.sharh;
        this.hokom = src.hokom;
        this.ism = src.ism;
        this.nassibFardiText = src.nassibFardiText;
        this.nassibFardiSahihText = src.nassibFardiSahihText;
        this.nassibMojmal = src.nassibMojmal;

        this.thuluth_albaqi = src.thuluth_albaqi;
        this.ashakikat_tarithna_albaqi_ila_alfardh = src.ashakikat_tarithna_albaqi_ila_alfardh;
    }

    public void addHajib(String hajb) {
        // assert sharh != null
        // assert hajb != null
        sharh += " و " + hajb;
    }

    public String getHokom() { return hokom; }

    public String getIsm() { return ism; }

    public String getSharh() { return sharh; }

    public int getNbr() { return  nbr; }

    public int getBast() { return bast; }

    public int getMaqam() { return maqam; }

    public boolean isTa3seeb() { return ta3seeb; }

    public int getRo2os() { return ro2os; }

    public boolean isShirka() { return (ro2os > 1); }

    public boolean isMahjoob() { return (bast == 0) && !ta3seeb; }

    public Warith getWarith() { return warith; }

    public int getFardh() { return fardh; }

    public void setFardh(int fardh) { this.fardh = fardh; }

    public boolean isFardh() { return (bast != 0) && (maqam != 1) && !thuluth_albaqi && !ashakikat_tarithna_albaqi_ila_alfardh; }

    public boolean isTholuthAlbaqi() { return thuluth_albaqi; }

    public boolean isShakikatTarithnaAlbaqiIlaFardh() { return ashakikat_tarithna_albaqi_ila_alfardh; }

    public String getNassibMojmal() { return this.nassibMojmal; }

    public void setNassibMojmal(String nassibMojmal) { this.nassibMojmal = nassibMojmal; }

    public String getNassibFardiText() { return nassibFardiText; }

    public void setNassibFardiText(String nassibFardi) { this.nassibFardiText = this.nassibFardiSahihText = nassibFardi; }

    public double getNassibFardi() { return this.nassib; }

    public void setNassibFardi(double nassib) { this.nassib = this.nassibSahih = nassib; }

    public static String nassibToString(double nassib) {
        //nassib = nassib < 0 ? - nassib : nassib;
        boolean is_decimal = (nassib - (int)nassib) != 0;
        return is_decimal? df.format(nassib) : "" + (int)nassib;
    }

    public String nassibFardiToString() {
        StringBuilder tmp = new StringBuilder();
        tmp.append(nassibToString(nassib));
        if (nbr > 1) {
            tmp.append("(*").append(nbr).append(")");
        }
        return tmp.toString();
    }

    public String getNassibFardiSahihText() { return nassibFardiSahihText; }

    public void setNassibFardiSahihText(String nassibFardiSahih) { this.nassibFardiSahihText = nassibFardiSahih; }

    public double getNassibFardiSahih() { return this.nassibSahih; }
    public void setNassibFardiSahih(double nassibSahih) { this.nassibSahih = nassibSahih; }

    public String nassibFardiSahihToString() {
        StringBuilder tmp = new StringBuilder();
        tmp.append(nassibToString(nassibSahih));
        if (nbr > 1) {
            tmp.append("(*").append(nbr).append(")");
        }
        return tmp.toString();
    }

    public boolean isJadah() { return (this.warith == Warith.ALJADAH_LI_OM || this.warith == Warith.ALJADAH_LI_AB); }

    public boolean isWaladAlom() { return (this.warith == Warith.ALIKHWA_LI_OM) || (this.warith == Warith.ALAKHAWAT_LI_OM); }

    public boolean isShakik() { return (this.warith == Warith.ALIKHWA_ALASHIKA) || (this.warith == Warith.ALAKHAWAT_ASHAKIKAT); }

    public boolean isWaladOmAwShakik() { return  isWaladAlom() || isShakik(); }

    public boolean isAhadZawjain() { return (this.warith == Warith.AZAWJ) || (this.warith == Warith.AZAWJAT); }

    public int getRad() { return rad; }

    public void setRad(int rad) { this.rad = rad; }

    public int getJami3Alfardh() { return sahmJami3a != 0 ? sahmJami3a : fardh; }

    // public int getSahmJami3a() { return sahmJami3a; }

    public void setJami3AlfardhWaRad(int sahmJami3a) { this.sahmJami3a = sahmJami3a; }

    public void addSharh(String extra) {
        sharh = sharh + " " + extra;
    }

    public final String getDhamir() { return warith != null ? warith.getDhamir(nbr) : ""; }
}
