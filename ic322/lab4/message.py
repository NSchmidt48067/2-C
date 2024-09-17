import requests
from bs4 import BeautifulSoup
#!/usr/bin/env python3

#Creaates the headers in a dictionary
def createH(cookiejar):
    file = open("data.txt", "r")
    data = file.read()#Read in entire file
    lines = data.strip().split("\n")#split by line
    d = {}
    for line in lines:
        key, value = line.split(':', 1)
        if (key == "Cookie"):
            d.update(cookiejar)
        else:
            d[key.strip()] = value.strip()
    return d

usr = "m265646"
pswrd = "MarryIsabelle12@$"

url = "https://login.usna.edu/oam/server/obrareq.cgi?encquery%3DPXq8TVWSS0EtxtXCaw%2Bo2WNCh7r6uwn726eOcS%2Bdi6UFI7UGU0fMfHYk8%2FaetBWmD1aM3zH0bkj1lDaSTfhuPRhu7Bb6w4ihGONOe%2BL1YgGseh90pyErekmsxjEkOMCakqx3BfgHT8SX6gu3WJtIaMuk2ejMQrQCSFvQA%2BnrRs9B9pmyWiCihiQSMf1g%2FV7wRGwpjBZR52O6RCMTfVNrcOXEBhtQSFTqE2Fm4sw9mgZhYM3zTzde7a1YOdrJEBD3jVtXixiBU4bJK5z7WfC6yw%3D%3D%20agentid%3DUSNA_OHS12c_WebGateAgent%20ver%3D1%20crmethod%3D2"

data = {'username' : usr, 'password' : pswrd}

curSession = requests.Session()

curSession.post(url, data = data)
cookies = curSession.cookies.get_dict()

line = ""

for key, value in cookies.items():
    line = line + key + "=" + value + "; "

cookiejar = {"Cookie" : line}

url = "https://mids.usna.edu/ITSD/mids/drgwq010$mids.actionquery"

d = createH(cookiejar)

header = d

payload = {
    'P_ALPHA': input("Enter an Alpha: "),
    'P_LAST_NAME': '',
    'P_MICO_CO_NBR':'', 
    'P_SECOF_COOF_SEBLDA_AC_YR':'2025',
    'P_SECOF_COOF_SEBLDA_SEM':'FALL',
    'P_SECOF_COOF_SEBLDA_BLK_NBR':'1',
    'P_MAJOR_CODE':'',
    'P_NOMI_FORMATTED_NAME':'',
    'Z_ACTION': 'QUERY',
    'Z_CHK':'0'
    }

response = requests.post(url, data=payload,headers=header)

print(f'Status Code: {response.status_code}')

parsed = BeautifulSoup(response.content, "html.parser")

file = open('file.html', 'w')
file.write(str(parsed.prettify()))
file.close()

line = parsed.find_all("td")
for index, stuff in enumerate(line):
    print(stuff.text)
    if (index == 50 ):
        break