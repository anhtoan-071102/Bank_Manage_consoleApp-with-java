class Account:
    def __init__(self, accountNum, balance):
        self.accountNum = accountNum
        self.balance = balance

    def isVipAccount(self):
        if self.balance >= 10000000:
            return True
        return False
    
    def showAccountInfor(self):
        return f"{self.accountNum:<24}                           |{' ':<19}{self.balance:>30}đ|"
    

