/**
 * Copyright 2013           Erik Wohllebe <erik.wohllebe@googlemail.com>.
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
package com.googlecode.htmlcompressor.compressor.Cmd;

import junit.framework.Assert;
import org.junit.Test;

import java.io.File;
import java.util.Map;

public class UrlTest
    extends AbstractCmd {
    /**
     * Test a single file as input and the standard output as output.
     *
     * @throws Throwable
     */
    @Test
    public void testSingleInputStdOutput() throws Throwable {
        String url = "http://example.com";
        String[] args = {
            url,
        };

        Map<String, String> map = getInputOutputMap(args);
        Assert.assertEquals(1, map.size());
        Assert.assertTrue(map.containsKey(url));
        Assert.assertNull(map.get(url));
    }

    /**
     * Test a single file as input and a file as output.
     *
     * @throws Throwable
     */
    @Test
    public void testSingleInputToFile() throws Throwable {
        String url = "https://example.com";
        File outputFile = new File(outputDirPath + "temp.html");
        String[] args = {
            "-o",
            outputFile.getPath(),
            url,
        };

        Map<String, String> map = getInputOutputMap(args);
        Assert.assertEquals(1, map.size());
        Assert.assertTrue(map.containsKey(url));
        Assert.assertEquals(outputFile.getAbsolutePath(), map.get(url));
    }

    /**
     * Test a single file as input and a file with suffix option as output.
     *
     * @throws Throwable
     */
    @Test
    public void testSingleInputToFileWithSuffix() throws Throwable {
        String url = "http://example.org/";
        File outputFile = new File(outputDirPath + "temp.html");
        File realOutputFile = new File(outputDirPath + "temp-min.html");
        String[] args = {
            "-o",
            outputFile.getPath(),
            "-s",
            "-min",
            url,
        };

        Map<String, String> map = getInputOutputMap(args);
        Assert.assertEquals(1, map.size());
        Assert.assertTrue(map.containsKey(url));
        Assert.assertEquals(realOutputFile.getAbsolutePath(), map.get(url));
    }

    /**
     * Test a single file as input and a dir as output.
     *
     * @throws Throwable
     */
    @Test
    public void testSingleInputToDirectory() throws Throwable {
        String url = "http://example.org/";
        String[] args = {
            "-o",
            outputDirPath,
            url,
        };

        try {
            getInputOutputMap(args);
            Assert.fail("Exception was expected.");
        } catch (Exception e) {
            Assert.assertEquals(
                "Input URL should be a single input and only output as standard output or file.",
                e.getMessage()
            );
        }
    }

    /**
     * Test multiple input files to standard output.
     *
     * @throws Throwable
     */
    @Test
    public void testMultipleInputStdOutput() throws Throwable {
        String[] args = {
            "http://example.org/",
            "http://example.com/",
        };

        try {
            getInputOutputMap(args);
            Assert.fail("Exception was expected.");
        } catch (Exception e) {
            Assert.assertEquals(
                String.format(
                    "Output must be a directory with a tailing \"%s\" for multiple input files.", File.separator
                ),
                e.getMessage()
            );
        }
    }

    /**
     * Test multiple input files to a single output file.
     *
     * @throws Throwable
     */
    @Test
    public void testMultipleInputToFile() throws Throwable {
        String[] args = {
            "-o",
            outputDirPath + "temp.html",
            "http://example.org/",
            "http://example.com/",
        };

        try {
            getInputOutputMap(args);
            Assert.fail("Exception was expected.");
        } catch (Exception e) {
            Assert.assertEquals(
                String.format(
                    "Output must be a directory with a tailing \"%s\" for multiple input files.", File.separator
                ),
                e.getMessage()
            );
        }
    }

    /**
     * Test multiple input files and a dir as output.
     *
     * @throws Throwable
     */
    @Test
    public void testMultipleInputToDirectory() throws Throwable {
        String[] args = {
            "-o",
            outputDirPath,
            "http://example.org/",
            "http://example.com/",
        };

        try {
            getInputOutputMap(args);
            Assert.fail("Exception was expected.");
        } catch (Exception e) {
            Assert.assertEquals(
                "Input URL should be a single input and only output as standard output or file.",
                e.getMessage()
            );
        }
    }
}
