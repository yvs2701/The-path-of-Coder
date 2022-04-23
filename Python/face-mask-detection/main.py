import joblib
import cv2
import numpy as np

if __name__ == '__main__':
    with_mask = np.load('test/data/with_mask.npy')
    row_with_mask, a, b, c = with_mask.shape
    with_mask = with_mask.reshape(row_with_mask, a*b*c)

    without_mask = np.load('test/data/without_mask.npy')
    row_without_mask, a, b, c = without_mask.shape
    without_mask = without_mask.reshape(row_without_mask, a*b*c)

    wrong_mask = np.load('test/data/wrong_mask.npy')
    row_wrong_mask, a, b, c = wrong_mask.shape
    wrong_mask = wrong_mask.reshape(row_wrong_mask, a*b*c)

    X = np.r_[with_mask, without_mask, wrong_mask] # merge them row wise
    labels = np.zeros(X.shape[0])
    # divide the array in with mask (0) and without mask
    labels[row_with_mask:] = 1 # all the rows from from last row_with_mask to end is rows without mask
    labels[row_with_mask + row_wrong_mask:] = 2 # all the rows from from last row_with_mask to end is rows under the nose
    rectangle_label = {0: 'Wearing Mask', 1: 'No Mask', 2: 'Under the Nose'}

    haar_data = cv2.CascadeClassifier('data/haarcascade_frontal_face_default.xml')
    capture = cv2.VideoCapture(0)

    svm = joblib.load('test/model/svm_model.pkl')

    print('press \'q\' to exit')
    # detecting mask from video stream
    while True:
        cam_available, img = capture.read()
        if cam_available:
            faces = haar_data.detectMultiScale(img)
            for x, y, w, h in faces:
                # slice face from the frame
                face = img[y:y+h, x:x+w, :]
                # resize face to match collected data's image dimensions
                face = cv2.resize(face, (a, b)).reshape(1, -1)

                pred = svm.predict(face)[0] # get the label/class

                if pred == 0:
                    cv2.rectangle(img, (x,y), (x+w,y+h), (0, 255, 0), 4) # green rectangle (wearing mask)
                elif pred == 1:
                    cv2.rectangle(img, (x,y), (x+w,y+h), (0, 0, 255), 4) # red rectangle (no mask)
                elif pred == 2:
                    cv2.rectangle(img, (x,y), (x+w,y+h), (0, 255, 255), 4) # yellow rectangle (no mask)
                # out a label on this rectangle
                cv2.putText(img=img, text=rectangle_label[pred], org=(x,y), fontFace=cv2.FONT_HERSHEY_SIMPLEX, fontScale=1, color=(255,255,255), thickness=2)

            cv2.imshow('Face Mask Detection', img)
            if cv2.waitKey(1) & 0xFF == ord('q'):
                break # break if user presses 'q'

    capture.release()
    cv2.destroyAllWindows()
