from tkinter import *
from tkinter import ttk
from tkinter import messagebox
import pickle
from PIL import Image, ImageTk, ImageFont, ImageDraw
from time import strftime
from datetime import datetime
import time
import os
import numpy as np
import cv2
import json
import requests
import csv


class Attendance:
    def __init__(self, root, id):
        self.root = root
        self.root.geometry("1500x900+0+0")
        self.root.title("Face Recognition System")
        self.root.resizable(False, False)
        self.id = id

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
        img1 = Image.open(r"./Images/home.jpg")
        img1 = img1.resize((200, 200), Image.ANTIALIAS)
        self.photoimg1 = ImageTk.PhotoImage(img1)

        b1 = Button(image=self.photoimg1, cursor="hand2",
                    command=self.home_details)
        b1.place(x=0, y=0, width=200, height=200)

        # second image
        img2 = Image.open(r"./Images/face-id.jpg")
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
        course_label = Label(attendance_frame, text="Course", font=(
            "times new roman", 12, "bold"))
        course_label.place(x=30, y=70)

        select_course_combo = ttk.Combobox(attendance_frame, font=("times new roman", 12, "bold"), state="readonly",
                                           textvariable=self.var_attendance_course)
        select_course_combo["values"] = ("AI", "SWE", "SP")
        select_course_combo.set("Select Course")
        select_course_combo.place(x=100, y =70)

        start_btn = Button(attendance_frame, text="Start Attendance", font=(
            "times new roman", 12, "bold"), width=15, command=self.face_recognition)
        start_btn.place(x=330, y=65)

        # Right Frame
        Right_frame = LabelFrame(bd=2, relief=RIDGE, text="Attendance Chart", font=(
            "times new roman", 12, "bold"))
        Right_frame.place(x=600, y=250, width=850, height=580)


        # Attendance Frame
        table_frame = Frame(Right_frame, bd=2, relief=RIDGE)
        table_frame.place(x=0, y=0, width=830, height=550)

        scroll_x = ttk.Scrollbar(table_frame, orient=HORIZONTAL)
        scroll_y = ttk.Scrollbar(table_frame, orient=VERTICAL)

        self.attendance_table = ttk.Treeview(table_frame, column=(
            "ID", "Course", "Date", "Time"), xscrollcommand=scroll_x.set,
            yscrollcommand=scroll_y.set)

        scroll_x.pack(side=BOTTOM, fill=X)
        scroll_y.pack(side=RIGHT, fill=Y)
        scroll_x.config(command=self.attendance_table.xview)
        scroll_y.config(command=self.attendance_table.yview)

        self.attendance_table.heading("ID", text="ID")
        self.attendance_table.heading("Course", text="Course")
        self.attendance_table.heading("Date", text="Date")
        self.attendance_table.heading("Time", text="Time")
        self.attendance_table["show"] = "headings"

        self.attendance_table.column("ID", width=100)
        self.attendance_table.column("Course", width=100)
        self.attendance_table.column("Date", width=100)
        self.attendance_table.column("Date", width=100)

        self.attendance_table.pack(fill=BOTH, expand=1)

    def train_classifier(self):
        if self.var_course.get() == "Select Course":
            messagebox.showerror(
                "Error", "Please select course", parent=self.root)
        else:
            face_classifier = cv2.CascadeClassifier("Resources/haarcascade_frontalface_default.xml")
            recognizer = cv2.face.LBPHFaceRecognizer_create()

            # 사진 경로
            BASE_DIR = os.path.dirname(os.path.abspath(__file__))
            image_dir = os.path.join(BASE_DIR, "FaceImages")
            image_dir = os.path.join(image_dir, str(self.id))
            image_dir = os.path.join(image_dir, self.var_course.get())

            current_id = 0
            label_ids = {}
            y_labels = []
            x_train = []

            for root, dirs, files in os.walk(image_dir):
                for file in files:
                    if file.endswith("png") or file.endswith("jpg"):
                        path = os.path.join(root, file)
                        label = os.path.basename(root)  # 해당 사진의 학번
                        # print(path, label)
                        # label 리스트 만들기
                        if not label in label_ids:
                            label_ids[label] = current_id   # {"B511006" : "0" , ...}
                            current_id += 1
                        id_ = label_ids[label]
                        pil_image = Image.open(path).convert("L") # grayscale
                        final_image = pil_image.resize((550,550), Image.ANTIALIAS)
                        image_array = np.array(final_image, "uint8")
                        cv2.imshow("Training", image_array)
                        cv2.waitKey(1) == 13
                        face_location = face_classifier.detectMultiScale(image_array, scaleFactor=1.5, minNeighbors=5)
                        for (x,y,w,h) in face_location:
                            roi = image_array[y:y+h, x:x+w]
                            x_train.append(roi)
                            y_labels.append(id_)

            file = str(self.id) + "_" + self.var_course.get() + ".pickle"
            print(label_ids)
            with open(file, "wb+") as f:
                pickle.dump(label_ids, f)

            recognizer.train(x_train, np.array(y_labels))
            recognizer.save(str(self.id) + "_" + self.var_course.get() + "_face-train.yml")
            cv2.destroyAllWindows()
            messagebox.showinfo("Result", "Training datasets completed!!")

    def face_recognition(self):

        for item in self.attendance_table.get_children():
            self.attendance_table.delete(item)

        js = {"profID": str(self.id), "course": self.var_attendance_course.get()}
        jsonObject = json.dumps(js)  # JSOn 형태로 바꾸기
        print(jsonObject)
        r = requests.post(url="http://localhost:8080/sw/date", data=jsonObject,
                          headers={'Content-Type': 'application/json'})


        face_classifier = cv2.CascadeClassifier("Resources/haarcascade_frontalface_default.xml")
        recognizer = cv2.face.LBPHFaceRecognizer_create()
        recognizer.read(str(self.id) + "_" + self.var_attendance_course.get() + "_face-train.yml")

        labels = {}
        file = str(self.id) + "_" + self.var_attendance_course.get() + ".pickle"
        with open(file, "rb") as f:
            original_labels = pickle.load(f)
            labels = {v:k for k,v in original_labels.items()}

        print(len(labels))
        attendance_list = []
        webcam = cv2.VideoCapture(0, cv2.CAP_DSHOW)
        cur_min = datetime.now().minute

        while True:
            success, img = webcam.read()
            #img = recognize(img, recognizer, face_classifier)
            gray_img = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
            faces = face_classifier.detectMultiScale(gray_img, scaleFactor=1.5, minNeighbors=5)

            for (x,y,w,h) in faces:
                roi_gray = gray_img[y:y+h, x:x+w]
                id, conf = recognizer.predict(roi_gray)
                print(conf)


                if conf <= 70:
                    cv2.rectangle(img, (x - 20, y - 20), (x + w + 20, y + h + 20), (0, 255, 0), 3)
                    cv2.rectangle(img, (x-20, y+h), (x+20+w, y+20+h+50), (0, 255, 0), cv2.FILLED)
                    cv2.putText(img, f"{labels[id]}", (x, y + h + 30), cv2.FONT_HERSHEY_COMPLEX, 0.8, (255, 255, 255), 3)
                    # 민수한테 출석한 학번 전달
                    # 교수id, 강의명, 학생학번, 날짜, 시간
                    if labels[id] not in attendance_list:
                        # 날짜 시간
                        now = datetime.now()
                        string_time = now.strftime('%H:%M:%S')
                        string_date = now.strftime('%Y-%m-%d')

                        # 교수id, 강의명, 학생학번, 날짜, 시간 딕셔너리에 넣기
                        js = {"profID": str(self.id), "course": self.var_attendance_course.get(),
                              "studentID": labels[id], "date":string_date, "time":string_time}
                        jsonObject = json.dumps(js)  # JsOn 형태로 바꾸기
                        print(jsonObject)
                        r = requests.post(url="http://localhost:8080/sw/check", data=jsonObject,
                                          headers={'Content-Type': 'application/json'})




                        # True이면 attendance_list에 넣기 / False이면 attendance_list에 저장하지 말기
                        if r.text == "True":
                            # 해당 학생 레이블 가져오기
                            attendance_list.append(labels[id])
                            print(labels[id])
                            print(len(attendance_list))
                            self.attendance_table.insert("", "end", text="", values=(labels[id], self.var_attendance_course.get(), string_date, string_time))

                            # messagebox.showinfo("Attendance", labels[id] + " 출석 처리 완료")

                else:
                    cv2.rectangle(img, (x - 20, y - 20), (x + w + 20, y + h + 20), (0, 0, 255), 3)
                    cv2.rectangle(img, (x - 20, y - 20 + h), (x + 20 + w, y + 20 + h + 50), (0, 0, 255), cv2.FILLED)
                    cv2.putText(img, "Unknown Face", (x, y + h + 30), cv2.FONT_HERSHEY_COMPLEX, 0.8, (255, 255, 255), 3)


            cv2.imshow("Attendance", img)
            if cv2.waitKey(1) == 13 :#or cur_min+5 == datetime.now().minute:
                print(attendance_list)
                break

        webcam.release()
        cv2.destroyAllWindows()

    # Function buttons

    def home_details(self):
        from main import Face_Recognition_System
        self.app = Face_Recognition_System(self.root, self.id)


if __name__ == "__main__":
    root = Tk()
    obj = Attendance(root, -1)
    root.mainloop()
