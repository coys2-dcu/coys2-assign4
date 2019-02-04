/*
 * Copyright (C) 2016 The Android Open Source Project
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
package com.application1.coys.assign42019stephencoy;

/**
 * {@link AndroidFlavor} represents a single Android platform release.
 * Each object has 3 properties: name, manufacturer, and image resource ID.
 */
public class AndroidFlavor {

    // Name of the Android version (e.g. Hammer, Screwdriver)
    private String mVersionName;

    // Android version number (e.g. Tool Make)
    private String mManufacturer;

    // Drawable resource ID
    private int mImageResourceId;

    /*
    * Create a new AndroidFlavor object.
    *
    * @param vName is the name of the Android version (e.g. Gingerbread)
    * @param vManufacturer is the corresponding Android version number (e.g. Stanley)
    * @param image is drawable reference ID that corresponds to the Android version
    * */
    public AndroidFlavor(String vName, String vNumber, int imageResourceId)
    {
        mVersionName = vName;
        mManufacturer = vNumber;
        mImageResourceId = imageResourceId;
    }

    /**
     * Get the name of the Android version
     */
    public String getVersionName() {
        return mVersionName;
    }

    /**
     * Get the Android version number
     */
    public String getVersionNumber() {
        return mManufacturer;
    }

    /**
     * Get the image resource ID
     */
    public int getImageResourceId() {
        return mImageResourceId;
    }


}
