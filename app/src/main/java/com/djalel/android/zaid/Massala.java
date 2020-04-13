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

import java.util.ArrayList;
import java.util.List;

public class Massala {
    WarathaInput mInput;

    List<Mirath> mWarathah;
    List<Mirath> mMahjoobin;
    private boolean mHal;
    private boolean mFardh;
    private boolean mTa3seeb;
    private boolean mTassawi;
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
        mInput = null;
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
        mTassawi = true;
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

    public void hissabMawarith(WarathaInput in) {
        mInput = in;

        boolean far3_warith_dhakar = (mInput.get_alabna() + mInput.get_abna_alabna()) > 0;
        boolean far3_warith_ontha = (mInput.get_albanat() + mInput.get_banat_alabna()) > 0;
        boolean far3_warith = far3_warith_dhakar || far3_warith_ontha;
        boolean jam3_alikhwa = (mInput.get_alikhwa_alashika() + mInput.get_alikhwa_li_ab() + mInput.get_alikhwa_li_om() +
                mInput.get_alakhawat_ashakikat() + mInput.get_alakhawat_li_ab() + mInput.get_alakhawat_li_om()) > 1;
        boolean asl_warith_dhaker = mInput.alab() || mInput.aljad();
        boolean far3_wa_asl_warith_dhaker = far3_warith_dhakar || asl_warith_dhaker;
        boolean alikhwa_alashika_wa_li_ab = (mInput.get_alikhwa_alashika() + mInput.get_alikhwa_li_ab()) > 0;
        boolean alikhwa_wa_abna_alikhwa = (mInput.get_alikhwa_alashika() + mInput.get_alikhwa_li_ab() + mInput.get_abna_alikhwa_alashika() + mInput.get_abna_alikhwa_li_ab()) > 0;
        boolean ala3mam = (mInput.get_ala3mam_alashika() + mInput.get_ala3mam_li_ab()) > 0;

        // Order matters. Here be dragons
        mirathAzawj(far3_warith);
        mirathAzawjat(far3_warith);
        mirathAlab(far3_warith_dhakar, far3_warith_ontha);
        mirathAljad(far3_warith_dhakar, far3_warith_ontha, alikhwa_alashika_wa_li_ab);
        mirathAlom(far3_warith, jam3_alikhwa);
        mirathAljadat();
        mirathAwladAlom(far3_warith, asl_warith_dhaker);
        mirathAlbanat();
        mirathAlkhawatAshakikat(far3_warith_dhakar, far3_warith_ontha);
        mirathAlakhawatLiAb(far3_warith_dhakar, far3_warith_ontha, alikhwa_alashika_wa_li_ab);
        mirathAlabnaWaAlbanat();
        mirathAbnaWaBanatAlabna();
        mirathAlikhwaAlashika(far3_warith_dhakar);
        mirathAlikhwaLiAb(far3_warith_dhakar);
        mirathAbnaAlikhwaAlashika(far3_wa_asl_warith_dhaker, alikhwa_alashika_wa_li_ab);
        mirathAbnaAlikhwaLiAb(far3_wa_asl_warith_dhaker, alikhwa_alashika_wa_li_ab);
        mirathAla3mamAlashika(far3_wa_asl_warith_dhaker, alikhwa_wa_abna_alikhwa);
        mirathAla3mamLiAb(far3_wa_asl_warith_dhaker, alikhwa_wa_abna_alikhwa);
        mirathAbnaAla3mamAlashika(far3_wa_asl_warith_dhaker, alikhwa_wa_abna_alikhwa, ala3mam);
        mirathAbnaAla3mamLiAb(far3_wa_asl_warith_dhaker, alikhwa_wa_abna_alikhwa, ala3mam);

        // TODO assert none of the Warith enums is both in mWarathah & mMahjoobin
        if (noInput()) {
            System.out.println("لم يتم إدخال أية ورثة");
            return;
        }

        // Order matters. Here be dragons
        hissabAslAndRo2os();
        hissabAshomAndBaqi();
        hissabAnsiba();
    }

    private void mirathAzawj(boolean far3_warith) {
        if (!mInput.zawj()) return;

        int maqam;

        String sharh = Warith.AZAWJ.getSharhPrefix();
        if (far3_warith) {
            maqam = 4;
            sharh += "الربع 1\\4 فرضا";
        } else {
            maqam = 2;
            sharh += "النصف 1\\2 فرضا";
        }
        addMirath(new Mirath(Warith.AZAWJ, sharh, 1, maqam));
    }

    private void mirathAzawjat(boolean far3_warith) {
        int nbr = mInput.get_azawjat();
        if (nbr == 0) return;

        int maqam;

        String sharh = Warith.AZAWJAT.getSharhPrefix(nbr);
        if (far3_warith) {
            maqam = 8;
            sharh += "الثمن 1\\8 فرضا";
        } else {
            maqam = 4;
            sharh += "الربع 1\\4 فرضا";
        }
        addMirath(new Mirath(Warith.AZAWJAT, nbr, sharh, 1, maqam));
    }

    private void mirathAlab(boolean far3_warith_dhakar, boolean far3_warith_ontha) {
        if (!mInput.alab()) return;

        int bast;
        int maqam;
        boolean ta3seeb;

        String sharh = Warith.ALAB.getSharhPrefix();
        if (far3_warith_dhakar) {
            sharh += "السدس 1\\6 فرضا فقط";
            bast = 1;
            maqam = 6;
            ta3seeb = false;
        } else if (far3_warith_ontha) {
            sharh += "السدس 1\\6 فرضا + الباقي تعصيبا بالنفس";
            bast = 1;
            maqam = 6;
            ta3seeb = true;
        } else {
            sharh += "الباقي تعصيبا بالنفس";
            bast = 0;
            maqam = 1;
            ta3seeb = true;
        }
        addMirath(new Mirath(Warith.ALAB, sharh, bast, maqam, ta3seeb));

        // alhajb bi alab
        if (mInput.aljad()) {
            addHajb(Warith.ALJAD, Warith.ALAB);
        }
        if (mInput.get_madhab() != Madhab.HAMBALI) {
            // تسقط الجدة من جهة الأب بالأب، ولا تسقط الجدة من جهة الأم بالأب
            if (mInput.aljadah_li_ab()) {
                addHajb(Warith.ALJADAH_LI_AB, Warith.ALAB);
            }
        }
        if (mInput.get_alikhwa_alashika() >= 1) {
            addHajb(Warith.ALIKHWA_ALASHIKA, mInput.get_alikhwa_alashika(), Warith.ALAB);
        }
        if (mInput.get_alakhawat_ashakikat() >= 1) {
            addHajb(Warith.ALAKHAWAT_ASHAKIKAT, mInput.get_alakhawat_ashakikat(), Warith.ALAB);
        }
        if (mInput.get_alikhwa_li_om() >= 1) {
            addHajb(Warith.ALIKHWA_LI_OM, mInput.get_alikhwa_li_om(), Warith.ALAB);
        }
        if (mInput.get_alakhawat_li_om() >= 1) {
            addHajb(Warith.ALAKHAWAT_LI_OM, mInput.get_alakhawat_li_om(), Warith.ALAB);
        }
        if (mInput.get_alikhwa_li_ab() >= 1) {
            addHajb(Warith.ALIKHWA_LI_AB, mInput.get_alikhwa_li_ab(), Warith.ALAB);
        }
        if (mInput.get_alakhawat_li_ab() >= 1) {
            addHajb(Warith.ALAKHAWAT_LI_AB, mInput.get_alakhawat_li_ab(), Warith.ALAB);
        }
        if (mInput.get_abna_alikhwa_alashika() >= 1) {
            addHajb(Warith.ABNA_ALIKHWA_ALASHIKA, mInput.get_abna_alikhwa_alashika(), Warith.ALAB);
        }
        if (mInput.get_abna_alikhwa_li_ab() >= 1) {
            addHajb(Warith.ABNA_ALIKHWA_LI_AB, mInput.get_abna_alikhwa_li_ab(), Warith.ALAB);
        }
        if (mInput.get_ala3mam_alashika() >= 1) {
            addHajb(Warith.ALA3MAM_ALASHIKA, mInput.get_ala3mam_alashika(), Warith.ALAB);
        }
        if (mInput.get_ala3mam_li_ab() >= 1) {
            addHajb(Warith.ALA3MAM_LI_AB, mInput.get_ala3mam_li_ab(), Warith.ALAB);
        }
        if (mInput.get_abna_ala3mam_alashika() >= 1) {
            addHajb(Warith.ABNA_ALA3MAM_ALASHIKA, mInput.get_abna_ala3mam_alashika(), Warith.ALAB);
        }
        if (mInput.get_abna_ala3mam_li_ab() >= 1) {
            addHajb(Warith.ABNA_ALA3MAM_LI_AB, mInput.get_abna_ala3mam_li_ab(), Warith.ALAB);
        }
    }

    private void mirathAljad(boolean far3_warith_dhakar, boolean far3_warith_ontha, boolean alikhwa_alashika_wa_li_ab) {
        if (!mInput.aljad()) return;

        String sharh;
        int bast;
        int maqam;
        int ro2os;
        boolean ta3seeb;

        if (!mInput.alab()) {        // no hajb by ab
            ro2os = 1;
            boolean tassawi = true;
            if (far3_warith_dhakar) {
                sharh = Warith.ALJAD.getSharhPrefix();
                sharh += "السدس 1\\6 فرضا فقط";
                bast = 1;
                maqam = 6;
                ta3seeb = false;
            } else {            // Here be dragons الجد يرث فرضا وتعصيبا أو تعصيبا فقط
                ta3seeb = true;
                if (far3_warith_ontha) {
                    sharh = Warith.ALJAD.getSharhPrefix();
                    sharh += "السدس 1\\6 فرضا + ";
                    bast = 1;
                    maqam = 6;
                } else {             // الجد يرث الباقي فقط
                    sharh = "";
                    bast = 0;
                    maqam = 1;
                }
                if (alikhwa_alashika_wa_li_ab) {        // الجد يُقاسم الإخوة
                    if (mInput.get_alikhwa_alashika() > 0) {
                        ro2os += mInput.get_alikhwa_alashika();
                        if (mInput.get_alakhawat_ashakikat() > 0) {
                            ro2os *= 2;
                            ro2os += mInput.get_alakhawat_ashakikat();
                            mTassawi = tassawi = false;
                        } else {
                            tassawi = true;
                        }
                        sharh += Warith.ALJAD.getSharhPrefix(1, Warith.ALIKHWA_ALASHIKA, mInput.get_alikhwa_alashika(), tassawi);
                    } else {  // mInput.get_alikhwa_li_ab() > 0
                        ro2os += mInput.get_alikhwa_li_ab();
                        if (mInput.get_alakhawat_li_ab() > 0) {
                            ro2os *= 2;
                            ro2os += mInput.get_alikhwa_li_ab();
                            mTassawi = tassawi = false;
                        } else {
                            tassawi = true;
                        }
                        sharh += Warith.ALJAD.getSharhPrefix(1, Warith.ALIKHWA_LI_AB, mInput.get_alikhwa_li_ab(), tassawi);
                    }
                }
                else {       // الجد يرث الباقي لوحده
                    sharh = Warith.ALJAD.getSharhPrefix();
                }
                sharh += "الباقي تعصيبا بالنفس";
            }
            addMirath(new Mirath(Warith.ALJAD, sharh, bast, maqam, ta3seeb, ro2os, tassawi));
        }

        // alhajb by aljad, we add it even if there is hajb by ab for detailed info
        if (mInput.get_alikhwa_li_om() >= 1) {
            addHajb(Warith.ALIKHWA_LI_OM, mInput.get_alikhwa_li_om(), Warith.ALJAD);
        }
        if (mInput.get_alakhawat_li_om() >= 1) {
            addHajb(Warith.ALAKHAWAT_LI_OM, mInput.get_alakhawat_li_om(), Warith.ALJAD);
        }
        if (mInput.get_abna_alikhwa_alashika() >= 1) {
            addHajb(Warith.ABNA_ALIKHWA_ALASHIKA, mInput.get_abna_alikhwa_alashika(), Warith.ALJAD);
        }
        if (mInput.get_abna_alikhwa_li_ab() >= 1) {
            addHajb(Warith.ABNA_ALIKHWA_LI_AB, mInput.get_abna_alikhwa_li_ab(), Warith.ALJAD);
        }
        if (mInput.get_ala3mam_alashika() >= 1) {
            addHajb(Warith.ALA3MAM_ALASHIKA, mInput.get_ala3mam_alashika(), Warith.ALJAD);
        }
        if (mInput.get_ala3mam_li_ab() >= 1) {
            addHajb(Warith.ALA3MAM_LI_AB, mInput.get_ala3mam_li_ab(), Warith.ALJAD);
        }
        if (mInput.get_abna_ala3mam_alashika() >= 1) {
            addHajb(Warith.ABNA_ALA3MAM_ALASHIKA, mInput.get_abna_ala3mam_alashika(), Warith.ALJAD);
        }
        if (mInput.get_abna_ala3mam_li_ab() >= 1) {
            addHajb(Warith.ABNA_ALA3MAM_LI_AB, mInput.get_abna_ala3mam_li_ab(), Warith.ALJAD);
        }
    }

    private void mirathAlom(boolean far3_warith, boolean jam3_alikhwa) {
        if (!mInput.alom()) return;

        int maqam;
        boolean ta3seeb;

        String sharh = Warith.ALOM.getSharhPrefix();
        if (jam3_alikhwa || far3_warith) {
            sharh += "السدس 1\\6 فرضا";
            maqam = 6;
            ta3seeb = false;
        } else if ((mInput.alab()) && ((mInput.get_azawjat() > 0) || (mInput.zawj()))) {
            sharh = "الأم ترث ثلث 1\\3 الباقي";
            maqam = 3;
            ta3seeb = true;
            mSpecialCase = "هذه مسألة الغرّاوين"; // TODO make it an enum type
        } else {
            sharh += "الثلث 1\\3 فرضا";
            maqam = 3;
            ta3seeb = false;
        }
        addMirath(new Mirath(Warith.ALOM, sharh, 1, maqam, ta3seeb));

        // alhajb bi mInput.alom()
        if (mInput.aljadah_li_ab()) {
            addHajb(Warith.ALJADAH_LI_AB, Warith.ALOM);
        }
        if (mInput.aljadah_li_om()) {
            addHajb(Warith.ALJADAH_LI_OM, Warith.ALOM);
        }
    }

    private void mirathAljadat() {
        if (mInput.alom()) return;

        String sharh;
        int ro2os;

        if (mInput.aljadah_li_ab() && !mInput.alab()) {
            sharh = "الجدة لأب ";
            if (mInput.aljadah_li_om()) {
                sharh += "تشترك بالتساوي مع الجدة لأم في السدس 1\\6 فرضا";
                ro2os = 2;
            } else {
                sharh += "ترث السدس 1\\6 فرضا";
                ro2os = 1;
            }
            addMirath(new Mirath(Warith.ALJADAH_LI_AB, sharh, 1, 6, ro2os));
        }

        if (mInput.aljadah_li_om()) {
            sharh = "الجدة لأم ";
            if (mInput.aljadah_li_ab() && !mInput.alab()) {
                sharh += "تشترك بالتساوي مع الجدة لأب في السدس 1\\6 فرضا";
                ro2os = 2;
            } else {
                sharh += "ترث السدس 1\\6 فرضا";
                ro2os = 1;
            }
            addMirath(new Mirath(Warith.ALJADAH_LI_OM, sharh, 1, 6, ro2os));
        }
    }

    private void mirathAwladAlom(boolean far3_warith, boolean asl_warith_dhaker) {
        int nbr_a = mInput.get_alikhwa_li_om();
        int nbr_b = mInput.get_alakhawat_li_om();
        int awlad_alom = nbr_a + nbr_b;
        if (awlad_alom == 0) return;

        String sharh;

        if (far3_warith) {
            if (nbr_a > 0) {
                addHajb(Warith.ALIKHWA_LI_OM, nbr_a, "الفرع الوارث");
            }
            if (nbr_b > 0) {
                addHajb(Warith.ALAKHAWAT_LI_OM, nbr_b, "الفرع الوارث");
            }
        } else if (!asl_warith_dhaker) { // hajb by asl warith dhaker done above
            if (awlad_alom > 1) {
                if (nbr_a > 0) {
                    if (nbr_b > 0) {
                        sharh = Warith.ALAKHAWAT_LI_OM.getSharhPrefix(nbr_b, Warith.ALIKHWA_LI_OM, nbr_a, true);
                        sharh += "الثلث 1\\3 فرضا";
                        addMirath(new Mirath(Warith.ALAKHAWAT_LI_OM, nbr_b, sharh, 1, 3, awlad_alom));

                        sharh = Warith.ALIKHWA_LI_OM.getSharhPrefix(nbr_a, Warith.ALAKHAWAT_LI_OM, nbr_b, true);
                        sharh += "الثلث 1\\3 فرضا";
                        addMirath(new Mirath(Warith.ALIKHWA_LI_OM, nbr_a, sharh, 1, 3, awlad_alom));
                    }
                    else {
                        sharh = Warith.ALIKHWA_LI_OM.getSharhPrefix(nbr_a);
                        sharh += "الثلث 1\\3 فرضا";
                        addMirath(new Mirath(Warith.ALIKHWA_LI_OM, nbr_a, sharh, 1, 3));
                    }
                }
                else if (nbr_b > 0) {
                    sharh = Warith.ALAKHAWAT_LI_OM.getSharhPrefix(nbr_b);
                    sharh += "الثلث 1\\3 فرضا";
                    addMirath(new Mirath(Warith.ALAKHAWAT_LI_OM, nbr_b, sharh, 1, 3));
                }
            } else if (nbr_a == 1) {
                sharh = Warith.ALIKHWA_LI_OM.getSharhPrefix();
                sharh += "السدس 1\\6 فرضا";
                addMirath(new Mirath(Warith.ALIKHWA_LI_OM, sharh, 1, 6));
            } else { // nbr_b == 1
                sharh = Warith.ALAKHAWAT_LI_OM.getSharhPrefix();
                sharh += "السدس 1\\6 فرضا";
                addMirath(new Mirath(Warith.ALAKHAWAT_LI_OM, sharh, 1, 6));
            }
        }

    }

    private void mirathAlbanat() {
        if (mInput.get_alabna() > 0) return;

        String sharh;
        int bast;
        int maqam;
        int nbr = mInput.get_albanat();

        // mirath albanat ma3a 3adam wojud alabna
        if (nbr == 1) {
            sharh = Warith.ALBANAT.getSharhPrefix();
            sharh += "النصف 1\\2 فرضا";
            addMirath(new Mirath(Warith.ALBANAT, sharh, 1, 2));
            if ((mInput.get_abna_alabna() == 0) && (mInput.get_banat_alabna() > 0)) {
                sharh = Warith.BANAT_ALABNA.getSharhPrefix(mInput.get_banat_alabna());
                sharh += "السدس 1\\6 تتمة الثلثين فرضا";
                addMirath(new Mirath(Warith.BANAT_ALABNA, mInput.get_banat_alabna(), sharh, 1, 6));
            }
        } else if (nbr >= 2) {
            sharh = Warith.ALBANAT.getSharhPrefix(nbr);
            sharh += "الثلثين 2\\3 فرضا";
            addMirath(new Mirath(Warith.ALBANAT, nbr, sharh, 2, 3));
            if ((mInput.get_abna_alabna() == 0) && (mInput.get_banat_alabna() > 0)) {
                addHajb(Warith.BANAT_ALABNA, mInput.get_banat_alabna(), "الجمع من البنات");
            }
        } else { // nbr == 0
            // same logic holds for 2nd generation if present
            if ((mInput.get_abna_alabna() == 0) && (mInput.get_banat_alabna() > 0)) {
                sharh = Warith.BANAT_ALABNA.getSharhPrefix(mInput.get_banat_alabna());
                if (mInput.get_banat_alabna() == 1) {
                    bast = 1; maqam = 2;
                    sharh += "النصف 1\\2 فرضا";
                } else {
                    bast = 2; maqam = 3;
                    sharh += "الثلثين 2\\3 فرضا";
                }
                addMirath(new Mirath(Warith.BANAT_ALABNA, mInput.get_banat_alabna(), sharh, bast, maqam));
            }
        }
    }

    private void mirathAlkhawatAshakikat(boolean far3_warith_dhakar, boolean far3_warith_ontha) {
        // mirath alakhawat ashakikat 3inda 3adam wojud alikhwa alashika
        int nbr = mInput.get_alakhawat_ashakikat();
        if ((nbr == 0) || mInput.alab() || far3_warith_dhakar || (mInput.get_alikhwa_alashika() > 0)) return;

        int bast;
        int maqam;

        String sharh = Warith.ALAKHAWAT_ASHAKIKAT.getSharhPrefix(nbr);
        if (!far3_warith_ontha) {
            if (nbr == 1) {
                bast = 1; maqam = 2;
                sharh += "النصف 1\\2 فرضا";
            } else { // (nbr >= 2)
                bast = 2; maqam = 3;
                sharh +="الثلثين 2\\3 فرضا";
            }
            addMirath(new Mirath(Warith.ALAKHAWAT_ASHAKIKAT, nbr, sharh, bast, maqam));
        }
        else { // presence of far3_warith_ontha
            sharh += "الباقي تعصيبا مع الغير";
            addMirath(new Mirath(Warith.ALAKHAWAT_ASHAKIKAT, nbr, sharh));
        }
    }

    private void mirathAlakhawatLiAb(boolean far3_warith_dhakar, boolean far3_warith_ontha, boolean alikhwa_alashika_wa_li_ab) {
        // mirath alakhawat li ab 3inda 3adam wojud alikhwa
        int nbr = mInput.get_alakhawat_li_ab();
        if ((nbr == 0) || mInput.alab() || far3_warith_dhakar || alikhwa_alashika_wa_li_ab) return;

        String sharh;
        int bast;
        int maqam;

        if (!far3_warith_ontha) {
            if (mInput.get_alakhawat_ashakikat() > 1) {
                addHajb(Warith.ALAKHAWAT_LI_AB, nbr, Warith.ALAKHAWAT_ASHAKIKAT.getName(mInput.get_alakhawat_ashakikat()));
            } else {
                sharh = Warith.ALAKHAWAT_LI_AB.getSharhPrefix(nbr);
                if (mInput.get_alakhawat_ashakikat() == 0) {
                    if (nbr == 1) {
                        bast = 1; maqam = 2;
                        sharh += "النصف 1\\2 فرضا";
                    } else { // (nbr >= 2)
                        bast = 2; maqam = 3;
                        sharh += "الثلثين 2\\3 فرضا";
                    }
                } else { // (mInput.get_alakhawat_ashakikat() == 1)
                    bast = 1; maqam = 6;
                    sharh += "السدس 1\\6 تتمة الثلثين فرضا";
                }
                addMirath(new Mirath(Warith.ALAKHAWAT_LI_AB, nbr, sharh, bast, maqam));
            }
        } else { // far3_warith_ontha
            if (mInput.get_alakhawat_ashakikat() == 0) {
                sharh = Warith.ALAKHAWAT_LI_AB.getSharhPrefix(nbr);
                sharh += " الباقي تعصيبا مع الغير";
                addMirath(new Mirath(Warith.ALAKHAWAT_LI_AB, nbr, sharh));
            } else {
                addHajb(Warith.ALAKHAWAT_LI_AB, nbr, Warith.ALAKHAWAT_ASHAKIKAT, mInput.get_alakhawat_ashakikat());
            }
        }
    }

    private void mirathAlabnaWaAlbanat() {
        //mirath alabna + albanat 3inda woujoud alabna
        int nbr_a = mInput.get_alabna();
        if (nbr_a == 0) return;

        String sharh;
        int nbr_b = mInput.get_albanat();
        if (nbr_b == 0) {
            sharh = Warith.ALABNA.getSharhPrefix(nbr_a);
            sharh += " الباقي تعصيبا بالنفس";
            addMirath(new Mirath(Warith.ALABNA, nbr_a, sharh));
        } else {
            int ro2os = 2 * nbr_a + nbr_b;
            mTassawi = false;
            sharh = Warith.ALABNA.getSharhPrefix(nbr_a, Warith.ALBANAT, nbr_b, false);
            sharh += "الباقي تعصيبا بالنفس";
            addMirath(new Mirath(Warith.ALABNA, nbr_a, sharh, ro2os));

            sharh = Warith.ALBANAT.getSharhPrefix(nbr_b, Warith.ALABNA, nbr_a, false);
            sharh += "الباقي تعصيبا بالغير";
            addMirath(new Mirath(Warith.ALBANAT, nbr_b, sharh, ro2os));
        }

        // hajb by alabna
        if (mInput.get_abna_alabna() > 0) {
            addHajb(Warith.ABNA_ALABNA, mInput.get_abna_alabna(), Warith.ALABNA, nbr_a);
        }
        if (mInput.get_banat_alabna() > 0) {
            addHajb(Warith.BANAT_ALABNA, mInput.get_banat_alabna(), Warith.ALABNA, nbr_a);
        }
        if (mInput.get_alikhwa_alashika() > 0) {
            addHajb(Warith.ALIKHWA_ALASHIKA, mInput.get_alikhwa_alashika(), Warith.ALABNA, nbr_a);
        }
        if (mInput.get_alakhawat_ashakikat() > 0) {
            addHajb(Warith.ALAKHAWAT_ASHAKIKAT, mInput.get_alakhawat_ashakikat(), Warith.ALABNA, nbr_a);
        }
        if (mInput.get_alikhwa_li_ab() > 0) {
            addHajb(Warith.ALIKHWA_LI_AB, mInput.get_alikhwa_li_ab(), Warith.ALABNA, nbr_a);
        }
        if (mInput.get_alakhawat_li_ab() > 0) {
            addHajb(Warith.ALAKHAWAT_LI_AB, mInput.get_alakhawat_li_ab(), Warith.ALABNA, nbr_a);
        }
        //الحجب تم بالفرع الوارث سابقا في ميراث أولاد الأم
           /* if (mInput.get_alikhwa_li_om() > 0) {
                addHajb(Warith.ALIKHWA_LI_OM, mInput.get_alikhwa_li_om(), Warith.ALABNA, nbr_a);
            }
            if (mInput.get_alakhawat_li_om() > 0) {
                addHajb(Warith.ALAKHAWAT_LI_OM, mInput.get_alakhawat_li_om(), Warith.ALABNA, nbr_a);
            } */
        if (mInput.get_abna_alikhwa_alashika() > 0) {
            addHajb(Warith.ABNA_ALIKHWA_ALASHIKA, mInput.get_abna_alikhwa_alashika(), Warith.ALABNA, nbr_a);
        }
        if (mInput.get_abna_alikhwa_li_ab() > 0) {
            addHajb(Warith.ABNA_ALIKHWA_LI_AB, mInput.get_abna_alikhwa_li_ab(), Warith.ALABNA, nbr_a);
        }
        if (mInput.get_ala3mam_alashika() > 0) {
            addHajb(Warith.ALA3MAM_ALASHIKA, mInput.get_ala3mam_alashika(), Warith.ALABNA, nbr_a);
        }
        if (mInput.get_ala3mam_li_ab() > 0) {
            addHajb(Warith.ALA3MAM_LI_AB, mInput.get_ala3mam_li_ab(), Warith.ALABNA, nbr_a);
        }
        if (mInput.get_abna_ala3mam_alashika() > 0) {
            addHajb(Warith.ABNA_ALA3MAM_ALASHIKA, mInput.get_abna_ala3mam_alashika(), Warith.ALABNA, nbr_a);
        }
        if (mInput.get_abna_ala3mam_li_ab() > 0) {
            addHajb(Warith.ABNA_ALA3MAM_LI_AB, mInput.get_abna_ala3mam_li_ab(), Warith.ALABNA, nbr_a);
        }
    }

    private void mirathAbnaWaBanatAlabna() {
        int nbr_a = mInput.get_abna_alabna();
        if ((nbr_a == 0) || (mInput.get_alabna() > 0)) return;

        String sharh;
        int nbr_b = mInput.get_banat_alabna();

        if (nbr_b == 0) {
            sharh = Warith.ABNA_ALABNA.getSharhPrefix(nbr_a);
            sharh += "الباقي تعصيبا بالنفس";
            addMirath(new Mirath(Warith.ABNA_ALABNA, nbr_a, sharh));
        } else {
            int ro2os = 2 * nbr_a + nbr_b;
            mTassawi = false;
            sharh = Warith.ABNA_ALABNA.getSharhPrefix(nbr_a, Warith.BANAT_ALABNA, nbr_b, false);
            sharh += "الباقي تعصيبا بالنفس";
            addMirath(new Mirath(Warith.ABNA_ALABNA, nbr_a, sharh, ro2os));

            sharh = Warith.BANAT_ALABNA.getSharhPrefix(nbr_b, Warith.ABNA_ALABNA, nbr_a, false);
            sharh += "الباقي تعصيبا بالغير";
            addMirath(new Mirath(Warith.BANAT_ALABNA, nbr_b, sharh, ro2os));
        }

        // hajb by abna alabna
        if (mInput.get_alikhwa_alashika() > 0) {
            addHajb(Warith.ALIKHWA_ALASHIKA, mInput.get_alikhwa_alashika(), Warith.ABNA_ALABNA, nbr_a);
        }
        if (mInput.get_alakhawat_ashakikat() > 0) {
            addHajb(Warith.ALAKHAWAT_ASHAKIKAT, mInput.get_alakhawat_ashakikat(), Warith.ABNA_ALABNA, nbr_a);
        }
        if (mInput.get_alikhwa_li_ab() > 0) {
            addHajb(Warith.ALIKHWA_LI_AB, mInput.get_alikhwa_li_ab(), Warith.ABNA_ALABNA, nbr_a);
        }
        if (mInput.get_alakhawat_li_ab() > 0) {
            addHajb(Warith.ALAKHAWAT_LI_AB, mInput.get_alakhawat_li_ab(), Warith.ABNA_ALABNA, nbr_a);
        }
        //الحجب تم بالفرع الوارث سابقا في ميراث أولاد الأم
         /*   if (mInput.get_alikhwa_li_om() > 0) {
                addHajb(Warith.ALIKHWA_LI_OM, mInput.get_alikhwa_li_om(), Warith.ABNA_ALABNA, nbr_a);
            }
            if (mInput.get_alakhawat_li_om() > 0) {
                addHajb(Warith.ALAKHAWAT_LI_OM, mInput.get_alakhawat_li_om(), Warith.ABNA_ALABNA, nbr_a);
            }*/
        if (mInput.get_abna_alikhwa_alashika() > 0) {
            addHajb(Warith.ABNA_ALIKHWA_ALASHIKA, mInput.get_abna_alikhwa_alashika(), Warith.ABNA_ALABNA, nbr_a);
        }
        if (mInput.get_abna_alikhwa_li_ab() > 0) {
            addHajb(Warith.ABNA_ALIKHWA_LI_AB, mInput.get_abna_alikhwa_li_ab(), Warith.ABNA_ALABNA, nbr_a);
        }
        if (mInput.get_ala3mam_alashika() > 0) {
            addHajb(Warith.ALA3MAM_ALASHIKA, mInput.get_ala3mam_alashika(), Warith.ABNA_ALABNA, nbr_a);
        }
        if (mInput.get_ala3mam_li_ab() > 0) {
            addHajb(Warith.ALA3MAM_LI_AB, mInput.get_ala3mam_li_ab(), Warith.ABNA_ALABNA, nbr_a);
        }
        if (mInput.get_abna_ala3mam_alashika() > 0) {
            addHajb(Warith.ABNA_ALA3MAM_ALASHIKA, mInput.get_abna_ala3mam_alashika(), Warith.ABNA_ALABNA, nbr_a);
        }
        if (mInput.get_abna_ala3mam_li_ab() > 0) {
            addHajb(Warith.ABNA_ALA3MAM_LI_AB, mInput.get_abna_ala3mam_li_ab(), Warith.ABNA_ALABNA, nbr_a);
        }
    }

    private void mirathAlikhwaAlashika(boolean far3_warith_dhakar) {
        int nbr_a = mInput.get_alikhwa_alashika();
        if (nbr_a == 0 || far3_warith_dhakar || mInput.alab()) return;

        String sharh;
        int nbr_b = mInput.get_alakhawat_ashakikat();
        int ro2os;

        if (nbr_b == 0) {
            ro2os = nbr_a;
            if (mInput.aljad()) {
                ro2os++;
                sharh = Warith.ALIKHWA_ALASHIKA.getSharhPrefix(nbr_a, Warith.ALJAD, 1, true);
            }
            else {
                sharh = Warith.ALIKHWA_ALASHIKA.getSharhPrefix(nbr_a);
            }
            sharh += "الباقي تعصيبا بالنفس";
            addMirath(new Mirath(Warith.ALIKHWA_ALASHIKA, nbr_a, sharh, ro2os, true));
        } else {
            String sharh2;
            mTassawi = false;
            sharh = Warith.ALIKHWA_ALASHIKA.getSharhPrefix(nbr_a, Warith.ALAKHAWAT_ASHAKIKAT, nbr_b, false);
            sharh += "الباقي تعصيبا بالنفس";
            sharh2 = Warith.ALAKHAWAT_ASHAKIKAT.getSharhPrefix(nbr_b, Warith.ALIKHWA_ALASHIKA, nbr_a, false);
            sharh2 += "الباقي تعصيبا بالغير";
            ro2os = 2 * nbr_a + nbr_b;
            if (mInput.aljad()) {
                sharh += " ومقاسمة مع الجد";
                sharh2 += " ومقاسمة مع الجد";
                ro2os += 2;
            }
            addMirath(new Mirath(Warith.ALIKHWA_ALASHIKA, nbr_a, sharh, ro2os));
            addMirath(new Mirath(Warith.ALAKHAWAT_ASHAKIKAT, nbr_b, sharh2, ro2os));
        }

        // الحجب بالإخوة الأشقاء
        if (mInput.get_alikhwa_li_ab() > 0) {
            addHajb(Warith.ALIKHWA_LI_AB, mInput.get_alikhwa_li_ab(), Warith.ALIKHWA_ALASHIKA, nbr_a);
        }
        if (mInput.get_alakhawat_li_ab() > 0) {
            addHajb(Warith.ALAKHAWAT_LI_AB, mInput.get_alakhawat_li_ab(), Warith.ALIKHWA_ALASHIKA, nbr_a);
        }
        if (mInput.get_abna_alikhwa_alashika() > 0) {
            addHajb(Warith.ABNA_ALIKHWA_ALASHIKA, mInput.get_abna_alikhwa_alashika(), Warith.ALIKHWA_ALASHIKA, nbr_a);
        }
        if (mInput.get_abna_alikhwa_li_ab() > 0) {
            addHajb(Warith.ABNA_ALIKHWA_LI_AB, mInput.get_abna_alikhwa_alashika(), Warith.ALIKHWA_ALASHIKA, nbr_a);
        }
        if (mInput.get_ala3mam_alashika() > 0) {
            addHajb(Warith.ALA3MAM_ALASHIKA, mInput.get_ala3mam_alashika(), Warith.ALIKHWA_ALASHIKA, nbr_a);
        }
        if (mInput.get_ala3mam_li_ab() > 0) {
            addHajb(Warith.ALA3MAM_LI_AB, mInput.get_ala3mam_li_ab(), Warith.ALIKHWA_ALASHIKA, nbr_a);
        }
        if (mInput.get_abna_ala3mam_alashika() > 0) {
            addHajb(Warith.ABNA_ALA3MAM_ALASHIKA, mInput.get_abna_ala3mam_alashika(), Warith.ALIKHWA_ALASHIKA, nbr_a);
        }
        if (mInput.get_abna_ala3mam_li_ab() > 0) {
            addHajb(Warith.ABNA_ALA3MAM_LI_AB, mInput.get_abna_ala3mam_li_ab(), Warith.ALIKHWA_ALASHIKA, nbr_a);
        }
    }

    private void mirathAlikhwaLiAb(boolean far3_warith_dhakar) {
        int nbr_a = mInput.get_alikhwa_li_ab();
        if ((nbr_a == 0) || far3_warith_dhakar || mInput.alab() || (mInput.get_alikhwa_alashika() > 0)) return;

        String sharh;
        int nbr_b = mInput.get_alakhawat_li_ab();
        int ro2os;

        if (nbr_b == 0) {
            ro2os = nbr_a;
            if (mInput.aljad()) {
                ro2os++;
                sharh = Warith.ALIKHWA_LI_AB.getSharhPrefix(nbr_a, Warith.ALJAD, 1, true);
            }
            else {
                sharh = Warith.ALIKHWA_LI_AB.getSharhPrefix(nbr_a);
            }
            sharh += "الباقي تعصيبا بالنفس";
            addMirath(new Mirath(Warith.ALIKHWA_LI_AB, nbr_a, sharh, ro2os, true));
        } else {
            String sharh2;
            mTassawi = false;
            sharh = Warith.ALIKHWA_LI_AB.getSharhPrefix(nbr_a, Warith.ALAKHAWAT_LI_AB, nbr_b, false);
            sharh += "الباقي تعصيبا بالنفس";
            sharh2 = Warith.ALAKHAWAT_LI_AB.getSharhPrefix(nbr_b, Warith.ALIKHWA_LI_AB, nbr_a, false);
            sharh2 += "الباقي تعصيبا بالغير";
            ro2os = 2 * nbr_a + nbr_b;
            if (mInput.aljad()) {
                sharh += " ومقاسمة مع الجد";
                sharh2 += " ومقاسمة مع الجد";
                ro2os += 2;
            }
            addMirath(new Mirath(Warith.ALIKHWA_LI_AB, nbr_a, sharh, ro2os));
            addMirath(new Mirath(Warith.ALAKHAWAT_LI_AB, nbr_b, sharh2, ro2os));
        }

        // alhajb by alikhwa li ab
        if (mInput.get_abna_alikhwa_alashika() > 0) {
            addHajb(Warith.ABNA_ALIKHWA_ALASHIKA, mInput.get_abna_alikhwa_alashika(), Warith.ALIKHWA_LI_AB, nbr_a);
        }
        if (mInput.get_abna_alikhwa_li_ab() > 0) {
            addHajb(Warith.ABNA_ALIKHWA_LI_AB, mInput.get_abna_alikhwa_li_ab(), Warith.ALIKHWA_LI_AB, nbr_a);
        }
        if (mInput.get_ala3mam_alashika() > 0) {
            addHajb(Warith.ALA3MAM_ALASHIKA, mInput.get_ala3mam_alashika(), Warith.ALIKHWA_LI_AB, nbr_a);
        }
        if (mInput.get_ala3mam_li_ab() > 0) {
            addHajb(Warith.ALA3MAM_LI_AB, mInput.get_ala3mam_li_ab(), Warith.ALIKHWA_LI_AB, nbr_a);
        }
        if (mInput.get_abna_ala3mam_alashika() > 0) {
            addHajb(Warith.ABNA_ALA3MAM_ALASHIKA, mInput.get_abna_ala3mam_alashika(), Warith.ALIKHWA_LI_AB, nbr_a);
        }
        if (mInput.get_abna_ala3mam_li_ab() > 0) {
            addHajb(Warith.ABNA_ALA3MAM_LI_AB, mInput.get_abna_ala3mam_li_ab(), Warith.ALIKHWA_LI_AB, nbr_a);
        }
    }

    private void mirathAbnaAlikhwaAlashika(boolean far3_wa_asl_warith_dhaker, boolean alikhwa_alashika_wa_li_ab) {
        int nbr = mInput.get_abna_alikhwa_alashika();
        if ((nbr == 0) || far3_wa_asl_warith_dhaker || alikhwa_alashika_wa_li_ab) return;

        String sharh = Warith.ABNA_ALIKHWA_ALASHIKA.getName(nbr);
        sharh += "الباقي تعصيبا بالنفس";
        addMirath(new Mirath(Warith.ABNA_ALIKHWA_ALASHIKA, nbr, sharh));

        // alhajb by abna alikhwa alashika
        if (mInput.get_abna_alikhwa_li_ab() > 0) {
            addHajb(Warith.ABNA_ALIKHWA_LI_AB, mInput.get_abna_alikhwa_li_ab(), Warith.ABNA_ALIKHWA_ALASHIKA, nbr);
        }
        if (mInput.get_ala3mam_alashika() > 0) {
            addHajb(Warith.ALA3MAM_ALASHIKA, mInput.get_ala3mam_alashika(), Warith.ABNA_ALIKHWA_ALASHIKA, nbr);
        }
        if (mInput.get_ala3mam_li_ab() > 0) {
            addHajb(Warith.ALA3MAM_LI_AB, mInput.get_ala3mam_li_ab(), Warith.ABNA_ALIKHWA_ALASHIKA, nbr);
        }
        if (mInput.get_abna_ala3mam_alashika() > 0) {
            addHajb(Warith.ABNA_ALA3MAM_ALASHIKA, mInput.get_abna_ala3mam_alashika(), Warith.ABNA_ALIKHWA_ALASHIKA, nbr);
        }
        if (mInput.get_abna_ala3mam_li_ab() > 0) {
            addHajb(Warith.ABNA_ALA3MAM_LI_AB, mInput.get_abna_ala3mam_li_ab(), Warith.ABNA_ALIKHWA_ALASHIKA, nbr);
        }
    }

    private void mirathAbnaAlikhwaLiAb(boolean far3_wa_asl_warith_dhaker, boolean alikhwa_alashika_wa_li_ab) {
        int nbr = mInput.get_abna_alikhwa_li_ab();
        if ((nbr == 0) || far3_wa_asl_warith_dhaker || alikhwa_alashika_wa_li_ab || (mInput.get_abna_alikhwa_alashika() > 0)) return;

        String sharh = Warith.ABNA_ALIKHWA_LI_AB.getName(nbr);
        sharh += "الباقي تعصيبا بالنفس";
        addMirath(new Mirath(Warith.ABNA_ALIKHWA_LI_AB, nbr, sharh));

        // alhajb by abna alikhwa li_ab
        if (mInput.get_ala3mam_alashika() > 0) {
            addHajb(Warith.ALA3MAM_ALASHIKA, mInput.get_ala3mam_alashika(), Warith.ABNA_ALIKHWA_LI_AB, nbr);
        }
        if (mInput.get_ala3mam_li_ab() > 0) {
            addHajb(Warith.ALA3MAM_LI_AB, mInput.get_ala3mam_li_ab(), Warith.ABNA_ALIKHWA_LI_AB, nbr);
        }
        if (mInput.get_abna_ala3mam_alashika() > 0) {
            addHajb(Warith.ABNA_ALA3MAM_ALASHIKA, mInput.get_abna_ala3mam_alashika(), Warith.ABNA_ALIKHWA_LI_AB, nbr);
        }
        if (mInput.get_abna_ala3mam_li_ab() > 0) {
            addHajb(Warith.ABNA_ALA3MAM_LI_AB, mInput.get_abna_ala3mam_li_ab(), Warith.ABNA_ALIKHWA_LI_AB, nbr);
        }
    }

    private void mirathAla3mamAlashika(boolean far3_wa_asl_warith_dhaker, boolean alikhwa_wa_abna_alikhwa) {
        int nbr = mInput.get_ala3mam_alashika();
        if ((nbr == 0) || far3_wa_asl_warith_dhaker || alikhwa_wa_abna_alikhwa) return;

        String sharh = Warith.ALA3MAM_ALASHIKA.getName(nbr);
        sharh += "الباقي تعصيبا بالنفس";
        addMirath(new Mirath(Warith.ALA3MAM_ALASHIKA, nbr, sharh));

        // alhajb by abna alikhwa alashika
        if (mInput.get_ala3mam_li_ab() > 0) {
            addHajb(Warith.ALA3MAM_LI_AB, mInput.get_ala3mam_li_ab(), Warith.ALA3MAM_ALASHIKA, nbr);
        }
        if (mInput.get_abna_ala3mam_alashika() > 0) {
            addHajb(Warith.ABNA_ALA3MAM_ALASHIKA, mInput.get_abna_ala3mam_alashika(), Warith.ALA3MAM_ALASHIKA, nbr);
        }
        if (mInput.get_abna_ala3mam_li_ab() > 0) {
            addHajb(Warith.ABNA_ALA3MAM_LI_AB, mInput.get_abna_ala3mam_li_ab(), Warith.ALA3MAM_ALASHIKA, nbr);
        }
    }

    private void mirathAla3mamLiAb(boolean far3_wa_asl_warith_dhaker, boolean alikhwa_wa_abna_alikhwa) {
        int nbr = mInput.get_ala3mam_li_ab();
        if ((nbr == 0) || far3_wa_asl_warith_dhaker || alikhwa_wa_abna_alikhwa && (mInput.get_ala3mam_alashika() > 0)) return;

        String sharh = Warith.ALA3MAM_LI_AB.getName(nbr);
        sharh += "الباقي تعصيبا بالنفس";
        addMirath(new Mirath(Warith.ALA3MAM_LI_AB, nbr, sharh));

        // alhajb by abna alikhwa li_ab
        if (mInput.get_abna_ala3mam_alashika() > 0) {
            addHajb(Warith.ABNA_ALA3MAM_ALASHIKA, mInput.get_abna_ala3mam_alashika(), Warith.ALA3MAM_LI_AB, nbr);
        }
        if (mInput.get_abna_ala3mam_li_ab() > 0) {
            addHajb(Warith.ABNA_ALA3MAM_LI_AB, mInput.get_abna_ala3mam_li_ab(), Warith.ALA3MAM_LI_AB, nbr);
        }
    }

    private void mirathAbnaAla3mamAlashika(boolean far3_wa_asl_warith_dhaker, boolean alikhwa_wa_abna_alikhwa, boolean ala3mam) {
        int nbr = mInput.get_abna_ala3mam_alashika();
        if ((mInput.get_abna_ala3mam_alashika() == 0) || far3_wa_asl_warith_dhaker || alikhwa_wa_abna_alikhwa || ala3mam) return;

        String sharh = Warith.ABNA_ALA3MAM_ALASHIKA.getName(mInput.get_abna_ala3mam_alashika());
        sharh += "الباقي تعصيبا بالنفس";
        addMirath(new Mirath(Warith.ABNA_ALA3MAM_ALASHIKA, nbr, sharh));

        // alhajb by abna alikhwa li_ab
        if (mInput.get_abna_ala3mam_li_ab() > 0) {
            addHajb(Warith.ABNA_ALA3MAM_LI_AB, mInput.get_abna_ala3mam_li_ab(), Warith.ABNA_ALA3MAM_ALASHIKA, nbr);
        }
    }

    private void mirathAbnaAla3mamLiAb(boolean far3_wa_asl_warith_dhaker, boolean alikhwa_wa_abna_alikhwa, boolean ala3mam) {
        int nbr = mInput.get_abna_ala3mam_li_ab();
        if ((nbr == 0) || far3_wa_asl_warith_dhaker || alikhwa_wa_abna_alikhwa || ala3mam || (mInput.get_abna_ala3mam_alashika() > 0))  return;

        String sharh = Warith.ABNA_ALA3MAM_LI_AB.getName(mInput.get_abna_ala3mam_li_ab());
        sharh += "الباقي تعصيبا بالنفس";
        addMirath(new Mirath(Warith.ABNA_ALA3MAM_LI_AB, nbr, sharh));
    }

    private boolean noInput() {
        return mWarathah.size() == 0;
    }

    private void hissabAslAndRo2os() {
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

    private void hissabAshomAndBaqi() {
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

    private void hissabAnsiba() {
        // قسمة الأسهم في الجدول
        int nassib;
        ArrayList<Mirath> all = new ArrayList<Mirath>();

        all.addAll(mWarathah);
        all.addAll(mMahjoobin);
        for (Mirath m : all) {          // Here be dragons
            StringBuilder nassibTxt = new StringBuilder();

            if (m.isFardh()) {
                if (m.isTa3seeb()) {
                    // assert m.getBast() == 1
                    if (m.isTholuthAlbaqi()) { // الأم في الغراوين والجد مع الإخوة
                        // الأم في الغراوين (ص 19) سهمها دوما 1
                        nassib = 1;
                        nassibTxt.append(mBaqi).append(" * 1\\3 = 1");
                        // TODO تفصيل الجد مع الإخوة في حال أخذ الجد ثلث الباقي بالمفاضلة يجب حسابه
                    }
                    else {                     // حالات الأب والجد يرثان 1\6 + ب
                        nassib = m.getFardh();
                        nassibTxt.append(m.getFardh() + " + ");
                        if (mBaqi > 0) {
                            nassibTxt.append(mBaqi);
                            if (m.isShirka()) {                 // assert Aljada
                                int factor = mTassawi || m.getWarith().isOntha() ? 1 : 2;
                                nassibTxt.append(" * ");
                                nassibTxt.append(factor);
                                nassibTxt.append("\\").append(m.getRo2os());

                                int bast = mBaqi * factor * m.getNbr();   // bast only for now
                                if (bast % m.getRo2os() == 0) {
                                    nassib += bast / m.getRo2os();
                                    nassibTxt.append(" = ").append(nassib);
                                }
                                else {
                                    // TODO انكسار
                                    // TODO nassib +=
                                    nassib += bast / m.getRo2os();
                                }
                            } else {                            // assert Alab
                                nassib += mBaqi;
                            }
                        } else {
                            nassibTxt.append(0);
                        }
                    }
                }
                else {
                    nassibTxt.append(m.getFardh());
                    if (m.isShirka()) {
                        nassibTxt.append(" * 1\\").append(m.getRo2os());
                        if (m.getFardh() % m.getRo2os() == 0) {
                            nassib = m.getFardh() / m.getRo2os();
                            nassibTxt.append(" = ").append(nassib);
                        }
                        else {  // TODO إنكسار
                            nassib = m.getFardh() / m.getRo2os();
                        }
                    } else {
                        nassib = m.getFardh();
                    }
                }
            }
            else if (m.isTa3seeb()) {
                if (mBaqi > 0) {
                    nassibTxt.append(mBaqi);
                    if (m.isShirka()) {
                        int factor = mTassawi || m.getWarith().isOntha() ? 1 : 2;

                        nassibTxt.append(" * ");
                        nassibTxt.append(factor);
                        nassibTxt.append("\\").append(m.getRo2os());

                        int bast = mBaqi * factor * m.getNbr();   // bast only for now
                        if (bast % m.getRo2os() == 0) {
                            nassib = bast / m.getRo2os();
                            nassibTxt.append(" = ").append(nassib);
                        }
                        else {
                            // TODO انكسار
                            // TODO nassib =
                            nassib = bast / m.getRo2os();
                        }
                    } else {
                        nassib = mBaqi;
                    }
                } else {
                    nassib = 0;
                    nassibTxt.append(0);
                }
            }
            else if (m.mahjoob()) {
                nassibTxt.append("--");
                nassib = 0;
            }
            else {
                System.out.println(String.format("BUG! %s خطأ وارث ليس صاحب فرض أو تعصيب أو حجب", m.getWarith().getName()));
                continue;
            }

            m.setNassibTxt(nassibTxt.toString());
            m.setNassib(nassib);
        }
    }

    public String getSharh() {
        StringBuilder sharh = new StringBuilder();

        if (mWarathah != null) {
            for (Mirath m : mWarathah) {
                sharh.append("- " + m.getSharh() + ".\n");
            }
        }

        if (mMahjoobin != null) {
            for (Mirath m : mMahjoobin) {
                sharh.append("- " + m.getSharh() + ".\n");
            }
        }

        if (mSpecialCase != null /* TODO غراوين */) {
            sharh.append(mSpecialCase + "\n");
        }

        sharh.append("\n" + "- أصل المسألة: " + mAsl + "\n");
        switch (mNaw3) {
            case NAW3_ADILA:
                sharh.append("- المسألة عادلة (تساوى أصلها مع أسهمها).\n");
                break;
            case NAW3_RAD:
                sharh.append(String.format("- المسألة ناقصة (أسهمها أقل من أصلها) فيرد الباقي %d على أصحاب الفروض عدى الزوجين\n", mBaqi));
                break;
            case NAW3_AWL:
                sharh.append(String.format("- المسألة عائلة (أسهمها أكثر من أصلها)، تعول إلى %d\n", mAshom));
            default:
                break;
        }

        if (mTa3seeb) {
            if (mIstighraq) {
                sharh.append("- استغرق أصحاب الفروض الأسهم ولم يبق للورثة بالتعصيب شيء.\n");
            } else {
                sharh.append(String.format("- الباقي %d وعدد رؤوسه %d\n", mBaqi, mAdadRo2os));
            }
        }

        return sharh.toString();
    }

    // Here is an example on how to retrieve the results to display them.
    public void printTable() {
        // رسم الجدول
        StringBuilder table = new StringBuilder();

        table.append("┌──────────┐\n");       // https://en.wikipedia.org/wiki/Box-drawing_character
        if (getAwl() != 0) {
            table.append(String.format("│ %d\t │\n", getAwl()));
            table.append("├──────┤\n");
            table.append(String.format("│ ع%d\t │\n", getAsl()));  // TODO strikethrough
        } else {
            table.append(String.format("│ %d\t │\n", getAsl()));
        }
        table.append(String.format("├──────────┼──────────┬──────────┐\n"));

        List<Mirath> all = getMawarith();
        for (Mirath m : all) {
            table.append(String.format("│ %s\t│ %s\t │ %s\t │\n",
                    m.getNassibTxt(), m.getIsm(), m.getHokom()));
            if (all.indexOf(m) == (all.size() - 1)) {
                table.append("└──────────┴──────────┴──────────┘\n");
            } else {
                table.append("├──────────┼──────────┼──────────┤\n");
            }
        }

        System.out.println(table.toString());
    }

    public boolean isHal() { return mHal; }

    public boolean isFardh() { return mFardh; }

    public boolean isTa3seeb() { return mTa3seeb; }

    public boolean isTassawi() { return mTassawi; }

    public int getAsl() { return mAsl; }

    public int getAshom() { return mAshom; }

    public int getBaqi() { return mBaqi; }

    public int getAwl() { return mAwl; }

    public int getMissah() { return mMissah; }

    public String getSpecialCase() { return mSpecialCase; }

    public List<Mirath> getMawarith() {
        ArrayList<Mirath> all = new ArrayList<>();

        all.addAll(mWarathah);
        all.addAll(mMahjoobin);

        return all;
    }

    public int getAdadRo2os() { return mAdadRo2os; }

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