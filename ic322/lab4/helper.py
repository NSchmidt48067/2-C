def createH():
    file = open("data.txt", "r")
    data = file.read()#Read in entire file
    d = dict(x.split(":") for x in data.split("\n"))#Split file into array of strings separated by new lines
    #Then split up said strings by colons into a dictionary
    print(d)
    return d