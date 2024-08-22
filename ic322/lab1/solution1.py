listy = []

with open("numbers.txt", "r") as file:
    for line in file:
        line = line.strip()
        listy.append(float(line))

listy.sort()

for num in listy:
    print(num)