from account import Account
class User:
    def __init__(self, name, id):
        self.name = name
        self.cccdNum = id
    
class Customer(User):
    def __init__(self, name, id):
        super().__init__(name, id)
        self.accountList = []

    def isVip(self):
        for account in self.accountList:
            if account.isVipAccount():
                return True
        return False
    
    def existedAccount(self, accont):
        if accont in self.accountList:
            return True
        return False
    
    def addAccount(self, newAccount):
        for account in self.accountList:
            if account.accountNum == newAccount.accountNum:
                print(f"tai khoan {account.accountNum} da ton tai")
                return self.accountList
        self.accountList.append(newAccount)
        print(f"da them tai khoan {newAccount.accountNum} thanh cong")
        return self.accountList
    
    def delAccount(self, delAccount):
        for account in self.accountList:
            if account.accountNum == delAccount.accountNum:
                self.accountList.remove(delAccount)
                print(f"da xoa tai khoan {delAccount.accountNum}")
                return self.accountList
        print(f"tai khoan {delAccount.accountNum} khong ton tai")
        return self.accountList
        
    
    def totalBalance(self):
        total = sum(account.balance for account in self.accountList)  
        return total
    
    def showCutomerInfor(self):
        totalBalance = ""
        account_type = "Premium" if self.isVip() else "Normal"
        basicInfo = f"{self.cccdNum:<30}|      {self.name:<20}|      {account_type:<10}   |   {self.totalBalance():>26}đ|\n"
        i = 1
        for account in self.accountList:
            basicInfo += f"{i}.    " + account.showAccountInfor() + "\n"
            i += 1
        return basicInfo
    

# a = Account("123456", 1200000000)
# b = Account("123455", 9999999999)
# c = Customer("Bui anh toan", "035202002788")
# c.addAccount(a)
