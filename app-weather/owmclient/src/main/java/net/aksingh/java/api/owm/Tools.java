/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2013-2014 Ashutosh Kumar Singh [me@aksingh.net]
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
*/

package net.aksingh.java.api.owm;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Provides various tools, which help doing tasks in this application. For
 * example, tool for downloading content from the Internet, tool for correcting,
 * etc.
 * <p>
 * <p>
 * Note: This class directly do not provide any functions, but has
 * <code>static</code> sub-classes which provide various related tools, i.e.,
 * this class only behaves as the container for those classes.
 * <p>
 * @author Ashutosh Kumar Singh
 * @version 2014-07-01
 * @since 2.5.0.1
 */
public class Tools {

    /**
     * Provides methods to download data or files from the Internet.
     * <p>
     * @author Ashutosh Kumar Singh
     * @version 2013-07-24
     * @since 2.5.0.1
     */
    public static class Downloader {

        /**
         * Downloads a page/content from the Internet. This method gets the
         * content of the web page, whose URL is given by the
         * <code>pageAddress</code>.
         * <p>
         * <p>
         * NOTE: <code>pageAddress</code> should be a correct URL, else this
         * method will throw {@link java.net.MalformedURLException}.
         * <p>
         * @param pageAddress Address of the web page to get from the Internet.
         * <p>
         * @return Content of the web page
         * <p>
         * @throws java.net.MalformedURLException Address of the web page is not correct.
         * <p>
         * @throws java.io.IOException           Error while loading the page from the
         *                               Internet or connection got
         *                               disconnected.
         */
        public static String downloadPage(String pageAddress)
                throws MalformedURLException, IOException {
            String webPage = null;

            URL url = new URL(pageAddress);
            InputStream iStream = url.openStream();

            if (iStream != null) {
                // converting InputStream to String (as we require String)
                webPage = (new Scanner(iStream)).useDelimiter("\\A").next();
            }
            iStream.close();

            return webPage;
        }
    }

    /**
     * Provides methods to do conversions. For example, converting degree to
     * direction, etc.
     * <p>
     * @author Ashutosh Kumar Singh
     * @version 2013/08/05
     * @since 2.5.0.1
     */
    public static class Convertor {

        /**
         * Converts degree to direction.
         * <p>
         * @param degree <p>
         * @return Direction
         * <p>
         * @throws IllegalArgumentException Degree should be between 0 and 360.
         */
        public String convertDegree2Direction(float degree)
                throws IllegalArgumentException {
            String direction;

            // degree should be between 0 and 360
            if ((degree < 0.0f) || (degree > 360.0f)) {
                throw new IllegalArgumentException("Degree cannot be less than 0 or more than 360.");
            }

            if (degree <= 11.25f) {
                direction = "N";
            } else if (degree <= 33.75f) {
                direction = "NNE";
            } else if (degree <= 56.25f) {
                direction = "NE";
            } else if (degree <= 78.75f) {
                direction = "ENE";
            } else if (degree <= 101.25f) {
                direction = "E";
            } else if (degree <= 123.75f) {
                direction = "ESE";
            } else if (degree <= 146.25f) {
                direction = "SE";
            } else if (degree <= 168.75f) {
                direction = "SSE";
            } else if (degree <= 191.25f) {
                direction = "S";
            } else if (degree <= 213.75f) {
                direction = "SSW";
            } else if (degree <= 236.25f) {
                direction = "SW";
            } else if (degree <= 258.75f) {
                direction = "WSW";
            } else if (degree <= 281.25f) {
                direction = "W";
            } else if (degree <= 303.75f) {
                direction = "WNW";
            } else if (degree <= 326.25f) {
                direction = "NW";
            } else if (degree <= 348.75f) {
                direction = "NNW";
            } else {
                direction = "N";
            }

            return direction;
        }
    }
}
