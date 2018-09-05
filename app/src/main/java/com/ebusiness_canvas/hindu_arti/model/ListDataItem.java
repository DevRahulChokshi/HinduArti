package com.ebusiness_canvas.hindu_arti.model;

import com.ebusiness_canvas.hindu_arti.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListDataItem {

    public static int [] getGroupImage(){
        return new int[]{
                R.drawable.img_arrti,
                R.drawable.img_shlock,
                R.drawable.img_swami_samrth,
                R.drawable.img_nitykarma,
                R.drawable.img_utsav,
        };
    }
    public static int [] getChildImageOne(){
        return new int[]{
                R.drawable.img_ganesh_arti,
                R.drawable.img_shiv,
                R.drawable.img_dutta,
                R.drawable.img_sri_devi_maa,
                R.drawable.img_hanuman,
                R.drawable.img_shree_krishna,
                R.drawable.img_trupati_balaji,
                R.drawable.swami_sadguru,
                R.drawable.img_vitthal
        } ;
    }
    public static int [] getChildImageTwo(){
        return new int[]{
                R.drawable.karpur,
                R.drawable.mantra_pushpajanli,
                R.drawable.ganesh_vandan,
                R.drawable.ganesh_stroat,
                R.drawable.bhim_rupee,
                R.drawable.img_ram,
                R.drawable.swarspati_vandan,
                R.drawable.mahalaxmi,
                R.drawable.atharvashirsha,
                R.drawable.gaytri_mantra,
                R.drawable.pshaydan
        } ;
    }
    public static int [] getChildImageThree(){
        return new int[]{
                R.drawable.swami_samarth_din,
                R.drawable.swami_tarak_mantra,
                R.drawable.swami_mala_mantra,
                R.drawable.img_swami_samrth,
                R.drawable.swami_pic1,
                R.drawable.swami_pic2,
                R.drawable.swami_pic3,
                R.drawable.swami_pic4,
                R.drawable.swami_pic5
        } ;
    }
    public static int [] getChildImageFour(){
        return new int[]{
                R.drawable.kar_prathna,
                R.drawable.bhoomi_vandan,
                R.drawable.sanhn_parthna,
                R.drawable.annh_grahan,
                R.drawable.shub_karoti,
                R.drawable.jop_shlock
        } ;
    }
    public static int [] getChildImageFive(){
        return new int[]{
                R.drawable.makarsankaranti,
                R.drawable.maha_shivratri,
                R.drawable.img_holi,
                R.drawable.gudi_padwa,
                R.drawable.img_ram,
                R.drawable.akshay_tritiya,
                R.drawable.vat_paurnima,
                R.drawable.guru_paurnima,
                R.drawable.nag_panchami,
                R.drawable.nareli_paurnima,
                R.drawable.janmasthami,
                R.drawable.pola,
                R.drawable.haritalika,
                R.drawable.rushipanchami,
                R.drawable.ganesh_stroat,
                R.drawable.img_ganesh_arti,
                R.drawable.img_navratri,
                R.drawable.img_dusera,
                R.drawable.img_dipawali
        } ;
    }

    public static HashMap<String, List<String>> getData() {

        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> nitykarma = new ArrayList<String>();
        nitykarma.add("कर प्रार्थना");
        nitykarma.add("भूमी वंदना");
        nitykarma.add("स्नान प्रार्थना");
        nitykarma.add("अन्नग्रहण प्रार्थना");
        nitykarma.add("शुभंकरोती");
        nitykarma.add("झोपताना म्हणावयाचा श्लोक");

        List<String> shlok_strot = new ArrayList<String>();
        shlok_strot.add("गणेश वंदना");
        shlok_strot.add("गणपती स्तोत्र");
        shlok_strot.add("भीम रूपीं");
        shlok_strot.add("राम रक्षा");
        shlok_strot.add("सरस्वती वंदना");
        shlok_strot.add("महालक्ष्मी वंदना");
        shlok_strot.add("अथर्व शीर्ष");
        shlok_strot.add("गायत्री मंत्र");
        shlok_strot.add("पसायदान");

        List<String> aarti = new ArrayList<String>();
        aarti.add("श्री गणपती आरती");
        aarti.add("श्री शंकराची आरती");
        aarti.add("श्री दत्त आरती");
        aarti.add("देवीची आरती");
        aarti.add("हनुमानाची आरती");
        aarti.add("श्री कृष्णाची आरती");
        aarti.add("बालाजीची आरती");
        aarti.add("सद्गुरूची आरती");
        aarti.add("श्री विट्ठलाची आरती");
        aarti.add("कर्पुरारती");
        aarti.add("मंत्र पुष्पांजली");

        List<String> utsav = new ArrayList<String>();
        utsav.add("मकरसंक्रांत");
        utsav.add("महाशिवरात्री");
        utsav.add("होळी उस्तव");
        utsav.add("गुढी पाडवा");
        utsav.add("श्रीरामनवमी");
        utsav.add("अक्षय तृतीया");
        utsav.add("वटपौर्णिमा");
        utsav.add("गुरूपौर्णिमा");
        utsav.add("आषाढी एकादशी");
        utsav.add("नागपंचमी");
        utsav.add("नारळी पौर्णिमा");
        utsav.add("श्रीकृष्ण जन्माष्टमी");
        utsav.add("पोळा");
        utsav.add("हरितालिका");
        utsav.add("ऋषि पंचमी");
        utsav.add("श्रीगणेश चतुर्थी");
        utsav.add("अनंत चतुर्दशी");
        utsav.add("नवरात्री");
        utsav.add("दसरा - विजयादशमी");
        utsav.add("दीपावली");

        List<String> swami_samrth = new ArrayList<String>();
        swami_samrth.add("श्री स्वामी समर्थ प्रकट दिन");
        swami_samrth.add("श्री स्वामी समर्थ तारक मंत्र");
        swami_samrth.add("श्री स्वामी समर्थ मालामंत्र");
        swami_samrth.add("श्री स्वामी समर्थ स्तवन");
        swami_samrth.add("श्री स्वामी समर्थाष्टक");
        swami_samrth.add("श्री स्वामी समर्थ अभिषेक पद");
        swami_samrth.add("श्री स्वामी समर्थ मानस पूजा");
        swami_samrth.add("औदुंबर प्रदक्षिणा");
        swami_samrth.add("श्री स्वामी समर्थ आरती संग्रह");


        expandableListDetail.put("नित्य कर्म", nitykarma);
        expandableListDetail.put("श्लोक/स्तोत्र", shlok_strot);
        expandableListDetail.put("आरती संग्रह", aarti);
        expandableListDetail.put("सण-उत्सव", utsav);
        expandableListDetail.put("श्री स्वामी समर्थ प्रकट दिन विशेष", swami_samrth);


        return expandableListDetail;
    }


}
