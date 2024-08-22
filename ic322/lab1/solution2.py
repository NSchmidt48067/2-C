dictionary = {}

with open("words.txt", "r") as file:
    for line in file:
        for word in line.split():
            word = word.lower()
            if word in dictionary:
                dictionary[word] += 1
            else:
                dictionary.update({word : 1})

sort = sorted(dictionary.items(), key=lambda x:x[1], reverse = True)

for key, val in sort[:5]:
    print(f"{key}: {val}")
