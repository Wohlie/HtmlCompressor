/**
 * Copyright 2009 - 2012    Sergiy Kovalchuk the original author or other authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.googlecode.htmlcompressor.compressor;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class XmlCompressorTest
    extends AbstractCompressor {

    public XmlCompressorTest() {
        this.setResourceBasePath("./src/test/resources/xml/");
    }

    @Test
    public void testCompress() throws Exception {
        String source = readResource("testCompress.xml");
        String result = readResource("testCompressResult.xml");

        XmlCompressor compressor = new XmlCompressor();

        assertEquals(result, compressor.compress(source));
    }

    @Test
    public void testEnabled() throws Exception {
        String source = readResource("testEnabled.xml");
        String result = readResource("testEnabledResult.xml");

        XmlCompressor compressor = new XmlCompressor();
        compressor.setEnabled(false);

        assertEquals(result, compressor.compress(source));
    }

    @Test
    public void testRemoveComments() throws Exception {
        String source = readResource("testRemoveComments.xml");
        String result = readResource("testRemoveCommentsResult.xml");

        XmlCompressor compressor = new XmlCompressor();
        compressor.setRemoveComments(true);

        assertEquals(result, compressor.compress(source));
    }

    @Test
    public void testRemoveIntertagSpaces() throws Exception {
        String source = readResource("testRemoveIntertagSpaces.xml");
        String result = readResource("testRemoveIntertagSpacesResult.xml");

        XmlCompressor compressor = new XmlCompressor();
        compressor.setRemoveIntertagSpaces(true);

        assertEquals(result, compressor.compress(source));
    }
}