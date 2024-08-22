list = []
old = 0
with open("numbers.txt", "r") as file:
    for line in file:
        line = line.strip()
        if not list:
            list.append(line)
        else:
            if (old) < (line):
                list.append(line)
            else:
                list.insert(0, line)
        old = line


for num in list:
    print(num)