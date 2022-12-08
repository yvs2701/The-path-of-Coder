import cv2
import numpy as np
import time
from os.path import exists

def saveDataFromCamera(haar_data, fileName="cam_data"):
    data =[] # used to collect data from the camera
    print('press \'q\' to exit')
    capture = cv2.VideoCapture(0) # initialze camera
    # start capturing data
    while True:
        cam_available, img = capture.read() # read frame by frame
        if cam_available:
            faces = haar_data.detectMultiScale(img) # detect face from frame
            for x, y, w, h in faces:
                cv2.rectangle(img, (x, y), (x+w, y+h), (255, 0, 0), 4) # draw blue rectangle on face
                face = img[y:y+h, x:x+w, : ] # slicing the face from the frame, and making the copy of that slice

                face = cv2.resize(face, (100, 100), interpolation=cv2.INTER_CUBIC) # resize face to 100 x 100
                # INTER_CUBIC or INTER_LANCZOS4 produced better quality images in my tests

                if len(data) < 200: # store only 200 images
                    data.append(face) # store the face data
            cv2.imshow('Capturing', img) # show image in new openCV window

            if cv2.waitKey(1) & 0xFF == ord('q') or len(data) >= 200:
                break # break if user presses 'q'
    capture.release() # release camera resource held by openCV
    cv2.destroyAllWindows() # close all windows

    if exists(fileName):
        old_Data = np.load(fileName)
        data = np.append(old_Data, data, axis=0) # axis 0 will append the rows or new data below the old one
    np.save(fileName, data)

    # checking the data
    # stored_Data = np.load(fileName)
    # print("Data shape = ", stored_Data.shape)

if __name__ == "__main__":
    haar_data = cv2.CascadeClassifier('data/haarcascade_frontal_face_default.xml')
    saveDataFromCamera(haar_data=haar_data, fileName="data/without_mask.npy")
    time.sleep(10) # wait for 10 seconds for user to wear his mask
    saveDataFromCamera(haar_data=haar_data, fileName="data/with_mask.npy")
