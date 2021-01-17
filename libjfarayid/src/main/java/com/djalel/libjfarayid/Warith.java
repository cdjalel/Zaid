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

public enum Warith {
    // order matters
    ALAB("الأب", "الأب", "الأب", "أب"),
    ALOM("الأم", "الأم", "الأم", "أم"),
    ALJAD("الجد", "الجد", "الجد", "جد"),
    ALJADAH_LI_AB("الجدة لأب", "الجدة لأب", "الجدة لأب", "جدة ب"),
    ALJADAH_LI_OM("الجدة لأم", "الجدة لأم", "الجدة لأم", "جدة م"),
    AZAWJ("الزوج", "الزوج", "الزوج", "زوج"),
    AZAWJAT("الزوجة", "الزوجتان", "الزوجات", "زوجة"),
    ALABNA("الابن", "الإبنان", "الأبناء", "ابن"),
    ALBANAT("البنت", "البنتان", "البنات", "بنت"),
    ABNA_ALABNA("ابن الابن", "ابنا الابن", "أبناء الأبناء", "ابن ابن"),
    BANAT_ALABNA("بنت الابن", "بنتا الابن", "بنات الأبناء", "بنت ابن"),
    ALIKHWA_LI_OM("الأخ لأم", "الأخوان لأم", "الإخوة لأم", "خ م"),
    ALAKHAWAT_LI_OM("الأخت لأم", "الأختان لأم", "الأخوات لأم", "خت م"),
    ALIKHWA_ALASHIKA("الأخ شقيق", "الأخوان الشقيقان", "الإخوة الأشقاء", "خ ش"),
    ALAKHAWAT_ASHAKIKAT("الأخت الشقيقة", "الأختان الشقيقتان", "الأخوات الشقيقات", "خت ش"),
    ALIKHWA_LI_AB("الأخ لأب", "الأخوان لأب", "الإخوة لأب", "خ ب"),
    ALAKHAWAT_LI_AB("الأخت لأب", "الأختان لأب", "الأخوات لأب", "خت ب"),
    ABNA_ALIKHWA_ALASHIKA("ابن الأخ الشقيق", "ابنا الأخ الشقيق", "أبناء الإخوة الأشقاء", "ابن خ ش"),
    ABNA_ALIKHWA_LI_AB("ابن الأخ لأب", "ابنا الأخ لأب", "أبناء الإخوة لأب", "ابن خ ب"),
    ALA3MAM_ALASHIKA("العم الشقيق", "العمان الشقيقان", "الأعمام الأشقاء", "عم ش"),
    ALA3MAM_LI_AB("العم لأب", "العمان لأب", "الأعمام لأب", "عم ب"),
    ABNA_ALA3MAM_ALASHIKA("ابن العم الشقيق", "ابنا العم الشقيق", "أبناء الأعمام الأشقاء", "ابن عم ش"),
    ABNA_ALA3MAM_LI_AB("ابن العم لأب", "ابنا العم لأب", "أبناء الأعمام لأب", "ابن عم ب");

    private final String mofrad;
    private final String mothana;
    private final String djam3;
    private final String short_name;

    Warith(String mofrad, String mothana, String djam3, String short_name) {
        this.mofrad = mofrad;
        this.mothana = mothana;
        this.djam3 = djam3;
        this.short_name = short_name;
    }

    public final String getName() {
        return mofrad;
    }

    public final String getName(int n) {
        return n == 1 ? mofrad : (n == 2 ? mothana : djam3);
    }

    public final String getShortName() {
        return short_name;
    }

    public final String getVerb(int n, boolean shirka) {
        switch (this) {
            case ALAB:
            case AZAWJ:
                return "يرث";

            case ALOM:
                return "ترث";

            case ALJAD:
                return /* assert n == 1  &&*/ !shirka? "يرث" : "يشترك";


            case ALJADAH_LI_AB:
            case ALJADAH_LI_OM:
                return /* assert n == 1  &&*/ !shirka? "ترث" : "تشترك";

            case AZAWJAT:
            case ALBANAT:
            case BANAT_ALABNA:
            case ALAKHAWAT_LI_OM:
            case ALAKHAWAT_ASHAKIKAT:
            case ALAKHAWAT_LI_AB:
                return n == 1 ? (shirka? "تشترك" : "ترث") : (n == 2 ? "تشتركان" : "تشتركن");

            case ALABNA:
            case ABNA_ALABNA:
            case ALIKHWA_LI_OM:
            case ALIKHWA_ALASHIKA:
            case ALIKHWA_LI_AB:
            case ABNA_ALIKHWA_ALASHIKA:
            case ABNA_ALIKHWA_LI_AB:
            case ALA3MAM_ALASHIKA:
            case ALA3MAM_LI_AB:
            case ABNA_ALA3MAM_ALASHIKA:
            case ABNA_ALA3MAM_LI_AB:
            default:
                return n == 1 ? (shirka? "يشترك" : "يرث")  : (n == 2 ? "يشتركان" : "يشتركون");
        }
    }

    public final String getVerb() {
        return getVerb(1, false);
    }

    public final String getDhamirHajaba(int n, Warith hajib) {
        String fa3el;                  // argument, hajib
        String maf3oul;                // this object, mahjoob

        switch (hajib) {
            case ALOM:                  // assert this == ALJADA_LI_OM || ALJADA__LI_AB
            case ALAKHAWAT_ASHAKIKAT:   // assert this == ALAKHAWAT_LI_OM, ALAKHAWAT_LI_AB
                fa3el = "ت";
                break;
            default:
                fa3el = "";
                break;
        }

        maf3oul = getDhamir(n);
        return fa3el + maf3oul;
    }

    public String getDhamir(int n) {
        String dhamir;
        if (n == 2) {
            dhamir = "هما";
        }
        else {
            switch (this) {
                case ALAB:
                case ALJAD:
                case AZAWJ:
                    dhamir = "ه";
                    break;
                case ALOM:
                case ALJADAH_LI_AB:
                case ALJADAH_LI_OM:
                    dhamir = "ها";
                    break;
                case AZAWJAT:
                case ALBANAT:
                case BANAT_ALABNA:
                case ALAKHAWAT_LI_OM:
                case ALAKHAWAT_ASHAKIKAT:
                case ALAKHAWAT_LI_AB:
                    dhamir = n == 1 ? "ها" : "هن";
                    break;
                case ALABNA:
                case ABNA_ALABNA:
                case ALIKHWA_LI_OM:
                case ALIKHWA_ALASHIKA:
                case ALIKHWA_LI_AB:
                case ABNA_ALIKHWA_ALASHIKA:
                case ABNA_ALIKHWA_LI_AB:
                case ALA3MAM_ALASHIKA:
                case ALA3MAM_LI_AB:
                case ABNA_ALA3MAM_ALASHIKA:
                case ABNA_ALA3MAM_LI_AB:
                    dhamir = n == 1 ? "ه" : "هم";
                    break;

                default:
                    dhamir = "";
                    break;
            }
        }
        return dhamir;
    }

    public final String getSharhPrefix(int n, Warith ma3a, int n2, boolean tassawi) {
        StringBuilder prefix = new StringBuilder();

        prefix.append(getName(n));
        prefix.append(" ");
        prefix.append(getVerb(n, true));
        prefix.append(" مع ");
        prefix.append(ma3a.getName(n2));
        prefix.append(tassawi ? " (بالتساوي)" : " (للذكر مثل حظ الأنثيين)");
        prefix.append(" في ");

        return prefix.toString();
    }
    public final String getSharhPrefix(int n, Warith ma3a, int n2) {
        return getSharhPrefix(n, ma3a, n2, false);
    }

    public final String getAlikhwa(WarathahInput input) {
        int n1 = input.get_alikhwa_alashika();
        int n2 = input.get_alakhawat_ashakikat();
        int n3 = input.get_alikhwa_li_ab();
        int n4 = input.get_alakhawat_li_ab();

        StringBuilder ma3a = new StringBuilder();
        if (n1 > 0) {  ma3a.append(ALIKHWA_ALASHIKA.getName(n1)); }
        if (n2 > 0) {  if (n1 > 0) { ma3a.append(" و"); } ma3a.append(ALAKHAWAT_ASHAKIKAT.getName(n2)); }
        if (n3 > 0) {  if ((n1 + n2) > 0) { ma3a.append(" و"); } ma3a.append(ALIKHWA_LI_AB.getName(n3)); }
        if (n4 > 0) {  if ((n1 + n2 + n3) > 0) { ma3a.append(" و"); } ma3a.append(ALAKHAWAT_LI_AB.getName(n4)); }

        return ma3a.toString();
    }

    public final String getSharhMa3aAlikhwaPrefix(WarathahInput input) {
        StringBuilder prefix = new StringBuilder();

        prefix.append(getName(1));
        prefix.append(" ");
        prefix.append(getVerb(1, false));
        prefix.append(" مع ");
        prefix.append(getAlikhwa(input));
        prefix.append(" الأحظ له وهو ");

        return prefix.toString();
    }

    public final String getSharhPrefix(int n, boolean tassawi) {
        StringBuilder prefix = new StringBuilder();

        prefix.append(getName(n));
        prefix.append(" ");
        prefix.append(getVerb(n, false));
        prefix.append(" ");
        if (n > 1) {
            if (tassawi) {
                prefix.append("(بالتساوي) ");
            }
            prefix.append(" في ");
        }

        return prefix.toString();
    }

    public final String getSharhPrefix(int n) {
        return getSharhPrefix(n, true);
    }

    public final String getSharhPrefix() {
        return getSharhPrefix(1);
    }

    public boolean isThakar() {
        switch (this) {
            case AZAWJ:
            case ALAB:
            case ALJAD:
            case ALABNA:
            case ABNA_ALABNA:
            case ALIKHWA_LI_OM:
            case ALIKHWA_ALASHIKA:
            case ALIKHWA_LI_AB:
            case ABNA_ALIKHWA_ALASHIKA:
            case ABNA_ALIKHWA_LI_AB:
            case ALA3MAM_ALASHIKA:
            case ALA3MAM_LI_AB:
            case ABNA_ALA3MAM_ALASHIKA:
            case ABNA_ALA3MAM_LI_AB:
                return true;
            case ALOM:
            case AZAWJAT:
            case ALJADAH_LI_AB:
            case ALJADAH_LI_OM:
            case ALBANAT:
            case BANAT_ALABNA:
            case ALAKHAWAT_LI_OM:
            case ALAKHAWAT_ASHAKIKAT:
            case ALAKHAWAT_LI_AB:
            default:
                return false;
        }
    }

    public boolean isOntha() { return !isThakar();}
}