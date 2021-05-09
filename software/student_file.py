from tkinter import *
from tkinter import messagebox
from tkinter import ttk
from PIL import Image, ImageTk
from time import strftime
from datetime import datetime
import cv2
import mysql.connector
import os


class Student:
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

        # ======variables======
        self.var_dep = StringVar()
        self.var_course = StringVar()
        self.var_year = StringVar()
        self.var_semester = StringVar()
        self.var_id = StringVar()
        self.var_name = StringVar()
        self.var_phone = StringVar()
        self.var_kor_name = StringVar()
        self.var_photo_addr = StringVar()
        self.search_by = StringVar()
        self.search_text = StringVar()

        # title
        title_lbl = Label(text="I'm Here", font=("times new roman", 40, "bold"), fg="blue")
        title_lbl.place(x=200, y=0, width=1100, height=100)
        title_lbl2 = Label(text="Student Management System", font=("times new roman", 20, "bold"),
                           fg="blue")
        title_lbl2.place(x=200, y=90, width=1100, height=100)

        #time
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

        # left label frame
        Left_frame = LabelFrame(bd=2, relief=RIDGE, text="Students Details", font=("times new roman", 12, "bold"))
        Left_frame.place(x=20, y=250, width=760, height=580)


        # current course information
        current_course_frame = LabelFrame(Left_frame, bd=2, relief=RIDGE, text="Current Course Info", font=("times new roman", 12, "bold"))
        current_course_frame.place(x=5, y=50, width=745, height=150)

        # Department / Major
        dep_label = Label(current_course_frame, text="Major", font=("times new roman", 12, "bold"))
        dep_label.grid(row=0, column=0, padx=10, pady=10, sticky=W)

        dep_combo = ttk.Combobox(current_course_frame, font=("times new roman", 12, "bold"), state="readonly", textvariable=self.var_dep)
        dep_combo["values"] = ("Computer", "Industrial")
        dep_combo.set("Select Major")
        dep_combo.grid(row=0, column=1, padx=2, pady=10, sticky=W)

        # Course
        course_label = Label(current_course_frame, text="Course", font=("times new roman", 12, "bold"))
        course_label.grid(row=0, column=2, padx=10, pady=10, sticky=W)

        course_combo = ttk.Combobox(current_course_frame, font=("times new roman", 12, "bold"), state="readonly", textvariable=self.var_course)
        # if self.var_dep.get() == "Computer":
        #     course_combo["values"] = ("시스템 프로그래밍", "인공지능", "소프트웨어 프로그래밍")
        # elif self.var_dep.get() == "Industrial":
        #     course_combo["values"] = ("산업공학과", "산공", "산업")
        course_combo["values"] = ("SP", "AI", "SWE")
        course_combo.set("Select Course")
        course_combo.grid(row=0, column=3, padx=2, pady=10, sticky=W)

        # Year
        year_label = Label(current_course_frame, text="Year", font=("times new roman", 12, "bold"))
        year_label.grid(row=1, column=0, padx=10, sticky=W)

        year_combo = ttk.Combobox(current_course_frame, font=("times new roman", 12, "bold"), state="readonly", textvariable=self.var_year)
        year_combo["values"] = ("2021", "2020", "2019")
        year_combo.set("Select Year")
        year_combo.grid(row=1, column=1, padx=2, pady=10, sticky=W)

        # Semester
        semester_label = Label(current_course_frame, text="Semester", font=("times new roman", 12, "bold"))
        semester_label.grid(row=1, column=2, padx=10, sticky=W)

        semester_combo = ttk.Combobox(current_course_frame, font=("times new roman", 12, "bold"), state="readonly", textvariable=self.var_semester)
        semester_combo["values"] = ("1", "2")
        semester_combo.set("Select Semester")
        semester_combo.grid(row=1, column=3, padx=2, pady=10, sticky=W)

        # Class Student information
        class_student_frame = LabelFrame(Left_frame, bd=2, relief=RIDGE, text="Class Student information", font=("times new roman", 12, "bold"))
        class_student_frame.place(x=5, y=250, width=745, height=255)

        # Student ID
        studentID_label = Label(class_student_frame, text="StudentID : ", font=("times new roman", 12, "bold"))
        studentID_label.grid(row=0, column=0, padx=10, sticky=W)

        studentID_entry = ttk.Entry(class_student_frame, width=20, font=("times new roman", 12, "bold"), textvariable=self.var_id)
        studentID_entry.grid(row=0, column=1, padx=10, sticky=W)

        # Student name
        student_name_label = Label(class_student_frame, text="Student Name : ", font=("times new roman", 12, "bold"))
        student_name_label.grid(row=0, column=2, padx=10, pady=10, sticky=W)

        student_name_entry = ttk.Entry(class_student_frame, width=20, font=("times new roman", 12, "bold"), textvariable=self.var_name)
        student_name_entry.grid(row=0, column=3, padx=10, pady=10, sticky=W)

        # Student Phone no
        phone_label = Label(class_student_frame, text="Phone (000-0000-0000):", font=("times new roman", 12, "bold"))
        phone_label.grid(row=1, column=0, padx=10, pady=10, sticky=W)

        phone_entry = ttk.Entry(class_student_frame, width=20, font=("times new roman", 12, "bold"), textvariable=self.var_phone)
        phone_entry.grid(row=1, column=1, padx=10, pady=10, sticky=W)

        # Korean name
        email_label = Label(class_student_frame, text="Student Korean Name : ", font=("times new roman", 12, "bold"))
        email_label.grid(row=1, column=2, padx=10, pady=10, sticky=W)

        email_entry = ttk.Entry(class_student_frame, width=20, font=("times new roman", 12, "bold"), textvariable=self.var_kor_name)
        email_entry.grid(row=1, column=3, padx=10, pady=10, sticky=W)

        # # Gender
        # gender_label = Label(class_student_frame, text="Gender : ", font=("times new roman", 12, "bold"))
        # gender_label.grid(row=2, column=0, padx=10, pady=10, sticky=W)
        #
        # gender_combo = ttk.Combobox(class_student_frame, font=("times new roman", 12, "bold"), state="readonly", textvariable=self.var_gender, width = 18)
        # gender_combo["values"] = ("Select Gender", "Male", "Female")
        # gender_combo.current(0)
        # gender_combo.grid(row=2, column=1, padx=10, pady=10, sticky=W)

        # gender_entry = ttk.Entry(class_student_frame, width=20, font=("times new roman", 12, "bold"), textvariable=self.var_gender)
        # gender_entry.grid(row=2, column=1, padx=10, pady=10, sticky=W)

        # # DOB
        # dob_label = Label(class_student_frame, text="DOB : ", font=("times new roman", 12, "bold"))
        # dob_label.grid(row=2, column=2, padx=10, pady=10, sticky=W)
        #
        # dob_entry = ttk.Entry(class_student_frame, width=20, font=("times new roman", 12, "bold"), textvariable=self.var_dob)
        # dob_entry.grid(row=2, column=3, padx=10, pady=10, sticky=W)

        # Radio Button
        # radiobtn1 = ttk.Radiobutton(class_student_frame, text="Take Photo Sample", value="Yes", variable=self.var_radio1)
        # radiobtn1.grid(row=2, column=0, padx=10, pady=10, sticky=W)

        # radiobtn2 = ttk.Radiobutton(class_student_frame, text="NO Photo Sample", value="No", variable=self.var_radio1)
        # radiobtn2.grid(row=2, column=1, padx=10, pady=10, sticky=W)

        # take photo
        take_photo_btn = Button(class_student_frame, text="Take Sample Photo", font=("times new roman", 12, "bold"), width=15, command=self.take_photo)
        take_photo_btn.grid(row=2, column=0)

        take_photo_entry = ttk.Entry(class_student_frame, width=65, font=("times new roman", 12, "bold"), textvariable=self.var_photo_addr)
        take_photo_entry.place(x=200, y=95)

        empty_label = Label(class_student_frame)
        empty_label.grid(row=3, column=0, pady=3)

        save_btn = Button(class_student_frame, text="Save", font=("times new roman", 12, "bold"), width=20, command=self.add_data)
        save_btn.grid(row=4, column=0)

        update_btn = Button(class_student_frame, text="Update", font=("times new roman", 12, "bold"), width=20, command=self.update_data)
        update_btn.grid(row=4, column=2)

        delete_btn = Button(class_student_frame, text="Delete", font=("times new roman", 12, "bold"), width=20, command=self.delete_data)
        delete_btn.grid(row=5, column=0)

        reset_btn = Button(class_student_frame, text="Reset", font=("times new roman", 12, "bold"), width=20, command=self.reset_data)
        reset_btn.grid(row=5, column=2)

        # buttons frame
        # btn_Frame = Frame(class_student_frame, bd=2, relief=RIDGE)
        # btn_Frame.place(x=0, y=190, width=740, height=40)

        # take_photo_btn = Button(btn_Frame, text="Take Sample Photo", font=("times new roman", 12, "bold"), width=40)
        # take_photo_btn.grid(row=0, column=0)
        #
        # update_photo_btn = Button(btn_Frame, text="Update Sample Photo", font=("times new roman", 12, "bold"), width=40)
        # update_photo_btn.grid(row=0, column=1)



        # ========Right label frame==========
        Right_frame = LabelFrame(bd=2, relief=RIDGE, text="Students Details", font=("times new roman", 12, "bold"))
        Right_frame.place(x=800, y=250, width=660, height=580)

        # Search System
        Search_frame = LabelFrame(Right_frame, bd=2, relief=RIDGE, text="Search System", font=("times new roman", 12, "bold"))
        Search_frame.place(x=5, y=50, width=650, height=70)

        search_label = Label(Search_frame, text="Search By : ", font=("times new roman", 12, "bold"))
        search_label.grid(row=0, column=0, padx=10, pady=10, sticky=W)

        search_combo = ttk.Combobox(Search_frame, textvariable=self.search_by, font=("times new roman", 12, "bold"), state="readonly", width=10)
        search_combo["values"] = ("Course", "Student_id")
        search_combo.set("Select")
        search_combo.grid(row=0, column=1, padx=2, pady=10, sticky=W)

        search_entry = ttk.Entry(Search_frame, textvariable=self.search_text, width=15, font=("times new roman", 13, "bold"))
        search_entry.grid(row=0, column=2, padx=10, pady=5, sticky=W)

        search_btn = Button(Search_frame, text="Search", font=("times new roman", 12, "bold"), width=13, command=self.search_data)
        search_btn.grid(row=0, column=3, padx=4)

        show_all_btn = Button(Search_frame, text="Show All", font=("times new roman", 12, "bold"), width=13, command=self.fetch_data)
        show_all_btn.grid(row=0, column=4, padx=4)

        # table frame
        table_frame = Frame(Right_frame, bd=2, relief=RIDGE)
        table_frame.place(x=5, y=130, width=650, height=420)

        scroll_x = ttk.Scrollbar(table_frame, orient=HORIZONTAL)
        scroll_y = ttk.Scrollbar(table_frame, orient=VERTICAL)

        self.student_table = ttk.Treeview(table_frame, column=("ID", "Name", "Korean Name", "Dep", "Course", "Year", "Semester", "Phone No"), xscrollcommand=scroll_x.set, yscrollcommand=scroll_y.set)

        scroll_x.pack(side=BOTTOM, fill=X)
        scroll_y.pack(side=RIGHT, fill=Y)
        scroll_x.config(command=self.student_table.xview)
        scroll_y.config(command=self.student_table.yview)

        self.student_table.heading("Dep", text="Department")
        self.student_table.heading("Course", text="Course")
        self.student_table.heading("Year", text="Year")
        self.student_table.heading("Semester", text="Semester")
        self.student_table.heading("ID", text="ID")
        self.student_table.heading("Name", text="Name")
        self.student_table.heading("Korean Name", text="Korean Name")
        self.student_table.heading("Phone No", text="Phone No")
        self.student_table["show"] = "headings"

        self.student_table.column("Dep", width=100)
        self.student_table.column("Course", width=100)
        self.student_table.column("Year", width=100)
        self.student_table.column("Semester", width=100)
        self.student_table.column("ID", width=100)
        self.student_table.column("Name", width=100)
        self.student_table.column("Korean Name", width=100)
        self.student_table.column("Phone No", width=100)

        self.student_table.pack(fill=BOTH, expand=1)
        self.student_table.bind("<ButtonRelease>", self.get_data)
        self.fetch_data()

    # ===============================================================================================================

    # Function buttons
    def home_details(self):
        from main import Face_Recognition_System
        self.app = Face_Recognition_System(self.root)

    # Function declaration
    # Add Data to DB
    def add_data(self):
        if self.var_course.get() == "Select Course" or self.var_name.get() == "" or self.var_id.get() == "" or self.var_kor_name.get() == "" or self.var_photo_addr.get() == "":
            messagebox.showerror("Error", "All Fields are required", parent=self.root)
        else:
            try:
                conn = mysql.connector.connect(host="localhost", username="root", password="123456", database="face_recognizer")
                my_cursor = conn.cursor()
                my_cursor.execute("insert into student values(%s,%s,%s,%s,%s,%s,%s,%s,%s)",(
                                                                                            self.var_id.get(),
                                                                                            self.var_name.get(),
                                                                                            self.var_kor_name.get(),
                                                                                            self.var_dep.get(),
                                                                                            self.var_course.get(),
                                                                                            self.var_year.get(),
                                                                                            self.var_semester.get(),
                                                                                            self.var_phone.get(),
                                                                                            self.var_photo_addr.get(),
                                                                                            ))
                conn.commit()
                self.fetch_data()
                conn.close()
                messagebox.showinfo("Success", f"{self.var_kor_name.get()} Data Saved Successfully", parent=self.root)
            except Exception as es:
                messagebox.showerror("Error", f"Due To: {str(es)}", parent=self.root)

    # Fetch Data
    def fetch_data(self):
        conn = mysql.connector.connect(host="localhost", username="root", password="123456", database="face_recognizer")
        my_cursor = conn.cursor()
        my_cursor.execute("select * from student")
        data = my_cursor.fetchall()

        if len(data) != 0:
            self.student_table.delete(*self.student_table.get_children())
            for i in data:
                self.student_table.insert("", END, values=i)
            conn.commit()
        conn.close()

    # Get Data
    def get_data(self, event=""):
        cursor_focus = self.student_table.focus()
        content = self.student_table.item(cursor_focus)
        data = content["values"]

        self.var_id.set(data[0])
        self.var_name.set(data[1])
        self.var_kor_name.set(data[2])
        self.var_dep.set(data[3])
        self.var_course.set(data[4])
        self.var_year.set(data[5])
        self.var_semester.set(data[6])
        self.var_phone.set(data[7])
        self.var_photo_addr.set(data[8])

    # Update Data -> 오류 날 수도 있음
    def update_data(self):
        messagebox.showerror("Error", "Can not Update Data\nAsk Professor to Update Data", parent=self.root)

    # Delete Data -> 오류 날 수도 있음
    def delete_data(self):
        messagebox.showerror("Error", "Can not Delete Data\nAsk Professor to Delete Data", parent=self.root)

    # Reset Data
    def reset_data(self):
        self.var_id.set("")
        self.var_name.set("")
        self.var_kor_name.set("")
        self.var_dep.set("Select Major")
        self.var_course.set("Select Course")
        self.var_year.set("Select Year")
        self.var_semester.set("Select Semester")
        self.var_phone.set("")
        self.var_photo_addr.set("")


    #========== Take photo samples ===========
    def take_photo(self):
        if self.var_dep.get() == "Select Department" or self.var_name.get() == "" or self.var_id.get() == "":
            messagebox.showerror("Error", "All Fields are required", parent=self.root)
        else:
            face_classifier = cv2.CascadeClassifier("Resources/haarcascade_frontalface_default.xml")
            webcam = cv2.VideoCapture(0, cv2.CAP_DSHOW)
            img_id = self.var_id.get()
            img_course = self.var_course.get()
            path = 'C:/Users/LG/PycharmProjects/SmartAttendance/FaceImages/' + img_course #+ '/' + img_id
            num_images = 0
            while True:
                success, img = webcam.read()
                imgGray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
                face_location = face_classifier.detectMultiScale(imgGray, 1.1, 5)
                for (x, y, w, h) in face_location:
                    cv2.rectangle(img, (x, y), (x + w, y + h), (255, 0, 0), 2)
                    num_images += 1
                    if not os.path.isdir(path):         # FaceImages/과목명
                        os.mkdir(path)

                    file = path + '/' + img_id          # FaceImages/과목명/학번
                    if not os.path.isdir(file):
                        os.mkdir(file)
                    pic_path = file + "/" + str(num_images) + ".jpg"
                    cv2.imwrite(pic_path, imgGray)
                    print(file)

                cv2.imshow("Result", img)
                if cv2.waitKey(1) == 13 or int(num_images) == 100:
                    break
            webcam.release()
            cv2.destroyAllWindows()
            self.var_photo_addr.set(path)
            messagebox.showinfo("Result", f"{self.var_kor_name.get()} face data added\n Click Save Button")

    # Search Data
    def search_data(self):
        conn = mysql.connector.connect(host="localhost", username="root", password="123456", database="face_recognizer")
        my_cursor = conn.cursor()
        my_cursor.execute("SELECT * FROM STUDENT WHERE " + str(self.search_by.get()) + " = '" + str(self.search_text.get()) + "'")
        data = my_cursor.fetchall()
        if len(data) != 0:
            self.student_table.delete(*self.student_table.get_children())
            for i in data:
                self.student_table.insert("", END, values=i)
            conn.commit()
        conn.close()




if __name__ == "__main__":
    root = Tk()
    obj = Student(root)
    root.mainloop()
