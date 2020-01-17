# Binary-Translator
This program can take three kinds of input: binary values, raw decimal values, and unicode values. It detects and converts one of these things into the other two accordingly, for your convenience. However, <b>keep in mind that any input containing only 1s and 0s will be assumed to be in binary unless you specify otherwise</b>. You can override this automatic detection by using the following override commands:

<p>#decimal# input - forces the translator to interpret input as being in the decimal format</p>
<p>#binary# input - forces the translator to interpret input as being in the binary format</p>
<p>#signed# input - forces the translator to interpret input as being in the two's complement signed binary format (if put in negative decimal input, the binary output will automatically be in signed two's complement for that particular number)</p>
<p>#unicode# input - forces the translator to interpret input as being in the unicode format</p>
<p>#exit# - force exits the program</p>

<p>This was one of my first coding projects that I have decided to continue maintaining as I gain more knowledge and experience. As such, my style and coding strategies have changed, but I try to keep things relatively consistent here.</p>
