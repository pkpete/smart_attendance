from tkinter import *
from tkinter import messagebox
from tkinter import ttk
from PIL import Image, ImageTk
from time import strftime
from datetime import datetime
import cv2
import mysql.connector


class Developer:
    def __init__(self, root):
        self.root = root
        self.root.geometry("1500x900+0+0")
        self.root.title("Face Recognition System")

        def time():
            string_time = strftime('%H:%M:%S %p')
            string_date = strftime('%Y/%m/%d')
            lbl.config(text=string_date + "   " + string_time)
            lbl.after(1000, time)

        # title
        title_lbl = Label(text="I'm Here", font=("times new roman", 40, "bold"), fg="blue")
        title_lbl.place(x=200, y=0, width=1100, height=100)
        title_lbl2 = Label(text="DEVELOPERS", font=("times new roman", 20, "bold"),
                           fg="blue")
        title_lbl2.place(x=200, y=90, width=1100, height=100)

        # time
        lbl = Label(font=("times new roman", 17), fg="blue")
        lbl.place(x=200, y=165, width=1100, height=30)
        time()

        # Home Button
        img1 = Image.open(r"C:\Users\LG\PycharmProjects\SmartAttendance\smart_attendance\software\Images\home.jpg")
        img1 = img1.resize((200, 200), Image.ANTIALIAS)
        self.photoimg1 = ImageTk.PhotoImage(img1)

        b1 = Button(image=self.photoimg1, cursor="hand2", command=self.home_details)
        b1.place(x=0, y=0, width=200, height=200)

        # second image
        img2 = Image.open(r"C:\Users\LG\PycharmProjects\SmartAttendance\smart_attendance\software\Images\face-id.jpg")
        img2 = img2.resize((200, 200), Image.ANTIALIAS)
        self.photoimg2 = ImageTk.PhotoImage(img2)

        f_lbl = Label(self.root, image=self.photoimg2)
        f_lbl.place(x=1300, y=0, width=200, height=200)

        # Frame
        frame = Frame(bd=2, relief=RIDGE)
        frame.place(x=0, y=200, width=1500, height=700)

        img3 = Image.open(r"C:\Users\LG\PycharmProjects\SmartAttendance\smart_attendance\software\Images\ko.jpg")
        img3 = img3.resize((200, 170), Image.ANTIALIAS)
        self.photoimg3 = ImageTk.PhotoImage(img3)

        f_lbl = Label(frame, image=self.photoimg3)
        f_lbl.place(x=250, y=40, width=200, height=170)

        ko_frame = LabelFrame(frame, bd=2, relief=RIDGE, text="고재욱 KO Jae Uk", font=("times new roman", 12, "bold"))
        ko_frame.place(x=600, y=40, width=760, height=170)

        ko_label = Label(ko_frame, text="자동 출석 관리 시스템에서 SW 개발을 담당하고 있습니다.", font=("times new roman", 12, "bold"))
        ko_label.place(x=10, y=60)

        img4 = Image.open(r"C:\Users\LG\PycharmProjects\SmartAttendance\smart_attendance\software\Images\kim.jpg")
        img4 = img4.resize((200, 170), Image.ANTIALIAS)
        self.photoimg4 = ImageTk.PhotoImage(img4)

        f_lbl = Label(frame, image=self.photoimg4)
        f_lbl.place(x=250, y=250, width=200, height=170)

        kim_frame = LabelFrame(frame, bd=2, relief=RIDGE, text="김민수 KIM Min Su", font=("times new roman", 12, "bold"))
        kim_frame.place(x=600, y=250, width=760, height=170)

        kim_label = Label(kim_frame, text="자동 출석 관리 시스템에서 Backend 개발을 담당하고 있습니다.", font=("times new roman", 12, "bold"))
        kim_label.place(x=10, y=60)

        img5 = Image.open(r"C:\Users\LG\PycharmProjects\SmartAttendance\smart_attendance\software\Images\jung.jpg")
        img5 = img5.resize((200, 170), Image.ANTIALIAS)
        self.photoimg5 = ImageTk.PhotoImage(img5)

        f_lbl = Label(frame, image=self.photoimg5)
        f_lbl.place(x=250, y=450, width=200, height=170)

        jung_frame = LabelFrame(frame, bd=2, relief=RIDGE, text="정도식 JUNG Do 식", font=("times new roman", 12, "bold"))
        jung_frame.place(x=600, y=450, width=760, height=170)

        jung_label = Label(jung_frame, text="자동 출석 관리 시스템에서 Frontend 개발을 담당하고 있습니다.", font=("times new roman", 12, "bold"))
        jung_label.place(x=10, y=60)

    # Function buttons
    def home_details(self):
        from main import Face_Recognition_System
        self.app = Face_Recognition_System(self.root)


if __name__ == "__main__":
    root = Tk()
    obj = Developer(root)
    root.mainloop()