import sys
import firebase_admin
from firebase_admin import credentials
from firebase_admin import db
from firebase_admin import auth


def show_users():
    ref = db.reference('Users')
    dict = ref.get()
    uidVec = []
    for uid in dict.keys():
        uidVec.append(uid)
    index = 0
    for user in dict.values():
        print(str(index+1)+" User UID: "+str(uidVec[index])+"\n")
        print("     "+str(user)+"\n")
        index += 1

def add_user():
    admin = bool((input("Is this user Admin?_ (true=0 else just enter)")))
    userName = (input("Enter first name_ "))
    password1 = (input("Enter password_ "))
    email = (input("Enter valide email_ " ))
    userAuth = auth.create_user(email=email, password=password1)
    ref = db.reference('Users').child(userAuth.uid)
    ref.set({
        'admin': admin,
        'password': password1,
        'userGe': None,
        'userId' : userAuth.uid,
        'userName': userName
    })
    print ("User has been added")

def delete_user_by_uid():
    show_users()
    ref = db.reference('Users')
    dict = ref.get()
    if dict != None :
            choose = (input("Enter id to delete: "))
            for uid in dict.keys():
                if uid == choose:
                    auth.delete_user(choose)
                    print ("User deleted")
                    ref.child(uid).delete()
                    return
    else:
        print ("There are no users")


def menu():
    cred = credentials.Certificate('./serviceAccountKey.json')
    firebase_admin.initialize_app(cred, {'databaseURL': 'https://libvidd.firebaseio.com'})
    print("Please choose what you want to do:")
    while(True):
        print("\n-1- show all users \n" + "-2- add user \n" + "-3- delete user \n" + "-4- exit \n")
        choose = int(input("choice is: "))
        if (choose == 1):
            show_users()
        elif (choose == 2):
            add_user()
        elif (choose == 3):
            delete_user_by_uid()
        elif (choose == 4):
            print("bye bye!")
            sys.exit()
        else:
            print("not a valide choice")

menu()