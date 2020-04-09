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

import java.util.ArrayList;
import java.util.List;

public class Massala {
    List<Mirath> mWarathah;
    List<Mirath> mMahjoobin;
    private boolean mHal;
    private boolean mFardh;
    private boolean mTa3seeb;
    private boolean mIstighraq;

    private int mAsl;
    private int mAshom;
    private int mBaqi;
    private int mAdadRo2os;
    private int mAwl;
    private int mMissah;
    private String mSpecialCase;

    private enum Naw3 {  // TODO public or package private
        NAW3_NONE,
        NAW3_ADILA,
        NAW3_RAD,
        NAW3_AWL,
    };
    private Naw3 mNaw3;

    public Massala() {
        reset();
    }

    public void reset() {
        mWarathah = new ArrayList<Mirath>();
        mMahjoobin = new ArrayList<Mirath>();

        mSpecialCase = null;
        mAsl = 1;
        mAshom = 0;
        mAdadRo2os = 1;
        mAwl = 0;

        mHal = false;
        mFardh = false;
        mTa3seeb = false;
        mIstighraq = false;

        mNaw3 = Naw3.NAW3_NONE;
    }

    public void addMirath(Mirath m) {
        mWarathah.add(m);
    }

    public void addHajb(Warith warith, int nw, String hajib, Warith hajib2, int nh)
    {
        for(Mirath m : mMahjoobin) {
            if (warith == m.getWarith()) {
                m.addHajib((hajib != null) ? hajib : hajib2.getName(nh));
                return;
            }
        }
        String hajb = warith.getName(nw) + " حجب";
        if (hajib != null) {
            // ALAB below is for male tense only
            hajb += warith.getDhamirHajaba(nw, Warith.ALAB) + " " + hajib;
        } else {
            hajb += warith.getDhamirHajaba(nw, hajib2) + " " + hajib2.getName(nh);
        }
        mMahjoobin.add(new Mirath(warith, hajb));
    }

    public void addHajb(Warith warith, int nw, Warith hajib2, int nh)
    {
        addHajb(warith, nw, null, hajib2, nh);
    }

    public void addHajb(Warith warith, int nw, String hajib)
    {
        addHajb(warith, nw, hajib, null, 1);
    }

    public void addHajb(Warith warith, int nw, Warith hajib)
    {
        addHajb(warith, nw, hajib, 1);
    }

    public void addHajb(Warith warith, Warith hajib)
    {
        addHajb(warith, 1, hajib, 1);
    }

    public void calcHal() {

        // TODO assert none of the enums is both in mWarathah & mMahjoobin

        calcAslAndRo2os();

    }

    public void calcAslAndRo2os() {
        // استخراج أصل المسألة (ص 40 من كتاب "الفرائض المُيسر) وعدد رؤوس ورثة الباقي"
        for (Mirath m : mWarathah) {
            if (m.isFardh()) {
                mAsl = lcm(mAsl, m.getMaqam());
                if (!mFardh) {
                    mFardh = true;
                }
            }
            if (m.isTa3seeb()) {
                if ((m.getRo2os() != 1) && (mAdadRo2os == 1)) {
                    mAdadRo2os = m.getRo2os();
                }
                if (!mTa3seeb) {
                    mTa3seeb = true;
                }
            }
        }
    }

    public void calcAshomAndBaqi() {
        // حساب مجموع أسهم أصحاب الفروض وباقي المسألة
        for (Mirath m : mWarathah) {
            if (m.isFardh()) {
                int fardh = m.getBast() * mAsl / m.getMaqam();
                m.setFardh(fardh);
                mAshom += fardh;
            }
        }
        mBaqi = mAsl - mAshom;
        if (mBaqi == 0) {
            if (mFardh) {
                mNaw3 = Naw3.NAW3_ADILA;
            }
            if (mTa3seeb) {
                mIstighraq = true;
            }
        }
        else if (mBaqi > 0) { // مسألة فيها باقي
            if (mTa3seeb) {
                // TODO ADILA here?
                // TODO mBaqi % mAdadRo2os != 0
            }
            else if (mAshom != 0) {                           // مسألة ناقصة فيها رد
                mNaw3 = Naw3.NAW3_RAD;
                // TODO rad albaqi
            }
        } else {                                // مسألة عائلة
            mNaw3 = Naw3.NAW3_AWL;
            mAwl = mAshom;
            if (mTa3seeb) {
                mIstighraq = true;
            }
            // TODO  XXX mBaqi < 0
        }
    }

    public void calcQissma() {
        // قسمة الأسهم في الجدول
        int sahm;
        ArrayList<Mirath> all = new ArrayList<Mirath>();

        all.addAll(mWarathah);
        all.addAll(mMahjoobin);
        for (Mirath m : all) {          // Here be dragons
            if (m.isFardh()) {
                if (m.isTa3seeb()) {
                    // TODO assert m.getBast() == 1
                    if (m.isTholuthAlbaqi()) {
                        // الأم في الغراوين (ص 19) سهمها دوما 1
                        sahm = 1;
                        // TODO تفصيل الجد مع الإخوة في حال أخذ الجد ثلث الباقي بالمفاضلة يجب حسابه
                    }
                    else {                     // حالات الأب والجد يرثان 1\6 + ب
                        sahm = m.getFardh();
                        if (mBaqi > 0) {
                            sahm += mBaqi;
                        }
                    }
                }
                else {
                    sahm = m.getFardh();
                }
            }
            else if (m.isTa3seeb()) {
                sahm = (mBaqi > 0) ? mBaqi : 0;
            }
            else if (m.mahjoob()) {
                sahm = 0;
            }
            else {
                System.out.println(String.format("BUG! %s خطأ وارث ليس صاحب فرض أو تعصيب أو حجب", m.getWarith().getName()));
                continue;
            }

            m.setSahm(sahm);
        }
    }

    public String getTafsir() {
        StringBuilder tafsir = new StringBuilder();

        if (mWarathah != null) {
            for (Mirath m : mWarathah) {
                tafsir.append("- " + m.getTafsir() + ".\n");
            }
        }

        if (mMahjoobin != null) {
            for (Mirath m : mMahjoobin) {
                tafsir.append("- " + m.getTafsir() + ".\n");
            }
        }

        if (mSpecialCase != null /* TODO غراوين */) {
            tafsir.append(mSpecialCase + "\n");
        }

        tafsir.append("\n" + "- أصل المسألة: " + mAsl + "\n");
        switch (mNaw3) {
            case NAW3_ADILA:
                tafsir.append("- المسألة عادلة (تساوى أصلها مع أسهمها).\n");
                break;
            case NAW3_RAD:
                tafsir.append(String.format("- المسألة ناقصة (أسهمها أقل من أصلها) فيرد الباقي %d على أصحاب الفروض عدى الزوجين\n", mBaqi));
                break;
            case NAW3_AWL:
                tafsir.append(String.format("- المسألة عائلة (أسهمها أكثر من أصلها)، تعول إلى %d\n", mAshom));
            default:
                break;
        }

        if (mTa3seeb) {
            if (mIstighraq) {
                tafsir.append("- استغرق أصحاب الفروض الأسهم ولم يبق للورثة بالتعصيب شيء.\n");
            } else {
                tafsir.append(String.format("- الباقي %d يُُقسم على: %d رؤوس\n", mBaqi, mAdadRo2os));
            }
        }

        return tafsir.toString();
    }

    public String getTable() {
        // رسم الجدول
        StringBuilder table = new StringBuilder();

        table.append("\u200e┌──────┐\n");       // https://en.wikipedia.org/wiki/Box-drawing_character
        if (mAwl != 0) {
            table.append(String.format("\u200e│%-6d│\n", mAwl));
            table.append(String.format("\u200e├──────┤\n", mAwl));
            table.append(String.format("\u200e│\u200fع\u200e%-5d│\n", mAsl));  // TODO strikethrough
        } else {
            table.append(String.format("\u200e│%-6d│\n", mAsl));
        }
        table.append(String.format("\u200e├──────┼──────────┬──────┐\n"));

        ArrayList<Mirath> all = new ArrayList<Mirath>();
        all.addAll(mWarathah);
        all.addAll(mMahjoobin);
        int i = 0;
        for (Mirath m : all) {
            i++;
            StringBuilder col_name = new StringBuilder();
            StringBuilder col_wirth = new StringBuilder();
            StringBuilder col_sahm = new StringBuilder();

            col_name.append(m.getWarith().getShortName());
            if (m.getNbr() > 1) {
                col_name.append("\u200f\\" + m.getNbr());
            }

            // Here be dragons
            if (m.isFardh()) {
                col_wirth.append(String.format("\u200f%d\\%d", m.getBast(), m.getMaqam()));
                if (m.isTa3seeb()) {
                    if (!m.isTholuthAlbaqi()) {
                        col_wirth.append("\u200f + ");
                        if (mBaqi > 0) {
                            col_sahm.append(m.getFardh() + " + " + mBaqi);
                            if (m.isShirka()) {
                                col_sahm.append("\u200fش");
                            }
                        }
                    }
                    col_wirth.append("\u200fب");
                }
            }
            else if (m.isTa3seeb()) {
                col_wirth.append("\u200fب");
                if (mBaqi > 0) {
                    col_sahm.append(mBaqi);
                    if (m.isShirka()) {
                        col_sahm.append("\u200fش");
                    }
                }
            }
            else if (m.mahjoob()) {
                col_wirth.append("\u200fم");
                col_sahm.append("\u200f--");
            }

            if (col_sahm.length() == 0) {
                col_sahm.append(m.getSahm());
            }

            table.append(String.format("\u200e│\u200f%7s\u200e│\u200f%10s\u200e│\u200f%7s\u200e│\n", col_sahm, col_name, col_wirth));
            if (i == all.size()) {
                table.append("\u200e└──────┴──────────┴──────┘\n");
            } else {
                table.append("\u200e├──────┼──────────┼──────┤\n");
            }
        }

        return table.toString();
    }

    public boolean noInput() {
        return mWarathah.size() == 0;
    }

    public boolean isHal() {
        return mHal;
    }

    public boolean isFardh() {
        return mFardh;
    }

    public void setFardh(boolean fardh) {
        this.mFardh = fardh;
    }

    public boolean isTa3seeb() {
        return mTa3seeb;
    }

    public void setTa3seeb(boolean ta3seeb) {
        this.mTa3seeb = ta3seeb;
    }

    public int getAsl() {
        return mAsl;
    }

    public void setAsl(int asl) {
        this.mAsl = asl;
    }

    public int getAshom() {
        return mAshom;
    }

    public void setAshom(int ashom) {
        this.mAshom = ashom;
    }

    public int getmBaqi() {
        return mBaqi;
    }

    public void setBaqi(int baqi) {
        this.mBaqi = baqi;
    }

    public int getAwl() {
        return mAwl;
    }

    public void setAwl(int awl) {
        this.mAwl = awl;
    }

    public int getMissah() {
        return mMissah;
    }

    public void setMissah(int missah) {
        this.mMissah = missah;
    }

    public String getmSpecialCase() {
        return mSpecialCase;
    }

    public void setmSpecialCase(String specialCase) {
        this.mSpecialCase = specialCase;
    }

    public int getmAdadRo2os() {
        return mAdadRo2os;
    }

    public void setmAdadRo2os(int adadRo2os) {
        this.mAdadRo2os = adadRo2os;
    }

    // TODO move to Utilities clas
    private int lcm(int a, int b) {
        if (a == 0 || b == 0) {
            return 0;
        }
        int aa = Math.abs(a);
        int ab = Math.abs(b);
        int h = Math.max(aa, ab);
        int l = Math.min(aa, ab);
        int lcm = h;
        while (lcm % l != 0) {
            lcm += h;
        }
        return lcm;
    }
}
