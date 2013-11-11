YaHc (Yet another HTML compressor)
==============
YaHc is a fork of [**HTMLCompressor**](https://code.google.com/p/htmlcompressor/).
YaHc is a small, fast and easy to use command line tool and Java library that
minifies HTML or XML source by removing extra whitespaces, comments and other
unneeded characters without breaking the content structure. As a result pages
become smaller in size and load faster. By providing  additional libraries
like [**YUI Compressor**](https://github.com/yui/yuicompressor/) or [**Google
Closure Compiler**](https://code.google.com/p/closure-compiler/) YaHc can
automatically optimize JS and CSS parts in the given input.

Here are few examples of YaHc results with default settings:
<table>
  <tr>
    <th>Site</th>
    <th>Plain Size / gziped Size</th>
    <th>YaHc Size / gziped Size</th>
    <th>Plain Ratio / gziped Ratio</th>
  </tr>
  <tr>
    <td><a href="https://github.com/">github.com</a></td>
    <td>12,553b / &nbsp;3,745b</td>
    <td>&nbsp;10,788b / &nbsp;3,475b</td>
    <td>14.06% / &nbsp;7.21%</td>
  </tr>
  <tr>
    <td><a href="http://stackoverflow.com/">stackoverflow.com</a></td>
    <td>195,049b / 30,660b</td>
    <td>166,124b / 29,546b</td>
    <td>14.83% / &nbsp;3.63%</td>
  </tr>
  <tr>
    <td><a href="http://magento.com/">magento.com</a></td>
    <td>62,500b / 14,319b</td>
    <td>&nbsp;46,117b / 11,798b</td>
    <td>26.21% / 17.61%</td>
  </tr>
  <tr>
    <td><a href="http://cnn.com/">cnn.com</a></td>
    <td>126,382b / 24,510b</td>
    <td>123,170b / 23,902b</td>
    <td>&nbsp;2.54% / &nbsp;2.48%</td>
  </tr>
  <tr>
    <td><a href="http://www.stern.de/">www.stern.de</a></td>
    <td>230,186b / 36,333b</td>
    <td>190,388b / 34,019b</td>
    <td>17.29% / &nbsp;6.37%</td>
  </tr>
  <tr>
    <td><a href="http://www.spiegel.de/">www.spiegel.de</a></td>
    <td>204,923b / 38,119b</td>
    <td>193,044b / 36,637b</td>
    <td>&nbsp;5.80% / &nbsp;3.89%</td>
  </tr>
</table>

## Requirements
 - JRE v6
 - [**YUI Compressor**](https://github.com/yui/yuicompressor/) (optional)
 - [**Google Closure Compiler**](https://code.google.com/p/closure-compiler/) (optional)

## Usage
    Usage: java -jar yahc.jar [options] [input]

    [input]                        URL, filename, directory, or space separated list
                                   of files and directories to compress.
                                   If none provided reads from <stdin>

    Global Options:
     -?, /?, -h, --help            Displays this help screen
     -t, --type <html|xml>         If not provided autodetects from file extension
     -c, --charset <charset>       Charset for reading files, UTF-8 by default
     -r, --recursive               Process files inside subdirectories
     -m, --mask <filemask;...>     Filter input files by mask
     -o, --output <path>           Filename or directory for compression results.
                                   If none provided outputs result to <stdout>
     -s, --output-suffix <suffix>  Saves the compression result under the input name
                                   with the suffix before the file extension.
     -a, --analyze                 Tries different settings and displays report.
                                   All settings except --js-compressor are ignored

    XML Compression Options:
     --preserve-comments           Preserve comments
     --preserve-intertag-spaces    Preserve intertag spaces

    HTML Compression Options:
     --preserve-comments           Preserve comments
     --preserve-multi-spaces       Preserve multiple spaces
     --preserve-line-breaks        Preserve line breaks
     --remove-intertag-spaces      Remove intertag spaces
     --remove-quotes               Remove unneeded quotes
     --simple-doctype              Change doctype to <!DOCTYPE html>
     --remove-style-attr           Remove TYPE attribute from STYLE tags
     --remove-link-attr            Remove TYPE attribute from LINK tags
     --remove-script-attr          Remove TYPE and LANGUAGE from SCRIPT tags
     --remove-form-attr            Remove METHOD="GET" from FORM tags
     --remove-input-attr           Remove TYPE="TEXT" from INPUT tags
     --simple-bool-attr            Remove values from boolean tag attributes
     --remove-js-protocol          Remove "javascript:" from inline event handlers
     --remove-http-protocol        Remove "http:" from tag attributes
     --remove-https-protocol       Remove "https:" from tag attributes
     --remove-surrounding-spaces <min|max|all|custom_list>
                                   Predefined or custom comma separated list of tags
     --compress-js                 Enable inline JavaScript compression
     --compress-js-skip-preserve   Disable the JavaScript compression for preserved
                                   user blocks
     --compress-css                Enable inline CSS compression using YUICompressor
     --compress-css-skip-preserve  Disable the CSS compression for preserved
                                   user blocks
     --js-compressor <yui|closure> Switch inline JavaScript compressor between
                                   YUICompressor (default) and Closure Compiler

    JavaScript Compression Options for YUI Compressor:
     --nomunge                     Minify only, do not obfuscate
     --preserve-semi               Preserve all semicolons
     --disable-optimizations       Disable all micro optimizations
     --line-break <column num>     Insert a line break after the specified column

    JavaScript Compression Options for Google Closure Compiler:
     --closure-opt-level <simple|advanced|whitespace>
                                   Sets level of optimization (simple by default)
     --closure-externs <file>      Sets custom externs file, repeat for each file
     --closure-custom-externs-only Disable default built-in externs

    CSS Compression Options for YUI Compressor:
     --line-break <column num>     Insert a line break after the specified column

    Custom Block Preservation Options:
     --preserve-php                Preserve <?php ... ?>, <?php ... EOF and
                                   <?= ... ?>
     --preserve-server-script      Preserve <% ... %> tags
     --preserve-ssi                Preserve <!--# ... --> tags
     -p, --preserve <path>         Read regular expressions that define
                                   custom preservation rules from a file

    Please note that if you enable CSS or JavaScript compression, additional
    YUI Compressor or Google Closure Compiler jar files must be present
    in the same directory as this jar.


For detailed explanations see the official [**HTMLCompressor docs**](https://code.google.com/p/htmlcompressor/).

## Version History
- [**1.5.3**](https://github.com/Wohlie/HtmlCompressor/releases/tag/v1.5.3) (2012-06-03): Newest release of HtmlCompressor

License
-------
    Copyright 2009 - 2012   Sergiy Kovalchuk the original author or other authors.
    Copyright 2013          Erik Wohllebe <erik.wohllebe@googlemail.com>.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.