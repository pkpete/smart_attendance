from tkinter import *
from tkinter import ttk
import tkinter
from PIL import Image, ImageTk
from time import strftime
from tkinter import messagebox
from datetime import datetime
from main import Face_Recognition_System

class Login_Window:
    def __init__(self, root):
        self.root = root
        self.root.geometry("1500x900+0+0")
        self.root.title("Login System")
        self.root.resizable(False, False)

        def time():
            string_time = strftime('%H:%M:%S %p')
            string_date = strftime('%Y/%m/%d')
            lbl.config(text=string_date +"   "+ string_time)
            lbl.after(1000, time)

        # title
        title_lbl = Label(text="I'm Here", font=("times new roman", 40, "bold"), fg="blue")
        title_lbl.place(x=200, y=0, width=1100, height=100)
        title_lbl2 = Label(text="FACE RECOGNITION ATTENDANCE SYSTEM", font=("times new roman", 20, "bold"), fg="blue")
        title_lbl2.place(x=200, y=90, width=1100, height=100)

        # time
        lbl = Label(font=("times new roman", 17), fg="blue")
        lbl.place(x=200, y=165, width=1100, height=30)
        time()

        # first image
        img1 = Image.open(r"C:\Users\LG\PycharmProjects\SmartAttendance\smart_attendance\software\Images\face-id.jpg")
        img1 = img1.resize((200,200), Image.ANTIALIAS)
        self.photoimg1 = ImageTk.PhotoImage(img1)

        f_lbl = Label(self.root, image=self.photoimg1)
        f_lbl.place(x=0, y=0, width=200, height=200)

        # second image
        img2 = Image.open(r"C:\Users\LG\PycharmProjects\SmartAttendance\smart_attendance\software\Images\face-id.jpg")
        img2 = img2.resize((200, 200), Image.ANTIALIAS)
        self.photoimg2 = ImageTk.PhotoImage(img2)

        f_lbl = Label(self.root, image=self.photoimg2)
        f_lbl.place(x=1300, y=0, width=200, height=200)

        # Frame
        frame = Frame(bd=2, relief=RIDGE)
        frame.place(x=0, y=200, width=1500, height=700)

        login_frame = Frame(frame, bg="black")
        login_frame.place(x=580, y=100, width=360, height=480)

        img3 = Image.open(r"C:\Users\LG\PycharmProjects\SmartAttendance\smart_attendance\software\Images\login.png")
        img3 = img3.resize((100,100), Image.ANTIALIAS)
        self.photoimg3 = ImageTk.PhotoImage(img3)

        f_lbl = Label(login_frame, image=self.photoimg3, background="black")
        f_lbl.place(x=135, y=10, width=100, height=100)

        get_str = Label(login_frame, text="Login", font=("times new roman", 20, "bold"), fg="white", bg="black")
        get_str.place(x=145, y=120)

        username = Label(login_frame, text="Username", font=("times new roman", 15, "bold"), fg="white", bg="black")
        username.place(x=30, y=195)

        self.txtuser = ttk.Entry(login_frame, font=("times new roman", 15, "bold"))
        self.txtuser.place(x=130, y=195, width=200)

        password = Label(login_frame, text="Password", font=("times new roman", 15, "bold"), fg="white", bg="black")
        password.place(x=30, y=255)

        self.txtpassword = ttk.Entry(login_frame, font=("times new roman", 15, "bold"), show="*")
        self.txtpassword.place(x=130, y=255, width=200)

        # Login Button
        login_btn = Button(login_frame, text="Login", font=("times new roman", 15, "bold"), bd=3, relief=RIDGE,
                           fg="white", bg="red", activeforeground="white", activebackground="red", command=self.login)
        login_btn.place(x=120, y=340, width=120, height=35)

    def login(self):
        if self.txtuser.get() == "" or self.txtpassword.get() == "":
            messagebox.showerror("Error", "All field required")
        else:
            print(self.txtpassword.get())
            # 여기서 아이디, 비번 보내고 확인 결과값 받아오기
            #self.new_window = Toplevel(self.root)
            self.app = Face_Recognition_System(self.root)




if __name__ == "__main__":
    root = Tk()
    obj = Login_Window(root)
    root.mainloop()
