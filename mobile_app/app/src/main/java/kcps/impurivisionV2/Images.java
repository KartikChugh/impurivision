/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package kcps.impurivisionV2;

/**
 * Some simple test data to use for this sample app.
 */
public class Images {

    /**
     * This are PicasaWeb URLs and could potentially change. Ideally the PicasaWeb API should be
     * used to fetch the URLs.
     *
     * Credit to Romain Guy for the photos:
     * http://www.curious-creature.org/
     * https://plus.google.com/109538161516040592207/about
     * http://www.flickr.com/photos/romainguy
     */
    public final static String[] imageThumbUrls = new String[] {
            "https://s18.postimg.org/kh06hqlhl/Arsenic.png",
            "https://s18.postimg.org/p2waq3eqh/Copper_Sulfate.png",
            "https://s18.postimg.org/wivkbuzux/Developed_General.jpg",
            "https://s18.postimg.org/5xt1gb57d/Developed_Industrial.jpg",
            "https://s18.postimg.org/rk81x9qw9/Developing_Industrial.jpg",
            "https://s18.postimg.org/pfnow74p5/Developing_Rural.png",
            "https://s18.postimg.org/6nbtsmg0p/Fertilizer.png",
            "https://s18.postimg.org/ajp5om8q1/Gasoline.png",
            "https://s18.postimg.org/dqjp890vt/Insect_Repellent.png",
            "https://s18.postimg.org/j1ylsz2e1/Lead.png",
            "https://s18.postimg.org/o0m47idwp/Mercury.png",
            "https://s18.postimg.org/lw1r6ewuh/Paint.png",
            "https://s18.postimg.org/ur2lgwj21/Pesticide.jpg",
            "https://s18.postimg.org/5xt1gaprt/Pure.png",
            "https://s18.postimg.org/7037yuvqh/Salt.png",
            "https://s18.postimg.org/6akfmi2wp/Sediment.png",
            "https://s18.postimg.org/llu8nanft/water1_post.png",
            "https://s18.postimg.org/5au4qzy3d/water2_post.png",
    };

    /**
     * This are PicasaWeb thumbnail URLs and could potentially change. Ideally the PicasaWeb API
     * should be used to fetch the URLs.
     *
     * Credit to Romain Guy for the photos:
     * http://www.curious-creature.org/
     * https://plus.google.com/109538161516040592207/about
     * http://www.flickr.com/photos/romainguy
     */
    public final static String[] imageUrls = new String[] {
            "https://s18.postimg.org/ybyh07fwp/arsenic.png",
            "https://s18.postimg.org/i0yd3vvp5/coppersulfate.png",
            "https://s18.postimg.org/7e4jyg849/developedgeneral.png",
            "https://s18.postimg.org/5z2z9r1w9/developingindustrial.png", // developed industrial
            "https://s18.postimg.org/y0h0nm789/actual_developing_industrial.png",
            "https://s18.postimg.org/wlffywtah/developing_rural.png",
            "https://s18.postimg.org/4k1el0dnt/fertilizer.png",
            "https://s18.postimg.org/6olrm4hvd/gasoline.png",
            "https://s18.postimg.org/q6gf201xl/repellent.png",
            "https://s18.postimg.org/59k6xew7t/lead.png",
            "https://s18.postimg.org/axqhoasuh/mercury.png",
            "https://s18.postimg.org/lkkatplk9/paint.png",
            "https://s18.postimg.org/o1w2100bt/pesticides.png",
            "https://s18.postimg.org/ma336143d/water.png",
            "https://s18.postimg.org/g95e8y21l/sodium_salt.png",
            "https://s18.postimg.org/tdayln1t5/sediment.png",
            "https://s18.postimg.org/dt3kvbp6h/water1_diff.png",
            "https://s18.postimg.org/mbd0zoiuh/water2_diff.png",

            //"https://s18.postimg.org/4k1ekzy89/water1.png", unused
    };

    public final static String[] imgId = new String[] {
        "Arsenic", "Copper Sulfate", "Cleaning Agents", "Industrial Toxins", "Industrial Waste", "Sediment Deposits", "Fertilizer", "Gasoline", "Insecticides", "Lead",
        "Mercury", "Paint", "Pesticides", "Water", "Sodium", "Sediment", "Water", "Water",
    };
}
