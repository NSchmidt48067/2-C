list = []
old = 0
min = 0
max = 1
with open("numbers.txt", "r") as file:
    for line in file:
        line = line.strip()
        list.append(line)

for num in list:
    print(num)
    if int(old) < int(num):
        list.append(num)
    elif int(num) <= int(min):
        list.insert(0,num)
        min = num
    elif int(line) >= int(max):
        list.insert(len(list)-1, num)
        max = num
    else:
        list.insert(1, num)


for num in list:
    print(num)