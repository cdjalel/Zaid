/*
 * Copyright © 2019 Djalel Chefrour
 * Copyright © 2020 Haihtem Guefassa
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

import java.util.ArrayList;
import java.util.Map;

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

    ArrayList<Mirath> warathah;
    // maintained in the natural order of their keys (the order in which the enum constants are declared)
    private int asl_mas2ala;
    private int ashom;              //  مجموع أسهم أصحاب الفروض بما فيها الأم في الغراوين
    private int adad_ro2os;         // عدد رؤوس الورثة بالباقي (للذكر مثل حظ الأنثيين)
    private int awl;


    private String special_case;

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

    // TODO default constructor?

    private void resetMawarith()
    {
        warathah = new ArrayList<Mirath>();
        special_case = null;
        asl_mas2ala = 1;
        ashom = 0;
        adad_ro2os = 0;
        awl = 0;
    }

    private void hajb(Warith warith, int n, String hajib)
    {
        if (warathah != null) {
            for(Mirath m : warathah) {
                if (warith == m.getWarith()) {
                    m.addHajib(hajib);
                    return;
                }
            }
            String hajb = warith.getName(n) + " حجب" + warith.getDhamir(n) + " " + hajib;
            warathah.add(new Mirath(warith, hajb));
        }
    }

    public String calculate_furudh() {
        boolean far3_warith_dhakar = (alabna + abna_alabna) > 0;
        boolean far3_warith_ontha = (albanat + banat_alabna) > 0;
        boolean far3_warith = far3_warith_dhakar || far3_warith_ontha;
        boolean jam3_alikhwa = (alikhwa_alashika + alikhwa_li_ab + alikhwa_li_om +
                alakhawat_ashakikat + alakhawat_li_ab + alakhawat_li_om) > 1;
        boolean asl_warith_dhaker = alab || aljad;
        boolean far3_wa_asl_warith_dhaker = far3_warith_dhakar || asl_warith_dhaker;
        boolean alikhwa_alashika_wa_li_ab = (alikhwa_alashika + alikhwa_li_ab) > 0;
        boolean alikhwa_wa_abna_alikhwa = (alikhwa_alashika + alikhwa_li_ab + abna_alikhwa_alashika + abna_alikhwa_li_ab) > 0;
        boolean ala3mam = (ala3mam_alashika + ala3mam_li_ab) > 0;
        int awlad_alom = alikhwa_li_om + alakhawat_li_om;

        String tafsir, ism, hajib, dhamir;  // keep un-intialized so Studio warns us if they're not set in the code
        int bast, maqam, ro2os;
        boolean baqi;

        resetMawarith();

        // mirath azzawj
        if (zawj) {
            bast = 1;
            tafsir = Warith.AZAWJ.getTafsirPrefix();
            if (far3_warith) {
                maqam = 4;
                tafsir += "الربع 1\\4 فرضا";
            } else {
                maqam = 2;
                tafsir += "النصف 1\\2 فرضا";
            }
            warathah.add(new Mirath(Warith.AZAWJ, tafsir, bast, maqam));
        }

        // mirath azzawjat
        if (azawjat >= 1) {
            tafsir = Warith.AZAWJAT.getTafsirPrefix(azawjat);
            if (far3_warith) {
                maqam = 8;
                tafsir += "الثمن 1\\8 فرضا";
            } else {
                maqam = 4;
                tafsir += "الربع 1\\4 فرضا";
            }
            warathah.add(new Mirath(Warith.AZAWJAT, tafsir, 1, maqam, false, azawjat));
        }

        // mirath alab
        if (alab) {
            tafsir = Warith.ALAB.getTafsirPrefix();
            if (far3_warith_dhakar) {
                tafsir += "السدس 1\\6 فرضا فقط";
                bast = 1;
                maqam = 6;
                baqi = false;
            } else if (far3_warith_ontha) {
                tafsir += "السدس 1\\6 فرضا + الباقي تعصيبا بالنفس";
                bast = 1;
                maqam = 6;
                baqi = true;
            } else {
                tafsir += "الباقي تعصيبا بالنفس";
                bast = 0;
                maqam = 1;
                baqi = true;
            }
            warathah.add(new Mirath(Warith.ALAB, tafsir, bast, maqam, baqi));

            // alhajb bi alab
            hajib = Warith.ALAB.getName();
            if (aljad) {
                hajb(Warith.ALJAD, 1, hajib);
            }
            if (madhab != Madhab.HAMBALI) {
                // تسقط الجدة من جهة الأب بالأب، ولا تسقط الجدة من جهة الأم بالأب
                if (aljadah_li_ab) {
                    hajb(Warith.ALJADAH_LI_AB, 1, "الأب المدلية به");
                }
            }
            if (alikhwa_alashika >= 1) {
                hajb(Warith.ALIKHWA_ALASHIKA, alikhwa_alashika, hajib);
            }
            if (alakhawat_ashakikat >= 1) {
                hajb(Warith.ALAKHAWAT_ASHAKIKAT, alakhawat_ashakikat, hajib);
            }
            if (alikhwa_li_om >= 1) {
                hajb(Warith.ALIKHWA_LI_OM, alikhwa_li_om, hajib);
            }
            if (alakhawat_li_om >= 1) {
                hajb(Warith.ALAKHAWAT_LI_OM, alakhawat_li_om, hajib);
            }
            if (alikhwa_li_ab >= 1) {
                hajb(Warith.ALIKHWA_LI_AB, alikhwa_li_ab, hajib);
            }
            if (alakhawat_li_ab >= 1) {
                hajb(Warith.ALAKHAWAT_LI_AB, alakhawat_li_ab, hajib);
            }
            if (abna_alikhwa_alashika >= 1) {
                hajb(Warith.ABNA_ALIKHWA_ALASHIKA, abna_alikhwa_alashika, hajib);
            }
            if (abna_alikhwa_li_ab >= 1) {
                hajb(Warith.ABNA_ALIKHWA_LI_AB, abna_alikhwa_li_ab, hajib);
            }
            if (ala3mam_alashika >= 1) {
                hajb(Warith.ALA3MAM_ALASHIKA, ala3mam_alashika, hajib);
            }
            if (ala3mam_li_ab >= 1) {
                hajb(Warith.ALA3MAM_LI_AB, ala3mam_li_ab, hajib);
            }
            if (abna_ala3mam_alashika >= 1) {
                hajb(Warith.ABNA_ALA3MAM_ALASHIKA, abna_ala3mam_alashika, hajib);
            }
            if (abna_ala3mam_li_ab >= 1) {
                hajb(Warith.ABNA_ALA3MAM_LI_AB, abna_ala3mam_li_ab, hajib);
            }
        }

        // mirath aljad
        if (aljad) {
            if (!alab) {        // no hajb by ab
                ro2os = 1;
                if (far3_warith_dhakar) {
                    tafsir = Warith.ALJAD.getTafsirPrefix();
                    tafsir += "السدس 1\\6 فرضا فقط";
                    bast = 1;
                    maqam = 6;
                    baqi = false;
                } else {            // Here be dragons الجد يرث فرضا وتعصيبا أو تعصيبا فقط
                    baqi = true;
                    if (far3_warith_ontha) {
                        tafsir = Warith.ALJAD.getTafsirPrefix();
                        tafsir += "السدس 1\\6 فرضا + ";
                        bast = 1;
                        maqam = 6;
                    } else {             // الجد يرث الباقي فقط
                        tafsir = "";
                        bast = 0;
                        maqam = 1;
                    }
                    if (alikhwa_alashika_wa_li_ab) {        // الجد يُقاسم الإخوة
                        boolean tassawi;
                        if (alikhwa_alashika > 0) {
                            ro2os += alikhwa_alashika;
                            if (alakhawat_ashakikat > 0) {
                                ro2os *= 2;
                                ro2os += alakhawat_ashakikat;
                                tassawi = false;
                            } else {
                                tassawi = true;
                            }
                            tafsir += Warith.ALJAD.getTafsirPrefix(1, Warith.ALIKHWA_ALASHIKA, alikhwa_alashika, tassawi);
                        } else {  // alikhwa_li_ab > 0
                            ro2os += alikhwa_li_ab;
                            if (alakhawat_li_ab > 0) {
                                ro2os *= 2;
                                ro2os += alikhwa_li_ab;
                                tassawi = false;
                            } else {
                                tassawi = true;
                            }
                            tafsir += Warith.ALJAD.getTafsirPrefix(1, Warith.ALIKHWA_LI_AB, alikhwa_li_ab, tassawi);
                        }
                    }
                    else {       // الجد يرث الباقي لوحده
                        tafsir = Warith.ALJAD.getTafsirPrefix();
                    }
                    tafsir += "الباقي تعصيبا بالنفس";
                }
                warathah.add(new Mirath(Warith.ALJAD, tafsir, bast, maqam, baqi, 1, ro2os));
            }

            // alhajb by aljad, we add it even if there is hajb by ab for detailed info
            hajib = Warith.ALJAD.getName();
            if (alikhwa_li_om >= 1) {
                hajb(Warith.ALIKHWA_LI_OM, alikhwa_li_om, hajib);
            }
            if (alakhawat_li_om >= 1) {
                hajb(Warith.ALAKHAWAT_LI_OM, alakhawat_li_om, hajib);
            }
            if (abna_alikhwa_alashika >= 1) {
                hajb(Warith.ABNA_ALIKHWA_ALASHIKA, abna_alikhwa_alashika, hajib);
            }
            if (abna_alikhwa_li_ab >= 1) {
                hajb(Warith.ABNA_ALIKHWA_LI_AB, abna_alikhwa_li_ab, hajib);
            }
            if (ala3mam_alashika >= 1) {
                hajb(Warith.ALA3MAM_ALASHIKA, ala3mam_alashika, hajib);
            }
            if (ala3mam_li_ab >= 1) {
                hajb(Warith.ALA3MAM_LI_AB, ala3mam_li_ab, hajib);
            }
            if (abna_ala3mam_alashika >= 1) {
                hajb(Warith.ABNA_ALA3MAM_ALASHIKA, abna_ala3mam_alashika, hajib);
            }
            if (abna_ala3mam_li_ab >= 1) {
                hajb(Warith.ABNA_ALA3MAM_LI_AB, abna_ala3mam_li_ab, hajib);
            }
        }

        // mirath alom
        if (alom) {
            tafsir = Warith.ALOM.getName();
            if (jam3_alikhwa || far3_warith) {
                tafsir += "السدس 1\\6 فرضا";
                maqam = 6;
                baqi = false;
            } else if ((alab) && ((azawjat > 0) || (zawj))) {
                tafsir = "الأم ترث ثلث 1\\3 الباقي";
                maqam = 3;
                baqi = true;
                special_case = "هذه مسألة الغرّاوين"; // TODO make it an enum type
            } else {
                tafsir += "الثلث 1\\3 فرضا";
                maqam = 3;
                baqi = false;
            }
            warathah.add(new Mirath(Warith.ALOM, tafsir, 1, maqam, baqi, 1));

            // alhajb bi alom
            hajib = Warith.ALOM.getName();
            if (aljadah_li_ab) {
                hajb(Warith.ALJADAH_LI_AB, 1, hajib);
            }
            if (aljadah_li_om) {
                hajb(Warith.ALJADAH_LI_OM, 1, hajib);
            }
        } else { // mirath aljadat
            if (aljadah_li_ab && !alab) {
                tafsir = "الجدة لأب ";
                if (aljadah_li_om) {
                    tafsir += "تشترك بالتساوي مع الجدة لأم في السدس 1\\6 فرضا";
                    ro2os = 2;
                } else {
                    tafsir += "ترث السدس 1\\6 فرضا";
                    ro2os = 1;
                }
                warathah.add(new Mirath(Warith.ALJADAH_LI_AB, tafsir, 1, 6, false, 1, ro2os));
            }

            if (aljadah_li_om) {
                tafsir = "الجدة لأم ";
                if (aljadah_li_ab && !alab) {
                    tafsir += "تشترك بالتساوي مع الجدة لأب في السدس 1\\6 فرضا";
                    ro2os = 2;
                } else {
                    tafsir += "ترث السدس 1\\6 فرضا";
                    ro2os = 1;
                }
                warathah.add(new Mirath(Warith.ALJADAH_LI_OM, tafsir, 1, 6, false, 1,  ro2os));
            }
        }

        // mirath alikhwa li om
        if (awlad_alom > 0) {
            if (far3_warith) {
                if (alikhwa_li_om > 0) {
                    hajb(Warith.ALIKHWA_LI_OM, alikhwa_li_om, "الفرع الوارث");
                }
                if (alakhawat_li_om > 0) {
                    hajb(Warith.ALAKHAWAT_LI_OM, alakhawat_li_om, "الفرع الوارث");
                }
            } else if (!asl_warith_dhaker) { // hajb by asl warith dhaker done above
                if (awlad_alom > 1) {
                    if (alikhwa_li_om > 0) {
                        if (alakhawat_li_om > 0) {
                            tafsir = Warith.ALAKHAWAT_LI_OM.getTafsirPrefix(alakhawat_li_om, Warith.ALIKHWA_LI_OM, alikhwa_li_om, true);
                            tafsir += "الثلث 1\\3 فرضا";
                            ro2os = awlad_alom;
                            warathah.add(new Mirath(Warith.ALAKHAWAT_LI_OM, tafsir, 1, 3, false, alakhawat_li_om, ro2os));
                            tafsir = Warith.ALIKHWA_LI_OM.getTafsirPrefix(alikhwa_li_om, Warith.ALAKHAWAT_LI_OM, alakhawat_li_om, true);
                        }
                        else {
                            tafsir = Warith.ALIKHWA_LI_OM.getTafsirPrefix(alikhwa_li_om);
                            ro2os = alikhwa_li_om;
                        }
                        tafsir += "الثلث 1\\3 فرضا";
                        warathah.add(new Mirath(Warith.ALIKHWA_LI_OM, tafsir, 1, 3, false, alikhwa_li_om, ro2os));
                    }
                    else if (alakhawat_li_om > 0) {
                        tafsir = Warith.ALAKHAWAT_LI_OM.getTafsirPrefix(alakhawat_li_om);
                        tafsir += "الثلث 1\\3 فرضا";
                        warathah.add(new Mirath(Warith.ALAKHAWAT_LI_OM, tafsir, 1, 3, false, alakhawat_li_om, alakhawat_li_om));
                    }
                } else if (alikhwa_li_om == 1) {
                    tafsir = Warith.ALIKHWA_LI_OM.getTafsirPrefix();
                    tafsir += "السدس 1\\6 فرضا";
                    warathah.add(new Mirath(Warith.ALIKHWA_LI_OM, tafsir, 1, 6));
                } else { // alakhawat_li_om == 1
                    tafsir = Warith.ALAKHAWAT_LI_OM.getTafsirPrefix();
                    tafsir += "السدس 1\\6 فرضا";
                    warathah.add(new Mirath(Warith.ALAKHAWAT_LI_OM, tafsir, 1, 6));
                }
            }
        }

        // mirath albanat ma3a 3adam wojud alabna
        if (alabna == 0) {
            if (albanat == 1) {
                tafsir = Warith.ALBANAT.getTafsirPrefix();
                tafsir += "النصف 1\\2 فرضا";
                warathah.add(new Mirath(Warith.ALBANAT, tafsir, 1, 2));
                if ((abna_alabna == 0) && (banat_alabna > 0)) {
                    tafsir = Warith.BANAT_ALABNA.getTafsirPrefix(banat_alabna);
                    tafsir += "السدس 1\\6 تتمة الثلثين فرضا";
                    warathah.add(new Mirath(Warith.BANAT_ALABNA, tafsir, 1, 6, false, banat_alabna));
                }
            } else if (albanat >= 2) {
                tafsir = Warith.ALBANAT.getTafsirPrefix(albanat);
                tafsir += "الثلثين 2\\3 فرضا";
                warathah.add(new Mirath(Warith.ALBANAT, tafsir, 2, 3, false, albanat));
                if ((abna_alabna == 0) && (banat_alabna > 0)) {
                    hajb(Warith.BANAT_ALABNA, banat_alabna, "الجمع من البنات");
                }
            } else { // albanat == 0
                // same logic holds for 2nd generation if present
                if ((abna_alabna == 0) && (banat_alabna > 0)) {
                    tafsir = Warith.BANAT_ALABNA.getTafsirPrefix(banat_alabna);
                    if (banat_alabna == 1) {
                        bast = 1; maqam = 2;
                        tafsir += "النصف 1\\2 فرضا";
                    } else {
                        bast = 2; maqam = 3;
                        tafsir += "الثلثين 2\\3 فرضا";
                    }
                    warathah.add(new Mirath(Warith.BANAT_ALABNA, tafsir, bast, maqam, false, banat_alabna));
                }
            }
        }

        // mirath alakhawat ashakikat 3inda 3adam wojud alikhwa alashika
        if ((alakhawat_ashakikat > 0) && !alab && !far3_warith_dhakar && (alikhwa_alashika == 0)) {
            tafsir = Warith.ALAKHAWAT_ASHAKIKAT.getTafsirPrefix(alakhawat_ashakikat);
            if (!far3_warith_ontha) {
                baqi = false;
                if (alakhawat_ashakikat == 1) {
                    bast = 1; maqam = 2;
                    tafsir += "النصف 1\\2 فرضا";
                } else { // (alakhawat_ashakikat >= 2)
                    bast = 2; maqam = 3;
                    tafsir +="الثلثين 2\\3 فرضا";
                }
            } else { // presence of far3_warith_ontha
                baqi = true;
                bast = 0; maqam = 1;
                tafsir += "الباقي تعصيبا مع الغير";
            }
            warathah.add(new Mirath(Warith.ALAKHAWAT_ASHAKIKAT, tafsir, bast, maqam, baqi, alakhawat_ashakikat));
        }

        // mirath alakhawat li ab 3inda 3adam wojud alikhwa
        if ((alakhawat_li_ab > 0) && !alab && !far3_warith_dhakar && !alikhwa_alashika_wa_li_ab) {
            if (!far3_warith_ontha) {
                if (alakhawat_ashakikat > 1) {
                    hajb(Warith.ALAKHAWAT_LI_AB, alakhawat_li_ab, Warith.ALAKHAWAT_ASHAKIKAT.getName(alakhawat_ashakikat));
                } else {
                    tafsir = Warith.ALAKHAWAT_LI_AB.getTafsirPrefix(alakhawat_li_ab);
                    if (alakhawat_ashakikat == 0) {
                        if (alakhawat_li_ab == 1) {
                            bast = 1; maqam = 2;
                            tafsir += "النصف 1\\2 فرضا";
                        } else { // (alakhawat_li_ab >= 2)
                            bast = 2; maqam = 3;
                            tafsir += "الثلثين 2\\3 فرضا";
                        }
                    } else { // (alakhawat_ashakikat == 1)
                        bast = 1; maqam = 6;
                        tafsir += "السدس 1\\6 تتمة الثلثين فرضا";
                    }
                    warathah.add(new Mirath(Warith.ALAKHAWAT_LI_AB, tafsir, bast, maqam, false, alakhawat_li_ab));
                }
            } else { // far3_warith_ontha
                if (alakhawat_ashakikat == 0) {
                    tafsir = Warith.ALAKHAWAT_LI_AB.getTafsirPrefix(alakhawat_li_ab);
                    tafsir += " الباقي تعصيبا مع الغير";
                    warathah.add(new Mirath(Warith.ALAKHAWAT_LI_AB, tafsir, 0, 1, true, alakhawat_li_ab));
                } else {
                    hajib = Warith.ALAKHAWAT_ASHAKIKAT.getName(alakhawat_ashakikat);
                    hajb(Warith.ALAKHAWAT_LI_AB, alakhawat_li_ab, hajib);
                }
            }
        }

        //mirath alabna + albanat 3inda woujoud alabna
        if (alabna > 0) {
            if (albanat == 0) {
                tafsir = Warith.ALABNA.getTafsirPrefix(alabna);
                tafsir += " الباقي تعصيبا بالنفس";
                warathah.add(new Mirath(Warith.ALABNA, tafsir, 0, 1, true, alabna));
            } else {
                String tafsir2;
                tafsir = Warith.ALABNA.getTafsirPrefix(alabna, Warith.ALBANAT, albanat, false);
                tafsir += "الباقي تعصيبا بالنفس";
                tafsir2 = Warith.ALBANAT.getTafsirPrefix(albanat, Warith.ALABNA, alabna, false);
                tafsir2 += "الباقي تعصيبا بالغير";
                ro2os = 2 * alabna + albanat;
                warathah.add(new Mirath(Warith.ALABNA, tafsir, 0, 1, true, alabna, ro2os));
                warathah.add(new Mirath(Warith.ALBANAT, tafsir2, 0, 1, true, albanat, ro2os));
            }

            // hajb by alabna
            hajib = Warith.ALABNA.getName(alabna);
            if (abna_alabna > 0) {
                hajb(Warith.ABNA_ALABNA, abna_alabna, hajib);
            }
            if (banat_alabna > 0) {
                hajb(Warith.BANAT_ALABNA, banat_alabna, hajib);
            }
            if (alikhwa_alashika > 0) {
                hajb(Warith.ALIKHWA_ALASHIKA, alikhwa_alashika, hajib);
            }
            if (alakhawat_ashakikat > 0) {
                hajb(Warith.ALAKHAWAT_ASHAKIKAT, alakhawat_ashakikat, hajib);
            }
            if (alikhwa_li_ab > 0) {
                hajb(Warith.ALIKHWA_LI_AB, alikhwa_li_ab, hajib);
            }
            if (alakhawat_li_ab > 0) {
                hajb(Warith.ALAKHAWAT_LI_AB, alakhawat_li_ab, hajib);
            }
            //الحجب تم بالفرع الوارث سابقا في ميراث أولاد الأم
           /* if (alikhwa_li_om > 0) {
                hajb(Warith.ALIKHWA_LI_OM, alikhwa_li_om, hajib);
            }
            if (alakhawat_li_om > 0) {
                hajb(Warith.ALAKHAWAT_LI_OM, alakhawat_li_om, hajib);
            } */
            if (abna_alikhwa_alashika > 0) {
                hajb(Warith.ABNA_ALIKHWA_ALASHIKA, abna_alikhwa_alashika, hajib);
            }
            if (abna_alikhwa_li_ab > 0) {
                hajb(Warith.ABNA_ALIKHWA_LI_AB, abna_alikhwa_li_ab, hajib);
            }
            if (ala3mam_alashika > 0) {
                hajb(Warith.ALA3MAM_ALASHIKA, ala3mam_alashika, hajib);
            }
            if (ala3mam_li_ab > 0) {
                hajb(Warith.ALA3MAM_LI_AB, ala3mam_li_ab, hajib);
            }
            if (abna_ala3mam_alashika > 0) {
                hajb(Warith.ABNA_ALA3MAM_ALASHIKA, abna_ala3mam_alashika, hajib);
            }
            if (abna_ala3mam_li_ab > 0) {
                hajb(Warith.ABNA_ALA3MAM_LI_AB, abna_ala3mam_li_ab, hajib);
            }
        }

        // mirath abna_alabna + banat_alabna
        if ((abna_alabna > 0) && (alabna == 0)) {
            if (banat_alabna == 0) {
                tafsir = Warith.ABNA_ALABNA.getTafsirPrefix(abna_alabna);
                tafsir += "الباقي تعصيبا بالنفس";
                warathah.add(new Mirath(Warith.ABNA_ALABNA, tafsir, 0, 1, true, abna_alabna));
            } else {
                String tafsir2;
                tafsir = Warith.ABNA_ALABNA.getTafsirPrefix(abna_alabna, Warith.BANAT_ALABNA, banat_alabna, false);
                tafsir += "الباقي تعصيبا بالنفس";
                tafsir2 = Warith.BANAT_ALABNA.getTafsirPrefix(banat_alabna, Warith.ABNA_ALABNA, abna_alabna, false);
                tafsir2 += "الباقي تعصيبا بالغير";
                ro2os = 2 * abna_alabna + banat_alabna;
                warathah.add(new Mirath(Warith.ABNA_ALABNA, tafsir, 0, 1, true, abna_alabna, ro2os));
                warathah.add(new Mirath(Warith.BANAT_ALABNA, tafsir2, 0, 1, true, banat_alabna, ro2os));
            }

            // hajb by abna alabna
            hajib = Warith.ABNA_ALABNA.getName(abna_alabna);
            if (alikhwa_alashika > 0) {
                hajb(Warith.ALIKHWA_ALASHIKA, alikhwa_alashika, hajib);
            }
            if (alakhawat_ashakikat > 0) {
                hajb(Warith.ALAKHAWAT_ASHAKIKAT, alakhawat_ashakikat, hajib);
            }
            if (alikhwa_li_ab > 0) {
                hajb(Warith.ALIKHWA_LI_AB, alikhwa_li_ab, hajib);
            }
            if (alakhawat_li_ab > 0) {
                hajb(Warith.ALAKHAWAT_LI_AB, alakhawat_li_ab, hajib);
            }
            //الحجب تم بالفرع الوارث سابقا في ميراث أولاد الأم
         /*   if (alikhwa_li_om > 0) {
                hajb(Warith.ALIKHWA_LI_OM, alikhwa_li_om, hajib);
            }
            if (alakhawat_li_om > 0) {
                hajb(Warith.ALAKHAWAT_LI_OM, alakhawat_li_om, hajib);
            }*/
            if (abna_alikhwa_alashika > 0) {
                hajb(Warith.ABNA_ALIKHWA_ALASHIKA, abna_alikhwa_alashika, hajib);
            }
            if (abna_alikhwa_li_ab > 0) {
                hajb(Warith.ABNA_ALIKHWA_LI_AB, abna_alikhwa_li_ab, hajib);
            }
            if (ala3mam_alashika > 0) {
                hajb(Warith.ALA3MAM_ALASHIKA, ala3mam_alashika, hajib);
            }
            if (ala3mam_li_ab > 0) {
                hajb(Warith.ALA3MAM_LI_AB, ala3mam_li_ab, hajib);
            }
            if (abna_ala3mam_alashika > 0) {
                hajb(Warith.ABNA_ALA3MAM_ALASHIKA, abna_ala3mam_alashika, hajib);
            }
            if (abna_ala3mam_li_ab > 0) {
                hajb(Warith.ABNA_ALA3MAM_LI_AB, abna_ala3mam_li_ab, hajib);
            }
        }

        // mirath alab and hajb by alab were previously calculated
        // mirath aljad and hajb by aljad were previously calculated

        // mirath alikhwa alashika + alakhawat ashakikat
        if (alikhwa_alashika > 0 && !far3_warith_dhakar && !alab) {
            if (alakhawat_ashakikat == 0) {
                ro2os = alikhwa_alashika;
                if (aljad) {
                    ro2os++;
                    tafsir = Warith.ALIKHWA_ALASHIKA.getTafsirPrefix(alikhwa_alashika, Warith.ALJAD, 1, true);
                }
                else {
                    tafsir = Warith.ALIKHWA_ALASHIKA.getTafsirPrefix(alikhwa_alashika);
                }
                tafsir += "الباقي تعصيبا بالنفس";
                warathah.add(new Mirath(Warith.ALIKHWA_ALASHIKA, tafsir, 0, 1, true, alikhwa_alashika, ro2os));
            } else {
                String tafsir2;
                tafsir = Warith.ALIKHWA_ALASHIKA.getTafsirPrefix(alikhwa_alashika, Warith.ALAKHAWAT_ASHAKIKAT, alakhawat_ashakikat, false);
                tafsir += "الباقي تعصيبا بالنفس";
                tafsir2 = Warith.ALAKHAWAT_ASHAKIKAT.getTafsirPrefix(alakhawat_ashakikat, Warith.ALIKHWA_ALASHIKA, alikhwa_alashika, false);
                tafsir2 += "الباقي تعصيبا بالغير";
                ro2os = 2 * alikhwa_alashika + alakhawat_ashakikat;
                if (aljad) {
                    tafsir += " ومقاسمة مع الجد";
                    tafsir2 += " ومقاسمة مع الجد";
                    ro2os += 2;
                }
                warathah.add(new Mirath(Warith.ALIKHWA_ALASHIKA, tafsir, 0, 1, true, alikhwa_alashika, ro2os));
                warathah.add(new Mirath(Warith.ALAKHAWAT_ASHAKIKAT, tafsir2, 0, 1, true, alakhawat_ashakikat, ro2os));
            }

            // الحجب بالإخوة الأشقاء
            hajib = Warith.ALIKHWA_ALASHIKA.getName(alikhwa_alashika);
            if (alikhwa_li_ab > 0) {
                hajb(Warith.ALIKHWA_LI_AB, alikhwa_li_ab, hajib);
            }
            if (alakhawat_li_ab > 0) {
                hajb(Warith.ALAKHAWAT_LI_AB, alakhawat_li_ab, hajib);
            }
            if (abna_alikhwa_alashika > 0) {
                hajb(Warith.ABNA_ALIKHWA_ALASHIKA, abna_alikhwa_alashika, hajib);
            }
            if (abna_alikhwa_li_ab > 0) {
                hajb(Warith.ABNA_ALIKHWA_LI_AB, abna_alikhwa_alashika, hajib);
            }
            if (ala3mam_alashika > 0) {
                hajb(Warith.ALA3MAM_ALASHIKA, ala3mam_alashika, hajib);
            }
            if (ala3mam_li_ab > 0) {
                hajb(Warith.ALA3MAM_LI_AB, ala3mam_li_ab, hajib);
            }
            if (abna_ala3mam_alashika > 0) {
                hajb(Warith.ABNA_ALA3MAM_ALASHIKA, abna_ala3mam_alashika, hajib);
            }
            if (abna_ala3mam_li_ab > 0) {
                hajb(Warith.ABNA_ALA3MAM_LI_AB, abna_ala3mam_li_ab, hajib);
            }
        }

        // mirath alikhwa li ab + alakhawat li ab
        if ((alikhwa_li_ab > 0) && !far3_warith_dhakar && !alab && (alikhwa_alashika == 0)) {
            if (alakhawat_li_ab == 0) {
                ro2os = alikhwa_li_ab;
                if (aljad) {
                    ro2os++;
                    tafsir = Warith.ALIKHWA_LI_AB.getTafsirPrefix(alikhwa_li_ab, Warith.ALJAD, 1, true);
                }
                else {
                    tafsir = Warith.ALIKHWA_LI_AB.getTafsirPrefix(alikhwa_li_ab);
                }
                tafsir += "الباقي تعصيبا بالنفس";
                warathah.add(new Mirath(Warith.ALIKHWA_LI_AB, tafsir, 0, 1, true, alikhwa_li_ab, ro2os));
            } else {
                String tafsir2;
                tafsir = Warith.ALIKHWA_LI_AB.getTafsirPrefix(alikhwa_li_ab, Warith.ALAKHAWAT_LI_AB, alakhawat_li_ab, false);
                tafsir += "الباقي تعصيبا بالنفس";
                tafsir2 = Warith.ALAKHAWAT_LI_AB.getTafsirPrefix(alakhawat_li_ab, Warith.ALIKHWA_LI_AB, alikhwa_li_ab, false);
                tafsir2 += "الباقي تعصيبا بالغير";
                ro2os = 2 * alikhwa_li_ab + alakhawat_li_ab;
                if (aljad) {
                    tafsir += " ومقاسمة مع الجد";
                    tafsir2 += " ومقاسمة مع الجد";
                    ro2os += 2;
                }
                warathah.add(new Mirath(Warith.ALIKHWA_LI_AB, tafsir, 0, 1, true, alikhwa_li_ab, ro2os));
                warathah.add(new Mirath(Warith.ALAKHAWAT_LI_AB, tafsir2, 0, 1, true, alakhawat_li_ab, ro2os));
            }

            // alhajb by alikhwa li ab
            hajib = Warith.ALIKHWA_LI_AB.getName(alikhwa_li_ab);
            if (abna_alikhwa_alashika > 0) {
                hajb(Warith.ABNA_ALIKHWA_ALASHIKA, abna_alikhwa_alashika, hajib);
            }
            if (abna_alikhwa_li_ab > 0) {
                hajb(Warith.ABNA_ALIKHWA_LI_AB, abna_alikhwa_li_ab, hajib);
            }
            if (ala3mam_alashika > 0) {
                hajb(Warith.ALA3MAM_ALASHIKA, ala3mam_alashika, hajib);
            }
            if (ala3mam_li_ab > 0) {
                hajb(Warith.ALA3MAM_LI_AB, ala3mam_li_ab, hajib);
            }
            if (abna_ala3mam_alashika > 0) {
                hajb(Warith.ABNA_ALA3MAM_ALASHIKA, abna_ala3mam_alashika, hajib);
            }
            if (abna_ala3mam_li_ab > 0) {
                hajb(Warith.ABNA_ALA3MAM_LI_AB, abna_ala3mam_li_ab, hajib);
            }
        }

        // mirath abna alikhwa alashika
        if ((abna_alikhwa_alashika > 0) && !far3_wa_asl_warith_dhaker && !alikhwa_alashika_wa_li_ab) {
            tafsir = Warith.ABNA_ALIKHWA_ALASHIKA.getName(abna_alikhwa_alashika);
            tafsir += "الباقي تعصيبا بالنفس";
            warathah.add(new Mirath(Warith.ABNA_ALIKHWA_ALASHIKA, tafsir, 0, 1, true, abna_alikhwa_alashika));

            // alhajb by abna alikhwa alashika
            hajib = Warith.ABNA_ALIKHWA_ALASHIKA.getName(abna_alikhwa_alashika);
            if (abna_alikhwa_li_ab > 0) {
                hajb(Warith.ABNA_ALIKHWA_LI_AB, abna_alikhwa_li_ab, hajib);
            }
            if (ala3mam_alashika > 0) {
                hajb(Warith.ALA3MAM_ALASHIKA, ala3mam_alashika, hajib);
            }
            if (ala3mam_li_ab > 0) {
                hajb(Warith.ALA3MAM_LI_AB, ala3mam_li_ab, hajib);
            }
            if (abna_ala3mam_alashika > 0) {
                hajb(Warith.ABNA_ALA3MAM_ALASHIKA, abna_ala3mam_alashika, hajib);
            }
            if (abna_ala3mam_li_ab > 0) {
                hajb(Warith.ABNA_ALA3MAM_LI_AB, abna_ala3mam_li_ab, hajib);
            }
        }

        // mirath abna alikhwa li ab
        if ((abna_alikhwa_li_ab > 0) && !far3_wa_asl_warith_dhaker && !alikhwa_alashika_wa_li_ab && (abna_alikhwa_alashika == 0)) {
            tafsir = Warith.ABNA_ALIKHWA_LI_AB.getName(abna_alikhwa_li_ab);
            tafsir += "الباقي تعصيبا بالنفس";
            warathah.add(new Mirath(Warith.ABNA_ALIKHWA_LI_AB, tafsir, 0, 1, true, abna_alikhwa_li_ab));

            // alhajb by abna alikhwa li_ab
            hajib = Warith.ABNA_ALIKHWA_LI_AB.getName(abna_alikhwa_li_ab);
            if (ala3mam_alashika > 0) {
                hajb(Warith.ALA3MAM_ALASHIKA, ala3mam_alashika, hajib);
            }
            if (ala3mam_li_ab > 0) {
                hajb(Warith.ALA3MAM_LI_AB, ala3mam_li_ab, hajib);
            }
            if (abna_ala3mam_alashika > 0) {
                hajb(Warith.ABNA_ALA3MAM_ALASHIKA, abna_ala3mam_alashika, hajib);
            }
            if (abna_ala3mam_li_ab > 0) {
                hajb(Warith.ABNA_ALA3MAM_LI_AB, abna_ala3mam_li_ab, hajib);
            }
        }

        // mirath ala3mam alashika
        if ((ala3mam_alashika > 0) && !far3_wa_asl_warith_dhaker && !alikhwa_wa_abna_alikhwa) {
            tafsir = Warith.ALA3MAM_ALASHIKA.getName(ala3mam_alashika);
            tafsir += "الباقي تعصيبا بالنفس";
            warathah.add(new Mirath(Warith.ALA3MAM_ALASHIKA, tafsir, 0, 1, true, ala3mam_alashika));

            // alhajb by abna alikhwa alashika
            hajib = Warith.ALA3MAM_ALASHIKA.getName(ala3mam_alashika);
            if (ala3mam_li_ab > 0) {
                hajb(Warith.ALA3MAM_LI_AB, ala3mam_li_ab, hajib);
            }
            if (abna_ala3mam_alashika > 0) {
                hajb(Warith.ABNA_ALA3MAM_ALASHIKA, abna_ala3mam_alashika, hajib);
            }
            if (abna_ala3mam_li_ab > 0) {
                hajb(Warith.ABNA_ALA3MAM_LI_AB, abna_ala3mam_li_ab, hajib);
            }
        }

        // mirath ala3mam li ab
        if ((ala3mam_li_ab > 0) && !far3_wa_asl_warith_dhaker && !alikhwa_wa_abna_alikhwa && (ala3mam_alashika == 0)) {
            tafsir = Warith.ALA3MAM_LI_AB.getName(ala3mam_li_ab);
            tafsir += "الباقي تعصيبا بالنفس";
            warathah.add(new Mirath(Warith.ALA3MAM_LI_AB, tafsir, 0, 1, true, ala3mam_li_ab));

            // alhajb by abna alikhwa li_ab
            hajib = Warith.ALA3MAM_LI_AB.getName(ala3mam_li_ab);
            if (abna_ala3mam_alashika > 0) {
                hajb(Warith.ABNA_ALA3MAM_ALASHIKA, abna_ala3mam_alashika, hajib);
            }
            if (abna_ala3mam_li_ab > 0) {
                hajb(Warith.ABNA_ALA3MAM_LI_AB, abna_ala3mam_li_ab, hajib);
            }
        }

        // mirath abna ala3mam alashika
        if ((abna_ala3mam_alashika > 0) && !far3_wa_asl_warith_dhaker && !alikhwa_wa_abna_alikhwa && !ala3mam) {
            tafsir = Warith.ABNA_ALA3MAM_ALASHIKA.getName(abna_ala3mam_alashika);
            tafsir += "الباقي تعصيبا بالنفس";
            warathah.add(new Mirath(Warith.ABNA_ALA3MAM_ALASHIKA, tafsir, 0, 1, true, abna_ala3mam_alashika));

            // alhajb by abna alikhwa li_ab
            hajib = Warith.ABNA_ALA3MAM_ALASHIKA.getName(abna_ala3mam_alashika);
            if (abna_ala3mam_li_ab > 0) {
                hajb(Warith.ABNA_ALA3MAM_LI_AB, abna_ala3mam_li_ab, hajib);
            }
        }

        // mirath abnaa ala3mam li ab
        if ((abna_ala3mam_li_ab > 0) && !far3_wa_asl_warith_dhaker && !alikhwa_wa_abna_alikhwa && !ala3mam && (abna_ala3mam_alashika == 0)) {
            tafsir = Warith.ABNA_ALA3MAM_LI_AB.getName(abna_ala3mam_li_ab);
            tafsir += "الباقي تعصيبا بالنفس";
            warathah.add(new Mirath(Warith.ABNA_ALA3MAM_LI_AB, tafsir, 0, 1, true, abna_ala3mam_li_ab));
        }

        // al-hissab

        // استخراج أصل المسألة (ص 40 من كتاب "الفرائض المُيسر) وعدد رؤوس ورثة الباقي"
        StringBuilder tafasir = new StringBuilder();
        for (Mirath m : warathah) {
            tafasir.append("- ").append(m.getTafsir()).append(".\n");
            if (m.isFardh()) {
                asl_mas2ala = lcm(asl_mas2ala, m.getMaqam());
            }
            if (m.getRo2os() != 1) {
                if (adad_ro2os != 1) {
                    if (adad_ro2os != m.getRo2os()) {
                        System.out.println(String.format("BUG! %s خطأ في حساب أصحاب العصبات", m.getWarith().getName(m.getNbr())));
                    }
                    // else already set
                } else {
                    adad_ro2os = m.getRo2os();
                }
            }
        }
        if (special_case != null /* TODO غراوين */) {
            tafasir.append(special_case + "\n");
        }
        tafasir.append("\n" + "أصل المسألة: " + asl_mas2ala + "\n");


        // حساب مجموع أسهم أصحاب الفروض وباقي المسألة
        for (Mirath m : warathah) {
            if (m.isFardh()) {
                int fardh = m.getBast() * asl_mas2ala / m.getMaqam();
                m.setFardh(fardh);
                ashom += fardh;
            }
        }
        int baki = asl_mas2ala - ashom;
        if (baki == 0) {
            if (adad_ro2os == 0) {              // مسألة عادلة وكل أحكام الفروض عادلة
                tafasir.append("المسألة عادلة (تساوى أصلها مع أسهمها).\n");
            } else {                            // لم يبق للورثة بالتعصيب شيء
                tafasir.append("استغرق أصحاب الفروض الأسهم ولم يبق للورثة بالتعصيب شيء.\n");
            }
        } else if (asl_mas2ala > ashom) {
            if (adad_ro2os > 0) {                  // مسألة فيها باقي
                tafasir.append(String.format("الباقي %d يُُقسم على: %d\n", baki, adad_ro2os));
                // TODO baki % adad_ro2os != 0
            } else {                           // مسألة ناقصة فيها رد
                tafasir.append(String.format("المسألة ناقصة (أسهمها أقل من أصلها) فيرد الباقي %d على أصحاب الفروض\n", baki));
                // TODO
            }
        } else {                                // مسألة عائلة
            // TODO
            tafasir.append(String.format("المسألة عائلة (أسهمها أكثر من أصلها)، تعول إلى %d\n", ashom));
            awl = ashom;
        }


        // قسمة الأسهم في الجدول
        StringBuilder table = new StringBuilder();
        table.append(" \t   \t ┌──────┐\n");       // https://en.wikipedia.org/wiki/Box-drawing_character
        if (awl != 0) {
            table.append(String.format(" \t   \t │ %4d │\n", awl));
            table.append(String.format(" \t   \t ├──────┤\n", awl));
            table.append(String.format(" \t   \t │ %4dع│\n", asl_mas2ala));  // TODO strikethrough
        } else {
            table.append(String.format(" \t   \t │ %4d │\n", asl_mas2ala));
        }
        table.append(String.format("┌─────────┼──────────┼──────┤\n"));
        int i = 0;
        for (Mirath m : warathah) {
            i++;
            int sahm;
            StringBuilder col_name = new StringBuilder();
            StringBuilder col_wirth = new StringBuilder();
            StringBuilder col_sahm = new StringBuilder();

            col_name.append(m.getWarith().getShortName());
            if (m.getNbr() != 1) {
                col_name.append(String.format("/%d", m.getNbr()));
            }

            // Here be dragons
            if (m.isFardh()) {
                col_wirth.append(String.format("%1d\\%1d "), m.getBast(), m.getMaqam());
                if (!m.isBaqi()) {
                    sahm = m.getFardh();
                }
                else {
                    // TODO assert m.getBast() == 1
                    if (m.getMaqam() == 3) {    // حالات ثلث الباقي مثل الأم في الغراوين (ص 19)
                        // om sahm is always 1 in Gharawain
                        sahm = 1;

                        // TODO تفصيل الجد مع الإخوة في حال أخذ الجد ثلث الباقي بالمفاضلة يجب حسابه
                    } else {                                        // حالات الأب والجد
                        if (baki > 0) {
                            sahm = m.getFardh() + baki;
                            col_sahm.append(String.format("1+%2d", baki));
                            if (m.isShirka()) {
                                col_sahm.append("م");
                            }
                        } else {
                            sahm = 1;
                        }
                        col_wirth.append(" + ");
                    }
                    col_wirth.append("ب");
                }
            }
            else if (m.isBaqi()) {
                col_wirth.append("ب");
                if (baki > 0) {
                    sahm = baki;
                    col_sahm.append(String.format("%2d", baki));
                    if (m.isShirka()) {
                        col_sahm.append("م");
                    }
                } else {
                    sahm = 0;
                }
            }
            else if (m.mahjoob()) {
                sahm = 0;
                col_wirth.append("م");
                col_sahm.append("--");
            }
            else {
                System.out.println(String.format("BUG! %s خطأ وارث ليس صاحب فرض أو تعصيب أو حجب", m.getWarith().getName()));
                break;
            }


            if (col_sahm.length() == 0) {
                col_sahm.append(String.format("%4d", sahm));
            }


            m.setSahm(sahm);
            table.append(String.format("+ %s\t + %s\t + %s +\n", col_wirth, col_name, col_sahm));
            if (i == warathah.size()) {
                table.append(String.format("└──────────┴──────────┴──────┘\n"));
            } else {
                table.append(String.format("├──────────┼──────────┼──────┤\n"));
            }
        }

        return tafasir.toString() + "\n\n" + table.toString();
    }

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
