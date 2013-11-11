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

public class FileTest
    extends AbstractCmd
{

    /**
     * Test a single file as input and the standard output as output.
     *
     * @throws Throwable
     */
    @Test
    public void testSingleInputStdOutput() throws Throwable {
        File inputFile      = new File(inputDirPath + "foo.html");
        String[] args       = {
            inputFile.getPath(),
        };

        Map<String, String> map = getInputOutputMap(args);
        Assert.assertEquals(1, map.size());
        Assert.assertTrue(map.containsKey(inputFile.getAbsolutePath()));
        Assert.assertNull(map.get(inputFile.getAbsolutePath()));
    }

    /**
     * Test a single file as input and a file as output.
     *
     * @throws Throwable
     */
    @Test
    public void testSingleInputToFile() throws Throwable {
        File inputFile      = new File(inputDirPath + "foo.html");
        File outputFile     = new File(outputDirPath + "temp.html");
        String[] args       = {
            "-o",
            outputFile.getPath(),
            inputFile.getPath(),
        };

        Map<String, String> map = getInputOutputMap(args);
        Assert.assertEquals(1, map.size());
        Assert.assertTrue(map.containsKey(inputFile.getAbsolutePath()));
        Assert.assertEquals(outputFile.getAbsolutePath(), map.get(inputFile.getAbsolutePath()));
    }

    /**
     * Test a single file as input and a file with suffix option as output.
     *
     * @throws Throwable
     */
    @Test
    public void testSingleInputToFileWithSuffix() throws Throwable {
        File inputFile      = new File(inputDirPath + "foo.html");
        File outputFile     = new File(outputDirPath + "temp.html");
        File realOutputFile = new File(outputDirPath + "temp-min.html");
        String[] args       = {
            "-o",
            outputFile.getPath(),
            "-s",
            "-min",
            inputFile.getPath(),
        };

        Map<String, String> map = getInputOutputMap(args);
        Assert.assertEquals(1, map.size());
        Assert.assertTrue(map.containsKey(inputFile.getAbsolutePath()));
        Assert.assertEquals(realOutputFile.getAbsolutePath(), map.get(inputFile.getAbsolutePath()));
    }

    /**
     * Test a single file as input and a dir as output.
     *
     * @throws Throwable
     */
    @Test
    public void testSingleInputToDirectory() throws Throwable {
        File inputFile      = new File(inputDirPath + "foo.html");
        File outputFile     = new File(outputDirPath + "foo.html");
        String[] args       = {
            "-o",
            outputDirPath,
            inputFile.getPath(),
        };

        Map<String, String> map = getInputOutputMap(args);
        Assert.assertEquals(1, map.size());
        Assert.assertTrue(map.containsKey(inputFile.getAbsolutePath()));
        Assert.assertEquals(outputFile.getAbsolutePath(), map.get(inputFile.getAbsolutePath()));
    }

    /**
     * Test a single file as input and a dir as output.
     *
     * @throws Throwable
     */
    @Test
    public void testSingleInputToDirectoryWithSuffix() throws Throwable {
        File inputFile      = new File(inputDirPath + "foo.html");
        File outputFile     = new File(outputDirPath + "foo-min.html");
        String[] args       = {
            "-o",
            outputDirPath,
            "-s",
            "-min",
            inputFile.getPath(),
        };

        Map<String, String> map = getInputOutputMap(args);
        Assert.assertEquals(1, map.size());
        Assert.assertTrue(map.containsKey(inputFile.getAbsolutePath()));
        Assert.assertEquals(outputFile.getAbsolutePath(), map.get(inputFile.getAbsolutePath()));
    }

    /**
     * Test a exception if a file is given that not exists.
     *
     * @throws Throwable
     */
    @Test
    public void testSingleNotExistingInputToStandardOutput() throws Throwable {
        String[] args       = {
            inputDirPath + "not-existing-file.html",
        };

        try {
            getInputOutputMap(args);
            Assert.fail("Exception was expected.");
        } catch (Exception e) {
            Assert.assertEquals(
                String.format(
                    "The given input \"%s\" doesn't exist.",
                    inputDirPath + "not-existing-file.html"
                ),
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
        String[] args       = {
            inputDirPath + "foo.html",
            inputDirPath + "bar.html",
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
        String[] args       = {
            "-o",
            outputDirPath + "temp.html",
            inputDirPath + "foo.html",
            inputDirPath + "bar.html",
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
        File inputFileFoo   = new File(inputDirPath + "foo.html");
        File inputFileBar   = new File(inputDirPath + "bar.html");
        File outputFileFoo  = new File(outputDirPath + "foo.html");
        File outputFileBar  = new File(outputDirPath + "bar.html");
        String[] args       = {
            "-o",
            outputDirPath,
            inputFileFoo.getPath(),
            inputFileBar.getPath(),
        };

        Map<String, String> map = getInputOutputMap(args);
        Assert.assertEquals(2, map.size());
        Assert.assertTrue(map.containsKey(inputFileFoo.getAbsolutePath()));
        Assert.assertTrue(map.containsKey(inputFileBar.getAbsolutePath()));
        Assert.assertEquals(outputFileFoo.getAbsolutePath(), map.get(inputFileFoo.getAbsolutePath()));
        Assert.assertEquals(outputFileBar.getAbsolutePath(), map.get(inputFileBar.getAbsolutePath()));
    }

    /**
     * Test multiple input files and a dir as output.
     *
     * @throws Throwable
     */
    @Test
    public void testMultipleInputToDirectoryWithSuffix() throws Throwable {
        File inputFileFoo   = new File(inputDirPath + "foo.html");
        File inputFileBar   = new File(inputDirPath + "bar.html");
        File outputFileFoo  = new File(outputDirPath + "foo-min.html");
        File outputFileBar  = new File(outputDirPath + "bar-min.html");
        String[] args       = {
            "-o",
            outputDirPath,
            "-s",
            "-min",
            inputFileFoo.getPath(),
            inputFileBar.getPath(),
        };

        Map<String, String> map = getInputOutputMap(args);
        Assert.assertEquals(2, map.size());
        Assert.assertTrue(map.containsKey(inputFileFoo.getAbsolutePath()));
        Assert.assertTrue(map.containsKey(inputFileBar.getAbsolutePath()));
        Assert.assertEquals(outputFileFoo.getAbsolutePath(), map.get(inputFileFoo.getAbsolutePath()));
        Assert.assertEquals(outputFileBar.getAbsolutePath(), map.get(inputFileBar.getAbsolutePath()));
    }

    /**
     * Test multiple input files and a dir as output.
     *
     * @throws Throwable
     */
    @Test
    public void testMultipleInputToDirectoryWithTheSameFileName() throws Throwable {
        File inputFileBar1   = new File(inputDirPath + "bar.html");
        File inputFileBar2   = new File(inputDirPath + "other/bar.html");
        File outputFileBar   = new File(outputDirPath + "bar.html");
        String[] args       = {
            "-o",
            outputDirPath,
            inputFileBar1.getPath(),
            inputFileBar2.getPath(),
        };

        try {
            getInputOutputMap(args);
            Assert.fail("Exception was expected.");
        } catch (Exception e) {
            Assert.assertEquals(
                e.getMessage(),
                String.format(
                    "The output file \"%s\" will used from the two input files \"%s\" and \"%s\".",
                    outputFileBar.getAbsolutePath(),
                    inputFileBar1.getAbsolutePath(),
                    inputFileBar2.getAbsolutePath()
                )
            );
        }
    }
}
