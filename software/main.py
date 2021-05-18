from tkinter import *
from tkinter import ttk
import tkinter
from PIL import Image, ImageTk
from time import strftime
from datetime import datetime
from student_file import Student
from developer import Developer
from attendance import Attendance


class Face_Recognition_System:
    def __init__(self, root, id):
        self.root = root
        self.root.geometry("1500x900+0+0")
        self.root.title("Face Recognition System")
        self.root.resizable(False, False)
        self.id = id

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
        img1 = Image.open(r"./Images/face-id.jpg")
        img1 = img1.resize((200,200), Image.ANTIALIAS)
        self.photoimg1 = ImageTk.PhotoImage(img1)

        f_lbl = Label(self.root, image=self.photoimg1)
        f_lbl.place(x=0, y=0, width=200, height=200)

        # second image
        img2 = Image.open(r"./Images/face-id.jpg")
        img2 = img2.resize((200, 200), Image.ANTIALIAS)
        self.photoimg2 = ImageTk.PhotoImage(img2)

        f_lbl = Label(self.root, image=self.photoimg2)
        f_lbl.place(x=1300, y=0, width=200, height=200)

        # Frame
        frame = Frame(bd=2, relief=RIDGE)
        frame.place(x=0, y=200, width=1500, height=700)

        # Student button
        img3 = Image.open(r"./Images/button.jpg")
        img3 = img3.resize((200, 200), Image.ANTIALIAS)
        self.photoimg3 = ImageTk.PhotoImage(img3)

        b1 = Button(frame, image=self.photoimg3, cursor="hand2", command=self.student_details)
        b1.place(x=200, y=150, width=200, height=200)
        b1_1 = Button(frame, text="Student Details", cursor="hand2", font=("times new roman", 15, "bold"), bg="darkblue", fg="white", command=self.student_details)
        b1_1.place(x=200, y=350, width=200, height=40)

        # # Detect face button
        # img4 = Image.open(r"C:\Users\LG\PycharmProjects\SmartAttendance\Images\button.jpg")
        # img4 = img4.resize((200, 200), Image.ANTIALIAS)
        # self.photoimg4 = ImageTk.PhotoImage(img4)
        #
        # b1 = Button(frame, image=self.photoimg4, cursor="hand2")
        # b1.place(x=500, y=50, width=200, height=200)
        # b1_1 = Button(frame, text="Face Detector", cursor="hand2", font=("times new roman", 15, "bold"), bg="darkblue", fg="white")
        # b1_1.place(x=500, y=250, width=200, height=40)

        # Attendance face button
        img5 = Image.open(r"./Images/button.jpg")
        img5 = img5.resize((200, 200), Image.ANTIALIAS)
        self.photoimg5 = ImageTk.PhotoImage(img5)

        b1 = Button(frame, image=self.photoimg5, cursor="hand2", command=self.attendance_details)
        b1.place(x=500, y=150, width=200, height=200)
        b1_1 = Button(frame, text="Attendance", cursor="hand2", font=("times new roman", 15, "bold"), bg="darkblue", fg="white", command=self.attendance_details)
        b1_1.place(x=500, y=350, width=200, height=40)

        # # Help button
        # img6 = Image.open(r"C:\Users\LG\PycharmProjects\SmartAttendance\Images\button.jpg")
        # img6 = img6.resize((200, 200), Image.ANTIALIAS)
        # self.photoimg6 = ImageTk.PhotoImage(img6)
        #
        # b1 = Button(frame, image=self.photoimg6, cursor="hand2")
        # b1.place(x=1100, y=50, width=200, height=200)
        # b1_1 = Button(frame, text="Help", cursor="hand2", font=("times new roman", 15, "bold"), bg="darkblue", fg="white")
        # b1_1.place(x=1100, y=250, width=200, height=40)

        # # Train button
        # img7 = Image.open(r"C:\Users\LG\PycharmProjects\SmartAttendance\Images\button.jpg")
        # img7 = img7.resize((200, 200), Image.ANTIALIAS)
        # self.photoimg7 = ImageTk.PhotoImage(img7)
        #
        # b1 = Button(frame, image=self.photoimg7, cursor="hand2")
        # b1.place(x=200, y=350, width=200, height=200)
        # b1_1 = Button(frame, text="Train Data", cursor="hand2", font=("times new roman", 15, "bold"), bg="darkblue", fg="white")
        # b1_1.place(x=200, y=550, width=200, height=40)

        # # Photo face button
        # img8 = Image.open(r"C:\Users\LG\PycharmProjects\SmartAttendance\Images\button.jpg")
        # img8 = img8.resize((200, 200), Image.ANTIALIAS)
        # self.photoimg8 = ImageTk.PhotoImage(img8)
        #
        # b1 = Button(frame, image=self.photoimg8, cursor="hand2")
        # b1.place(x=500, y=350, width=200, height=200)
        # b1_1 = Button(frame, text="Photos", cursor="hand2", font=("times new roman", 15, "bold"), bg="darkblue",
        #               fg="white")
        # b1_1.place(x=500, y=550, width=200, height=40)

        # Developer button
        img9 = Image.open(r"./Images/button.jpg")
        img9 = img9.resize((200, 200), Image.ANTIALIAS)
        self.photoimg9 = ImageTk.PhotoImage(img9)

        b1 = Button(frame, image=self.photoimg9, cursor="hand2", command=self.develop_details)
        b1.place(x=800, y=150, width=200, height=200)
        b1_1 = Button(frame, text="Developer", cursor="hand2", font=("times new roman", 15, "bold"), bg="darkblue",
                      fg="white", command=self.develop_details)
        b1_1.place(x=800, y=350, width=200, height=40)

        # Exit button
        img10 = Image.open(r"./Images/exit.jpg")
        img10 = img10.resize((200, 200), Image.ANTIALIAS)
        self.photoimg10 = ImageTk.PhotoImage(img10)

        b1 = Button(frame, image=self.photoimg10, cursor="hand2", command=self.exit)
        b1.place(x=1100, y=150, width=200, height=200)
        b1_1 = Button(frame, text="Exit", cursor="hand2", font=("times new roman", 15, "bold"), bg="darkblue",
                      fg="white", command=self.exit)
        b1_1.place(x=1100, y=350, width=200, height=40)

    # Function buttons
    def student_details(self):
        #self.new_window = Toplevel(self.root)
        self.app = Student(self.root, self.id)

    def develop_details(self):
        self.app = Developer(self.root, self.id)

    def attendance_details(self):
        self.app = Attendance(self.root, self.id)

    def exit(self):
        self.exit = tkinter.messagebox.askyesno("Face Rcognition", "Exit this project")
        if self.exit>0:
            self.root.destroy()
        else:
            return




if __name__ == "__main__":
    root = Tk()
    obj = Face_Recognition_System(root, -1)
    root.mainloop()
