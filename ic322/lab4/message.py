import requests
from bs4 import BeautifulSoup
#!/usr/bin/env python3

#Creaates the headers in a dictionary
def createH():
    file = open("data.txt", "r")
    data = file.read()#Read in entire file
    lines = data.strip().split("\n")#split by line
    d = {}
    for line in lines:
        x = line.strip().split(":", 1)
        if (x[0] == "Cookie:"):
            cookies = x[1].strip().split(";")
            a = cookies.strip().split("=")
            d.update(a)
        d[x[0].strip()] = x[1].strip()
    return d



url = "https://mids.usna.edu/ITSD/mids/drgwq010$.startup"

header = createH()

payload = {
    'P_ALPHA': input("Enter an alpha: "),
    'P_LAST_NAME': '',
    'P_MICO_CO_NBR':'', 
    'P_SECOF_COOF_SEBLDA_AC_YR':'2025',
    'P_SECOF_COOF_SEBLDA_SEM':'FALL',
    'P_SECOF_COOF_SEBLDA_BLK_NBR':'1',
    'P_MAJOR_CODE':'',
    'P_NOMI_FORMATTED_NAME':'',
    'Z_ACTION=QUERY&Z_CHK':'0'
    }

response = requests.post(url, data=payload,headers=header)

print(f'Status Code: {response.status_code}')

parsed = BeautifulSoup(response.content, "html.parser")

file = open('file.html', 'w')
file.write(str(parsed))

print(parsed)