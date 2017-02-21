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

...

[HTML]

...

[sequences]

...

1. Under the [links] section, provide a list of all links embedded within the HTML page, one
per line.

2. Under the [HTML] section, extract the set of raw HTML tags contained in the document.

Tags should be output in the order in which they appear. Make sure to:
a. Remove all whitespace between HTML tags
b. Remove any content outside any HTML tags
c. Remove all parameters within the HTML tags.

For example, the sample HTML snippet:

<html>
<head>
<title>About PitchBook | Private Financial Data, Technology, and Service</title>
<meta name="description" content="Learn more about PitchBook, our company history, our
leadership team, career opportunities and how to partner with us.">
<meta property="og:title" content="Learn more about PitchBook, our company history,
our leadership team, career opportunities and how to partner with us."/>
<meta property="og:type" content="website"/>
<script type="application/ld+json">
</script>
<link rel="shortcut icon" href="/favicon.ico?uq=Ba6s4HMR"/>
<link href="/css/reset.css?uq=Ba6s4HMR" rel="stylesheet" type="text/css"/>
</head>
<body>
<div data-mega-menu class="mega-menu__container">
<div class="login-part">
<div class="container clearfix main-horizontal-offset-L">
<div data-close-cookie-policy class="cookie-policy">
<span class="close-cookie-policy"></span>
<p data-cookie-policy-text></p>
</div>
</body>
</html>

would be transformed into:

<html><head><title></title><meta><meta/><meta/><script></script><link/><link/></head>
<body><div><div><div><div><span></span><p></p></div></body></html>

3. Finally, under the [sequences] section, output all sequences of two or more words that
have the first letter in each word is capitalized.
a. For our purposes, a word is defined as a sequence of characters of length
greater than two separated by whitespace.
b. Do not include any HTML tags, text within HTML tags, and any punctuation. For
example, the following HTML snippet:

<html>
<body>
<table>
<tr class=tb1><td>John Gabbert is the CEO of the company</td></tr>
<tr class=tb1><td>Rod Diefendorf is the Chief Operating Officer at Pitchbook</td></tr>
<tr><td>Fabrice Forget serves as the Chief Product Officer</td></tr>
<tr><td>The VP of Market Development and Analysis is Adley Bowden</td></tr>
</table>
</body>
</html>

would generate the following output:

John Gabbert
Rod Diefendorf
Chief Operating Officer
Fabrice Forget
Chief Product Officer
The VP
Market Development
Adley Bowden
