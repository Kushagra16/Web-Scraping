Problem Statement

Develop an application that extracts the set of features (described below) from an HTML
document. Include any test cases used to verify the correctness of your application.

Input

The application takes two parameters as arguments: URL and output filename. The first
argument is the URL to crawl and the second argument is the filename where the extracted
features are written to. If the URL is missing the transport protocol, assume it is http://; assume
transport protocols other than http:// or https:// are not a valid use case.
e.g., app pitchbook.com/about-pitchbook output.txt

Output

The output is a file that is divided into three sections and has the following format:

[links]

Under the [links] section, provide a list of all links embedded within the HTML page, one
per line

[HTML]

Under the [HTML] section, extract the set of raw HTML tags contained in the document.

Tags should be output in the order in which they appear. Make sure to:
a. Remove all whitespace between HTML tags
b. Remove any content outside any HTML tags
c. Remove all parameters within the HTML tags.

[sequences]

Finally, under the [sequences] section, output all sequences of two or more words that
have the first letter in each word is capitalized.
a. For our purposes, a word is defined as a sequence of characters of length
greater than two separated by whitespace.
b. Do not include any HTML tags, text within HTML tags, and any punctuation.
