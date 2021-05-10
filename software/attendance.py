from tkinter import *
from tkinter import ttk
from tkinter import messagebox
from PIL import Image, ImageTk, ImageFont, ImageDraw
from time import strftime
from datetime import datetime
import os
import numpy as np
import cv2
import mysql.connector
import csv


class Attendance:
    def __init__(self, root):
        self.root = root
        self.root.geometry("1500x900+0+0")
        self.root.title("Face Recognition System")
        self.root.resizable(False, False)

        def time():
            string_time = strftime('%H:%M:%S %p')
            string_date = strftime('%Y/%m/%d')
            lbl.config(text=string_date + "   " + string_time)
            lbl.after(1000, time)

        # ==========variables==========
        self.var_course = StringVar()
        self.var_attendance_course = StringVar()

        # title
        title_lbl = Label(text="I'm Here", font=(
            "times new roman", 40, "bold"), fg="blue")
        title_lbl.place(x=200, y=0, width=1100, height=100)
        title_lbl2 = Label(text="Attendance", font=(
            "times new roman", 20, "bold"), fg="blue")
        title_lbl2.place(x=200, y=90, width=1100, height=100)

        lbl = Label(font=("times new roman", 17), fg="blue")
        lbl.place(x=200, y=165, width=1100, height=30)
        time()

        # Home Button
        img1 = Image.open(
            r"C:\Users\LG\PycharmProjects\SmartAttendance\smart_attendance\software\Images\home.jpg")
        img1 = img1.resize((200, 200), Image.ANTIALIAS)
        self.photoimg1 = ImageTk.PhotoImage(img1)

        b1 = Button(image=self.photoimg1, cursor="hand2",
                    command=self.home_details)
        b1.place(x=0, y=0, width=200, height=200)

        # second image
        img2 = Image.open(
            r"C:\Users\LG\PycharmProjects\SmartAttendance\smart_attendance\software\Images\face-id.jpg")
        img2 = img2.resize((200, 200), Image.ANTIALIAS)
        self.photoimg2 = ImageTk.PhotoImage(img2)
        f_lbl = Label(self.root, image=self.photoimg2)
        f_lbl.place(x=1300, y=0, width=200, height=200)

        # Frame
        frame = Frame(bd=2, relief=RIDGE)
        frame.place(x=0, y=200, width=1500, height=700)

        # Choose Course frame
        choose_course_frame = LabelFrame(
            bd=2, relief=RIDGE, text="Choose Course", font=("times new roman", 12, "bold"))
        choose_course_frame.place(x=20, y=250, width=500, height=200)

        # Choose Course
        course_label = Label(choose_course_frame, text="Course",
                             font=("times new roman", 12, "bold"))
        course_label.place(x=30, y=70)

        course_combo = ttk.Combobox(choose_course_frame, font=("times new roman", 12, "bold"), state="readonly",
                                    textvariable=self.var_course)
        course_combo["values"] = ("AI", "SWE", "SP")
        course_combo.set("Select Course")
        course_combo.place(x=100, y=70)

        train_btn = Button(choose_course_frame, text="Train Data", font=(
            "times new roman", 12, "bold"), width=15, command=self.train_classifier)
        train_btn.place(x=330, y=65)

        # Attendance Start frame
        attendance_frame = LabelFrame(
            bd=2, relief=RIDGE, text="Attendance Start", font=("times new roman", 12, "bold"))
        attendance_frame.place(x=20, y=500, width=500, height=200)

        # Attendance Start
        course_label = Label(attendance_frame, text="Attendance Start", font=(
            "times new roman", 12, "bold"))
        course_label.grid(row=0, column=0)

        select_course_combo = ttk.Combobox(attendance_frame, font=("times new roman", 12, "bold"), state="readonly",
                                           textvariable=self.var_attendance_course)
        select_course_combo["values"] = ("AI", "SWE", "SP")
        select_course_combo.set("Select Course")
        select_course_combo.grid(row=0, column=1)

        start_btn = Button(attendance_frame, text="Start Attendance", font=(
            "times new roman", 12, "bold"), width=15, command=self.face_recognition)
        start_btn.grid(row=0, column=2)

        # Right Frame
        Right_frame = LabelFrame(bd=2, relief=RIDGE, text="Attendance Chart", font=(
            "times new roman", 12, "bold"))
        Right_frame.place(x=600, y=250, width=850, height=580)

        # Search System
        self.search_by = StringVar()
        self.search_text = StringVar()

        Search_frame = LabelFrame(Right_frame, bd=2, relief=RIDGE, text="Search System",
                                  font=("times new roman", 12, "bold"))
        Search_frame.place(x=0, y=0, width=830, height=70)

        search_label = Label(Search_frame, text="Search By : ",
                             font=("times new roman", 12, "bold"))
        search_label.grid(row=0, column=0, padx=10, pady=10, sticky=W)

        search_combo = ttk.Combobox(Search_frame, textvariable=self.search_by, font=("times new roman", 12, "bold"),
                                    state="readonly", width=10)
        search_combo["values"] = ("Student_id", "Date")
        search_combo.set("Select")
        search_combo.grid(row=0, column=1, padx=2, pady=10, sticky=W)

        search_entry = ttk.Entry(Search_frame, textvariable=self.search_text, width=15,
                                 font=("times new roman", 13, "bold"))
        search_entry.grid(row=0, column=2, padx=10, pady=5, sticky=W)

        search_btn = Button(Search_frame, text="Search", font=("times new roman", 12, "bold"), width=13,
                            command=self.search_data)
        search_btn.grid(row=0, column=3, padx=4)

        show_all_btn = Button(Search_frame, text="Show All", font=("times new roman", 12, "bold"), width=13,
                              command=self.fetch_data)
        show_all_btn.grid(row=0, column=4, padx=4)

        # Attendance Frame
        table_frame = Frame(Right_frame, bd=2, relief=RIDGE)
        table_frame.place(x=0, y=80, width=830, height=470)

        scroll_x = ttk.Scrollbar(table_frame, orient=HORIZONTAL)
        scroll_y = ttk.Scrollbar(table_frame, orient=VERTICAL)

        self.attendance_table = ttk.Treeview(table_frame, column=(
            "ID", "Name", "Korean Name", "Course", "Date", "Time"), xscrollcommand=scroll_x.set,
            yscrollcommand=scroll_y.set)

        scroll_x.pack(side=BOTTOM, fill=X)
        scroll_y.pack(side=RIGHT, fill=Y)
        scroll_x.config(command=self.attendance_table.xview)
        scroll_y.config(command=self.attendance_table.yview)

        self.attendance_table.heading("ID", text="ID")
        self.attendance_table.heading("Name", text="Name")
        self.attendance_table.heading("Korean Name", text="Korean Name")
        self.attendance_table.heading("Course", text="Course")
        self.attendance_table.heading("Date", text="Date")
        self.attendance_table.heading("Time", text="Time")
        self.attendance_table["show"] = "headings"

        self.attendance_table.column("ID", width=100)
        self.attendance_table.column("Name", width=100)
        self.attendance_table.column("Korean Name", width=100)
        self.attendance_table.column("Course", width=100)
        self.attendance_table.column("Date", width=100)
        self.attendance_table.column("Date", width=100)

        self.attendance_table.pack(fill=BOTH, expand=1)
        self.fetch_data()

    def train_classifier(self):
        if self.var_course.get() == "Select Course":
            messagebox.showerror(
                "Error", "Please select course", parent=self.root)
        else:
            data_dir = ("FaceImages/" + self.var_course.get())
            file_path = [data_dir + "/" +
                         file for file in os.listdir(data_dir)]
            faces = []
            ids = []
            for file in file_path:
                path = [file + "/" + img for img in os.listdir(file)]

                for image in path:
                    img = Image.open(image).convert('L')
                    imageNP = np.array(img, 'uint8')
                    id = int(image.split('/')[2][1:])   # B511006에서 B 빼기
                    faces.append(imageNP)
                    ids.append(id)
                    cv2.imshow("Training", imageNP)
                    cv2.waitKey(1) == 13
            ids = np.array(ids)

            clf = cv2.face.LBPHFaceRecognizer_create()
            clf.train(faces, ids)
            clf.write(self.var_course.get() + " classifier.xml")
            cv2.destroyAllWindows()
            messagebox.showinfo("Result", "Training datasets completed!!")

    def mark_attendance(self, name, kor_name, id, course):
        with open("attendance.csv", "r+", newline="\n") as f:
            myDataList = f.readlines()
            name_list = []
            for line in myDataList:
                entry = line.split(",")
                name_list.append(entry[0])
            if (name not in name_list) and (kor_name not in name_list) and (id not in name_list) and (course not in name_list):
                now = datetime.now()
                string_time = now.strftime('%H:%M:%S')
                string_date = now.strftime('%Y/%m/%d')
                f.writelines(
                    f"\n{id}, {kor_name}, {name}, {course}, {string_date}, {string_time}")
                try:
                    conn = mysql.connector.connect(host="localhost", username="root", password="123456",
                                                   database="face_recognizer")
                    my_cursor = conn.cursor()
                    my_cursor.execute("insert into attendance values(%s,%s,%s,%s,%s,%s)", (
                        id,
                        kor_name,
                        name,
                        course,
                        string_date,
                        string_time
                    ))
                    conn.commit()
                    self.fetch_data()
                    conn.close()
                except Exception as es:
                    messagebox.showerror(
                        "Error", f"Due To: {str(es)}", parent=self.root)

    def face_recognition(self):
        def draw_boundray(img, classifier, scaleFactor, minNeighbors, color, text, clf):
            gray_image = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
            features = classifier.detectMultiScale(
                gray_image, scaleFactor, minNeighbors)

            coord = []

            for(x, y, w, h) in features:
                id, predict = clf.predict(gray_image[y:y+h, x:x+w])
                id = "B" + str(id)
                #confidence = int(100*(1-predict/300))

                conn = mysql.connector.connect(host="localhost", username="root", password="123456",
                                               database="face_recognizer")
                my_cursor = conn.cursor(buffered=True)

                my_cursor.execute(
                    "SELECT Name FROM STUDENT WHERE Student_id = '" + str(id) + "'")
                name = my_cursor.fetchone()
                name = "+".join(name)

                my_cursor.execute(
                    "SELECT Kor_Name FROM STUDENT WHERE Student_id = '" + str(id) + "'")
                kor_name = my_cursor.fetchone()
                kor_name = "+".join(kor_name)
                print(kor_name)

                # if confidence > 75:
                if predict < 80:
                    cv2.rectangle(img, (x - 20, y - 20),
                                  (x + w + 20, y + h + 20), (0, 255, 0), 3)
                    cv2.rectangle(img, (x-20, y-20+h),
                                  (x+20+w, y+20+h+50), (0, 255, 0), cv2.FILLED)
                    cv2.putText(img, f"{id} {name}", (x, y+h + 30),
                                cv2.FONT_HERSHEY_COMPLEX, 0.8, (255, 255, 255), 1)
                    self.mark_attendance(
                        name, kor_name, id, self.var_attendance_course.get())
                else:
                    cv2.rectangle(img, (x-20, y-20),
                                  (x+20 + w, y+20 + h), (0, 0, 255), 3)
                    cv2.putText(img, "Unknown Face", (x, y - 55),
                                cv2.FONT_HERSHEY_COMPLEX, 0.8, (0, 0, 255), 3)

                coord = [x, y, w, y]

            return coord

        def recognize(img, clf, faceCascade):
            coord = draw_boundray(img, faceCascade, 1.1,
                                  10, (255, 25, 255), "Face", clf)
            return img

        face_classifier = cv2.CascadeClassifier(
            "Resources/haarcascade_frontalface_default.xml")
        clf = cv2.face.LBPHFaceRecognizer_create()
        clf.read(self.var_attendance_course.get() + " classifier.xml")

        webcam = cv2.VideoCapture(0)

        while True:
            success, img = webcam.read()
            img = recognize(img, clf, face_classifier)
            cv2.imshow("Attendance", img)

            if cv2.waitKey(1) == 13:
                break
        webcam.release()
        cv2.destroyAllWindows()

    def fetch_data(self):
        conn = mysql.connector.connect(
            host="localhost", username="root", password="123456", database="face_recognizer")
        my_cursor = conn.cursor()
        # WHERE COURSE = '" + self.var_attendance_course.get() +"'")
        my_cursor.execute("SELECT * FROM ATTENDANCE")
        data = my_cursor.fetchall()

        if len(data) != 0:
            self.attendance_table.delete(*self.attendance_table.get_children())
            for i in data:
                self.attendance_table.insert("", END, values=i)
            conn.commit()
        conn.close()

        # Search Data

    def search_data(self):
        conn = mysql.connector.connect(
            host="localhost", username="root", password="123456", database="face_recognizer")
        my_cursor = conn.cursor()
        my_cursor.execute(
            "SELECT * FROM ATTENDANCE WHERE " + str(self.search_by.get()) + " = '" + str(self.search_text.get()) + "'" "AND COURSE = '" + str(self.var_attendance_course.get()) + "'")
        data = my_cursor.fetchall()
        if len(data) != 0:
            self.attendance_table.delete(*self.attendance_table.get_children())
            for i in data:
                self.attendance_table.insert("", END, values=i)
            conn.commit()
        conn.close()

    # Function buttons

    def home_details(self):
        from main import Face_Recognition_System
        self.app = Face_Recognition_System(self.root)


if __name__ == "__main__":
    root = Tk()
    obj = Attendance(root)
    root.mainloop()
