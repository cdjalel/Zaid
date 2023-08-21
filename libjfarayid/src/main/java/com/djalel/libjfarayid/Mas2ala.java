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

import java.util.ArrayList;
import java.util.List;

public class Mas2ala {
    WarathahInput mInput;
    private boolean m_far3_warith_dhakar;
    private boolean m_far3_warith_ontha;
    private boolean m_far3_warith;
    private boolean m_jam3_alikhwa;
    private boolean m_asl_warith_dhaker;
    private boolean m_far3_wa_asl_warith_dhaker;
    private boolean m_alikhwa_alashika_wa_li_ab;
    private boolean m_alikhwa_wa_abna_alikhwa;
    private boolean m_ala3mam;

    private enum AljadMa3aAlikhwa {
        LA,                 // لا
        NA3AM,
        MA3A_FARDH,
        THULUTH_ALMAL,
        MUQASSAMA,
        SUDUSS,
        THULUTH_ALBAQI,
    }

    private AljadMa3aAlikhwa mAljadMa3aAlikhwa;

    private final List<WarathahOutput> mHalList;
    private WarathahOutput mHal;

    private boolean m_alakhawat_ashakikat_3assabat_ma3a_lghayr;
    private boolean m_alakhawat_3assabat_ma3a_lghayr;
    private boolean m_alakhawat_tarithna_albaki_ila_alfardh;

    private String mSharh;

    public Mas2ala() {
        mInput = null;

        mHalList = new ArrayList<>();
        mHalList.add(mHal = new WarathahOutput());

        mSharh = "لم يتم إدخال أية ورثة.\n";

        m_alakhawat_ashakikat_3assabat_ma3a_lghayr = false;
        m_alakhawat_3assabat_ma3a_lghayr = false;
        m_alakhawat_tarithna_albaki_ila_alfardh = false;

        mAljadMa3aAlikhwa = AljadMa3aAlikhwa.LA;
    }

    private void addMirath(Mirath m) { addMirath(0, m); }

    private void addMirath(int halIdx, Mirath m) {
        if ((0 <= halIdx) && (halIdx < mHalList.size())) {
            mHalList.get(halIdx).mWarathah.add(m);
            if (m.getBast() != 0 && !m.isTholuthAlbaqi() && !m.isShakikatTarithnaAlbaqiIlaFardh()) {
                mHalList.get(halIdx).mNbrFurudh++;      // وارث بالفرض (فقط أو مع التعصيب)
            }
            if (m.isTa3seeb()){
                mHalList.get(halIdx).mNbr3assabat++;    // وارث بالتعصيب (فقط أو مع الفرض)
            }
        }
    }

    private void addHajb(Warith warith, int nw, String hajib, Warith hajib2, int nh)
    {
        for(Mirath m : mHal.mMahjoobin) {
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
        mHal.mMahjoobin.add(new Mirath(warith, hajb, nw));
    }

    private void addHajb(Warith warith, int nw, Warith hajib2, int nh)
    {
        addHajb(warith, nw, null, hajib2, nh);
    }

    private void addHajb(Warith warith, int nw, String hajib)
    {
        addHajb(warith, nw, hajib, null, 1);
    }

    private void addHajb(Warith warith, int nw, Warith hajib)
    {
        addHajb(warith, nw, hajib, 1);
    }

    private void addHajb(Warith warith, Warith hajib)
    {
        addHajb(warith, 1, hajib, 1);
    }

    public void hissabMawarith(WarathahInput in) {
        mInput = in;

        m_far3_warith_dhakar = (mInput.get_alabna() + mInput.get_abna_alabna()) > 0;
        m_far3_warith_ontha = (mInput.get_albanat() + mInput.get_banat_alabna()) > 0;
        m_far3_warith = m_far3_warith_dhakar || m_far3_warith_ontha;
        m_jam3_alikhwa = (mInput.get_alikhwa_alashika() + mInput.get_alikhwa_li_ab() + mInput.get_alikhwa_li_om() +
                mInput.get_alakhawat_ashakikat() + mInput.get_alakhawat_li_ab() + mInput.get_alakhawat_li_om()) > 1;
        m_asl_warith_dhaker = mInput.alab() || mInput.aljad();
        m_far3_wa_asl_warith_dhaker = m_far3_warith_dhakar || m_asl_warith_dhaker;
        m_alikhwa_alashika_wa_li_ab = (mInput.get_alikhwa_alashika() + mInput.get_alikhwa_li_ab()) > 0;
        m_alikhwa_wa_abna_alikhwa = (mInput.get_alikhwa_alashika() + mInput.get_alikhwa_li_ab() + mInput.get_abna_alikhwa_alashika() + mInput.get_abna_alikhwa_li_ab()) > 0;
        m_ala3mam = (mInput.get_ala3mam_alashika() + mInput.get_ala3mam_li_ab()) > 0;

        // Order matters. Here be dragons
        // أصحاب الفروض
        mirathAzawj();
        mirathAzawjat();
        mirathAlab();
        mirathAljad();
        mirathAlom();
        mirathAljadat();
        mirathAwladAlom();
        mirathAlbanatBiAlfardh();
        mirathAlakhawatAshakikatBiAlfardh();
        mirathAlakhawatLiAbBiAlfardh();

        // أصحاب التعصيب
        mirathAlabnaWaAlbanatBita3seeb();
        mirathAbnaWaBanatAlabnaBita3seeb();
        if (mAljadMa3aAlikhwa == AljadMa3aAlikhwa.LA) {
            mirathAlashikaWaAshakikatBita3seeb(0, false, false);
            mirathAlikhwaWaAlakhawatLiAbBita3seeb(0, false, false);
        }
        else {
            mirathAljadMa3aAlikhwa();
        }
        mirathAbnaAlikhwaAlashika();
        mirathAbnaAlikhwaLiAb();
        mirathAla3mamAlashika();
        mirathAla3mamLiAb();
        mirathAbnaAla3mamAlashika();
        mirathAbnaAla3mamLiAb();

        if (mHal.mWarathah.size() == 0) {
            System.out.println(mSharh);
            return;
        }

        if (mAljadMa3aAlikhwa == AljadMa3aAlikhwa.MA3A_FARDH) {
            mHalList.get(1).copyWarathahExceptAljadWaAlikhwa(mHal);
            mHalList.get(2).copyWarathahExceptAljadWaAlikhwa(mHal);
        }

        for (WarathahOutput hal : mHalList) {
            hissabAlaslWaRo2os(hal);
            hissabAlashom(hal);
            hissabAlbaqiWaRad(hal);
        }

        if (mAljadMa3aAlikhwa == AljadMa3aAlikhwa.MA3A_FARDH) {
            hissabAlahadhLiljad();
        }

        hissabAnsiba();
        hissabAsharh();
    }

    private void mirathAzawj() {
        if (!mInput.zawj()) return;

        mHal.saveZawjiaIndex();

        int maqam;

        String sharh = Warith.AZAWJ.getSharhPrefix();
        if (m_far3_warith) {
            maqam = 4;
            sharh += "الربع 1\\4 فرضا";
        } else {
            maqam = 2;
            sharh += "النصف 1\\2 فرضا";
        }
        addMirath(new Mirath(Warith.AZAWJ, sharh, 1, maqam));
    }

    private void mirathAzawjat() {
        int nbr = mInput.get_azawjat();
        if (nbr == 0) return;

        mHal.saveZawjiaIndex();

        int maqam;

        String sharh = Warith.AZAWJAT.getSharhPrefix(nbr);
        if (m_far3_warith) {
            maqam = 8;
            sharh += "الثمن 1\\8 فرضا";
        } else {
            maqam = 4;
            sharh += "الربع 1\\4 فرضا";
        }
        addMirath(new Mirath(Warith.AZAWJAT, nbr, sharh, 1, maqam));
    }

    private void mirathAlab() {
        if (!mInput.alab()) return;

        int bast;
        int maqam;
        int ro2os;
        boolean ta3seeb;

        String sharh = Warith.ALAB.getSharhPrefix();
        if (m_far3_warith_dhakar) {
            sharh += "السدس 1\\6 فرضا فقط";
            bast = 1;
            maqam = 6;
            ro2os = 1;
            ta3seeb = false;
        } else if (m_far3_warith_ontha) {
            sharh += "السدس 1\\6 فرضا والباقي تعصيبا بالنفس";
            bast = 1;
            maqam = 6;
            ro2os = 1;
            ta3seeb = true;
        } else {
            bast = 0;
            ta3seeb = true;
            if (!m_jam3_alikhwa && mInput.alom() && ((mInput.get_azawjat() > 0) || (mInput.zawj()))) {
                sharh += "ثلثي 2\\3 الباقي تعصيبا بالنفس";
                maqam = 3;      // not used anyway
                ro2os = 3;
                mHal.mTassawi = false;
                mHal.mShirkaTa3seeb = true;
                mHal.mNaw3 = Naw3.NAW3_ALGHARRAWAYN;
            } else {
                maqam = 1;
                ro2os = 1;
                sharh += "الباقي تعصيبا بالنفس";
            }
        }
        addMirath(new Mirath(Warith.ALAB, sharh, bast, maqam, ta3seeb, ro2os));

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

    private void mirathAljad() {
        if (!mInput.aljad() || mInput.alab()) return;

        String sharh;
        int bast;
        int maqam;
        boolean alakhawat_ashakikat_wa_li_ab = (mInput.get_alakhawat_ashakikat() + mInput.get_alakhawat_li_ab()) > 0;

        if (m_far3_warith_dhakar) {
            sharh = Warith.ALJAD.getSharhPrefix();
            sharh += "السدس 1\\6 فرضا فقط";
            bast = 1;
            maqam = 6;
            addMirath(new Mirath(Warith.ALJAD, sharh, bast, maqam));
        }
        else if (!m_alikhwa_alashika_wa_li_ab && !alakhawat_ashakikat_wa_li_ab) {
            // الجد يرث فرضا وتعصيبا أو تعصيبا فقط
            if (m_far3_warith_ontha) {
                sharh = Warith.ALJAD.getSharhPrefix();
                sharh += "السدس 1\\6 فرضا و";
                bast = 1;
                maqam = 6;
            } else {             // الجد يرث بالتعصيب فقط
                sharh = "";
                bast = 0;
                maqam = 1;
            }
            sharh += Warith.ALJAD.getSharhPrefix();
            sharh += "الباقي تعصيبا بالنفس";
            addMirath(new Mirath(Warith.ALJAD, sharh, bast, maqam, true));
        }
        else if (mInput.get_madhab() == Madhab.HANAFI) {
            if (mInput.get_alikhwa_alashika() >= 1) {
                addHajb(Warith.ALIKHWA_ALASHIKA, mInput.get_alikhwa_alashika(), Warith.ALJAD);
            }
            if (mInput.get_alakhawat_ashakikat() >= 1) {
                addHajb(Warith.ALAKHAWAT_ASHAKIKAT, mInput.get_alakhawat_ashakikat(), Warith.ALJAD);
            }
            if (mInput.get_alikhwa_li_ab() >= 1) {
                addHajb(Warith.ALIKHWA_LI_AB, mInput.get_alikhwa_li_ab(), Warith.ALJAD);
            }
            if (mInput.get_alakhawat_li_ab() >= 1) {
                addHajb(Warith.ALAKHAWAT_LI_AB, mInput.get_alakhawat_li_ab(), Warith.ALJAD);
            }
        }
        else {
            // عند الجمهور الجد يرث مع الإخوة بالأحظ له
            mAljadMa3aAlikhwa = AljadMa3aAlikhwa.NA3AM;
            // يؤجل إلى ما بعد أصحاب الفروض وقبل الفصل في ميراث الإخوة بالتعصيب
        }

        // alhajb by aljad
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

    private void mirathAlom() {
        if (!mInput.alom()) return;

        int bast = 1;
        int maqam;
        int ro2os;
        boolean ta3seeb;

        String sharh = Warith.ALOM.getSharhPrefix();
        if (m_jam3_alikhwa || m_far3_warith) {
            sharh += "السدس 1\\6 فرضا";
            maqam = 6;
            ro2os = 1;
            ta3seeb = false;
        } else if ((mInput.alab()) && ((mInput.get_azawjat() > 0) || (mInput.zawj()))) {
            sharh = "الأم ترث ثلث 1\\3 الباقي";
            bast = 0;
            maqam = 3;  // not used anyway
            ro2os = 3;
            ta3seeb = true;
            mHal.mTassawi = false;
            mHal.mShirkaTa3seeb = true;
            mHal.mNaw3 = Naw3.NAW3_ALGHARRAWAYN;
     } else {
            sharh += "الثلث 1\\3 فرضا";
            maqam = 3;
            ro2os = 1;
            ta3seeb = false;
        }
        addMirath(new Mirath(Warith.ALOM, sharh, bast, maqam, ta3seeb, ro2os));

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

        if (mInput.aljadah_li_ab() && (!mInput.alab() || mInput.get_madhab() == Madhab.HAMBALI)) {
            sharh = "الجدة لأب ";
            if (mInput.aljadah_li_om()) {
                sharh += "تشترك (بالتساوي) مع الجدة لأم في السدس 1\\6 فرضا";
                ro2os = 2;
            } else {
                sharh += "ترث السدس 1\\6 فرضا";
                ro2os = 1;
            }
            addMirath(new Mirath(Warith.ALJADAH_LI_AB, sharh, 1, 6, ro2os));
        }

        if (mInput.aljadah_li_om()) {
            sharh = "الجدة لأم ";
            if (mInput.aljadah_li_ab() && (!mInput.alab() || mInput.get_madhab() == Madhab.HAMBALI)) {
                sharh += "تشترك (بالتساوي) مع الجدة لأب في السدس 1\\6 فرضا";
                ro2os = 2;
            } else {
                sharh += "ترث السدس 1\\6 فرضا";
                ro2os = 1;
            }
            addMirath(new Mirath(Warith.ALJADAH_LI_OM, sharh, 1, 6, ro2os));
        }
    }

    private void mirathAwladAlom() {
        int nbr_a = mInput.get_alikhwa_li_om();
        int nbr_b = mInput.get_alakhawat_li_om();
        int awlad_alom = nbr_a + nbr_b;
        if (awlad_alom == 0) return;

        if (m_far3_warith) {
            if (nbr_a > 0) {
                addHajb(Warith.ALIKHWA_LI_OM, nbr_a, "الفرع الوارث");
            }
            if (nbr_b > 0) {
                addHajb(Warith.ALAKHAWAT_LI_OM, nbr_b, "الفرع الوارث");
            }
        } else if (!m_asl_warith_dhaker) { // hajb by asl warith dhaker done above
            String sharh;

            if (awlad_alom > 1) {
                // Mushtarika?
                int nbr_aa = mInput.get_alikhwa_alashika();
                int ashika = nbr_aa + mInput.get_alakhawat_ashakikat();
                int ro2os = awlad_alom;
                boolean ishtirak = false;
                String ma3aAshika = "";

                if (!mInput.is_male() && mInput.zawj() &&  nbr_aa > 0
                        && (mInput.alom() || mInput.aljadah_li_om() || mInput.aljadah_li_ab())) {
                    mHal.mNaw3 = Naw3.NAW3_MUSHTARIKA;
                    if (mInput.get_madhab() == Madhab.MALIKI || mInput.get_madhab() == Madhab.CHAFI3I) {
                        ishtirak = true;
                        ma3aAshika = Warith.ALIKHWA_ALASHIKA.getAlikhwaAshika(mInput);
                        ro2os = awlad_alom + ashika;
                    }
                }

                if (nbr_a > 0) {
                    if (nbr_b > 0) {
                        String sharh2;

                        sharh = Warith.ALIKHWA_LI_OM.getSharhPrefix(nbr_a, Warith.ALAKHAWAT_LI_OM, nbr_b, true);
                        sharh += "الثلث 1\\3 فرضا";
                        sharh2 = Warith.ALAKHAWAT_LI_OM.getSharhPrefix(nbr_b, Warith.ALIKHWA_LI_OM, nbr_a, true);
                        sharh2 += "الثلث 1\\3 فرضا";
                        if (ishtirak) {
                            sharh += "، ويشترك معهم أيضا (بالتساوي)" + ma3aAshika;
                            sharh2 += "، ويشترك معهم أيضا (بالتساوي) " + ma3aAshika;
                        }
                        addMirath(new Mirath(Warith.ALIKHWA_LI_OM, nbr_a, sharh, 1, 3, ro2os));
                        addMirath(new Mirath(Warith.ALAKHAWAT_LI_OM, nbr_b, sharh2, 1, 3, ro2os));
                    }
                    else {
                        sharh = Warith.ALIKHWA_LI_OM.getSharhPrefix(nbr_a);
                        sharh += "الثلث 1\\3 فرضا";
                        if (ishtirak) {
                            sharh += "، ويشترك معه" + (nbr_a > 1 ? "م" : "");
                            sharh += " أيضا (بالتساوي) " + ma3aAshika;
                        }
                        addMirath(new Mirath(Warith.ALIKHWA_LI_OM, nbr_a, sharh, 1, 3, ro2os));
                    }
                }
                else if (nbr_b > 0) {
                    sharh = Warith.ALAKHAWAT_LI_OM.getSharhPrefix(nbr_b);
                    sharh += "الثلث 1\\3 فرضا";
                    if (ishtirak) {
                        sharh += "، ويشترك معه" + (nbr_b > 1 ? "نّ" : "ا");
                        sharh += " أيضا (بالتساوي) " + ma3aAshika;
                    }
                    addMirath(new Mirath(Warith.ALAKHAWAT_LI_OM, nbr_b, sharh, 1, 3, ro2os));
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

    private void mirathAlbanatBiAlfardh() {
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

    private void mirathAlakhawatAshakikatBiAlfardh() {
        // mirath alakhawat ashakikat 3inda 3adam wojud alikhwa alashika
        int nbr = mInput.get_alakhawat_ashakikat();
        if ((nbr == 0) || mInput.alab() || m_far3_warith_dhakar || (mInput.get_alikhwa_alashika() > 0) || m_far3_warith_ontha || (mAljadMa3aAlikhwa != AljadMa3aAlikhwa.LA)) return;

        int bast;
        int maqam;

        String sharh;
        sharh = Warith.ALAKHAWAT_ASHAKIKAT.getSharhPrefix(nbr);
        if (nbr == 1) {
            bast = 1; maqam = 2;
            sharh += "النصف 1\\2 فرضا";
        } else { // (nbr >= 2)
            bast = 2; maqam = 3;
            sharh +="الثلثين 2\\3 فرضا";
            if ((mInput.get_alakhawat_li_ab() >= 1) && (mInput.get_alikhwa_li_ab() == 0)) {
                addHajb(Warith.ALAKHAWAT_LI_AB, nbr, Warith.ALAKHAWAT_ASHAKIKAT.getName(mInput.get_alakhawat_ashakikat()));
            }
        }
        addMirath(new Mirath(Warith.ALAKHAWAT_ASHAKIKAT, nbr, sharh, bast, maqam));
    }

    private void mirathAlakhawatLiAbBiAlfardh() {
        // mirath alakhawat li ab 3inda 3adam wojud alikhwa
        int nbr = mInput.get_alakhawat_li_ab();
        if ((nbr == 0) || mInput.alab() || m_far3_warith_dhakar || m_alikhwa_alashika_wa_li_ab
                || m_alakhawat_ashakikat_3assabat_ma3a_lghayr || m_far3_warith_ontha || (mAljadMa3aAlikhwa != AljadMa3aAlikhwa.LA)) return;

        String sharh;
        int bast;
        int maqam;

        sharh = Warith.ALAKHAWAT_LI_AB.getSharhPrefix(nbr);
        if (mInput.get_alakhawat_ashakikat() == 0) {
            if (nbr == 1) {
                bast = 1; maqam = 2;
                sharh += "النصف 1\\2 فرضا";
            } else { // (nbr >= 2)
                bast = 2; maqam = 3;
                sharh += "الثلثين 2\\3 فرضا";
            }
            addMirath(new Mirath(Warith.ALAKHAWAT_LI_AB, nbr, sharh, bast, maqam));
        }
        else if (mInput.get_alakhawat_ashakikat() == 1) {
            bast = 1; maqam = 6;
            sharh += "السدس 1\\6 تتمة الثلثين فرضا";
            addMirath(new Mirath(Warith.ALAKHAWAT_LI_AB, nbr, sharh, bast, maqam));
        }
        // else hajb by alakhawat_ashakikat() >=2 handled above
    }

    private void mirathAlabnaWaAlbanatBita3seeb() {
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
            mHal.mTassawi = false;
            mHal.mShirkaTa3seeb = true;
            sharh = Warith.ALABNA.getSharhPrefix(nbr_a, Warith.ALBANAT, nbr_b);
            sharh += "الباقي تعصيبا بالنفس";
            addMirath(new Mirath(Warith.ALABNA, nbr_a, sharh, ro2os));

            sharh = Warith.ALBANAT.getSharhPrefix(nbr_b, Warith.ALABNA, nbr_a);
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

    private void mirathAbnaWaBanatAlabnaBita3seeb() {
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
            mHal.mTassawi = false;
            mHal.mShirkaTa3seeb = true;
            sharh = Warith.ABNA_ALABNA.getSharhPrefix(nbr_a, Warith.BANAT_ALABNA, nbr_b);
            sharh += "الباقي تعصيبا بالنفس";
            addMirath(new Mirath(Warith.ABNA_ALABNA, nbr_a, sharh, ro2os));

            sharh = Warith.BANAT_ALABNA.getSharhPrefix(nbr_b, Warith.ABNA_ALABNA, nbr_a);
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

    private void mirathAlashikaWaAshakikatBita3seeb(int halIdx, boolean muqassamaMa3aAljad, boolean ta3seebBiljad) {
        // Here be dragons
        if (m_far3_warith_dhakar || mInput.alab() || (mInput.aljad() && mInput.get_madhab() == Madhab.HANAFI)) return;
        // assert(halIdx < 0 || halIdx >= mHal.size());

        String sharh;
        int nbr_a = mInput.get_alikhwa_alashika();
        int nbr_b = mInput.get_alakhawat_ashakikat();

        if (nbr_a > 0) {
            int ro2os = 2 * nbr_a + nbr_b;
            boolean ishtirak = false;
            String ma3aAlikhwaLiOm = "";

            if (mHal.mNaw3 == Naw3.NAW3_MUSHTARIKA
                && (mInput.get_madhab() == Madhab.MALIKI || mInput.get_madhab() == Madhab.CHAFI3I)) {
                ishtirak = true;
                ma3aAlikhwaLiOm = Warith.ALIKHWA_LI_OM.getAlikhwaLiOm(mInput);
                ro2os = mInput.get_alikhwa_li_om() + mInput.get_alakhawat_li_om() + nbr_a + nbr_b;
            }

            if (nbr_b == 0) {
                sharh = Warith.ALIKHWA_ALASHIKA.getSharhPrefix(nbr_a);
                if (ishtirak) {
                    mHalList.get(halIdx).mShirkaTa3seeb = true;
                    sharh += "الثلث 1\\3، ويشترك معه" + (nbr_a > 1 ? "م": "");
                    sharh += " أيضا (بالتساوي) " + ma3aAlikhwaLiOm;
                    // Here be dragons: Mirath.ta3seeb below is false so Ikhwa Ashika are counted with Awlad Alom
                    addMirath(halIdx, new Mirath(Warith.ALIKHWA_ALASHIKA, nbr_a, sharh, 1, 3, ro2os));
                }
                else {
                    sharh += "الباقي تعصيبا بالنفس";
                    if (muqassamaMa3aAljad) { sharh += " ومقاسمة مع الجد"; }
                    addMirath(halIdx, new Mirath(Warith.ALIKHWA_ALASHIKA, nbr_a, sharh));
                }
            } else {
                String sharh2;

                mHalList.get(halIdx).mShirkaTa3seeb = true;
                if (ishtirak) {
                    sharh = Warith.ALIKHWA_ALASHIKA.getSharhPrefix(nbr_a, Warith.ALAKHAWAT_ASHAKIKAT, nbr_b, true);
                    sharh2 = Warith.ALAKHAWAT_ASHAKIKAT.getSharhPrefix(nbr_b, Warith.ALIKHWA_ALASHIKA, nbr_a, true);
                    mHalList.get(halIdx).mTassawi = true;
                    sharh += "الثلث 1\\3، ويشترك معهم أيضا (بالتساوي) " + ma3aAlikhwaLiOm;
                    sharh2 += "الثلث 1\\3، ويشترك معهم أيضا (بالتساوي) " + ma3aAlikhwaLiOm;
                    addMirath(halIdx, new Mirath(Warith.ALIKHWA_ALASHIKA, nbr_a, sharh, 1, 3, ro2os));
                    addMirath(halIdx, new Mirath(Warith.ALAKHAWAT_ASHAKIKAT, nbr_b, sharh2, 1, 3, ro2os));
                }
                else {
                    sharh = Warith.ALIKHWA_ALASHIKA.getSharhPrefix(nbr_a, Warith.ALAKHAWAT_ASHAKIKAT, nbr_b);
                    sharh2 = Warith.ALAKHAWAT_ASHAKIKAT.getSharhPrefix(nbr_b, Warith.ALIKHWA_ALASHIKA, nbr_a);
                    mHalList.get(halIdx).mTassawi = false;
                    sharh += "الباقي تعصيبا بالنفس";
                    sharh2 += "الباقي تعصيبا بالغير";
                    if (muqassamaMa3aAljad) {
                        sharh += " ومقاسمة مع الجد";
                        sharh2 += " ومقاسمة مع الجد";
                    }
                    addMirath(halIdx, new Mirath(Warith.ALIKHWA_ALASHIKA, nbr_a, sharh, ro2os));
                    addMirath(halIdx, new Mirath(Warith.ALAKHAWAT_ASHAKIKAT, nbr_b, sharh2, ro2os));
                }
            }

            // Hajb bi alikhwa is the same for all Jad & Ikhwa cases. So we do it only once.
            if (halIdx == 0) {
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
        }
        else if (nbr_b > 0) {
            if (m_far3_warith_ontha) {
                sharh = Warith.ALAKHAWAT_ASHAKIKAT.getSharhPrefix(nbr_b);
                sharh += "الباقي تعصيبا مع الغير";
                if (muqassamaMa3aAljad) { sharh += " ومقاسمة مع الجد"; }
                addMirath(halIdx, new Mirath(Warith.ALAKHAWAT_ASHAKIKAT, nbr_b, sharh));
                m_alakhawat_ashakikat_3assabat_ma3a_lghayr = true;
                m_alakhawat_3assabat_ma3a_lghayr = true;

                //الأخوات الشقيقات حال العصبة مع الغير (أي مع الفرع الوارث الأنثى) تقمن مقام الإخوة الأشقاء وتعملن عملهم في الحجب
                if (halIdx == 0) {
                    if (mInput.get_alikhwa_li_ab() >= 1) {
                        addHajb(Warith.ALIKHWA_LI_AB, mInput.get_alikhwa_li_ab(), Warith.ALAKHAWAT_ASHAKIKAT, nbr_b);
                    }
                    if (mInput.get_alakhawat_li_ab() >= 1) {
                        addHajb(Warith.ALAKHAWAT_LI_AB, mInput.get_alakhawat_li_ab(), Warith.ALAKHAWAT_ASHAKIKAT, nbr_b);
                    }
                    if (mInput.get_abna_alikhwa_alashika() >= 1) {
                        addHajb(Warith.ABNA_ALIKHWA_ALASHIKA, mInput.get_abna_alikhwa_alashika(), Warith.ALAKHAWAT_ASHAKIKAT, nbr_b);
                    }
                    if (mInput.get_abna_alikhwa_li_ab() >= 1) {
                        addHajb(Warith.ABNA_ALIKHWA_LI_AB, mInput.get_abna_alikhwa_li_ab(), Warith.ALAKHAWAT_ASHAKIKAT, nbr_b);
                    }
                    if (mInput.get_ala3mam_alashika() >= 1) {
                        addHajb(Warith.ALA3MAM_ALASHIKA, mInput.get_ala3mam_alashika(), Warith.ALAKHAWAT_ASHAKIKAT, nbr_b);
                    }
                    if (mInput.get_ala3mam_li_ab() >= 1) {
                        addHajb(Warith.ALA3MAM_LI_AB, mInput.get_ala3mam_li_ab(), Warith.ALAKHAWAT_ASHAKIKAT, nbr_b);
                    }
                    if (mInput.get_abna_ala3mam_alashika() >= 1) {
                        addHajb(Warith.ABNA_ALA3MAM_ALASHIKA, mInput.get_abna_ala3mam_alashika(), Warith.ALAKHAWAT_ASHAKIKAT, nbr_b);
                    }
                    if (mInput.get_abna_ala3mam_li_ab() >= 1) {
                        addHajb(Warith.ABNA_ALA3MAM_LI_AB, mInput.get_abna_ala3mam_li_ab(), Warith.ALAKHAWAT_ASHAKIKAT, nbr_b);
                    }
                }
            }
            else if (ta3seebBiljad) {
                // الأخت بلا أخ ولا فرع وارث أنثى مع الجد تصبح عصبة به وترث الباقي إلى النصف (أو الباقي إلى الثلثين للأختين)
                m_alakhawat_tarithna_albaki_ila_alfardh = true;
                mHalList.get(halIdx).mShirkaTa3seeb = false;
                mHalList.get(halIdx).mTassawi = false;
                sharh = Warith.ALAKHAWAT_ASHAKIKAT.getSharhPrefix(nbr_b, false);
                sharh += "الباقي إلى ";
                int bast, maqam;
                if (nbr_b == 1 ) {
                    sharh += "النصف 1\\2";
                    bast = 1; maqam = 2;
                } else {
                    sharh += "الثلثين 2\\3" ;
                    bast = 2; maqam = 3;
                }
                sharh += " بعد نصيب الجد";
                addMirath(halIdx, new Mirath(Warith.ALAKHAWAT_ASHAKIKAT, nbr_b, sharh, bast, maqam, true, nbr_b));
            }
            // else يرثن بالفرض وقد تم أعلاه
        }
    }

    private void mirathAlikhwaWaAlakhawatLiAbBita3seeb(int halIdx, boolean muqassamaMa3aAljad, boolean ta3seebBiljad) {
        if (m_far3_warith_dhakar || mInput.alab() || (mInput.aljad() && mInput.get_madhab() == Madhab.HANAFI)
        || (mInput.get_alikhwa_alashika() > 0) || m_alakhawat_ashakikat_3assabat_ma3a_lghayr /* || (mInput.get_alakhawat_ashakikat() > 0 && ta3seebBiljad) */) return;
        // assert(halIdx < 0 || halIdx >= mHal.size());

        String sharh;
        int nbr_a = mInput.get_alikhwa_li_ab();
        int nbr_b = mInput.get_alakhawat_li_ab();

        if (nbr_a > 0) {
            if (nbr_b == 0) {
                sharh = Warith.ALIKHWA_LI_AB.getSharhPrefix(nbr_a);
                sharh += "الباقي تعصيبا بالنفس";
                if (muqassamaMa3aAljad && !m_alakhawat_tarithna_albaki_ila_alfardh) { sharh += " ومقاسمة مع الجد"; }
                addMirath(halIdx, new Mirath(Warith.ALIKHWA_LI_AB, nbr_a, sharh, nbr_a));
            } else {
                String sharh2;
                mHalList.get(halIdx).mTassawi = false;
                mHalList.get(halIdx).mShirkaTa3seeb = true;
                sharh = Warith.ALIKHWA_LI_AB.getSharhPrefix(nbr_a, Warith.ALAKHAWAT_LI_AB, nbr_b);
                sharh2 = Warith.ALAKHAWAT_LI_AB.getSharhPrefix(nbr_b, Warith.ALIKHWA_LI_AB, nbr_a);
                sharh += "الباقي تعصيبا بالنفس";
                sharh2 += "الباقي تعصيبا بالغير";
                if (muqassamaMa3aAljad && !m_alakhawat_tarithna_albaki_ila_alfardh) {
                    sharh += " ومقاسمة مع الجد";
                    sharh2 += " ومقاسمة مع الجد";
                }
                addMirath(halIdx, new Mirath(Warith.ALIKHWA_LI_AB, nbr_a, sharh, 2 * nbr_a + nbr_b));
                addMirath(halIdx, new Mirath(Warith.ALAKHAWAT_LI_AB, nbr_b, sharh2, 2 * nbr_a + nbr_b));
            }

                        // Hajb bi alikhwa is the same for all Jad & Ikhwa cases. So we do it only once.
            if (halIdx == 0) {
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
        }
        else if (nbr_b > 0) {
            if (m_far3_warith_ontha) {
                sharh = Warith.ALAKHAWAT_LI_AB.getSharhPrefix(nbr_b);
                sharh += " الباقي تعصيبا مع الغير";
                if (muqassamaMa3aAljad) { sharh += " ومقاسمة مع الجد"; }
                addMirath(halIdx, new Mirath(Warith.ALAKHAWAT_LI_AB, nbr_b, sharh));
                m_alakhawat_3assabat_ma3a_lghayr = true;

                //alhajb by ALAKHAWAT_LI_AB
                if (halIdx == 0) {
                    if (mInput.get_abna_alikhwa_alashika() >= 1) {
                        addHajb(Warith.ABNA_ALIKHWA_ALASHIKA, mInput.get_abna_alikhwa_alashika(), Warith.ALAKHAWAT_LI_AB, nbr_b);
                    }
                    if (mInput.get_abna_alikhwa_li_ab() >= 1) {
                        addHajb(Warith.ABNA_ALIKHWA_LI_AB, mInput.get_abna_alikhwa_li_ab(), Warith.ALAKHAWAT_LI_AB, nbr_b);
                    }
                    if (mInput.get_ala3mam_alashika() >= 1) {
                        addHajb(Warith.ALA3MAM_ALASHIKA, mInput.get_ala3mam_alashika(), Warith.ALAKHAWAT_LI_AB, nbr_b);
                    }
                    if (mInput.get_ala3mam_li_ab() >= 1) {
                        addHajb(Warith.ALA3MAM_LI_AB, mInput.get_ala3mam_li_ab(), Warith.ALAKHAWAT_LI_AB, nbr_b);
                    }
                    if (mInput.get_abna_ala3mam_alashika() >= 1) {
                        addHajb(Warith.ABNA_ALA3MAM_ALASHIKA, mInput.get_abna_ala3mam_alashika(), Warith.ALAKHAWAT_LI_AB, nbr_b);
                    }
                    if (mInput.get_abna_ala3mam_li_ab() >= 1) {
                        addHajb(Warith.ABNA_ALA3MAM_LI_AB, mInput.get_abna_ala3mam_li_ab(), Warith.ALAKHAWAT_LI_AB, nbr_b);
                    }
                }
            }
            else if (ta3seebBiljad) {
                // الأخت بلا أخ ولا فرع وارث أنثى مع الجد تصبح عصبة به وترث الباقي إلى النصف (أو الباقي إلى الثلثين للأختين)
                m_alakhawat_tarithna_albaki_ila_alfardh = true;
                mHalList.get(halIdx).mShirkaTa3seeb = false;
                mHalList.get(halIdx).mTassawi = false;
                sharh = Warith.ALAKHAWAT_LI_AB.getSharhPrefix(nbr_b, false);
                sharh += "الباقي إلى ";
                int bast, maqam;
                if (nbr_b == 1 ) {
                    sharh += "النصف 1\\2";
                    bast = 1; maqam = 2;
                } else {
                    sharh += "الثلثين 2\\3" ;
                    bast = 2; maqam = 3;
                }
                sharh += " بعد نصيب الجد";
                addMirath(halIdx, new Mirath(Warith.ALAKHAWAT_LI_AB, nbr_b, sharh, bast, maqam, true, nbr_b));
            }
            // else يرثن بالفرض وقد تم أعلاه
        }
    }

    private void mirathAljadMa3aAlikhwa() {
        boolean tassawi = mInput.get_alakhawat_ashakikat() == 0 && mInput.get_alakhawat_li_ab() == 0;

        int ro2os_aljad = tassawi ? 1 : 2;
        int ro2os_ashika = tassawi ? mInput.get_alikhwa_alashika() : mInput.get_alikhwa_alashika() * 2 + mInput.get_alakhawat_ashakikat();
        int ro2os_li_ab = tassawi ? mInput.get_alikhwa_li_ab() : mInput.get_alikhwa_li_ab() * 2 + mInput.get_alakhawat_li_ab();
        int ro2os_alikhwa = ro2os_ashika + ro2os_li_ab;
        int ro2os = ro2os_aljad + ro2os_alikhwa;

        String sharh = Warith.ALJAD.getSharhMa3aAlikhwaPrefix(mInput);

        // هل هناك صاحب فرض آخر غير الجد
        if (mHal.mNbrFurudh == 0) {
            if (ro2os_alikhwa >= 2 * ro2os_aljad) {
                mAljadMa3aAlikhwa = AljadMa3aAlikhwa.THULUTH_ALMAL;
                sharh += "الثلث 1\\3";
                addMirath(new Mirath(Warith.ALJAD, sharh, 1, 3));
                mirathAlashikaWaAshakikatBita3seeb(0, false, true);
                mirathAlikhwaWaAlakhawatLiAbBita3seeb(0, false, true);
            } else {
                mAljadMa3aAlikhwa = AljadMa3aAlikhwa.MUQASSAMA;
                mHal.mShirkaTa3seeb = true;
                sharh += "المقاسمة";
                addMirath(new Mirath(Warith.ALJAD, 1, sharh, ro2os)); // يُحسب عليه جميع الإخوة
                mirathAlashikaWaAshakikatBita3seeb(0, true, true);
                mirathAlikhwaWaAlakhawatLiAbBita3seeb(0, true, true);
            }
        } else {
            mAljadMa3aAlikhwa = AljadMa3aAlikhwa.MA3A_FARDH;

            // مؤقتا نحسب ثلاث مسائل حيث يأخذ الجد في الأولى ([0]mHalList) السدس فرضا،
            // وفي الثانية (mHalList[1]) ثلث الباقي تعصيبا،
            // وفي الثالثة (mHalList[2]) يقاسم الإخوة كأخ منهم تعصيبا،
            // ثم عند الحساب بعد إكمال كل الورثة نختار له الأحظ من هذه المسائل الثلاثة.
            mHalList.add(new WarathahOutput());
            mHalList.add(new WarathahOutput());

            // 1
            addMirath(0, new Mirath(Warith.ALJAD, sharh + "السدس 1\\6 فرضا", 1, 6));
            mirathAlashikaWaAshakikatBita3seeb(0, false, true);
            mirathAlikhwaWaAlakhawatLiAbBita3seeb(0, false, true);

            // 2
            mHalList.get(1).mShirkaTa3seeb = true;
            addMirath(1, new Mirath(Warith.ALJAD, sharh + "ثلث 1\\3 الباقي", 1, 3, true, 3));
            mirathAlashikaWaAshakikatBita3seeb(1, false, true);
            mirathAlikhwaWaAlakhawatLiAbBita3seeb(1, false, true);

            // 3
            mHalList.get(2).mShirkaTa3seeb = true;
            sharh += "المقاسمة";
            addMirath(2, new Mirath(Warith.ALJAD, 1, sharh, ro2os)); // يُحسب عليه جميع الإخوة
            mirathAlashikaWaAshakikatBita3seeb(2, true, true);
            mirathAlikhwaWaAlakhawatLiAbBita3seeb(2, true, true);
        }
        if ((ro2os_ashika > 0) && (ro2os_li_ab > 0)) {
            for (WarathahOutput hal :mHalList) { hal.mMu3addah = true; }
        }
    }

    private void mirathAbnaAlikhwaAlashika() {
        int nbr = mInput.get_abna_alikhwa_alashika();
        if ((nbr == 0) || m_far3_wa_asl_warith_dhaker || m_alikhwa_alashika_wa_li_ab || m_alakhawat_3assabat_ma3a_lghayr) return;

        String sharh = Warith.ABNA_ALIKHWA_ALASHIKA.getSharhPrefix(nbr);
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

    private void mirathAbnaAlikhwaLiAb() {
        int nbr = mInput.get_abna_alikhwa_li_ab();
        if ((nbr == 0) || m_far3_wa_asl_warith_dhaker || m_alikhwa_alashika_wa_li_ab || m_alakhawat_3assabat_ma3a_lghayr || (mInput.get_abna_alikhwa_alashika() > 0)) return;

        String sharh = Warith.ABNA_ALIKHWA_LI_AB.getSharhPrefix(nbr);
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

    private void mirathAla3mamAlashika() {
        int nbr = mInput.get_ala3mam_alashika();
        if ((nbr == 0) || m_far3_wa_asl_warith_dhaker || m_alikhwa_wa_abna_alikhwa || m_alakhawat_3assabat_ma3a_lghayr) return;

        String sharh = Warith.ALA3MAM_ALASHIKA.getSharhPrefix(nbr);
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

    private void mirathAla3mamLiAb() {
        int nbr = mInput.get_ala3mam_li_ab();
        if ((nbr == 0) || m_far3_wa_asl_warith_dhaker || m_alikhwa_wa_abna_alikhwa || m_alakhawat_3assabat_ma3a_lghayr || (mInput.get_ala3mam_alashika() > 0)) return;

        String sharh = Warith.ALA3MAM_LI_AB.getSharhPrefix(nbr);
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

    private void mirathAbnaAla3mamAlashika() {
        int nbr = mInput.get_abna_ala3mam_alashika();
        if ((mInput.get_abna_ala3mam_alashika() == 0) || m_far3_wa_asl_warith_dhaker || m_alikhwa_wa_abna_alikhwa || m_alakhawat_3assabat_ma3a_lghayr || m_ala3mam) return;

        String sharh = Warith.ABNA_ALA3MAM_ALASHIKA.getSharhPrefix(mInput.get_abna_ala3mam_alashika());
        sharh += "الباقي تعصيبا بالنفس";
        addMirath(new Mirath(Warith.ABNA_ALA3MAM_ALASHIKA, nbr, sharh));

        // alhajb by abna alikhwa li_ab
        if (mInput.get_abna_ala3mam_li_ab() > 0) {
            addHajb(Warith.ABNA_ALA3MAM_LI_AB, mInput.get_abna_ala3mam_li_ab(), Warith.ABNA_ALA3MAM_ALASHIKA, nbr);
        }
    }

    private void mirathAbnaAla3mamLiAb() {
        int nbr = mInput.get_abna_ala3mam_li_ab();
        if ((nbr == 0) || m_far3_wa_asl_warith_dhaker || m_alikhwa_wa_abna_alikhwa || m_alakhawat_3assabat_ma3a_lghayr || m_ala3mam || (mInput.get_abna_ala3mam_alashika() > 0))  return;

        String sharh = Warith.ABNA_ALA3MAM_LI_AB.getSharhPrefix(mInput.get_abna_ala3mam_li_ab());
        sharh += "الباقي تعصيبا بالنفس";
        addMirath(new Mirath(Warith.ABNA_ALA3MAM_LI_AB, nbr, sharh));
    }

    private void hissabAlaslWaRo2os(WarathahOutput hal) {
        // استخراج أصل المسألة (ص 40 من كتاب "الفرائض المُيسر) وعدد رؤوس ورثة الباقي"
        hal.mAsl = 1;
        for (Mirath m : hal.mWarathah) {
            if (m.isFardh()) {
                hal.mAsl = lcm(hal.mAsl, m.getMaqam());
            }

            if (m.isTa3seeb() && (m.getRo2os() != 1)) {
                if (hal.mRo2osAlbaqi == 1) {
                    hal.mRo2osAlbaqi = m.getRo2os();            // بالأخص من يأخذ ثلث الباقي أو الجد مقاسمة مع الإخوة بصنفيهم (المعادة)
                } else if (hal.mRo2osBaqiAlbaqi == 1) {
                    hal.mRo2osBaqiAlbaqi = m.getRo2os();        // من يأخذ ثلثي الباقي بعد إعطاء ثلثه أو الإخوة بصنفيهم بعد الجد
                }
            }
        }
        if ((hal.mNbrFurudh == 0) && (hal.mNbr3assabat > 0)) {
            hal.mAsl = hal.mRo2osAlbaqi;
        }
    }

    private void hissabAlashom(WarathahOutput hal) {
        // حساب مجموع أسهم أصحاب الفروض
        hal.mAshom = 0;
        boolean jadahCounted = false;
        boolean waladAlomCounted = false;
        for (Mirath m : hal.mWarathah) {
            if (m.isFardh()) {
                int fardh = m.getBast() * hal.mAsl / m.getMaqam();
                m.setFardh(fardh);
                if (m.isJadah()) {
                    if (jadahCounted) { continue; }
                    jadahCounted = true;
                }
                else if (m.isWaladAlom()) {
                    if (waladAlomCounted) { continue; }
                    waladAlomCounted = true;
                }
                else if (hal.mNaw3 == Naw3.NAW3_MUSHTARIKA && m.isShakik()) {
                    continue;
                }
                hal.mAshom += fardh;
            }
        }
    }

    private void hissabAlbaqiWaRad(WarathahOutput hal) {
        // حساب باقي المسألة
        hal.mBaqi = hal.mAsl - hal.mAshom;
        if (hal.mBaqi == 0) {
            if (hal.mNbrFurudh > 0 && hal.mNaw3 != Naw3.NAW3_MUSHTARIKA) {
                hal.mNaw3 = Naw3.NAW3_3ADILA;
            }
            if (hal.mNbr3assabat > 0) {
                hal.mIstighraq = true;
            }
        } else if (hal.mBaqi > 0) { // مسألة فيها باقي
            if ((hal.mNbr3assabat == 0) && (hal.mAshom != 0)) {
                // مسألة ناقصة فيها رد
                // من صفحة 69 في كتاب الفرائض المُيسر
                if (hal.isZawjia()) {
                    if (hal.mNbrFurudh == 1) {
                        // TODO إضافة ميراث ذوي الأرحام حسب المذاهب
                        hal.mNaw3 = hal.mWarathah.get(0).getNbr() > 1 ?     // عدة زوجات
                                Naw3.NAW3_RAD_3ALA_MUTAJANISEEN : Naw3.NAW3_RAD_3ALA_WAHED;
                        hal.mAslRad = hal.mAsl;
                        hal.mAslJami3a = 1;
                        hal.mWarathah.get(0).setRad(mHal.mBaqi);
                        hal.mWarathah.get(0).setJami3AlfardhWaRad(1);
                        hal.mWarathah.get(0).addSharh("والباقي ردًا");
                    }
                    else if (hal.mNbrFurudh == 2 ||
                            (hal.mNbrFurudh == 3 && (isJinsayAwladAlom() || isAljadatayn()))) {
                        hal.mNaw3 = hal.mNbrFurudh == 2 &&
                                hal.mWarathah.get((hal.mIndexZawjia+1) %2).getNbr() == 1 ?
                                Naw3.NAW3_RAD_3ALA_WAHED_ZAWJIA : Naw3.NAW3_RAD_3ALA_MUTAJANISEEN_ZAWJIA;
                        hal.mAslRad = hal.mWarathah.get(hal.mIndexZawjia).getMaqam();
                        hal.mAslJami3a = hal.mAslRad;

                        int i = 0;
                        for (Mirath m : hal.mWarathah) {
                            if (i == hal.mIndexZawjia) {
                                m.setRad(0);
                                m.setJami3AlfardhWaRad(1);
                                m.addSharh("ولا يُرد علي" + m.getDhamir());
                            }
                            else {
                                m.setRad(mHal.mBaqi);
                                m.setJami3AlfardhWaRad(hal.mAslRad - 1);
                                m.addSharh(hal.mNbrFurudh == 2 ? "والباقي ردًا" :
                                        String.format("ويُرد علي%s من الباقي", m.getDhamir()));
                            }
                            i++;
                        }
                    }
                    else {
                        hal.mNaw3 = Naw3.NAW3_RAD_3ALA_MUKHTALIFEEN_ZAWJIA;
                        hal.mAslZawjia = hal.mWarathah.get(hal.mIndexZawjia).getMaqam();
                        hal.mBaqiZawjia = hal.mAslZawjia - 1;

                        // Here be dragons
                        // Use one recursive call to recalculate ashom whithout Zawjayn
                        WarathahOutput halRad = hal.copyWarathahExceptAhadZawjayn();
                        hissabAlaslWaRo2os(halRad);
                        hissabAlashom(halRad);
                        hissabAlbaqiWaRad(halRad);

                        hal.mAslRad = halRad.mAshom;
                        int gcd = gcd(hal.mAslRad, hal.mBaqiZawjia);
                        hal.mAslJami3a = hal.mAslRad/gcd * hal.mAslZawjia;
                        int i = 0;
                        for (Mirath m : hal.mWarathah) {
                            if (i == hal.mIndexZawjia) {
                                m.setRad(0);
                                m.setJami3AlfardhWaRad(hal.mAslRad/gcd);
                                m.addSharh("ولا يُرد علي" + m.getDhamir());
                            }
                            else {
                                m.setRad(halRad.getFardh(m.getWarith()));
                                m.setJami3AlfardhWaRad(m.getRad() * hal.mBaqiZawjia/gcd);
                                m.addSharh(String.format("ويُرد علي%s من الباقي", m.getDhamir()));
                            }
                            i++;
                        }
                    }
                }
                else if ((hal.mNbrFurudh == 1 ||
                        (hal.mNbrFurudh == 2 && (isJinsayAwladAlom() || isAljadatayn())))) {
                    hal.mNaw3 = hal.mNbrFurudh == 1 &&
                            hal.mWarathah.get(0).getNbr() == 1 ?
                            Naw3.NAW3_RAD_3ALA_WAHED : Naw3.NAW3_RAD_3ALA_MUTAJANISEEN;
                    hal.mAslRad = hal.mAsl;
                    hal.mAslJami3a = 1;
                    for (Mirath m : hal.mWarathah) {
                        m.setRad(hal.mBaqi);
                        m.setJami3AlfardhWaRad(1);
                        m.addSharh(hal.mNbrFurudh == 1 ? "والباقي ردًا" :
                                String.format("ويُرد علي%s من الباقي", m.getDhamir()));
                    }
                }
                else {
                    // assert  hal.mNbrFurudh > 1
                    hal.mNaw3 = Naw3.NAW3_RAD_3ALA_MUKHTALIFEEN;
                    hal.mAslRad = hal.mAshom;
                    hal.mAslJami3a = hal.mAshom;
                    for (Mirath m : hal.mWarathah) {
                        //m.setRad(0);
                        m.setJami3AlfardhWaRad(m.getFardh());
                        m.addSharh(String.format("ويُرد علي%s من الباقي", m.getDhamir()));
                    }
                    // the 1 level deep recursive call ends here
                }
            }
        } else {   // mBaqi < 0 مسألة عائلة
            hal.mNaw3 = Naw3.NAW3_AWL;
            hal.mAwl = hal.mAshom;
            if (hal.mNbr3assabat > 0) {
                hal.mIstighraq = true;
            }
            hal.mBaqi = 0;
        }
    }

    private void hissabAlahadhLiljad() {
        // اختيار الأحظ للجد في وجود صاحب فرض آخر غيره
        if (mHal.mBaqi == 0) {
            mAljadMa3aAlikhwa = AljadMa3aAlikhwa.SUDUSS;
            mHalList.remove(2);
            mHalList.remove(1);
        } else { // if (mHal.get(0).mBaqi > 0) {
            // compare Jad parts (nassib = fraction of the total) in the three cases with Ikhwa
            double asl0 = mHal.mAsl;
            double baqi0 = mHal.mBaqi;
            double nassib0 = 1.0 / 6;

            if (nassib0 >= (baqi0 / asl0)) {
                mAljadMa3aAlikhwa = AljadMa3aAlikhwa.SUDUSS;
                mHalList.remove(2);
                mHalList.remove(1);
            } else {
                double asl1 = mHalList.get(1).mAsl;
                double baqi1 = mHalList.get(1).mBaqi;
                double nassib1 = (baqi1 / asl1) / 3;

                int ro2os = 1;
                for (Mirath mirath : mHalList.get(2).mWarathah) {
                    if (mirath.getWarith() == Warith.ALJAD) {
                        ro2os =  mirath.getRo2os();
                        break;
                    }
                }
                double asl2 = mHalList.get(2).mAsl;
                double baqi2 = mHalList.get(2).mBaqi;
                double nassib2 = (baqi2 / asl2) / ro2os;
                if (!mHalList.get(2).mTassawi) {
                    nassib2 *= 2;
                }

                if ((nassib0 >= nassib1) && (nassib0 >= nassib2)) {
                    mAljadMa3aAlikhwa = AljadMa3aAlikhwa.SUDUSS;
                    mHalList.remove(2);
                    mHalList.remove(1);
                } else if ((nassib1 >= nassib0) && (nassib1 >= nassib2)) {
                    mAljadMa3aAlikhwa = AljadMa3aAlikhwa.THULUTH_ALBAQI;
                    mHalList.remove(2);
                    mHalList.remove(0);
                } else {
                    mAljadMa3aAlikhwa = AljadMa3aAlikhwa.MUQASSAMA;
                    mHalList.remove(1);
                    mHalList.remove(0);
                }
                mHal = mHalList.get(0);
            }
        }
    }

    private void hissabAnsiba() {
        // قسمة الأسهم في الجدول
        double nassib;
        int missahFactor = 1;
        ArrayList<Mirath> all = getMawarith();

        for (Mirath m : all) {          // Here be dragons
            StringBuilder nassibMojmal = new StringBuilder();
            StringBuilder nassibFardi = new StringBuilder();

            if (m.isFardh()) {
                int fardh = m.getJami3Alfardh() ;
                nassib = fardh;
                nassibMojmal.append(Mirath.nassibToString(nassib));
                nassibFardi.append(Mirath.nassibToString(nassib));

                if (m.isTa3seeb()) {
                    // حالات الأب والجد يرثان 1\6 + الباقي
                    // assert m.getBast() == 1 && m.getMaqam() == 6
                    nassibMojmal.append(" + ").append(mHal.mBaqi);
                    nassibFardi.append(" + ").append(mHal.mBaqi);

                    if (m.isShirka()) {
                        // assert Aljada
//                        nassibMojmal.append("ش");

                        int factor = mHal.mTassawi || m.getWarith().isOntha() ? 1 : 2;
                        nassibFardi.append(" * ");
                        nassibFardi.append(factor);
                        nassibFardi.append("\\").append(m.getRo2os());

                        int bast = mHal.mBaqi * factor;
                        if (bast % m.getRo2os() == 0) {
                            nassib += bast * 1.0 / m.getRo2os();
                            nassibFardi.append(" = ").append(Mirath.nassibToString(nassib));
                        }
                        else { // إنكسار
                            missahFactor = lcm(missahFactor, m.getRo2os() / gcd(bast, m.getRo2os()));
                            nassib = bast * 1.0 / m.getRo2os();
                        }
                    } else {
                        nassib += mHal.mBaqi;
                    }
                } else {              // وارث بالفرض فقط
                    if (m.isShirka()) {
//                        nassibMojmal.append("ش");

                        nassibFardi.append(" * 1\\");
                        nassibFardi.append(m.getRo2os());
                        if (fardh % m.getRo2os() == 0) {
                            nassib = fardh * 1.0 / m.getRo2os();
                            nassibFardi.append(" = ").append(Mirath.nassibToString(nassib));
                        }
                        else {  // إنكسار
                            missahFactor = lcm(missahFactor, m.getRo2os() / gcd(fardh, m.getRo2os()));
                            nassib = fardh * 1.0 / m.getRo2os();
                        }
                    }
                }
            }
            else if (m.isTa3seeb()) {       // وارث بالتعصيب فقط
                // nasibFardi  = (factor/ro2os) [ * (factor1/ro2os1)  [ * (factor2/ro2os2)]]
                double factor = mHal.mBaqi;
                int ro2os = 1;
//                boolean shirka = false;
                int factor1;
                int ro2os1;
                int ro2os_aljad;
                boolean qissmatAlikhwa;
                boolean is_decimal = false;        // النصيب دوما عدد صحيح ما عدا ربما حالة الشقيقات دون عصبة مع الجد

                switch (mAljadMa3aAlikhwa) {
                    case THULUTH_ALBAQI:
                        ro2os_aljad = 1;
                        if (m.getWarith() == Warith.ALJAD) {
                            factor1 = ro2os_aljad;
                            ro2os1 = 3;  // assert m.getRo2os() == mHal.mAdadRo2osAlbaqi == 3;
                            qissmatAlikhwa = false;
                        } else {  // ikhwa
                            factor1 = 2;
                            ro2os1 = 3;
                            qissmatAlikhwa = true;
                        }
                        break;

                    case MUQASSAMA:
                        ro2os_aljad = mHal.mTassawi ? 1 : 2;
                        if (m.getWarith() == Warith.ALJAD) {
                            factor1 = ro2os_aljad;
                            ro2os1 = m.getRo2os();
                            qissmatAlikhwa = false;
                        } else {  // ikhwa
                            ro2os1 = mHal.mRo2osAlbaqi;
                            factor1 = ro2os1 - ro2os_aljad;
                            qissmatAlikhwa = true;
                        }
                        break;

                    default:
                        factor1 = ro2os1 = ro2os_aljad = 0;
                        qissmatAlikhwa = true;
                        break;
                }

                if (m_alakhawat_tarithna_albaki_ila_alfardh) {
                    if (m.isShakikatTarithnaAlbaqiIlaFardh()) {
                        double baqi = mHal.mBaqi;
                        double limit = (double)mHal.mAsl * m.getBast() / m.getMaqam();
                        double nassib_aljad = 0;
                        if (mAljadMa3aAlikhwa == AljadMa3aAlikhwa.THULUTH_ALBAQI) {
                            nassib_aljad = ro2os_aljad;
                        }
                        else if (mAljadMa3aAlikhwa == AljadMa3aAlikhwa.MUQASSAMA) {
                            nassib_aljad = all.get(mHal.mNbrFurudh).getNassibFardi();  // nassib aljad
                        }
                        baqi -= nassib_aljad;
                        if (baqi > limit) {
                            factor = limit;
                            is_decimal = (factor - (int)factor) != 0;
                            mHal.mBaqiAlbaqi = (double)baqi - limit;
                        }
                        else {
                            factor = baqi;
                            mHal.mBaqiAlbaqi = 0;
                        }
                        factor1 = 1;
                        ro2os1 = m.getRo2os();      // mHal.mRo2osAlbaqi;
                    }
                    else if (m.getWarith() != Warith.ALJAD) {
                        factor = mHal.mBaqiAlbaqi;
                        is_decimal = (factor - (int)factor) != 0;
                        if (m.getRo2os() > 1) {
                            factor1 = mHal.mTassawi ? 1 : 2;
                            ro2os1 = m.getRo2os();  // mHal.mRo2osBaqiAlbaqi;
                        }
                        else {
                            factor1 = ro2os1 = 0;
                        }
                    }
                    qissmatAlikhwa = false;
                }

                nassibMojmal.append(Mirath.nassibToString(factor));
                nassibFardi.append(Mirath.nassibToString(factor));

                if (ro2os1 != 0) {
//                    nassibMojmal.append("ش");
//                    shirka = true;

                    nassibFardi.append(" * ");
                    nassibFardi.append(factor1).append("\\").append(ro2os1);
                    factor *= factor1;
                    ro2os *= ro2os1;
                }

                if (qissmatAlikhwa && m.isShirka()) {
//                    if (!shirka) { nassibMojmal.append("ش"); }

                    int factor2 = mHal.mTassawi || m.getWarith().isOntha() ? 1 : 2;
                    factor *= factor2;

                    int ro2os2 = m.getRo2os();
                    nassibFardi.append(" * ").append(factor2).append("\\").append(ro2os2);
                    ro2os *= ro2os2;
                }

                if ((factor % ro2os) == 0) {
                    nassib = factor / ro2os;
                }
                else { //  إنكسار
                    if (m_alakhawat_tarithna_albaki_ila_alfardh && is_decimal) {
                        if ((factor - (int)factor) == .5) {
                            missahFactor = lcm(missahFactor, 2*ro2os / gcd((int)(2*factor), 2*ro2os));
                        } else {
                            missahFactor = lcm(missahFactor, 3*ro2os / gcd((int)(3*factor), 3*ro2os));
                        }
                    } else {
                        missahFactor = lcm(missahFactor, ro2os / gcd((int)factor, ro2os));
                    }
                    nassib = factor/ro2os;
                }
            }
            else if (m.isMahjoob()) {
                nassibMojmal.append("--");
                nassibFardi.append("--");
                nassib = 0;
            }
            else {
                System.out.printf("BUG! %s خطأ وارث ليس صاحب فرض أو تعصيب أو حجب", m.getWarith().getName());
                continue;
            }

            if (m_alakhawat_tarithna_albaki_ila_alfardh && m.getWarith() == Warith.ALJAD) {
                m.setNassibMojmal(Mirath.nassibToString(nassib));
            }
            else {
                m.setNassibMojmal( nassibMojmal.toString());
            }
            m.setNassibFardiText(nassibFardi.toString());
            m.setNassibFardi(nassib);
        }

        if (mHal.mAwl != 0) {
            mHal.mMissah = mHal.mAwl;
        }
        else if (mHal.mAslJami3a != 0) {
            mHal.mMissah = mHal.mAslJami3a;
        }
        else if (mHal.mAslRad != 0) {
            mHal.mMissah = mHal.mAslRad;
        }
        else if (mInput.get_madhab() != Madhab.HANAFI && mInput.zawj() && mInput.alom() && mInput.aljad() &&
                (mInput.get_alakhawat_ashakikat() == 1 || mInput.get_alakhawat_li_ab() == 1) &&
                /* mHal.mNbrFurudh == 3 && mHal.mNbr3assabat == 1 && */
                mHal.mWarathah.size() == 4 && mHal.mMahjoobin.size() == 0) {

            mHal.mAkdaria = true;
            mHal.mNaw3 = Naw3.NAW3_AWL;
            mHal.mMissah = mHal.mAwl = 9;
            missahFactor = 3;

            Mirath aljad = mHal.mWarathah.get(mHal.mWarathah.size()-2);
            aljad.setNassibFardi(4.0 * 2/3);

            Mirath alukht = mHal.mWarathah.get(mHal.mWarathah.size()-1);
            alukht.setNassibMojmal("3");
            alukht.setNassibFardi(4.0/3);
        }
        else {
            mHal.mMissah = mHal.mAsl;
        }
        mHal.mMissah *= missahFactor;            // assert !=0

        if (missahFactor != 1) {
            // Tashih, go again :-)
            for (Mirath m:all) {
                if (m.isMahjoob()) continue;
                double oldNassib = m.getNassibFardi();
                if (m.isFardh() && m.isTa3seeb()) {
                    nassib = (m.getFardh() + oldNassib) * missahFactor;
                }
                else {
                    nassib = oldNassib * missahFactor;
                }
                m.setNassibFardiSahih(nassib);
                m.setNassibFardiSahihText(m.getNassibFardiText() + " * " + missahFactor + " = " + Mirath.nassibToString(nassib));
            }
        }
    }
    public String getSharh() {  return mSharh; }

    private void hissabAsharh() {
        StringBuilder sharh = new StringBuilder("\n");

        if (mHal.mWarathah != null) {
            for (Mirath m : mHal.mWarathah) {
                sharh.append("- ").append(m.getSharh()).append(".\n");
            }
        }

        if (mHal.mMahjoobin != null) {
            for (Mirath m : mHal.mMahjoobin) {
                sharh.append("- ").append(m.getSharh()).append(".\n");
            }
        }

        sharh.append("\n- أصل المسألة ").append(mHal.mAsl);
        switch (mHal.mNaw3) {
            case NAW3_3ADILA:
                sharh.append("، وهي عادلة (تساوى أصلها مع أسهمها).\n");
                break;
            case NAW3_RAD_3ALA_WAHED:
            case NAW3_RAD_3ALA_WAHED_ZAWJIA:
            case NAW3_RAD_3ALA_MUTAJANISEEN_ZAWJIA:
            case NAW3_RAD_3ALA_MUKHTALIFEEN_ZAWJIA:
            case NAW3_RAD_3ALA_MUTAJANISEEN:
            case NAW3_RAD_3ALA_MUKHTALIFEEN:    // TODO detail msg?
                sharh.append(" وهي ناقصة (أسهمها ").append(mHal.mAshom).append(" أقل من أصلها).\n");
                sharh.append("- الباقي ").append(mHal.mBaqi).append(" يُرد على أصحاب الفروض عدا من وُجد من الزوجين.\n");
                if (mHal.mNaw3 != Naw3.NAW3_RAD_3ALA_MUKHTALIFEEN_ZAWJIA) {
                    sharh.append("- بعد الرد يُصبح أصل المسألة ").append(mHal.mAslJami3a).append(".\n");
                }
                else {
                    sharh.append("- مخرج مسألة الزوجية ").append(mHal.mAslZawjia);
                    sharh.append("، وأصل مسألة الرد (دون أحد الزوجين) ").append(mHal.mAslRad);
                    sharh.append("، وأصل مسألة الرد الجامعة ").append(mHal.mAslJami3a).append(".\n");
                }
                break;
            case NAW3_AWL:
                sharh.append(" وهي عائلة (أسهمها أكثر من أصلها)، تعول إلى ").append(mHal.mAwl).append(".\n");
                break;
            case NAW3_ALGHARRAWAYN:
                sharh.append(" وتسمى الغرّاوين.\n");
                break;
            case NAW3_MUSHTARIKA:
                sharh.append(" وتسمى المشتركة (والمشرّكة واليمّية والحجرية والمنبرية والحمارية).\n");
                if (mInput.get_madhab() == Madhab.HAMBALI || mInput.get_madhab() == Madhab.HANAFI) {
                    sharh.append("- استغرق أصحاب الفروض الأسهم، ولم يبق للورثة بالتعصيب شيء فسقطوا، وهذا على مذهب الإمامين أبي حنيفة وابن حنبل رحمهما الله.\n");
                }
                else {
                    sharh.append("- استغرق أصحاب الفروض الأسهم، فاشترك الورثة بالتعصيب مع الإخوة لأم في الثلث، وهذا على مذهب الإمامين مالك والشافعي رحمهما الله.\n");
                }
                break;
            case NAW3_3ADIA:
            default:
                sharh.append(".\n");
                break;
        }

        if (mHal.mNbr3assabat > 0) {
            if (mHal.mIstighraq && !mHal.mAkdaria && mHal.mNaw3 != Naw3.NAW3_MUSHTARIKA) {
                sharh.append("- استغرق أصحاب الفروض الأسهم ولم يبق للورثة بالتعصيب شيء.\n");
            } else {
                sharh.append("- الباقي من أصل المسألة ").append(mHal.mBaqi);
                switch (mAljadMa3aAlikhwa) {
                    case MUQASSAMA:
                        sharh.append("، يتقاسمه الجد و ").append(Warith.ALJAD.getAlikhwa(mInput));
                        break;
                    case THULUTH_ALBAQI:
                        sharh.append("، يأخذ منه الجد الثلث، ويتقاسم ما بقي ").append(Warith.ALJAD.getAlikhwa(mInput));
                        break;
                    case THULUTH_ALMAL:
                    case SUDUSS:
                    case LA:
                    default:
                        break;
                }
                sharh.append("، وعدد رؤوسه ").append(mHal.mRo2osAlbaqi).append(".\n");
                // Note: can be more verbose by showing BaqiAlbaqi and its Ro2osBaqiAlbaqi

                if (mHal.mMu3addah) {
                    sharh.append("- هذه المسألة من مسائل المُعادّة، أي يُعدُّ (بمعنى يُحسب) على الجد جميع الإخوة الأشقاء ولأب عند تقدير الأحظ له، ");
                    sharh.append(" ثم يشترك الأشقاء و\\أو الشقيقات في ما بقي، فإن كانوا عصبة حجبوا الإخوة و\\أو الأخوات لأب، ");
                    sharh.append("وإلا تأخذ الشقيقات في غياب العاصب (الأخ أو الفرع الوارث الأنثى) الباقي إلى فرضهن (وهو النصف للواحدة أو الثلثين للإثنتين فأكثر)، ");
                    sharh.append("فإن بقي شيء بعد ذلك فللإخوة و\\أو الأخوات لأب، وإلا سقطوا.\n");
                }
                else if (mHal.mAkdaria) {
                    sharh.append("- هذه المسألة تُسمى الأكدرية لأنها كدّرت قواعد الجد والإخوة، حيث بقي بعد فرض الزوج والأم سدس المال، ");
                    sharh.append("وكان المفروض أن يُعطى للجد وتسقط الأخت، لكنهم فرضوا لها الباقي إلى النصف (أي 3 أسهم)، فعالت المسألة من 6 إلى 9، ");
                    sharh.append("ثم يعود الجد والأخت إلى المقاسمة حيث يُجمع سدسه إلى نصفها ويُقسم عليهما للذكر مثل حظ الأنثيين، ");
                    sharh.append("فيُؤخذ عدد رؤوسهما 3 ويُضرب في 9 فتصح المسألة من 27.\n");
                }
            }
        }
        if (mHal.isInkissar() && !mHal.mAkdaria) {
            sharh.append("- المسألة فيها انكسار وتصح من ").append(mHal.mMissah).append(".\n");
        }
        mSharh = sharh.toString();
    }

    //public boolean isTassawi() { return mHal.mTassawi; }

    public boolean isMushtarika() {
        return mHal.mNaw3 == Naw3.NAW3_MUSHTARIKA
            && (mInput.get_madhab() == Madhab.MALIKI || mInput.get_madhab() == Madhab.CHAFI3I);
    }

    public boolean isShirkaTa3seeb() { return mHal.mShirkaTa3seeb; }

    public boolean isInkissar() { return mHal.isInkissar();}

    public boolean isJinsayAwladAlom() { return (mInput.get_alikhwa_li_om() > 0) && (mInput.get_alakhawat_li_om() > 0); }

    public boolean isAljadatayn() { return mInput.aljadah_li_om() && mInput.aljadah_li_ab(); }

    public boolean isHissabFardi() {
        return isInkissar() || isShirkaTa3seeb() || isJinsayAwladAlom() || isAljadatayn() ||
                mInput.get_azawjat() > 1 ||
                mInput.get_alabna() > 1 ||
                mInput.get_albanat() > 1 ||
                mInput.get_abna_alabna() > 1 ||
                mInput.get_banat_alabna() > 1 ||
                mInput.get_alikhwa_li_om() > 1 ||
                mInput.get_alakhawat_li_om() > 1 ||
                mInput.get_alikhwa_alashika() > 1 ||
                mInput.get_alakhawat_ashakikat() > 1 ||
                mInput.get_alikhwa_li_ab() > 1 ||
                mInput.get_alakhawat_li_ab() > 1 ||
                mInput.get_abna_alikhwa_alashika() > 1 ||
                mInput.get_abna_alikhwa_li_ab() > 1 ||
                mInput.get_ala3mam_alashika() > 1 ||
                mInput.get_ala3mam_li_ab() > 1 ||
                mInput.get_abna_ala3mam_alashika() > 1 ||
                mInput.get_abna_ala3mam_li_ab() > 1;
    }

    public Naw3 getNaw3() { return mHal.mNaw3; }

    public int getAsl() { return mHal.mAsl; }
    public int getAslRad() { return mHal.mAslRad; }
    public int getAslJami3a() { return mHal.mAslJami3a; }
    public int getAslZawjia() { return mHal.mAslZawjia; }

    public int getBaqiZawjia() { return mHal.mBaqiZawjia; }

    //public int getAshom() { return mHal.mAshom; }

    //public int getBaqi() { return mHal.mBaqi; }

    public int getAwl() { return mHal.mAwl; }

    public int getMissah() { return mHal.mMissah; }

    public ArrayList<Mirath> getMawarith() {
        ArrayList<Mirath> all = new ArrayList<>();

        all.addAll(mHal.mWarathah);
        all.addAll(mHal.mMahjoobin);

        return all;
    }

    // move to Utilities class?
    private int lcm(int a, int b) {
        if (a == 0 || b == 0) { return 0; }
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

    private int gcd(int a, int b) {  // Euclid's Algorithm
        if (b == 0) { return a; }
        return gcd(b, a % b);
    }
}
