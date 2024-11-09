from user import Customer
from account import Account
class Bank:
    def __init__(self, name, id):
        self.name = name
        self.id = id
        self.customerList = []

    def customerExist(self, checkCustomer):
        if checkCustomer in self.customerList:
            return True
        return False
    
    def addCustomer(self, newCustomer):
        if self.customerExist(newCustomer):
            print(f"khach hang {newCustomer.cccdNum} da ton tai")
            return
        self.customerList.append(newCustomer)
        print(f"them khach hang {newCustomer.cccdNum} thanh cong")

    def delCustomer(self, delCustomer):
        if self.customerExist(delCustomer):
            self.customerList.remove(delCustomer)
            print(f"da xoa khach hang {delCustomer.cccdNum} thanh cong")
            return
        print(f"khong ton tai khach hang {delCustomer.cccdNum}")

    def addAccountForCustomer(self, newAccount, customerNeed):
        if not self.customerExist(customerNeed):
            print(f"khong ton tai khach hang {customerNeed.cccdNum}")
            return
        customerNeed.addAccount(newAccount)

    def showBankInfor(self):
        bankInfor = f"         {self.name:<25}                       |                      {self.id:<25}   |"
        for customer in self.customerList:
            bankInfor += "\n" + customer.showCutomerInfor()
        return bankInfor



nganHnag = Bank("bIDV", 123)
c = Customer("Bui ANh Tona", "035202002788")
c2 = Customer("Tran Van B", "123456789101")
a = Account("123456", 120394932874)
a2 = Account("123455", 123837492384)
nganHnag.addCustomer(c)
nganHnag.addCustomer(c2)
nganHnag.addAccountForCustomer(a, c)
nganHnag.addAccountForCustomer(a2, c2)
print(nganHnag.showBankInfor())

            
