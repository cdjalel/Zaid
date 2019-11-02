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

import android.app.Application;

public class ZaidApplication extends Application {

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

    private String mirath_alab;
    private String mirath_alom;
    private String mirath_aljad;
    private String mirath_aljadah_li_ab;
    private String mirath_aljadah_li_om;
    private String mirath_azawj;
    private String mirath_azawjat;
    private String mirath_alabna;
    private String mirath_albanat;
    private String mirath_abna_alabna;
    private String mirath_banat_alabna;
    private String mirath_alikhwa_li_om;
    private String mirath_alakhawat_li_om;
    private String mirath_alikhwa_alashika;
    private String mirath_alakhawat_ashakikat;
    private String mirath_alikhwa_li_ab;
    private String mirath_alakhawat_li_ab;
    private String mirath_abna_alikhwa_alashika;
    private String mirath_abna_alikhwa_li_ab;
    private String mirath_ala3mam_alashika;
    private String mirath_ala3mam_li_ab;
    private String mirath_abna_ala3mam_alashika;
    private String mirath_abna_ala3mam_li_ab;

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

    public boolean has_alab() {
        return alab;
    }

    public void set_alab(boolean b) {
        alab = b;
    }

    public boolean has_alom() {
        return alom;
    }

    public void set_alom(boolean b) {
        alom = b;
    }

    public boolean has_aljad() {
        return aljad;
    }

    public void set_aljad(boolean b) {
        aljad = b;
    }

    public boolean has_aljadah_li_ab() {
        return aljadah_li_ab;
    }

    public void set_aljadah_li_ab(boolean b) {
        aljadah_li_ab = b;
    }

    public boolean has_aljadah_li_om() {
        return aljadah_li_om;
    }

    public void set_aljadah_li_om(boolean b) {
        aljadah_li_om = b;
    }

    public boolean has_zawj() {
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

    public int get_ala3mam_alashika() {
        return ala3mam_alashika;
    }

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

    public int get_abna_ala3mam_li_ab() {
        return abna_ala3mam_li_ab;
    }

    private void resetMawarith()
    {
        mirath_alab = null;
        mirath_alom = null;
        mirath_aljad = null;
        mirath_aljadah_li_ab = null;
        mirath_aljadah_li_om = null;
        mirath_azawj = null;
        mirath_azawjat = null;
        mirath_alabna = null;
        mirath_albanat = null;
        mirath_abna_alabna = null;
        mirath_banat_alabna = null;
        mirath_alikhwa_li_om = null;
        mirath_alakhawat_li_om = null;
        mirath_alikhwa_alashika = null;
        mirath_alakhawat_ashakikat = null;
        mirath_alikhwa_li_ab = null;
        mirath_alakhawat_li_ab = null;
        mirath_abna_alikhwa_alashika = null;
        mirath_abna_alikhwa_li_ab = null;
        mirath_ala3mam_alashika = null;
        mirath_ala3mam_li_ab = null;
        mirath_abna_ala3mam_alashika = null;
        mirath_abna_ala3mam_li_ab = null;
    }

    private String hajb(String warith, String dhamir, String hajib)
    {
        return warith + " حجب" + dhamir + " " + hajib;
    }

    private String hajb(String warith, String dhamir, String hajib, String old)
    {
        if (old == null) {
            return hajb(warith, dhamir, hajib);
        }
        else {
            return old + " و " + hajib;
        }
    }

    public String calculate_furudh() 
    {
        //boolean assl = alab || aljad;
        boolean far3_wareth_dhakar = (alabna > 0) || (abna_alabna > 0);
        boolean far3_wareth_ontha = (albanat > 0) || (banat_alabna > 0);
        boolean far3_wareth = far3_wareth_dhakar || far3_wareth_ontha;
        boolean jam3_alikhwa = (alikhwa_alashika + alikhwa_li_ab + alikhwa_li_om + 
                                alakhawat_ashakikat + alakhawat_li_ab + alakhawat_li_om) > 1;
        boolean ikhwa_ma3a_aljad = (alikhwa_alashika + alikhwa_li_ab) >= 1;
        int walad_alom = alikhwa_li_om + alakhawat_li_om;

        resetMawarith();

        // mirath azzawj
        if (zawj)
        {
            mirath_azawj = "الزوج يرث  " + 
                           (far3_wareth ? "الربع 1/4" : "النصف 1/2") +
                           " فرضا";
        }

        // mirath azzawjat
        if (azawjat >= 1)
        {
            switch(azawjat) {
                case 1:
                    mirath_azawjat = "الزوجة ترث ";
                    break;
                case 2:
                    mirath_azawjat = "الزوجتان تشتركان بالتساوي في ";
                    break;
                default:
                    mirath_azawjat = "الزوجات تشتركن بالتساوي في ";
                    break;
            }
            mirath_azawjat += (far3_wareth ? "الثمن 1/8" : "الربع 1/4") + " فرضا";
        }

        // mirath alab
        if (alab)
        {
            mirath_alab = "الأب يرث ";
            if (far3_wareth_dhakar)
            {
                mirath_alab += "السدس 1/6 فرضا فقط";
            }
            else if (far3_wareth_ontha)
            {
                mirath_alab += "السدس 1/6 فرضا + الباقي تعصيبا";
            }
            else
            {
                mirath_alab += "الباقي تعصيبا";
            }

            // alhajb
            if (aljad)
            {
                mirath_aljad = hajb("الجد", "ه", "الأب");
            }

            if (madhab != Madhab.HAMBALI)
            {
                if (aljadah_li_ab)
                {
                    mirath_aljadah_li_ab = hajb("الجدة لأب", "ها", "الأب المدلية به");
                }
            }

            if (alikhwa_alashika >= 1)
            {
                mirath_alikhwa_alashika = hajb("الأخوة الأشقاء", "هم", "الأب");
            }

            if (alakhawat_ashakikat >= 1)
            {
                mirath_alakhawat_ashakikat = hajb("الأخوات ااشقيقات", "هن", "الأب");
            }

            if (alikhwa_li_om >= 1)
            {
                mirath_alikhwa_li_om = hajb("الأخوة لأم", "هم", "الأب");
            }

            if (alakhawat_li_om >= 1)
            {
                mirath_alakhawat_li_om = hajb("الأخوات لأم", "هن", "الأب");
            }

            if (alikhwa_li_ab >= 1)
            {
                mirath_alikhwa_li_ab = hajb("الأخوة لأب", "هم", "الأب");
            }

            if (alakhawat_li_ab >= 1)
            {
                mirath_alakhawat_li_ab = hajb("الأخوات لأب", "هن", "الأب");
            }

            if (abna_alikhwa_alashika >= 1)
            {
                mirath_abna_alikhwa_alashika = hajb("أبناء الإخوة الأشقاء", "هم", "الأب");
            }

            if (abna_alikhwa_li_ab >= 1)
            {
                mirath_abna_alikhwa_li_ab = hajb("أبناء الإخوة لأب", "هم", "الأب");
            }

            if (ala3mam_alashika >= 1)
            {
                mirath_ala3mam_alashika = hajb("الأعمام الأشقاء", "هم", "الأب");
            }

            if (ala3mam_li_ab >= 1)
            {
                mirath_ala3mam_li_ab = hajb("الأعمام لأب", "هم", "الأب");
            }

            if (abna_ala3mam_alashika >= 1)
            {
                mirath_abna_ala3mam_alashika = hajb("أبناء الأعمام الأشقاء", "هم", "الأب");
            }

            if (abna_ala3mam_li_ab >= 1)
            {
                mirath_abna_ala3mam_li_ab = hajb("أبناء الأعمام لأب", "هم", "الأب");
            }
        }

        // mirath aljad
        if (aljad)
        {
            if (mirath_aljad == null) // no hajb by ab
            {
                mirath_aljad = "الجد يرث ";
                if (far3_wareth_dhakar)
                {
                    mirath_aljad = "السدس 1/6 فرضا فقط"; 
                }
                else if (far3_wareth_ontha)
                {
                    mirath_aljad = "السدس 1/6 فرضا + الباقي تعصيبا";
                }
                else if (!ikhwa_ma3a_aljad)
                {
                    mirath_aljad = "الباقي تعصيبا";
                }
                else
                {
                    mirath_aljad = "مقاسمة مع الإخوة تعصيبا"; // TODO? التساوي
                }
            }

            // alhajb
            if (alikhwa_li_om >= 1)
            {
                mirath_alikhwa_li_om = hajb("الأخوة لأم", "هم", "الجد", mirath_alikhwa_li_om);
            }

            if (alakhawat_li_om >= 1)
            {
                mirath_alakhawat_li_om = hajb("الأخوات لأم", "هن", "الجد", mirath_alakhawat_li_om);
            }

            if (abna_alikhwa_alashika >= 1)
            {
                mirath_abna_alikhwa_alashika = hajb("أبناء الإخوة الأشقاء", "هم", "الجد", mirath_abna_alikhwa_alashika);
            }

            if (abna_alikhwa_li_ab >= 1)
            {
                mirath_abna_alikhwa_li_ab = hajb("أبناء الإخوة لأب", "هم", "الجد", mirath_abna_alikhwa_li_ab);
            }

            if (ala3mam_alashika >= 1)
            {
                mirath_ala3mam_alashika = hajb("الأعمام الأشقاء", "هم", "الجد", mirath_ala3mam_alashika);
            }

            if (ala3mam_li_ab >= 1)
            {
                mirath_ala3mam_li_ab = hajb("الأعمام لأب", "هم", "الجد", mirath_ala3mam_li_ab);
            }

            if (abna_ala3mam_alashika >= 1)
            {
                mirath_abna_ala3mam_alashika = hajb("أبناء الأعمام الأشقاء", "هم", "الجد", mirath_abna_ala3mam_alashika);
            }

            if (abna_ala3mam_li_ab >= 1)
            {
                mirath_abna_ala3mam_li_ab = hajb("أبناء الأعمام لأب", "هم", "الجد", mirath_abna_ala3mam_li_ab);
            }
        }

        // mirath alom
        if (alom)
        {
            mirath_alom = "الأم ترث ";
            if (jam3_alikhwa || far3_wareth)
            {
                mirath_alom += "السدس 1/6 فرضا";
            }
            else
            {
                mirath_alom += "الثلث 1/3 فرضا";
            }

            if ((alab) && ((azawjat > 0) || (zawj)) && !far3_wareth)
            {
                mirath_alom = "الأم ترث ثلث 1/3 الباقي (مسألة الغرّاوين)";
            }

            // alhajb
            if (aljadah_li_ab)
            {
                mirath_aljadah_li_ab = hajb("الجدة لأب", "تها", "الأم", mirath_aljadah_li_ab);
            }

            if (aljadah_li_om)
            {
                mirath_aljadah_li_om = hajb("الجدة لأم", "تها", "الأم", mirath_aljadah_li_om);
            }
        }
        else
        {
            // mirath aljadat
            if (aljadah_li_ab && (mirath_aljadah_li_ab == null))
            {
                mirath_aljadah_li_ab = "الجدة لأب ";
                if (aljadah_li_om)
                {
                    mirath_aljadah_li_ab += "تشترك بالتساوي مع الجدة لأم في السدس 1/6 فرضا";
                }
                else
                {
                    mirath_aljadah_li_ab += "ترث السدس 1/6 فرضا";
                }
            }

            if (aljadah_li_om)
            {
                mirath_aljadah_li_om = "الجدة لأم ";
                if ((mirath_aljadah_li_ab == null) || mirath_aljadah_li_ab.startsWith("حجب")) 
                {
                    mirath_aljadah_li_om += "ترث السدس 1/6 فرضا";
                }
                else
                {
                    mirath_aljadah_li_om += "تشترك بالتساوي مع الجدة لأب في السدس 1/6 فرضا";
                }
            }
        }

        // mirath alikhwa li om
        if ((walad_alom > 0) /* && (mirath_alikhwa_li_om == null) && (alakhawat_li_om == null) */)
        {
            if (far3_wareth)
            {
                if (alikhwa_li_om > 0)
                {
                    mirath_alikhwa_li_om = hajb("الأخوة لأم", "هم", "الفرع الوارث", mirath_alikhwa_li_om);
                }

                if (alakhawat_li_om > 0)
                {
                    mirath_alakhawat_li_om = hajb("الأخوات لأم", "هن", "الفرع الوارث", mirath_alakhawat_li_om);
                }
            }
            else
            {
                if (walad_alom > 1)
                {
                    if (alikhwa_li_om > 0)
                    {
                        String warith;
                        String dhamir;
                        if (alikhwa_li_om == 1)
                        {
                            warith = "الأخ لأم";
                            dhamir = "";
                        }
                        else
                        {
                            warith = "الأخوة لأم";
                            dhamir = "ون";
                        }
                        mirath_alikhwa_li_om = warith + " يشترك" + dhamir + " بالتساوي مع أولاد الأم في الثلث 1/3 فرضا";
                    }

                    if (alakhawat_li_om > 0)
                    {
                        String warith;
                        String dhamir;
                        if (alakhawat_li_om == 1)
                        {
                            warith = "الأخت لأم";
                            dhamir = "";
                        }
                        else
                        {
                            warith = "الأخوات لأم";
                            dhamir = "ون";
                        }
                        mirath_alakhawat_li_om = warith + " تشترك" + dhamir + " بالتساوي مع أولاد الأم في الثلث 1/3 فرضا";
                    }
                }
                else if (alikhwa_li_om == 1)
                {
                    mirath_alikhwa_li_om = "الأخ لأم يرث السدس 1/6 فرضا";
                }
                else  // alakhawat_li_om == 1
                {
                    mirath_alakhawat_li_om = "الأخت لأم ترث السدس 1/6 فرضا";
                }
            }
        }

        // mirath albanat ma3a 3adam wojud alabna
        if (alabna == 0)
        {
            if (albanat == 1)
            {
                mirath_albanat = "البنت ترث النصف 1/2 فرضا";

                if (abna_alabna == 0) 
                {
                    if (banat_alabna == 1)
                    {
                        mirath_banat_alabna = "بنت الإبن ترث السدس 1/6 تتمة الثلثين فرضا";
                    }
                    else if (banat_alabna > 1)
                    {
                        mirath_banat_alabna = "بنات الإبن تشتركن بالتساوي في السدس 1/6 تتمة الثلثين فرضا";
                    }
                }
            }
            else if (albanat >= 2)
            {
                mirath_albanat = "البنات تشتركن بالتساوي في الثلثين 2/3 فرضا";

                if ((abna_alabna == 0) && (banat_alabna >= 1))
                {
                    String dhamir = (banat_alabna == 1) ? "ها" : "هن";
                    mirath_banat_alabna = hajb("بنات الأبناء", dhamir, "الجمع من البنات");
                }
            }
            else // albanat == 0
            {
                // same for 2nd generation
                if (abna_alabna == 0)
                {
                    if (banat_alabna == 1)
                    {
                        mirath_banat_alabna = "بنت الإبن ترث النصف 1/2 فرضا";
                    }
                    else if (banat_alabna >= 2)
                    {
                        mirath_banat_alabna = "بنات الأبناء تشتركن بالتساوي في الثلثين 2/3 فرضا";
                    }
                }
            }
        }

        // mirath alakhawat ashakikat 3inda 3adam wojud alikhwa alashika
        if ((alakhawat_ashakikat > 0) && !alab && !far3_wareth_dhakar && (alikhwa_alashika == 0))
        {
            if (!far3_wareth_ontha)
            {
                if (alakhawat_ashakikat == 1)
                {
                    mirath_alakhawat_ashakikat = "الأخت الشقيقة ترث النصف 1/2 فرضا";
                }
                else // (alakhawat_ashakikat >= 2)
                {
                    mirath_alakhawat_ashakikat = "الأخوات الشقيقات تشتركن بالتساوي في الثلثين 2/3 فرضا";
                }
            }
            else
            {
                if (alakhawat_ashakikat == 1)
                {
                    mirath_alakhawat_ashakikat = "الأخت الشقيقة تصير عصبة مع الغير";
                }
                else // (alakhawat_ashakikat >= 2)
                {
                    mirath_alakhawat_ashakikat = "الأخوات الشقيقات يصرن عصبة مع الغير";
                }    
            }
        }

        // mirath alakhawat li ab 3inda 3adam wojud alikhwa
        if ((alakhawat_li_ab > 0) && !alab && !far3_wareth_dhakar && (alikhwa_alashika == 0) && (alikhwa_li_ab == 0))
        {
            if (!far3_wareth_ontha)
            {
                if (alakhawat_ashakikat == 0)
                {
                    if (alakhawat_li_ab == 1)
                    {
                        mirath_alakhawat_li_ab = "الأخت لأب ترث النصف 1/2 فرضا";
                    }
                    else // (alakhawat_li_ab >= 2)
                    {
                        mirath_alakhawat_li_ab = "الأخوات لأب تشتركن بالتساوي في الثلثين 2/3 فرضا";
                    }
                }
                else if (alakhawat_ashakikat == 1)
                {
                    if (alakhawat_li_ab == 1)
                    {
                        mirath_alakhawat_li_ab = "الأخت لأب ترث السدس 1/6 تتمة الثلثين فرضا";
                    }
                    else // (alakhawat_li_ab >= 2)
                    {
                        mirath_alakhawat_li_ab = "الأخوات لأب تشتركن بالتساوي في السدس 1/6 تتمة الثلثين فرضا";
                    }
                }
                else
                {
                    String warith;
                    String dhamir;
                    String hajib = (alakhawat_ashakikat == 1) ? "الأخت الشقيقة" : "الأخوات الشقيقات";
                    if (alakhawat_li_ab == 1) 
                    {
                        warith = "الأخت لأب";
                        dhamir = "ها";
                    }
                    else 
                    {
                        warith = "الأخوات لأب";
                        dhamir = "هن";
                    }
                    mirath_alakhawat_li_ab = hajb(warith, dhamir, hajib, mirath_alakhawat_li_ab);
                }
            }
            else
            {
                String warith_v;
                String warith;
                String dhamir;
                if (alakhawat_li_ab == 1)
                {
                    warith_v = "الأخت لأب تصير";
                    warith = "الأخت لأب";
                    dhamir = "ها";
                }
                else
                {
                    warith_v = "الأخوات لأب يصرن";
                    warith = "الأخوات لأب";
                    dhamir = "هن";
                }

                if (alakhawat_ashakikat == 0)
                {
                    mirath_alakhawat_li_ab = warith_v + " عصبة مع الغير";
                }
                else
                {
                    String hajib = (alakhawat_ashakikat == 1) ?  "الأخت الشقيقة" : "الأخوات الشقيقات";
                    mirath_alakhawat_li_ab = hajb(warith, dhamir, hajib, mirath_alakhawat_li_ab);
                    //mirath_alakhawat_li_ab = hajb(dhamir, "alakhawat ashakikat sirna 3osba ma3a alghayr", mirath_alakhawat_li_ab);
                }
            }
        }

        String result = "";
        String[] warathah = {
            mirath_alab,
            mirath_alom,
            mirath_aljad,
            mirath_aljadah_li_ab,
            mirath_aljadah_li_om,
            mirath_azawj,
            mirath_azawjat,
            mirath_alabna,
            mirath_albanat,
            mirath_abna_alabna,
            mirath_banat_alabna,
            mirath_alikhwa_li_om,
            mirath_alakhawat_li_om,
            mirath_alikhwa_alashika,
            mirath_alakhawat_ashakikat,
            mirath_alikhwa_li_ab,
            mirath_alakhawat_li_ab,
            mirath_abna_alikhwa_alashika,
            mirath_abna_alikhwa_li_ab,
            mirath_ala3mam_alashika,
            mirath_ala3mam_li_ab,
            mirath_abna_ala3mam_alashika,
            mirath_abna_ala3mam_li_ab
        };

        for (int i = 0; i < warathah.length; i++) {
            if (warathah[i] != null)
                result += "- " + warathah[i] + ".\n";
        }

        return result;
    }
}
