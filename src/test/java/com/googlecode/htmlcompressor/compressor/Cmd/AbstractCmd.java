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

import com.googlecode.htmlcompressor.CmdLineCompressor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public abstract class AbstractCmd {

    /**
     * Holds the input directory.
     */
    protected String inputDirPath = "./src/test/resources/cmd/input/";

    /**
     * Holds a sample output directory.
     */
    protected String outputDirPath = "./src/test/resources/cmd/output/";

    /**
     * Create the input output map from the command line parser.
     *
     * @param args Holds the command line parameters.
     * @return Map with input to output file mapping.
     * @throws Exception
     */
    protected Map<String, String> getInputOutputMap(String[] args) throws Throwable {
        Class classDefinition = Class.forName("com.googlecode.htmlcompressor.CmdLineCompressor");
        CmdLineCompressor cmdLineCompressor = (CmdLineCompressor) classDefinition.getConstructor(new Class[]{String[].class}).newInstance(new Object[]{args});
        Method method = classDefinition.getDeclaredMethod("buildInputOutputMap");
        method.setAccessible(true);

        try {
            return (Map<String, String>) method.invoke(cmdLineCompressor);
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }
}
