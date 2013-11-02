/**
 * Copyright 2009-2013 the original author or authors.
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
package com.googlecode.htmlcompressor.compressor;

import com.google.javascript.jscomp.CompilationLevel;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;

public class HtmlCompressorTest
    extends AbstractCompressor
{

    public HtmlCompressorTest()
    {
        this.setResourceBasePath("./src/test/resources/html/");
    }

	@Test
	public void testEnabled() throws Exception {
		String source = readResource("testEnabled.html");
		String result = readResource("testEnabledResult.html");
		
		HtmlCompressor compressor = new HtmlCompressor();
		compressor.setEnabled(false);
		
		assertEquals(result, compressor.compress(source));
	}
	
	@Test
	public void testRemoveSpacesInsideTags() throws Exception {
		String source = readResource("testRemoveSpacesInsideTags.html");
		String result = readResource("testRemoveSpacesInsideTagsResult.html");
		
		HtmlCompressor compressor = new HtmlCompressor();
		compressor.setRemoveMultiSpaces(false);
		
		assertEquals(result, compressor.compress(source));
	}
	
	@Test
	public void testRemoveComments() throws Exception {
		String source = readResource("testRemoveComments.html");
		String result = readResource("testRemoveCommentsResult.html");
		
		HtmlCompressor compressor = new HtmlCompressor();
		compressor.setRemoveComments(true);
		compressor.setRemoveIntertagSpaces(true);

		assertEquals(result, compressor.compress(source));
	}
	
	@Test
	public void testRemoveQuotes() throws Exception {
		String source = readResource("testRemoveQuotes.html");
		String result = readResource("testRemoveQuotesResult.html");
		
		HtmlCompressor compressor = new HtmlCompressor();
		compressor.setRemoveQuotes(true);
		
		assertEquals(result, compressor.compress(source));
	}
	
	@Test
	public void testRemoveMultiSpaces() throws Exception {
		String source = readResource("testRemoveMultiSpaces.html");
		String result = readResource("testRemoveMultiSpacesResult.html");
		
		HtmlCompressor compressor = new HtmlCompressor();
		compressor.setRemoveMultiSpaces(true);
		
		assertEquals(result, compressor.compress(source));
	}
	
	@Test
	public void testRemoveIntertagSpaces() throws Exception {
		String source = readResource("testRemoveIntertagSpaces.html");
		String result = readResource("testRemoveIntertagSpacesResult.html");
		
		HtmlCompressor compressor = new HtmlCompressor();
		compressor.setRemoveIntertagSpaces(true);
		
		assertEquals(result, compressor.compress(source));
	}
	
	@Test
	public void testPreservePatterns() throws Exception {
		String source = readResource("testPreservePatterns.html");
		String result = readResource("testPreservePatternsResult.html");
		
		List<Pattern> preservePatterns = new ArrayList<Pattern>();
		preservePatterns.add(HtmlCompressor.PHP_TAG_PATTERN); //<?php ... ?> blocks
		preservePatterns.add(HtmlCompressor.SERVER_SCRIPT_TAG_PATTERN); //<% ... %> blocks
		preservePatterns.add(HtmlCompressor.SERVER_SIDE_INCLUDE_PATTERN); //<!--# ... --> blocks
		preservePatterns.add(Pattern.compile("<jsp:.*?>", Pattern.DOTALL | Pattern.CASE_INSENSITIVE)); //<jsp: ... > tags

		HtmlCompressor compressor = new HtmlCompressor();
		compressor.setPreservePatterns(preservePatterns);
		compressor.setRemoveComments(true);
		compressor.setRemoveIntertagSpaces(true);
		
		assertEquals(result, compressor.compress(source));
	}
	
	@Test
	public void testCompressJavaScriptYui() throws Exception {
		String source = readResource("testCompressJavaScript.html");
		String result = readResource("testCompressJavaScriptYuiResult.html");
		
		HtmlCompressor compressor = new HtmlCompressor();
		compressor.setCompressJavaScript(true);
		compressor.setRemoveIntertagSpaces(true);
		
		assertEquals(result, compressor.compress(source));
	}

	@Test
	public void testCompressJavaScriptClosure() throws Exception {
		String source = readResource("testCompressJavaScript.html");
		String result = readResource("testCompressJavaScriptClosureResult.html");
		
		HtmlCompressor compressor = new HtmlCompressor();
		compressor.setCompressJavaScript(true);
		compressor.setJavaScriptCompressor(new ClosureJavaScriptCompressor(CompilationLevel.ADVANCED_OPTIMIZATIONS));
		compressor.setRemoveIntertagSpaces(true);
		
		assertEquals(result, compressor.compress(source));
	}
	
	@Test
	public void testCompressCss() throws Exception {
		String source = readResource("testCompressCss.html");
		String result = readResource("testCompressCssResult.html");
		
		HtmlCompressor compressor = new HtmlCompressor();
		compressor.setCompressCss(true);
		compressor.setRemoveIntertagSpaces(true);
		
		assertEquals(result, compressor.compress(source));
	}
	
	@Test
	public void testCompress() throws Exception {
		String source = readResource("testCompress.html");
		String result = readResource("testCompressResult.html");
		
		HtmlCompressor compressor = new HtmlCompressor();
		
		assertEquals(result, compressor.compress(source));
	}
	
	@Test
	public void testSimpleDoctype() throws Exception {
		String source = readResource("testSimpleDoctype.html");
		String result = readResource("testSimpleDoctypeResult.html");
		
		HtmlCompressor compressor = new HtmlCompressor();
		compressor.setSimpleDoctype(true);
		
		assertEquals(result, compressor.compress(source));
	}
	
	@Test
	public void testRemoveScriptAttributes() throws Exception {
		String source = readResource("testRemoveScriptAttributes.html");
		String result = readResource("testRemoveScriptAttributesResult.html");
		
		HtmlCompressor compressor = new HtmlCompressor();
		compressor.setRemoveScriptAttributes(true);
		
		assertEquals(result, compressor.compress(source));
	}
	
	@Test
	public void testRemoveStyleAttributes() throws Exception {
		String source = readResource("testRemoveStyleAttributes.html");
		String result = readResource("testRemoveStyleAttributesResult.html");
		
		HtmlCompressor compressor = new HtmlCompressor();
		compressor.setRemoveStyleAttributes(true);
		
		assertEquals(result, compressor.compress(source));
	}
	
	@Test
	public void testRemoveLinkAttributes() throws Exception {
		String source = readResource("testRemoveLinkAttributes.html");
		String result = readResource("testRemoveLinkAttributesResult.html");
		
		HtmlCompressor compressor = new HtmlCompressor();
		compressor.setRemoveLinkAttributes(true);
		
		assertEquals(result, compressor.compress(source));
	}
	
	@Test
	public void testRemoveFormAttributes() throws Exception {
		String source = readResource("testRemoveFormAttributes.html");
		String result = readResource("testRemoveFormAttributesResult.html");
		
		HtmlCompressor compressor = new HtmlCompressor();
		compressor.setRemoveFormAttributes(true);
		
		assertEquals(result, compressor.compress(source));
	}
	
	@Test
	public void testRemoveInputAttributes() throws Exception {
		String source = readResource("testRemoveInputAttributes.html");
		String result = readResource("testRemoveInputAttributesResult.html");
		
		HtmlCompressor compressor = new HtmlCompressor();
		compressor.setRemoveInputAttributes(true);
		
		assertEquals(result, compressor.compress(source));
	}
	
	@Test
	public void testRemoveJavaScriptProtocol() throws Exception {
		String source = readResource("testRemoveJavaScriptProtocol.html");
		String result = readResource("testRemoveJavaScriptProtocolResult.html");
		
		HtmlCompressor compressor = new HtmlCompressor();
		compressor.setRemoveJavaScriptProtocol(true);
		
		assertEquals(result, compressor.compress(source));
	}

	@Test
	public void testRemoveHttpProtocol() throws Exception {
		String source = readResource("testRemoveHttpProtocol.html");
		String result = readResource("testRemoveHttpProtocolResult.html");
		
		HtmlCompressor compressor = new HtmlCompressor();
		compressor.setRemoveHttpProtocol(true);
		
		assertEquals(result, compressor.compress(source));
	}

	@Test
	public void testRemoveHttpsProtocol() throws Exception {
		String source = readResource("testRemoveHttpsProtocol.html");
		String result = readResource("testRemoveHttpsProtocolResult.html");
		
		HtmlCompressor compressor = new HtmlCompressor();
		compressor.setRemoveHttpsProtocol(true);
		
		assertEquals(result, compressor.compress(source));
	}

	@Test
	public void testPreserveLineBreaks() throws Exception {
		String source = readResource("testPreserveLineBreaks.html");
		String result = readResource("testPreserveLineBreaksResult.html");
		
		HtmlCompressor compressor = new HtmlCompressor();
		compressor.setPreserveLineBreaks(true);
		
		assertEquals(result, compressor.compress(source));
	}

	@Test
	public void testSurroundingSpaces() throws Exception {
		String source = readResource("testSurroundingSpaces.html");
		String result = readResource("testSurroundingSpacesResult.html");
		
		HtmlCompressor compressor = new HtmlCompressor();
		compressor.setRemoveIntertagSpaces(true);
		compressor.setRemoveSurroundingSpaces("p,br");
		
		assertEquals(result, compressor.compress(source));
	}

    @Test
    public void testCdataVariants() throws Exception {
        String source = readResource("testCdata.html");
        String result = readResource("testCdataResult.html");

        HtmlCompressor compressor = new HtmlCompressor();
        compressor.setCompressJavaScript(true);
        compressor.setCompressCss(true);
        compressor.setRemoveIntertagSpaces(true);

        assertEquals(result, compressor.compress(source));
    }

    @Test
    public void testPhpEof() throws Exception {
        String source = readResource("testPhpEof.html");
        String result = readResource("testPhpEofResult.html");

        List<Pattern> preservePatterns = new ArrayList<Pattern>();
        preservePatterns.add(HtmlCompressor.PHP_TAG_PATTERN); //<?php ... ?> blocks

        HtmlCompressor compressor = new HtmlCompressor();
        compressor.setRemoveIntertagSpaces(true);
        compressor.setPreservePatterns(preservePatterns);

        assertEquals(result, compressor.compress(source));
    }
}