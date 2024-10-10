class Pos:

    def __init__(self,row,col):
        self.row = row
        self.col = col

    def len(self):
        return self.row + self.col

    def __str__(self):
        return Pos.disp(self.row,self.col)
    
    sep = ":"

    def disp(v1,v2):
        return f"({v1}{Pos.sep}{v2})";

    def __repr__(self):
        return f"({self.row},{self.col})"
    


    def step(self, dir):
        dirmap = {
            "N" : (-1, 0),
            "E" : (0, 1),
            "S" : (1, 0),
            "W" : (0, -1)
        }
        if (dir in dirmap):
            trow, tcol = dirmap[dir]
            self.row += trow
            self.col += tcol

    def  __eq__(self,other):
        if (self.row == other.row and self.col == other.col):
            return True
        else:
            return False
        

class LabPos(Pos): # means LabPos extends Pos

    def __init__(self,row,col,lab):
        Pos.__init__(self,row,col)  # calls the constructor from Pos 
        self.lab = lab
    
    def __str__(self):  # override the __str__(self) from class Pos 
        return Pos.__str__(self) + ":" + self.lab
    
    def getLabel(self):
        return str(self.lab)

    @staticmethod
    def labMerge(T):
        for pos in T:
            print(pos.getLabel()) 
    
    @staticmethod
    def dist(T1, T2):
        x = abs(T1.row - T2.row)
        y = abs(T1.col - T2.col)
        return x+y