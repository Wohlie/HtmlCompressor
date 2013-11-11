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
package com.googlecode.htmlcompressor.compressor;

import java.io.*;

public abstract class AbstractCompressor {

    /**
     * Holds the base path for the test resources.
     */
    protected String resourceBasePath;


    /**
     * Return the content of the given file that are located in the resourceBasePath.
     */
    protected String readResource(String filename) {
        StringBuilder builder = new StringBuilder();
        try {
            FileInputStream stream = new FileInputStream(new File(getResourceBasePath() + filename));
            try {
                Reader reader = new BufferedReader(new InputStreamReader(stream));
                char[] buffer = new char[8192];
                int read;
                while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
                    builder.append(buffer, 0, read);
                }
            } finally {
                stream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return builder.toString();
    }

    /**
     * Return the resource base path.
     */
    public String getResourceBasePath() {
        return resourceBasePath;
    }

    /**
     * Set the resource base path.
     */
    public void setResourceBasePath(String resourceBasePath) {
        this.resourceBasePath = resourceBasePath;
    }
}