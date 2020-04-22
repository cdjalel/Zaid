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
    private int nassib;    // النصيب الفردي لكل وارث

    // short textual form of the solution to display in a table
    private String hokom;
    private String ism;
    private String nassibMojmal;   // إجمالي نصيب الوارث (أو الورثة المتشابهين) من أصل المسألة
    private String nassibFardi;    // النصيب الفردي لكل وارث

    public Mirath(Warith warith, int nbr, String sharh, int bast, int maqam, boolean ta3seeb, int ro2os) {
        // TODO assert maqam != 0 & sharh != null
        this.warith = warith;
        this.nbr = nbr;
        this.sharh = sharh;
        this.bast = bast;
        this.maqam = maqam;
        this.ta3seeb = ta3seeb;
        this.ro2os = ro2os;

        switch (bast) {
            case 1:
                switch (maqam) {
                    case 2:
                        hokom = "1\\2"; //"½";
                        break;
                    case 3:
                        hokom = ta3seeb? "1\\3 الباقي": "1\\3"; //"⅓" ;
                        // من يأخذ ثلث الباقي: الأم في الغراوين أو بعض حالات الجد مع الإخوة
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
                hokom = "2\\3"; //"⅔";
                break;
            case 0:
            default:
                hokom = ta3seeb ? "تعصيب" : "حجب";
                break;
        }

        StringBuilder tmp = new StringBuilder();
        tmp.append(warith.getName());
        if (nbr > 1) {
            tmp.append("(*").append(nbr).append(")");
        }
        ism = tmp.toString();

        // calculated later
        fardh = 0;
        nassib = 0;
        nassibFardi = null;
        nassibMojmal = null;
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
    public Mirath(Warith warith, int nbr, String sharh, int bast, int maqam, int ro2os) {
        this(warith, nbr, sharh, bast, maqam, false, ro2os);
    }

    // وارث واحد بالتعصيب فقط
    public Mirath(Warith warith, String sharh, boolean ta3seeb) {
        this(warith, 1, sharh, 0, 1, ta3seeb, 1);
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

    // وارث واحد بالفرض والتعصيب مقاسمة وهو الجد مع الإخوة أو من يأخذ ثلث الباقي (الأم في الغرواين)
    public Mirath(Warith warith, String sharh, int bast, int maqam, boolean ta3seeb, int ro2os) {
        this(warith, 1, sharh, bast, maqam, ta3seeb, ro2os);
    }

    public Mirath(Warith warith, String hajb) {
        this(warith, hajb, 0, 1);
        // TODO assert hajb != null
    }

    // no default constructor
    private Mirath() {
        this(null, 0, null, 0, 0, false, 0);
    }

    public void addHajib(String hajb) {
        // assert sharh != null
        // assert hajb != null
        sharh += " و " + hajb;
    }

    public String getSharh() { return sharh; }

    public int getNbr() { return  nbr; }

    public int getBast() { return bast; }

    public int getMaqam() { return maqam; }

    public boolean isTa3seeb() { return ta3seeb; }

    public int getRo2os() { return ro2os; }

    public boolean isShirka() { return (ro2os > 1); }

    public int getFardh() { return fardh; }

    public Warith getWarith() { return warith; }

    public boolean isMahjoob() { return (bast == 0) && !ta3seeb; }

    public boolean isFardh() { return (bast != 0) && (maqam != 1) && !isTholuthAlbaqi(); }

    public boolean isTholuthAlbaqi() { return bast == 1 && maqam == 3 && ta3seeb; }

    public void setFardh(int fardh) { this.fardh = fardh; }

    public void setNassib(int nassib) { this.nassib = nassib; }

    public int getNassib() { return this.nassib; }

    public String getHokom() { return hokom; }

    public String getIsm() { return ism; }

    public String getNassibMojmal() { return this.nassibMojmal; }

    public void setNassibMojmal(String nassibMojmal) { this.nassibMojmal = nassibMojmal; }

    public String getNassibFardi(boolean verbose) {
        if (verbose) {
            return this.nassibFardi;
        }
        StringBuilder tmp = new StringBuilder();
        tmp.append(nassib);
        if (nbr > 1) {
            tmp.append("(*").append(nbr).append(")");
        }
        return tmp.toString();
    }

    public String getNassibFardi() { return getNassibFardi(false); }

    public void setNassibFardi(String nassibFardi) { this.nassibFardi = nassibFardi; }

    public boolean isJadah(){ return (this.warith == Warith.ALJADAH_LI_OM || this.warith == Warith.ALJADAH_LI_AB); }
}
