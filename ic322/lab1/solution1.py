list = []
old = 0
with open("numbers.txt", "r") as file:
    for line in file:
        if not list:
            list.append(line)
        else:
            if int(old) < int(line):
                list.append(line)
            else:
                list.insert(0, line)
        old = line


for num in list:
    print(num)