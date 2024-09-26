import sys             # "sys" module means "system"
import re

# Loop over each line of input
for line in sys.stdin: # loops over each line of input
    mres = re.search(r'<b>(.*)</b>',line) # use r'...' strings for regexs
    print(mres.group()[3:len(mres.group()) - 4])