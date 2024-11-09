import random

def docFile():
    try:
        bangMaTinh = {}
        with open('C:/funixjava/Asm1/src/main/resources/Province.csv', 'r', encoding='utf-8') as file:
            for line in file:
                vanBan = line.strip()
                if ',' in vanBan:
                    tenTinh, maTinh = vanBan.split(',',1)
                    bangMaTinh[maTinh.strip()] = tenTinh.strip()
                # for key, value in bangMaTinh.items():
                #     print(f"Key: {key}, Value: {value}")
    except FileNotFoundError:
        print("File không tồn tại.")
    except OSError as e:
        print(f"Đã xảy ra lỗi khi mở file: {e}")
    return bangMaTinh

def namSinhGioiTinh(maNamsinh, maGioiTinh):
    gioiTinh = None
    namSinh = None
    if int(maNamsinh) % 2 == 0:
        gioiTinh = "Nam"
    else:
        gioiTinh = "Nu"
    namSinh = 1900 + (int(maGioiTinh) // 2) * 100 + int(maNamsinh)
    print(f'Gioi tinh {gioiTinh}, Nam sinh {namSinh}')

def meNu():
    thoat = False
    global canCuocCD
    while not thoat:
        print("""
        ------------+-------------------------------------+----------      
        |    1. nhap ma can cuoc                                    |
        |    0. thoat                                               |
        ------------+-------------------------------------+----------       
            """)
        luachon = input("nhap lua chon: ")
        while luachon.isdigit() == False or (luachon != '1' and luachon != '0'):
            luachon = input("moi nhap lai: ")
        if luachon == "1":
            maxacThuc = random.randint(100, 999)
            maNhap = input("ma xac thuc la : " + str(maxacThuc) + "\n" + "nhap ma xac thuc: ")
            while int(maNhap) != maxacThuc:
                maNhap = input("nhap lai ma xac thuc hoac nhap 'No' de thoat: ")
                if maNhap == 'No':
                    break
                    thoat = True
            if thoat == True:
                break
            canCuocCD = input("nhap can cuoc cong dan: ")
            while canCuocCD.isdigit() == False or len(canCuocCD) != 12:
                canCuocCD = input("nhap lai can cuoc: ")
            meNu2()
        else:
            break

def meNu2():
    bangMaTinh = docFile()
    maTinh = canCuocCD[0:3]
    maGTinh = canCuocCD[3:4]
    maNSinh = canCuocCD[4:6]
    soNgauNhien = canCuocCD[6:]
    while True:
        print("""
            ----------+-----------------------------------------------------+----------
            |   1. xem noi sinh                                                       |
            |   2. xem nam sinh va gioi tinh                                          |
            |   3. xem so ngau nhien                                                  |
            |   0. thoat                                                              |
            ----------+-----------------------------------------------------+----------
            """)
        luachon = input("nhap lua chon: ")
        while luachon.isdigit() == False or (int(luachon) < 0 or int(luachon) > 3):
            luachon = input("Moi nhap lai: ")
        match luachon:
            case '0':
                break
            case '1':
                print(f'Noi sinh: {bangMaTinh[maTinh]}')
            case '2':
                namSinhGioiTinh(maNSinh, maGTinh)
            case '3':
                print("so ngau nhien la: " + soNgauNhien)


if __name__ == "__main__":
    meNu()

          