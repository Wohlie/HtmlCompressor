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

public class DirTest
    extends AbstractCmd {
    /**
     * Test a single directory as input and the standard output as output.
     *
     * @throws Throwable
     */
    @Test
    public void testSingleInputStdOutput() throws Throwable {
        String[] args = {
            inputDirPath,
        };

        try {
            getInputOutputMap(args);
            Assert.fail("Exception was expected.");
        } catch (Exception e) {
            Assert.assertEquals(
                "Only a single file or standard input can process if no output or a output file is defined.",
                e.getMessage()
            );
        }
    }

    /**
     * Test a single directory as input with no output directory but an file name suffix so all files will saved
     * in the directory with the file name suffix.
     *
     * @throws Throwable
     */
    @Test
    public void testSingleInputToWithSuffix() throws Throwable {
        File inputFileFoo = new File(inputDirPath + "foo.html");
        File inputFileBar = new File(inputDirPath + "bar.html");
        File inputFileTest = new File(inputDirPath + "test.phtml");
        File outputFileFoo = new File(inputDirPath + "foo-min.html");
        File outputFileBar = new File(inputDirPath + "bar-min.html");
        File outputFileTest = new File(inputDirPath + "test-min.phtml");
        String[] args = {
            "-s",
            "-min",
            inputDirPath,
        };

        Map<String, String> map = getInputOutputMap(args);
        Assert.assertEquals(3, map.size());

        Assert.assertTrue(map.containsKey(inputFileFoo.getAbsolutePath()));
        Assert.assertEquals(outputFileFoo.getAbsolutePath(), map.get(inputFileFoo.getAbsolutePath()));

        Assert.assertTrue(map.containsKey(inputFileBar.getAbsolutePath()));
        Assert.assertEquals(outputFileBar.getAbsolutePath(), map.get(inputFileBar.getAbsolutePath()));

        Assert.assertTrue(map.containsKey(inputFileTest.getAbsolutePath()));
        Assert.assertEquals(outputFileTest.getAbsolutePath(), map.get(inputFileTest.getAbsolutePath()));
    }

    /**
     * Test a single directory as input and a file as output.
     *
     * @throws Throwable
     */
    @Test
    public void testSingleInputToFile() throws Throwable {
        String[] args = {
            "-o",
            inputDirPath + "foo.html",
            inputDirPath,
        };

        try {
            getInputOutputMap(args);
            Assert.fail("Exception was expected.");
        } catch (Exception e) {
            Assert.assertEquals(
                "Only a single file or standard input can process if no output or a output file is defined.",
                e.getMessage()
            );
        }
    }

    /**
     * Test a single directory as input and a dir as output.
     *
     * @throws Throwable
     */
    @Test
    public void testSingleInputToDirectory() throws Throwable {
        File inputFileFoo = new File(inputDirPath + "foo.html");
        File inputFileBar = new File(inputDirPath + "bar.html");
        File inputFileTest = new File(inputDirPath + "test.phtml");
        File outputFileFoo = new File(outputDirPath + "foo.html");
        File outputFileBar = new File(outputDirPath + "bar.html");
        File outputFileTest = new File(outputDirPath + "test.phtml");
        String[] args = {
            "-o",
            outputDirPath,
            inputDirPath,
        };

        Map<String, String> map = getInputOutputMap(args);
        Assert.assertEquals(3, map.size());

        Assert.assertTrue(map.containsKey(inputFileFoo.getAbsolutePath()));
        Assert.assertEquals(outputFileFoo.getAbsolutePath(), map.get(inputFileFoo.getAbsolutePath()));

        Assert.assertTrue(map.containsKey(inputFileBar.getAbsolutePath()));
        Assert.assertEquals(outputFileBar.getAbsolutePath(), map.get(inputFileBar.getAbsolutePath()));

        Assert.assertTrue(map.containsKey(inputFileTest.getAbsolutePath()));
        Assert.assertEquals(outputFileTest.getAbsolutePath(), map.get(inputFileTest.getAbsolutePath()));
    }

    /**
     * Test a single directory as input and a dir with a file name suffix as output.
     *
     * @throws Throwable
     */
    @Test
    public void testSingleInputToDirectoryWithSuffix() throws Throwable {
        File inputFileFoo = new File(inputDirPath + "foo.html");
        File inputFileBar = new File(inputDirPath + "bar.html");
        File inputFileTest = new File(inputDirPath + "test.phtml");
        File outputFileFoo = new File(outputDirPath + "foo-min.html");
        File outputFileBar = new File(outputDirPath + "bar-min.html");
        File outputFileTest = new File(outputDirPath + "test-min.phtml");
        String[] args = {
            "-o",
            outputDirPath,
            "-s",
            "-min",
            inputDirPath,
        };

        Map<String, String> map = getInputOutputMap(args);
        Assert.assertEquals(3, map.size());

        Assert.assertTrue(map.containsKey(inputFileFoo.getAbsolutePath()));
        Assert.assertEquals(outputFileFoo.getAbsolutePath(), map.get(inputFileFoo.getAbsolutePath()));

        Assert.assertTrue(map.containsKey(inputFileBar.getAbsolutePath()));
        Assert.assertEquals(outputFileBar.getAbsolutePath(), map.get(inputFileBar.getAbsolutePath()));

        Assert.assertTrue(map.containsKey(inputFileTest.getAbsolutePath()));
        Assert.assertEquals(outputFileTest.getAbsolutePath(), map.get(inputFileTest.getAbsolutePath()));
    }

    /**
     * Test a single directory as input and a dir with a file mask as output.
     *
     * @throws Throwable
     */
    @Test
    public void testSingleInputToDirectoryWithFileMask() throws Throwable {
        File inputFileFoo = new File(inputDirPath + "foo.html");
        File inputFileBar = new File(inputDirPath + "bar.html");
        File outputFileFoo = new File(outputDirPath + "foo.html");
        File outputFileBar = new File(outputDirPath + "bar.html");
        String[] args = {
            "-o",
            outputDirPath,
            "-m",
            "*.html",
            inputDirPath,
        };

        Map<String, String> map = getInputOutputMap(args);
        Assert.assertEquals(2, map.size());

        Assert.assertTrue(map.containsKey(inputFileFoo.getAbsolutePath()));
        Assert.assertEquals(outputFileFoo.getAbsolutePath(), map.get(inputFileFoo.getAbsolutePath()));

        Assert.assertTrue(map.containsKey(inputFileBar.getAbsolutePath()));
        Assert.assertEquals(outputFileBar.getAbsolutePath(), map.get(inputFileBar.getAbsolutePath()));
    }

    /**
     * Test a single directory as input and a dir with a file name suffix and file mask as output.
     *
     * @throws Throwable
     */
    @Test
    public void testSingleInputToDirectoryWithSuffixAndFileMask() throws Throwable {
        File inputFileFoo = new File(inputDirPath + "foo.html");
        File inputFileBar = new File(inputDirPath + "bar.html");
        File outputFileFoo = new File(outputDirPath + "foo-min.html");
        File outputFileBar = new File(outputDirPath + "bar-min.html");
        String[] args = {
            "-o",
            outputDirPath,
            "-s",
            "-min",
            "-m",
            "*.html",
            inputDirPath,
        };

        Map<String, String> map = getInputOutputMap(args);
        Assert.assertEquals(2, map.size());

        Assert.assertTrue(map.containsKey(inputFileFoo.getAbsolutePath()));
        Assert.assertEquals(outputFileFoo.getAbsolutePath(), map.get(inputFileFoo.getAbsolutePath()));

        Assert.assertTrue(map.containsKey(inputFileBar.getAbsolutePath()));
        Assert.assertEquals(outputFileBar.getAbsolutePath(), map.get(inputFileBar.getAbsolutePath()));
    }

    /**
     * Test a exception with an input directory that has no files.
     *
     * @throws Throwable
     */
    @Test
    public void testSingleInputWithNoFiles() throws Throwable {
        String[] args = {
            "-o",
            outputDirPath,
            inputDirPath + "empty",
        };

        try {
            getInputOutputMap(args);
            Assert.fail("Exception was expected.");
        } catch (Exception e) {
            Assert.assertEquals(
                "No input files found.",
                e.getMessage()
            );
        }
    }

    /**
     * Test a exception if a directory has files but all will excluded by the file mask.
     *
     * @throws Throwable
     */
    @Test
    public void testSingleInputToDirectoryWithFileMaskNoFiles() throws Throwable {
        String[] args = {
            "-o",
            outputDirPath,
            "-m",
            "*.foo",
            inputDirPath,
        };

        try {
            getInputOutputMap(args);
            Assert.fail("Exception was expected.");
        } catch (Exception e) {
            Assert.assertEquals(
                "No input files found.",
                e.getMessage()
            );
        }
    }

    /**
     * Test multiple directory as input and a dir as output.
     *
     * @throws Throwable
     */
    @Test
    public void testMultipleInputToDirectory() throws Throwable {
        File inputFileFoo = new File(inputDirPath + "foo.html");
        File inputFileBar = new File(inputDirPath + "bar.html");
        File inputFileTest = new File(inputDirPath + "test.phtml");
        File inputFileFoobar = new File(inputDirPath + "other/other/Foobar.html");
        File inputFileTmp = new File(inputDirPath + "other/other/tmp");
        File outputFileFoo = new File(outputDirPath + "foo.html");
        File outputFileBar = new File(outputDirPath + "bar.html");
        File outputFileTest = new File(outputDirPath + "test.phtml");
        File outputFileFoobar = new File(outputDirPath + "Foobar.html");
        File outputFileTmp = new File(outputDirPath + "tmp");
        String[] args = {
            "-o",
            outputDirPath,
            inputDirPath,
            inputDirPath + "/other/other",
        };

        Map<String, String> map = getInputOutputMap(args);
        Assert.assertEquals(5, map.size());

        Assert.assertTrue(map.containsKey(inputFileFoo.getAbsolutePath()));
        Assert.assertEquals(outputFileFoo.getAbsolutePath(), map.get(inputFileFoo.getAbsolutePath()));

        Assert.assertTrue(map.containsKey(inputFileBar.getAbsolutePath()));
        Assert.assertEquals(outputFileBar.getAbsolutePath(), map.get(inputFileBar.getAbsolutePath()));

        Assert.assertTrue(map.containsKey(inputFileTest.getAbsolutePath()));
        Assert.assertEquals(outputFileTest.getAbsolutePath(), map.get(inputFileTest.getAbsolutePath()));

        Assert.assertTrue(map.containsKey(inputFileFoobar.getAbsolutePath()));
        Assert.assertEquals(outputFileFoobar.getAbsolutePath(), map.get(inputFileFoobar.getAbsolutePath()));

        Assert.assertTrue(map.containsKey(inputFileTmp.getAbsolutePath()));
        Assert.assertEquals(outputFileTmp.getAbsolutePath(), map.get(inputFileTmp.getAbsolutePath()));
    }

    /**
     * Test multiple directory as input and a dir with file name suffix as output.
     *
     * @throws Throwable
     */
    @Test
    public void testMultipleInputToDirectoryWithSuffix() throws Throwable {
        File inputFileFoo = new File(inputDirPath + "foo.html");
        File inputFileBar = new File(inputDirPath + "bar.html");
        File inputFileTest = new File(inputDirPath + "test.phtml");
        File inputFileFoobar = new File(inputDirPath + "other/other/Foobar.html");
        File inputFileTmp = new File(inputDirPath + "other/other/tmp");
        File outputFileFoo = new File(outputDirPath + "foo-test.html");
        File outputFileBar = new File(outputDirPath + "bar-test.html");
        File outputFileTest = new File(outputDirPath + "test-test.phtml");
        File outputFileFoobar = new File(outputDirPath + "Foobar-test.html");
        File outputFileTmp = new File(outputDirPath + "tmp-test");
        String[] args = {
            "-o",
            outputDirPath,
            "-s",
            "-test",
            inputDirPath,
            inputDirPath + "/other/other",
        };

        Map<String, String> map = getInputOutputMap(args);
        Assert.assertEquals(5, map.size());

        Assert.assertTrue(map.containsKey(inputFileFoo.getAbsolutePath()));
        Assert.assertEquals(outputFileFoo.getAbsolutePath(), map.get(inputFileFoo.getAbsolutePath()));

        Assert.assertTrue(map.containsKey(inputFileBar.getAbsolutePath()));
        Assert.assertEquals(outputFileBar.getAbsolutePath(), map.get(inputFileBar.getAbsolutePath()));

        Assert.assertTrue(map.containsKey(inputFileTest.getAbsolutePath()));
        Assert.assertEquals(outputFileTest.getAbsolutePath(), map.get(inputFileTest.getAbsolutePath()));

        Assert.assertTrue(map.containsKey(inputFileFoobar.getAbsolutePath()));
        Assert.assertEquals(outputFileFoobar.getAbsolutePath(), map.get(inputFileFoobar.getAbsolutePath()));

        Assert.assertTrue(map.containsKey(inputFileTmp.getAbsolutePath()));
        Assert.assertEquals(outputFileTmp.getAbsolutePath(), map.get(inputFileTmp.getAbsolutePath()));
    }

    /**
     * Test a exception if multiple directories has the same file name.
     *
     * @throws Throwable
     */
    @Test
    public void testMultipleInputToDirectoryWithTheSameFile() throws Throwable {
        File outputFileBar = new File(outputDirPath + "bar.html");
        File inputFileBar1 = new File(inputDirPath + "bar.html");
        File inputFileBar2 = new File(inputDirPath + "other/bar.html");
        String[] args = {
            "-o",
            outputDirPath,
            inputDirPath,
            inputDirPath + "/other/",
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

    /**
     * Set a single directory with recursive option and a dir, file mask filter and file name suffix as output.
     *
     * @throws Throwable
     */
    @Test
    public void testRecursiveSingleInputToDirectoryWithSuffixAndFileMask() throws Throwable {
        File inputFileBar = new File(inputDirPath + "other/bar.html");
        File inputFileBaz = new File(inputDirPath + "other/baz.html");
        File inputFileFoobar = new File(inputDirPath + "other/other/Foobar.html");
        File outputFileBar = new File(outputDirPath + "bar-test.html");
        File outputFileBaz = new File(outputDirPath + "baz-test.html");
        File outputFileFoobar = new File(outputDirPath + "other/Foobar-test.html");
        String[] args = {
            "-r",
            "-o",
            outputDirPath,
            "-s",
            "-test",
            "-m",
            "*.html",
            inputDirPath + "other",
        };

        Map<String, String> map = getInputOutputMap(args);
        Assert.assertEquals(3, map.size());

        Assert.assertTrue(map.containsKey(inputFileBar.getAbsolutePath()));
        Assert.assertEquals(outputFileBar.getAbsolutePath(), map.get(inputFileBar.getAbsolutePath()));

        Assert.assertTrue(map.containsKey(inputFileBaz.getAbsolutePath()));
        Assert.assertEquals(outputFileBaz.getAbsolutePath(), map.get(inputFileBaz.getAbsolutePath()));

        Assert.assertTrue(map.containsKey(inputFileFoobar.getAbsolutePath()));
        Assert.assertEquals(outputFileFoobar.getAbsolutePath(), map.get(inputFileFoobar.getAbsolutePath()));
    }

    /**
     * Set a single directory with recursive option and file mask filter and file name suffix as output.
     *
     * @throws Throwable
     */
    @Test
    public void testRecursiveSingleInputToWithSuffixAndFileMask() throws Throwable {
        File inputFileBar = new File(inputDirPath + "other/bar.html");
        File inputFileBaz = new File(inputDirPath + "other/baz.html");
        File inputFileFoobar = new File(inputDirPath + "other/other/Foobar.html");
        File outputFileBar = new File(inputDirPath + "other/bar-test.html");
        File outputFileBaz = new File(inputDirPath + "other/baz-test.html");
        File outputFileFoobar = new File(inputDirPath + "other/other/Foobar-test.html");
        String[] args = {
            "-r",
            "-s",
            "-test",
            "-m",
            "*.html",
            inputDirPath + "other",
        };

        Map<String, String> map = getInputOutputMap(args);
        Assert.assertEquals(3, map.size());

        Assert.assertTrue(map.containsKey(inputFileBar.getAbsolutePath()));
        Assert.assertEquals(outputFileBar.getAbsolutePath(), map.get(inputFileBar.getAbsolutePath()));

        Assert.assertTrue(map.containsKey(inputFileBaz.getAbsolutePath()));
        Assert.assertEquals(outputFileBaz.getAbsolutePath(), map.get(inputFileBaz.getAbsolutePath()));

        Assert.assertTrue(map.containsKey(inputFileFoobar.getAbsolutePath()));
        Assert.assertEquals(outputFileFoobar.getAbsolutePath(), map.get(inputFileFoobar.getAbsolutePath()));
    }
}
