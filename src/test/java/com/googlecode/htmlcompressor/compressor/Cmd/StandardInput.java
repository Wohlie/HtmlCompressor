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

public class StandardInput
    extends AbstractCmd {

    /**
     * Test standard input and standard output as output.
     *
     * @throws Throwable
     */
    @Test
    public void testSingleInputStdOutput() throws Throwable {
        String[] args = {
        };

        Map<String, String> map = getInputOutputMap(args);
        Assert.assertEquals(1, map.size());
        Assert.assertTrue(map.containsKey(null));
        Assert.assertNull(map.get(null));
    }

    /**
     * Test standard input and a file as output.
     *
     * @throws Throwable
     */
    @Test
    public void testSingleInputToFile() throws Throwable {
        File outputFile = new File(outputDirPath + "temp.html");
        String[] args = {
            "-o",
            outputFile.getPath(),
        };

        Map<String, String> map = getInputOutputMap(args);
        Assert.assertEquals(1, map.size());
        Assert.assertTrue(map.containsKey(null));
        Assert.assertEquals(outputFile.getAbsolutePath(), map.get(null));
    }

    /**
     * Test standard input and a file with suffix option as output.
     *
     * @throws Throwable
     */
    @Test
    public void testSingleInputToFileWithSuffix() throws Throwable {
        File outputFile = new File(outputDirPath + "temp.html");
        File realOutputFile = new File(outputDirPath + "temp-min.html");
        String[] args = {
            "-o",
            outputFile.getPath(),
            "-s",
            "-min",
        };

        Map<String, String> map = getInputOutputMap(args);
        Assert.assertEquals(1, map.size());
        Assert.assertTrue(map.containsKey(null));
        Assert.assertEquals(realOutputFile.getAbsolutePath(), map.get(null));
    }

    /**
     * Test standard input and a dir as output.
     *
     * @throws Throwable
     */
    @Test
    public void testSingleInputToDirectory() throws Throwable {
        String[] args = {
            "-o",
            outputDirPath,
        };

        try {
            getInputOutputMap(args);
            Assert.fail("Exception was expected.");
        } catch (Exception e) {
            Assert.assertEquals(
                "Standard input can only output as standard output or file.",
                e.getMessage()
            );
        }
    }

    /**
     * Test standard input and a dir with suffix option as output.
     *
     * @throws Throwable
     */
    @Test
    public void testSingleInputToDirectoryWithSuffix() throws Throwable {
        String[] args = {
            "-o",
            outputDirPath,
            "-s",
            "-min",
        };

        try {
            getInputOutputMap(args);
            Assert.fail("Exception was expected.");
        } catch (Exception e) {
            Assert.assertEquals(
                "Standard input can only output as standard output or file.",
                e.getMessage()
            );
        }
    }
}
